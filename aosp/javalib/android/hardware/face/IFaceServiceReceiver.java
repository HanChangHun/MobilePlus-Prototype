package android.hardware.face;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IFaceServiceReceiver extends IInterface {
  void onAcquired(long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  void onAuthenticationFailed(long paramLong) throws RemoteException;
  
  void onAuthenticationSucceeded(long paramLong, Face paramFace, int paramInt, boolean paramBoolean) throws RemoteException;
  
  void onEnrollResult(long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  void onEnumerated(long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  void onError(long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  void onFeatureGet(boolean paramBoolean1, int paramInt, boolean paramBoolean2) throws RemoteException;
  
  void onFeatureSet(boolean paramBoolean, int paramInt) throws RemoteException;
  
  void onRemoved(long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IFaceServiceReceiver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onAcquired(long param1Long, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onAuthenticationFailed(long param1Long) throws RemoteException {}
    
    public void onAuthenticationSucceeded(long param1Long, Face param1Face, int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public void onEnrollResult(long param1Long, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onEnumerated(long param1Long, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onError(long param1Long, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void onFeatureGet(boolean param1Boolean1, int param1Int, boolean param1Boolean2) throws RemoteException {}
    
    public void onFeatureSet(boolean param1Boolean, int param1Int) throws RemoteException {}
    
    public void onRemoved(long param1Long, int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IFaceServiceReceiver {
    private static final String DESCRIPTOR = "android.hardware.face.IFaceServiceReceiver";
    
    static final int TRANSACTION_onAcquired = 2;
    
    static final int TRANSACTION_onAuthenticationFailed = 4;
    
    static final int TRANSACTION_onAuthenticationSucceeded = 3;
    
    static final int TRANSACTION_onEnrollResult = 1;
    
    static final int TRANSACTION_onEnumerated = 7;
    
    static final int TRANSACTION_onError = 5;
    
    static final int TRANSACTION_onFeatureGet = 9;
    
    static final int TRANSACTION_onFeatureSet = 8;
    
    static final int TRANSACTION_onRemoved = 6;
    
    public Stub() {
      attachInterface(this, "android.hardware.face.IFaceServiceReceiver");
    }
    
    public static IFaceServiceReceiver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.face.IFaceServiceReceiver");
      return (iInterface != null && iInterface instanceof IFaceServiceReceiver) ? (IFaceServiceReceiver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IFaceServiceReceiver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 9:
          return "onFeatureGet";
        case 8:
          return "onFeatureSet";
        case 7:
          return "onEnumerated";
        case 6:
          return "onRemoved";
        case 5:
          return "onError";
        case 4:
          return "onAuthenticationFailed";
        case 3:
          return "onAuthenticationSucceeded";
        case 2:
          return "onAcquired";
        case 1:
          break;
      } 
      return "onEnrollResult";
    }
    
    public static boolean setDefaultImpl(IFaceServiceReceiver param1IFaceServiceReceiver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IFaceServiceReceiver != null) {
          Proxy.sDefaultImpl = param1IFaceServiceReceiver;
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
        boolean bool1 = false;
        boolean bool2 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 9:
            param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0)
              bool2 = true; 
            onFeatureGet(bool1, param1Int1, bool2);
            return true;
          case 8:
            param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
            if (param1Parcel1.readInt() != 0)
              bool1 = true; 
            onFeatureSet(bool1, param1Parcel1.readInt());
            return true;
          case 7:
            param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
            onEnumerated(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 6:
            param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
            onRemoved(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
            onError(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
            onAuthenticationFailed(param1Parcel1.readLong());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
            l = param1Parcel1.readLong();
            if (param1Parcel1.readInt() != 0) {
              Face face = (Face)Face.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel2 = null;
            } 
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            onAuthenticationSucceeded(l, (Face)param1Parcel2, param1Int1, bool1);
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
            onAcquired(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.hardware.face.IFaceServiceReceiver");
        onEnrollResult(param1Parcel1.readLong(), param1Parcel1.readInt(), param1Parcel1.readInt());
        return true;
      } 
      param1Parcel2.writeString("android.hardware.face.IFaceServiceReceiver");
      return true;
    }
    
    private static class Proxy implements IFaceServiceReceiver {
      public static IFaceServiceReceiver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.face.IFaceServiceReceiver";
      }
      
      public void onAcquired(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(2, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onAcquired(param2Long, param2Int1, param2Int2);
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
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          parcel.writeLong(param2Long);
          if (!this.mRemote.transact(4, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed(param2Long);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onAuthenticationSucceeded(long param2Long, Face param2Face, int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          parcel.writeLong(param2Long);
          boolean bool = false;
          if (param2Face != null) {
            parcel.writeInt(1);
            param2Face.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeInt(param2Int);
          if (param2Boolean)
            bool = true; 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(3, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param2Long, param2Face, param2Int, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onEnrollResult(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(1, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onEnrollResult(param2Long, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onEnumerated(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(7, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onEnumerated(param2Long, param2Int1, param2Int2);
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
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(5, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onError(param2Long, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onFeatureGet(boolean param2Boolean1, int param2Int, boolean param2Boolean2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          boolean bool1 = false;
          if (param2Boolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel.writeInt(bool2);
          parcel.writeInt(param2Int);
          boolean bool2 = bool1;
          if (param2Boolean2)
            bool2 = true; 
          parcel.writeInt(bool2);
          if (!this.mRemote.transact(9, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onFeatureGet(param2Boolean1, param2Int, param2Boolean2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onFeatureSet(boolean param2Boolean, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(8, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onFeatureSet(param2Boolean, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onRemoved(long param2Long, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
          parcel.writeLong(param2Long);
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(6, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
            IFaceServiceReceiver.Stub.getDefaultImpl().onRemoved(param2Long, param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IFaceServiceReceiver {
    public static IFaceServiceReceiver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.face.IFaceServiceReceiver";
    }
    
    public void onAcquired(long param1Long, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(2, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onAcquired(param1Long, param1Int1, param1Int2);
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
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param1Long);
        if (!this.mRemote.transact(4, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onAuthenticationFailed(param1Long);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAuthenticationSucceeded(long param1Long, Face param1Face, int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param1Long);
        boolean bool = false;
        if (param1Face != null) {
          parcel.writeInt(1);
          param1Face.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(3, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onAuthenticationSucceeded(param1Long, param1Face, param1Int, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEnrollResult(long param1Long, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onEnrollResult(param1Long, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onEnumerated(long param1Long, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(7, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onEnumerated(param1Long, param1Int1, param1Int2);
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
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onError(param1Long, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFeatureGet(boolean param1Boolean1, int param1Int, boolean param1Boolean2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        boolean bool1 = false;
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel.writeInt(bool2);
        parcel.writeInt(param1Int);
        boolean bool2 = bool1;
        if (param1Boolean2)
          bool2 = true; 
        parcel.writeInt(bool2);
        if (!this.mRemote.transact(9, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onFeatureGet(param1Boolean1, param1Int, param1Boolean2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFeatureSet(boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onFeatureSet(param1Boolean, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRemoved(long param1Long, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.hardware.face.IFaceServiceReceiver");
        parcel.writeLong(param1Long);
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(6, parcel, null, 1) && IFaceServiceReceiver.Stub.getDefaultImpl() != null) {
          IFaceServiceReceiver.Stub.getDefaultImpl().onRemoved(param1Long, param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/IFaceServiceReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */