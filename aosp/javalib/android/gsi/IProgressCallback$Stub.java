package android.gsi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IProgressCallback {
  private static final String DESCRIPTOR = "android.gsi.IProgressCallback";
  
  static final int TRANSACTION_onProgress = 1;
  
  public Stub() {
    attachInterface(this, "android.gsi.IProgressCallback");
  }
  
  public static IProgressCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.gsi.IProgressCallback");
    return (iInterface != null && iInterface instanceof IProgressCallback) ? (IProgressCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IProgressCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onProgress";
  }
  
  public static boolean setDefaultImpl(IProgressCallback paramIProgressCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIProgressCallback != null) {
        Proxy.sDefaultImpl = paramIProgressCallback;
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
      paramParcel2.writeString("android.gsi.IProgressCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.gsi.IProgressCallback");
    onProgress(paramParcel1.readLong(), paramParcel1.readLong());
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IProgressCallback {
    public static IProgressCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.gsi.IProgressCallback";
    }
    
    public void onProgress(long param2Long1, long param2Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IProgressCallback");
        parcel1.writeLong(param2Long1);
        parcel1.writeLong(param2Long2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IProgressCallback.Stub.getDefaultImpl() != null) {
          IProgressCallback.Stub.getDefaultImpl().onProgress(param2Long1, param2Long2);
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


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IProgressCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */