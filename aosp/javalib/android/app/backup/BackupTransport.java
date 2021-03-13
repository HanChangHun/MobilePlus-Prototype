package android.app.backup;

import android.annotation.SystemApi;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.android.internal.backup.IBackupTransport;

@SystemApi
public class BackupTransport {
  public static final int AGENT_ERROR = -1003;
  
  public static final int AGENT_UNKNOWN = -1004;
  
  public static final String EXTRA_TRANSPORT_REGISTRATION = "android.app.backup.extra.TRANSPORT_REGISTRATION";
  
  public static final int FLAG_DATA_NOT_CHANGED = 8;
  
  public static final int FLAG_INCREMENTAL = 2;
  
  public static final int FLAG_NON_INCREMENTAL = 4;
  
  public static final int FLAG_USER_INITIATED = 1;
  
  public static final int NO_MORE_DATA = -1;
  
  public static final int TRANSPORT_ERROR = -1000;
  
  public static final int TRANSPORT_NON_INCREMENTAL_BACKUP_REQUIRED = -1006;
  
  public static final int TRANSPORT_NOT_INITIALIZED = -1001;
  
  public static final int TRANSPORT_OK = 0;
  
  public static final int TRANSPORT_PACKAGE_REJECTED = -1002;
  
  public static final int TRANSPORT_QUOTA_EXCEEDED = -1005;
  
  IBackupTransport mBinderImpl = (IBackupTransport)new TransportImpl();
  
  public int abortFullRestore() {
    return 0;
  }
  
  public void cancelFullBackup() {
    throw new UnsupportedOperationException("Transport cancelFullBackup() not implemented");
  }
  
  public int checkFullBackupSize(long paramLong) {
    return 0;
  }
  
  public int clearBackupData(PackageInfo paramPackageInfo) {
    return -1000;
  }
  
  public Intent configurationIntent() {
    return null;
  }
  
  public String currentDestinationString() {
    throw new UnsupportedOperationException("Transport currentDestinationString() not implemented");
  }
  
  public Intent dataManagementIntent() {
    return null;
  }
  
  public CharSequence dataManagementIntentLabel() {
    return dataManagementLabel();
  }
  
  @Deprecated
  public String dataManagementLabel() {
    throw new UnsupportedOperationException("Transport dataManagementLabel() not implemented");
  }
  
  public int finishBackup() {
    return -1000;
  }
  
  public void finishRestore() {
    throw new UnsupportedOperationException("Transport finishRestore() not implemented");
  }
  
  public RestoreSet[] getAvailableRestoreSets() {
    return null;
  }
  
  public long getBackupQuota(String paramString, boolean paramBoolean) {
    return Long.MAX_VALUE;
  }
  
  public IBinder getBinder() {
    return this.mBinderImpl.asBinder();
  }
  
  public long getCurrentRestoreSet() {
    return 0L;
  }
  
  public int getNextFullRestoreDataChunk(ParcelFileDescriptor paramParcelFileDescriptor) {
    return 0;
  }
  
  public int getRestoreData(ParcelFileDescriptor paramParcelFileDescriptor) {
    return -1000;
  }
  
  public int getTransportFlags() {
    return 0;
  }
  
  public int initializeDevice() {
    return -1000;
  }
  
  public boolean isAppEligibleForBackup(PackageInfo paramPackageInfo, boolean paramBoolean) {
    return true;
  }
  
  public String name() {
    throw new UnsupportedOperationException("Transport name() not implemented");
  }
  
  public RestoreDescription nextRestorePackage() {
    return null;
  }
  
  public int performBackup(PackageInfo paramPackageInfo, ParcelFileDescriptor paramParcelFileDescriptor) {
    return -1000;
  }
  
  public int performBackup(PackageInfo paramPackageInfo, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt) {
    return performBackup(paramPackageInfo, paramParcelFileDescriptor);
  }
  
  public int performFullBackup(PackageInfo paramPackageInfo, ParcelFileDescriptor paramParcelFileDescriptor) {
    return -1002;
  }
  
  public int performFullBackup(PackageInfo paramPackageInfo, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt) {
    return performFullBackup(paramPackageInfo, paramParcelFileDescriptor);
  }
  
  public long requestBackupTime() {
    return 0L;
  }
  
  public long requestFullBackupTime() {
    return 0L;
  }
  
  public int sendBackupData(int paramInt) {
    return -1000;
  }
  
  public int startRestore(long paramLong, PackageInfo[] paramArrayOfPackageInfo) {
    return -1000;
  }
  
  public String transportDirName() {
    throw new UnsupportedOperationException("Transport transportDirName() not implemented");
  }
  
  class TransportImpl extends IBackupTransport.Stub {
    public int abortFullRestore() {
      return BackupTransport.this.abortFullRestore();
    }
    
    public void cancelFullBackup() throws RemoteException {
      BackupTransport.this.cancelFullBackup();
    }
    
    public int checkFullBackupSize(long param1Long) {
      return BackupTransport.this.checkFullBackupSize(param1Long);
    }
    
    public int clearBackupData(PackageInfo param1PackageInfo) throws RemoteException {
      return BackupTransport.this.clearBackupData(param1PackageInfo);
    }
    
    public Intent configurationIntent() throws RemoteException {
      return BackupTransport.this.configurationIntent();
    }
    
    public String currentDestinationString() throws RemoteException {
      return BackupTransport.this.currentDestinationString();
    }
    
    public Intent dataManagementIntent() {
      return BackupTransport.this.dataManagementIntent();
    }
    
    public CharSequence dataManagementIntentLabel() {
      return BackupTransport.this.dataManagementIntentLabel();
    }
    
    public int finishBackup() throws RemoteException {
      return BackupTransport.this.finishBackup();
    }
    
    public void finishRestore() throws RemoteException {
      BackupTransport.this.finishRestore();
    }
    
    public RestoreSet[] getAvailableRestoreSets() throws RemoteException {
      return BackupTransport.this.getAvailableRestoreSets();
    }
    
    public long getBackupQuota(String param1String, boolean param1Boolean) {
      return BackupTransport.this.getBackupQuota(param1String, param1Boolean);
    }
    
    public long getCurrentRestoreSet() throws RemoteException {
      return BackupTransport.this.getCurrentRestoreSet();
    }
    
    public int getNextFullRestoreDataChunk(ParcelFileDescriptor param1ParcelFileDescriptor) {
      return BackupTransport.this.getNextFullRestoreDataChunk(param1ParcelFileDescriptor);
    }
    
    public int getRestoreData(ParcelFileDescriptor param1ParcelFileDescriptor) throws RemoteException {
      return BackupTransport.this.getRestoreData(param1ParcelFileDescriptor);
    }
    
    public int getTransportFlags() {
      return BackupTransport.this.getTransportFlags();
    }
    
    public int initializeDevice() throws RemoteException {
      return BackupTransport.this.initializeDevice();
    }
    
    public boolean isAppEligibleForBackup(PackageInfo param1PackageInfo, boolean param1Boolean) throws RemoteException {
      return BackupTransport.this.isAppEligibleForBackup(param1PackageInfo, param1Boolean);
    }
    
    public String name() throws RemoteException {
      return BackupTransport.this.name();
    }
    
    public RestoreDescription nextRestorePackage() throws RemoteException {
      return BackupTransport.this.nextRestorePackage();
    }
    
    public int performBackup(PackageInfo param1PackageInfo, ParcelFileDescriptor param1ParcelFileDescriptor, int param1Int) throws RemoteException {
      return BackupTransport.this.performBackup(param1PackageInfo, param1ParcelFileDescriptor, param1Int);
    }
    
    public int performFullBackup(PackageInfo param1PackageInfo, ParcelFileDescriptor param1ParcelFileDescriptor, int param1Int) throws RemoteException {
      return BackupTransport.this.performFullBackup(param1PackageInfo, param1ParcelFileDescriptor, param1Int);
    }
    
    public long requestBackupTime() throws RemoteException {
      return BackupTransport.this.requestBackupTime();
    }
    
    public long requestFullBackupTime() throws RemoteException {
      return BackupTransport.this.requestFullBackupTime();
    }
    
    public int sendBackupData(int param1Int) throws RemoteException {
      return BackupTransport.this.sendBackupData(param1Int);
    }
    
    public int startRestore(long param1Long, PackageInfo[] param1ArrayOfPackageInfo) throws RemoteException {
      return BackupTransport.this.startRestore(param1Long, param1ArrayOfPackageInfo);
    }
    
    public String transportDirName() throws RemoteException {
      return BackupTransport.this.transportDirName();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupTransport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */