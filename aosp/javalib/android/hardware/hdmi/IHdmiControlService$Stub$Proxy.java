package android.hardware.hdmi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IHdmiControlService {
  public static IHdmiControlService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addDeviceEventListener(IHdmiDeviceEventListener paramIHdmiDeviceEventListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiDeviceEventListener != null) {
        iBinder = paramIHdmiDeviceEventListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().addDeviceEventListener(paramIHdmiDeviceEventListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiCecVolumeControlFeatureListener != null) {
        iBinder = paramIHdmiCecVolumeControlFeatureListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().addHdmiCecVolumeControlFeatureListener(paramIHdmiCecVolumeControlFeatureListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiControlStatusChangeListener != null) {
        iBinder = paramIHdmiControlStatusChangeListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().addHdmiControlStatusChangeListener(paramIHdmiControlStatusChangeListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener paramIHdmiMhlVendorCommandListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiMhlVendorCommandListener != null) {
        iBinder = paramIHdmiMhlVendorCommandListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().addHdmiMhlVendorCommandListener(paramIHdmiMhlVendorCommandListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addHotplugEventListener(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiHotplugEventListener != null) {
        iBinder = paramIHdmiHotplugEventListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().addHotplugEventListener(paramIHdmiHotplugEventListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiSystemAudioModeChangeListener != null) {
        iBinder = paramIHdmiSystemAudioModeChangeListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().addSystemAudioModeChangeListener(paramIHdmiSystemAudioModeChangeListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addVendorCommandListener(IHdmiVendorCommandListener paramIHdmiVendorCommandListener, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiVendorCommandListener != null) {
        iBinder = paramIHdmiVendorCommandListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().addVendorCommandListener(paramIHdmiVendorCommandListener, paramInt);
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
  
  public void askRemoteDeviceToBecomeActiveSource(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().askRemoteDeviceToBecomeActiveSource(paramInt);
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
  
  public void clearTimerRecording(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().clearTimerRecording(paramInt1, paramInt2, paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void deviceSelect(int paramInt, IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt);
      if (paramIHdmiControlCallback != null) {
        iBinder = paramIHdmiControlCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().deviceSelect(paramInt, paramIHdmiControlCallback);
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
  
  public void oneTouchPlay(IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiControlCallback != null) {
        iBinder = paramIHdmiControlCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().oneTouchPlay(paramIHdmiControlCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void portSelect(int paramInt, IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt);
      if (paramIHdmiControlCallback != null) {
        iBinder = paramIHdmiControlCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().portSelect(paramInt, paramIHdmiControlCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void powerOffRemoteDevice(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().powerOffRemoteDevice(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void powerOnRemoteDevice(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().powerOnRemoteDevice(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void queryDisplayStatus(IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiControlCallback != null) {
        iBinder = paramIHdmiControlCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().queryDisplayStatus(paramIHdmiControlCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeHdmiCecVolumeControlFeatureListener(IHdmiCecVolumeControlFeatureListener paramIHdmiCecVolumeControlFeatureListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiCecVolumeControlFeatureListener != null) {
        iBinder = paramIHdmiCecVolumeControlFeatureListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().removeHdmiCecVolumeControlFeatureListener(paramIHdmiCecVolumeControlFeatureListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeHdmiControlStatusChangeListener(IHdmiControlStatusChangeListener paramIHdmiControlStatusChangeListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiControlStatusChangeListener != null) {
        iBinder = paramIHdmiControlStatusChangeListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().removeHdmiControlStatusChangeListener(paramIHdmiControlStatusChangeListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeHotplugEventListener(IHdmiHotplugEventListener paramIHdmiHotplugEventListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiHotplugEventListener != null) {
        iBinder = paramIHdmiHotplugEventListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().removeHotplugEventListener(paramIHdmiHotplugEventListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener paramIHdmiSystemAudioModeChangeListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiSystemAudioModeChangeListener != null) {
        iBinder = paramIHdmiSystemAudioModeChangeListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().removeSystemAudioModeChangeListener(paramIHdmiSystemAudioModeChangeListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void reportAudioStatus(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().reportAudioStatus(paramInt1, paramInt2, paramInt3, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendKeyEvent(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().sendKeyEvent(paramInt1, paramInt2, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendMhlVendorCommand(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().sendMhlVendorCommand(paramInt1, paramInt2, paramInt3, paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendStandby(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().sendStandby(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendVendorCommand(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().sendVendorCommand(paramInt1, paramInt2, paramArrayOfbyte, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendVolumeKeyEvent(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().sendVolumeKeyEvent(paramInt1, paramInt2, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setArcMode(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setArcMode(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setHdmiCecVolumeControlEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setHdmiCecVolumeControlEnabled(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setHdmiRecordListener(IHdmiRecordListener paramIHdmiRecordListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiRecordListener != null) {
        iBinder = paramIHdmiRecordListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setHdmiRecordListener(paramIHdmiRecordListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setInputChangeListener(IHdmiInputChangeListener paramIHdmiInputChangeListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramIHdmiInputChangeListener != null) {
        iBinder = paramIHdmiInputChangeListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setInputChangeListener(paramIHdmiInputChangeListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setProhibitMode(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setProhibitMode(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setStandbyMode(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setStandbyMode(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSystemAudioMode(boolean paramBoolean, IHdmiControlCallback paramIHdmiControlCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (paramIHdmiControlCallback != null) {
        iBinder = paramIHdmiControlCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setSystemAudioMode(paramBoolean, paramIHdmiControlCallback);
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
  
  public void setSystemAudioMute(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setSystemAudioMute(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSystemAudioVolume(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().setSystemAudioVolume(paramInt1, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startOneTouchRecord(int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().startOneTouchRecord(paramInt, paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startTimerRecording(int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().startTimerRecording(paramInt1, paramInt2, paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopOneTouchRecord(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IHdmiControlService.Stub.getDefaultImpl() != null) {
        IHdmiControlService.Stub.getDefaultImpl().stopOneTouchRecord(paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */