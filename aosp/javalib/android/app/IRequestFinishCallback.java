package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRequestFinishCallback extends IInterface {
  void requestFinish() throws RemoteException;
  
  public static class Default implements IRequestFinishCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void requestFinish() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IRequestFinishCallback {
    private static final String DESCRIPTOR = "android.app.IRequestFinishCallback";
    
    static final int TRANSACTION_requestFinish = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IRequestFinishCallback");
    }
    
    public static IRequestFinishCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IRequestFinishCallback");
      return (iInterface != null && iInterface instanceof IRequestFinishCallback) ? (IRequestFinishCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IRequestFinishCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "requestFinish";
    }
    
    public static boolean setDefaultImpl(IRequestFinishCallback param1IRequestFinishCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IRequestFinishCallback != null) {
          Proxy.sDefaultImpl = param1IRequestFinishCallback;
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
        param1Parcel2.writeString("android.app.IRequestFinishCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IRequestFinishCallback");
      requestFinish();
      return true;
    }
    
    private static class Proxy implements IRequestFinishCallback {
      public static IRequestFinishCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IRequestFinishCallback";
      }
      
      public void requestFinish() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IRequestFinishCallback");
          if (!this.mRemote.transact(1, parcel, null, 1) && IRequestFinishCallback.Stub.getDefaultImpl() != null) {
            IRequestFinishCallback.Stub.getDefaultImpl().requestFinish();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IRequestFinishCallback {
    public static IRequestFinishCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IRequestFinishCallback";
    }
    
    public void requestFinish() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IRequestFinishCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && IRequestFinishCallback.Stub.getDefaultImpl() != null) {
          IRequestFinishCallback.Stub.getDefaultImpl().requestFinish();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IRequestFinishCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */