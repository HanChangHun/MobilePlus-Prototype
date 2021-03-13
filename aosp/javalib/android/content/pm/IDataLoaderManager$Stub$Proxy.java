package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IDataLoaderManager {
  public static IDataLoaderManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean bindToDataLoader(int paramInt, DataLoaderParamsParcel paramDataLoaderParamsParcel, IDataLoaderStatusListener paramIDataLoaderStatusListener) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
      parcel1.writeInt(paramInt);
      boolean bool = true;
      if (paramDataLoaderParamsParcel != null) {
        parcel1.writeInt(1);
        paramDataLoaderParamsParcel.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIDataLoaderStatusListener != null) {
        iBinder = paramIDataLoaderStatusListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null) {
        bool = IDataLoaderManager.Stub.getDefaultImpl().bindToDataLoader(paramInt, paramDataLoaderParamsParcel, paramIDataLoaderStatusListener);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IDataLoader getDataLoader(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null)
        return IDataLoaderManager.Stub.getDefaultImpl().getDataLoader(paramInt); 
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
  
  public void unbindFromDataLoader(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null) {
        IDataLoaderManager.Stub.getDefaultImpl().unbindFromDataLoader(paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoaderManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */