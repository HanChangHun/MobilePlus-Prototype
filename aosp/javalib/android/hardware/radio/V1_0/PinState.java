package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class PinState {
  public static final int DISABLED = 3;
  
  public static final int ENABLED_BLOCKED = 4;
  
  public static final int ENABLED_NOT_VERIFIED = 1;
  
  public static final int ENABLED_PERM_BLOCKED = 5;
  
  public static final int ENABLED_VERIFIED = 2;
  
  public static final int UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ENABLED_NOT_VERIFIED");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("ENABLED_VERIFIED");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("DISABLED");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("ENABLED_BLOCKED");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("ENABLED_PERM_BLOCKED");
      i = j | 0x5;
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
      return "ENABLED_NOT_VERIFIED"; 
    if (paramInt == 2)
      return "ENABLED_VERIFIED"; 
    if (paramInt == 3)
      return "DISABLED"; 
    if (paramInt == 4)
      return "ENABLED_BLOCKED"; 
    if (paramInt == 5)
      return "ENABLED_PERM_BLOCKED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/PinState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */