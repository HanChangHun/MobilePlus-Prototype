package android.app.admin;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IKeyguardClient {
  public IBinder asBinder() {
    return null;
  }
  
  public void onCreateKeyguardSurface(IBinder paramIBinder, IKeyguardCallback paramIKeyguardCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IKeyguardClient$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */