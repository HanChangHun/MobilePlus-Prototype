package android.hardware.radio.V1_2;

import java.util.ArrayList;

public final class IncrementalResultsPeriodicityRange {
  public static final int MAX = 10;
  
  public static final int MIN = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("MIN");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("MAX");
      j = i | 0xA;
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
    if (paramInt == 1)
      return "MIN"; 
    if (paramInt == 10)
      return "MAX"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/IncrementalResultsPeriodicityRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */