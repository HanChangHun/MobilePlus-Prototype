package android.hardware.fingerprint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IFingerprintClientActiveCallback {
  private static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintClientActiveCallback";
  
  static final int TRANSACTION_onClientActiveChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.fingerprint.IFingerprintClientActiveCallback");
  }
  
  public static IFingerprintClientActiveCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintClientActiveCallback");
    return (iInterface != null && iInterface instanceof IFingerprintClientActiveCallback) ? (IFingerprintClientActiveCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IFingerprintClientActiveCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onClientActiveChanged";
  }
  
  public static boolean setDefaultImpl(IFingerprintClientActiveCallback paramIFingerprintClientActiveCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIFingerprintClientActiveCallback != null) {
        Proxy.sDefaultImpl = paramIFingerprintClientActiveCallback;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.fingerprint.IFingerprintClientActiveCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.fingerprint.IFingerprintClientActiveCallback");
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onClientActiveChanged(bool);
    return true;
  }
  
  private static class Proxy implements IFingerprintClientActiveCallback {
    public static IFingerprintClientActiveCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.fingerprint.IFingerprintClientActiveCallback";
    }
    
    public void onClientActiveChanged(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintClientActiveCallback");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IFingerprintClientActiveCallback.Stub.getDefaultImpl() != null) {
          IFingerprintClientActiveCallback.Stub.getDefaultImpl().onClientActiveChanged(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintClientActiveCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */