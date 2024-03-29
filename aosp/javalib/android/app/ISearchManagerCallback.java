package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISearchManagerCallback extends IInterface {
  void onCancel() throws RemoteException;
  
  void onDismiss() throws RemoteException;
  
  public static class Default implements ISearchManagerCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onCancel() throws RemoteException {}
    
    public void onDismiss() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISearchManagerCallback {
    private static final String DESCRIPTOR = "android.app.ISearchManagerCallback";
    
    static final int TRANSACTION_onCancel = 2;
    
    static final int TRANSACTION_onDismiss = 1;
    
    public Stub() {
      attachInterface(this, "android.app.ISearchManagerCallback");
    }
    
    public static ISearchManagerCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.ISearchManagerCallback");
      return (iInterface != null && iInterface instanceof ISearchManagerCallback) ? (ISearchManagerCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISearchManagerCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onCancel") : "onDismiss";
    }
    
    public static boolean setDefaultImpl(ISearchManagerCallback param1ISearchManagerCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISearchManagerCallback != null) {
          Proxy.sDefaultImpl = param1ISearchManagerCallback;
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
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.ISearchManagerCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.ISearchManagerCallback");
        onCancel();
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.ISearchManagerCallback");
      onDismiss();
      return true;
    }
    
    private static class Proxy implements ISearchManagerCallback {
      public static ISearchManagerCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.ISearchManagerCallback";
      }
      
      public void onCancel() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
          if (!this.mRemote.transact(2, parcel, null, 1) && ISearchManagerCallback.Stub.getDefaultImpl() != null) {
            ISearchManagerCallback.Stub.getDefaultImpl().onCancel();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onDismiss() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
          if (!this.mRemote.transact(1, parcel, null, 1) && ISearchManagerCallback.Stub.getDefaultImpl() != null) {
            ISearchManagerCallback.Stub.getDefaultImpl().onDismiss();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISearchManagerCallback {
    public static ISearchManagerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.ISearchManagerCallback";
    }
    
    public void onCancel() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && ISearchManagerCallback.Stub.getDefaultImpl() != null) {
          ISearchManagerCallback.Stub.getDefaultImpl().onCancel();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDismiss() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && ISearchManagerCallback.Stub.getDefaultImpl() != null) {
          ISearchManagerCallback.Stub.getDefaultImpl().onDismiss();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ISearchManagerCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */