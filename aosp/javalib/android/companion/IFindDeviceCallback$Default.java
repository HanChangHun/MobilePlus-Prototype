package android.companion;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IFindDeviceCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onFailure(CharSequence paramCharSequence) throws RemoteException {}
  
  public void onSuccess(PendingIntent paramPendingIntent) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/IFindDeviceCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */