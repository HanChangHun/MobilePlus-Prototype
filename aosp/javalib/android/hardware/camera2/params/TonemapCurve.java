package android.hardware.camera2.params;

import android.graphics.PointF;
import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

public final class TonemapCurve {
  public static final int CHANNEL_BLUE = 2;
  
  public static final int CHANNEL_GREEN = 1;
  
  public static final int CHANNEL_RED = 0;
  
  public static final float LEVEL_BLACK = 0.0F;
  
  public static final float LEVEL_WHITE = 1.0F;
  
  private static final int MIN_CURVE_LENGTH = 4;
  
  private static final int OFFSET_POINT_IN = 0;
  
  private static final int OFFSET_POINT_OUT = 1;
  
  public static final int POINT_SIZE = 2;
  
  private static final int TONEMAP_MIN_CURVE_POINTS = 2;
  
  private final float[] mBlue;
  
  private final float[] mGreen;
  
  private boolean mHashCalculated = false;
  
  private int mHashCode;
  
  private final float[] mRed;
  
  public TonemapCurve(float[] paramArrayOffloat1, float[] paramArrayOffloat2, float[] paramArrayOffloat3) {
    Preconditions.checkNotNull(paramArrayOffloat1, "red must not be null");
    Preconditions.checkNotNull(paramArrayOffloat2, "green must not be null");
    Preconditions.checkNotNull(paramArrayOffloat3, "blue must not be null");
    checkArgumentArrayLengthDivisibleBy(paramArrayOffloat1, 2, "red");
    checkArgumentArrayLengthDivisibleBy(paramArrayOffloat2, 2, "green");
    checkArgumentArrayLengthDivisibleBy(paramArrayOffloat3, 2, "blue");
    checkArgumentArrayLengthNoLessThan(paramArrayOffloat1, 4, "red");
    checkArgumentArrayLengthNoLessThan(paramArrayOffloat2, 4, "green");
    checkArgumentArrayLengthNoLessThan(paramArrayOffloat3, 4, "blue");
    Preconditions.checkArrayElementsInRange(paramArrayOffloat1, 0.0F, 1.0F, "red");
    Preconditions.checkArrayElementsInRange(paramArrayOffloat2, 0.0F, 1.0F, "green");
    Preconditions.checkArrayElementsInRange(paramArrayOffloat3, 0.0F, 1.0F, "blue");
    this.mRed = Arrays.copyOf(paramArrayOffloat1, paramArrayOffloat1.length);
    this.mGreen = Arrays.copyOf(paramArrayOffloat2, paramArrayOffloat2.length);
    this.mBlue = Arrays.copyOf(paramArrayOffloat3, paramArrayOffloat3.length);
  }
  
  private static void checkArgumentArrayLengthDivisibleBy(float[] paramArrayOffloat, int paramInt, String paramString) {
    if (paramArrayOffloat.length % paramInt == 0)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" size must be divisible by ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static void checkArgumentArrayLengthNoLessThan(float[] paramArrayOffloat, int paramInt, String paramString) {
    if (paramArrayOffloat.length >= paramInt)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" size must be at least ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static int checkArgumentColorChannel(int paramInt) {
    if (paramInt == 0 || paramInt == 1 || paramInt == 2)
      return paramInt; 
    throw new IllegalArgumentException("colorChannel out of range");
  }
  
  private String curveToString(int paramInt) {
    checkArgumentColorChannel(paramInt);
    StringBuilder stringBuilder = new StringBuilder("[");
    float[] arrayOfFloat = getCurve(paramInt);
    int i = arrayOfFloat.length / 2;
    byte b = 0;
    for (paramInt = 0; b < i; paramInt += 2) {
      stringBuilder.append("(");
      stringBuilder.append(arrayOfFloat[paramInt]);
      stringBuilder.append(", ");
      stringBuilder.append(arrayOfFloat[paramInt + 1]);
      stringBuilder.append("), ");
      b++;
    } 
    stringBuilder.setLength(stringBuilder.length() - 2);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  private float[] getCurve(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt == 2)
          return this.mBlue; 
        throw new AssertionError("colorChannel out of range");
      } 
      return this.mGreen;
    } 
    return this.mRed;
  }
  
  public void copyColorCurve(int paramInt1, float[] paramArrayOffloat, int paramInt2) {
    Preconditions.checkArgumentNonnegative(paramInt2, "offset must not be negative");
    Preconditions.checkNotNull(paramArrayOffloat, "destination must not be null");
    if (paramArrayOffloat.length + paramInt2 >= getPointCount(paramInt1) * 2) {
      float[] arrayOfFloat = getCurve(paramInt1);
      System.arraycopy(arrayOfFloat, 0, paramArrayOffloat, paramInt2, arrayOfFloat.length);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof TonemapCurve) {
      paramObject = paramObject;
      if (Arrays.equals(this.mRed, ((TonemapCurve)paramObject).mRed) && Arrays.equals(this.mGreen, ((TonemapCurve)paramObject).mGreen) && Arrays.equals(this.mBlue, ((TonemapCurve)paramObject).mBlue))
        bool = true; 
      return bool;
    } 
    return false;
  }
  
  public PointF getPoint(int paramInt1, int paramInt2) {
    checkArgumentColorChannel(paramInt1);
    if (paramInt2 >= 0 && paramInt2 < getPointCount(paramInt1)) {
      float[] arrayOfFloat = getCurve(paramInt1);
      return new PointF(arrayOfFloat[paramInt2 * 2 + 0], arrayOfFloat[paramInt2 * 2 + 1]);
    } 
    throw new IllegalArgumentException("index out of range");
  }
  
  public int getPointCount(int paramInt) {
    checkArgumentColorChannel(paramInt);
    return (getCurve(paramInt)).length / 2;
  }
  
  public int hashCode() {
    if (this.mHashCalculated)
      return this.mHashCode; 
    int i = HashCodeHelpers.hashCodeGeneric((Object[])new float[][] { this.mRed, this.mGreen, this.mBlue });
    this.mHashCode = i;
    this.mHashCalculated = true;
    return i;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("TonemapCurve{");
    stringBuilder.append("R:");
    stringBuilder.append(curveToString(0));
    stringBuilder.append(", G:");
    stringBuilder.append(curveToString(1));
    stringBuilder.append(", B:");
    stringBuilder.append(curveToString(2));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/TonemapCurve.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */