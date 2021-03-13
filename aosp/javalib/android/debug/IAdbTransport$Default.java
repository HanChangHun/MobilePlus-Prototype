package android.debug;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAdbTransport {
  public IBinder asBinder() {
    return null;
  }
  
  public void onAdbEnabled(boolean paramBoolean, byte paramByte) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/debug/IAdbTransport$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */