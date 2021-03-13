package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaSmsNumberPlan {
  public static final int DATA = 3;
  
  public static final int PRIVATE = 9;
  
  public static final int RESERVED_10 = 10;
  
  public static final int RESERVED_11 = 11;
  
  public static final int RESERVED_12 = 12;
  
  public static final int RESERVED_13 = 13;
  
  public static final int RESERVED_14 = 14;
  
  public static final int RESERVED_15 = 15;
  
  public static final int RESERVED_2 = 2;
  
  public static final int RESERVED_5 = 5;
  
  public static final int RESERVED_6 = 6;
  
  public static final int RESERVED_7 = 7;
  
  public static final int RESERVED_8 = 8;
  
  public static final int TELEPHONY = 1;
  
  public static final int TELEX = 4;
  
  public static final int UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("TELEPHONY");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("RESERVED_2");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("DATA");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("TELEX");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("RESERVED_5");
      i = j | 0x5;
    } 
    int k = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("RESERVED_6");
      k = i | 0x6;
    } 
    j = k;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("RESERVED_7");
      j = k | 0x7;
    } 
    i = j;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("RESERVED_8");
      i = j | 0x8;
    } 
    j = i;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("PRIVATE");
      j = i | 0x9;
    } 
    k = j;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("RESERVED_10");
      k = j | 0xA;
    } 
    i = k;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("RESERVED_11");
      i = k | 0xB;
    } 
    j = i;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("RESERVED_12");
      j = i | 0xC;
    } 
    k = j;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("RESERVED_13");
      k = j | 0xD;
    } 
    i = k;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("RESERVED_14");
      i = k | 0xE;
    } 
    j = i;
    if ((paramInt & 0xF) == 15) {
      arrayList.add("RESERVED_15");
      j = i | 0xF;
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
      return "UNKNOWN"; 
    if (paramInt == 1)
      return "TELEPHONY"; 
    if (paramInt == 2)
      return "RESERVED_2"; 
    if (paramInt == 3)
      return "DATA"; 
    if (paramInt == 4)
      return "TELEX"; 
    if (paramInt == 5)
      return "RESERVED_5"; 
    if (paramInt == 6)
      return "RESERVED_6"; 
    if (paramInt == 7)
      return "RESERVED_7"; 
    if (paramInt == 8)
      return "RESERVED_8"; 
    if (paramInt == 9)
      return "PRIVATE"; 
    if (paramInt == 10)
      return "RESERVED_10"; 
    if (paramInt == 11)
      return "RESERVED_11"; 
    if (paramInt == 12)
      return "RESERVED_12"; 
    if (paramInt == 13)
      return "RESERVED_13"; 
    if (paramInt == 14)
      return "RESERVED_14"; 
    if (paramInt == 15)
      return "RESERVED_15"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsNumberPlan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */