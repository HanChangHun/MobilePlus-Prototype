package android.app;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IUiModeManager {
  public static IUiModeManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void disableCarMode(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        IUiModeManager.Stub.getDefaultImpl().disableCarMode(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void disableCarModeByCallingPackage(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        IUiModeManager.Stub.getDefaultImpl().disableCarModeByCallingPackage(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enableCarMode(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        IUiModeManager.Stub.getDefaultImpl().enableCarMode(paramInt1, paramInt2, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getCurrentModeType() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null)
        return IUiModeManager.Stub.getDefaultImpl().getCurrentModeType(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getCustomNightModeEnd() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null)
        return IUiModeManager.Stub.getDefaultImpl().getCustomNightModeEnd(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getCustomNightModeStart() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null)
        return IUiModeManager.Stub.getDefaultImpl().getCustomNightModeStart(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IUiModeManager";
  }
  
  public int getNightMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null)
        return IUiModeManager.Stub.getDefaultImpl().getNightMode(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isNightModeLocked() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(8, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        bool = IUiModeManager.Stub.getDefaultImpl().isNightModeLocked();
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
  
  public boolean isUiModeLocked() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(7, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        bool = IUiModeManager.Stub.getDefaultImpl().isUiModeLocked();
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
  
  public void setCustomNightModeEnd(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        IUiModeManager.Stub.getDefaultImpl().setCustomNightModeEnd(paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setCustomNightModeStart(long paramLong) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      parcel1.writeLong(paramLong);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        IUiModeManager.Stub.getDefaultImpl().setCustomNightModeStart(paramLong);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setNightMode(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        IUiModeManager.Stub.getDefaultImpl().setNightMode(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setNightModeActivated(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.IUiModeManager");
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IUiModeManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IUiModeManager.Stub.getDefaultImpl().setNightModeActivated(paramBoolean);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUiModeManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */