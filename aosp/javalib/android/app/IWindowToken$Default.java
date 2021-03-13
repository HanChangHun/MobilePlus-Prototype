package android.app;

import android.content.res.Configuration;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IWindowToken {
  public IBinder asBinder() {
    return null;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration, int paramInt) throws RemoteException {}
  
  public void onWindowTokenRemoved() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWindowToken$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */