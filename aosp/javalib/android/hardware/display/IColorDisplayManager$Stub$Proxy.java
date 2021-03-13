package android.hardware.display;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IColorDisplayManager {
  public static IColorDisplayManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
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
  
  public boolean setAppSaturationLevel(String paramString, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(3, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        bool = IColorDisplayManager.Stub.getDefaultImpl().setAppSaturationLevel(paramString, paramInt);
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
  
  public void setColorMode(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        IColorDisplayManager.Stub.getDefaultImpl().setColorMode(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setDisplayWhiteBalanceEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IColorDisplayManager.Stub.getDefaultImpl().setDisplayWhiteBalanceEnabled(paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0) {
        paramBoolean = bool;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setNightDisplayActivated(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayActivated(paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0) {
        paramBoolean = bool;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setNightDisplayAutoMode(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(12, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        bool = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayAutoMode(paramInt);
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
  
  public boolean setNightDisplayColorTemperature(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(9, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        bool = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayColorTemperature(paramInt);
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
  
  public boolean setNightDisplayCustomEndTime(Time paramTime) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      boolean bool = true;
      if (paramTime != null) {
        parcel1.writeInt(1);
        paramTime.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        bool = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayCustomEndTime(paramTime);
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
  
  public boolean setNightDisplayCustomStartTime(Time paramTime) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      boolean bool = true;
      if (paramTime != null) {
        parcel1.writeInt(1);
        paramTime.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        bool = IColorDisplayManager.Stub.getDefaultImpl().setNightDisplayCustomStartTime(paramTime);
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
  
  public boolean setSaturationLevel(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.display.IColorDisplayManager");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IColorDisplayManager.Stub.getDefaultImpl() != null) {
        bool = IColorDisplayManager.Stub.getDefaultImpl().setSaturationLevel(paramInt);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IColorDisplayManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */