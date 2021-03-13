package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

class Proxy implements IDataLoader {
  public static IDataLoader sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void create(int paramInt, DataLoaderParamsParcel paramDataLoaderParamsParcel, FileSystemControlParcel paramFileSystemControlParcel, IDataLoaderStatusListener paramIDataLoaderStatusListener) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.content.pm.IDataLoader");
      parcel.writeInt(paramInt);
      if (paramDataLoaderParamsParcel != null) {
        parcel.writeInt(1);
        paramDataLoaderParamsParcel.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramFileSystemControlParcel != null) {
        parcel.writeInt(1);
        paramFileSystemControlParcel.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramIDataLoaderStatusListener != null) {
        iBinder = paramIDataLoaderStatusListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
        IDataLoader.Stub.getDefaultImpl().create(paramInt, paramDataLoaderParamsParcel, paramFileSystemControlParcel, paramIDataLoaderStatusListener);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void destroy(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IDataLoader");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
        IDataLoader.Stub.getDefaultImpl().destroy(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IDataLoader";
  }
  
  public void prepareImage(int paramInt, InstallationFileParcel[] paramArrayOfInstallationFileParcel, String[] paramArrayOfString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IDataLoader");
      parcel.writeInt(paramInt);
      parcel.writeTypedArray((Parcelable[])paramArrayOfInstallationFileParcel, 0);
      parcel.writeStringArray(paramArrayOfString);
      if (!this.mRemote.transact(5, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
        IDataLoader.Stub.getDefaultImpl().prepareImage(paramInt, paramArrayOfInstallationFileParcel, paramArrayOfString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void start(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IDataLoader");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
        IDataLoader.Stub.getDefaultImpl().start(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void stop(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IDataLoader");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
        IDataLoader.Stub.getDefaultImpl().stop(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoader$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */