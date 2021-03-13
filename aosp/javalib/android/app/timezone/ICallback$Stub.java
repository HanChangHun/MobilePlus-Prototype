package android.app.timezone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ICallback {
  private static final String DESCRIPTOR = "android.app.timezone.ICallback";
  
  static final int TRANSACTION_onFinished = 1;
  
  public Stub() {
    attachInterface(this, "android.app.timezone.ICallback");
  }
  
  public static ICallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.timezone.ICallback");
    return (iInterface != null && iInterface instanceof ICallback) ? (ICallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static ICallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onFinished";
  }
  
  public static boolean setDefaultImpl(ICallback paramICallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramICallback != null) {
        Proxy.sDefaultImpl = paramICallback;
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
      paramParcel2.writeString("android.app.timezone.ICallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.timezone.ICallback");
    onFinished(paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements ICallback {
    public static ICallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.timezone.ICallback";
    }
    
    public void onFinished(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.timezone.ICallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && ICallback.Stub.getDefaultImpl() != null) {
          ICallback.Stub.getDefaultImpl().onFinished(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/ICallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */