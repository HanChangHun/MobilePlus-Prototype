package android.app;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IServiceConnection {
  public IBinder asBinder() {
    return null;
  }
  
  public void connected(ComponentName paramComponentName, IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IServiceConnection$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */