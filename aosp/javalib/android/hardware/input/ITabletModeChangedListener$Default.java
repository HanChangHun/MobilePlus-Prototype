package android.hardware.input;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ITabletModeChangedListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onTabletModeChanged(long paramLong, boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/ITabletModeChangedListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */