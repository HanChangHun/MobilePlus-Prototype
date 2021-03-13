package android.app.blob;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBlobCommitCallback extends IInterface {
  void onResult(int paramInt) throws RemoteException;
  
  public static class Default implements IBlobCommitCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onResult(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBlobCommitCallback {
    private static final String DESCRIPTOR = "android.app.blob.IBlobCommitCallback";
    
    static final int TRANSACTION_onResult = 1;
    
    public Stub() {
      attachInterface(this, "android.app.blob.IBlobCommitCallback");
    }
    
    public static IBlobCommitCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.blob.IBlobCommitCallback");
      return (iInterface != null && iInterface instanceof IBlobCommitCallback) ? (IBlobCommitCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBlobCommitCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onResult";
    }
    
    public static boolean setDefaultImpl(IBlobCommitCallback param1IBlobCommitCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBlobCommitCallback != null) {
          Proxy.sDefaultImpl = param1IBlobCommitCallback;
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
        param1Parcel2.writeString("android.app.blob.IBlobCommitCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.blob.IBlobCommitCallback");
      onResult(param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IBlobCommitCallback {
      public static IBlobCommitCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.blob.IBlobCommitCallback";
      }
      
      public void onResult(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.blob.IBlobCommitCallback");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBlobCommitCallback.Stub.getDefaultImpl() != null) {
            IBlobCommitCallback.Stub.getDefaultImpl().onResult(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IBlobCommitCallback {
    public static IBlobCommitCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.blob.IBlobCommitCallback";
    }
    
    public void onResult(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.blob.IBlobCommitCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBlobCommitCallback.Stub.getDefaultImpl() != null) {
          IBlobCommitCallback.Stub.getDefaultImpl().onResult(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/IBlobCommitCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */