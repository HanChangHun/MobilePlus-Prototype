package android.app.backup;

import android.annotation.SystemApi;

@SystemApi
public abstract class BackupObserver {
  public void backupFinished(int paramInt) {}
  
  public void onResult(String paramString, int paramInt) {}
  
  public void onUpdate(String paramString, BackupProgress paramBackupProgress) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */