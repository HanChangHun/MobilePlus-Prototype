package android.graphics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class FrameInfo {
  private static final int ANIMATION_START = 6;
  
  private static final int DRAW_START = 8;
  
  private static final int FLAGS = 0;
  
  public static final long FLAG_SURFACE_CANVAS = 4L;
  
  public static final long FLAG_WINDOW_LAYOUT_CHANGED = 1L;
  
  private static final int HANDLE_INPUT_START = 5;
  
  private static final int INTENDED_VSYNC = 1;
  
  private static final int NEWEST_INPUT_EVENT = 4;
  
  private static final int OLDEST_INPUT_EVENT = 3;
  
  private static final int PERFORM_TRAVERSALS_START = 7;
  
  private static final int VSYNC = 2;
  
  public long[] frameInfo = new long[9];
  
  public void addFlags(long paramLong) {
    long[] arrayOfLong = this.frameInfo;
    arrayOfLong[0] = arrayOfLong[0] | paramLong;
  }
  
  public void markAnimationsStart() {
    this.frameInfo[6] = System.nanoTime();
  }
  
  public void markDrawStart() {
    this.frameInfo[8] = System.nanoTime();
  }
  
  public void markInputHandlingStart() {
    this.frameInfo[5] = System.nanoTime();
  }
  
  public void markPerformTraversalsStart() {
    this.frameInfo[7] = System.nanoTime();
  }
  
  public void setVsync(long paramLong1, long paramLong2) {
    long[] arrayOfLong = this.frameInfo;
    arrayOfLong[1] = paramLong1;
    arrayOfLong[2] = paramLong2;
    arrayOfLong[3] = Long.MAX_VALUE;
    arrayOfLong[4] = 0L;
    arrayOfLong[0] = 0L;
  }
  
  public void updateInputEventTime(long paramLong1, long paramLong2) {
    long[] arrayOfLong = this.frameInfo;
    if (paramLong2 < arrayOfLong[3])
      arrayOfLong[3] = paramLong2; 
    arrayOfLong = this.frameInfo;
    if (paramLong1 > arrayOfLong[4])
      arrayOfLong[4] = paramLong1; 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FrameInfoFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/FrameInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */