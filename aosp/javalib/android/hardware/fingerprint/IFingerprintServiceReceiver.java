package android.hardware.fingerprint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IFingerprintServiceReceiver extends IInterface {
  void onAcquired(long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  void onAuthenticationFailed(long paramLong) throws RemoteException;
  
  void onAuthenticationSucceeded(long paramLong, Fingerprint paramFingerprint, int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onEnrollResult(long paramLong, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onEnumerated(long paramLong, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void onError(long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  void onFingerprintDetected(long paramLong, int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onRemoved(long paramLong, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  public static class Default implements IFingerprintServiceReceiver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onAcquired(long param1Long, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onAuthenticationFailed(long param1Long) throws RemoteException {}
    
    public void onAuthenticationSucceeded(long param1Long, Fingerprint param1Fingerprint, int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onEnrollResult(long param1Long, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onEnumerated(long param1Long, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void onError(long param1Long, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onFingerprintDetected(long param1Long, int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onRemoved(long param1Long, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IFingerprintServiceReceiver {
    private static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintServiceReceiver";
    
    static final int TRANSACTION_onAcquired = 2;
    
    static final int TRANSACTION_onAuthenticationFailed = 5;
    
    static final int TRANSACTION_onAuthenticationSucceeded = 3;
    
    static final int TRANSACTION_onEnrollResult = 1;
    
    static final int TRANSACTION_onEnumerated = 8;
    
    static final int TRANSACTION_onError = 6;
    
    static final int TRANSACTION_onFingerprintDetected = 4;
    
    static final int TRANSACTION_onRemoved = 7;
    
    public Stub() {
      attachInterface(this, "android.hardware.fingerprint.IFingerprintServiceReceiver");
    }
    
    public static IFingerprintServiceReceiver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
      return (iInterface != null && iInterface instanceof IFingerprintServiceReceiver) ? (IFingerprintServiceReceiver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IFingerprintServiceReceiver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 8:
          return "onEnumerated";
        case 7:
          return "onRemoved";
        case 6:
          return "onError";
        case 5:
          return "onAuthenticationFailed";
        case 4:
          return "onFingerprintDetected";
        case 3:
          return "onAuthenticationSucceeded";
        case 2:
          return "onAcquired";
        case 1:
          break;
      } 
      return "onEnrollResult";
    }
    
    public static boolean setDefaultImpl(IFingerprintServiceReceiver param1IFingerprintServiceReceiver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IFingerprintServiceReceiver != null) {
          Proxy.sDefaultImpl = param1IFingerprintServiceReceiver;
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
        long l;
        boolean bool = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 8:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
            onEnumerated(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
            onRemoved(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
            onError(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
            onAuthenticationFailed(param1Parcel1.readLong());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
            l = param1Parcel1.readLong();
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0)
              bool = true; 
            onFingerprintDetected(l, param1Int1, bool);
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
            l = param1Parcel1.readLong();
            if (param1Parcel1.readInt() != 0) {
              Fingerprint fingerprint = (Fingerprint)Fingerprint.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool = true;
            } else {
              bool = false;
            } 
            onAuthenticationSucceeded(l, (Fingerprint)param1Parcel2, param1Int1, bool);
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
            onAcquired(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
        onEnrollResult(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel2.writeString("android.hardware.fingerprint.IFingerprintServiceReceiver");
      return true;
    }
    
    private static class Proxy implements IFingerprintServiceReceiver {
      public static IFingerprintServiceReceiver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.fingerprint.IFingerprintServiceReceiver";
      }
      
      public void onAcquired(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(2, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
            IFingerprintServiceReceiver.Stub.getDefaultImpl().onAcquired(param2Long, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAuthenticationFailed(long param2Long) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(5, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
            IFingerprintServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAuthenticationSucceeded(long param2Long, Fingerprint param2Fingerprint, int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
          parcel.writeLong(param2Long);
          boolean bool = false;
          if (param2Fingerprint != null) {
            parcel.writeInt(1);
            param2Fingerprint.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (param2Boolean)
            bool = true; 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(3, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
            IFingerprintServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param2Long, param2Fingerprint, param2Int, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onEnrollResult(long param2Long, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(1, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
            IFingerprintServiceReceiver.Stub.getDefaultImpl().onEnrollResult(param2Long, param2Int1, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onEnumerated(long param2Long, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(8, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
            IFingerprintServiceReceiver.Stub.getDefaultImpl().onEnumerated(param2Long, param2Int1, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onError(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(6, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
            IFingerprintServiceReceiver.Stub.getDefaultImpl().onError(param2Long, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onFingerprintDetected(long param2Long, int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(4, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
            IFingerprintServiceReceiver.Stub.getDefaultImpl().onFingerprintDetected(param2Long, param2Int, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onRemoved(long param2Long, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          parcel.writeInt(param2Int3);
          if (!this.mRemote.transact(7, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
            IFingerprintServiceReceiver.Stub.getDefaultImpl().onRemoved(param2Long, param2Int1, param2Int2, param2Int3);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IFingerprintServiceReceiver {
    public static IFingerprintServiceReceiver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.fingerprint.IFingerprintServiceReceiver";
    }
    
    public void onAcquired(long param1Long, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onAcquired(param1Long, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationFailed(long param1Long) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(5, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationSucceeded(long param1Long, Fingerprint param1Fingerprint, int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param1Long);
        boolean bool = false;
        if (param1Fingerprint != null) {
          parcel.writeInt(1);
          param1Fingerprint.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(3, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param1Long, param1Fingerprint, param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEnrollResult(long param1Long, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(1, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onEnrollResult(param1Long, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEnumerated(long param1Long, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(8, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onEnumerated(param1Long, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onError(long param1Long, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(6, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onError(param1Long, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFingerprintDetected(long param1Long, int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(4, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onFingerprintDetected(param1Long, param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRemoved(long param1Long, int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        parcel.writeInt(param1Int3);
        if (!this.mRemote.transact(7, parcel, null, 1) && IFingerprintServiceReceiver.Stub.getDefaultImpl() != null) {
          IFingerprintServiceReceiver.Stub.getDefaultImpl().onRemoved(param1Long, param1Int1, param1Int2, param1Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintServiceReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */