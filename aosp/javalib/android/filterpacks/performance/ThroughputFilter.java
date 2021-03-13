package android.filterpacks.performance;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.format.ObjectFormat;
import android.os.SystemClock;

public class ThroughputFilter extends Filter {
  private long mLastTime = 0L;
  
  private FrameFormat mOutputFormat;
  
  @GenerateFieldPort(hasDefault = true, name = "period")
  private int mPeriod = 5;
  
  private int mPeriodFrameCount = 0;
  
  private int mTotalFrameCount = 0;
  
  public ThroughputFilter(String paramString) {
    super(paramString);
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void open(FilterContext paramFilterContext) {
    this.mTotalFrameCount = 0;
    this.mPeriodFrameCount = 0;
    this.mLastTime = 0L;
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame = pullInput("frame");
    pushOutput("frame", frame);
    this.mTotalFrameCount++;
    this.mPeriodFrameCount++;
    if (this.mLastTime == 0L)
      this.mLastTime = SystemClock.elapsedRealtime(); 
    long l = SystemClock.elapsedRealtime();
    if (l - this.mLastTime >= (this.mPeriod * 1000)) {
      FrameFormat frameFormat = frame.getFormat();
      int i = frameFormat.getWidth();
      int j = frameFormat.getHeight();
      Throughput throughput = new Throughput(this.mTotalFrameCount, this.mPeriodFrameCount, this.mPeriod, i * j);
      Frame frame1 = paramFilterContext.getFrameManager().newFrame(this.mOutputFormat);
      frame1.setObjectValue(throughput);
      pushOutput("throughput", frame1);
      this.mLastTime = l;
      this.mPeriodFrameCount = 0;
    } 
  }
  
  public void setupPorts() {
    addInputPort("frame");
    this.mOutputFormat = (FrameFormat)ObjectFormat.fromClass(Throughput.class, 1);
    addOutputBasedOnInput("frame", "frame");
    addOutputPort("throughput", this.mOutputFormat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/performance/ThroughputFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */