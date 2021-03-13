package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ITransientNotificationCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onToastHidden() throws RemoteException {}
  
  public void onToastShown() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITransientNotificationCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */