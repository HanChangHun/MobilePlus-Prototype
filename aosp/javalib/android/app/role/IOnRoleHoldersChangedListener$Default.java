package android.app.role;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IOnRoleHoldersChangedListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onRoleHoldersChanged(String paramString, int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IOnRoleHoldersChangedListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */