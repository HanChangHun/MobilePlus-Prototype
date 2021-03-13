package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SsServiceType {
  public static final int ALL_BARRING = 16;
  
  public static final int BAIC = 14;
  
  public static final int BAIC_ROAMING = 15;
  
  public static final int BAOC = 11;
  
  public static final int BAOIC = 12;
  
  public static final int BAOIC_EXC_HOME = 13;
  
  public static final int CFU = 0;
  
  public static final int CF_ALL = 4;
  
  public static final int CF_ALL_CONDITIONAL = 5;
  
  public static final int CF_BUSY = 1;
  
  public static final int CF_NOT_REACHABLE = 3;
  
  public static final int CF_NO_REPLY = 2;
  
  public static final int CLIP = 6;
  
  public static final int CLIR = 7;
  
  public static final int COLP = 8;
  
  public static final int COLR = 9;
  
  public static final int INCOMING_BARRING = 18;
  
  public static final int OUTGOING_BARRING = 17;
  
  public static final int WAIT = 10;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("CFU");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("CF_BUSY");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("CF_NO_REPLY");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("CF_NOT_REACHABLE");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("CF_ALL");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("CF_ALL_CONDITIONAL");
      i = j | 0x5;
    } 
    j = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("CLIP");
      j = i | 0x6;
    } 
    int k = j;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("CLIR");
      k = j | 0x7;
    } 
    i = k;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("COLP");
      i = k | 0x8;
    } 
    j = i;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("COLR");
      j = i | 0x9;
    } 
    i = j;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("WAIT");
      i = j | 0xA;
    } 
    j = i;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("BAOC");
      j = i | 0xB;
    } 
    i = j;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("BAOIC");
      i = j | 0xC;
    } 
    k = i;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("BAOIC_EXC_HOME");
      k = i | 0xD;
    } 
    j = k;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("BAIC");
      j = k | 0xE;
    } 
    i = j;
    if ((paramInt & 0xF) == 15) {
      arrayList.add("BAIC_ROAMING");
      i = j | 0xF;
    } 
    j = i;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("ALL_BARRING");
      j = i | 0x10;
    } 
    i = j;
    if ((paramInt & 0x11) == 17) {
      arrayList.add("OUTGOING_BARRING");
      i = j | 0x11;
    } 
    j = i;
    if ((paramInt & 0x12) == 18) {
      arrayList.add("INCOMING_BARRING");
      j = i | 0x12;
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
      return "CFU"; 
    if (paramInt == 1)
      return "CF_BUSY"; 
    if (paramInt == 2)
      return "CF_NO_REPLY"; 
    if (paramInt == 3)
      return "CF_NOT_REACHABLE"; 
    if (paramInt == 4)
      return "CF_ALL"; 
    if (paramInt == 5)
      return "CF_ALL_CONDITIONAL"; 
    if (paramInt == 6)
      return "CLIP"; 
    if (paramInt == 7)
      return "CLIR"; 
    if (paramInt == 8)
      return "COLP"; 
    if (paramInt == 9)
      return "COLR"; 
    if (paramInt == 10)
      return "WAIT"; 
    if (paramInt == 11)
      return "BAOC"; 
    if (paramInt == 12)
      return "BAOIC"; 
    if (paramInt == 13)
      return "BAOIC_EXC_HOME"; 
    if (paramInt == 14)
      return "BAIC"; 
    if (paramInt == 15)
      return "BAIC_ROAMING"; 
    if (paramInt == 16)
      return "ALL_BARRING"; 
    if (paramInt == 17)
      return "OUTGOING_BARRING"; 
    if (paramInt == 18)
      return "INCOMING_BARRING"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SsServiceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */