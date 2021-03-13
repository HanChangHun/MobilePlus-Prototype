package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaCallWaitingNumberPlan {
  public static final int DATA = 3;
  
  public static final int ISDN = 1;
  
  public static final int NATIONAL = 8;
  
  public static final int PRIVATE = 9;
  
  public static final int TELEX = 4;
  
  public static final int UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ISDN");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("DATA");
      j = i | 0x3;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("TELEX");
      i = j | 0x4;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("NATIONAL");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("PRIVATE");
      i = j | 0x9;
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
      return "UNKNOWN"; 
    if (paramInt == 1)
      return "ISDN"; 
    if (paramInt == 3)
      return "DATA"; 
    if (paramInt == 4)
      return "TELEX"; 
    if (paramInt == 8)
      return "NATIONAL"; 
    if (paramInt == 9)
      return "PRIVATE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaCallWaitingNumberPlan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */