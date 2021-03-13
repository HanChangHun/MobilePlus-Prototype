package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITransientNotificationCallback extends IInterface {
  void onToastHidden() throws RemoteException;
  
  void onToastShown() throws RemoteException;
  
  public static class Default implements ITransientNotificationCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onToastHidden() throws RemoteException {}
    
    public void onToastShown() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ITransientNotificationCallback {
    private static final String DESCRIPTOR = "android.app.ITransientNotificationCallback";
    
    static final int TRANSACTION_onToastHidden = 2;
    
    static final int TRANSACTION_onToastShown = 1;
    
    public Stub() {
      attachInterface(this, "android.app.ITransientNotificationCallback");
    }
    
    public static ITransientNotificationCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.ITransientNotificationCallback");
      return (iInterface != null && iInterface instanceof ITransientNotificationCallback) ? (ITransientNotificationCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static ITransientNotificationCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onToastHidden") : "onToastShown";
    }
    
    public static boolean setDefaultImpl(ITransientNotificationCallback param1ITransientNotificationCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ITransientNotificationCallback != null) {
          Proxy.sDefaultImpl = param1ITransientNotificationCallback;
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
          param1Parcel2.writeString("android.app.ITransientNotificationCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.ITransientNotificationCallback");
        onToastHidden();
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.ITransientNotificationCallback");
      onToastShown();
      return true;
    }
    
    private static class Proxy implements ITransientNotificationCallback {
      public static ITransientNotificationCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.ITransientNotificationCallback";
      }
      
      public void onToastHidden() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.ITransientNotificationCallback");
          if (!this.mRemote.transact(2, parcel, null, 1) && ITransientNotificationCallback.Stub.getDefaultImpl() != null) {
            ITransientNotificationCallback.Stub.getDefaultImpl().onToastHidden();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onToastShown() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.ITransientNotificationCallback");
          if (!this.mRemote.transact(1, parcel, null, 1) && ITransientNotificationCallback.Stub.getDefaultImpl() != null) {
            ITransientNotificationCallback.Stub.getDefaultImpl().onToastShown();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ITransientNotificationCallback {
    public static ITransientNotificationCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.ITransientNotificationCallback";
    }
    
    public void onToastHidden() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITransientNotificationCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && ITransientNotificationCallback.Stub.getDefaultImpl() != null) {
          ITransientNotificationCallback.Stub.getDefaultImpl().onToastHidden();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onToastShown() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITransientNotificationCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && ITransientNotificationCallback.Stub.getDefaultImpl() != null) {
          ITransientNotificationCallback.Stub.getDefaultImpl().onToastShown();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITransientNotificationCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */