package android.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

class null extends BroadcastReceiver {
  public void onReceive(Context paramContext, Intent paramIntent) {
    int i = paramIntent.getIntExtra("android.intent.extra.UID", -1);
    if (i != -1)
      RegisteredServicesCache.access$100(RegisteredServicesCache.this, paramIntent, UserHandle.getUserId(i)); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/RegisteredServicesCache$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */