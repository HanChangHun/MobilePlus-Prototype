package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IDataLoaderManager {
  private static final String DESCRIPTOR = "android.content.pm.IDataLoaderManager";
  
  static final int TRANSACTION_bindToDataLoader = 1;
  
  static final int TRANSACTION_getDataLoader = 2;
  
  static final int TRANSACTION_unbindFromDataLoader = 3;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IDataLoaderManager");
  }
  
  public static IDataLoaderManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IDataLoaderManager");
    return (iInterface != null && iInterface instanceof IDataLoaderManager) ? (IDataLoaderManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDataLoaderManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "unbindFromDataLoader") : "getDataLoader") : "bindToDataLoader";
  }
  
  public static boolean setDefaultImpl(IDataLoaderManager paramIDataLoaderManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDataLoaderManager != null) {
        Proxy.sDefaultImpl = paramIDataLoaderManager;
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
    IDataLoader iDataLoader;
    DataLoaderParamsParcel dataLoaderParamsParcel;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.content.pm.IDataLoaderManager");
          return true;
        } 
        paramParcel1.enforceInterface("android.content.pm.IDataLoaderManager");
        unbindFromDataLoader(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.IDataLoaderManager");
      iDataLoader = getDataLoader(paramParcel1.readInt());
      paramParcel2.writeNoException();
      if (iDataLoader != null) {
        IBinder iBinder = iDataLoader.asBinder();
      } else {
        iDataLoader = null;
      } 
      paramParcel2.writeStrongBinder((IBinder)iDataLoader);
      return true;
    } 
    iDataLoader.enforceInterface("android.content.pm.IDataLoaderManager");
    paramInt1 = iDataLoader.readInt();
    if (iDataLoader.readInt() != 0) {
      dataLoaderParamsParcel = (DataLoaderParamsParcel)DataLoaderParamsParcel.CREATOR.createFromParcel((Parcel)iDataLoader);
    } else {
      dataLoaderParamsParcel = null;
    } 
    boolean bool = bindToDataLoader(paramInt1, dataLoaderParamsParcel, IDataLoaderStatusListener.Stub.asInterface(iDataLoader.readStrongBinder()));
    paramParcel2.writeNoException();
    paramParcel2.writeInt(bool);
    return true;
  }
  
  private static class Proxy implements IDataLoaderManager {
    public static IDataLoaderManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean bindToDataLoader(int param2Int, DataLoaderParamsParcel param2DataLoaderParamsParcel, IDataLoaderStatusListener param2IDataLoaderStatusListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
        parcel1.writeInt(param2Int);
        boolean bool = true;
        if (param2DataLoaderParamsParcel != null) {
          parcel1.writeInt(1);
          param2DataLoaderParamsParcel.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IDataLoaderStatusListener != null) {
          iBinder = param2IDataLoaderStatusListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null) {
          bool = IDataLoaderManager.Stub.getDefaultImpl().bindToDataLoader(param2Int, param2DataLoaderParamsParcel, param2IDataLoaderStatusListener);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IDataLoader getDataLoader(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null)
          return IDataLoaderManager.Stub.getDefaultImpl().getDataLoader(param2Int); 
        parcel2.readException();
        return IDataLoader.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IDataLoaderManager";
    }
    
    public void unbindFromDataLoader(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null) {
          IDataLoaderManager.Stub.getDefaultImpl().unbindFromDataLoader(param2Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoaderManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */