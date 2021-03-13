package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISensorPrivacyListener extends IInterface {
  void onSensorPrivacyChanged(boolean paramBoolean) throws RemoteException;
  
  public static class Default implements ISensorPrivacyListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onSensorPrivacyChanged(boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISensorPrivacyListener {
    private static final String DESCRIPTOR = "android.hardware.ISensorPrivacyListener";
    
    static final int TRANSACTION_onSensorPrivacyChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.ISensorPrivacyListener");
    }
    
    public static ISensorPrivacyListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.ISensorPrivacyListener");
      return (iInterface != null && iInterface instanceof ISensorPrivacyListener) ? (ISensorPrivacyListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISensorPrivacyListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onSensorPrivacyChanged";
    }
    
    public static boolean setDefaultImpl(ISensorPrivacyListener param1ISensorPrivacyListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISensorPrivacyListener != null) {
          Proxy.sDefaultImpl = param1ISensorPrivacyListener;
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
        param1Parcel2.writeString("android.hardware.ISensorPrivacyListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.ISensorPrivacyListener");
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onSensorPrivacyChanged(bool);
      return true;
    }
    
    private static class Proxy implements ISensorPrivacyListener {
      public static ISensorPrivacyListener sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.ISensorPrivacyListener";
      }
      
      public void onSensorPrivacyChanged(boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.hardware.ISensorPrivacyListener");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(1, parcel, null, 1) && ISensorPrivacyListener.Stub.getDefaultImpl() != null) {
            ISensorPrivacyListener.Stub.getDefaultImpl().onSensorPrivacyChanged(param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISensorPrivacyListener {
    public static ISensorPrivacyListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ISensorPrivacyListener";
    }
    
    public void onSensorPrivacyChanged(boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.ISensorPrivacyListener");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISensorPrivacyListener.Stub.getDefaultImpl() != null) {
          ISensorPrivacyListener.Stub.getDefaultImpl().onSensorPrivacyChanged(param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISensorPrivacyListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */