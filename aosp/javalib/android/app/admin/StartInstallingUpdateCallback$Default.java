package android.app.admin;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements StartInstallingUpdateCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onStartInstallingUpdateError(int paramInt, String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/StartInstallingUpdateCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */