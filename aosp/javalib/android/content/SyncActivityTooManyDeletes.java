package android.content;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SyncActivityTooManyDeletes extends Activity implements AdapterView.OnItemClickListener {
  private Account mAccount;
  
  private String mAuthority;
  
  private long mNumDeletes;
  
  private String mProvider;
  
  private void startSyncReallyDelete() {
    Bundle bundle = new Bundle();
    bundle.putBoolean("deletions_override", true);
    bundle.putBoolean("force", true);
    bundle.putBoolean("expedited", true);
    bundle.putBoolean("upload", true);
    ContentResolver.requestSync(this.mAccount, this.mAuthority, bundle);
  }
  
  private void startSyncUndoDeletes() {
    Bundle bundle = new Bundle();
    bundle.putBoolean("discard_deletions", true);
    bundle.putBoolean("force", true);
    bundle.putBoolean("expedited", true);
    bundle.putBoolean("upload", true);
    ContentResolver.requestSync(this.mAccount, this.mAuthority, bundle);
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    if (paramBundle == null) {
      finish();
      return;
    } 
    this.mNumDeletes = paramBundle.getLong("numDeletes");
    this.mAccount = (Account)paramBundle.getParcelable("account");
    this.mAuthority = paramBundle.getString("authority");
    this.mProvider = paramBundle.getString("provider");
    ArrayAdapter arrayAdapter = new ArrayAdapter((Context)this, 17367043, 16908308, (Object[])new CharSequence[] { getResources().getText(17041335), getResources().getText(17041338), getResources().getText(17041334) });
    ListView listView = new ListView((Context)this);
    listView.setAdapter((ListAdapter)arrayAdapter);
    listView.setItemsCanFocus(true);
    listView.setOnItemClickListener(this);
    TextView textView = new TextView((Context)this);
    textView.setText(String.format(getResources().getText(17041337).toString(), new Object[] { Long.valueOf(this.mNumDeletes), this.mProvider, this.mAccount.name }));
    LinearLayout linearLayout = new LinearLayout((Context)this);
    linearLayout.setOrientation(1);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 0.0F);
    linearLayout.addView((View)textView, (ViewGroup.LayoutParams)layoutParams);
    linearLayout.addView((View)listView, (ViewGroup.LayoutParams)layoutParams);
    setContentView((View)linearLayout);
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    if (paramInt == 0) {
      startSyncReallyDelete();
    } else if (paramInt == 1) {
      startSyncUndoDeletes();
    } 
    finish();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncActivityTooManyDeletes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */