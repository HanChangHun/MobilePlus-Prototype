package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface StartInstallingUpdateCallback extends IInterface {
  void onStartInstallingUpdateError(int paramInt, String paramString) throws RemoteException;
  
  public static class Default implements StartInstallingUpdateCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onStartInstallingUpdateError(int param1Int, String param1String) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements StartInstallingUpdateCallback {
    private static final String DESCRIPTOR = "android.app.admin.StartInstallingUpdateCallback";
    
    static final int TRANSACTION_onStartInstallingUpdateError = 1;
    
    public Stub() {
      attachInterface(this, "android.app.admin.StartInstallingUpdateCallback");
    }
    
    public static StartInstallingUpdateCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.admin.StartInstallingUpdateCallback");
      return (iInterface != null && iInterface instanceof StartInstallingUpdateCallback) ? (StartInstallingUpdateCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static StartInstallingUpdateCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onStartInstallingUpdateError";
    }
    
    public static boolean setDefaultImpl(StartInstallingUpdateCallback param1StartInstallingUpdateCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1StartInstallingUpdateCallback != null) {
          Proxy.sDefaultImpl = param1StartInstallingUpdateCallback;
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
        param1Parcel2.writeString("android.app.admin.StartInstallingUpdateCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.admin.StartInstallingUpdateCallback");
      onStartInstallingUpdateError(param1Parcel1.readInt(), param1Parcel1.readString());
      return true;
    }
    
    private static class Proxy implements StartInstallingUpdateCallback {
      public static StartInstallingUpdateCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.admin.StartInstallingUpdateCallback";
      }
      
      public void onStartInstallingUpdateError(int param2Int, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.admin.StartInstallingUpdateCallback");
          parcel.writeInt(param2Int);
          parcel.writeString(param2String);
          if (!this.mRemote.transact(1, parcel, null, 1) && StartInstallingUpdateCallback.Stub.getDefaultImpl() != null) {
            StartInstallingUpdateCallback.Stub.getDefaultImpl().onStartInstallingUpdateError(param2Int, param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements StartInstallingUpdateCallback {
    public static StartInstallingUpdateCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.admin.StartInstallingUpdateCallback";
    }
    
    public void onStartInstallingUpdateError(int param1Int, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.admin.StartInstallingUpdateCallback");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(1, parcel, null, 1) && StartInstallingUpdateCallback.Stub.getDefaultImpl() != null) {
          StartInstallingUpdateCallback.Stub.getDefaultImpl().onStartInstallingUpdateError(param1Int, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/StartInstallingUpdateCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */