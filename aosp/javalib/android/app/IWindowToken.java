package android.app;

import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IWindowToken extends IInterface {
  void onConfigurationChanged(Configuration paramConfiguration, int paramInt) throws RemoteException;
  
  void onWindowTokenRemoved() throws RemoteException;
  
  public static class Default implements IWindowToken {
    public IBinder asBinder() {
      return null;
    }
    
    public void onConfigurationChanged(Configuration param1Configuration, int param1Int) throws RemoteException {}
    
    public void onWindowTokenRemoved() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IWindowToken {
    private static final String DESCRIPTOR = "android.app.IWindowToken";
    
    static final int TRANSACTION_onConfigurationChanged = 1;
    
    static final int TRANSACTION_onWindowTokenRemoved = 2;
    
    public Stub() {
      attachInterface(this, "android.app.IWindowToken");
    }
    
    public static IWindowToken asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IWindowToken");
      return (iInterface != null && iInterface instanceof IWindowToken) ? (IWindowToken)iInterface : new Proxy(param1IBinder);
    }
    
    public static IWindowToken getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onWindowTokenRemoved") : "onConfigurationChanged";
    }
    
    public static boolean setDefaultImpl(IWindowToken param1IWindowToken) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IWindowToken != null) {
          Proxy.sDefaultImpl = param1IWindowToken;
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
          param1Parcel2.writeString("android.app.IWindowToken");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IWindowToken");
        onWindowTokenRemoved();
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IWindowToken");
      if (param1Parcel1.readInt() != 0) {
        Configuration configuration = (Configuration)Configuration.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      onConfigurationChanged((Configuration)param1Parcel2, param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IWindowToken {
      public static IWindowToken sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IWindowToken";
      }
      
      public void onConfigurationChanged(Configuration param2Configuration, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IWindowToken");
          if (param2Configuration != null) {
            parcel.writeInt(1);
            param2Configuration.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && IWindowToken.Stub.getDefaultImpl() != null) {
            IWindowToken.Stub.getDefaultImpl().onConfigurationChanged(param2Configuration, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onWindowTokenRemoved() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IWindowToken");
          if (!this.mRemote.transact(2, parcel, null, 1) && IWindowToken.Stub.getDefaultImpl() != null) {
            IWindowToken.Stub.getDefaultImpl().onWindowTokenRemoved();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IWindowToken {
    public static IWindowToken sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IWindowToken";
    }
    
    public void onConfigurationChanged(Configuration param1Configuration, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IWindowToken");
        if (param1Configuration != null) {
          parcel.writeInt(1);
          param1Configuration.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IWindowToken.Stub.getDefaultImpl() != null) {
          IWindowToken.Stub.getDefaultImpl().onConfigurationChanged(param1Configuration, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onWindowTokenRemoved() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IWindowToken");
        if (!this.mRemote.transact(2, parcel, null, 1) && IWindowToken.Stub.getDefaultImpl() != null) {
          IWindowToken.Stub.getDefaultImpl().onWindowTokenRemoved();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWindowToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */