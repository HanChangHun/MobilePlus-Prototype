package android.app;

import java.util.Objects;

final class ProviderKey {
  final String authority;
  
  final int userId;
  
  public ProviderKey(String paramString, int paramInt) {
    this.authority = paramString;
    this.userId = paramInt;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof ProviderKey;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      bool = bool1;
      if (Objects.equals(this.authority, ((ProviderKey)paramObject).authority)) {
        bool = bool1;
        if (this.userId == ((ProviderKey)paramObject).userId)
          bool = true; 
      } 
      return bool;
    } 
    return false;
  }
  
  public int hashCode() {
    boolean bool;
    String str = this.authority;
    if (str != null) {
      bool = str.hashCode();
    } else {
      bool = false;
    } 
    return bool ^ this.userId;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$ProviderKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */