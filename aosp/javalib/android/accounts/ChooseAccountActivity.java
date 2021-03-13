package android.accounts;

import android.app.Activity;
import android.app.ActivityTaskManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.HashMap;

public class ChooseAccountActivity extends Activity {
  private static final String TAG = "AccountManager";
  
  private AccountManagerResponse mAccountManagerResponse = null;
  
  private Parcelable[] mAccounts = null;
  
  private String mCallingPackage;
  
  private int mCallingUid;
  
  private Bundle mResult;
  
  private HashMap<String, AuthenticatorDescription> mTypeToAuthDescription = new HashMap<>();
  
  private void getAuthDescriptions() {
    for (AuthenticatorDescription authenticatorDescription : AccountManager.get((Context)this).getAuthenticatorTypes())
      this.mTypeToAuthDescription.put(authenticatorDescription.type, authenticatorDescription); 
  }
  
  private Drawable getDrawableForType(String paramString) {
    AuthenticatorDescription authenticatorDescription1 = null;
    PackageManager.NameNotFoundException nameNotFoundException = null;
    AuthenticatorDescription authenticatorDescription2 = authenticatorDescription1;
    if (this.mTypeToAuthDescription.containsKey(paramString))
      try {
        authenticatorDescription2 = this.mTypeToAuthDescription.get(paramString);
        Drawable drawable = createPackageContext(authenticatorDescription2.packageName, 0).getDrawable(authenticatorDescription2.iconId);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException1) {
        nameNotFoundException1 = nameNotFoundException;
        if (Log.isLoggable("AccountManager", 5)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No icon name for account type ");
          stringBuilder.append(paramString);
          Log.w("AccountManager", stringBuilder.toString());
          PackageManager.NameNotFoundException nameNotFoundException2 = nameNotFoundException;
        } 
      } catch (android.content.res.Resources.NotFoundException notFoundException) {
        AuthenticatorDescription authenticatorDescription = authenticatorDescription1;
        if (Log.isLoggable("AccountManager", 5)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No icon resource for account type ");
          stringBuilder.append(paramString);
          Log.w("AccountManager", stringBuilder.toString());
          authenticatorDescription2 = authenticatorDescription1;
        } 
      }  
    return (Drawable)authenticatorDescription2;
  }
  
  public void finish() {
    AccountManagerResponse accountManagerResponse = this.mAccountManagerResponse;
    if (accountManagerResponse != null) {
      Bundle bundle = this.mResult;
      if (bundle != null) {
        accountManagerResponse.onResult(bundle);
      } else {
        accountManagerResponse.onError(4, "canceled");
      } 
    } 
    super.finish();
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.mAccounts = getIntent().getParcelableArrayExtra("accounts");
    this.mAccountManagerResponse = (AccountManagerResponse)getIntent().getParcelableExtra("accountManagerResponse");
    if (this.mAccounts == null) {
      setResult(0);
      finish();
      return;
    } 
    try {
      IBinder iBinder = getActivityToken();
      this.mCallingUid = ActivityTaskManager.getService().getLaunchedFromUid(iBinder);
      this.mCallingPackage = ActivityTaskManager.getService().getLaunchedFromPackage(iBinder);
    } catch (RemoteException remoteException) {
      String str = getClass().getSimpleName();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to get caller identity \n");
      stringBuilder.append(remoteException);
      Log.w(str, stringBuilder.toString());
    } 
    if (UserHandle.isSameApp(this.mCallingUid, 1000) && getIntent().getStringExtra("androidPackageName") != null)
      this.mCallingPackage = getIntent().getStringExtra("androidPackageName"); 
    if (!UserHandle.isSameApp(this.mCallingUid, 1000) && getIntent().getStringExtra("androidPackageName") != null) {
      String str = getClass().getSimpleName();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Non-system Uid: ");
      stringBuilder.append(this.mCallingUid);
      stringBuilder.append(" tried to override packageName \n");
      Log.w(str, stringBuilder.toString());
    } 
    getAuthDescriptions();
    AccountInfo[] arrayOfAccountInfo = new AccountInfo[this.mAccounts.length];
    byte b = 0;
    while (true) {
      Parcelable[] arrayOfParcelable = this.mAccounts;
      if (b < arrayOfParcelable.length) {
        arrayOfAccountInfo[b] = new AccountInfo(((Account)arrayOfParcelable[b]).name, getDrawableForType(((Account)this.mAccounts[b]).type));
        b++;
        continue;
      } 
      setContentView(17367119);
      ListView listView = (ListView)findViewById(16908298);
      listView.setAdapter((ListAdapter)new AccountArrayAdapter((Context)this, 17367043, arrayOfAccountInfo));
      listView.setChoiceMode(1);
      listView.setTextFilterEnabled(true);
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
              ChooseAccountActivity.this.onListItemClick((ListView)param1AdapterView, param1View, param1Int, param1Long);
            }
          });
      return;
    } 
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong) {
    Account account = (Account)this.mAccounts[paramInt];
    AccountManager accountManager = AccountManager.get((Context)this);
    Integer integer = Integer.valueOf(accountManager.getAccountVisibility(account, this.mCallingPackage));
    if (integer != null && integer.intValue() == 4)
      accountManager.setAccountVisibility(account, this.mCallingPackage, 2); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("selected account ");
    stringBuilder.append(account);
    Log.d("AccountManager", stringBuilder.toString());
    Bundle bundle = new Bundle();
    bundle.putString("authAccount", account.name);
    bundle.putString("accountType", account.type);
    this.mResult = bundle;
    finish();
  }
  
  private static class AccountArrayAdapter extends ArrayAdapter<AccountInfo> {
    private ChooseAccountActivity.AccountInfo[] mInfos;
    
    private LayoutInflater mLayoutInflater;
    
    public AccountArrayAdapter(Context param1Context, int param1Int, ChooseAccountActivity.AccountInfo[] param1ArrayOfAccountInfo) {
      super(param1Context, param1Int, (Object[])param1ArrayOfAccountInfo);
      this.mInfos = param1ArrayOfAccountInfo;
      this.mLayoutInflater = (LayoutInflater)param1Context.getSystemService("layout_inflater");
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      ChooseAccountActivity.ViewHolder viewHolder1;
      ChooseAccountActivity.ViewHolder viewHolder2;
      if (param1View == null) {
        View view = this.mLayoutInflater.inflate(17367120, null);
        viewHolder1 = new ChooseAccountActivity.ViewHolder();
        viewHolder1.text = (TextView)view.findViewById(16908694);
        viewHolder1.icon = (ImageView)view.findViewById(16908693);
        view.setTag(viewHolder1);
      } else {
        ChooseAccountActivity.ViewHolder viewHolder = (ChooseAccountActivity.ViewHolder)viewHolder1.getTag();
        viewHolder2 = viewHolder1;
        viewHolder1 = viewHolder;
      } 
      viewHolder1.text.setText((this.mInfos[param1Int]).name);
      viewHolder1.icon.setImageDrawable((this.mInfos[param1Int]).drawable);
      return (View)viewHolder2;
    }
  }
  
  private static class AccountInfo {
    final Drawable drawable;
    
    final String name;
    
    AccountInfo(String param1String, Drawable param1Drawable) {
      this.name = param1String;
      this.drawable = param1Drawable;
    }
  }
  
  private static class ViewHolder {
    ImageView icon;
    
    TextView text;
    
    private ViewHolder() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/ChooseAccountActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */