package android.app.backup;

public class PathWithRequiredFlags {
  private final String mPath;
  
  private final int mRequiredFlags;
  
  public PathWithRequiredFlags(String paramString, int paramInt) {
    this.mPath = paramString;
    this.mRequiredFlags = paramInt;
  }
  
  public String getPath() {
    return this.mPath;
  }
  
  public int getRequiredFlags() {
    return this.mRequiredFlags;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/FullBackup$BackupScheme$PathWithRequiredFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */