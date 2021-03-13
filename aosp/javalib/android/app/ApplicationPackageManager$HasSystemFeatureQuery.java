package android.app;

import java.util.Objects;

final class HasSystemFeatureQuery {
  public final String name;
  
  public final int version;
  
  public HasSystemFeatureQuery(String paramString, int paramInt) {
    this.name = paramString;
    this.version = paramInt;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof HasSystemFeatureQuery;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      bool = bool1;
      if (Objects.equals(this.name, ((HasSystemFeatureQuery)paramObject).name)) {
        bool = bool1;
        if (this.version == ((HasSystemFeatureQuery)paramObject).version)
          bool = true; 
      } 
      return bool;
    } 
    return false;
  }
  
  public int hashCode() {
    return Objects.hashCode(this.name) * 13 + this.version;
  }
  
  public String toString() {
    return String.format("HasSystemFeatureQuery(name=\"%s\", version=%d)", new Object[] { this.name, Integer.valueOf(this.version) });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationPackageManager$HasSystemFeatureQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */