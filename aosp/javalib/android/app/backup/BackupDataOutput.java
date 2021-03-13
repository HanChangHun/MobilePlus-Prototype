package android.app.backup;

import android.annotation.SystemApi;
import java.io.FileDescriptor;
import java.io.IOException;

public class BackupDataOutput {
  long mBackupWriter;
  
  private final long mQuota;
  
  private final int mTransportFlags;
  
  @SystemApi
  public BackupDataOutput(FileDescriptor paramFileDescriptor) {
    this(paramFileDescriptor, -1L, 0);
  }
  
  @SystemApi
  public BackupDataOutput(FileDescriptor paramFileDescriptor, long paramLong) {
    this(paramFileDescriptor, paramLong, 0);
  }
  
  public BackupDataOutput(FileDescriptor paramFileDescriptor, long paramLong, int paramInt) {
    if (paramFileDescriptor != null) {
      this.mQuota = paramLong;
      this.mTransportFlags = paramInt;
      paramLong = ctor(paramFileDescriptor);
      this.mBackupWriter = paramLong;
      if (paramLong != 0L)
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
  
  private static native void setKeyPrefix_native(long paramLong, String paramString);
  
  private static native int writeEntityData_native(long paramLong, byte[] paramArrayOfbyte, int paramInt);
  
  private static native int writeEntityHeader_native(long paramLong, String paramString, int paramInt);
  
  protected void finalize() throws Throwable {
    try {
      dtor(this.mBackupWriter);
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public long getQuota() {
    return this.mQuota;
  }
  
  public int getTransportFlags() {
    return this.mTransportFlags;
  }
  
  public void setKeyPrefix(String paramString) {
    setKeyPrefix_native(this.mBackupWriter, paramString);
  }
  
  public int writeEntityData(byte[] paramArrayOfbyte, int paramInt) throws IOException {
    paramInt = writeEntityData_native(this.mBackupWriter, paramArrayOfbyte, paramInt);
    if (paramInt >= 0)
      return paramInt; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("result=0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new IOException(stringBuilder.toString());
  }
  
  public int writeEntityHeader(String paramString, int paramInt) throws IOException {
    paramInt = writeEntityHeader_native(this.mBackupWriter, paramString, paramInt);
    if (paramInt >= 0)
      return paramInt; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("result=0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    throw new IOException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupDataOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */