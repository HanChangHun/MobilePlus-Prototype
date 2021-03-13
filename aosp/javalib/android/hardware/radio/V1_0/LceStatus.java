package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class LceStatus {
  public static final int ACTIVE = 2;
  
  public static final int NOT_SUPPORTED = 0;
  
  public static final int STOPPED = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NOT_SUPPORTED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("STOPPED");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("ACTIVE");
      j = i | 0x2;
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
    if (paramInt == 0)
      return "NOT_SUPPORTED"; 
    if (paramInt == 1)
      return "STOPPED"; 
    if (paramInt == 2)
      return "ACTIVE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/LceStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */