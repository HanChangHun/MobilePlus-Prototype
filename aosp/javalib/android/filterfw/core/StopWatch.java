package android.filterfw.core;

import android.os.SystemClock;
import android.util.Log;

class StopWatch {
  private int STOP_WATCH_LOGGING_PERIOD = 200;
  
  private String TAG = "MFF";
  
  private String mName;
  
  private int mNumCalls;
  
  private long mStartTime;
  
  private long mTotalTime;
  
  public StopWatch(String paramString) {
    this.mName = paramString;
    this.mStartTime = -1L;
    this.mTotalTime = 0L;
    this.mNumCalls = 0;
  }
  
  public void start() {
    if (this.mStartTime == -1L) {
      this.mStartTime = SystemClock.elapsedRealtime();
      return;
    } 
    throw new RuntimeException("Calling start with StopWatch already running");
  }
  
  public void stop() {
    if (this.mStartTime != -1L) {
      long l = SystemClock.elapsedRealtime();
      this.mTotalTime += l - this.mStartTime;
      int i = this.mNumCalls + 1;
      this.mNumCalls = i;
      this.mStartTime = -1L;
      if (i % this.STOP_WATCH_LOGGING_PERIOD == 0) {
        String str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AVG ms/call ");
        stringBuilder.append(this.mName);
        stringBuilder.append(": ");
        stringBuilder.append(String.format("%.1f", new Object[] { Float.valueOf((float)this.mTotalTime * 1.0F / this.mNumCalls) }));
        Log.i(str, stringBuilder.toString());
        this.mTotalTime = 0L;
        this.mNumCalls = 0;
      } 
      return;
    } 
    throw new RuntimeException("Calling stop with StopWatch already stopped");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/StopWatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */