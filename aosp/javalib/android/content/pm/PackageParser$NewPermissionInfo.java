package android.content.pm;

public class NewPermissionInfo {
  public final int fileVersion;
  
  public final String name;
  
  public final int sdkVersion;
  
  public NewPermissionInfo(String paramString, int paramInt1, int paramInt2) {
    this.name = paramString;
    this.sdkVersion = paramInt1;
    this.fileVersion = paramInt2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$NewPermissionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */