package android.hardware.biometrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBiometricServiceReceiver extends IInterface {
  void onAcquired(int paramInt, String paramString) throws RemoteException;
  
  void onAuthenticationFailed() throws RemoteException;
  
  void onAuthenticationSucceeded(int paramInt) throws RemoteException;
  
  void onDialogDismissed(int paramInt) throws RemoteException;
  
  void onError(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onSystemEvent(int paramInt) throws RemoteException;
  
  public static class Default implements IBiometricServiceReceiver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onAcquired(int param1Int, String param1String) throws RemoteException {}
    
    public void onAuthenticationFailed() throws RemoteException {}
    
    public void onAuthenticationSucceeded(int param1Int) throws RemoteException {}
    
    public void onDialogDismissed(int param1Int) throws RemoteException {}
    
    public void onError(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onSystemEvent(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBiometricServiceReceiver {
    private static final String DESCRIPTOR = "android.hardware.biometrics.IBiometricServiceReceiver";
    
    static final int TRANSACTION_onAcquired = 4;
    
    static final int TRANSACTION_onAuthenticationFailed = 2;
    
    static final int TRANSACTION_onAuthenticationSucceeded = 1;
    
    static final int TRANSACTION_onDialogDismissed = 5;
    
    static final int TRANSACTION_onError = 3;
    
    static final int TRANSACTION_onSystemEvent = 6;
    
    public Stub() {
      attachInterface(this, "android.hardware.biometrics.IBiometricServiceReceiver");
    }
    
    public static IBiometricServiceReceiver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.biometrics.IBiometricServiceReceiver");
      return (iInterface != null && iInterface instanceof IBiometricServiceReceiver) ? (IBiometricServiceReceiver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBiometricServiceReceiver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 6:
          return "onSystemEvent";
        case 5:
          return "onDialogDismissed";
        case 4:
          return "onAcquired";
        case 3:
          return "onError";
        case 2:
          return "onAuthenticationFailed";
        case 1:
          break;
      } 
      return "onAuthenticationSucceeded";
    }
    
    public static boolean setDefaultImpl(IBiometricServiceReceiver param1IBiometricServiceReceiver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBiometricServiceReceiver != null) {
          Proxy.sDefaultImpl = param1IBiometricServiceReceiver;
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
      if (param1Int1 != 1598968902) {
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 6:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
            onSystemEvent(param1Parcel1.readInt());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
            onDialogDismissed(param1Parcel1.readInt());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
            onAcquired(param1Parcel1.readInt(), param1Parcel1.readString());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
            onError(param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
            onAuthenticationFailed();
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.biometrics.IBiometricServiceReceiver");
        onAuthenticationSucceeded(param1Parcel1.readInt());
        return true;
      } 
      param1Parcel2.writeString("android.hardware.biometrics.IBiometricServiceReceiver");
      return true;
    }
    
    private static class Proxy implements IBiometricServiceReceiver {
      public static IBiometricServiceReceiver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.biometrics.IBiometricServiceReceiver";
      }
      
      public void onAcquired(int param2Int, String param2String) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
          parcel.writeInt(param2Int);
          parcel.writeString(param2String);
          if (!this.mRemote.transact(4, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiver.Stub.getDefaultImpl().onAcquired(param2Int, param2String);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAuthenticationFailed() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
          if (!this.mRemote.transact(2, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAuthenticationSucceeded(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onDialogDismissed(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(5, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiver.Stub.getDefaultImpl().onDialogDismissed(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onError(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(3, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiver.Stub.getDefaultImpl().onError(param2Int1, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onSystemEvent(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(6, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
            IBiometricServiceReceiver.Stub.getDefaultImpl().onSystemEvent(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IBiometricServiceReceiver {
    public static IBiometricServiceReceiver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.biometrics.IBiometricServiceReceiver";
    }
    
    public void onAcquired(int param1Int, String param1String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String);
        if (!this.mRemote.transact(4, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onAcquired(param1Int, param1String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationFailed() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        if (!this.mRemote.transact(2, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationSucceeded(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDialogDismissed(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onDialogDismissed(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onError(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(3, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onError(param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSystemEvent(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.biometrics.IBiometricServiceReceiver");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(6, parcel, null, 1) && IBiometricServiceReceiver.Stub.getDefaultImpl() != null) {
          IBiometricServiceReceiver.Stub.getDefaultImpl().onSystemEvent(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/IBiometricServiceReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */