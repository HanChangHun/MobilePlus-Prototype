package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SapResultCode {
  public static final int CARD_ALREADY_POWERED_OFF = 3;
  
  public static final int CARD_ALREADY_POWERED_ON = 5;
  
  public static final int CARD_NOT_ACCESSSIBLE = 2;
  
  public static final int CARD_REMOVED = 4;
  
  public static final int DATA_NOT_AVAILABLE = 6;
  
  public static final int GENERIC_FAILURE = 1;
  
  public static final int NOT_SUPPORTED = 7;
  
  public static final int SUCCESS = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("SUCCESS");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("GENERIC_FAILURE");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("CARD_NOT_ACCESSSIBLE");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("CARD_ALREADY_POWERED_OFF");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("CARD_REMOVED");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("CARD_ALREADY_POWERED_ON");
      i = j | 0x5;
    } 
    j = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("DATA_NOT_AVAILABLE");
      j = i | 0x6;
    } 
    i = j;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("NOT_SUPPORTED");
      i = j | 0x7;
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
      return "SUCCESS"; 
    if (paramInt == 1)
      return "GENERIC_FAILURE"; 
    if (paramInt == 2)
      return "CARD_NOT_ACCESSSIBLE"; 
    if (paramInt == 3)
      return "CARD_ALREADY_POWERED_OFF"; 
    if (paramInt == 4)
      return "CARD_REMOVED"; 
    if (paramInt == 5)
      return "CARD_ALREADY_POWERED_ON"; 
    if (paramInt == 6)
      return "DATA_NOT_AVAILABLE"; 
    if (paramInt == 7)
      return "NOT_SUPPORTED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SapResultCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */