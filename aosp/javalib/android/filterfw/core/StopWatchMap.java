package android.filterfw.core;

import java.util.HashMap;

public class StopWatchMap {
  public boolean LOG_MFF_RUNNING_TIMES = false;
  
  private HashMap<String, StopWatch> mStopWatches = null;
  
  public StopWatchMap() {
    this.mStopWatches = new HashMap<>();
  }
  
  public void start(String paramString) {
    if (!this.LOG_MFF_RUNNING_TIMES)
      return; 
    if (!this.mStopWatches.containsKey(paramString))
      this.mStopWatches.put(paramString, new StopWatch(paramString)); 
    ((StopWatch)this.mStopWatches.get(paramString)).start();
  }
  
  public void stop(String paramString) {
    if (!this.LOG_MFF_RUNNING_TIMES)
      return; 
    if (this.mStopWatches.containsKey(paramString)) {
      ((StopWatch)this.mStopWatches.get(paramString)).stop();
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Calling stop with unknown stopWatchName: ");
    stringBuilder.append(paramString);
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/StopWatchMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */