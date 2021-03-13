package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class RegState {
  public static final int NOT_REG_MT_NOT_SEARCHING_OP = 0;
  
  public static final int NOT_REG_MT_NOT_SEARCHING_OP_EM = 10;
  
  public static final int NOT_REG_MT_SEARCHING_OP = 2;
  
  public static final int NOT_REG_MT_SEARCHING_OP_EM = 12;
  
  public static final int REG_DENIED = 3;
  
  public static final int REG_DENIED_EM = 13;
  
  public static final int REG_HOME = 1;
  
  public static final int REG_ROAMING = 5;
  
  public static final int UNKNOWN = 4;
  
  public static final int UNKNOWN_EM = 14;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NOT_REG_MT_NOT_SEARCHING_OP");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("REG_HOME");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("NOT_REG_MT_SEARCHING_OP");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("REG_DENIED");
      i = j | 0x3;
    } 
    int k = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("UNKNOWN");
      k = i | 0x4;
    } 
    j = k;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("REG_ROAMING");
      j = k | 0x5;
    } 
    i = j;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("NOT_REG_MT_NOT_SEARCHING_OP_EM");
      i = j | 0xA;
    } 
    k = i;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("NOT_REG_MT_SEARCHING_OP_EM");
      k = i | 0xC;
    } 
    j = k;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("REG_DENIED_EM");
      j = k | 0xD;
    } 
    i = j;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("UNKNOWN_EM");
      i = j | 0xE;
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
      return "NOT_REG_MT_NOT_SEARCHING_OP"; 
    if (paramInt == 1)
      return "REG_HOME"; 
    if (paramInt == 2)
      return "NOT_REG_MT_SEARCHING_OP"; 
    if (paramInt == 3)
      return "REG_DENIED"; 
    if (paramInt == 4)
      return "UNKNOWN"; 
    if (paramInt == 5)
      return "REG_ROAMING"; 
    if (paramInt == 10)
      return "NOT_REG_MT_NOT_SEARCHING_OP_EM"; 
    if (paramInt == 12)
      return "NOT_REG_MT_SEARCHING_OP_EM"; 
    if (paramInt == 13)
      return "REG_DENIED_EM"; 
    if (paramInt == 14)
      return "UNKNOWN_EM"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RegState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */