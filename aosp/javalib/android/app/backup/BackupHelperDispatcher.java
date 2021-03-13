package android.app.backup;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class BackupHelperDispatcher {
  private static final String TAG = "BackupHelperDispatcher";
  
  TreeMap<String, BackupHelper> mHelpers = new TreeMap<>();
  
  private static native int allocateHeader_native(Header paramHeader, FileDescriptor paramFileDescriptor);
  
  private void doOneBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2, Header paramHeader, BackupHelper paramBackupHelper) throws IOException {
    FileDescriptor fileDescriptor = paramParcelFileDescriptor2.getFileDescriptor();
    int i = allocateHeader_native(paramHeader, fileDescriptor);
    if (i >= 0) {
      paramBackupDataOutput.setKeyPrefix(paramHeader.keyPrefix);
      paramBackupHelper.performBackup(paramParcelFileDescriptor1, paramBackupDataOutput, paramParcelFileDescriptor2);
      i = writeHeader_native(paramHeader, fileDescriptor, i);
      if (i == 0)
        return; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("writeHeader_native failed (error ");
      stringBuilder1.append(i);
      stringBuilder1.append(")");
      throw new IOException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("allocateHeader_native failed (error ");
    stringBuilder.append(i);
    stringBuilder.append(")");
    throw new IOException(stringBuilder.toString());
  }
  
  private static native int readHeader_native(Header paramHeader, FileDescriptor paramFileDescriptor);
  
  private static native int skipChunk_native(FileDescriptor paramFileDescriptor, int paramInt);
  
  private static native int writeHeader_native(Header paramHeader, FileDescriptor paramFileDescriptor, int paramInt);
  
  public void addHelper(String paramString, BackupHelper paramBackupHelper) {
    this.mHelpers.put(paramString, paramBackupHelper);
  }
  
  public void performBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2) throws IOException {
    Header header = new Header();
    TreeMap treeMap = (TreeMap)this.mHelpers.clone();
    if (paramParcelFileDescriptor1 != null) {
      FileDescriptor fileDescriptor = paramParcelFileDescriptor1.getFileDescriptor();
      while (true) {
        int i = readHeader_native(header, fileDescriptor);
        if (i >= 0) {
          if (i == 0) {
            BackupHelper backupHelper = (BackupHelper)treeMap.get(header.keyPrefix);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("handling existing helper '");
            stringBuilder.append(header.keyPrefix);
            stringBuilder.append("' ");
            stringBuilder.append(backupHelper);
            Log.d("BackupHelperDispatcher", stringBuilder.toString());
            if (backupHelper != null) {
              doOneBackup(paramParcelFileDescriptor1, paramBackupDataOutput, paramParcelFileDescriptor2, header, backupHelper);
              treeMap.remove(header.keyPrefix);
              continue;
            } 
            skipChunk_native(fileDescriptor, header.chunkSize);
          } 
          continue;
        } 
        break;
      } 
    } 
    for (Map.Entry entry : treeMap.entrySet()) {
      header.keyPrefix = (String)entry.getKey();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("handling new helper '");
      stringBuilder.append(header.keyPrefix);
      stringBuilder.append("'");
      Log.d("BackupHelperDispatcher", stringBuilder.toString());
      doOneBackup(paramParcelFileDescriptor1, paramBackupDataOutput, paramParcelFileDescriptor2, header, (BackupHelper)entry.getValue());
    } 
  }
  
  public void performRestore(BackupDataInput paramBackupDataInput, int paramInt, ParcelFileDescriptor paramParcelFileDescriptor) throws IOException {
    paramInt = 0;
    BackupDataInputStream backupDataInputStream = new BackupDataInputStream(paramBackupDataInput);
    while (paramBackupDataInput.readNextHeader()) {
      String str = paramBackupDataInput.getKey();
      int i = str.indexOf(':');
      if (i > 0) {
        String str1 = str.substring(0, i);
        BackupHelper backupHelper = this.mHelpers.get(str1);
        if (backupHelper != null) {
          backupDataInputStream.dataSize = paramBackupDataInput.getDataSize();
          backupDataInputStream.key = str.substring(i + 1);
          backupHelper.restoreEntity(backupDataInputStream);
          i = paramInt;
        } else {
          i = paramInt;
          if (paramInt == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Couldn't find helper for: '");
            stringBuilder.append(str);
            stringBuilder.append("'");
            Log.w("BackupHelperDispatcher", stringBuilder.toString());
            i = 1;
          } 
        } 
      } else {
        i = paramInt;
        if (paramInt == 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Entity with no prefix: '");
          stringBuilder.append(str);
          stringBuilder.append("'");
          Log.w("BackupHelperDispatcher", stringBuilder.toString());
          i = 1;
        } 
      } 
      paramBackupDataInput.skipEntityData();
      paramInt = i;
    } 
    Iterator<BackupHelper> iterator = this.mHelpers.values().iterator();
    while (iterator.hasNext())
      ((BackupHelper)iterator.next()).writeNewStateDescription(paramParcelFileDescriptor); 
  }
  
  private static class Header {
    int chunkSize;
    
    String keyPrefix;
    
    private Header() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupHelperDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */