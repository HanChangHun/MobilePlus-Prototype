package android.content.rollback;

public class RestoreInfo {
  public final int appId;
  
  public final String seInfo;
  
  public final int userId;
  
  public RestoreInfo(int paramInt1, int paramInt2, String paramString) {
    this.userId = paramInt1;
    this.appId = paramInt2;
    this.seInfo = paramString;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/PackageRollbackInfo$RestoreInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */