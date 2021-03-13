package android.app;

import android.app.backup.IBackupCallback;
import android.app.backup.IBackupManager;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBackupAgent {
  public IBinder asBinder() {
    return null;
  }
  
  public void doBackup(ParcelFileDescriptor paramParcelFileDescriptor1, ParcelFileDescriptor paramParcelFileDescriptor2, ParcelFileDescriptor paramParcelFileDescriptor3, long paramLong, IBackupCallback paramIBackupCallback, int paramInt) throws RemoteException {}
  
  public void doFullBackup(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong, int paramInt1, IBackupManager paramIBackupManager, int paramInt2) throws RemoteException {}
  
  public void doMeasureFullBackup(long paramLong, int paramInt1, IBackupManager paramIBackupManager, int paramInt2) throws RemoteException {}
  
  public void doQuotaExceeded(long paramLong1, long paramLong2, IBackupCallback paramIBackupCallback) throws RemoteException {}
  
  public void doRestore(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager) throws RemoteException {}
  
  public void doRestoreFile(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, int paramInt1, String paramString1, String paramString2, long paramLong2, long paramLong3, int paramInt2, IBackupManager paramIBackupManager) throws RemoteException {}
  
  public void doRestoreFinished(int paramInt, IBackupManager paramIBackupManager) throws RemoteException {}
  
  public void doRestoreWithExcludedKeys(ParcelFileDescriptor paramParcelFileDescriptor1, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor2, int paramInt, IBackupManager paramIBackupManager, List<String> paramList) throws RemoteException {}
  
  public void fail(String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IBackupAgent$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */