package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SapStatus {
  public static final int CARD_INSERTED = 4;
  
  public static final int CARD_NOT_ACCESSIBLE = 2;
  
  public static final int CARD_REMOVED = 3;
  
  public static final int CARD_RESET = 1;
  
  public static final int RECOVERED = 5;
  
  public static final int UNKNOWN_ERROR = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN_ERROR");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("CARD_RESET");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("CARD_NOT_ACCESSIBLE");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("CARD_REMOVED");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("CARD_INSERTED");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("RECOVERED");
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
      return "UNKNOWN_ERROR"; 
    if (paramInt == 1)
      return "CARD_RESET"; 
    if (paramInt == 2)
      return "CARD_NOT_ACCESSIBLE"; 
    if (paramInt == 3)
      return "CARD_REMOVED"; 
    if (paramInt == 4)
      return "CARD_INSERTED"; 
    if (paramInt == 5)
      return "RECOVERED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SapStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */