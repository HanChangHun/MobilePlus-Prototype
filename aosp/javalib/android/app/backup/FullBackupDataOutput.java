package android.app.backup;

import android.os.ParcelFileDescriptor;

public class FullBackupDataOutput {
  private final BackupDataOutput mData = null;
  
  private final long mQuota;
  
  private long mSize;
  
  private final int mTransportFlags;
  
  public FullBackupDataOutput(long paramLong) {
    this.mQuota = paramLong;
    this.mSize = 0L;
    this.mTransportFlags = 0;
  }
  
  public FullBackupDataOutput(long paramLong, int paramInt) {
    this.mQuota = paramLong;
    this.mSize = 0L;
    this.mTransportFlags = paramInt;
  }
  
  public FullBackupDataOutput(ParcelFileDescriptor paramParcelFileDescriptor) {
    this(paramParcelFileDescriptor, -1L, 0);
  }
  
  public FullBackupDataOutput(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong) {
    this.mQuota = paramLong;
    this.mTransportFlags = 0;
  }
  
  public FullBackupDataOutput(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong, int paramInt) {
    this.mQuota = paramLong;
    this.mTransportFlags = paramInt;
  }
  
  public void addSize(long paramLong) {
    if (paramLong > 0L)
      this.mSize += paramLong; 
  }
  
  public BackupDataOutput getData() {
    return this.mData;
  }
  
  public long getQuota() {
    return this.mQuota;
  }
  
  public long getSize() {
    return this.mSize;
  }
  
  public int getTransportFlags() {
    return this.mTransportFlags;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/FullBackupDataOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */