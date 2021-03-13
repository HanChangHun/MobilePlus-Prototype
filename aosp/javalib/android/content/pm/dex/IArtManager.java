package android.content.pm.dex;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IArtManager extends IInterface {
  boolean isRuntimeProfilingEnabled(int paramInt, String paramString) throws RemoteException;
  
  void snapshotRuntimeProfile(int paramInt, String paramString1, String paramString2, ISnapshotRuntimeProfileCallback paramISnapshotRuntimeProfileCallback, String paramString3) throws RemoteException;
  
  public static class Default implements IArtManager {
    public IBinder asBinder() {
      return null;
    }
    
    public boolean isRuntimeProfilingEnabled(int param1Int, String param1String) throws RemoteException {
      return false;
    }
    
    public void snapshotRuntimeProfile(int param1Int, String param1String1, String param1String2, ISnapshotRuntimeProfileCallback param1ISnapshotRuntimeProfileCallback, String param1String3) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IArtManager {
    private static final String DESCRIPTOR = "android.content.pm.dex.IArtManager";
    
    static final int TRANSACTION_isRuntimeProfilingEnabled = 2;
    
    static final int TRANSACTION_snapshotRuntimeProfile = 1;
    
    public Stub() {
      attachInterface(this, "android.content.pm.dex.IArtManager");
    }
    
    public static IArtManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.pm.dex.IArtManager");
      return (iInterface != null && iInterface instanceof IArtManager) ? (IArtManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IArtManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "isRuntimeProfilingEnabled") : "snapshotRuntimeProfile";
    }
    
    public static boolean setDefaultImpl(IArtManager param1IArtManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IArtManager != null) {
          Proxy.sDefaultImpl = param1IArtManager;
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
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.content.pm.dex.IArtManager");
          return true;
        } 
        param1Parcel1.enforceInterface("android.content.pm.dex.IArtManager");
        boolean bool = isRuntimeProfilingEnabled(param1Parcel1.readInt(), param1Parcel1.readString());
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(bool);
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.pm.dex.IArtManager");
      snapshotRuntimeProfile(param1Parcel1.readInt(), param1Parcel1.readString(), param1Parcel1.readString(), ISnapshotRuntimeProfileCallback.Stub.asInterface(param1Parcel1.readStrongBinder()), param1Parcel1.readString());
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements IArtManager {
      public static IArtManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.content.pm.dex.IArtManager";
      }
      
      public boolean isRuntimeProfilingEnabled(int param2Int, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.content.pm.dex.IArtManager");
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(2, parcel1, parcel2, 0) && IArtManager.Stub.getDefaultImpl() != null) {
            bool = IArtManager.Stub.getDefaultImpl().isRuntimeProfilingEnabled(param2Int, param2String);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void snapshotRuntimeProfile(int param2Int, String param2String1, String param2String2, ISnapshotRuntimeProfileCallback param2ISnapshotRuntimeProfileCallback, String param2String3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.content.pm.dex.IArtManager");
          parcel1.writeInt(param2Int);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2ISnapshotRuntimeProfileCallback != null) {
            iBinder = param2ISnapshotRuntimeProfileCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String3);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IArtManager.Stub.getDefaultImpl() != null) {
            IArtManager.Stub.getDefaultImpl().snapshotRuntimeProfile(param2Int, param2String1, param2String2, param2ISnapshotRuntimeProfileCallback, param2String3);
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
  
  private static class Proxy implements IArtManager {
    public static IArtManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.dex.IArtManager";
    }
    
    public boolean isRuntimeProfilingEnabled(int param1Int, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.dex.IArtManager");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IArtManager.Stub.getDefaultImpl() != null) {
          bool = IArtManager.Stub.getDefaultImpl().isRuntimeProfilingEnabled(param1Int, param1String);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void snapshotRuntimeProfile(int param1Int, String param1String1, String param1String2, ISnapshotRuntimeProfileCallback param1ISnapshotRuntimeProfileCallback, String param1String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.dex.IArtManager");
        parcel1.writeInt(param1Int);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (param1ISnapshotRuntimeProfileCallback != null) {
          iBinder = param1ISnapshotRuntimeProfileCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String3);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IArtManager.Stub.getDefaultImpl() != null) {
          IArtManager.Stub.getDefaultImpl().snapshotRuntimeProfile(param1Int, param1String1, param1String2, param1ISnapshotRuntimeProfileCallback, param1String3);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/IArtManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */