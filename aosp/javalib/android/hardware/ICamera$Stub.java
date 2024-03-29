package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ICamera {
  private static final String DESCRIPTOR = "android.hardware.ICamera";
  
  static final int TRANSACTION_disconnect = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.ICamera");
  }
  
  public static ICamera asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.ICamera");
    return (iInterface != null && iInterface instanceof ICamera) ? (ICamera)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICamera getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "disconnect";
  }
  
  public static boolean setDefaultImpl(ICamera paramICamera) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICamera != null) {
        Proxy.sDefaultImpl = paramICamera;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.ICamera");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.ICamera");
    disconnect();
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements ICamera {
    public static ICamera sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void disconnect() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ICamera");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICamera.Stub.getDefaultImpl() != null) {
          ICamera.Stub.getDefaultImpl().disconnect();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ICamera";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICamera$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */