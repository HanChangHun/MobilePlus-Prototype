package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements StartInstallingUpdateCallback {
  private static final String DESCRIPTOR = "android.app.admin.StartInstallingUpdateCallback";
  
  static final int TRANSACTION_onStartInstallingUpdateError = 1;
  
  public Stub() {
    attachInterface(this, "android.app.admin.StartInstallingUpdateCallback");
  }
  
  public static StartInstallingUpdateCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.admin.StartInstallingUpdateCallback");
    return (iInterface != null && iInterface instanceof StartInstallingUpdateCallback) ? (StartInstallingUpdateCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static StartInstallingUpdateCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onStartInstallingUpdateError";
  }
  
  public static boolean setDefaultImpl(StartInstallingUpdateCallback paramStartInstallingUpdateCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramStartInstallingUpdateCallback != null) {
        Proxy.sDefaultImpl = paramStartInstallingUpdateCallback;
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
      paramParcel2.writeString("android.app.admin.StartInstallingUpdateCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.admin.StartInstallingUpdateCallback");
    onStartInstallingUpdateError(paramParcel1.readInt(), paramParcel1.readString());
    return true;
  }
  
  private static class Proxy implements StartInstallingUpdateCallback {
    public static StartInstallingUpdateCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.admin.StartInstallingUpdateCallback";
    }
    
    public void onStartInstallingUpdateError(int param2Int, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.admin.StartInstallingUpdateCallback");
        parcel.writeInt(param2Int);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(1, parcel, null, 1) && StartInstallingUpdateCallback.Stub.getDefaultImpl() != null) {
          StartInstallingUpdateCallback.Stub.getDefaultImpl().onStartInstallingUpdateError(param2Int, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/StartInstallingUpdateCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */