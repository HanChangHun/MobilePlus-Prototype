package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class RadioAccessFamily {
  public static final int EDGE = 4;
  
  public static final int EHRPD = 8192;
  
  public static final int EVDO_0 = 128;
  
  public static final int EVDO_A = 256;
  
  public static final int EVDO_B = 4096;
  
  public static final int GPRS = 2;
  
  public static final int GSM = 65536;
  
  public static final int HSDPA = 512;
  
  public static final int HSPA = 2048;
  
  public static final int HSPAP = 32768;
  
  public static final int HSUPA = 1024;
  
  public static final int IS95A = 16;
  
  public static final int IS95B = 32;
  
  public static final int LTE = 16384;
  
  public static final int LTE_CA = 524288;
  
  public static final int ONE_X_RTT = 64;
  
  public static final int TD_SCDMA = 131072;
  
  public static final int UMTS = 8;
  
  public static final int UNKNOWN = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("UNKNOWN");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("GPRS");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("EDGE");
      i = j | 0x4;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("UMTS");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("IS95A");
      i = j | 0x10;
    } 
    j = i;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("IS95B");
      j = i | 0x20;
    } 
    int k = j;
    if ((paramInt & 0x40) == 64) {
      arrayList.add("ONE_X_RTT");
      k = j | 0x40;
    } 
    i = k;
    if ((paramInt & 0x80) == 128) {
      arrayList.add("EVDO_0");
      i = k | 0x80;
    } 
    j = i;
    if ((paramInt & 0x100) == 256) {
      arrayList.add("EVDO_A");
      j = i | 0x100;
    } 
    i = j;
    if ((paramInt & 0x200) == 512) {
      arrayList.add("HSDPA");
      i = j | 0x200;
    } 
    j = i;
    if ((paramInt & 0x400) == 1024) {
      arrayList.add("HSUPA");
      j = i | 0x400;
    } 
    i = j;
    if ((paramInt & 0x800) == 2048) {
      arrayList.add("HSPA");
      i = j | 0x800;
    } 
    j = i;
    if ((paramInt & 0x1000) == 4096) {
      arrayList.add("EVDO_B");
      j = i | 0x1000;
    } 
    k = j;
    if ((paramInt & 0x2000) == 8192) {
      arrayList.add("EHRPD");
      k = j | 0x2000;
    } 
    i = k;
    if ((paramInt & 0x4000) == 16384) {
      arrayList.add("LTE");
      i = k | 0x4000;
    } 
    j = i;
    if ((paramInt & 0x8000) == 32768) {
      arrayList.add("HSPAP");
      j = i | 0x8000;
    } 
    k = j;
    if ((paramInt & 0x10000) == 65536) {
      arrayList.add("GSM");
      k = j | 0x10000;
    } 
    i = k;
    if ((paramInt & 0x20000) == 131072) {
      arrayList.add("TD_SCDMA");
      i = k | 0x20000;
    } 
    j = i;
    if ((paramInt & 0x80000) == 524288) {
      arrayList.add("LTE_CA");
      j = i | 0x80000;
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
    if (paramInt == 1)
      return "UNKNOWN"; 
    if (paramInt == 2)
      return "GPRS"; 
    if (paramInt == 4)
      return "EDGE"; 
    if (paramInt == 8)
      return "UMTS"; 
    if (paramInt == 16)
      return "IS95A"; 
    if (paramInt == 32)
      return "IS95B"; 
    if (paramInt == 64)
      return "ONE_X_RTT"; 
    if (paramInt == 128)
      return "EVDO_0"; 
    if (paramInt == 256)
      return "EVDO_A"; 
    if (paramInt == 512)
      return "HSDPA"; 
    if (paramInt == 1024)
      return "HSUPA"; 
    if (paramInt == 2048)
      return "HSPA"; 
    if (paramInt == 4096)
      return "EVDO_B"; 
    if (paramInt == 8192)
      return "EHRPD"; 
    if (paramInt == 16384)
      return "LTE"; 
    if (paramInt == 32768)
      return "HSPAP"; 
    if (paramInt == 65536)
      return "GSM"; 
    if (paramInt == 131072)
      return "TD_SCDMA"; 
    if (paramInt == 524288)
      return "LTE_CA"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioAccessFamily.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */