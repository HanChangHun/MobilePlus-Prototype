package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDataLoaderManager extends IInterface {
  boolean bindToDataLoader(int paramInt, DataLoaderParamsParcel paramDataLoaderParamsParcel, IDataLoaderStatusListener paramIDataLoaderStatusListener) throws RemoteException;
  
  IDataLoader getDataLoader(int paramInt) throws RemoteException;
  
  void unbindFromDataLoader(int paramInt) throws RemoteException;
  
  public static class Default implements IDataLoaderManager {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean bindToDataLoader(int param1Int, DataLoaderParamsParcel param1DataLoaderParamsParcel, IDataLoaderStatusListener param1IDataLoaderStatusListener) throws RemoteException {
      return false;
    }
    
    public IDataLoader getDataLoader(int param1Int) throws RemoteException {
      return null;
    }
    
    public void unbindFromDataLoader(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IDataLoaderManager {
    private static final String DESCRIPTOR = "android.content.pm.IDataLoaderManager";
    
    static final int TRANSACTION_bindToDataLoader = 1;
    
    static final int TRANSACTION_getDataLoader = 2;
    
    static final int TRANSACTION_unbindFromDataLoader = 3;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IDataLoaderManager");
    }
    
    public static IDataLoaderManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IDataLoaderManager");
      return (iInterface != null && iInterface instanceof IDataLoaderManager) ? (IDataLoaderManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IDataLoaderManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "unbindFromDataLoader") : "getDataLoader") : "bindToDataLoader";
    }
    
    public static boolean setDefaultImpl(IDataLoaderManager param1IDataLoaderManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IDataLoaderManager != null) {
          Proxy.sDefaultImpl = param1IDataLoaderManager;
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
      IDataLoader iDataLoader;
      DataLoaderParamsParcel dataLoaderParamsParcel;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.content.pm.IDataLoaderManager");
            return true;
          } 
          param1Parcel1.enforceInterface("android.content.pm.IDataLoaderManager");
          unbindFromDataLoader(param1Parcel1.readInt());
          param1Parcel2.writeNoException();
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.pm.IDataLoaderManager");
        iDataLoader = getDataLoader(param1Parcel1.readInt());
        param1Parcel2.writeNoException();
        if (iDataLoader != null) {
          IBinder iBinder = iDataLoader.asBinder();
        } else {
          iDataLoader = null;
        } 
        param1Parcel2.writeStrongBinder((IBinder)iDataLoader);
        return true;
      } 
      iDataLoader.enforceInterface("android.content.pm.IDataLoaderManager");
      param1Int1 = iDataLoader.readInt();
      if (iDataLoader.readInt() != 0) {
        dataLoaderParamsParcel = (DataLoaderParamsParcel)DataLoaderParamsParcel.CREATOR.createFromParcel((Parcel)iDataLoader);
      } else {
        dataLoaderParamsParcel = null;
      } 
      boolean bool = bindToDataLoader(param1Int1, dataLoaderParamsParcel, IDataLoaderStatusListener.Stub.asInterface(iDataLoader.readStrongBinder()));
      param1Parcel2.writeNoException();
      param1Parcel2.writeInt(bool);
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
  
  private static class Proxy implements IDataLoaderManager {
    public static IDataLoaderManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean bindToDataLoader(int param1Int, DataLoaderParamsParcel param1DataLoaderParamsParcel, IDataLoaderStatusListener param1IDataLoaderStatusListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
        parcel1.writeInt(param1Int);
        boolean bool = true;
        if (param1DataLoaderParamsParcel != null) {
          parcel1.writeInt(1);
          param1DataLoaderParamsParcel.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IDataLoaderStatusListener != null) {
          iBinder = param1IDataLoaderStatusListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null) {
          bool = IDataLoaderManager.Stub.getDefaultImpl().bindToDataLoader(param1Int, param1DataLoaderParamsParcel, param1IDataLoaderStatusListener);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IDataLoader getDataLoader(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null)
          return IDataLoaderManager.Stub.getDefaultImpl().getDataLoader(param1Int); 
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
    
    public void unbindFromDataLoader(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.IDataLoaderManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IDataLoaderManager.Stub.getDefaultImpl() != null) {
          IDataLoaderManager.Stub.getDefaultImpl().unbindFromDataLoader(param1Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoaderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */