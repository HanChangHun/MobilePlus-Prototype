package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssPositionRecurrence {
  public static final int RECURRENCE_PERIODIC = 0;
  
  public static final int RECURRENCE_SINGLE = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("RECURRENCE_PERIODIC");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("RECURRENCE_SINGLE");
      i = false | true;
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
    if (paramInt == 0)
      return "RECURRENCE_PERIODIC"; 
    if (paramInt == 1)
      return "RECURRENCE_SINGLE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnss$GnssPositionRecurrence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */