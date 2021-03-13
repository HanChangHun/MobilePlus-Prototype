package android.app.backup;

import android.os.ParcelFileDescriptor;

public interface BackupHelper {
  void performBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2);
  
  void restoreEntity(BackupDataInputStream paramBackupDataInputStream);
  
  void writeNewStateDescription(ParcelFileDescriptor paramParcelFileDescriptor);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */