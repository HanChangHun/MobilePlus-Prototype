package android.drm;

public class Playback {
  public static final int PAUSE = 2;
  
  public static final int RESUME = 3;
  
  public static final int START = 0;
  
  public static final int STOP = 1;
  
  static boolean isValid(int paramInt) {
    boolean bool = false;
    if (paramInt == 0 || paramInt == 1 || paramInt == 2 || paramInt == 3)
      bool = true; 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmStore$Playback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */