package android.drm;

public class Action {
  public static final int DEFAULT = 0;
  
  public static final int DISPLAY = 7;
  
  public static final int EXECUTE = 6;
  
  public static final int OUTPUT = 4;
  
  public static final int PLAY = 1;
  
  public static final int PREVIEW = 5;
  
  public static final int RINGTONE = 2;
  
  public static final int TRANSFER = 3;
  
  static boolean isValid(int paramInt) {
    boolean bool = false;
    switch (paramInt) {
      default:
        return bool;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
        break;
    } 
    bool = true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmStore$Action.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */