package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;
import java.util.Arrays;
import java.util.Objects;

public final class LensShadingMap {
  public static final float MINIMUM_GAIN_FACTOR = 1.0F;
  
  private final int mColumns;
  
  private final float[] mElements;
  
  private final int mRows;
  
  public LensShadingMap(float[] paramArrayOffloat, int paramInt1, int paramInt2) {
    this.mRows = Preconditions.checkArgumentPositive(paramInt1, "rows must be positive");
    this.mColumns = Preconditions.checkArgumentPositive(paramInt2, "columns must be positive");
    Objects.requireNonNull(paramArrayOffloat, "elements must not be null");
    this.mElements = paramArrayOffloat;
    if (paramArrayOffloat.length == getGainFactorCount()) {
      Preconditions.checkArrayElementsInRange(paramArrayOffloat, 1.0F, Float.MAX_VALUE, "elements");
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("elements must be ");
    stringBuilder.append(getGainFactorCount());
    stringBuilder.append(" length, received ");
    stringBuilder.append(paramArrayOffloat.length);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void copyGainFactors(float[] paramArrayOffloat, int paramInt) {
    Preconditions.checkArgumentNonnegative(paramInt, "offset must not be negative");
    Objects.requireNonNull(paramArrayOffloat, "destination must not be null");
    if (paramArrayOffloat.length + paramInt >= getGainFactorCount()) {
      System.arraycopy(this.mElements, 0, paramArrayOffloat, paramInt, getGainFactorCount());
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
    if (paramObject instanceof LensShadingMap) {
      paramObject = paramObject;
      if (this.mRows == ((LensShadingMap)paramObject).mRows && this.mColumns == ((LensShadingMap)paramObject).mColumns && Arrays.equals(this.mElements, ((LensShadingMap)paramObject).mElements))
        bool = true; 
      return bool;
    } 
    return false;
  }
  
  public int getColumnCount() {
    return this.mColumns;
  }
  
  public float getGainFactor(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 >= 0 && paramInt1 <= 4) {
      if (paramInt2 >= 0) {
        int i = this.mColumns;
        if (paramInt2 < i) {
          if (paramInt3 >= 0 && paramInt3 < this.mRows)
            return this.mElements[(i * paramInt3 + paramInt2) * 4 + paramInt1]; 
          throw new IllegalArgumentException("row out of range");
        } 
      } 
      throw new IllegalArgumentException("column out of range");
    } 
    throw new IllegalArgumentException("colorChannel out of range");
  }
  
  public int getGainFactorCount() {
    return this.mRows * this.mColumns * 4;
  }
  
  public RggbChannelVector getGainFactorVector(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0) {
      int i = this.mColumns;
      if (paramInt1 < i) {
        if (paramInt2 >= 0 && paramInt2 < this.mRows) {
          paramInt1 = (i * paramInt2 + paramInt1) * 4;
          float[] arrayOfFloat = this.mElements;
          return new RggbChannelVector(arrayOfFloat[paramInt1 + 0], arrayOfFloat[paramInt1 + 1], arrayOfFloat[paramInt1 + 2], arrayOfFloat[paramInt1 + 3]);
        } 
        throw new IllegalArgumentException("row out of range");
      } 
    } 
    throw new IllegalArgumentException("column out of range");
  }
  
  public int getRowCount() {
    return this.mRows;
  }
  
  public int hashCode() {
    int i = HashCodeHelpers.hashCode(this.mElements);
    return HashCodeHelpers.hashCode(new int[] { this.mRows, this.mColumns, i });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("LensShadingMap{");
    for (byte b = 0; b < 4; b++) {
      (new String[4])[0] = "R:(";
      (new String[4])[1] = "G_even:(";
      (new String[4])[2] = "G_odd:(";
      (new String[4])[3] = "B:(";
      stringBuilder.append((new String[4])[b]);
      for (byte b1 = 0; b1 < this.mRows; b1++) {
        stringBuilder.append("[");
        for (byte b2 = 0; b2 < this.mColumns; b2++) {
          stringBuilder.append(getGainFactor(b, b2, b1));
          if (b2 < this.mColumns - 1)
            stringBuilder.append(", "); 
        } 
        stringBuilder.append("]");
        if (b1 < this.mRows - 1)
          stringBuilder.append(", "); 
      } 
      stringBuilder.append(")");
      if (b < 3)
        stringBuilder.append(", "); 
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/LensShadingMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */