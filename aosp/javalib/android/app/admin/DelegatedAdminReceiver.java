package android.app.admin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class DelegatedAdminReceiver extends BroadcastReceiver {
  private static final String TAG = "DelegatedAdminReceiver";
  
  public String onChoosePrivateKeyAlias(Context paramContext, Intent paramIntent, int paramInt, Uri paramUri, String paramString) {
    throw new UnsupportedOperationException("onChoosePrivateKeyAlias should be implemented");
  }
  
  public void onNetworkLogsAvailable(Context paramContext, Intent paramIntent, long paramLong, int paramInt) {
    throw new UnsupportedOperationException("onNetworkLogsAvailable should be implemented");
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    String str = paramIntent.getAction();
    if ("android.app.action.CHOOSE_PRIVATE_KEY_ALIAS".equals(str)) {
      setResultData(onChoosePrivateKeyAlias(paramContext, paramIntent, paramIntent.getIntExtra("android.app.extra.CHOOSE_PRIVATE_KEY_SENDER_UID", -1), (Uri)paramIntent.getParcelableExtra("android.app.extra.CHOOSE_PRIVATE_KEY_URI"), paramIntent.getStringExtra("android.app.extra.CHOOSE_PRIVATE_KEY_ALIAS")));
    } else if ("android.app.action.NETWORK_LOGS_AVAILABLE".equals(str)) {
      onNetworkLogsAvailable(paramContext, paramIntent, paramIntent.getLongExtra("android.app.extra.EXTRA_NETWORK_LOGS_TOKEN", -1L), paramIntent.getIntExtra("android.app.extra.EXTRA_NETWORK_LOGS_COUNT", 0));
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unhandled broadcast: ");
      stringBuilder.append(str);
      Log.w("DelegatedAdminReceiver", stringBuilder.toString());
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DelegatedAdminReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */