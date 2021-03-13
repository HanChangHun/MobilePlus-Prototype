package android.hardware;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ICameraServiceProxy {
  public IBinder asBinder() {
    return null;
  }
  
  public void notifyCameraState(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3) throws RemoteException {}
  
  public void pingForUserUpdate() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICameraServiceProxy$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */