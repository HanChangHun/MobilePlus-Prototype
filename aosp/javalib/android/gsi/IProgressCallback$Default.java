package android.gsi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IProgressCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onProgress(long paramLong1, long paramLong2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IProgressCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */