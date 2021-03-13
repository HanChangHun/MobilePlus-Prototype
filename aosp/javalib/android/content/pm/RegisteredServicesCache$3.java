package android.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class null extends BroadcastReceiver {
  public void onReceive(Context paramContext, Intent paramIntent) {
    int i = paramIntent.getIntExtra("android.intent.extra.user_handle", -1);
    RegisteredServicesCache.this.onUserRemoved(i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/RegisteredServicesCache$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */