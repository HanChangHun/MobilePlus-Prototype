package android.ddm;

final class Names {
  private final String mAppName;
  
  private final String mPkgName;
  
  private Names(String paramString1, String paramString2) {
    this.mAppName = paramString1;
    this.mPkgName = paramString2;
  }
  
  public String getAppName() {
    return this.mAppName;
  }
  
  public String getPkgName() {
    return this.mPkgName;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/ddm/DdmHandleAppName$Names.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */