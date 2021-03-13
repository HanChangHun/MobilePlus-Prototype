package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class RadioBandMode {
  public static final int BAND_MODE_10_800M_2 = 15;
  
  public static final int BAND_MODE_5_450M = 10;
  
  public static final int BAND_MODE_7_700M_2 = 12;
  
  public static final int BAND_MODE_8_1800M = 13;
  
  public static final int BAND_MODE_9_900M = 14;
  
  public static final int BAND_MODE_AUS = 4;
  
  public static final int BAND_MODE_AUS_2 = 5;
  
  public static final int BAND_MODE_AWS = 17;
  
  public static final int BAND_MODE_CELL_800 = 6;
  
  public static final int BAND_MODE_EURO = 1;
  
  public static final int BAND_MODE_EURO_PAMR_400M = 16;
  
  public static final int BAND_MODE_IMT2000 = 11;
  
  public static final int BAND_MODE_JPN = 3;
  
  public static final int BAND_MODE_JTACS = 8;
  
  public static final int BAND_MODE_KOREA_PCS = 9;
  
  public static final int BAND_MODE_PCS = 7;
  
  public static final int BAND_MODE_UNSPECIFIED = 0;
  
  public static final int BAND_MODE_USA = 2;
  
  public static final int BAND_MODE_USA_2500M = 18;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("BAND_MODE_UNSPECIFIED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("BAND_MODE_EURO");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("BAND_MODE_USA");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("BAND_MODE_JPN");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("BAND_MODE_AUS");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("BAND_MODE_AUS_2");
      i = j | 0x5;
    } 
    j = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("BAND_MODE_CELL_800");
      j = i | 0x6;
    } 
    i = j;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("BAND_MODE_PCS");
      i = j | 0x7;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("BAND_MODE_JTACS");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("BAND_MODE_KOREA_PCS");
      i = j | 0x9;
    } 
    j = i;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("BAND_MODE_5_450M");
      j = i | 0xA;
    } 
    i = j;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("BAND_MODE_IMT2000");
      i = j | 0xB;
    } 
    j = i;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("BAND_MODE_7_700M_2");
      j = i | 0xC;
    } 
    i = j;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("BAND_MODE_8_1800M");
      i = j | 0xD;
    } 
    j = i;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("BAND_MODE_9_900M");
      j = i | 0xE;
    } 
    i = j;
    if ((paramInt & 0xF) == 15) {
      arrayList.add("BAND_MODE_10_800M_2");
      i = j | 0xF;
    } 
    int k = i;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("BAND_MODE_EURO_PAMR_400M");
      k = i | 0x10;
    } 
    j = k;
    if ((paramInt & 0x11) == 17) {
      arrayList.add("BAND_MODE_AWS");
      j = k | 0x11;
    } 
    i = j;
    if ((paramInt & 0x12) == 18) {
      arrayList.add("BAND_MODE_USA_2500M");
      i = j | 0x12;
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
      return "BAND_MODE_UNSPECIFIED"; 
    if (paramInt == 1)
      return "BAND_MODE_EURO"; 
    if (paramInt == 2)
      return "BAND_MODE_USA"; 
    if (paramInt == 3)
      return "BAND_MODE_JPN"; 
    if (paramInt == 4)
      return "BAND_MODE_AUS"; 
    if (paramInt == 5)
      return "BAND_MODE_AUS_2"; 
    if (paramInt == 6)
      return "BAND_MODE_CELL_800"; 
    if (paramInt == 7)
      return "BAND_MODE_PCS"; 
    if (paramInt == 8)
      return "BAND_MODE_JTACS"; 
    if (paramInt == 9)
      return "BAND_MODE_KOREA_PCS"; 
    if (paramInt == 10)
      return "BAND_MODE_5_450M"; 
    if (paramInt == 11)
      return "BAND_MODE_IMT2000"; 
    if (paramInt == 12)
      return "BAND_MODE_7_700M_2"; 
    if (paramInt == 13)
      return "BAND_MODE_8_1800M"; 
    if (paramInt == 14)
      return "BAND_MODE_9_900M"; 
    if (paramInt == 15)
      return "BAND_MODE_10_800M_2"; 
    if (paramInt == 16)
      return "BAND_MODE_EURO_PAMR_400M"; 
    if (paramInt == 17)
      return "BAND_MODE_AWS"; 
    if (paramInt == 18)
      return "BAND_MODE_USA_2500M"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioBandMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */