package android.hardware.hdmi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IHdmiControlService {
  private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiControlService";
  
  static final int TRANSACTION_addDeviceEventListener = 11;
  
  static final int TRANSACTION_addHdmiCecVolumeControlFeatureListener = 7;
  
  static final int TRANSACTION_addHdmiControlStatusChangeListener = 5;
  
  static final int TRANSACTION_addHdmiMhlVendorCommandListener = 42;
  
  static final int TRANSACTION_addHotplugEventListener = 9;
  
  static final int TRANSACTION_addSystemAudioModeChangeListener = 21;
  
  static final int TRANSACTION_addVendorCommandListener = 34;
  
  static final int TRANSACTION_askRemoteDeviceToBecomeActiveSource = 32;
  
  static final int TRANSACTION_canChangeSystemAudioMode = 17;
  
  static final int TRANSACTION_clearTimerRecording = 40;
  
  static final int TRANSACTION_deviceSelect = 12;
  
  static final int TRANSACTION_getActiveSource = 2;
  
  static final int TRANSACTION_getDeviceList = 29;
  
  static final int TRANSACTION_getInputDevices = 28;
  
  static final int TRANSACTION_getPhysicalAddress = 19;
  
  static final int TRANSACTION_getPortInfo = 16;
  
  static final int TRANSACTION_getSupportedTypes = 1;
  
  static final int TRANSACTION_getSystemAudioMode = 18;
  
  static final int TRANSACTION_isHdmiCecVolumeControlEnabled = 45;
  
  static final int TRANSACTION_oneTouchPlay = 3;
  
  static final int TRANSACTION_portSelect = 13;
  
  static final int TRANSACTION_powerOffRemoteDevice = 30;
  
  static final int TRANSACTION_powerOnRemoteDevice = 31;
  
  static final int TRANSACTION_queryDisplayStatus = 4;
  
  static final int TRANSACTION_removeHdmiCecVolumeControlFeatureListener = 8;
  
  static final int TRANSACTION_removeHdmiControlStatusChangeListener = 6;
  
  static final int TRANSACTION_removeHotplugEventListener = 10;
  
  static final int TRANSACTION_removeSystemAudioModeChangeListener = 22;
  
  static final int TRANSACTION_reportAudioStatus = 46;
  
  static final int TRANSACTION_sendKeyEvent = 14;
  
  static final int TRANSACTION_sendMhlVendorCommand = 41;
  
  static final int TRANSACTION_sendStandby = 35;
  
  static final int TRANSACTION_sendVendorCommand = 33;
  
  static final int TRANSACTION_sendVolumeKeyEvent = 15;
  
  static final int TRANSACTION_setArcMode = 23;
  
  static final int TRANSACTION_setHdmiCecVolumeControlEnabled = 44;
  
  static final int TRANSACTION_setHdmiRecordListener = 36;
  
  static final int TRANSACTION_setInputChangeListener = 27;
  
  static final int TRANSACTION_setProhibitMode = 24;
  
  static final int TRANSACTION_setStandbyMode = 43;
  
  static final int TRANSACTION_setSystemAudioMode = 20;
  
  static final int TRANSACTION_setSystemAudioModeOnForAudioOnlySource = 47;
  
  static final int TRANSACTION_setSystemAudioMute = 26;
  
  static final int TRANSACTION_setSystemAudioVolume = 25;
  
  static final int TRANSACTION_startOneTouchRecord = 37;
  
  static final int TRANSACTION_startTimerRecording = 39;
  
  static final int TRANSACTION_stopOneTouchRecord = 38;
  
  public Stub() {
    attachInterface(this, "android.hardware.hdmi.IHdmiControlService");
  }
  
  public static IHdmiControlService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.hdmi.IHdmiControlService");
    return (iInterface != null && iInterface instanceof IHdmiControlService) ? (IHdmiControlService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IHdmiControlService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 47:
        return "setSystemAudioModeOnForAudioOnlySource";
      case 46:
        return "reportAudioStatus";
      case 45:
        return "isHdmiCecVolumeControlEnabled";
      case 44:
        return "setHdmiCecVolumeControlEnabled";
      case 43:
        return "setStandbyMode";
      case 42:
        return "addHdmiMhlVendorCommandListener";
      case 41:
        return "sendMhlVendorCommand";
      case 40:
        return "clearTimerRecording";
      case 39:
        return "startTimerRecording";
      case 38:
        return "stopOneTouchRecord";
      case 37:
        return "startOneTouchRecord";
      case 36:
        return "setHdmiRecordListener";
      case 35:
        return "sendStandby";
      case 34:
        return "addVendorCommandListener";
      case 33:
        return "sendVendorCommand";
      case 32:
        return "askRemoteDeviceToBecomeActiveSource";
      case 31:
        return "powerOnRemoteDevice";
      case 30:
        return "powerOffRemoteDevice";
      case 29:
        return "getDeviceList";
      case 28:
        return "getInputDevices";
      case 27:
        return "setInputChangeListener";
      case 26:
        return "setSystemAudioMute";
      case 25:
        return "setSystemAudioVolume";
      case 24:
        return "setProhibitMode";
      case 23:
        return "setArcMode";
      case 22:
        return "removeSystemAudioModeChangeListener";
      case 21:
        return "addSystemAudioModeChangeListener";
      case 20:
        return "setSystemAudioMode";
      case 19:
        return "getPhysicalAddress";
      case 18:
        return "getSystemAudioMode";
      case 17:
        return "canChangeSystemAudioMode";
      case 16:
        return "getPortInfo";
      case 15:
        return "sendVolumeKeyEvent";
      case 14:
        return "sendKeyEvent";
      case 13:
        return "portSelect";
      case 12:
        return "deviceSelect";
      case 11:
        return "addDeviceEventListener";
      case 10:
        return "removeHotplugEventListener";
      case 9:
        return "addHotplugEventListener";
      case 8:
        return "removeHdmiCecVolumeControlFeatureListener";
      case 7:
        return "addHdmiCecVolumeControlFeatureListener";
      case 6:
        return "removeHdmiControlStatusChangeListener";
      case 5:
        return "addHdmiControlStatusChangeListener";
      case 4:
        return "queryDisplayStatus";
      case 3:
        return "oneTouchPlay";
      case 2:
        return "getActiveSource";
      case 1:
        break;
    } 
    return "getSupportedTypes";
  }
  
  public static boolean setDefaultImpl(IHdmiControlService paramIHdmiControlService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIHdmiControlService != null) {
        Proxy.sDefaultImpl = paramIHdmiControlService;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      boolean bool2;
      int j;
      boolean bool1;
      int i;
      List<HdmiDeviceInfo> list;
      HdmiDeviceInfo hdmiDeviceInfo;
      int k;
      byte[] arrayOfByte;
      boolean bool3 = false;
      boolean bool4 = false;
      boolean bool5 = false;
      boolean bool6 = false;
      boolean bool7 = false;
      boolean bool8 = false;
      boolean bool9 = false;
      boolean bool10 = false;
      boolean bool11 = false;
      boolean bool12 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 47:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          setSystemAudioModeOnForAudioOnlySource();
          paramParcel2.writeNoException();
          return true;
        case 46:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          k = paramParcel1.readInt();
          paramInt1 = paramParcel1.readInt();
          paramInt2 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0)
            bool12 = true; 
          reportAudioStatus(k, paramInt1, paramInt2, bool12);
          paramParcel2.writeNoException();
          return true;
        case 45:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool2 = isHdmiCecVolumeControlEnabled();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 44:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool12 = bool3;
          if (paramParcel1.readInt() != 0)
            bool12 = true; 
          setHdmiCecVolumeControlEnabled(bool12);
          paramParcel2.writeNoException();
          return true;
        case 43:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool12 = bool4;
          if (paramParcel1.readInt() != 0)
            bool12 = true; 
          setStandbyMode(bool12);
          paramParcel2.writeNoException();
          return true;
        case 42:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 41:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          sendMhlVendorCommand(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 40:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          clearTimerRecording(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 39:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          startTimerRecording(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 38:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          stopOneTouchRecord(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 37:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          startOneTouchRecord(paramParcel1.readInt(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 36:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          setHdmiRecordListener(IHdmiRecordListener.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 35:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          sendStandby(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 34:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          addVendorCommandListener(IHdmiVendorCommandListener.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 33:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          j = paramParcel1.readInt();
          paramInt2 = paramParcel1.readInt();
          arrayOfByte = paramParcel1.createByteArray();
          bool12 = bool5;
          if (paramParcel1.readInt() != 0)
            bool12 = true; 
          sendVendorCommand(j, paramInt2, arrayOfByte, bool12);
          paramParcel2.writeNoException();
          return true;
        case 32:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          askRemoteDeviceToBecomeActiveSource(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 31:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          powerOnRemoteDevice(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 30:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          powerOffRemoteDevice(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 29:
          paramParcel1.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          list = getDeviceList();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 28:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          list = getInputDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 27:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          setInputChangeListener(IHdmiInputChangeListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 26:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool12 = bool6;
          if (list.readInt() != 0)
            bool12 = true; 
          setSystemAudioMute(bool12);
          paramParcel2.writeNoException();
          return true;
        case 25:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          setSystemAudioVolume(list.readInt(), list.readInt(), list.readInt());
          paramParcel2.writeNoException();
          return true;
        case 24:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool12 = bool7;
          if (list.readInt() != 0)
            bool12 = true; 
          setProhibitMode(bool12);
          paramParcel2.writeNoException();
          return true;
        case 23:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool12 = bool8;
          if (list.readInt() != 0)
            bool12 = true; 
          setArcMode(bool12);
          paramParcel2.writeNoException();
          return true;
        case 22:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 21:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 20:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool12 = bool9;
          if (list.readInt() != 0)
            bool12 = true; 
          setSystemAudioMode(bool12, IHdmiControlCallback.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 19:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          j = getPhysicalAddress();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 18:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool1 = getSystemAudioMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 17:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          bool1 = canChangeSystemAudioMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 16:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          list = (List)getPortInfo();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 15:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          i = list.readInt();
          paramInt2 = list.readInt();
          bool12 = bool10;
          if (list.readInt() != 0)
            bool12 = true; 
          sendVolumeKeyEvent(i, paramInt2, bool12);
          paramParcel2.writeNoException();
          return true;
        case 14:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          paramInt2 = list.readInt();
          i = list.readInt();
          bool12 = bool11;
          if (list.readInt() != 0)
            bool12 = true; 
          sendKeyEvent(paramInt2, i, bool12);
          paramParcel2.writeNoException();
          return true;
        case 13:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          portSelect(list.readInt(), IHdmiControlCallback.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 12:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          deviceSelect(list.readInt(), IHdmiControlCallback.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 11:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          addDeviceEventListener(IHdmiDeviceEventListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 10:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          removeHotplugEventListener(IHdmiHotplugEventListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 9:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          addHotplugEventListener(IHdmiHotplugEventListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 8:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 7:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 6:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 5:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 4:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          queryDisplayStatus(IHdmiControlCallback.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 3:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          oneTouchPlay(IHdmiControlCallback.Stub.asInterface(list.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 2:
          list.enforceInterface("android.hardware.hdmi.IHdmiControlService");
          hdmiDeviceInfo = getActiveSource();
          paramParcel2.writeNoException();
          if (hdmiDeviceInfo != null) {
            paramParcel2.writeInt(1);
            hdmiDeviceInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 1:
          break;
      } 
      hdmiDeviceInfo.enforceInterface("android.hardware.hdmi.IHdmiControlService");
      int[] arrayOfInt = getSupportedTypes();
      paramParcel2.writeNoException();
      paramParcel2.writeIntArray(arrayOfInt);
      return true;
    } 
    paramParcel2.writeString("android.hardware.hdmi.IHdmiControlService");
    return true;
  }
  
  private static class Proxy implements IHdmiControlService {
    public static IHdmiControlService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addDeviceEventListener(IHdmiDeviceEventListener param2IHdmiDeviceEventListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiDeviceEventListener != null) {
          iBinder = param2IHdmiDeviceEventListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().addDeviceEventListener(param2IHdmiDeviceEventListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener param2IHdmiCecVolumeControlFeatureListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiCecVolumeControlFeatureListener != null) {
          iBinder = param2IHdmiCecVolumeControlFeatureListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().addHdmiCecVolumeControlFeatureListener(param2IHdmiCecVolumeControlFeatureListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener param2IHdmiControlStatusChangeListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiControlStatusChangeListener != null) {
          iBinder = param2IHdmiControlStatusChangeListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().addHdmiControlStatusChangeListener(param2IHdmiControlStatusChangeListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener param2IHdmiMhlVendorCommandListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiMhlVendorCommandListener != null) {
          iBinder = param2IHdmiMhlVendorCommandListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().addHdmiMhlVendorCommandListener(param2IHdmiMhlVendorCommandListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addHotplugEventListener(IHdmiHotplugEventListener param2IHdmiHotplugEventListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiHotplugEventListener != null) {
          iBinder = param2IHdmiHotplugEventListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().addHotplugEventListener(param2IHdmiHotplugEventListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener param2IHdmiSystemAudioModeChangeListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiSystemAudioModeChangeListener != null) {
          iBinder = param2IHdmiSystemAudioModeChangeListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().addSystemAudioModeChangeListener(param2IHdmiSystemAudioModeChangeListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void addVendorCommandListener(IHdmiVendorCommandListener param2IHdmiVendorCommandListener, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiVendorCommandListener != null) {
          iBinder = param2IHdmiVendorCommandListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().addVendorCommandListener(param2IHdmiVendorCommandListener, param2Int);
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
    
    public void askRemoteDeviceToBecomeActiveSource(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().askRemoteDeviceToBecomeActiveSource(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean canChangeSystemAudioMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          bool = IHdmiControlService.Stub.getDefaultImpl().canChangeSystemAudioMode();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearTimerRecording(int param2Int1, int param2Int2, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().clearTimerRecording(param2Int1, param2Int2, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void deviceSelect(int param2Int, IHdmiControlCallback param2IHdmiControlCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int);
        if (param2IHdmiControlCallback != null) {
          iBinder = param2IHdmiControlCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().deviceSelect(param2Int, param2IHdmiControlCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public HdmiDeviceInfo getActiveSource() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        HdmiDeviceInfo hdmiDeviceInfo;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          hdmiDeviceInfo = IHdmiControlService.Stub.getDefaultImpl().getActiveSource();
          return hdmiDeviceInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          hdmiDeviceInfo = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(parcel2);
        } else {
          hdmiDeviceInfo = null;
        } 
        return hdmiDeviceInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<HdmiDeviceInfo> getDeviceList() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null)
          return IHdmiControlService.Stub.getDefaultImpl().getDeviceList(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(HdmiDeviceInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<HdmiDeviceInfo> getInputDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null)
          return IHdmiControlService.Stub.getDefaultImpl().getInputDevices(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(HdmiDeviceInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.hdmi.IHdmiControlService";
    }
    
    public int getPhysicalAddress() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null)
          return IHdmiControlService.Stub.getDefaultImpl().getPhysicalAddress(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<HdmiPortInfo> getPortInfo() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null)
          return IHdmiControlService.Stub.getDefaultImpl().getPortInfo(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(HdmiPortInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int[] getSupportedTypes() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null)
          return IHdmiControlService.Stub.getDefaultImpl().getSupportedTypes(); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getSystemAudioMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(18, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          bool = IHdmiControlService.Stub.getDefaultImpl().getSystemAudioMode();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isHdmiCecVolumeControlEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(45, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          bool = IHdmiControlService.Stub.getDefaultImpl().isHdmiCecVolumeControlEnabled();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void oneTouchPlay(IHdmiControlCallback param2IHdmiControlCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiControlCallback != null) {
          iBinder = param2IHdmiControlCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().oneTouchPlay(param2IHdmiControlCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void portSelect(int param2Int, IHdmiControlCallback param2IHdmiControlCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int);
        if (param2IHdmiControlCallback != null) {
          iBinder = param2IHdmiControlCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().portSelect(param2Int, param2IHdmiControlCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void powerOffRemoteDevice(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().powerOffRemoteDevice(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void powerOnRemoteDevice(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().powerOnRemoteDevice(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void queryDisplayStatus(IHdmiControlCallback param2IHdmiControlCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiControlCallback != null) {
          iBinder = param2IHdmiControlCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().queryDisplayStatus(param2IHdmiControlCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener param2IHdmiCecVolumeControlFeatureListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiCecVolumeControlFeatureListener != null) {
          iBinder = param2IHdmiCecVolumeControlFeatureListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().removeHdmiCecVolumeControlFeatureListener(param2IHdmiCecVolumeControlFeatureListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener param2IHdmiControlStatusChangeListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiControlStatusChangeListener != null) {
          iBinder = param2IHdmiControlStatusChangeListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().removeHdmiControlStatusChangeListener(param2IHdmiControlStatusChangeListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeHotplugEventListener(IHdmiHotplugEventListener param2IHdmiHotplugEventListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiHotplugEventListener != null) {
          iBinder = param2IHdmiHotplugEventListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().removeHotplugEventListener(param2IHdmiHotplugEventListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener param2IHdmiSystemAudioModeChangeListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiSystemAudioModeChangeListener != null) {
          iBinder = param2IHdmiSystemAudioModeChangeListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().removeSystemAudioModeChangeListener(param2IHdmiSystemAudioModeChangeListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportAudioStatus(int param2Int1, int param2Int2, int param2Int3, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().reportAudioStatus(param2Int1, param2Int2, param2Int3, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendKeyEvent(int param2Int1, int param2Int2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().sendKeyEvent(param2Int1, param2Int2, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendMhlVendorCommand(int param2Int1, int param2Int2, int param2Int3, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().sendMhlVendorCommand(param2Int1, param2Int2, param2Int3, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendStandby(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().sendStandby(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendVendorCommand(int param2Int1, int param2Int2, byte[] param2ArrayOfbyte, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().sendVendorCommand(param2Int1, param2Int2, param2ArrayOfbyte, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendVolumeKeyEvent(int param2Int1, int param2Int2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().sendVolumeKeyEvent(param2Int1, param2Int2, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setArcMode(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setArcMode(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setHdmiCecVolumeControlEnabled(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setHdmiCecVolumeControlEnabled(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setHdmiRecordListener(IHdmiRecordListener param2IHdmiRecordListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiRecordListener != null) {
          iBinder = param2IHdmiRecordListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setHdmiRecordListener(param2IHdmiRecordListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setInputChangeListener(IHdmiInputChangeListener param2IHdmiInputChangeListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2IHdmiInputChangeListener != null) {
          iBinder = param2IHdmiInputChangeListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setInputChangeListener(param2IHdmiInputChangeListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setProhibitMode(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setProhibitMode(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setStandbyMode(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setStandbyMode(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSystemAudioMode(boolean param2Boolean, IHdmiControlCallback param2IHdmiControlCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (param2IHdmiControlCallback != null) {
          iBinder = param2IHdmiControlCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setSystemAudioMode(param2Boolean, param2IHdmiControlCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSystemAudioModeOnForAudioOnlySource() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setSystemAudioModeOnForAudioOnlySource();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSystemAudioMute(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setSystemAudioMute(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSystemAudioVolume(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().setSystemAudioVolume(param2Int1, param2Int2, param2Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startOneTouchRecord(int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().startOneTouchRecord(param2Int, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startTimerRecording(int param2Int1, int param2Int2, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().startTimerRecording(param2Int1, param2Int2, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopOneTouchRecord(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
          IHdmiControlService.Stub.getDefaultImpl().stopOneTouchRecord(param2Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */