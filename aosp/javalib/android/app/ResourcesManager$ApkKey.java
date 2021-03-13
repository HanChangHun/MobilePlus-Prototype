package android.app;

class ApkKey {
  public final boolean overlay;
  
  public final String path;
  
  public final boolean sharedLib;
  
  ApkKey(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    this.path = paramString;
    this.sharedLib = paramBoolean1;
    this.overlay = paramBoolean2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof ApkKey;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    bool = bool1;
    if (this.path.equals(((ApkKey)paramObject).path)) {
      bool = bool1;
      if (this.sharedLib == ((ApkKey)paramObject).sharedLib) {
        bool = bool1;
        if (this.overlay == ((ApkKey)paramObject).overlay)
          bool = true; 
      } 
    } 
    return bool;
  }
  
  public int hashCode() {
    return ((1 * 31 + this.path.hashCode()) * 31 + Boolean.hashCode(this.sharedLib)) * 31 + Boolean.hashCode(this.overlay);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ResourcesManager$ApkKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */