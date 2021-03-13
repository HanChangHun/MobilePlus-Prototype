package android.hardware.radio.V1_2;

import java.util.ArrayList;

public final class CellConnectionStatus {
  public static final int NONE = 0;
  
  public static final int PRIMARY_SERVING = 1;
  
  public static final int SECONDARY_SERVING = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NONE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("PRIMARY_SERVING");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("SECONDARY_SERVING");
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
      return "NONE"; 
    if (paramInt == 1)
      return "PRIMARY_SERVING"; 
    if (paramInt == 2)
      return "SECONDARY_SERVING"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/CellConnectionStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */