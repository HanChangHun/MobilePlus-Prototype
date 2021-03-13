package android.app.backup;

import android.app.QueuedWork;
import android.content.Context;
import android.os.ParcelFileDescriptor;

public class SharedPreferencesBackupHelper extends FileBackupHelperBase implements BackupHelper {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "SharedPreferencesBackupHelper";
  
  private Context mContext;
  
  private String[] mPrefGroups;
  
  public SharedPreferencesBackupHelper(Context paramContext, String... paramVarArgs) {
    super(paramContext);
    this.mContext = paramContext;
    this.mPrefGroups = paramVarArgs;
  }
  
  public void performBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2) {
    Context context = this.mContext;
    QueuedWork.waitToFinish();
    String[] arrayOfString1 = this.mPrefGroups;
    int i = arrayOfString1.length;
    String[] arrayOfString2 = new String[i];
    for (byte b = 0; b < i; b++)
      arrayOfString2[b] = context.getSharedPrefsFile(arrayOfString1[b]).getAbsolutePath(); 
    performBackup_checked(paramParcelFileDescriptor1, paramBackupDataOutput, paramParcelFileDescriptor2, arrayOfString2, arrayOfString1);
  }
  
  public void restoreEntity(BackupDataInputStream paramBackupDataInputStream) {
    Context context = this.mContext;
    String str = paramBackupDataInputStream.getKey();
    if (isKeyInList(str, this.mPrefGroups))
      writeFile(context.getSharedPrefsFile(str).getAbsoluteFile(), paramBackupDataInputStream); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/SharedPreferencesBackupHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */