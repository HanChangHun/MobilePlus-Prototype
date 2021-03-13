package android.hardware.camera2.utils;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.camera2.CaptureRequest;
import android.util.Rational;
import android.util.Size;
import com.android.internal.util.Preconditions;

public class ParamsUtils {
  private static final int RATIONAL_DENOMINATOR = 1000000;
  
  private ParamsUtils() {
    throw new AssertionError();
  }
  
  public static void convertRectF(Rect paramRect, RectF paramRectF) {
    Preconditions.checkNotNull(paramRect, "source must not be null");
    Preconditions.checkNotNull(paramRectF, "destination must not be null");
    paramRectF.left = paramRect.left;
    paramRectF.right = paramRect.right;
    paramRectF.bottom = paramRect.bottom;
    paramRectF.top = paramRect.top;
  }
  
  public static Rational createRational(float paramFloat) {
    if (Float.isNaN(paramFloat))
      return Rational.NaN; 
    if (paramFloat == Float.POSITIVE_INFINITY)
      return Rational.POSITIVE_INFINITY; 
    if (paramFloat == Float.NEGATIVE_INFINITY)
      return Rational.NEGATIVE_INFINITY; 
    if (paramFloat == 0.0F)
      return Rational.ZERO; 
    for (int i = 1000000;; i /= 10) {
      float f = i * paramFloat;
      if ((f > -2.14748365E9F && f < 2.14748365E9F) || i == 1)
        return new Rational((int)f, i); 
    } 
  }
  
  public static Rect createRect(RectF paramRectF) {
    Preconditions.checkNotNull(paramRectF, "rect must not be null");
    Rect rect = new Rect();
    paramRectF.roundOut(rect);
    return rect;
  }
  
  public static Rect createRect(Size paramSize) {
    Preconditions.checkNotNull(paramSize, "size must not be null");
    return new Rect(0, 0, paramSize.getWidth(), paramSize.getHeight());
  }
  
  public static Size createSize(Rect paramRect) {
    Preconditions.checkNotNull(paramRect, "rect must not be null");
    return new Size(paramRect.width(), paramRect.height());
  }
  
  public static <T> T getOrDefault(CaptureRequest paramCaptureRequest, CaptureRequest.Key<T> paramKey, T paramT) {
    Preconditions.checkNotNull(paramCaptureRequest, "r must not be null");
    Preconditions.checkNotNull(paramKey, "key must not be null");
    Preconditions.checkNotNull(paramT, "defaultValue must not be null");
    Object object = paramCaptureRequest.get(paramKey);
    return (T)((object == null) ? (Object)paramT : object);
  }
  
  public static Rect mapRect(Matrix paramMatrix, Rect paramRect) {
    Preconditions.checkNotNull(paramMatrix, "transform must not be null");
    Preconditions.checkNotNull(paramRect, "rect must not be null");
    RectF rectF = new RectF(paramRect);
    paramMatrix.mapRect(rectF);
    return createRect(rectF);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/ParamsUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */