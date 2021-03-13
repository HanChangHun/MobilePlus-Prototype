package android.hardware.radio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

public abstract class Stub extends Binder implements ITunerCallback {
  private static final String DESCRIPTOR = "android.hardware.radio.ITunerCallback";
  
  static final int TRANSACTION_onAntennaState = 7;
  
  static final int TRANSACTION_onBackgroundScanAvailabilityChange = 8;
  
  static final int TRANSACTION_onBackgroundScanComplete = 9;
  
  static final int TRANSACTION_onConfigurationChanged = 3;
  
  static final int TRANSACTION_onCurrentProgramInfoChanged = 4;
  
  static final int TRANSACTION_onEmergencyAnnouncement = 6;
  
  static final int TRANSACTION_onError = 1;
  
  static final int TRANSACTION_onParametersUpdated = 12;
  
  static final int TRANSACTION_onProgramListChanged = 10;
  
  static final int TRANSACTION_onProgramListUpdated = 11;
  
  static final int TRANSACTION_onTrafficAnnouncement = 5;
  
  static final int TRANSACTION_onTuneFailed = 2;
  
  public Stub() {
    attachInterface(this, "android.hardware.radio.ITunerCallback");
  }
  
  public static ITunerCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.radio.ITunerCallback");
    return (iInterface != null && iInterface instanceof ITunerCallback) ? (ITunerCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITunerCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 12:
        return "onParametersUpdated";
      case 11:
        return "onProgramListUpdated";
      case 10:
        return "onProgramListChanged";
      case 9:
        return "onBackgroundScanComplete";
      case 8:
        return "onBackgroundScanAvailabilityChange";
      case 7:
        return "onAntennaState";
      case 6:
        return "onEmergencyAnnouncement";
      case 5:
        return "onTrafficAnnouncement";
      case 4:
        return "onCurrentProgramInfoChanged";
      case 3:
        return "onConfigurationChanged";
      case 2:
        return "onTuneFailed";
      case 1:
        break;
    } 
    return "onError";
  }
  
  public static boolean setDefaultImpl(ITunerCallback paramITunerCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITunerCallback != null) {
        Proxy.sDefaultImpl = paramITunerCallback;
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
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      boolean bool4 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 12:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          onParametersUpdated(paramParcel1.readHashMap(getClass().getClassLoader()));
          return true;
        case 11:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          if (paramParcel1.readInt() != 0) {
            ProgramList.Chunk chunk = (ProgramList.Chunk)ProgramList.Chunk.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onProgramListUpdated((ProgramList.Chunk)paramParcel1);
          return true;
        case 10:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          onProgramListChanged();
          return true;
        case 9:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          onBackgroundScanComplete();
          return true;
        case 8:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          onBackgroundScanAvailabilityChange(bool4);
          return true;
        case 7:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          bool4 = bool1;
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          onAntennaState(bool4);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          bool4 = bool2;
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          onEmergencyAnnouncement(bool4);
          return true;
        case 5:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          bool4 = bool3;
          if (paramParcel1.readInt() != 0)
            bool4 = true; 
          onTrafficAnnouncement(bool4);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          if (paramParcel1.readInt() != 0) {
            RadioManager.ProgramInfo programInfo = (RadioManager.ProgramInfo)RadioManager.ProgramInfo.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onCurrentProgramInfoChanged((RadioManager.ProgramInfo)paramParcel1);
          return true;
        case 3:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          if (paramParcel1.readInt() != 0) {
            RadioManager.BandConfig bandConfig = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onConfigurationChanged((RadioManager.BandConfig)paramParcel1);
          return true;
        case 2:
          paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            ProgramSelector programSelector = (ProgramSelector)ProgramSelector.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onTuneFailed(paramInt1, (ProgramSelector)paramParcel1);
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.hardware.radio.ITunerCallback");
      onError(paramParcel1.readInt());
      return true;
    } 
    paramParcel2.writeString("android.hardware.radio.ITunerCallback");
    return true;
  }
  
  private static class Proxy implements ITunerCallback {
    public static ITunerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.radio.ITunerCallback";
    }
    
    public void onAntennaState(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(7, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onAntennaState(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onBackgroundScanAvailabilityChange(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(8, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onBackgroundScanAvailabilityChange(param2Boolean);
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
    
    public void onConfigurationChanged(RadioManager.BandConfig param2BandConfig) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        if (param2BandConfig != null) {
          parcel.writeInt(1);
          param2BandConfig.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onConfigurationChanged(param2BandConfig);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onCurrentProgramInfoChanged(RadioManager.ProgramInfo param2ProgramInfo) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        if (param2ProgramInfo != null) {
          parcel.writeInt(1);
          param2ProgramInfo.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onCurrentProgramInfoChanged(param2ProgramInfo);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEmergencyAnnouncement(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(6, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onEmergencyAnnouncement(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onError(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onError(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onParametersUpdated(Map param2Map) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        parcel.writeMap(param2Map);
        if (!this.mRemote.transact(12, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onParametersUpdated(param2Map);
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
    
    public void onProgramListUpdated(ProgramList.Chunk param2Chunk) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        if (param2Chunk != null) {
          parcel.writeInt(1);
          param2Chunk.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(11, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onProgramListUpdated(param2Chunk);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTrafficAnnouncement(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(5, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onTrafficAnnouncement(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onTuneFailed(int param2Int, ProgramSelector param2ProgramSelector) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.radio.ITunerCallback");
        parcel.writeInt(param2Int);
        if (param2ProgramSelector != null) {
          parcel.writeInt(1);
          param2ProgramSelector.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && ITunerCallback.Stub.getDefaultImpl() != null) {
          ITunerCallback.Stub.getDefaultImpl().onTuneFailed(param2Int, param2ProgramSelector);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ITunerCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */