package android.app.slice;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISliceListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onSliceUpdated(Slice paramSlice) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/ISliceListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */