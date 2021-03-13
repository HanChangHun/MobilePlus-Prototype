package android.app.trust;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IStrongAuthTracker {
  public static IStrongAuthTracker sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.trust.IStrongAuthTracker";
  }
  
  public void onIsNonStrongBiometricAllowedChanged(boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IStrongAuthTracker.Stub.getDefaultImpl() != null) {
        IStrongAuthTracker.Stub.getDefaultImpl().onIsNonStrongBiometricAllowedChanged(paramBoolean, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onStrongAuthRequiredChanged(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IStrongAuthTracker.Stub.getDefaultImpl() != null) {
        IStrongAuthTracker.Stub.getDefaultImpl().onStrongAuthRequiredChanged(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/IStrongAuthTracker$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */