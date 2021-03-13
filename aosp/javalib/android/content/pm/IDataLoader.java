package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface IDataLoader extends IInterface {
  void create(int paramInt, DataLoaderParamsParcel paramDataLoaderParamsParcel, FileSystemControlParcel paramFileSystemControlParcel, IDataLoaderStatusListener paramIDataLoaderStatusListener) throws RemoteException;
  
  void destroy(int paramInt) throws RemoteException;
  
  void prepareImage(int paramInt, InstallationFileParcel[] paramArrayOfInstallationFileParcel, String[] paramArrayOfString) throws RemoteException;
  
  void start(int paramInt) throws RemoteException;
  
  void stop(int paramInt) throws RemoteException;
  
  public static class Default implements IDataLoader {
    public IBinder asBinder() {
      return null;
    }
    
    public void create(int param1Int, DataLoaderParamsParcel param1DataLoaderParamsParcel, FileSystemControlParcel param1FileSystemControlParcel, IDataLoaderStatusListener param1IDataLoaderStatusListener) throws RemoteException {}
    
    public void destroy(int param1Int) throws RemoteException {}
    
    public void prepareImage(int param1Int, InstallationFileParcel[] param1ArrayOfInstallationFileParcel, String[] param1ArrayOfString) throws RemoteException {}
    
    public void start(int param1Int) throws RemoteException {}
    
    public void stop(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IDataLoader {
    private static final String DESCRIPTOR = "android.content.pm.IDataLoader";
    
    static final int TRANSACTION_create = 1;
    
    static final int TRANSACTION_destroy = 4;
    
    static final int TRANSACTION_prepareImage = 5;
    
    static final int TRANSACTION_start = 2;
    
    static final int TRANSACTION_stop = 3;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IDataLoader");
    }
    
    public static IDataLoader asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IDataLoader");
      return (iInterface != null && iInterface instanceof IDataLoader) ? (IDataLoader)iInterface : new Proxy(param1IBinder);
    }
    
    public static IDataLoader getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "prepareImage") : "destroy") : "stop") : "start") : "create";
    }
    
    public static boolean setDefaultImpl(IDataLoader param1IDataLoader) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IDataLoader != null) {
          Proxy.sDefaultImpl = param1IDataLoader;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      FileSystemControlParcel fileSystemControlParcel;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.content.pm.IDataLoader");
                return true;
              } 
              param1Parcel1.enforceInterface("android.content.pm.IDataLoader");
              prepareImage(param1Parcel1.readInt(), (InstallationFileParcel[])param1Parcel1.createTypedArray(InstallationFileParcel.CREATOR), param1Parcel1.createStringArray());
              return true;
            } 
            param1Parcel1.enforceInterface("android.content.pm.IDataLoader");
            destroy(param1Parcel1.readInt());
            return true;
          } 
          param1Parcel1.enforceInterface("android.content.pm.IDataLoader");
          stop(param1Parcel1.readInt());
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.pm.IDataLoader");
        start(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IDataLoader");
      param1Int1 = param1Parcel1.readInt();
      if (param1Parcel1.readInt() != 0) {
        DataLoaderParamsParcel dataLoaderParamsParcel = (DataLoaderParamsParcel)DataLoaderParamsParcel.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      if (param1Parcel1.readInt() != 0) {
        fileSystemControlParcel = (FileSystemControlParcel)FileSystemControlParcel.CREATOR.createFromParcel(param1Parcel1);
      } else {
        fileSystemControlParcel = null;
      } 
      create(param1Int1, (DataLoaderParamsParcel)param1Parcel2, fileSystemControlParcel, IDataLoaderStatusListener.Stub.asInterface(param1Parcel1.readStrongBinder()));
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
  
  private static class Proxy implements IDataLoader {
    public static IDataLoader sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void create(int param1Int, DataLoaderParamsParcel param1DataLoaderParamsParcel, FileSystemControlParcel param1FileSystemControlParcel, IDataLoaderStatusListener param1IDataLoaderStatusListener) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param1Int);
        if (param1DataLoaderParamsParcel != null) {
          parcel.writeInt(1);
          param1DataLoaderParamsParcel.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1FileSystemControlParcel != null) {
          parcel.writeInt(1);
          param1FileSystemControlParcel.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1IDataLoaderStatusListener != null) {
          iBinder = param1IDataLoaderStatusListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().create(param1Int, param1DataLoaderParamsParcel, param1FileSystemControlParcel, param1IDataLoaderStatusListener);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void destroy(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().destroy(param1Int);
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
    
    public void prepareImage(int param1Int, InstallationFileParcel[] param1ArrayOfInstallationFileParcel, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param1Int);
        parcel.writeTypedArray((Parcelable[])param1ArrayOfInstallationFileParcel, 0);
        parcel.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(5, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().prepareImage(param1Int, param1ArrayOfInstallationFileParcel, param1ArrayOfString);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void start(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().start(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void stop(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoader");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IDataLoader.Stub.getDefaultImpl() != null) {
          IDataLoader.Stub.getDefaultImpl().stop(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */