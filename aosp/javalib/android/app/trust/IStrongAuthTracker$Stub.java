package android.app.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IStrongAuthTracker {
  private static final String DESCRIPTOR = "android.app.trust.IStrongAuthTracker";
  
  static final int TRANSACTION_onIsNonStrongBiometricAllowedChanged = 2;
  
  static final int TRANSACTION_onStrongAuthRequiredChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.app.trust.IStrongAuthTracker");
  }
  
  public static IStrongAuthTracker asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.trust.IStrongAuthTracker");
    return (iInterface != null && iInterface instanceof IStrongAuthTracker) ? (IStrongAuthTracker)iInterface : new Proxy(paramIBinder);
  }
  
  public static IStrongAuthTracker getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onIsNonStrongBiometricAllowedChanged") : "onStrongAuthRequiredChanged";
  }
  
  public static boolean setDefaultImpl(IStrongAuthTracker paramIStrongAuthTracker) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIStrongAuthTracker != null) {
        Proxy.sDefaultImpl = paramIStrongAuthTracker;
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
      boolean bool;
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.app.trust.IStrongAuthTracker");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.trust.IStrongAuthTracker");
      if (paramParcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onIsNonStrongBiometricAllowedChanged(bool, paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.app.trust.IStrongAuthTracker");
    onStrongAuthRequiredChanged(paramParcel1.readInt(), paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IStrongAuthTracker {
    public static IStrongAuthTracker sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.trust.IStrongAuthTracker";
    }
    
    public void onIsNonStrongBiometricAllowedChanged(boolean param2Boolean, int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IStrongAuthTracker.Stub.getDefaultImpl() != null) {
          IStrongAuthTracker.Stub.getDefaultImpl().onIsNonStrongBiometricAllowedChanged(param2Boolean, param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onStrongAuthRequiredChanged(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IStrongAuthTracker.Stub.getDefaultImpl() != null) {
          IStrongAuthTracker.Stub.getDefaultImpl().onStrongAuthRequiredChanged(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/trust/IStrongAuthTracker$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */