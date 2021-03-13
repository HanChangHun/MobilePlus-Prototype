package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SuppServiceClass {
  public static final int DATA = 2;
  
  public static final int DATA_ASYNC = 32;
  
  public static final int DATA_SYNC = 16;
  
  public static final int FAX = 4;
  
  public static final int MAX = 128;
  
  public static final int NONE = 0;
  
  public static final int PACKET = 64;
  
  public static final int PAD = 128;
  
  public static final int SMS = 8;
  
  public static final int VOICE = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NONE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("VOICE");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("DATA");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("FAX");
      i = j | 0x4;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("SMS");
      j = i | 0x8;
    } 
    int k = j;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("DATA_SYNC");
      k = j | 0x10;
    } 
    i = k;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("DATA_ASYNC");
      i = k | 0x20;
    } 
    j = i;
    if ((paramInt & 0x40) == 64) {
      arrayList.add("PACKET");
      j = i | 0x40;
    } 
    i = j;
    if ((paramInt & 0x80) == 128) {
      arrayList.add("PAD");
      i = j | 0x80;
    } 
    j = i;
    if ((paramInt & 0x80) == 128) {
      arrayList.add("MAX");
      j = i | 0x80;
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
      return "VOICE"; 
    if (paramInt == 2)
      return "DATA"; 
    if (paramInt == 4)
      return "FAX"; 
    if (paramInt == 8)
      return "SMS"; 
    if (paramInt == 16)
      return "DATA_SYNC"; 
    if (paramInt == 32)
      return "DATA_ASYNC"; 
    if (paramInt == 64)
      return "PACKET"; 
    if (paramInt == 128)
      return "PAD"; 
    if (paramInt == 128)
      return "MAX"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SuppServiceClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */