package android.hardware.camera2.params;

import com.android.internal.util.Preconditions;

public final class RggbChannelVector {
  public static final int BLUE = 3;
  
  public static final int COUNT = 4;
  
  public static final int GREEN_EVEN = 1;
  
  public static final int GREEN_ODD = 2;
  
  public static final int RED = 0;
  
  private final float mBlue;
  
  private final float mGreenEven;
  
  private final float mGreenOdd;
  
  private final float mRed;
  
  public RggbChannelVector(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    this.mRed = Preconditions.checkArgumentFinite(paramFloat1, "red");
    this.mGreenEven = Preconditions.checkArgumentFinite(paramFloat2, "greenEven");
    this.mGreenOdd = Preconditions.checkArgumentFinite(paramFloat3, "greenOdd");
    this.mBlue = Preconditions.checkArgumentFinite(paramFloat4, "blue");
  }
  
  private String toShortString() {
    return String.format("{R:%f, G_even:%f, G_odd:%f, B:%f}", new Object[] { Float.valueOf(this.mRed), Float.valueOf(this.mGreenEven), Float.valueOf(this.mGreenOdd), Float.valueOf(this.mBlue) });
  }
  
  public void copyTo(float[] paramArrayOffloat, int paramInt) {
    Preconditions.checkNotNull(paramArrayOffloat, "destination must not be null");
    if (paramArrayOffloat.length - paramInt >= 4) {
      paramArrayOffloat[paramInt + 0] = this.mRed;
      paramArrayOffloat[paramInt + 1] = this.mGreenEven;
      paramArrayOffloat[paramInt + 2] = this.mGreenOdd;
      paramArrayOffloat[paramInt + 3] = this.mBlue;
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
    if (paramObject instanceof RggbChannelVector) {
      paramObject = paramObject;
      boolean bool1 = bool;
      if (this.mRed == ((RggbChannelVector)paramObject).mRed) {
        bool1 = bool;
        if (this.mGreenEven == ((RggbChannelVector)paramObject).mGreenEven) {
          bool1 = bool;
          if (this.mGreenOdd == ((RggbChannelVector)paramObject).mGreenOdd) {
            bool1 = bool;
            if (this.mBlue == ((RggbChannelVector)paramObject).mBlue)
              bool1 = true; 
          } 
        } 
      } 
      return bool1;
    } 
    return false;
  }
  
  public float getBlue() {
    return this.mBlue;
  }
  
  public float getComponent(int paramInt) {
    if (paramInt >= 0 && paramInt < 4) {
      if (paramInt != 0) {
        if (paramInt != 1) {
          if (paramInt != 2) {
            if (paramInt == 3)
              return this.mBlue; 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unhandled case ");
            stringBuilder.append(paramInt);
            throw new AssertionError(stringBuilder.toString());
          } 
          return this.mGreenOdd;
        } 
        return this.mGreenEven;
      } 
      return this.mRed;
    } 
    throw new IllegalArgumentException("Color channel out of range");
  }
  
  public float getGreenEven() {
    return this.mGreenEven;
  }
  
  public float getGreenOdd() {
    return this.mGreenOdd;
  }
  
  public final float getRed() {
    return this.mRed;
  }
  
  public int hashCode() {
    return Float.floatToIntBits(this.mRed) ^ Float.floatToIntBits(this.mGreenEven) ^ Float.floatToIntBits(this.mGreenOdd) ^ Float.floatToIntBits(this.mBlue);
  }
  
  public String toString() {
    return String.format("RggbChannelVector%s", new Object[] { toShortString() });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/RggbChannelVector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */