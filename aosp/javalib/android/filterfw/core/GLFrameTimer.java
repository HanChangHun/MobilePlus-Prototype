package android.filterfw.core;

class GLFrameTimer {
  private static StopWatchMap mTimer = null;
  
  public static StopWatchMap get() {
    if (mTimer == null)
      mTimer = new StopWatchMap(); 
    return mTimer;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/GLFrameTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */