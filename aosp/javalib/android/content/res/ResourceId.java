package android.content.res;

public final class ResourceId {
  public static boolean isValid(int paramInt) {
    boolean bool;
    if (paramInt != -1 && (0xFF000000 & paramInt) != 0 && (0xFF0000 & paramInt) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/ResourceId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */