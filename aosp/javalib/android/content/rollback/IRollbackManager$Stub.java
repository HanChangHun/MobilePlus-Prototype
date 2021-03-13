package android.content.rollback;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IRollbackManager {
  private static final String DESCRIPTOR = "android.content.rollback.IRollbackManager";
  
  static final int TRANSACTION_blockRollbackManager = 9;
  
  static final int TRANSACTION_commitRollback = 3;
  
  static final int TRANSACTION_expireRollbackForPackage = 6;
  
  static final int TRANSACTION_getAvailableRollbacks = 1;
  
  static final int TRANSACTION_getRecentlyCommittedRollbacks = 2;
  
  static final int TRANSACTION_notifyStagedApkSession = 8;
  
  static final int TRANSACTION_notifyStagedSession = 7;
  
  static final int TRANSACTION_reloadPersistedData = 5;
  
  static final int TRANSACTION_snapshotAndRestoreUserData = 4;
  
  public Stub() {
    attachInterface(this, "android.content.rollback.IRollbackManager");
  }
  
  public static IRollbackManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.rollback.IRollbackManager");
    return (iInterface != null && iInterface instanceof IRollbackManager) ? (IRollbackManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRollbackManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 9:
        return "blockRollbackManager";
      case 8:
        return "notifyStagedApkSession";
      case 7:
        return "notifyStagedSession";
      case 6:
        return "expireRollbackForPackage";
      case 5:
        return "reloadPersistedData";
      case 4:
        return "snapshotAndRestoreUserData";
      case 3:
        return "commitRollback";
      case 2:
        return "getRecentlyCommittedRollbacks";
      case 1:
        break;
    } 
    return "getAvailableRollbacks";
  }
  
  public static boolean setDefaultImpl(IRollbackManager paramIRollbackManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRollbackManager != null) {
        Proxy.sDefaultImpl = paramIRollbackManager;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      ParceledListSlice parceledListSlice2;
      String str;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 9:
          paramParcel1.enforceInterface("android.content.rollback.IRollbackManager");
          blockRollbackManager(paramParcel1.readLong());
          paramParcel2.writeNoException();
          return true;
        case 8:
          paramParcel1.enforceInterface("android.content.rollback.IRollbackManager");
          notifyStagedApkSession(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          paramParcel1.enforceInterface("android.content.rollback.IRollbackManager");
          paramInt1 = notifyStagedSession(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 6:
          paramParcel1.enforceInterface("android.content.rollback.IRollbackManager");
          expireRollbackForPackage(paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.content.rollback.IRollbackManager");
          reloadPersistedData();
          paramParcel2.writeNoException();
          return true;
        case 4:
          paramParcel1.enforceInterface("android.content.rollback.IRollbackManager");
          snapshotAndRestoreUserData(paramParcel1.readString(), paramParcel1.createIntArray(), paramParcel1.readInt(), paramParcel1.readLong(), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 3:
          paramParcel1.enforceInterface("android.content.rollback.IRollbackManager");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            parceledListSlice2 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(paramParcel1);
          } else {
            parceledListSlice2 = null;
          } 
          str = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            IntentSender intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          commitRollback(paramInt1, parceledListSlice2, str, (IntentSender)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.content.rollback.IRollbackManager");
          parceledListSlice1 = getRecentlyCommittedRollbacks();
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 1:
          break;
      } 
      parceledListSlice1.enforceInterface("android.content.rollback.IRollbackManager");
      ParceledListSlice parceledListSlice1 = getAvailableRollbacks();
      paramParcel2.writeNoException();
      if (parceledListSlice1 != null) {
        paramParcel2.writeInt(1);
        parceledListSlice1.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    paramParcel2.writeString("android.content.rollback.IRollbackManager");
    return true;
  }
  
  private static class Proxy implements IRollbackManager {
    public static IRollbackManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void blockRollbackManager(long param2Long) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        parcel1.writeLong(param2Long);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
          IRollbackManager.Stub.getDefaultImpl().blockRollbackManager(param2Long);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void commitRollback(int param2Int, ParceledListSlice param2ParceledListSlice, String param2String, IntentSender param2IntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        parcel1.writeInt(param2Int);
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (param2IntentSender != null) {
          parcel1.writeInt(1);
          param2IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
          IRollbackManager.Stub.getDefaultImpl().commitRollback(param2Int, param2ParceledListSlice, param2String, param2IntentSender);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void expireRollbackForPackage(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
          IRollbackManager.Stub.getDefaultImpl().expireRollbackForPackage(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAvailableRollbacks() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IRollbackManager.Stub.getDefaultImpl().getAvailableRollbacks();
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.rollback.IRollbackManager";
    }
    
    public ParceledListSlice getRecentlyCommittedRollbacks() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IRollbackManager.Stub.getDefaultImpl().getRecentlyCommittedRollbacks();
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyStagedApkSession(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
          IRollbackManager.Stub.getDefaultImpl().notifyStagedApkSession(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int notifyStagedSession(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
          param2Int = IRollbackManager.Stub.getDefaultImpl().notifyStagedSession(param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reloadPersistedData() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
          IRollbackManager.Stub.getDefaultImpl().reloadPersistedData();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void snapshotAndRestoreUserData(String param2String1, int[] param2ArrayOfint, int param2Int1, long param2Long, String param2String2, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.rollback.IRollbackManager");
        try {
          parcel1.writeString(param2String1);
          try {
            parcel1.writeIntArray(param2ArrayOfint);
            try {
              parcel1.writeInt(param2Int1);
              try {
                parcel1.writeLong(param2Long);
                parcel1.writeString(param2String2);
                parcel1.writeInt(param2Int2);
                if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRollbackManager.Stub.getDefaultImpl() != null) {
                  IRollbackManager.Stub.getDefaultImpl().snapshotAndRestoreUserData(param2String1, param2ArrayOfint, param2Int1, param2Long, param2String2, param2Int2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } 
                parcel2.readException();
                parcel2.recycle();
                parcel1.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/IRollbackManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */