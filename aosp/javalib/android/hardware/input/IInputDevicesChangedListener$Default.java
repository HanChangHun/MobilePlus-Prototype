package android.hardware.input;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IInputDevicesChangedListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onInputDevicesChanged(int[] paramArrayOfint) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/IInputDevicesChangedListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */