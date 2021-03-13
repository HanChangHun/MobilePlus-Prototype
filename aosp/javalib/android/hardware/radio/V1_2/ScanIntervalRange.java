package android.hardware.radio.V1_2;

import java.util.ArrayList;

public final class ScanIntervalRange {
  public static final int MAX = 300;
  
  public static final int MIN = 5;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("MIN");
      i = 0x0 | 0x5;
    } 
    int j = i;
    if ((paramInt & 0x12C) == 300) {
      arrayList.add("MAX");
      j = i | 0x12C;
    } 
    if (paramInt != j) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(j & paramInt));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(int paramInt) {
    if (paramInt == 5)
      return "MIN"; 
    if (paramInt == 300)
      return "MAX"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/ScanIntervalRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */