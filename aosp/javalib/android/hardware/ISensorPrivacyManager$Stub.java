package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISensorPrivacyManager {
  private static final String DESCRIPTOR = "android.hardware.ISensorPrivacyManager";
  
  static final int TRANSACTION_addSensorPrivacyListener = 1;
  
  static final int TRANSACTION_isSensorPrivacyEnabled = 3;
  
  static final int TRANSACTION_removeSensorPrivacyListener = 2;
  
  static final int TRANSACTION_setSensorPrivacy = 4;
  
  public Stub() {
    attachInterface(this, "android.hardware.ISensorPrivacyManager");
  }
  
  public static ISensorPrivacyManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.ISensorPrivacyManager");
    return (iInterface != null && iInterface instanceof ISensorPrivacyManager) ? (ISensorPrivacyManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISensorPrivacyManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? null : "setSensorPrivacy") : "isSensorPrivacyEnabled") : "removeSensorPrivacyListener") : "addSensorPrivacyListener";
  }
  
  public static boolean setDefaultImpl(ISensorPrivacyManager paramISensorPrivacyManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISensorPrivacyManager != null) {
        Proxy.sDefaultImpl = paramISensorPrivacyManager;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          boolean bool1;
          if (paramInt1 != 4) {
            if (paramInt1 != 1598968902)
              return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
            paramParcel2.writeString("android.hardware.ISensorPrivacyManager");
            return true;
          } 
          paramParcel1.enforceInterface("android.hardware.ISensorPrivacyManager");
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          setSensorPrivacy(bool1);
          paramParcel2.writeNoException();
          return true;
        } 
        paramParcel1.enforceInterface("android.hardware.ISensorPrivacyManager");
        boolean bool = isSensorPrivacyEnabled();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(bool);
        return true;
      } 
      paramParcel1.enforceInterface("android.hardware.ISensorPrivacyManager");
      removeSensorPrivacyListener(ISensorPrivacyListener.Stub.asInterface(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.ISensorPrivacyManager");
    addSensorPrivacyListener(ISensorPrivacyListener.Stub.asInterface(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements ISensorPrivacyManager {
    public static ISensorPrivacyManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addSensorPrivacyListener(ISensorPrivacyListener param2ISensorPrivacyListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
        if (param2ISensorPrivacyListener != null) {
          iBinder = param2ISensorPrivacyListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
          ISensorPrivacyManager.Stub.getDefaultImpl().addSensorPrivacyListener(param2ISensorPrivacyListener);
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
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ISensorPrivacyManager";
    }
    
    public boolean isSensorPrivacyEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
          bool = ISensorPrivacyManager.Stub.getDefaultImpl().isSensorPrivacyEnabled();
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
    
    public void removeSensorPrivacyListener(ISensorPrivacyListener param2ISensorPrivacyListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
        if (param2ISensorPrivacyListener != null) {
          iBinder = param2ISensorPrivacyListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
          ISensorPrivacyManager.Stub.getDefaultImpl().removeSensorPrivacyListener(param2ISensorPrivacyListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSensorPrivacy(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
          ISensorPrivacyManager.Stub.getDefaultImpl().setSensorPrivacy(param2Boolean);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISensorPrivacyManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */