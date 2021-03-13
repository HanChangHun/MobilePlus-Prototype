package android.app.blob;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBlobCommitCallback {
  private static final String DESCRIPTOR = "android.app.blob.IBlobCommitCallback";
  
  static final int TRANSACTION_onResult = 1;
  
  public Stub() {
    attachInterface(this, "android.app.blob.IBlobCommitCallback");
  }
  
  public static IBlobCommitCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.blob.IBlobCommitCallback");
    return (iInterface != null && iInterface instanceof IBlobCommitCallback) ? (IBlobCommitCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBlobCommitCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onResult";
  }
  
  public static boolean setDefaultImpl(IBlobCommitCallback paramIBlobCommitCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBlobCommitCallback != null) {
        Proxy.sDefaultImpl = paramIBlobCommitCallback;
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
      paramParcel2.writeString("android.app.blob.IBlobCommitCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.blob.IBlobCommitCallback");
    onResult(paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IBlobCommitCallback {
    public static IBlobCommitCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.blob.IBlobCommitCallback";
    }
    
    public void onResult(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.blob.IBlobCommitCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBlobCommitCallback.Stub.getDefaultImpl() != null) {
          IBlobCommitCallback.Stub.getDefaultImpl().onResult(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobCommitCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */