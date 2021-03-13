package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class RadioCapabilityPhase {
  public static final int APPLY = 2;
  
  public static final int CONFIGURED = 0;
  
  public static final int FINISH = 4;
  
  public static final int START = 1;
  
  public static final int UNSOL_RSP = 3;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("CONFIGURED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("START");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("APPLY");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("UNSOL_RSP");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("FINISH");
      j = i | 0x4;
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
      return "CONFIGURED"; 
    if (paramInt == 1)
      return "START"; 
    if (paramInt == 2)
      return "APPLY"; 
    if (paramInt == 3)
      return "UNSOL_RSP"; 
    if (paramInt == 4)
      return "FINISH"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioCapabilityPhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */