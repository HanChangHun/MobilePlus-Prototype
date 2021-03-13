package android.hardware;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ICameraServiceListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onCameraAccessPrioritiesChanged() throws RemoteException {}
  
  public void onCameraClosed(String paramString) throws RemoteException {}
  
  public void onCameraOpened(String paramString1, String paramString2) throws RemoteException {}
  
  public void onPhysicalCameraStatusChanged(int paramInt, String paramString1, String paramString2) throws RemoteException {}
  
  public void onStatusChanged(int paramInt, String paramString) throws RemoteException {}
  
  public void onTorchStatusChanged(int paramInt, String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraServiceListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */