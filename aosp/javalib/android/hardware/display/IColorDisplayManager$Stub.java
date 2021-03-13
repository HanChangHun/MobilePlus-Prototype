package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IColorDisplayManager {
  private static final String DESCRIPTOR = "android.hardware.display.IColorDisplayManager";
  
  static final int TRANSACTION_getColorMode = 17;
  
  static final int TRANSACTION_getNightDisplayAutoMode = 10;
  
  static final int TRANSACTION_getNightDisplayAutoModeRaw = 11;
  
  static final int TRANSACTION_getNightDisplayColorTemperature = 8;
  
  static final int TRANSACTION_getNightDisplayCustomEndTime = 15;
  
  static final int TRANSACTION_getNightDisplayCustomStartTime = 13;
  
  static final int TRANSACTION_getTransformCapabilities = 5;
  
  static final int TRANSACTION_isDeviceColorManaged = 1;
  
  static final int TRANSACTION_isDisplayWhiteBalanceEnabled = 19;
  
  static final int TRANSACTION_isNightDisplayActivated = 6;
  
  static final int TRANSACTION_isSaturationActivated = 4;
  
  static final int TRANSACTION_setAppSaturationLevel = 3;
  
  static final int TRANSACTION_setColorMode = 18;
  
  static final int TRANSACTION_setDisplayWhiteBalanceEnabled = 20;
  
  static final int TRANSACTION_setNightDisplayActivated = 7;
  
  static final int TRANSACTION_setNightDisplayAutoMode = 12;
  
  static final int TRANSACTION_setNightDisplayColorTemperature = 9;
  
  static final int TRANSACTION_setNightDisplayCustomEndTime = 16;
  
  static final int TRANSACTION_setNightDisplayCustomStartTime = 14;
  
  static final int TRANSACTION_setSaturationLevel = 2;
  
  public Stub() {
    attachInterface(this, "android.hardware.display.IColorDisplayManager");
  }
  
  public static IColorDisplayManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.display.IColorDisplayManager");
    return (iInterface != null && iInterface instanceof IColorDisplayManager) ? (IColorDisplayManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IColorDisplayManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 20:
        return "setDisplayWhiteBalanceEnabled";
      case 19:
        return "isDisplayWhiteBalanceEnabled";
      case 18:
        return "setColorMode";
      case 17:
        return "getColorMode";
      case 16:
        return "setNightDisplayCustomEndTime";
      case 15:
        return "getNightDisplayCustomEndTime";
      case 14:
        return "setNightDisplayCustomStartTime";
      case 13:
        return "getNightDisplayCustomStartTime";
      case 12:
        return "setNightDisplayAutoMode";
      case 11:
        return "getNightDisplayAutoModeRaw";
      case 10:
        return "getNightDisplayAutoMode";
      case 9:
        return "setNightDisplayColorTemperature";
      case 8:
        return "getNightDisplayColorTemperature";
      case 7:
        return "setNightDisplayActivated";
      case 6:
        return "isNightDisplayActivated";
      case 5:
        return "getTransformCapabilities";
      case 4:
        return "isSaturationActivated";
      case 3:
        return "setAppSaturationLevel";
      case 2:
        return "setSaturationLevel";
      case 1:
        break;
    } 
    return "isDeviceColorManaged";
  }
  
  public static boolean setDefaultImpl(IColorDisplayManager paramIColorDisplayManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIColorDisplayManager != null) {
        Proxy.sDefaultImpl = paramIColorDisplayManager;
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
      boolean bool5;
      int m;
      boolean bool4;
      int k;
      boolean bool3;
      int j;
      boolean bool2;
      int i;
      Time time;
      boolean bool6 = false;
      boolean bool7 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 20:
          paramParcel1.enforceInterface("android.hardware.display.IColorDisplayManager");
          if (paramParcel1.readInt() != 0)
            bool7 = true; 
          bool5 = setDisplayWhiteBalanceEnabled(bool7);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool5);
          return true;
        case 19:
          paramParcel1.enforceInterface("android.hardware.display.IColorDisplayManager");
          bool5 = isDisplayWhiteBalanceEnabled();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool5);
          return true;
        case 18:
          paramParcel1.enforceInterface("android.hardware.display.IColorDisplayManager");
          setColorMode(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 17:
          paramParcel1.enforceInterface("android.hardware.display.IColorDisplayManager");
          m = getColorMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 16:
          paramParcel1.enforceInterface("android.hardware.display.IColorDisplayManager");
          if (paramParcel1.readInt() != 0) {
            Time time1 = (Time)Time.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          bool4 = setNightDisplayCustomEndTime((Time)paramParcel1);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool4);
          return true;
        case 15:
          paramParcel1.enforceInterface("android.hardware.display.IColorDisplayManager");
          time = getNightDisplayCustomEndTime();
          paramParcel2.writeNoException();
          if (time != null) {
            paramParcel2.writeInt(1);
            time.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 14:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          if (time.readInt() != 0) {
            time = (Time)Time.CREATOR.createFromParcel((Parcel)time);
          } else {
            time = null;
          } 
          bool4 = setNightDisplayCustomStartTime(time);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool4);
          return true;
        case 13:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          time = getNightDisplayCustomStartTime();
          paramParcel2.writeNoException();
          if (time != null) {
            paramParcel2.writeInt(1);
            time.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 12:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          bool4 = setNightDisplayAutoMode(time.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool4);
          return true;
        case 11:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          k = getNightDisplayAutoModeRaw();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(k);
          return true;
        case 10:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          k = getNightDisplayAutoMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(k);
          return true;
        case 9:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          bool3 = setNightDisplayColorTemperature(time.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 8:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          j = getNightDisplayColorTemperature();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 7:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          bool7 = bool6;
          if (time.readInt() != 0)
            bool7 = true; 
          bool2 = setNightDisplayActivated(bool7);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 6:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          bool2 = isNightDisplayActivated();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 5:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          i = getTransformCapabilities();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 4:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          bool1 = isSaturationActivated();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 3:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          bool1 = setAppSaturationLevel(time.readString(), time.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 2:
          time.enforceInterface("android.hardware.display.IColorDisplayManager");
          bool1 = setSaturationLevel(time.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 1:
          break;
      } 
      time.enforceInterface("android.hardware.display.IColorDisplayManager");
      boolean bool1 = isDeviceColorManaged();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool1);
      return true;
    } 
    paramParcel2.writeString("android.hardware.display.IColorDisplayManager");
    return true;
  }
  
  private static class Proxy implements IColorDisplayManager {
    public static IColorDisplayManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public int getColorMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null)
          return IColorDisplayManager.Stub.getDefaultImpl().getColorMode(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.display.IColorDisplayManager";
    }
    
    public int getNightDisplayAutoMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null)
          return IColorDisplayManager.Stub.getDefaultImpl().getNightDisplayAutoMode(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getNightDisplayAutoModeRaw() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null)
          return IColorDisplayManager.Stub.getDefaultImpl().getNightDisplayAutoModeRaw(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getNightDisplayColorTemperature() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null)
          return IColorDisplayManager.Stub.getDefaultImpl().getNightDisplayColorTemperature(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Time getNightDisplayCustomEndTime() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Time time;
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          time = IColorDisplayManager.Stub.getDefaultImpl().getNightDisplayCustomEndTime();
          return time;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          time = (Time)Time.CREATOR.createFromParcel(parcel2);
        } else {
          time = null;
        } 
        return time;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Time getNightDisplayCustomStartTime() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Time time;
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          time = IColorDisplayManager.Stub.getDefaultImpl().getNightDisplayCustomStartTime();
          return time;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          time = (Time)Time.CREATOR.createFromParcel(parcel2);
        } else {
          time = null;
        } 
        return time;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getTransformCapabilities() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null)
          return IColorDisplayManager.Stub.getDefaultImpl().getTransformCapabilities(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isDeviceColorManaged() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(1, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().isDeviceColorManaged();
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
    
    public boolean isDisplayWhiteBalanceEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(19, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().isDisplayWhiteBalanceEnabled();
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
    
    public boolean isNightDisplayActivated() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().isNightDisplayActivated();
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
    
    public boolean isSaturationActivated() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(4, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().isSaturationActivated();
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
    
    public boolean setAppSaturationLevel(String param2String, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().setAppSaturationLevel(param2String, param2Int);
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
    
    public void setColorMode(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          IColorDisplayManager.Stub.getDefaultImpl().setColorMode(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setDisplayWhiteBalanceEnabled(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        boolean bool = true;
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IColorDisplayManager.Stub.getDefaultImpl().setDisplayWhiteBalanceEnabled(param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setNightDisplayActivated(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        boolean bool = true;
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          param2Boolean = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayActivated(param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setNightDisplayAutoMode(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(12, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayAutoMode(param2Int);
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
    
    public boolean setNightDisplayColorTemperature(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(9, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayColorTemperature(param2Int);
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
    
    public boolean setNightDisplayCustomEndTime(Time param2Time) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        boolean bool = true;
        if (param2Time != null) {
          parcel1.writeInt(1);
          param2Time.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayCustomEndTime(param2Time);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setNightDisplayCustomStartTime(Time param2Time) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        boolean bool = true;
        if (param2Time != null) {
          parcel1.writeInt(1);
          param2Time.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayCustomStartTime(param2Time);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setSaturationLevel(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IColorDisplayManager.Stub.getDefaultImpl().setSaturationLevel(param2Int);
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
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IColorDisplayManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */