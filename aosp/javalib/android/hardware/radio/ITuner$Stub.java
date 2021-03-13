package android.hardware.radio;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public abstract class Stub extends Binder implements ITuner {
  private static final String DESCRIPTOR = "android.hardware.radio.ITuner";
  
  static final int TRANSACTION_cancel = 10;
  
  static final int TRANSACTION_cancelAnnouncement = 11;
  
  static final int TRANSACTION_close = 1;
  
  static final int TRANSACTION_getConfiguration = 4;
  
  static final int TRANSACTION_getImage = 12;
  
  static final int TRANSACTION_getParameters = 20;
  
  static final int TRANSACTION_isClosed = 2;
  
  static final int TRANSACTION_isConfigFlagSet = 17;
  
  static final int TRANSACTION_isConfigFlagSupported = 16;
  
  static final int TRANSACTION_isMuted = 6;
  
  static final int TRANSACTION_scan = 8;
  
  static final int TRANSACTION_setConfigFlag = 18;
  
  static final int TRANSACTION_setConfiguration = 3;
  
  static final int TRANSACTION_setMuted = 5;
  
  static final int TRANSACTION_setParameters = 19;
  
  static final int TRANSACTION_startBackgroundScan = 13;
  
  static final int TRANSACTION_startProgramListUpdates = 14;
  
  static final int TRANSACTION_step = 7;
  
  static final int TRANSACTION_stopProgramListUpdates = 15;
  
  static final int TRANSACTION_tune = 9;
  
  public Stub() {
    attachInterface(this, "android.hardware.radio.ITuner");
  }
  
  public static ITuner asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.radio.ITuner");
    return (iInterface != null && iInterface instanceof ITuner) ? (ITuner)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITuner getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 20:
        return "getParameters";
      case 19:
        return "setParameters";
      case 18:
        return "setConfigFlag";
      case 17:
        return "isConfigFlagSet";
      case 16:
        return "isConfigFlagSupported";
      case 15:
        return "stopProgramListUpdates";
      case 14:
        return "startProgramListUpdates";
      case 13:
        return "startBackgroundScan";
      case 12:
        return "getImage";
      case 11:
        return "cancelAnnouncement";
      case 10:
        return "cancel";
      case 9:
        return "tune";
      case 8:
        return "scan";
      case 7:
        return "step";
      case 6:
        return "isMuted";
      case 5:
        return "setMuted";
      case 4:
        return "getConfiguration";
      case 3:
        return "setConfiguration";
      case 2:
        return "isClosed";
      case 1:
        break;
    } 
    return "close";
  }
  
  public static boolean setDefaultImpl(ITuner paramITuner) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITuner != null) {
        Proxy.sDefaultImpl = paramITuner;
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
      boolean bool;
      Map map;
      Bitmap bitmap;
      RadioManager.BandConfig bandConfig;
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      boolean bool4 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 20:
          paramParcel1.enforceInterface("android.hardware.radio.ITuner");
          map = getParameters(paramParcel1.createStringArrayList());
          paramParcel2.writeNoException();
          paramParcel2.writeMap(map);
          return true;
        case 19:
          map.enforceInterface("android.hardware.radio.ITuner");
          map = setParameters(map.readHashMap(getClass().getClassLoader()));
          paramParcel2.writeNoException();
          paramParcel2.writeMap(map);
          return true;
        case 18:
          map.enforceInterface("android.hardware.radio.ITuner");
          paramInt1 = map.readInt();
          if (map.readInt() != 0)
            bool4 = true; 
          setConfigFlag(paramInt1, bool4);
          paramParcel2.writeNoException();
          return true;
        case 17:
          map.enforceInterface("android.hardware.radio.ITuner");
          bool = isConfigFlagSet(map.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 16:
          map.enforceInterface("android.hardware.radio.ITuner");
          bool = isConfigFlagSupported(map.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 15:
          map.enforceInterface("android.hardware.radio.ITuner");
          stopProgramListUpdates();
          paramParcel2.writeNoException();
          return true;
        case 14:
          map.enforceInterface("android.hardware.radio.ITuner");
          if (map.readInt() != 0) {
            ProgramList.Filter filter = (ProgramList.Filter)ProgramList.Filter.CREATOR.createFromParcel((Parcel)map);
          } else {
            map = null;
          } 
          startProgramListUpdates((ProgramList.Filter)map);
          paramParcel2.writeNoException();
          return true;
        case 13:
          map.enforceInterface("android.hardware.radio.ITuner");
          bool = startBackgroundScan();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 12:
          map.enforceInterface("android.hardware.radio.ITuner");
          bitmap = getImage(map.readInt());
          paramParcel2.writeNoException();
          if (bitmap != null) {
            paramParcel2.writeInt(1);
            bitmap.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 11:
          bitmap.enforceInterface("android.hardware.radio.ITuner");
          cancelAnnouncement();
          paramParcel2.writeNoException();
          return true;
        case 10:
          bitmap.enforceInterface("android.hardware.radio.ITuner");
          cancel();
          paramParcel2.writeNoException();
          return true;
        case 9:
          bitmap.enforceInterface("android.hardware.radio.ITuner");
          if (bitmap.readInt() != 0) {
            ProgramSelector programSelector = (ProgramSelector)ProgramSelector.CREATOR.createFromParcel((Parcel)bitmap);
          } else {
            bitmap = null;
          } 
          tune((ProgramSelector)bitmap);
          paramParcel2.writeNoException();
          return true;
        case 8:
          bitmap.enforceInterface("android.hardware.radio.ITuner");
          if (bitmap.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          if (bitmap.readInt() != 0)
            bool1 = true; 
          scan(bool4, bool1);
          paramParcel2.writeNoException();
          return true;
        case 7:
          bitmap.enforceInterface("android.hardware.radio.ITuner");
          if (bitmap.readInt() != 0) {
            bool4 = true;
          } else {
            bool4 = false;
          } 
          bool1 = bool2;
          if (bitmap.readInt() != 0)
            bool1 = true; 
          step(bool4, bool1);
          paramParcel2.writeNoException();
          return true;
        case 6:
          bitmap.enforceInterface("android.hardware.radio.ITuner");
          bool = isMuted();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 5:
          bitmap.enforceInterface("android.hardware.radio.ITuner");
          bool4 = bool3;
          if (bitmap.readInt() != 0)
            bool4 = true; 
          setMuted(bool4);
          paramParcel2.writeNoException();
          return true;
        case 4:
          bitmap.enforceInterface("android.hardware.radio.ITuner");
          bandConfig = getConfiguration();
          paramParcel2.writeNoException();
          if (bandConfig != null) {
            paramParcel2.writeInt(1);
            bandConfig.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 3:
          bandConfig.enforceInterface("android.hardware.radio.ITuner");
          if (bandConfig.readInt() != 0) {
            bandConfig = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel((Parcel)bandConfig);
          } else {
            bandConfig = null;
          } 
          setConfiguration(bandConfig);
          paramParcel2.writeNoException();
          return true;
        case 2:
          bandConfig.enforceInterface("android.hardware.radio.ITuner");
          bool = isClosed();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 1:
          break;
      } 
      bandConfig.enforceInterface("android.hardware.radio.ITuner");
      close();
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.hardware.radio.ITuner");
    return true;
  }
  
  private static class Proxy implements ITuner {
    public static ITuner sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cancel() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().cancel();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelAnnouncement() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().cancelAnnouncement();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void close() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().close();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public RadioManager.BandConfig getConfiguration() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        RadioManager.BandConfig bandConfig;
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          bandConfig = ITuner.Stub.getDefaultImpl().getConfiguration();
          return bandConfig;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          bandConfig = (RadioManager.BandConfig)RadioManager.BandConfig.CREATOR.createFromParcel(parcel2);
        } else {
          bandConfig = null;
        } 
        return bandConfig;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bitmap getImage(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Bitmap bitmap;
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          bitmap = ITuner.Stub.getDefaultImpl().getImage(param2Int);
          return bitmap;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
        } else {
          bitmap = null;
        } 
        return bitmap;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.radio.ITuner";
    }
    
    public Map getParameters(List<String> param2List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        parcel1.writeStringList(param2List);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null)
          return ITuner.Stub.getDefaultImpl().getParameters(param2List); 
        parcel2.readException();
        return parcel2.readHashMap(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isClosed() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          bool = ITuner.Stub.getDefaultImpl().isClosed();
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
    
    public boolean isConfigFlagSet(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          bool = ITuner.Stub.getDefaultImpl().isConfigFlagSet(param2Int);
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
    
    public boolean isConfigFlagSupported(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(16, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          bool = ITuner.Stub.getDefaultImpl().isConfigFlagSupported(param2Int);
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
    
    public boolean isMuted() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          bool = ITuner.Stub.getDefaultImpl().isMuted();
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
    
    public void scan(boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        boolean bool1 = true;
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().scan(param2Boolean1, param2Boolean2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setConfigFlag(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().setConfigFlag(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setConfiguration(RadioManager.BandConfig param2BandConfig) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (param2BandConfig != null) {
          parcel1.writeInt(1);
          param2BandConfig.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().setConfiguration(param2BandConfig);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setMuted(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().setMuted(param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Map setParameters(Map param2Map) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        parcel1.writeMap(param2Map);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          param2Map = ITuner.Stub.getDefaultImpl().setParameters(param2Map);
          return param2Map;
        } 
        parcel2.readException();
        param2Map = parcel2.readHashMap(getClass().getClassLoader());
        return param2Map;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startBackgroundScan() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(13, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          bool = ITuner.Stub.getDefaultImpl().startBackgroundScan();
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
    
    public void startProgramListUpdates(ProgramList.Filter param2Filter) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (param2Filter != null) {
          parcel1.writeInt(1);
          param2Filter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().startProgramListUpdates(param2Filter);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void step(boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        boolean bool1 = true;
        if (param2Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param2Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().step(param2Boolean1, param2Boolean2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopProgramListUpdates() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().stopProgramListUpdates();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void tune(ProgramSelector param2ProgramSelector) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ITuner");
        if (param2ProgramSelector != null) {
          parcel1.writeInt(1);
          param2ProgramSelector.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && ITuner.Stub.getDefaultImpl() != null) {
          ITuner.Stub.getDefaultImpl().tune(param2ProgramSelector);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ITuner$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */