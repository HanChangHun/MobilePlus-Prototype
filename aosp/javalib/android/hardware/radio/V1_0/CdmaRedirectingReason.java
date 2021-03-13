package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaRedirectingReason {
  public static final int CALLED_DTE_OUT_OF_ORDER = 9;
  
  public static final int CALL_FORWARDING_BUSY = 1;
  
  public static final int CALL_FORWARDING_BY_THE_CALLED_DTE = 10;
  
  public static final int CALL_FORWARDING_NO_REPLY = 2;
  
  public static final int CALL_FORWARDING_UNCONDITIONAL = 15;
  
  public static final int RESERVED = 16;
  
  public static final int UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("CALL_FORWARDING_BUSY");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("CALL_FORWARDING_NO_REPLY");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("CALLED_DTE_OUT_OF_ORDER");
      i = j | 0x9;
    } 
    int k = i;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("CALL_FORWARDING_BY_THE_CALLED_DTE");
      k = i | 0xA;
    } 
    j = k;
    if ((paramInt & 0xF) == 15) {
      arrayList.add("CALL_FORWARDING_UNCONDITIONAL");
      j = k | 0xF;
    } 
    i = j;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("RESERVED");
      i = j | 0x10;
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
      return "CALL_FORWARDING_BUSY"; 
    if (paramInt == 2)
      return "CALL_FORWARDING_NO_REPLY"; 
    if (paramInt == 9)
      return "CALLED_DTE_OUT_OF_ORDER"; 
    if (paramInt == 10)
      return "CALL_FORWARDING_BY_THE_CALLED_DTE"; 
    if (paramInt == 15)
      return "CALL_FORWARDING_UNCONDITIONAL"; 
    if (paramInt == 16)
      return "RESERVED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaRedirectingReason.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */