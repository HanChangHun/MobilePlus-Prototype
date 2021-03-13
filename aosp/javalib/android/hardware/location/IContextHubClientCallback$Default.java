package android.hardware.location;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IContextHubClientCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onHubReset() throws RemoteException {}
  
  public void onMessageFromNanoApp(NanoAppMessage paramNanoAppMessage) throws RemoteException {}
  
  public void onNanoAppAborted(long paramLong, int paramInt) throws RemoteException {}
  
  public void onNanoAppDisabled(long paramLong) throws RemoteException {}
  
  public void onNanoAppEnabled(long paramLong) throws RemoteException {}
  
  public void onNanoAppLoaded(long paramLong) throws RemoteException {}
  
  public void onNanoAppUnloaded(long paramLong) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IContextHubClientCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */