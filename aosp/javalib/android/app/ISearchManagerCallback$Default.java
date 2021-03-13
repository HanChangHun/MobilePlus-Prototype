package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISearchManagerCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onCancel() throws RemoteException {}
  
  public void onDismiss() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ISearchManagerCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */