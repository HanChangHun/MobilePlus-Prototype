package android.app.backup;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.android.internal.backup.IBackupTransport;

class TransportImpl extends IBackupTransport.Stub {
  public int abortFullRestore() {
    return BackupTransport.this.abortFullRestore();
  }
  
  public void cancelFullBackup() throws RemoteException {
    BackupTransport.this.cancelFullBackup();
  }
  
  public int checkFullBackupSize(long paramLong) {
    return BackupTransport.this.checkFullBackupSize(paramLong);
  }
  
  public int clearBackupData(PackageInfo paramPackageInfo) throws RemoteException {
    return BackupTransport.this.clearBackupData(paramPackageInfo);
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
  
  public long getBackupQuota(String paramString, boolean paramBoolean) {
    return BackupTransport.this.getBackupQuota(paramString, paramBoolean);
  }
  
  public long getCurrentRestoreSet() throws RemoteException {
    return BackupTransport.this.getCurrentRestoreSet();
  }
  
  public int getNextFullRestoreDataChunk(ParcelFileDescriptor paramParcelFileDescriptor) {
    return BackupTransport.this.getNextFullRestoreDataChunk(paramParcelFileDescriptor);
  }
  
  public int getRestoreData(ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {
    return BackupTransport.this.getRestoreData(paramParcelFileDescriptor);
  }
  
  public int getTransportFlags() {
    return BackupTransport.this.getTransportFlags();
  }
  
  public int initializeDevice() throws RemoteException {
    return BackupTransport.this.initializeDevice();
  }
  
  public boolean isAppEligibleForBackup(PackageInfo paramPackageInfo, boolean paramBoolean) throws RemoteException {
    return BackupTransport.this.isAppEligibleForBackup(paramPackageInfo, paramBoolean);
  }
  
  public String name() throws RemoteException {
    return BackupTransport.this.name();
  }
  
  public RestoreDescription nextRestorePackage() throws RemoteException {
    return BackupTransport.this.nextRestorePackage();
  }
  
  public int performBackup(PackageInfo paramPackageInfo, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt) throws RemoteException {
    return BackupTransport.this.performBackup(paramPackageInfo, paramParcelFileDescriptor, paramInt);
  }
  
  public int performFullBackup(PackageInfo paramPackageInfo, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt) throws RemoteException {
    return BackupTransport.this.performFullBackup(paramPackageInfo, paramParcelFileDescriptor, paramInt);
  }
  
  public long requestBackupTime() throws RemoteException {
    return BackupTransport.this.requestBackupTime();
  }
  
  public long requestFullBackupTime() throws RemoteException {
    return BackupTransport.this.requestFullBackupTime();
  }
  
  public int sendBackupData(int paramInt) throws RemoteException {
    return BackupTransport.this.sendBackupData(paramInt);
  }
  
  public int startRestore(long paramLong, PackageInfo[] paramArrayOfPackageInfo) throws RemoteException {
    return BackupTransport.this.startRestore(paramLong, paramArrayOfPackageInfo);
  }
  
  public String transportDirName() throws RemoteException {
    return BackupTransport.this.transportDirName();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupTransport$TransportImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */