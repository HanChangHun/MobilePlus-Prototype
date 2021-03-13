package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISyncAdapterUnsyncableAccountCallback extends IInterface {
  void onUnsyncableAccountDone(boolean paramBoolean) throws RemoteException;
  
  public static class Default implements ISyncAdapterUnsyncableAccountCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onUnsyncableAccountDone(boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISyncAdapterUnsyncableAccountCallback {
    private static final String DESCRIPTOR = "android.content.ISyncAdapterUnsyncableAccountCallback";
    
    static final int TRANSACTION_onUnsyncableAccountDone = 1;
    
    public Stub() {
      attachInterface(this, "android.content.ISyncAdapterUnsyncableAccountCallback");
    }
    
    public static ISyncAdapterUnsyncableAccountCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.ISyncAdapterUnsyncableAccountCallback");
      return (iInterface != null && iInterface instanceof ISyncAdapterUnsyncableAccountCallback) ? (ISyncAdapterUnsyncableAccountCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISyncAdapterUnsyncableAccountCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onUnsyncableAccountDone";
    }
    
    public static boolean setDefaultImpl(ISyncAdapterUnsyncableAccountCallback param1ISyncAdapterUnsyncableAccountCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISyncAdapterUnsyncableAccountCallback != null) {
          Proxy.sDefaultImpl = param1ISyncAdapterUnsyncableAccountCallback;
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
      boolean bool;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.content.ISyncAdapterUnsyncableAccountCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.ISyncAdapterUnsyncableAccountCallback");
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onUnsyncableAccountDone(bool);
      return true;
    }
    
    private static class Proxy implements ISyncAdapterUnsyncableAccountCallback {
      public static ISyncAdapterUnsyncableAccountCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.ISyncAdapterUnsyncableAccountCallback";
      }
      
      public void onUnsyncableAccountDone(boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.content.ISyncAdapterUnsyncableAccountCallback");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(1, parcel, null, 1) && ISyncAdapterUnsyncableAccountCallback.Stub.getDefaultImpl() != null) {
            ISyncAdapterUnsyncableAccountCallback.Stub.getDefaultImpl().onUnsyncableAccountDone(param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISyncAdapterUnsyncableAccountCallback {
    public static ISyncAdapterUnsyncableAccountCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.ISyncAdapterUnsyncableAccountCallback";
    }
    
    public void onUnsyncableAccountDone(boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.content.ISyncAdapterUnsyncableAccountCallback");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISyncAdapterUnsyncableAccountCallback.Stub.getDefaultImpl() != null) {
          ISyncAdapterUnsyncableAccountCallback.Stub.getDefaultImpl().onUnsyncableAccountDone(param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncAdapterUnsyncableAccountCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */