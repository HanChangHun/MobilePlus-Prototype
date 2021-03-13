package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IDataLoaderStatusListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onStatusChanged(int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoaderStatusListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */