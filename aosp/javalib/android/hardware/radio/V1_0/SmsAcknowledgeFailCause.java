package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SmsAcknowledgeFailCause {
  public static final int MEMORY_CAPACITY_EXCEEDED = 211;
  
  public static final int UNSPECIFIED_ERROR = 255;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0xD3) == 211) {
      arrayList.add("MEMORY_CAPACITY_EXCEEDED");
      i = Character.MIN_VALUE | 0xD3;
    } 
    int j = i;
    if ((paramInt & 0xFF) == 255) {
      arrayList.add("UNSPECIFIED_ERROR");
      j = i | 0xFF;
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
    if (paramInt == 211)
      return "MEMORY_CAPACITY_EXCEEDED"; 
    if (paramInt == 255)
      return "UNSPECIFIED_ERROR"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SmsAcknowledgeFailCause.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */