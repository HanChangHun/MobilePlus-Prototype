package android.accounts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ChooseAccountTypeActivity extends Activity {
  private static final String TAG = "AccountChooser";
  
  private ArrayList<AuthInfo> mAuthenticatorInfosToDisplay;
  
  private HashMap<String, AuthInfo> mTypeToAuthenticatorInfo = new HashMap<>();
  
  private void buildTypeToAuthDescriptionMap() {
    for (AuthenticatorDescription authenticatorDescription : AccountManager.get((Context)this).getAuthenticatorTypes()) {
      String str3;
      String str5;
      String str1 = null;
      String str2 = null;
      Drawable drawable1 = null;
      Drawable drawable2 = null;
      Drawable drawable3 = null;
      String str4 = str1;
      Drawable drawable4 = drawable3;
      String str6 = str2;
      Drawable drawable5 = drawable2;
      try {
        String str;
        Context context = createPackageContext(authenticatorDescription.packageName, 0);
        str4 = str1;
        drawable4 = drawable3;
        str6 = str2;
        drawable5 = drawable2;
        drawable3 = context.getDrawable(authenticatorDescription.iconId);
        str4 = str1;
        drawable4 = drawable3;
        str6 = str2;
        drawable5 = drawable3;
        CharSequence charSequence = context.getResources().getText(authenticatorDescription.labelId);
        drawable5 = drawable1;
        if (charSequence != null) {
          str4 = str1;
          drawable4 = drawable3;
          str6 = str2;
          drawable5 = drawable3;
          str1 = charSequence.toString();
          str = str1;
        } 
        str4 = str;
        drawable4 = drawable3;
        str6 = str;
        drawable5 = drawable3;
        str1 = charSequence.toString();
        str5 = str1;
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        str5 = str6;
        drawable3 = drawable5;
        if (Log.isLoggable("AccountChooser", 5)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No icon name for account type ");
          stringBuilder.append(authenticatorDescription.type);
          Log.w("AccountChooser", stringBuilder.toString());
          drawable3 = drawable5;
          str5 = str6;
        } 
      } catch (android.content.res.Resources.NotFoundException notFoundException) {
        String str = str4;
        str3 = str5;
        if (Log.isLoggable("AccountChooser", 5)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No icon resource for account type ");
          stringBuilder.append(authenticatorDescription.type);
          Log.w("AccountChooser", stringBuilder.toString());
          String str8 = str4;
          str3 = str5;
        } 
      } 
      String str7 = str5;
      AuthInfo authInfo = new AuthInfo(authenticatorDescription, str7, (Drawable)str3);
      this.mTypeToAuthenticatorInfo.put(authenticatorDescription.type, authInfo);
    } 
  }
  
  private void setResultAndFinish(String paramString) {
    Bundle bundle = new Bundle();
    bundle.putString("accountType", paramString);
    setResult(-1, (new Intent()).putExtras(bundle));
    if (Log.isLoggable("AccountChooser", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ChooseAccountTypeActivity.setResultAndFinish: selected account type ");
      stringBuilder.append(paramString);
      Log.v("AccountChooser", stringBuilder.toString());
    } 
    finish();
  }
  
  public void onCreate(Bundle paramBundle) {
    HashSet<String> hashSet;
    super.onCreate(paramBundle);
    if (Log.isLoggable("AccountChooser", 2)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ChooseAccountTypeActivity.onCreate(savedInstanceState=");
      stringBuilder.append(paramBundle);
      stringBuilder.append(")");
      Log.v("AccountChooser", stringBuilder.toString());
    } 
    paramBundle = null;
    String[] arrayOfString = getIntent().getStringArrayExtra("allowableAccountTypes");
    if (arrayOfString != null) {
      HashSet<String> hashSet1 = new HashSet(arrayOfString.length);
      int i = arrayOfString.length;
      byte b = 0;
      while (true) {
        hashSet = hashSet1;
        if (b < i) {
          hashSet1.add(arrayOfString[b]);
          b++;
          continue;
        } 
        break;
      } 
    } 
    buildTypeToAuthDescriptionMap();
    this.mAuthenticatorInfosToDisplay = new ArrayList<>(this.mTypeToAuthenticatorInfo.size());
    for (Map.Entry<String, AuthInfo> entry : this.mTypeToAuthenticatorInfo.entrySet()) {
      String str = (String)entry.getKey();
      AuthInfo authInfo = (AuthInfo)entry.getValue();
      if (hashSet != null && !hashSet.contains(str))
        continue; 
      this.mAuthenticatorInfosToDisplay.add(authInfo);
    } 
    if (this.mAuthenticatorInfosToDisplay.isEmpty()) {
      Bundle bundle = new Bundle();
      bundle.putString("errorMessage", "no allowable account types");
      setResult(-1, (new Intent()).putExtras(bundle));
      finish();
      return;
    } 
    if (this.mAuthenticatorInfosToDisplay.size() == 1) {
      setResultAndFinish(((AuthInfo)this.mAuthenticatorInfosToDisplay.get(0)).desc.type);
      return;
    } 
    setContentView(17367121);
    ListView listView = (ListView)findViewById(16908298);
    listView.setAdapter((ListAdapter)new AccountArrayAdapter((Context)this, 17367043, this.mAuthenticatorInfosToDisplay));
    listView.setChoiceMode(0);
    listView.setTextFilterEnabled(false);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            ChooseAccountTypeActivity chooseAccountTypeActivity = ChooseAccountTypeActivity.this;
            chooseAccountTypeActivity.setResultAndFinish((chooseAccountTypeActivity.mAuthenticatorInfosToDisplay.get(param1Int)).desc.type);
          }
        });
  }
  
  private static class AccountArrayAdapter extends ArrayAdapter<AuthInfo> {
    private ArrayList<ChooseAccountTypeActivity.AuthInfo> mInfos;
    
    private LayoutInflater mLayoutInflater;
    
    public AccountArrayAdapter(Context param1Context, int param1Int, ArrayList<ChooseAccountTypeActivity.AuthInfo> param1ArrayList) {
      super(param1Context, param1Int, param1ArrayList);
      this.mInfos = param1ArrayList;
      this.mLayoutInflater = (LayoutInflater)param1Context.getSystemService("layout_inflater");
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      ChooseAccountTypeActivity.ViewHolder viewHolder;
      if (param1View == null) {
        param1View = this.mLayoutInflater.inflate(17367120, null);
        viewHolder = new ChooseAccountTypeActivity.ViewHolder();
        viewHolder.text = (TextView)param1View.findViewById(16908694);
        viewHolder.icon = (ImageView)param1View.findViewById(16908693);
        param1View.setTag(viewHolder);
      } else {
        viewHolder = (ChooseAccountTypeActivity.ViewHolder)param1View.getTag();
      } 
      viewHolder.text.setText(((ChooseAccountTypeActivity.AuthInfo)this.mInfos.get(param1Int)).name);
      viewHolder.icon.setImageDrawable(((ChooseAccountTypeActivity.AuthInfo)this.mInfos.get(param1Int)).drawable);
      return param1View;
    }
  }
  
  private static class AuthInfo {
    final AuthenticatorDescription desc;
    
    final Drawable drawable;
    
    final String name;
    
    AuthInfo(AuthenticatorDescription param1AuthenticatorDescription, String param1String, Drawable param1Drawable) {
      this.desc = param1AuthenticatorDescription;
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


/* Location:              /home/chun/Desktop/temp/!/android/accounts/ChooseAccountTypeActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */