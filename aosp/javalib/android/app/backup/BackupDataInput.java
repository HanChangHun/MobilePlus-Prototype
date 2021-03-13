package android.app.backup;

import android.annotation.SystemApi;
import java.io.FileDescriptor;
import java.io.IOException;

public class BackupDataInput {
  long mBackupReader;
  
  private EntityHeader mHeader = new EntityHeader();
  
  private boolean mHeaderReady;
  
  @SystemApi
  public BackupDataInput(FileDescriptor paramFileDescriptor) {
    if (paramFileDescriptor != null) {
      long l = ctor(paramFileDescriptor);
      this.mBackupReader = l;
      if (l != 0L)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Native initialization failed with fd=");
      stringBuilder.append(paramFileDescriptor);
      throw new RuntimeException(stringBuilder.toString());
    } 
    throw null;
  }
  
  private static native long ctor(FileDescriptor paramFileDescriptor);
  
  private static native void dtor(long paramLong);
  
  private native int readEntityData_native(long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  private native int readNextHeader_native(long paramLong, EntityHeader paramEntityHeader);
  
  private native int skipEntityData_native(long paramLong);
  
  protected void finalize() throws Throwable {
    try {
      dtor(this.mBackupReader);
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getDataSize() {
    if (this.mHeaderReady)
      return this.mHeader.dataSize; 
    throw new IllegalStateException("Entity header not read");
  }
  
  public String getKey() {
    if (this.mHeaderReady)
      return this.mHeader.key; 
    throw new IllegalStateException("Entity header not read");
  }
  
  public int readEntityData(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (this.mHeaderReady) {
      paramInt1 = readEntityData_native(this.mBackupReader, paramArrayOfbyte, paramInt1, paramInt2);
      if (paramInt1 >= 0)
        return paramInt1; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("result=0x");
      stringBuilder.append(Integer.toHexString(paramInt1));
      throw new IOException(stringBuilder.toString());
    } 
    throw new IllegalStateException("Entity header not read");
  }
  
  public boolean readNextHeader() throws IOException {
    int i = readNextHeader_native(this.mBackupReader, this.mHeader);
    if (i == 0) {
      this.mHeaderReady = true;
      return true;
    } 
    if (i > 0) {
      this.mHeaderReady = false;
      return false;
    } 
    this.mHeaderReady = false;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("failed: 0x");
    stringBuilder.append(Integer.toHexString(i));
    throw new IOException(stringBuilder.toString());
  }
  
  public void skipEntityData() throws IOException {
    if (this.mHeaderReady) {
      skipEntityData_native(this.mBackupReader);
      return;
    } 
    throw new IllegalStateException("Entity header not read");
  }
  
  private static class EntityHeader {
    int dataSize;
    
    String key;
    
    private EntityHeader() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupDataInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */