package android.hardware.display;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IVirtualDisplayCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onPaused() throws RemoteException {}
  
  public void onResumed() throws RemoteException {}
  
  public void onStopped() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IVirtualDisplayCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */