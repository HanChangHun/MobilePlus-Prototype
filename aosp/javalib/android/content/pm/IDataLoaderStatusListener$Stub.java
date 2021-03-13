package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IDataLoaderStatusListener {
  private static final String DESCRIPTOR = "android.content.pm.IDataLoaderStatusListener";
  
  static final int TRANSACTION_onStatusChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IDataLoaderStatusListener");
  }
  
  public static IDataLoaderStatusListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IDataLoaderStatusListener");
    return (iInterface != null && iInterface instanceof IDataLoaderStatusListener) ? (IDataLoaderStatusListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDataLoaderStatusListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onStatusChanged";
  }
  
  public static boolean setDefaultImpl(IDataLoaderStatusListener paramIDataLoaderStatusListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDataLoaderStatusListener != null) {
        Proxy.sDefaultImpl = paramIDataLoaderStatusListener;
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
      paramParcel2.writeString("android.content.pm.IDataLoaderStatusListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IDataLoaderStatusListener");
    onStatusChanged(paramParcel1.readInt(), paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IDataLoaderStatusListener {
    public static IDataLoaderStatusListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IDataLoaderStatusListener";
    }
    
    public void onStatusChanged(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoaderStatusListener");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDataLoaderStatusListener.Stub.getDefaultImpl() != null) {
          IDataLoaderStatusListener.Stub.getDefaultImpl().onStatusChanged(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoaderStatusListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */