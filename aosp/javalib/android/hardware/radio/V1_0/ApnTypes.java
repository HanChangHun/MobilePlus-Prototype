package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class ApnTypes {
  public static final int ALL = 1023;
  
  public static final int CBS = 128;
  
  public static final int DEFAULT = 1;
  
  public static final int DUN = 8;
  
  public static final int EMERGENCY = 512;
  
  public static final int FOTA = 32;
  
  public static final int HIPRI = 16;
  
  public static final int IA = 256;
  
  public static final int IMS = 64;
  
  public static final int MMS = 2;
  
  public static final int NONE = 0;
  
  public static final int SUPL = 4;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NONE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("DEFAULT");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("MMS");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("SUPL");
      i = j | 0x4;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("DUN");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("HIPRI");
      i = j | 0x10;
    } 
    int k = i;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("FOTA");
      k = i | 0x20;
    } 
    j = k;
    if ((paramInt & 0x40) == 64) {
      arrayList.add("IMS");
      j = k | 0x40;
    } 
    i = j;
    if ((paramInt & 0x80) == 128) {
      arrayList.add("CBS");
      i = j | 0x80;
    } 
    j = i;
    if ((paramInt & 0x100) == 256) {
      arrayList.add("IA");
      j = i | 0x100;
    } 
    i = j;
    if ((paramInt & 0x200) == 512) {
      arrayList.add("EMERGENCY");
      i = j | 0x200;
    } 
    j = i;
    if ((paramInt & 0x3FF) == 1023) {
      arrayList.add("ALL");
      j = i | 0x3FF;
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
      return "DEFAULT"; 
    if (paramInt == 2)
      return "MMS"; 
    if (paramInt == 4)
      return "SUPL"; 
    if (paramInt == 8)
      return "DUN"; 
    if (paramInt == 16)
      return "HIPRI"; 
    if (paramInt == 32)
      return "FOTA"; 
    if (paramInt == 64)
      return "IMS"; 
    if (paramInt == 128)
      return "CBS"; 
    if (paramInt == 256)
      return "IA"; 
    if (paramInt == 512)
      return "EMERGENCY"; 
    if (paramInt == 1023)
      return "ALL"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/ApnTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */