package android.hardware.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IVirtualDisplayCallback extends IInterface {
  void onPaused() throws RemoteException;
  
  void onResumed() throws RemoteException;
  
  void onStopped() throws RemoteException;
  
  public static class Default implements IVirtualDisplayCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onPaused() throws RemoteException {}
    
    public void onResumed() throws RemoteException {}
    
    public void onStopped() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IVirtualDisplayCallback {
    private static final String DESCRIPTOR = "android.hardware.display.IVirtualDisplayCallback";
    
    static final int TRANSACTION_onPaused = 1;
    
    static final int TRANSACTION_onResumed = 2;
    
    static final int TRANSACTION_onStopped = 3;
    
    public Stub() {
      attachInterface(this, "android.hardware.display.IVirtualDisplayCallback");
    }
    
    public static IVirtualDisplayCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.display.IVirtualDisplayCallback");
      return (iInterface != null && iInterface instanceof IVirtualDisplayCallback) ? (IVirtualDisplayCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IVirtualDisplayCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "onStopped") : "onResumed") : "onPaused";
    }
    
    public static boolean setDefaultImpl(IVirtualDisplayCallback param1IVirtualDisplayCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IVirtualDisplayCallback != null) {
          Proxy.sDefaultImpl = param1IVirtualDisplayCallback;
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
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.hardware.display.IVirtualDisplayCallback");
            return true;
          } 
          param1Parcel1.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
          onStopped();
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
        onResumed();
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.display.IVirtualDisplayCallback");
      onPaused();
      return true;
    }
    
    private static class Proxy implements IVirtualDisplayCallback {
      public static IVirtualDisplayCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.display.IVirtualDisplayCallback";
      }
      
      public void onPaused() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
          if (!this.mRemote.transact(1, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
            IVirtualDisplayCallback.Stub.getDefaultImpl().onPaused();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onResumed() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
          if (!this.mRemote.transact(2, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
            IVirtualDisplayCallback.Stub.getDefaultImpl().onResumed();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onStopped() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
          if (!this.mRemote.transact(3, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
            IVirtualDisplayCallback.Stub.getDefaultImpl().onStopped();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IVirtualDisplayCallback {
    public static IVirtualDisplayCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.display.IVirtualDisplayCallback";
    }
    
    public void onPaused() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
          IVirtualDisplayCallback.Stub.getDefaultImpl().onPaused();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onResumed() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
          IVirtualDisplayCallback.Stub.getDefaultImpl().onResumed();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onStopped() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.display.IVirtualDisplayCallback");
        if (!this.mRemote.transact(3, parcel, null, 1) && IVirtualDisplayCallback.Stub.getDefaultImpl() != null) {
          IVirtualDisplayCallback.Stub.getDefaultImpl().onStopped();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IVirtualDisplayCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */