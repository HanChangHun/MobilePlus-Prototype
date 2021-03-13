package android.app.blob;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBlobCommitCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onResult(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobCommitCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */