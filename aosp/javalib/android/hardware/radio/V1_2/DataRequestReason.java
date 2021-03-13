package android.hardware.radio.V1_2;

import java.util.ArrayList;

public final class DataRequestReason {
  public static final int HANDOVER = 3;
  
  public static final int NORMAL = 1;
  
  public static final int SHUTDOWN = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("NORMAL");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("SHUTDOWN");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("HANDOVER");
      i = j | 0x3;
    } 
    if (paramInt != i) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(i & paramInt));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(int paramInt) {
    if (paramInt == 1)
      return "NORMAL"; 
    if (paramInt == 2)
      return "SHUTDOWN"; 
    if (paramInt == 3)
      return "HANDOVER"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/DataRequestReason.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */