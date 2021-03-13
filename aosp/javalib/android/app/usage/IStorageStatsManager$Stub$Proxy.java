package android.app.usage;

import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IStorageStatsManager {
  public static IStorageStatsManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public long getCacheBytes(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().getCacheBytes(paramString1, paramString2); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getCacheQuotaBytes(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().getCacheQuotaBytes(paramString1, paramInt, paramString2); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getFreeBytes(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().getFreeBytes(paramString1, paramString2); 
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
  
  public long getTotalBytes(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().getTotalBytes(paramString1, paramString2); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isQuotaSupported(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(1, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null) {
        bool = IStorageStatsManager.Stub.getDefaultImpl().isQuotaSupported(paramString1, paramString2);
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
  
  public boolean isReservedSupported(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null) {
        bool = IStorageStatsManager.Stub.getDefaultImpl().isReservedSupported(paramString1, paramString2);
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
  
  public ParceledListSlice queryCratesForPackage(String paramString1, String paramString2, int paramInt, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().queryCratesForPackage(paramString1, paramString2, paramInt, paramString3); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ParceledListSlice)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice queryCratesForUid(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().queryCratesForUid(paramString1, paramInt, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ParceledListSlice)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice queryCratesForUser(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().queryCratesForUser(paramString1, paramInt, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ParceledListSlice)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ExternalStorageStats queryExternalStatsForUser(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().queryExternalStatsForUser(paramString1, paramInt, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ExternalStorageStats externalStorageStats = (ExternalStorageStats)ExternalStorageStats.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (ExternalStorageStats)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public StorageStats queryStatsForPackage(String paramString1, String paramString2, int paramInt, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().queryStatsForPackage(paramString1, paramString2, paramInt, paramString3); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        StorageStats storageStats = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (StorageStats)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public StorageStats queryStatsForUid(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().queryStatsForUid(paramString1, paramInt, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        StorageStats storageStats = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (StorageStats)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public StorageStats queryStatsForUser(String paramString1, int paramInt, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.app.usage.IStorageStatsManager");
      parcel1.writeString(paramString1);
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IStorageStatsManager.Stub.getDefaultImpl() != null)
        return IStorageStatsManager.Stub.getDefaultImpl().queryStatsForUser(paramString1, paramInt, paramString2); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        StorageStats storageStats = (StorageStats)StorageStats.CREATOR.createFromParcel(parcel2);
      } else {
        paramString1 = null;
      } 
      return (StorageStats)paramString1;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/IStorageStatsManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */