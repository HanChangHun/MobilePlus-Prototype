package android.app.timezone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICallback extends IInterface {
  void onFinished(int paramInt) throws RemoteException;
  
  public static class Default implements ICallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onFinished(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICallback {
    private static final String DESCRIPTOR = "android.app.timezone.ICallback";
    
    static final int TRANSACTION_onFinished = 1;
    
    public Stub() {
      attachInterface(this, "android.app.timezone.ICallback");
    }
    
    public static ICallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.timezone.ICallback");
      return (iInterface != null && iInterface instanceof ICallback) ? (ICallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onFinished";
    }
    
    public static boolean setDefaultImpl(ICallback param1ICallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICallback != null) {
          Proxy.sDefaultImpl = param1ICallback;
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
        param1Parcel2.writeString("android.app.timezone.ICallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.timezone.ICallback");
      onFinished(param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements ICallback {
      public static ICallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.timezone.ICallback";
      }
      
      public void onFinished(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.timezone.ICallback");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && ICallback.Stub.getDefaultImpl() != null) {
            ICallback.Stub.getDefaultImpl().onFinished(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ICallback {
    public static ICallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.timezone.ICallback";
    }
    
    public void onFinished(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.timezone.ICallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && ICallback.Stub.getDefaultImpl() != null) {
          ICallback.Stub.getDefaultImpl().onFinished(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/ICallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */