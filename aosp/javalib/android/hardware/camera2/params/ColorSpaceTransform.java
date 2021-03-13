package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import android.util.Rational;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

public final class ColorSpaceTransform {
  private static final int COLUMNS = 3;
  
  private static final int COUNT = 9;
  
  private static final int COUNT_INT = 18;
  
  private static final int OFFSET_DENOMINATOR = 1;
  
  private static final int OFFSET_NUMERATOR = 0;
  
  private static final int RATIONAL_SIZE = 2;
  
  private static final int ROWS = 3;
  
  private final int[] mElements;
  
  public ColorSpaceTransform(int[] paramArrayOfint) {
    Preconditions.checkNotNull(paramArrayOfint, "elements must not be null");
    if (paramArrayOfint.length == 18) {
      for (byte b = 0; b < paramArrayOfint.length; b++) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("element ");
        stringBuilder.append(b);
        stringBuilder.append(" must not be null");
        Preconditions.checkNotNull(paramArrayOfint, stringBuilder.toString());
      } 
      this.mElements = Arrays.copyOf(paramArrayOfint, paramArrayOfint.length);
      return;
    } 
    throw new IllegalArgumentException("elements must be 18 length");
  }
  
  public ColorSpaceTransform(Rational[] paramArrayOfRational) {
    Preconditions.checkNotNull(paramArrayOfRational, "elements must not be null");
    if (paramArrayOfRational.length == 9) {
      this.mElements = new int[18];
      for (byte b = 0; b < paramArrayOfRational.length; b++) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("element[");
        stringBuilder.append(b);
        stringBuilder.append("] must not be null");
        Preconditions.checkNotNull(paramArrayOfRational, stringBuilder.toString());
        this.mElements[b * 2 + 0] = paramArrayOfRational[b].getNumerator();
        this.mElements[b * 2 + 1] = paramArrayOfRational[b].getDenominator();
      } 
      return;
    } 
    throw new IllegalArgumentException("elements must be 9 length");
  }
  
  private String toShortString() {
    StringBuilder stringBuilder = new StringBuilder("(");
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < 3) {
      stringBuilder.append("[");
      byte b = 0;
      while (b < 3) {
        int[] arrayOfInt = this.mElements;
        int i = arrayOfInt[b2 + 0];
        int j = arrayOfInt[b2 + 1];
        stringBuilder.append(i);
        stringBuilder.append("/");
        stringBuilder.append(j);
        if (b < 2)
          stringBuilder.append(", "); 
        b++;
        b2 += 2;
      } 
      stringBuilder.append("]");
      if (b1 < 2)
        stringBuilder.append(", "); 
      b1++;
    } 
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public void copyElements(int[] paramArrayOfint, int paramInt) {
    Preconditions.checkArgumentNonnegative(paramInt, "offset must not be negative");
    Preconditions.checkNotNull(paramArrayOfint, "destination must not be null");
    if (paramArrayOfint.length - paramInt >= 18) {
      for (byte b = 0; b < 18; b++)
        paramArrayOfint[b + paramInt] = this.mElements[b]; 
      return;
    } 
    throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
  }
  
  public void copyElements(Rational[] paramArrayOfRational, int paramInt) {
    Preconditions.checkArgumentNonnegative(paramInt, "offset must not be negative");
    Preconditions.checkNotNull(paramArrayOfRational, "destination must not be null");
    if (paramArrayOfRational.length - paramInt >= 9) {
      byte b1 = 0;
      for (byte b2 = 0; b1 < 9; b2 += 2) {
        int[] arrayOfInt = this.mElements;
        paramArrayOfRational[b1 + paramInt] = new Rational(arrayOfInt[b2 + 0], arrayOfInt[b2 + 1]);
        b1++;
      } 
      return;
    } 
    throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof ColorSpaceTransform) {
      paramObject = paramObject;
      byte b1 = 0;
      for (byte b2 = 0; b1 < 9; b2 += 2) {
        int[] arrayOfInt = this.mElements;
        int i = arrayOfInt[b2 + 0];
        int j = arrayOfInt[b2 + 1];
        arrayOfInt = ((ColorSpaceTransform)paramObject).mElements;
        int k = arrayOfInt[b2 + 0];
        int m = arrayOfInt[b2 + 1];
        if (!(new Rational(i, j)).equals(new Rational(k, m)))
          return false; 
        b1++;
      } 
      return true;
    } 
    return false;
  }
  
  public Rational getElement(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0 && paramInt1 < 3) {
      if (paramInt2 >= 0 && paramInt2 < 3) {
        int[] arrayOfInt = this.mElements;
        return new Rational(arrayOfInt[(paramInt2 * 3 + paramInt1) * 2 + 0], arrayOfInt[(paramInt2 * 3 + paramInt1) * 2 + 1]);
      } 
      throw new IllegalArgumentException("row out of range");
    } 
    throw new IllegalArgumentException("column out of range");
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(this.mElements);
  }
  
  public String toString() {
    return String.format("ColorSpaceTransform%s", new Object[] { toShortString() });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/ColorSpaceTransform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */