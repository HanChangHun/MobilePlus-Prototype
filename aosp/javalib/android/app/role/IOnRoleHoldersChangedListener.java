package android.app.role;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOnRoleHoldersChangedListener extends IInterface {
  void onRoleHoldersChanged(String paramString, int paramInt) throws RemoteException;
  
  public static class Default implements IOnRoleHoldersChangedListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onRoleHoldersChanged(String param1String, int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IOnRoleHoldersChangedListener {
    private static final String DESCRIPTOR = "android.app.role.IOnRoleHoldersChangedListener";
    
    static final int TRANSACTION_onRoleHoldersChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.app.role.IOnRoleHoldersChangedListener");
    }
    
    public static IOnRoleHoldersChangedListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.role.IOnRoleHoldersChangedListener");
      return (iInterface != null && iInterface instanceof IOnRoleHoldersChangedListener) ? (IOnRoleHoldersChangedListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IOnRoleHoldersChangedListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onRoleHoldersChanged";
    }
    
    public static boolean setDefaultImpl(IOnRoleHoldersChangedListener param1IOnRoleHoldersChangedListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IOnRoleHoldersChangedListener != null) {
          Proxy.sDefaultImpl = param1IOnRoleHoldersChangedListener;
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
        param1Parcel2.writeString("android.app.role.IOnRoleHoldersChangedListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.role.IOnRoleHoldersChangedListener");
      onRoleHoldersChanged(param1Parcel1.readString(), param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IOnRoleHoldersChangedListener {
      public static IOnRoleHoldersChangedListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.role.IOnRoleHoldersChangedListener";
      }
      
      public void onRoleHoldersChanged(String param2String, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.role.IOnRoleHoldersChangedListener");
          parcel.writeString(param2String);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && IOnRoleHoldersChangedListener.Stub.getDefaultImpl() != null) {
            IOnRoleHoldersChangedListener.Stub.getDefaultImpl().onRoleHoldersChanged(param2String, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IOnRoleHoldersChangedListener {
    public static IOnRoleHoldersChangedListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.role.IOnRoleHoldersChangedListener";
    }
    
    public void onRoleHoldersChanged(String param1String, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.role.IOnRoleHoldersChangedListener");
        parcel.writeString(param1String);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IOnRoleHoldersChangedListener.Stub.getDefaultImpl() != null) {
          IOnRoleHoldersChangedListener.Stub.getDefaultImpl().onRoleHoldersChanged(param1String, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IOnRoleHoldersChangedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */