package android.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class null extends BroadcastReceiver {
  public void onReceive(Context paramContext, Intent paramIntent) {
    if (paramIntent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED"))
      SearchDialog.this.onConfigurationChanged(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchDialog$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */