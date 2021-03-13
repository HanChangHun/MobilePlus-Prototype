package android.app.backup;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import java.io.File;

public class FileBackupHelper extends FileBackupHelperBase implements BackupHelper {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "FileBackupHelper";
  
  Context mContext;
  
  String[] mFiles;
  
  File mFilesDir;
  
  public FileBackupHelper(Context paramContext, String... paramVarArgs) {
    super(paramContext);
    this.mContext = paramContext;
    this.mFilesDir = paramContext.getFilesDir();
    this.mFiles = paramVarArgs;
  }
  
  public void performBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2) {
    String[] arrayOfString1 = this.mFiles;
    File file = this.mContext.getFilesDir();
    int i = arrayOfString1.length;
    String[] arrayOfString2 = new String[i];
    for (byte b = 0; b < i; b++)
      arrayOfString2[b] = (new File(file, arrayOfString1[b])).getAbsolutePath(); 
    performBackup_checked(paramParcelFileDescriptor1, paramBackupDataOutput, paramParcelFileDescriptor2, arrayOfString2, arrayOfString1);
  }
  
  public void restoreEntity(BackupDataInputStream paramBackupDataInputStream) {
    String str = paramBackupDataInputStream.getKey();
    if (isKeyInList(str, this.mFiles))
      writeFile(new File(this.mFilesDir, str), paramBackupDataInputStream); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/FileBackupHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */