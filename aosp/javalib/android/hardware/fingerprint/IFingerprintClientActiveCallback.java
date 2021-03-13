package android.hardware.fingerprint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IFingerprintClientActiveCallback extends IInterface {
  void onClientActiveChanged(boolean paramBoolean) throws RemoteException;
  
  public static class Default implements IFingerprintClientActiveCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onClientActiveChanged(boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IFingerprintClientActiveCallback {
    private static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintClientActiveCallback";
    
    static final int TRANSACTION_onClientActiveChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.fingerprint.IFingerprintClientActiveCallback");
    }
    
    public static IFingerprintClientActiveCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintClientActiveCallback");
      return (iInterface != null && iInterface instanceof IFingerprintClientActiveCallback) ? (IFingerprintClientActiveCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IFingerprintClientActiveCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onClientActiveChanged";
    }
    
    public static boolean setDefaultImpl(IFingerprintClientActiveCallback param1IFingerprintClientActiveCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IFingerprintClientActiveCallback != null) {
          Proxy.sDefaultImpl = param1IFingerprintClientActiveCallback;
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
        param1Parcel2.writeString("android.hardware.fingerprint.IFingerprintClientActiveCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintClientActiveCallback");
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onClientActiveChanged(bool);
      return true;
    }
    
    private static class Proxy implements IFingerprintClientActiveCallback {
      public static IFingerprintClientActiveCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.fingerprint.IFingerprintClientActiveCallback";
      }
      
      public void onClientActiveChanged(boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintClientActiveCallback");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(1, parcel, null, 1) && IFingerprintClientActiveCallback.Stub.getDefaultImpl() != null) {
            IFingerprintClientActiveCallback.Stub.getDefaultImpl().onClientActiveChanged(param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IFingerprintClientActiveCallback {
    public static IFingerprintClientActiveCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.fingerprint.IFingerprintClientActiveCallback";
    }
    
    public void onClientActiveChanged(boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintClientActiveCallback");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IFingerprintClientActiveCallback.Stub.getDefaultImpl() != null) {
          IFingerprintClientActiveCallback.Stub.getDefaultImpl().onClientActiveChanged(param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintClientActiveCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */