package android.hardware.input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.InputMonitor;
import android.view.PointerIcon;
import android.view.VerifiedInputEvent;

public interface IInputManager extends IInterface {
  void addKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException;
  
  void addPortAssociation(String paramString, int paramInt) throws RemoteException;
  
  void cancelVibrate(int paramInt, IBinder paramIBinder) throws RemoteException;
  
  void disableInputDevice(int paramInt) throws RemoteException;
  
  void enableInputDevice(int paramInt) throws RemoteException;
  
  String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException;
  
  String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException;
  
  InputDevice getInputDevice(int paramInt) throws RemoteException;
  
  int[] getInputDeviceIds() throws RemoteException;
  
  KeyboardLayout getKeyboardLayout(String paramString) throws RemoteException;
  
  KeyboardLayout[] getKeyboardLayouts() throws RemoteException;
  
  KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier) throws RemoteException;
  
  TouchCalibration getTouchCalibrationForInputDevice(String paramString, int paramInt) throws RemoteException;
  
  boolean hasKeys(int paramInt1, int paramInt2, int[] paramArrayOfint, boolean[] paramArrayOfboolean) throws RemoteException;
  
  boolean injectInputEvent(InputEvent paramInputEvent, int paramInt) throws RemoteException;
  
  int isInTabletMode() throws RemoteException;
  
  boolean isInputDeviceEnabled(int paramInt) throws RemoteException;
  
  int isMicMuted() throws RemoteException;
  
  InputMonitor monitorGestureInput(String paramString, int paramInt) throws RemoteException;
  
  void registerInputDevicesChangedListener(IInputDevicesChangedListener paramIInputDevicesChangedListener) throws RemoteException;
  
  void registerTabletModeChangedListener(ITabletModeChangedListener paramITabletModeChangedListener) throws RemoteException;
  
  void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException;
  
  void removePortAssociation(String paramString) throws RemoteException;
  
  void requestPointerCapture(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier paramInputDeviceIdentifier, String paramString) throws RemoteException;
  
  void setCustomPointerIcon(PointerIcon paramPointerIcon) throws RemoteException;
  
  void setPointerIconType(int paramInt) throws RemoteException;
  
  void setTouchCalibrationForInputDevice(String paramString, int paramInt, TouchCalibration paramTouchCalibration) throws RemoteException;
  
  void tryPointerSpeed(int paramInt) throws RemoteException;
  
  VerifiedInputEvent verifyInputEvent(InputEvent paramInputEvent) throws RemoteException;
  
  void vibrate(int paramInt1, long[] paramArrayOflong, int paramInt2, IBinder paramIBinder) throws RemoteException;
  
  public static class Default implements IInputManager {
    public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier, String param1String) throws RemoteException {}
    
    public void addPortAssociation(String param1String, int param1Int) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public void cancelVibrate(int param1Int, IBinder param1IBinder) throws RemoteException {}
    
    public void disableInputDevice(int param1Int) throws RemoteException {}
    
    public void enableInputDevice(int param1Int) throws RemoteException {}
    
    public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier) throws RemoteException {
      return null;
    }
    
    public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier) throws RemoteException {
      return null;
    }
    
    public InputDevice getInputDevice(int param1Int) throws RemoteException {
      return null;
    }
    
    public int[] getInputDeviceIds() throws RemoteException {
      return null;
    }
    
    public KeyboardLayout getKeyboardLayout(String param1String) throws RemoteException {
      return null;
    }
    
    public KeyboardLayout[] getKeyboardLayouts() throws RemoteException {
      return null;
    }
    
    public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier) throws RemoteException {
      return null;
    }
    
    public TouchCalibration getTouchCalibrationForInputDevice(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public boolean hasKeys(int param1Int1, int param1Int2, int[] param1ArrayOfint, boolean[] param1ArrayOfboolean) throws RemoteException {
      return false;
    }
    
    public boolean injectInputEvent(InputEvent param1InputEvent, int param1Int) throws RemoteException {
      return false;
    }
    
    public int isInTabletMode() throws RemoteException {
      return 0;
    }
    
    public boolean isInputDeviceEnabled(int param1Int) throws RemoteException {
      return false;
    }
    
    public int isMicMuted() throws RemoteException {
      return 0;
    }
    
    public InputMonitor monitorGestureInput(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public void registerInputDevicesChangedListener(IInputDevicesChangedListener param1IInputDevicesChangedListener) throws RemoteException {}
    
    public void registerTabletModeChangedListener(ITabletModeChangedListener param1ITabletModeChangedListener) throws RemoteException {}
    
    public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier, String param1String) throws RemoteException {}
    
    public void removePortAssociation(String param1String) throws RemoteException {}
    
    public void requestPointerCapture(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier, String param1String) throws RemoteException {}
    
    public void setCustomPointerIcon(PointerIcon param1PointerIcon) throws RemoteException {}
    
    public void setPointerIconType(int param1Int) throws RemoteException {}
    
    public void setTouchCalibrationForInputDevice(String param1String, int param1Int, TouchCalibration param1TouchCalibration) throws RemoteException {}
    
    public void tryPointerSpeed(int param1Int) throws RemoteException {}
    
    public VerifiedInputEvent verifyInputEvent(InputEvent param1InputEvent) throws RemoteException {
      return null;
    }
    
    public void vibrate(int param1Int1, long[] param1ArrayOflong, int param1Int2, IBinder param1IBinder) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IInputManager {
    private static final String DESCRIPTOR = "android.hardware.input.IInputManager";
    
    static final int TRANSACTION_addKeyboardLayoutForInputDevice = 18;
    
    static final int TRANSACTION_addPortAssociation = 30;
    
    static final int TRANSACTION_cancelVibrate = 25;
    
    static final int TRANSACTION_disableInputDevice = 5;
    
    static final int TRANSACTION_enableInputDevice = 4;
    
    static final int TRANSACTION_getCurrentKeyboardLayoutForInputDevice = 15;
    
    static final int TRANSACTION_getEnabledKeyboardLayoutsForInputDevice = 17;
    
    static final int TRANSACTION_getInputDevice = 1;
    
    static final int TRANSACTION_getInputDeviceIds = 2;
    
    static final int TRANSACTION_getKeyboardLayout = 14;
    
    static final int TRANSACTION_getKeyboardLayouts = 12;
    
    static final int TRANSACTION_getKeyboardLayoutsForInputDevice = 13;
    
    static final int TRANSACTION_getTouchCalibrationForInputDevice = 10;
    
    static final int TRANSACTION_hasKeys = 6;
    
    static final int TRANSACTION_injectInputEvent = 8;
    
    static final int TRANSACTION_isInTabletMode = 21;
    
    static final int TRANSACTION_isInputDeviceEnabled = 3;
    
    static final int TRANSACTION_isMicMuted = 23;
    
    static final int TRANSACTION_monitorGestureInput = 29;
    
    static final int TRANSACTION_registerInputDevicesChangedListener = 20;
    
    static final int TRANSACTION_registerTabletModeChangedListener = 22;
    
    static final int TRANSACTION_removeKeyboardLayoutForInputDevice = 19;
    
    static final int TRANSACTION_removePortAssociation = 31;
    
    static final int TRANSACTION_requestPointerCapture = 28;
    
    static final int TRANSACTION_setCurrentKeyboardLayoutForInputDevice = 16;
    
    static final int TRANSACTION_setCustomPointerIcon = 27;
    
    static final int TRANSACTION_setPointerIconType = 26;
    
    static final int TRANSACTION_setTouchCalibrationForInputDevice = 11;
    
    static final int TRANSACTION_tryPointerSpeed = 7;
    
    static final int TRANSACTION_verifyInputEvent = 9;
    
    static final int TRANSACTION_vibrate = 24;
    
    public Stub() {
      attachInterface(this, "android.hardware.input.IInputManager");
    }
    
    public static IInputManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.input.IInputManager");
      return (iInterface != null && iInterface instanceof IInputManager) ? (IInputManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IInputManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 31:
          return "removePortAssociation";
        case 30:
          return "addPortAssociation";
        case 29:
          return "monitorGestureInput";
        case 28:
          return "requestPointerCapture";
        case 27:
          return "setCustomPointerIcon";
        case 26:
          return "setPointerIconType";
        case 25:
          return "cancelVibrate";
        case 24:
          return "vibrate";
        case 23:
          return "isMicMuted";
        case 22:
          return "registerTabletModeChangedListener";
        case 21:
          return "isInTabletMode";
        case 20:
          return "registerInputDevicesChangedListener";
        case 19:
          return "removeKeyboardLayoutForInputDevice";
        case 18:
          return "addKeyboardLayoutForInputDevice";
        case 17:
          return "getEnabledKeyboardLayoutsForInputDevice";
        case 16:
          return "setCurrentKeyboardLayoutForInputDevice";
        case 15:
          return "getCurrentKeyboardLayoutForInputDevice";
        case 14:
          return "getKeyboardLayout";
        case 13:
          return "getKeyboardLayoutsForInputDevice";
        case 12:
          return "getKeyboardLayouts";
        case 11:
          return "setTouchCalibrationForInputDevice";
        case 10:
          return "getTouchCalibrationForInputDevice";
        case 9:
          return "verifyInputEvent";
        case 8:
          return "injectInputEvent";
        case 7:
          return "tryPointerSpeed";
        case 6:
          return "hasKeys";
        case 5:
          return "disableInputDevice";
        case 4:
          return "enableInputDevice";
        case 3:
          return "isInputDeviceEnabled";
        case 2:
          return "getInputDeviceIds";
        case 1:
          break;
      } 
      return "getInputDevice";
    }
    
    public static boolean setDefaultImpl(IInputManager param1IInputManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IInputManager != null) {
          Proxy.sDefaultImpl = param1IInputManager;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        boolean bool2;
        int i;
        boolean bool1;
        InputMonitor inputMonitor;
        String[] arrayOfString;
        String str1;
        KeyboardLayout keyboardLayout;
        KeyboardLayout[] arrayOfKeyboardLayout;
        TouchCalibration touchCalibration;
        VerifiedInputEvent verifiedInputEvent;
        boolean[] arrayOfBoolean;
        int[] arrayOfInt1;
        IBinder iBinder;
        String str2;
        int[] arrayOfInt2;
        int j;
        boolean bool = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 31:
            param1Parcel1.enforceInterface("android.hardware.input.IInputManager");
            removePortAssociation(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            return true;
          case 30:
            param1Parcel1.enforceInterface("android.hardware.input.IInputManager");
            addPortAssociation(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 29:
            param1Parcel1.enforceInterface("android.hardware.input.IInputManager");
            inputMonitor = monitorGestureInput(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            if (inputMonitor != null) {
              param1Parcel2.writeInt(1);
              inputMonitor.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 28:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            iBinder = inputMonitor.readStrongBinder();
            if (inputMonitor.readInt() != 0)
              bool = true; 
            requestPointerCapture(iBinder, bool);
            param1Parcel2.writeNoException();
            return true;
          case 27:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            if (inputMonitor.readInt() != 0) {
              PointerIcon pointerIcon = (PointerIcon)PointerIcon.CREATOR.createFromParcel((Parcel)inputMonitor);
            } else {
              inputMonitor = null;
            } 
            setCustomPointerIcon((PointerIcon)inputMonitor);
            param1Parcel2.writeNoException();
            return true;
          case 26:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            setPointerIconType(inputMonitor.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 25:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            cancelVibrate(inputMonitor.readInt(), inputMonitor.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 24:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            vibrate(inputMonitor.readInt(), inputMonitor.createLongArray(), inputMonitor.readInt(), inputMonitor.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 23:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            param1Int1 = isMicMuted();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 22:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            registerTabletModeChangedListener(ITabletModeChangedListener.Stub.asInterface(inputMonitor.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 21:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            param1Int1 = isInTabletMode();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 20:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            registerInputDevicesChangedListener(IInputDevicesChangedListener.Stub.asInterface(inputMonitor.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 19:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            if (inputMonitor.readInt() != 0) {
              InputDeviceIdentifier inputDeviceIdentifier = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel((Parcel)inputMonitor);
            } else {
              iBinder = null;
            } 
            removeKeyboardLayoutForInputDevice((InputDeviceIdentifier)iBinder, inputMonitor.readString());
            param1Parcel2.writeNoException();
            return true;
          case 18:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            if (inputMonitor.readInt() != 0) {
              InputDeviceIdentifier inputDeviceIdentifier = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel((Parcel)inputMonitor);
            } else {
              iBinder = null;
            } 
            addKeyboardLayoutForInputDevice((InputDeviceIdentifier)iBinder, inputMonitor.readString());
            param1Parcel2.writeNoException();
            return true;
          case 17:
            inputMonitor.enforceInterface("android.hardware.input.IInputManager");
            if (inputMonitor.readInt() != 0) {
              InputDeviceIdentifier inputDeviceIdentifier = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel((Parcel)inputMonitor);
            } else {
              inputMonitor = null;
            } 
            arrayOfString = getEnabledKeyboardLayoutsForInputDevice((InputDeviceIdentifier)inputMonitor);
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringArray(arrayOfString);
            return true;
          case 16:
            arrayOfString.enforceInterface("android.hardware.input.IInputManager");
            if (arrayOfString.readInt() != 0) {
              InputDeviceIdentifier inputDeviceIdentifier = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel((Parcel)arrayOfString);
            } else {
              iBinder = null;
            } 
            setCurrentKeyboardLayoutForInputDevice((InputDeviceIdentifier)iBinder, arrayOfString.readString());
            param1Parcel2.writeNoException();
            return true;
          case 15:
            arrayOfString.enforceInterface("android.hardware.input.IInputManager");
            if (arrayOfString.readInt() != 0) {
              InputDeviceIdentifier inputDeviceIdentifier = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel((Parcel)arrayOfString);
            } else {
              arrayOfString = null;
            } 
            str1 = getCurrentKeyboardLayoutForInputDevice((InputDeviceIdentifier)arrayOfString);
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str1);
            return true;
          case 14:
            str1.enforceInterface("android.hardware.input.IInputManager");
            keyboardLayout = getKeyboardLayout(str1.readString());
            param1Parcel2.writeNoException();
            if (keyboardLayout != null) {
              param1Parcel2.writeInt(1);
              keyboardLayout.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 13:
            keyboardLayout.enforceInterface("android.hardware.input.IInputManager");
            if (keyboardLayout.readInt() != 0) {
              InputDeviceIdentifier inputDeviceIdentifier = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel((Parcel)keyboardLayout);
            } else {
              keyboardLayout = null;
            } 
            arrayOfKeyboardLayout = getKeyboardLayoutsForInputDevice((InputDeviceIdentifier)keyboardLayout);
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedArray((Parcelable[])arrayOfKeyboardLayout, 1);
            return true;
          case 12:
            arrayOfKeyboardLayout.enforceInterface("android.hardware.input.IInputManager");
            arrayOfKeyboardLayout = getKeyboardLayouts();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedArray((Parcelable[])arrayOfKeyboardLayout, 1);
            return true;
          case 11:
            arrayOfKeyboardLayout.enforceInterface("android.hardware.input.IInputManager");
            str2 = arrayOfKeyboardLayout.readString();
            param1Int1 = arrayOfKeyboardLayout.readInt();
            if (arrayOfKeyboardLayout.readInt() != 0) {
              TouchCalibration touchCalibration1 = (TouchCalibration)TouchCalibration.CREATOR.createFromParcel((Parcel)arrayOfKeyboardLayout);
            } else {
              arrayOfKeyboardLayout = null;
            } 
            setTouchCalibrationForInputDevice(str2, param1Int1, (TouchCalibration)arrayOfKeyboardLayout);
            param1Parcel2.writeNoException();
            return true;
          case 10:
            arrayOfKeyboardLayout.enforceInterface("android.hardware.input.IInputManager");
            touchCalibration = getTouchCalibrationForInputDevice(arrayOfKeyboardLayout.readString(), arrayOfKeyboardLayout.readInt());
            param1Parcel2.writeNoException();
            if (touchCalibration != null) {
              param1Parcel2.writeInt(1);
              touchCalibration.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 9:
            touchCalibration.enforceInterface("android.hardware.input.IInputManager");
            if (touchCalibration.readInt() != 0) {
              InputEvent inputEvent = (InputEvent)InputEvent.CREATOR.createFromParcel((Parcel)touchCalibration);
            } else {
              touchCalibration = null;
            } 
            verifiedInputEvent = verifyInputEvent((InputEvent)touchCalibration);
            param1Parcel2.writeNoException();
            if (verifiedInputEvent != null) {
              param1Parcel2.writeInt(1);
              verifiedInputEvent.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 8:
            verifiedInputEvent.enforceInterface("android.hardware.input.IInputManager");
            if (verifiedInputEvent.readInt() != 0) {
              InputEvent inputEvent = (InputEvent)InputEvent.CREATOR.createFromParcel((Parcel)verifiedInputEvent);
            } else {
              str2 = null;
            } 
            bool2 = injectInputEvent((InputEvent)str2, verifiedInputEvent.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 7:
            verifiedInputEvent.enforceInterface("android.hardware.input.IInputManager");
            tryPointerSpeed(verifiedInputEvent.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 6:
            verifiedInputEvent.enforceInterface("android.hardware.input.IInputManager");
            param1Int2 = verifiedInputEvent.readInt();
            i = verifiedInputEvent.readInt();
            arrayOfInt2 = verifiedInputEvent.createIntArray();
            j = verifiedInputEvent.readInt();
            if (j < 0) {
              verifiedInputEvent = null;
            } else {
              arrayOfBoolean = new boolean[j];
            } 
            bool1 = hasKeys(param1Int2, i, arrayOfInt2, arrayOfBoolean);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            param1Parcel2.writeBooleanArray(arrayOfBoolean);
            return true;
          case 5:
            arrayOfBoolean.enforceInterface("android.hardware.input.IInputManager");
            disableInputDevice(arrayOfBoolean.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 4:
            arrayOfBoolean.enforceInterface("android.hardware.input.IInputManager");
            enableInputDevice(arrayOfBoolean.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 3:
            arrayOfBoolean.enforceInterface("android.hardware.input.IInputManager");
            bool1 = isInputDeviceEnabled(arrayOfBoolean.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 2:
            arrayOfBoolean.enforceInterface("android.hardware.input.IInputManager");
            arrayOfInt1 = getInputDeviceIds();
            param1Parcel2.writeNoException();
            param1Parcel2.writeIntArray(arrayOfInt1);
            return true;
          case 1:
            break;
        } 
        arrayOfInt1.enforceInterface("android.hardware.input.IInputManager");
        InputDevice inputDevice = getInputDevice(arrayOfInt1.readInt());
        param1Parcel2.writeNoException();
        if (inputDevice != null) {
          param1Parcel2.writeInt(1);
          inputDevice.writeToParcel(param1Parcel2, 1);
        } else {
          param1Parcel2.writeInt(0);
        } 
        return true;
      } 
      param1Parcel2.writeString("android.hardware.input.IInputManager");
      return true;
    }
    
    private static class Proxy implements IInputManager {
      public static IInputManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier param2InputDeviceIdentifier, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2InputDeviceIdentifier != null) {
            parcel1.writeInt(1);
            param2InputDeviceIdentifier.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().addKeyboardLayoutForInputDevice(param2InputDeviceIdentifier, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void addPortAssociation(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().addPortAssociation(param2String, param2Int);
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
      
      public void cancelVibrate(int param2Int, IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int);
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().cancelVibrate(param2Int, param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void disableInputDevice(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().disableInputDevice(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void enableInputDevice(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().enableInputDevice(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier param2InputDeviceIdentifier) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2InputDeviceIdentifier != null) {
            parcel1.writeInt(1);
            param2InputDeviceIdentifier.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
            return IInputManager.Stub.getDefaultImpl().getCurrentKeyboardLayoutForInputDevice(param2InputDeviceIdentifier); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier param2InputDeviceIdentifier) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2InputDeviceIdentifier != null) {
            parcel1.writeInt(1);
            param2InputDeviceIdentifier.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
            return IInputManager.Stub.getDefaultImpl().getEnabledKeyboardLayoutsForInputDevice(param2InputDeviceIdentifier); 
          parcel2.readException();
          return parcel2.createStringArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public InputDevice getInputDevice(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          InputDevice inputDevice;
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            inputDevice = IInputManager.Stub.getDefaultImpl().getInputDevice(param2Int);
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
      
      public KeyboardLayout getKeyboardLayout(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
            return IInputManager.Stub.getDefaultImpl().getKeyboardLayout(param2String); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            KeyboardLayout keyboardLayout = (KeyboardLayout)KeyboardLayout.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (KeyboardLayout)param2String;
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
      
      public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier param2InputDeviceIdentifier) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2InputDeviceIdentifier != null) {
            parcel1.writeInt(1);
            param2InputDeviceIdentifier.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
            return IInputManager.Stub.getDefaultImpl().getKeyboardLayoutsForInputDevice(param2InputDeviceIdentifier); 
          parcel2.readException();
          return (KeyboardLayout[])parcel2.createTypedArray(KeyboardLayout.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public TouchCalibration getTouchCalibrationForInputDevice(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
            return IInputManager.Stub.getDefaultImpl().getTouchCalibrationForInputDevice(param2String, param2Int); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            TouchCalibration touchCalibration = (TouchCalibration)TouchCalibration.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (TouchCalibration)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean hasKeys(int param2Int1, int param2Int2, int[] param2ArrayOfint, boolean[] param2ArrayOfboolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeIntArray(param2ArrayOfint);
          if (param2ArrayOfboolean == null) {
            parcel1.writeInt(-1);
          } else {
            parcel1.writeInt(param2ArrayOfboolean.length);
          } 
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(6, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            bool = IInputManager.Stub.getDefaultImpl().hasKeys(param2Int1, param2Int2, param2ArrayOfint, param2ArrayOfboolean);
            return bool;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0)
            bool = true; 
          parcel2.readBooleanArray(param2ArrayOfboolean);
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean injectInputEvent(InputEvent param2InputEvent, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          boolean bool = true;
          if (param2InputEvent != null) {
            parcel1.writeInt(1);
            param2InputEvent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            bool = IInputManager.Stub.getDefaultImpl().injectInputEvent(param2InputEvent, param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
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
      
      public boolean isInputDeviceEnabled(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(3, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            bool = IInputManager.Stub.getDefaultImpl().isInputDeviceEnabled(param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
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
      
      public InputMonitor monitorGestureInput(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
            return IInputManager.Stub.getDefaultImpl().monitorGestureInput(param2String, param2Int); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            InputMonitor inputMonitor = (InputMonitor)InputMonitor.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (InputMonitor)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerInputDevicesChangedListener(IInputDevicesChangedListener param2IInputDevicesChangedListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2IInputDevicesChangedListener != null) {
            iBinder = param2IInputDevicesChangedListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().registerInputDevicesChangedListener(param2IInputDevicesChangedListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerTabletModeChangedListener(ITabletModeChangedListener param2ITabletModeChangedListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2ITabletModeChangedListener != null) {
            iBinder = param2ITabletModeChangedListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().registerTabletModeChangedListener(param2ITabletModeChangedListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier param2InputDeviceIdentifier, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2InputDeviceIdentifier != null) {
            parcel1.writeInt(1);
            param2InputDeviceIdentifier.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().removeKeyboardLayoutForInputDevice(param2InputDeviceIdentifier, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removePortAssociation(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().removePortAssociation(param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestPointerCapture(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().requestPointerCapture(param2IBinder, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier param2InputDeviceIdentifier, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2InputDeviceIdentifier != null) {
            parcel1.writeInt(1);
            param2InputDeviceIdentifier.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().setCurrentKeyboardLayoutForInputDevice(param2InputDeviceIdentifier, param2String);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setCustomPointerIcon(PointerIcon param2PointerIcon) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2PointerIcon != null) {
            parcel1.writeInt(1);
            param2PointerIcon.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().setCustomPointerIcon(param2PointerIcon);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setPointerIconType(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().setPointerIconType(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setTouchCalibrationForInputDevice(String param2String, int param2Int, TouchCalibration param2TouchCalibration) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (param2TouchCalibration != null) {
            parcel1.writeInt(1);
            param2TouchCalibration.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().setTouchCalibrationForInputDevice(param2String, param2Int, param2TouchCalibration);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void tryPointerSpeed(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().tryPointerSpeed(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public VerifiedInputEvent verifyInputEvent(InputEvent param2InputEvent) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          if (param2InputEvent != null) {
            parcel1.writeInt(1);
            param2InputEvent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
            return IInputManager.Stub.getDefaultImpl().verifyInputEvent(param2InputEvent); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            VerifiedInputEvent verifiedInputEvent = (VerifiedInputEvent)VerifiedInputEvent.CREATOR.createFromParcel(parcel2);
          } else {
            param2InputEvent = null;
          } 
          return (VerifiedInputEvent)param2InputEvent;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void vibrate(int param2Int1, long[] param2ArrayOflong, int param2Int2, IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeLongArray(param2ArrayOflong);
          parcel1.writeInt(param2Int2);
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
            IInputManager.Stub.getDefaultImpl().vibrate(param2Int1, param2ArrayOflong, param2Int2, param2IBinder);
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
  }
  
  private static class Proxy implements IInputManager {
    public static IInputManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1InputDeviceIdentifier != null) {
          parcel1.writeInt(1);
          param1InputDeviceIdentifier.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().addKeyboardLayoutForInputDevice(param1InputDeviceIdentifier, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addPortAssociation(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().addPortAssociation(param1String, param1Int);
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
    
    public void cancelVibrate(int param1Int, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int);
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().cancelVibrate(param1Int, param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disableInputDevice(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().disableInputDevice(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enableInputDevice(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().enableInputDevice(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1InputDeviceIdentifier != null) {
          parcel1.writeInt(1);
          param1InputDeviceIdentifier.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
          return IInputManager.Stub.getDefaultImpl().getCurrentKeyboardLayoutForInputDevice(param1InputDeviceIdentifier); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1InputDeviceIdentifier != null) {
          parcel1.writeInt(1);
          param1InputDeviceIdentifier.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
          return IInputManager.Stub.getDefaultImpl().getEnabledKeyboardLayoutsForInputDevice(param1InputDeviceIdentifier); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public InputDevice getInputDevice(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        InputDevice inputDevice;
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          inputDevice = IInputManager.Stub.getDefaultImpl().getInputDevice(param1Int);
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
    
    public KeyboardLayout getKeyboardLayout(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
          return IInputManager.Stub.getDefaultImpl().getKeyboardLayout(param1String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          KeyboardLayout keyboardLayout = (KeyboardLayout)KeyboardLayout.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (KeyboardLayout)param1String;
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
    
    public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1InputDeviceIdentifier != null) {
          parcel1.writeInt(1);
          param1InputDeviceIdentifier.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
          return IInputManager.Stub.getDefaultImpl().getKeyboardLayoutsForInputDevice(param1InputDeviceIdentifier); 
        parcel2.readException();
        return (KeyboardLayout[])parcel2.createTypedArray(KeyboardLayout.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public TouchCalibration getTouchCalibrationForInputDevice(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
          return IInputManager.Stub.getDefaultImpl().getTouchCalibrationForInputDevice(param1String, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          TouchCalibration touchCalibration = (TouchCalibration)TouchCalibration.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (TouchCalibration)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean hasKeys(int param1Int1, int param1Int2, int[] param1ArrayOfint, boolean[] param1ArrayOfboolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeIntArray(param1ArrayOfint);
        if (param1ArrayOfboolean == null) {
          parcel1.writeInt(-1);
        } else {
          parcel1.writeInt(param1ArrayOfboolean.length);
        } 
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          bool = IInputManager.Stub.getDefaultImpl().hasKeys(param1Int1, param1Int2, param1ArrayOfint, param1ArrayOfboolean);
          return bool;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0)
          bool = true; 
        parcel2.readBooleanArray(param1ArrayOfboolean);
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean injectInputEvent(InputEvent param1InputEvent, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        boolean bool = true;
        if (param1InputEvent != null) {
          parcel1.writeInt(1);
          param1InputEvent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          bool = IInputManager.Stub.getDefaultImpl().injectInputEvent(param1InputEvent, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
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
    
    public boolean isInputDeviceEnabled(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          bool = IInputManager.Stub.getDefaultImpl().isInputDeviceEnabled(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
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
    
    public InputMonitor monitorGestureInput(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
          return IInputManager.Stub.getDefaultImpl().monitorGestureInput(param1String, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          InputMonitor inputMonitor = (InputMonitor)InputMonitor.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (InputMonitor)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerInputDevicesChangedListener(IInputDevicesChangedListener param1IInputDevicesChangedListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1IInputDevicesChangedListener != null) {
          iBinder = param1IInputDevicesChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().registerInputDevicesChangedListener(param1IInputDevicesChangedListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerTabletModeChangedListener(ITabletModeChangedListener param1ITabletModeChangedListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1ITabletModeChangedListener != null) {
          iBinder = param1ITabletModeChangedListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().registerTabletModeChangedListener(param1ITabletModeChangedListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1InputDeviceIdentifier != null) {
          parcel1.writeInt(1);
          param1InputDeviceIdentifier.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().removeKeyboardLayoutForInputDevice(param1InputDeviceIdentifier, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removePortAssociation(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().removePortAssociation(param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestPointerCapture(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().requestPointerCapture(param1IBinder, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier param1InputDeviceIdentifier, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1InputDeviceIdentifier != null) {
          parcel1.writeInt(1);
          param1InputDeviceIdentifier.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().setCurrentKeyboardLayoutForInputDevice(param1InputDeviceIdentifier, param1String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setCustomPointerIcon(PointerIcon param1PointerIcon) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1PointerIcon != null) {
          parcel1.writeInt(1);
          param1PointerIcon.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().setCustomPointerIcon(param1PointerIcon);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPointerIconType(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().setPointerIconType(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTouchCalibrationForInputDevice(String param1String, int param1Int, TouchCalibration param1TouchCalibration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (param1TouchCalibration != null) {
          parcel1.writeInt(1);
          param1TouchCalibration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().setTouchCalibrationForInputDevice(param1String, param1Int, param1TouchCalibration);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void tryPointerSpeed(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().tryPointerSpeed(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public VerifiedInputEvent verifyInputEvent(InputEvent param1InputEvent) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        if (param1InputEvent != null) {
          parcel1.writeInt(1);
          param1InputEvent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null)
          return IInputManager.Stub.getDefaultImpl().verifyInputEvent(param1InputEvent); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          VerifiedInputEvent verifiedInputEvent = (VerifiedInputEvent)VerifiedInputEvent.CREATOR.createFromParcel(parcel2);
        } else {
          param1InputEvent = null;
        } 
        return (VerifiedInputEvent)param1InputEvent;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void vibrate(int param1Int1, long[] param1ArrayOflong, int param1Int2, IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.input.IInputManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeLongArray(param1ArrayOflong);
        parcel1.writeInt(param1Int2);
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IInputManager.Stub.getDefaultImpl() != null) {
          IInputManager.Stub.getDefaultImpl().vibrate(param1Int1, param1ArrayOflong, param1Int2, param1IBinder);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/IInputManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */