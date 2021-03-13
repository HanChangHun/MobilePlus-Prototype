package android.app.usage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.os.ParcelableException;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.storage.CrateInfo;
import android.os.storage.StorageManager;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class StorageStatsManager {
  private final Context mContext;
  
  private final IStorageStatsManager mService;
  
  public StorageStatsManager(Context paramContext, IStorageStatsManager paramIStorageStatsManager) {
    Objects.requireNonNull(paramContext);
    this.mContext = paramContext;
    Objects.requireNonNull(paramIStorageStatsManager);
    this.mService = paramIStorageStatsManager;
  }
  
  @Deprecated
  public long getCacheBytes(String paramString) throws IOException {
    return getCacheBytes(StorageManager.convert(paramString));
  }
  
  public long getCacheBytes(UUID paramUUID) throws IOException {
    try {
      return this.mService.getCacheBytes(StorageManager.convert(paramUUID), this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long getCacheQuotaBytes(String paramString, int paramInt) {
    try {
      return this.mService.getCacheQuotaBytes(paramString, paramInt, this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public long getFreeBytes(String paramString) throws IOException {
    return getFreeBytes(StorageManager.convert(paramString));
  }
  
  public long getFreeBytes(UUID paramUUID) throws IOException {
    try {
      return this.mService.getFreeBytes(StorageManager.convert(paramUUID), this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public long getTotalBytes(String paramString) throws IOException {
    return getTotalBytes(StorageManager.convert(paramString));
  }
  
  public long getTotalBytes(UUID paramUUID) throws IOException {
    try {
      return this.mService.getTotalBytes(StorageManager.convert(paramUUID), this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public boolean isQuotaSupported(String paramString) {
    return isQuotaSupported(StorageManager.convert(paramString));
  }
  
  public boolean isQuotaSupported(UUID paramUUID) {
    try {
      return this.mService.isQuotaSupported(StorageManager.convert(paramUUID), this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isReservedSupported(UUID paramUUID) {
    try {
      return this.mService.isReservedSupported(StorageManager.convert(paramUUID), this.mContext.getOpPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Collection<CrateInfo> queryCratesForPackage(UUID paramUUID, String paramString, UserHandle paramUserHandle) throws PackageManager.NameNotFoundException, IOException {
    try {
      ParceledListSlice parceledListSlice = this.mService.queryCratesForPackage(StorageManager.convert(paramUUID), paramString, paramUserHandle.getIdentifier(), this.mContext.getOpPackageName());
      Objects.requireNonNull(parceledListSlice);
      return parceledListSlice.getList();
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(PackageManager.NameNotFoundException.class);
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Collection<CrateInfo> queryCratesForUid(UUID paramUUID, int paramInt) throws IOException, PackageManager.NameNotFoundException {
    try {
      ParceledListSlice parceledListSlice = this.mService.queryCratesForUid(StorageManager.convert(paramUUID), paramInt, this.mContext.getOpPackageName());
      Objects.requireNonNull(parceledListSlice);
      return parceledListSlice.getList();
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(PackageManager.NameNotFoundException.class);
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Collection<CrateInfo> queryCratesForUser(UUID paramUUID, UserHandle paramUserHandle) throws PackageManager.NameNotFoundException, IOException {
    try {
      ParceledListSlice parceledListSlice = this.mService.queryCratesForUser(StorageManager.convert(paramUUID), paramUserHandle.getIdentifier(), this.mContext.getOpPackageName());
      Objects.requireNonNull(parceledListSlice);
      return parceledListSlice.getList();
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(PackageManager.NameNotFoundException.class);
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public ExternalStorageStats queryExternalStatsForUser(String paramString, UserHandle paramUserHandle) throws IOException {
    return queryExternalStatsForUser(StorageManager.convert(paramString), paramUserHandle);
  }
  
  public ExternalStorageStats queryExternalStatsForUser(UUID paramUUID, UserHandle paramUserHandle) throws IOException {
    try {
      return this.mService.queryExternalStatsForUser(StorageManager.convert(paramUUID), paramUserHandle.getIdentifier(), this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public StorageStats queryStatsForPackage(String paramString1, String paramString2, UserHandle paramUserHandle) throws PackageManager.NameNotFoundException, IOException {
    return queryStatsForPackage(StorageManager.convert(paramString1), paramString2, paramUserHandle);
  }
  
  public StorageStats queryStatsForPackage(UUID paramUUID, String paramString, UserHandle paramUserHandle) throws PackageManager.NameNotFoundException, IOException {
    try {
      return this.mService.queryStatsForPackage(StorageManager.convert(paramUUID), paramString, paramUserHandle.getIdentifier(), this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(PackageManager.NameNotFoundException.class);
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public StorageStats queryStatsForUid(String paramString, int paramInt) throws IOException {
    return queryStatsForUid(StorageManager.convert(paramString), paramInt);
  }
  
  public StorageStats queryStatsForUid(UUID paramUUID, int paramInt) throws IOException {
    try {
      return this.mService.queryStatsForUid(StorageManager.convert(paramUUID), paramInt, this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Deprecated
  public StorageStats queryStatsForUser(String paramString, UserHandle paramUserHandle) throws IOException {
    return queryStatsForUser(StorageManager.convert(paramString), paramUserHandle);
  }
  
  public StorageStats queryStatsForUser(UUID paramUUID, UserHandle paramUserHandle) throws IOException {
    try {
      return this.mService.queryStatsForUser(StorageManager.convert(paramUUID), paramUserHandle.getIdentifier(), this.mContext.getOpPackageName());
    } catch (ParcelableException parcelableException) {
      parcelableException.maybeRethrow(IOException.class);
      throw new RuntimeException(parcelableException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/StorageStatsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */