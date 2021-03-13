package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IDataLoader {
  private static final String DESCRIPTOR = "android.content.pm.IDataLoader";
  
  static final int TRANSACTION_create = 1;
  
  static final int TRANSACTION_destroy = 4;
  
  static final int TRANSACTION_prepareImage = 5;
  
  static final int TRANSACTION_start = 2;
  
  static final int TRANSACTION_stop = 3;
  
  public Stub() {
    attachInterface(this, "android.content.pm.IDataLoader");
  }
  
  public static IDataLoader asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.IDataLoader");
    return (iInterface != null && iInterface instanceof IDataLoader) ? (IDataLoader)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDataLoader getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "prepareImage") : "destroy") : "stop") : "start") : "create";
  }
  
  public static boolean setDefaultImpl(IDataLoader paramIDataLoader) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDataLoader != null) {
        Proxy.sDefaultImpl = paramIDataLoader;
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
    FileSystemControlParcel fileSystemControlParcel;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.content.pm.IDataLoader");
              return true;
            } 
            paramParcel1.enforceInterface("android.content.pm.IDataLoader");
            prepareImage(paramParcel1.readInt(), (InstallationFileParcel[])paramParcel1.createTypedArray(InstallationFileParcel.CREATOR), paramParcel1.createStringArray());
            return true;
          } 
          paramParcel1.enforceInterface("android.content.pm.IDataLoader");
          destroy(paramParcel1.readInt());
          return true;
        } 
        paramParcel1.enforceInterface("android.content.pm.IDataLoader");
        stop(paramParcel1.readInt());
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.IDataLoader");
      start(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.IDataLoader");
    paramInt1 = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      DataLoaderParamsParcel dataLoaderParamsParcel = (DataLoaderParamsParcel)DataLoaderParamsParcel.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    if (paramParcel1.readInt() != 0) {
      fileSystemControlParcel = (FileSystemControlParcel)FileSystemControlParcel.CREATOR.createFromParcel(paramParcel1);
    } else {
      fileSystemControlParcel = null;
    } 
    create(paramInt1, (DataLoaderParamsParcel)paramParcel2, fileSystemControlParcel, IDataLoaderStatusListener.Stub.asInterface(paramParcel1.readStrongBinder()));
    return true;
  }
  
  private static class Proxy implements IDataLoader {
    public static IDataLoader sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void create(int param2Int, DataLoaderParamsParcel param2DataLoaderParamsParcel, FileSystemControlParcel param2FileSystemControlParcel, IDataLoaderStatusListener param2IDataLoaderStatusListener) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param2Int);
        if (param2DataLoaderParamsParcel != null) {
          parcel.writeInt(1);
          param2DataLoaderParamsParcel.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2FileSystemControlParcel != null) {
          parcel.writeInt(1);
          param2FileSystemControlParcel.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2IDataLoaderStatusListener != null) {
          iBinder = param2IDataLoaderStatusListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().create(param2Int, param2DataLoaderParamsParcel, param2FileSystemControlParcel, param2IDataLoaderStatusListener);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void destroy(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().destroy(param2Int);
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
    
    public void prepareImage(int param2Int, InstallationFileParcel[] param2ArrayOfInstallationFileParcel, String[] param2ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param2Int);
        parcel.writeTypedArray((Parcelable[])param2ArrayOfInstallationFileParcel, 0);
        parcel.writeStringArray(param2ArrayOfString);
        if (!this.mRemote.transact(5, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().prepareImage(param2Int, param2ArrayOfInstallationFileParcel, param2ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void start(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().start(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void stop(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().stop(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoader$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */