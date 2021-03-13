package android.hardware.input;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.InputMonitor;
import android.view.PointerIcon;
import android.view.VerifiedInputEvent;

class Proxy implements IInputManager {
  public static IInputManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramInputDeviceIdentifier != null) {
        parcel1.writeInt(1);
        paramInputDeviceIdentifier.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().addKeyboardLayoutForInputDevice(paramInputDeviceIdentifier, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addPortAssociation(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().addPortAssociation(paramString, paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void cancelVibrate(int paramInt, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().cancelVibrate(paramInt, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disableInputDevice(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().disableInputDevice(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enableInputDevice(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().enableInputDevice(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramInputDeviceIdentifier != null) {
        parcel1.writeInt(1);
        paramInputDeviceIdentifier.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().getCurrentKeyboardLayoutForInputDevice(paramInputDeviceIdentifier); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramInputDeviceIdentifier != null) {
        parcel1.writeInt(1);
        paramInputDeviceIdentifier.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().getEnabledKeyboardLayoutsForInputDevice(paramInputDeviceIdentifier); 
      parcel2.readException();
      return parcel2.createStringArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public InputDevice getInputDevice(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      InputDevice inputDevice;
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        inputDevice = IInputManager.Stub.getDefaultImpl().getInputDevice(paramInt);
        return inputDevice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        inputDevice = (InputDevice)InputDevice.CREATOR.createFromParcel(parcel2);
      } else {
        inputDevice = null;
      } 
      return inputDevice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int[] getInputDeviceIds() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().getInputDeviceIds(); 
      parcel2.readException();
      return parcel2.createIntArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.input.IInputManager";
  }
  
  public KeyboardLayout getKeyboardLayout(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().getKeyboardLayout(paramString); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        KeyboardLayout keyboardLayout = (KeyboardLayout)KeyboardLayout.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (KeyboardLayout)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public KeyboardLayout[] getKeyboardLayouts() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().getKeyboardLayouts(); 
      parcel2.readException();
      return (KeyboardLayout[])parcel2.createTypedArray(KeyboardLayout.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramInputDeviceIdentifier != null) {
        parcel1.writeInt(1);
        paramInputDeviceIdentifier.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().getKeyboardLayoutsForInputDevice(paramInputDeviceIdentifier); 
      parcel2.readException();
      return (KeyboardLayout[])parcel2.createTypedArray(KeyboardLayout.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public TouchCalibration getTouchCalibrationForInputDevice(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().getTouchCalibrationForInputDevice(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        TouchCalibration touchCalibration = (TouchCalibration)TouchCalibration.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (TouchCalibration)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean hasKeys(int paramInt1, int paramInt2, int[] paramArrayOfint, boolean[] paramArrayOfboolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeIntArray(paramArrayOfint);
      if (paramArrayOfboolean == null) {
        parcel1.writeInt(-1);
      } else {
        parcel1.writeInt(paramArrayOfboolean.length);
      } 
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        bool = IInputManager.Stub.getDefaultImpl().hasKeys(paramInt1, paramInt2, paramArrayOfint, paramArrayOfboolean);
        return bool;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0)
        bool = true; 
      parcel2.readBooleanArray(paramArrayOfboolean);
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean injectInputEvent(InputEvent paramInputEvent, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      boolean bool = true;
      if (paramInputEvent != null) {
        parcel1.writeInt(1);
        paramInputEvent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        bool = IInputManager.Stub.getDefaultImpl().injectInputEvent(paramInputEvent, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int isInTabletMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().isInTabletMode(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isInputDeviceEnabled(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(3, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        bool = IInputManager.Stub.getDefaultImpl().isInputDeviceEnabled(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int isMicMuted() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().isMicMuted(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public InputMonitor monitorGestureInput(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().monitorGestureInput(paramString, paramInt); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        InputMonitor inputMonitor = (InputMonitor)InputMonitor.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (InputMonitor)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerInputDevicesChangedListener(IInputDevicesChangedListener paramIInputDevicesChangedListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramIInputDevicesChangedListener != null) {
        iBinder = paramIInputDevicesChangedListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().registerInputDevicesChangedListener(paramIInputDevicesChangedListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerTabletModeChangedListener(ITabletModeChangedListener paramITabletModeChangedListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramITabletModeChangedListener != null) {
        iBinder = paramITabletModeChangedListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().registerTabletModeChangedListener(paramITabletModeChangedListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramInputDeviceIdentifier != null) {
        parcel1.writeInt(1);
        paramInputDeviceIdentifier.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().removeKeyboardLayoutForInputDevice(paramInputDeviceIdentifier, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removePortAssociation(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().removePortAssociation(paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestPointerCapture(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeStrongBinder(paramIBinder);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().requestPointerCapture(paramIBinder, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramInputDeviceIdentifier != null) {
        parcel1.writeInt(1);
        paramInputDeviceIdentifier.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().setCurrentKeyboardLayoutForInputDevice(paramInputDeviceIdentifier, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCustomPointerIcon(PointerIcon paramPointerIcon) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramPointerIcon != null) {
        parcel1.writeInt(1);
        paramPointerIcon.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().setCustomPointerIcon(paramPointerIcon);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPointerIconType(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().setPointerIconType(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setTouchCalibrationForInputDevice(String paramString, int paramInt, TouchCalibration paramTouchCalibration) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      if (paramTouchCalibration != null) {
        parcel1.writeInt(1);
        paramTouchCalibration.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().setTouchCalibrationForInputDevice(paramString, paramInt, paramTouchCalibration);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void tryPointerSpeed(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().tryPointerSpeed(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public VerifiedInputEvent verifyInputEvent(InputEvent paramInputEvent) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      if (paramInputEvent != null) {
        parcel1.writeInt(1);
        paramInputEvent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
        return IInputManager.Stub.getDefaultImpl().verifyInputEvent(paramInputEvent); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        VerifiedInputEvent verifiedInputEvent = (VerifiedInputEvent)VerifiedInputEvent.CREATOR.createFromParcel(parcel2);
      } else {
        paramInputEvent = null;
      } 
      return (VerifiedInputEvent)paramInputEvent;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void vibrate(int paramInt1, long[] paramArrayOflong, int paramInt2, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeLongArray(paramArrayOflong);
      parcel1.writeInt(paramInt2);
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
        IInputManager.Stub.getDefaultImpl().vibrate(paramInt1, paramArrayOflong, paramInt2, paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/IInputManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */