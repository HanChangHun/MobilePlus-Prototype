package android.hardware;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISensorPrivacyManager {
  public static ISensorPrivacyManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addSensorPrivacyListener(ISensorPrivacyListener paramISensorPrivacyListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
      if (paramISensorPrivacyListener != null) {
        iBinder = paramISensorPrivacyListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
        ISensorPrivacyManager.Stub.getDefaultImpl().addSensorPrivacyListener(paramISensorPrivacyListener);
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
  
  public void removeSensorPrivacyListener(ISensorPrivacyListener paramISensorPrivacyListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
      if (paramISensorPrivacyListener != null) {
        iBinder = paramISensorPrivacyListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
        ISensorPrivacyManager.Stub.getDefaultImpl().removeSensorPrivacyListener(paramISensorPrivacyListener);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSensorPrivacy(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
        ISensorPrivacyManager.Stub.getDefaultImpl().setSensorPrivacy(paramBoolean);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISensorPrivacyManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */