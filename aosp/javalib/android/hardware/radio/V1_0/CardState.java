package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CardState {
  public static final int ABSENT = 0;
  
  public static final int ERROR = 2;
  
  public static final int PRESENT = 1;
  
  public static final int RESTRICTED = 3;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("ABSENT");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("PRESENT");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("ERROR");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("RESTRICTED");
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
      return "ABSENT"; 
    if (paramInt == 1)
      return "PRESENT"; 
    if (paramInt == 2)
      return "ERROR"; 
    if (paramInt == 3)
      return "RESTRICTED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CardState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */