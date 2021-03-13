package android.hardware.input;

import android.os.RemoteException;

final class InputDevicesChangedListener extends IInputDevicesChangedListener.Stub {
  private InputDevicesChangedListener() {}
  
  public void onInputDevicesChanged(int[] paramArrayOfint) throws RemoteException {
    InputManager.access$200(InputManager.this, paramArrayOfint);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputManager$InputDevicesChangedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */