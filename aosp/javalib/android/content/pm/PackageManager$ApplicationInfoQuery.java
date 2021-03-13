package android.content.pm;

import java.util.Objects;

final class ApplicationInfoQuery {
  final int flags;
  
  final String packageName;
  
  final int userId;
  
  ApplicationInfoQuery(String paramString, int paramInt1, int paramInt2) {
    this.packageName = paramString;
    this.flags = paramInt1;
    this.userId = paramInt2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    try {
      paramObject = paramObject;
      boolean bool1 = bool;
      if (Objects.equals(this.packageName, ((ApplicationInfoQuery)paramObject).packageName)) {
        bool1 = bool;
        if (this.flags == ((ApplicationInfoQuery)paramObject).flags) {
          bool1 = bool;
          if (this.userId == ((ApplicationInfoQuery)paramObject).userId)
            bool1 = true; 
        } 
      } 
      return bool1;
    } catch (ClassCastException classCastException) {
      return false;
    } 
  }
  
  public int hashCode() {
    return (Objects.hashCode(this.packageName) * 13 + Objects.hashCode(Integer.valueOf(this.flags))) * 13 + Objects.hashCode(Integer.valueOf(this.userId));
  }
  
  public String toString() {
    return String.format("ApplicationInfoQuery(packageName=\"%s\", flags=%s, userId=%s)", new Object[] { this.packageName, Integer.valueOf(this.flags), Integer.valueOf(this.userId) });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageManager$ApplicationInfoQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */