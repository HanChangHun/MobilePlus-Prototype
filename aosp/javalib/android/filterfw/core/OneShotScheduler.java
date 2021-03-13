package android.filterfw.core;

import android.util.Log;
import java.util.HashMap;

public class OneShotScheduler extends RoundRobinScheduler {
  private static final String TAG = "OneShotScheduler";
  
  private final boolean mLogVerbose = Log.isLoggable("OneShotScheduler", 2);
  
  private HashMap<String, Integer> scheduled = new HashMap<>();
  
  public OneShotScheduler(FilterGraph paramFilterGraph) {
    super(paramFilterGraph);
  }
  
  public void reset() {
    super.reset();
    this.scheduled.clear();
  }
  
  public Filter scheduleNextNode() {
    StringBuilder stringBuilder = null;
    while (true) {
      Filter filter3;
      Filter filter2 = super.scheduleNextNode();
      if (filter2 == null) {
        if (this.mLogVerbose)
          Log.v("OneShotScheduler", "No filters available to run."); 
        return null;
      } 
      if (!this.scheduled.containsKey(filter2.getName())) {
        if (filter2.getNumberOfConnectedInputs() == 0)
          this.scheduled.put(filter2.getName(), Integer.valueOf(1)); 
        if (this.mLogVerbose) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Scheduling filter \"");
          stringBuilder.append(filter2.getName());
          stringBuilder.append("\" of type ");
          stringBuilder.append(filter2.getFilterClassName());
          Log.v("OneShotScheduler", stringBuilder.toString());
        } 
        return filter2;
      } 
      if (stringBuilder == filter2) {
        if (this.mLogVerbose)
          Log.v("OneShotScheduler", "One pass through graph completed."); 
        return null;
      } 
      StringBuilder stringBuilder1 = stringBuilder;
      if (stringBuilder == null)
        filter3 = filter2; 
      Filter filter1 = filter3;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/OneShotScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */