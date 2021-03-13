package android.app;

import android.os.IBinder;

class ConnectionInfo {
  IBinder binder;
  
  IBinder.DeathRecipient deathMonitor;
  
  private ConnectionInfo() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LoadedApk$ServiceDispatcher$ConnectionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */