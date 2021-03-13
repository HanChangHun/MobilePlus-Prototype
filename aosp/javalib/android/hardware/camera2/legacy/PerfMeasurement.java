package android.hardware.camera2.legacy;

import android.os.SystemClock;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class PerfMeasurement {
  public static final int DEFAULT_MAX_QUERIES = 3;
  
  private static final long FAILED_TIMING = -2L;
  
  private static final long NO_DURATION_YET = -1L;
  
  private static final String TAG = "PerfMeasurement";
  
  private ArrayList<Long> mCollectedCpuDurations = new ArrayList<>();
  
  private ArrayList<Long> mCollectedGpuDurations = new ArrayList<>();
  
  private ArrayList<Long> mCollectedTimestamps = new ArrayList<>();
  
  private int mCompletedQueryCount = 0;
  
  private Queue<Long> mCpuDurationsQueue = new LinkedList<>();
  
  private final long mNativeContext;
  
  private long mStartTimeNs;
  
  private Queue<Long> mTimestampQueue = new LinkedList<>();
  
  public PerfMeasurement() {
    this.mNativeContext = nativeCreateContext(3);
  }
  
  public PerfMeasurement(int paramInt) {
    if (paramInt >= 1) {
      this.mNativeContext = nativeCreateContext(paramInt);
      return;
    } 
    throw new IllegalArgumentException("maxQueries is less than 1");
  }
  
  private long getNextGlDuration() {
    long l = nativeGetNextGlDuration(this.mNativeContext);
    if (l > 0L)
      this.mCompletedQueryCount++; 
    return l;
  }
  
  public static boolean isGlTimingSupported() {
    return nativeQuerySupport();
  }
  
  private static native long nativeCreateContext(int paramInt);
  
  private static native void nativeDeleteContext(long paramLong);
  
  protected static native long nativeGetNextGlDuration(long paramLong);
  
  private static native boolean nativeQuerySupport();
  
  protected static native void nativeStartGlTimer(long paramLong);
  
  protected static native void nativeStopGlTimer(long paramLong);
  
  public void addTimestamp(long paramLong) {
    this.mTimestampQueue.add(Long.valueOf(paramLong));
  }
  
  public void dumpPerformanceData(String paramString) {
    try {
      BufferedWriter bufferedWriter = new BufferedWriter();
      null = new FileWriter();
      this(paramString);
      this(null);
      try {
        bufferedWriter.write("timestamp gpu_duration cpu_duration\n");
        for (byte b = 0; b < this.mCollectedGpuDurations.size(); b++) {
          bufferedWriter.write(String.format("%d %d %d\n", new Object[] { this.mCollectedTimestamps.get(b), this.mCollectedGpuDurations.get(b), this.mCollectedCpuDurations.get(b) }));
        } 
        this.mCollectedTimestamps.clear();
        this.mCollectedGpuDurations.clear();
        this.mCollectedCpuDurations.clear();
      } finally {
        try {
          bufferedWriter.close();
        } finally {
          bufferedWriter = null;
        } 
      } 
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error writing data dump to ");
      stringBuilder.append(paramString);
      stringBuilder.append(":");
      stringBuilder.append(iOException);
      Log.e("PerfMeasurement", stringBuilder.toString());
    } 
  }
  
  protected void finalize() {
    nativeDeleteContext(this.mNativeContext);
  }
  
  public int getCompletedQueryCount() {
    return this.mCompletedQueryCount;
  }
  
  public void startTimer() {
    nativeStartGlTimer(this.mNativeContext);
    this.mStartTimeNs = SystemClock.elapsedRealtimeNanos();
  }
  
  public void stopTimer() {
    long l1 = SystemClock.elapsedRealtimeNanos();
    this.mCpuDurationsQueue.add(Long.valueOf(l1 - this.mStartTimeNs));
    nativeStopGlTimer(this.mNativeContext);
    long l2 = getNextGlDuration();
    if (l2 > 0L) {
      this.mCollectedGpuDurations.add(Long.valueOf(l2));
      ArrayList<Long> arrayList = this.mCollectedTimestamps;
      boolean bool = this.mTimestampQueue.isEmpty();
      long l = -1L;
      if (bool) {
        l1 = -1L;
      } else {
        l1 = ((Long)this.mTimestampQueue.poll()).longValue();
      } 
      arrayList.add(Long.valueOf(l1));
      arrayList = this.mCollectedCpuDurations;
      if (this.mCpuDurationsQueue.isEmpty()) {
        l1 = l;
      } else {
        l1 = ((Long)this.mCpuDurationsQueue.poll()).longValue();
      } 
      arrayList.add(Long.valueOf(l1));
    } 
    if (l2 == -2L) {
      if (!this.mTimestampQueue.isEmpty())
        this.mTimestampQueue.poll(); 
      if (!this.mCpuDurationsQueue.isEmpty())
        this.mCpuDurationsQueue.poll(); 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/PerfMeasurement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */