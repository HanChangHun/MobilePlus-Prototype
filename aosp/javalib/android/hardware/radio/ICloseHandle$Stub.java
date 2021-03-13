package android.hardware.radio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ICloseHandle {
  private static final String DESCRIPTOR = "android.hardware.radio.ICloseHandle";
  
  static final int TRANSACTION_close = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.radio.ICloseHandle");
  }
  
  public static ICloseHandle asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.radio.ICloseHandle");
    return (iInterface != null && iInterface instanceof ICloseHandle) ? (ICloseHandle)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICloseHandle getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "close";
  }
  
  public static boolean setDefaultImpl(ICloseHandle paramICloseHandle) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICloseHandle != null) {
        Proxy.sDefaultImpl = paramICloseHandle;
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
      paramParcel2.writeString("android.hardware.radio.ICloseHandle");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.radio.ICloseHandle");
    close();
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements ICloseHandle {
    public static ICloseHandle sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void close() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ICloseHandle");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICloseHandle.Stub.getDefaultImpl() != null) {
          ICloseHandle.Stub.getDefaultImpl().close();
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
      return "android.hardware.radio.ICloseHandle";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ICloseHandle$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */