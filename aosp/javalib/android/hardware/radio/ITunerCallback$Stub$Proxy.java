package android.hardware.radio;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

class Proxy implements ITunerCallback {
  public static ITunerCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.radio.ITunerCallback";
  }
  
  public void onAntennaState(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(7, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onAntennaState(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onBackgroundScanAvailabilityChange(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(8, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onBackgroundScanAvailabilityChange(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onBackgroundScanComplete() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (!this.mRemote.transact(9, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onBackgroundScanComplete();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onConfigurationChanged(RadioManager.BandConfig paramBandConfig) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (paramBandConfig != null) {
        parcel.writeInt(1);
        paramBandConfig.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onConfigurationChanged(paramBandConfig);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onCurrentProgramInfoChanged(RadioManager.ProgramInfo paramProgramInfo) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (paramProgramInfo != null) {
        parcel.writeInt(1);
        paramProgramInfo.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onCurrentProgramInfoChanged(paramProgramInfo);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onEmergencyAnnouncement(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(6, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onEmergencyAnnouncement(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onError(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onError(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onParametersUpdated(Map paramMap) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      parcel.writeMap(paramMap);
      if (!this.mRemote.transact(12, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onParametersUpdated(paramMap);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onProgramListChanged() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (!this.mRemote.transact(10, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onProgramListChanged();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onProgramListUpdated(ProgramList.Chunk paramChunk) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (paramChunk != null) {
        parcel.writeInt(1);
        paramChunk.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(11, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onProgramListUpdated(paramChunk);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTrafficAnnouncement(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(5, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onTrafficAnnouncement(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onTuneFailed(int paramInt, ProgramSelector paramProgramSelector) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
      parcel.writeInt(paramInt);
      if (paramProgramSelector != null) {
        parcel.writeInt(1);
        paramProgramSelector.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
        ITunerCallback.Stub.getDefaultImpl().onTuneFailed(paramInt, paramProgramSelector);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ITunerCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */