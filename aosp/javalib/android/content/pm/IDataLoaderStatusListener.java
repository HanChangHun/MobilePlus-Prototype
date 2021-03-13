package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDataLoaderStatusListener extends IInterface {
  public static final int DATA_LOADER_BOUND = 1;
  
  public static final int DATA_LOADER_CREATED = 2;
  
  public static final int DATA_LOADER_DESTROYED = 0;
  
  public static final int DATA_LOADER_IMAGE_NOT_READY = 6;
  
  public static final int DATA_LOADER_IMAGE_READY = 5;
  
  public static final int DATA_LOADER_STARTED = 3;
  
  public static final int DATA_LOADER_STOPPED = 4;
  
  public static final int DATA_LOADER_UNAVAILABLE = 7;
  
  public static final int DATA_LOADER_UNRECOVERABLE = 8;
  
  void onStatusChanged(int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IDataLoaderStatusListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onStatusChanged(int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IDataLoaderStatusListener {
    private static final String DESCRIPTOR = "android.content.pm.IDataLoaderStatusListener";
    
    static final int TRANSACTION_onStatusChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.IDataLoaderStatusListener");
    }
    
    public static IDataLoaderStatusListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.IDataLoaderStatusListener");
      return (iInterface != null && iInterface instanceof IDataLoaderStatusListener) ? (IDataLoaderStatusListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IDataLoaderStatusListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onStatusChanged";
    }
    
    public static boolean setDefaultImpl(IDataLoaderStatusListener param1IDataLoaderStatusListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IDataLoaderStatusListener != null) {
          Proxy.sDefaultImpl = param1IDataLoaderStatusListener;
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
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.content.pm.IDataLoaderStatusListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.IDataLoaderStatusListener");
      onStatusChanged(param1Parcel1.readInt(), param1Parcel1.readInt());
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
  
  private static class Proxy implements IDataLoaderStatusListener {
    public static IDataLoaderStatusListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.IDataLoaderStatusListener";
    }
    
    public void onStatusChanged(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.IDataLoaderStatusListener");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDataLoaderStatusListener.Stub.getDefaultImpl() != null) {
          IDataLoaderStatusListener.Stub.getDefaultImpl().onStatusChanged(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoaderStatusListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */