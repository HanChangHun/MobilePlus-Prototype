package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDisplayManagerCallback extends IInterface {
  void onDisplayEvent(int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IDisplayManagerCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onDisplayEvent(int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IDisplayManagerCallback {
    private static final String DESCRIPTOR = "android.hardware.display.IDisplayManagerCallback";
    
    static final int TRANSACTION_onDisplayEvent = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.display.IDisplayManagerCallback");
    }
    
    public static IDisplayManagerCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.display.IDisplayManagerCallback");
      return (iInterface != null && iInterface instanceof IDisplayManagerCallback) ? (IDisplayManagerCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IDisplayManagerCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onDisplayEvent";
    }
    
    public static boolean setDefaultImpl(IDisplayManagerCallback param1IDisplayManagerCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IDisplayManagerCallback != null) {
          Proxy.sDefaultImpl = param1IDisplayManagerCallback;
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
        param1Parcel2.writeString("android.hardware.display.IDisplayManagerCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.display.IDisplayManagerCallback");
      onDisplayEvent(param1Parcel1.readInt(), param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IDisplayManagerCallback {
      public static IDisplayManagerCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.display.IDisplayManagerCallback";
      }
      
      public void onDisplayEvent(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.display.IDisplayManagerCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(1, parcel, null, 1) && IDisplayManagerCallback.Stub.getDefaultImpl() != null) {
            IDisplayManagerCallback.Stub.getDefaultImpl().onDisplayEvent(param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IDisplayManagerCallback {
    public static IDisplayManagerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.display.IDisplayManagerCallback";
    }
    
    public void onDisplayEvent(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.display.IDisplayManagerCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDisplayManagerCallback.Stub.getDefaultImpl() != null) {
          IDisplayManagerCallback.Stub.getDefaultImpl().onDisplayEvent(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IDisplayManagerCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */