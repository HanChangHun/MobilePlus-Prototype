package android.graphics;

import android.os.SystemClock;

public class Interpolator {
  private int mFrameCount;
  
  private int mValueCount;
  
  private long native_instance;
  
  public Interpolator(int paramInt) {
    this.mValueCount = paramInt;
    this.mFrameCount = 2;
    this.native_instance = nativeConstructor(paramInt, 2);
  }
  
  public Interpolator(int paramInt1, int paramInt2) {
    this.mValueCount = paramInt1;
    this.mFrameCount = paramInt2;
    this.native_instance = nativeConstructor(paramInt1, paramInt2);
  }
  
  private static native long nativeConstructor(int paramInt1, int paramInt2);
  
  private static native void nativeDestructor(long paramLong);
  
  private static native void nativeReset(long paramLong, int paramInt1, int paramInt2);
  
  private static native void nativeSetKeyFrame(long paramLong, int paramInt1, int paramInt2, float[] paramArrayOffloat1, float[] paramArrayOffloat2);
  
  private static native void nativeSetRepeatMirror(long paramLong, float paramFloat, boolean paramBoolean);
  
  private static native int nativeTimeToValues(long paramLong, int paramInt, float[] paramArrayOffloat);
  
  protected void finalize() throws Throwable {
    nativeDestructor(this.native_instance);
    this.native_instance = 0L;
  }
  
  public final int getKeyFrameCount() {
    return this.mFrameCount;
  }
  
  public final int getValueCount() {
    return this.mValueCount;
  }
  
  public void reset(int paramInt) {
    reset(paramInt, 2);
  }
  
  public void reset(int paramInt1, int paramInt2) {
    this.mValueCount = paramInt1;
    this.mFrameCount = paramInt2;
    nativeReset(this.native_instance, paramInt1, paramInt2);
  }
  
  public void setKeyFrame(int paramInt1, int paramInt2, float[] paramArrayOffloat) {
    setKeyFrame(paramInt1, paramInt2, paramArrayOffloat, null);
  }
  
  public void setKeyFrame(int paramInt1, int paramInt2, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if (paramInt1 >= 0 && paramInt1 < this.mFrameCount) {
      if (paramArrayOffloat1.length >= this.mValueCount) {
        if (paramArrayOffloat2 == null || paramArrayOffloat2.length >= 4) {
          nativeSetKeyFrame(this.native_instance, paramInt1, paramInt2, paramArrayOffloat1, paramArrayOffloat2);
          return;
        } 
        throw new ArrayStoreException();
      } 
      throw new ArrayStoreException();
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public void setRepeatMirror(float paramFloat, boolean paramBoolean) {
    if (paramFloat >= 0.0F)
      nativeSetRepeatMirror(this.native_instance, paramFloat, paramBoolean); 
  }
  
  public Result timeToValues(int paramInt, float[] paramArrayOffloat) {
    if (paramArrayOffloat == null || paramArrayOffloat.length >= this.mValueCount) {
      paramInt = nativeTimeToValues(this.native_instance, paramInt, paramArrayOffloat);
      return (paramInt != 0) ? ((paramInt != 1) ? Result.FREEZE_END : Result.FREEZE_START) : Result.NORMAL;
    } 
    throw new ArrayStoreException();
  }
  
  public Result timeToValues(float[] paramArrayOffloat) {
    return timeToValues((int)SystemClock.uptimeMillis(), paramArrayOffloat);
  }
  
  public enum Result {
    FREEZE_END, FREEZE_START, NORMAL;
    
    static {
      Result result = new Result("FREEZE_END", 2);
      FREEZE_END = result;
      $VALUES = new Result[] { NORMAL, FREEZE_START, result };
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Interpolator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */