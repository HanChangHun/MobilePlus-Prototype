package android.app.backup;

import android.os.ParcelFileDescriptor;
import java.io.IOException;

public class FullBackupAgent extends BackupAgent {
  public void onBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2) throws IOException {}
  
  public void onRestore(BackupDataInput paramBackupDataInput, int paramInt, ParcelFileDescriptor paramParcelFileDescriptor) throws IOException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/FullBackupAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */