package android.hardware.input;

import android.os.IBinder;
import android.os.RemoteException;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.InputMonitor;
import android.view.PointerIcon;
import android.view.VerifiedInputEvent;

public class Default implements IInputManager {
  public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException {}
  
  public void addPortAssociation(String paramString, int paramInt) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void cancelVibrate(int paramInt, IBinder paramIBinder) throws RemoteException {}
  
  public void disableInputDevice(int paramInt) throws RemoteException {}
  
  public void enableInputDevice(int paramInt) throws RemoteException {}
  
  public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException {
    return null;
  }
  
  public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException {
    return null;
  }
  
  public InputDevice getInputDevice(int paramInt) throws RemoteException {
    return null;
  }
  
  public int[] getInputDeviceIds() throws RemoteException {
    return null;
  }
  
  public KeyboardLayout getKeyboardLayout(String paramString) throws RemoteException {
    return null;
  }
  
  public KeyboardLayout[] getKeyboardLayouts() throws RemoteException {
    return null;
  }
  
  public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException {
    return null;
  }
  
  public TouchCalibration getTouchCalibrationForInputDevice(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean hasKeys(int paramInt1, int paramInt2, int[] paramArrayOfint, boolean[] paramArrayOfboolean) throws RemoteException {
    return false;
  }
  
  public boolean injectInputEvent(InputEvent paramInputEvent, int paramInt) throws RemoteException {
    return false;
  }
  
  public int isInTabletMode() throws RemoteException {
    return 0;
  }
  
  public boolean isInputDeviceEnabled(int paramInt) throws RemoteException {
    return false;
  }
  
  public int isMicMuted() throws RemoteException {
    return 0;
  }
  
  public InputMonitor monitorGestureInput(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public void registerInputDevicesChangedListener(IInputDevicesChangedListener paramIInputDevicesChangedListener) throws RemoteException {}
  
  public void registerTabletModeChangedListener(ITabletModeChangedListener paramITabletModeChangedListener) throws RemoteException {}
  
  public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException {}
  
  public void removePortAssociation(String paramString) throws RemoteException {}
  
  public void requestPointerCapture(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException {}
  
  public void setCustomPointerIcon(PointerIcon paramPointerIcon) throws RemoteException {}
  
  public void setPointerIconType(int paramInt) throws RemoteException {}
  
  public void setTouchCalibrationForInputDevice(String paramString, int paramInt, TouchCalibration paramTouchCalibration) throws RemoteException {}
  
  public void tryPointerSpeed(int paramInt) throws RemoteException {}
  
  public VerifiedInputEvent verifyInputEvent(InputEvent paramInputEvent) throws RemoteException {
    return null;
  }
  
  public void vibrate(int paramInt1, long[] paramArrayOflong, int paramInt2, IBinder paramIBinder) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/IInputManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */