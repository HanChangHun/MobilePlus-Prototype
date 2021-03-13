package android.hardware.camera2.params;

import java.util.Arrays;
import java.util.Objects;

public final class BlackLevelPattern {
  public static final int COUNT = 4;
  
  private final int[] mCfaOffsets;
  
  public BlackLevelPattern(int[] paramArrayOfint) {
    if (paramArrayOfint != null) {
      if (paramArrayOfint.length >= 4) {
        this.mCfaOffsets = Arrays.copyOf(paramArrayOfint, 4);
        return;
      } 
      throw new IllegalArgumentException("Invalid offsets array length");
    } 
    throw new NullPointerException("Null offsets array passed to constructor");
  }
  
  public void copyTo(int[] paramArrayOfint, int paramInt) {
    Objects.requireNonNull(paramArrayOfint, "destination must not be null");
    if (paramInt >= 0) {
      if (paramArrayOfint.length - paramInt >= 4) {
        for (byte b = 0; b < 4; b++)
          paramArrayOfint[paramInt + b] = this.mCfaOffsets[b]; 
        return;
      } 
      throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
    } 
    throw new IllegalArgumentException("Null offset passed to copyTo");
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == null) ? false : ((this == paramObject) ? true : ((paramObject instanceof BlackLevelPattern) ? Arrays.equals(((BlackLevelPattern)paramObject).mCfaOffsets, this.mCfaOffsets) : false));
  }
  
  public int getOffsetForIndex(int paramInt1, int paramInt2) {
    if (paramInt2 >= 0 && paramInt1 >= 0)
      return this.mCfaOffsets[(paramInt2 & 0x1) << 1 | paramInt1 & 0x1]; 
    throw new IllegalArgumentException("column, row arguments must be positive");
  }
  
  public int hashCode() {
    return Arrays.hashCode(this.mCfaOffsets);
  }
  
  public String toString() {
    return String.format("BlackLevelPattern([%d, %d], [%d, %d])", new Object[] { Integer.valueOf(this.mCfaOffsets[0]), Integer.valueOf(this.mCfaOffsets[1]), Integer.valueOf(this.mCfaOffsets[2]), Integer.valueOf(this.mCfaOffsets[3]) });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/BlackLevelPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */