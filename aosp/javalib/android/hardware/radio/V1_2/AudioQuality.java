package android.hardware.radio.V1_2;

import java.util.ArrayList;

public final class AudioQuality {
  public static final int AMR = 1;
  
  public static final int AMR_WB = 2;
  
  public static final int EVRC = 6;
  
  public static final int EVRC_B = 7;
  
  public static final int EVRC_NW = 9;
  
  public static final int EVRC_WB = 8;
  
  public static final int GSM_EFR = 3;
  
  public static final int GSM_FR = 4;
  
  public static final int GSM_HR = 5;
  
  public static final int UNSPECIFIED = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNSPECIFIED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("AMR");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("AMR_WB");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("GSM_EFR");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("GSM_FR");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("GSM_HR");
      i = j | 0x5;
    } 
    j = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("EVRC");
      j = i | 0x6;
    } 
    i = j;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("EVRC_B");
      i = j | 0x7;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("EVRC_WB");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("EVRC_NW");
      i = j | 0x9;
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
      return "UNSPECIFIED"; 
    if (paramInt == 1)
      return "AMR"; 
    if (paramInt == 2)
      return "AMR_WB"; 
    if (paramInt == 3)
      return "GSM_EFR"; 
    if (paramInt == 4)
      return "GSM_FR"; 
    if (paramInt == 5)
      return "GSM_HR"; 
    if (paramInt == 6)
      return "EVRC"; 
    if (paramInt == 7)
      return "EVRC_B"; 
    if (paramInt == 8)
      return "EVRC_WB"; 
    if (paramInt == 9)
      return "EVRC_NW"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/AudioQuality.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */