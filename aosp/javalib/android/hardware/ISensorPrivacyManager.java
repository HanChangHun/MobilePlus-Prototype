package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISensorPrivacyManager extends IInterface {
  void addSensorPrivacyListener(ISensorPrivacyListener paramISensorPrivacyListener) throws RemoteException;
  
  boolean isSensorPrivacyEnabled() throws RemoteException;
  
  void removeSensorPrivacyListener(ISensorPrivacyListener paramISensorPrivacyListener) throws RemoteException;
  
  void setSensorPrivacy(boolean paramBoolean) throws RemoteException;
  
  public static class Default implements ISensorPrivacyManager {
    public void addSensorPrivacyListener(ISensorPrivacyListener param1ISensorPrivacyListener) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public boolean isSensorPrivacyEnabled() throws RemoteException {
      return false;
    }
    
    public void removeSensorPrivacyListener(ISensorPrivacyListener param1ISensorPrivacyListener) throws RemoteException {}
    
    public void setSensorPrivacy(boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ISensorPrivacyManager {
    private static final String DESCRIPTOR = "android.hardware.ISensorPrivacyManager";
    
    static final int TRANSACTION_addSensorPrivacyListener = 1;
    
    static final int TRANSACTION_isSensorPrivacyEnabled = 3;
    
    static final int TRANSACTION_removeSensorPrivacyListener = 2;
    
    static final int TRANSACTION_setSensorPrivacy = 4;
    
    public Stub() {
      attachInterface(this, "android.hardware.ISensorPrivacyManager");
    }
    
    public static ISensorPrivacyManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.ISensorPrivacyManager");
      return (iInterface != null && iInterface instanceof ISensorPrivacyManager) ? (ISensorPrivacyManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISensorPrivacyManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? null : "setSensorPrivacy") : "isSensorPrivacyEnabled") : "removeSensorPrivacyListener") : "addSensorPrivacyListener";
    }
    
    public static boolean setDefaultImpl(ISensorPrivacyManager param1ISensorPrivacyManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISensorPrivacyManager != null) {
          Proxy.sDefaultImpl = param1ISensorPrivacyManager;
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
            boolean bool1;
            if (param1Int1 != 4) {
              if (param1Int1 != 1598968902)
                return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
              param1Parcel2.writeString("android.hardware.ISensorPrivacyManager");
              return true;
            } 
            param1Parcel1.enforceInterface("android.hardware.ISensorPrivacyManager");
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            setSensorPrivacy(bool1);
            param1Parcel2.writeNoException();
            return true;
          } 
          param1Parcel1.enforceInterface("android.hardware.ISensorPrivacyManager");
          boolean bool = isSensorPrivacyEnabled();
          param1Parcel2.writeNoException();
          param1Parcel2.writeInt(bool);
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.ISensorPrivacyManager");
        removeSensorPrivacyListener(ISensorPrivacyListener.Stub.asInterface(param1Parcel1.readStrongBinder()));
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.ISensorPrivacyManager");
      addSensorPrivacyListener(ISensorPrivacyListener.Stub.asInterface(param1Parcel1.readStrongBinder()));
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements ISensorPrivacyManager {
      public static ISensorPrivacyManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public void addSensorPrivacyListener(ISensorPrivacyListener param2ISensorPrivacyListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
          if (param2ISensorPrivacyListener != null) {
            iBinder = param2ISensorPrivacyListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
            ISensorPrivacyManager.Stub.getDefaultImpl().addSensorPrivacyListener(param2ISensorPrivacyListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.ISensorPrivacyManager";
      }
      
      public boolean isSensorPrivacyEnabled() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(3, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
            bool = ISensorPrivacyManager.Stub.getDefaultImpl().isSensorPrivacyEnabled();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeSensorPrivacyListener(ISensorPrivacyListener param2ISensorPrivacyListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
          if (param2ISensorPrivacyListener != null) {
            iBinder = param2ISensorPrivacyListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
            ISensorPrivacyManager.Stub.getDefaultImpl().removeSensorPrivacyListener(param2ISensorPrivacyListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setSensorPrivacy(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
            ISensorPrivacyManager.Stub.getDefaultImpl().setSensorPrivacy(param2Boolean);
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
  
  private static class Proxy implements ISensorPrivacyManager {
    public static ISensorPrivacyManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void addSensorPrivacyListener(ISensorPrivacyListener param1ISensorPrivacyListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
        if (param1ISensorPrivacyListener != null) {
          iBinder = param1ISensorPrivacyListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
          ISensorPrivacyManager.Stub.getDefaultImpl().addSensorPrivacyListener(param1ISensorPrivacyListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ISensorPrivacyManager";
    }
    
    public boolean isSensorPrivacyEnabled() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
          bool = ISensorPrivacyManager.Stub.getDefaultImpl().isSensorPrivacyEnabled();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeSensorPrivacyListener(ISensorPrivacyListener param1ISensorPrivacyListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
        if (param1ISensorPrivacyListener != null) {
          iBinder = param1ISensorPrivacyListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
          ISensorPrivacyManager.Stub.getDefaultImpl().removeSensorPrivacyListener(param1ISensorPrivacyListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSensorPrivacy(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.ISensorPrivacyManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && ISensorPrivacyManager.Stub.getDefaultImpl() != null) {
          ISensorPrivacyManager.Stub.getDefaultImpl().setSensorPrivacy(param1Boolean);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISensorPrivacyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */