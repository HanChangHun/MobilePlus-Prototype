package android.app;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IServiceConnection extends IInterface {
  void connected(ComponentName paramComponentName, IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  public static class Default implements IServiceConnection {
    public IBinder asBinder() {
      return null;
    }
    
    public void connected(ComponentName param1ComponentName, IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IServiceConnection {
    private static final String DESCRIPTOR = "android.app.IServiceConnection";
    
    static final int TRANSACTION_connected = 1;
    
    public Stub() {
      attachInterface(this, "android.app.IServiceConnection");
    }
    
    public static IServiceConnection asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IServiceConnection");
      return (iInterface != null && iInterface instanceof IServiceConnection) ? (IServiceConnection)iInterface : new Proxy(param1IBinder);
    }
    
    public static IServiceConnection getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "connected";
    }
    
    public static boolean setDefaultImpl(IServiceConnection param1IServiceConnection) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IServiceConnection != null) {
          Proxy.sDefaultImpl = param1IServiceConnection;
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
        param1Parcel2.writeString("android.app.IServiceConnection");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IServiceConnection");
      if (param1Parcel1.readInt() != 0) {
        ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      IBinder iBinder = param1Parcel1.readStrongBinder();
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      connected((ComponentName)param1Parcel2, iBinder, bool);
      return true;
    }
    
    private static class Proxy implements IServiceConnection {
      public static IServiceConnection sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void connected(ComponentName param2ComponentName, IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IServiceConnection");
          boolean bool = false;
          if (param2ComponentName != null) {
            parcel.writeInt(1);
            param2ComponentName.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStrongBinder(param2IBinder);
          if (param2Boolean)
            bool = true; 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(1, parcel, null, 1) && IServiceConnection.Stub.getDefaultImpl() != null) {
            IServiceConnection.Stub.getDefaultImpl().connected(param2ComponentName, param2IBinder, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IServiceConnection";
      }
    }
  }
  
  private static class Proxy implements IServiceConnection {
    public static IServiceConnection sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void connected(ComponentName param1ComponentName, IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IServiceConnection");
        boolean bool = false;
        if (param1ComponentName != null) {
          parcel.writeInt(1);
          param1ComponentName.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStrongBinder(param1IBinder);
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IServiceConnection.Stub.getDefaultImpl() != null) {
          IServiceConnection.Stub.getDefaultImpl().connected(param1ComponentName, param1IBinder, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IServiceConnection";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IServiceConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */