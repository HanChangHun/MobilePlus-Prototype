package android.graphics.animation;

import android.animation.TimeInterpolator;

public final class NativeInterpolatorFactory {
  public static native long createAccelerateDecelerateInterpolator();
  
  public static native long createAccelerateInterpolator(float paramFloat);
  
  public static native long createAnticipateInterpolator(float paramFloat);
  
  public static native long createAnticipateOvershootInterpolator(float paramFloat);
  
  public static native long createBounceInterpolator();
  
  public static native long createCycleInterpolator(float paramFloat);
  
  public static native long createDecelerateInterpolator(float paramFloat);
  
  public static native long createLinearInterpolator();
  
  public static native long createLutInterpolator(float[] paramArrayOffloat);
  
  public static long createNativeInterpolator(TimeInterpolator paramTimeInterpolator, long paramLong) {
    return (paramTimeInterpolator == null) ? createLinearInterpolator() : (RenderNodeAnimator.isNativeInterpolator(paramTimeInterpolator) ? ((NativeInterpolator)paramTimeInterpolator).createNativeInterpolator() : FallbackLUTInterpolator.createNativeInterpolator(paramTimeInterpolator, paramLong));
  }
  
  public static native long createOvershootInterpolator(float paramFloat);
  
  public static native long createPathInterpolator(float[] paramArrayOffloat1, float[] paramArrayOffloat2);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/animation/NativeInterpolatorFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */