package android.gsi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IProgressCallback extends IInterface {
  void onProgress(long paramLong1, long paramLong2) throws RemoteException;
  
  public static class Default implements IProgressCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onProgress(long param1Long1, long param1Long2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IProgressCallback {
    private static final String DESCRIPTOR = "android.gsi.IProgressCallback";
    
    static final int TRANSACTION_onProgress = 1;
    
    public Stub() {
      attachInterface(this, "android.gsi.IProgressCallback");
    }
    
    public static IProgressCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.gsi.IProgressCallback");
      return (iInterface != null && iInterface instanceof IProgressCallback) ? (IProgressCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IProgressCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onProgress";
    }
    
    public static boolean setDefaultImpl(IProgressCallback param1IProgressCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IProgressCallback != null) {
          Proxy.sDefaultImpl = param1IProgressCallback;
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
        param1Parcel2.writeString("android.gsi.IProgressCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.gsi.IProgressCallback");
      onProgress(param1Parcel1.readLong(), param1Parcel1.readLong());
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements IProgressCallback {
      public static IProgressCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.gsi.IProgressCallback";
      }
      
      public void onProgress(long param2Long1, long param2Long2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.gsi.IProgressCallback");
          parcel1.writeLong(param2Long1);
          parcel1.writeLong(param2Long2);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IProgressCallback.Stub.getDefaultImpl() != null) {
            IProgressCallback.Stub.getDefaultImpl().onProgress(param2Long1, param2Long2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IProgressCallback {
    public static IProgressCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.gsi.IProgressCallback";
    }
    
    public void onProgress(long param1Long1, long param1Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.gsi.IProgressCallback");
        parcel1.writeLong(param1Long1);
        parcel1.writeLong(param1Long2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IProgressCallback.Stub.getDefaultImpl() != null) {
          IProgressCallback.Stub.getDefaultImpl().onProgress(param1Long1, param1Long2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IProgressCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */