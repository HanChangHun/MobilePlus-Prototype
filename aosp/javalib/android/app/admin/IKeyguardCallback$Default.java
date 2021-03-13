package android.app.admin;

import android.os.IBinder;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;

public class Default implements IKeyguardCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onDismiss() throws RemoteException {}
  
  public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage paramSurfacePackage) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IKeyguardCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */