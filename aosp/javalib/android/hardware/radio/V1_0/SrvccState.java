package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SrvccState {
  public static final int HANDOVER_CANCELED = 3;
  
  public static final int HANDOVER_COMPLETED = 1;
  
  public static final int HANDOVER_FAILED = 2;
  
  public static final int HANDOVER_STARTED = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("HANDOVER_STARTED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("HANDOVER_COMPLETED");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("HANDOVER_FAILED");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("HANDOVER_CANCELED");
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
    if (paramInt == 0)
      return "HANDOVER_STARTED"; 
    if (paramInt == 1)
      return "HANDOVER_COMPLETED"; 
    if (paramInt == 2)
      return "HANDOVER_FAILED"; 
    if (paramInt == 3)
      return "HANDOVER_CANCELED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SrvccState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */