package android.filterpacks.performance;

public class Throughput {
  private final int mPeriodFrames;
  
  private final int mPeriodTime;
  
  private final int mPixels;
  
  private final int mTotalFrames;
  
  public Throughput(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mTotalFrames = paramInt1;
    this.mPeriodFrames = paramInt2;
    this.mPeriodTime = paramInt3;
    this.mPixels = paramInt4;
  }
  
  public float getFramesPerSecond() {
    return this.mPeriodFrames / this.mPeriodTime;
  }
  
  public float getNanosPerPixel() {
    return (float)(this.mPeriodTime / this.mPeriodFrames * 1000000.0D / this.mPixels);
  }
  
  public int getPeriodFrameCount() {
    return this.mPeriodFrames;
  }
  
  public int getPeriodTime() {
    return this.mPeriodTime;
  }
  
  public int getTotalFrameCount() {
    return this.mTotalFrames;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getFramesPerSecond());
    stringBuilder.append(" FPS");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/performance/Throughput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */