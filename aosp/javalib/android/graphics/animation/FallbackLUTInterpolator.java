package android.graphics.animation;

import android.animation.TimeInterpolator;
import android.view.Choreographer;

@HasNativeInterpolator
public class FallbackLUTInterpolator implements NativeInterpolator, TimeInterpolator {
  private static final int MAX_SAMPLE_POINTS = 300;
  
  private final float[] mLut;
  
  private TimeInterpolator mSourceInterpolator;
  
  public FallbackLUTInterpolator(TimeInterpolator paramTimeInterpolator, long paramLong) {
    this.mSourceInterpolator = paramTimeInterpolator;
    this.mLut = createLUT(paramTimeInterpolator, paramLong);
  }
  
  private static float[] createLUT(TimeInterpolator paramTimeInterpolator, long paramLong) {
    int i = (int)(Choreographer.getInstance().getFrameIntervalNanos() / 1000000L);
    int j = Math.min(Math.max(2, (int)Math.ceil(paramLong / i)), 300);
    float[] arrayOfFloat = new float[j];
    float f = (j - 1);
    for (i = 0; i < j; i++)
      arrayOfFloat[i] = paramTimeInterpolator.getInterpolation(i / f); 
    return arrayOfFloat;
  }
  
  public static long createNativeInterpolator(TimeInterpolator paramTimeInterpolator, long paramLong) {
    return NativeInterpolatorFactory.createLutInterpolator(createLUT(paramTimeInterpolator, paramLong));
  }
  
  public long createNativeInterpolator() {
    return NativeInterpolatorFactory.createLutInterpolator(this.mLut);
  }
  
  public float getInterpolation(float paramFloat) {
    return this.mSourceInterpolator.getInterpolation(paramFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/animation/FallbackLUTInterpolator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */