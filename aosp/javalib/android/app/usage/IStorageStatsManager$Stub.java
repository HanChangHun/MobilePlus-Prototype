package android.app.usage;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IStorageStatsManager {
  private static final String DESCRIPTOR = "android.app.usage.IStorageStatsManager";
  
  static final int TRANSACTION_getCacheBytes = 5;
  
  static final int TRANSACTION_getCacheQuotaBytes = 6;
  
  static final int TRANSACTION_getFreeBytes = 4;
  
  static final int TRANSACTION_getTotalBytes = 3;
  
  static final int TRANSACTION_isQuotaSupported = 1;
  
  static final int TRANSACTION_isReservedSupported = 2;
  
  static final int TRANSACTION_queryCratesForPackage = 11;
  
  static final int TRANSACTION_queryCratesForUid = 12;
  
  static final int TRANSACTION_queryCratesForUser = 13;
  
  static final int TRANSACTION_queryExternalStatsForUser = 10;
  
  static final int TRANSACTION_queryStatsForPackage = 7;
  
  static final int TRANSACTION_queryStatsForUid = 8;
  
  static final int TRANSACTION_queryStatsForUser = 9;
  
  public Stub() {
    attachInterface(this, "android.app.usage.IStorageStatsManager");
  }
  
  public static IStorageStatsManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.usage.IStorageStatsManager");
    return (iInterface != null && iInterface instanceof IStorageStatsManager) ? (IStorageStatsManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IStorageStatsManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 13:
        return "queryCratesForUser";
      case 12:
        return "queryCratesForUid";
      case 11:
        return "queryCratesForPackage";
      case 10:
        return "queryExternalStatsForUser";
      case 9:
        return "queryStatsForUser";
      case 8:
        return "queryStatsForUid";
      case 7:
        return "queryStatsForPackage";
      case 6:
        return "getCacheQuotaBytes";
      case 5:
        return "getCacheBytes";
      case 4:
        return "getFreeBytes";
      case 3:
        return "getTotalBytes";
      case 2:
        return "isReservedSupported";
      case 1:
        break;
    } 
    return "isQuotaSupported";
  }
  
  public static boolean setDefaultImpl(IStorageStatsManager paramIStorageStatsManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIStorageStatsManager != null) {
        Proxy.sDefaultImpl = paramIStorageStatsManager;
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
      ParceledListSlice parceledListSlice;
      ExternalStorageStats externalStorageStats;
      StorageStats storageStats;
      long l;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 13:
          paramParcel1.enforceInterface("android.app.usage.IStorageStatsManager");
          parceledListSlice = queryCratesForUser(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 12:
          parceledListSlice.enforceInterface("android.app.usage.IStorageStatsManager");
          parceledListSlice = queryCratesForUid(parceledListSlice.readString(), parceledListSlice.readInt(), parceledListSlice.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 11:
          parceledListSlice.enforceInterface("android.app.usage.IStorageStatsManager");
          parceledListSlice = queryCratesForPackage(parceledListSlice.readString(), parceledListSlice.readString(), parceledListSlice.readInt(), parceledListSlice.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 10:
          parceledListSlice.enforceInterface("android.app.usage.IStorageStatsManager");
          externalStorageStats = queryExternalStatsForUser(parceledListSlice.readString(), parceledListSlice.readInt(), parceledListSlice.readString());
          paramParcel2.writeNoException();
          if (externalStorageStats != null) {
            paramParcel2.writeInt(1);
            externalStorageStats.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 9:
          externalStorageStats.enforceInterface("android.app.usage.IStorageStatsManager");
          storageStats = queryStatsForUser(externalStorageStats.readString(), externalStorageStats.readInt(), externalStorageStats.readString());
          paramParcel2.writeNoException();
          if (storageStats != null) {
            paramParcel2.writeInt(1);
            storageStats.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 8:
          storageStats.enforceInterface("android.app.usage.IStorageStatsManager");
          storageStats = queryStatsForUid(storageStats.readString(), storageStats.readInt(), storageStats.readString());
          paramParcel2.writeNoException();
          if (storageStats != null) {
            paramParcel2.writeInt(1);
            storageStats.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 7:
          storageStats.enforceInterface("android.app.usage.IStorageStatsManager");
          storageStats = queryStatsForPackage(storageStats.readString(), storageStats.readString(), storageStats.readInt(), storageStats.readString());
          paramParcel2.writeNoException();
          if (storageStats != null) {
            paramParcel2.writeInt(1);
            storageStats.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 6:
          storageStats.enforceInterface("android.app.usage.IStorageStatsManager");
          l = getCacheQuotaBytes(storageStats.readString(), storageStats.readInt(), storageStats.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 5:
          storageStats.enforceInterface("android.app.usage.IStorageStatsManager");
          l = getCacheBytes(storageStats.readString(), storageStats.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 4:
          storageStats.enforceInterface("android.app.usage.IStorageStatsManager");
          l = getFreeBytes(storageStats.readString(), storageStats.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 3:
          storageStats.enforceInterface("android.app.usage.IStorageStatsManager");
          l = getTotalBytes(storageStats.readString(), storageStats.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 2:
          storageStats.enforceInterface("android.app.usage.IStorageStatsManager");
          bool = isReservedSupported(storageStats.readString(), storageStats.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 1:
          break;
      } 
      storageStats.enforceInterface("android.app.usage.IStorageStatsManager");
      boolean bool = isQuotaSupported(storageStats.readString(), storageStats.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool);
      return true;
    } 
    paramParcel2.writeString("android.app.usage.IStorageStatsManager");
    return true;
  }
  
  private static class Proxy implements IStorageStatsManager {
    public static IStorageStatsManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public long getCacheBytes(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().getCacheBytes(param2String1, param2String2); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getCacheQuotaBytes(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().getCacheQuotaBytes(param2String1, param2Int, param2String2); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getFreeBytes(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().getFreeBytes(param2String1, param2String2); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.usage.IStorageStatsManager";
    }
    
    public long getTotalBytes(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().getTotalBytes(param2String1, param2String2); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isQuotaSupported(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(1, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null) {
          bool = IStorageStatsManager.Stub.getDefaultImpl().isQuotaSupported(param2String1, param2String2);
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
    
    public boolean isReservedSupported(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null) {
          bool = IStorageStatsManager.Stub.getDefaultImpl().isReservedSupported(param2String1, param2String2);
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
    
    public ParceledListSlice queryCratesForPackage(String param2String1, String param2String2, int param2Int, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().queryCratesForPackage(param2String1, param2String2, param2Int, param2String3); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ParceledListSlice)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryCratesForUid(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().queryCratesForUid(param2String1, param2Int, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ParceledListSlice)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice queryCratesForUser(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().queryCratesForUser(param2String1, param2Int, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ParceledListSlice)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ExternalStorageStats queryExternalStatsForUser(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().queryExternalStatsForUser(param2String1, param2Int, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ExternalStorageStats externalStorageStats = (ExternalStorageStats)ExternalStorageStats.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (ExternalStorageStats)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public StorageStats queryStatsForPackage(String param2String1, String param2String2, int param2Int, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().queryStatsForPackage(param2String1, param2String2, param2Int, param2String3); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          StorageStats storageStats = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (StorageStats)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public StorageStats queryStatsForUid(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().queryStatsForUid(param2String1, param2Int, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          StorageStats storageStats = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (StorageStats)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public StorageStats queryStatsForUser(String param2String1, int param2Int, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
        parcel1.writeString(param2String1);
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
          return IStorageStatsManager.Stub.getDefaultImpl().queryStatsForUser(param2String1, param2Int, param2String2); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          StorageStats storageStats = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel2);
        } else {
          param2String1 = null;
        } 
        return (StorageStats)param2String1;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/IStorageStatsManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */