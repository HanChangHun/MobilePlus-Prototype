package android.app.slice;

import android.content.Context;
import android.metrics.LogMaker;
import android.net.Uri;
import com.android.internal.logging.MetricsLogger;

public class SliceMetrics {
  private static final String TAG = "SliceMetrics";
  
  private LogMaker mLogMaker;
  
  private MetricsLogger mMetricsLogger = new MetricsLogger();
  
  public SliceMetrics(Context paramContext, Uri paramUri) {
    LogMaker logMaker = new LogMaker(0);
    this.mLogMaker = logMaker;
    logMaker.addTaggedData(1402, paramUri.getAuthority());
    this.mLogMaker.addTaggedData(1403, paramUri.getPath());
  }
  
  public void logHidden() {
    synchronized (this.mLogMaker) {
      this.mLogMaker.setCategory(1401).setType(2);
      this.mMetricsLogger.write(this.mLogMaker);
      return;
    } 
  }
  
  public void logTouch(int paramInt, Uri paramUri) {
    synchronized (this.mLogMaker) {
      this.mLogMaker.setCategory(1401).setType(4).addTaggedData(1404, paramUri.getAuthority()).addTaggedData(1405, paramUri.getPath());
      this.mMetricsLogger.write(this.mLogMaker);
      return;
    } 
  }
  
  public void logVisible() {
    synchronized (this.mLogMaker) {
      this.mLogMaker.setCategory(1401).setType(1);
      this.mMetricsLogger.write(this.mLogMaker);
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceMetrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */