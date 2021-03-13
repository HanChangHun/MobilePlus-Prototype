package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITransientNotification extends IInterface {
  void hide() throws RemoteException;
  
  void show(IBinder paramIBinder) throws RemoteException;
  
  public static class Default implements ITransientNotification {
    public IBinder asBinder() {
      return null;
    }
    
    public void hide() throws RemoteException {}
    
    public void show(IBinder param1IBinder) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ITransientNotification {
    private static final String DESCRIPTOR = "android.app.ITransientNotification";
    
    static final int TRANSACTION_hide = 2;
    
    static final int TRANSACTION_show = 1;
    
    public Stub() {
      attachInterface(this, "android.app.ITransientNotification");
    }
    
    public static ITransientNotification asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.ITransientNotification");
      return (iInterface != null && iInterface instanceof ITransientNotification) ? (ITransientNotification)iInterface : new Proxy(param1IBinder);
    }
    
    public static ITransientNotification getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "hide") : "show";
    }
    
    public static boolean setDefaultImpl(ITransientNotification param1ITransientNotification) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ITransientNotification != null) {
          Proxy.sDefaultImpl = param1ITransientNotification;
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
          param1Parcel2.writeString("android.app.ITransientNotification");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.ITransientNotification");
        hide();
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.ITransientNotification");
      show(param1Parcel1.readStrongBinder());
      return true;
    }
    
    private static class Proxy implements ITransientNotification {
      public static ITransientNotification sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.ITransientNotification";
      }
      
      public void hide() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.ITransientNotification");
          if (!this.mRemote.transact(2, parcel, null, 1) && ITransientNotification.Stub.getDefaultImpl() != null) {
            ITransientNotification.Stub.getDefaultImpl().hide();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void show(IBinder param2IBinder) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.ITransientNotification");
          parcel.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(1, parcel, null, 1) && ITransientNotification.Stub.getDefaultImpl() != null) {
            ITransientNotification.Stub.getDefaultImpl().show(param2IBinder);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ITransientNotification {
    public static ITransientNotification sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.ITransientNotification";
    }
    
    public void hide() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITransientNotification");
        if (!this.mRemote.transact(2, parcel, null, 1) && ITransientNotification.Stub.getDefaultImpl() != null) {
          ITransientNotification.Stub.getDefaultImpl().hide();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void show(IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITransientNotification");
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && ITransientNotification.Stub.getDefaultImpl() != null) {
          ITransientNotification.Stub.getDefaultImpl().show(param1IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITransientNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */