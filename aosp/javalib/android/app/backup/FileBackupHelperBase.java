package android.app.backup;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;

class FileBackupHelperBase {
  private static final String TAG = "FileBackupHelperBase";
  
  Context mContext;
  
  boolean mExceptionLogged;
  
  long mPtr = ctor();
  
  FileBackupHelperBase(Context paramContext) {
    this.mContext = paramContext;
  }
  
  private static native long ctor();
  
  private static native void dtor(long paramLong);
  
  static void performBackup_checked(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2, String[] paramArrayOfString1, String[] paramArrayOfString2) {
    if (paramArrayOfString1.length == 0)
      return; 
    int i = paramArrayOfString1.length;
    int j = 0;
    while (j < i) {
      String str = paramArrayOfString1[j];
      if (str.charAt(0) == '/') {
        j++;
        continue;
      } 
      stringBuilder = new StringBuilder();
      stringBuilder.append("files must have all absolute paths: ");
      stringBuilder.append(str);
      throw new RuntimeException(stringBuilder.toString());
    } 
    if (paramArrayOfString1.length == paramArrayOfString2.length) {
      if (stringBuilder != null) {
        FileDescriptor fileDescriptor1 = stringBuilder.getFileDescriptor();
      } else {
        stringBuilder = null;
      } 
      FileDescriptor fileDescriptor = paramParcelFileDescriptor2.getFileDescriptor();
      if (fileDescriptor != null) {
        j = performBackup_native((FileDescriptor)stringBuilder, paramBackupDataOutput.mBackupWriter, fileDescriptor, paramArrayOfString1, paramArrayOfString2);
        if (j == 0)
          return; 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Backup failed 0x");
        stringBuilder.append(Integer.toHexString(j));
        throw new RuntimeException(stringBuilder.toString());
      } 
      throw null;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("files.length=");
    stringBuilder.append(paramArrayOfString1.length);
    stringBuilder.append(" keys.length=");
    stringBuilder.append(paramArrayOfString2.length);
    throw new RuntimeException(stringBuilder.toString());
  }
  
  private static native int performBackup_native(FileDescriptor paramFileDescriptor1, long paramLong, FileDescriptor paramFileDescriptor2, String[] paramArrayOfString1, String[] paramArrayOfString2);
  
  private static native int writeFile_native(long paramLong1, String paramString, long paramLong2);
  
  private static native int writeSnapshot_native(long paramLong, FileDescriptor paramFileDescriptor);
  
  protected void finalize() throws Throwable {
    try {
      dtor(this.mPtr);
      return;
    } finally {
      super.finalize();
    } 
  }
  
  boolean isKeyInList(String paramString, String[] paramArrayOfString) {
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfString[b].equals(paramString))
        return true; 
    } 
    return false;
  }
  
  boolean writeFile(File paramFile, BackupDataInputStream paramBackupDataInputStream) {
    paramFile.getParentFile().mkdirs();
    int i = writeFile_native(this.mPtr, paramFile.getAbsolutePath(), paramBackupDataInputStream.mData.mBackupReader);
    boolean bool = true;
    if (i != 0 && !this.mExceptionLogged) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed restoring file '");
      stringBuilder.append(paramFile);
      stringBuilder.append("' for app '");
      stringBuilder.append(this.mContext.getPackageName());
      stringBuilder.append("' result=0x");
      stringBuilder.append(Integer.toHexString(i));
      Log.e("FileBackupHelperBase", stringBuilder.toString());
      this.mExceptionLogged = true;
    } 
    if (i != 0)
      bool = false; 
    return bool;
  }
  
  public void writeNewStateDescription(ParcelFileDescriptor paramParcelFileDescriptor) {
    writeSnapshot_native(this.mPtr, paramParcelFileDescriptor.getFileDescriptor());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/FileBackupHelperBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */