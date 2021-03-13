package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;
import java.util.Arrays;

public final class ReprocessFormatsMap {
  private final int[] mEntry;
  
  private final int mInputCount;
  
  public ReprocessFormatsMap(int[] paramArrayOfint) {
    Preconditions.checkNotNull(paramArrayOfint, "entry must not be null");
    byte b = 0;
    int i = paramArrayOfint.length;
    int j = 0;
    while (j < paramArrayOfint.length) {
      int k = StreamConfigurationMap.checkArgumentFormatInternal(paramArrayOfint[j]);
      int m = i - 1;
      i = j + 1;
      if (m >= 1) {
        int n = paramArrayOfint[i];
        m--;
        int i1 = i + 1;
        for (i = 0; i < n; i++)
          StreamConfigurationMap.checkArgumentFormatInternal(paramArrayOfint[i1 + i]); 
        i = m;
        j = i1;
        if (n > 0)
          if (m >= n) {
            j = i1 + n;
            i = m - n;
          } else {
            throw new IllegalArgumentException(String.format("Input %x had too few output formats listed (actual: %d, expected: %d)", new Object[] { Integer.valueOf(k), Integer.valueOf(m), Integer.valueOf(n) }));
          }  
        b++;
        continue;
      } 
      throw new IllegalArgumentException(String.format("Input %x had no output format length listed", new Object[] { Integer.valueOf(k) }));
    } 
    this.mEntry = paramArrayOfint;
    this.mInputCount = b;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof ReprocessFormatsMap) {
      paramObject = paramObject;
      return Arrays.equals(this.mEntry, ((ReprocessFormatsMap)paramObject).mEntry);
    } 
    return false;
  }
  
  public int[] getInputs() {
    int[] arrayOfInt = new int[this.mInputCount];
    int i = this.mEntry.length;
    int j = 0;
    byte b = 0;
    while (true) {
      int[] arrayOfInt1 = this.mEntry;
      if (j < arrayOfInt1.length) {
        int k = arrayOfInt1[j];
        i--;
        j++;
        if (i >= 1) {
          int m = arrayOfInt1[j];
          int n = i - 1;
          int i1 = j + 1;
          i = n;
          j = i1;
          if (m > 0)
            if (n >= m) {
              j = i1 + m;
              i = n - m;
            } else {
              throw new AssertionError(String.format("Input %x had too few output formats listed (actual: %d, expected: %d)", new Object[] { Integer.valueOf(k), Integer.valueOf(n), Integer.valueOf(m) }));
            }  
          arrayOfInt[b] = k;
          b++;
          continue;
        } 
        throw new AssertionError(String.format("Input %x had no output format length listed", new Object[] { Integer.valueOf(k) }));
      } 
      return StreamConfigurationMap.imageFormatToPublic(arrayOfInt);
    } 
  }
  
  public int[] getOutputs(int paramInt) {
    int i = this.mEntry.length;
    int j = 0;
    while (true) {
      int[] arrayOfInt = this.mEntry;
      if (j < arrayOfInt.length) {
        int k = arrayOfInt[j];
        int m = i - 1;
        j++;
        if (m >= 1) {
          i = arrayOfInt[j];
          m--;
          j++;
          if (i <= 0 || m >= i) {
            if (k == paramInt) {
              arrayOfInt = new int[i];
              for (paramInt = 0; paramInt < i; paramInt++)
                arrayOfInt[paramInt] = this.mEntry[j + paramInt]; 
              return StreamConfigurationMap.imageFormatToPublic(arrayOfInt);
            } 
            j += i;
            i = m - i;
            continue;
          } 
          throw new AssertionError(String.format("Input %x had too few output formats listed (actual: %d, expected: %d)", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(m), Integer.valueOf(i) }));
        } 
        throw new AssertionError(String.format("Input %x had no output format length listed", new Object[] { Integer.valueOf(paramInt) }));
      } 
      throw new IllegalArgumentException(String.format("Input format %x was not one in #getInputs", new Object[] { Integer.valueOf(paramInt) }));
    } 
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(this.mEntry);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/ReprocessFormatsMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */