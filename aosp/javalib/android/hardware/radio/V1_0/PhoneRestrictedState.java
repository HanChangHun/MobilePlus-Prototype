package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class PhoneRestrictedState {
  public static final int CS_ALL = 4;
  
  public static final int CS_EMERGENCY = 1;
  
  public static final int CS_NORMAL = 2;
  
  public static final int NONE = 0;
  
  public static final int PS_ALL = 16;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NONE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("CS_EMERGENCY");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("CS_NORMAL");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("CS_ALL");
      i = j | 0x4;
    } 
    j = i;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("PS_ALL");
      j = i | 0x10;
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
      return "CS_EMERGENCY"; 
    if (paramInt == 2)
      return "CS_NORMAL"; 
    if (paramInt == 4)
      return "CS_ALL"; 
    if (paramInt == 16)
      return "PS_ALL"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/PhoneRestrictedState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */