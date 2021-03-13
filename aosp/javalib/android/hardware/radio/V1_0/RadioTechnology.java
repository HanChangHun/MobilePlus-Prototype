package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class RadioTechnology {
  public static final int EDGE = 2;
  
  public static final int EHRPD = 13;
  
  public static final int EVDO_0 = 7;
  
  public static final int EVDO_A = 8;
  
  public static final int EVDO_B = 12;
  
  public static final int GPRS = 1;
  
  public static final int GSM = 16;
  
  public static final int HSDPA = 9;
  
  public static final int HSPA = 11;
  
  public static final int HSPAP = 15;
  
  public static final int HSUPA = 10;
  
  public static final int IS95A = 4;
  
  public static final int IS95B = 5;
  
  public static final int IWLAN = 18;
  
  public static final int LTE = 14;
  
  public static final int LTE_CA = 19;
  
  public static final int ONE_X_RTT = 6;
  
  public static final int TD_SCDMA = 17;
  
  public static final int UMTS = 3;
  
  public static final int UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("GPRS");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("EDGE");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("UMTS");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("IS95A");
      j = i | 0x4;
    } 
    int k = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("IS95B");
      k = j | 0x5;
    } 
    i = k;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("ONE_X_RTT");
      i = k | 0x6;
    } 
    j = i;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("EVDO_0");
      j = i | 0x7;
    } 
    i = j;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("EVDO_A");
      i = j | 0x8;
    } 
    j = i;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("HSDPA");
      j = i | 0x9;
    } 
    k = j;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("HSUPA");
      k = j | 0xA;
    } 
    i = k;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("HSPA");
      i = k | 0xB;
    } 
    j = i;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("EVDO_B");
      j = i | 0xC;
    } 
    i = j;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("EHRPD");
      i = j | 0xD;
    } 
    j = i;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("LTE");
      j = i | 0xE;
    } 
    i = j;
    if ((paramInt & 0xF) == 15) {
      arrayList.add("HSPAP");
      i = j | 0xF;
    } 
    k = i;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("GSM");
      k = i | 0x10;
    } 
    j = k;
    if ((paramInt & 0x11) == 17) {
      arrayList.add("TD_SCDMA");
      j = k | 0x11;
    } 
    i = j;
    if ((paramInt & 0x12) == 18) {
      arrayList.add("IWLAN");
      i = j | 0x12;
    } 
    j = i;
    if ((paramInt & 0x13) == 19) {
      arrayList.add("LTE_CA");
      j = i | 0x13;
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
      return "GPRS"; 
    if (paramInt == 2)
      return "EDGE"; 
    if (paramInt == 3)
      return "UMTS"; 
    if (paramInt == 4)
      return "IS95A"; 
    if (paramInt == 5)
      return "IS95B"; 
    if (paramInt == 6)
      return "ONE_X_RTT"; 
    if (paramInt == 7)
      return "EVDO_0"; 
    if (paramInt == 8)
      return "EVDO_A"; 
    if (paramInt == 9)
      return "HSDPA"; 
    if (paramInt == 10)
      return "HSUPA"; 
    if (paramInt == 11)
      return "HSPA"; 
    if (paramInt == 12)
      return "EVDO_B"; 
    if (paramInt == 13)
      return "EHRPD"; 
    if (paramInt == 14)
      return "LTE"; 
    if (paramInt == 15)
      return "HSPAP"; 
    if (paramInt == 16)
      return "GSM"; 
    if (paramInt == 17)
      return "TD_SCDMA"; 
    if (paramInt == 18)
      return "IWLAN"; 
    if (paramInt == 19)
      return "LTE_CA"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioTechnology.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */