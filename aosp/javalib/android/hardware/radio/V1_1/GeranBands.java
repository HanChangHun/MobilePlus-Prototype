package android.hardware.radio.V1_1;

import java.util.ArrayList;

public final class GeranBands {
  public static final int BAND_450 = 3;
  
  public static final int BAND_480 = 4;
  
  public static final int BAND_710 = 5;
  
  public static final int BAND_750 = 6;
  
  public static final int BAND_850 = 8;
  
  public static final int BAND_DCS1800 = 12;
  
  public static final int BAND_E900 = 10;
  
  public static final int BAND_ER900 = 14;
  
  public static final int BAND_P900 = 9;
  
  public static final int BAND_PCS1900 = 13;
  
  public static final int BAND_R900 = 11;
  
  public static final int BAND_T380 = 1;
  
  public static final int BAND_T410 = 2;
  
  public static final int BAND_T810 = 7;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("BAND_T380");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("BAND_T410");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("BAND_450");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("BAND_480");
      j = i | 0x4;
    } 
    int k = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("BAND_710");
      k = j | 0x5;
    } 
    i = k;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("BAND_750");
      i = k | 0x6;
    } 
    j = i;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("BAND_T810");
      j = i | 0x7;
    } 
    i = j;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("BAND_850");
      i = j | 0x8;
    } 
    j = i;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("BAND_P900");
      j = i | 0x9;
    } 
    i = j;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("BAND_E900");
      i = j | 0xA;
    } 
    j = i;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("BAND_R900");
      j = i | 0xB;
    } 
    i = j;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("BAND_DCS1800");
      i = j | 0xC;
    } 
    j = i;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("BAND_PCS1900");
      j = i | 0xD;
    } 
    i = j;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("BAND_ER900");
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
    if (paramInt == 1)
      return "BAND_T380"; 
    if (paramInt == 2)
      return "BAND_T410"; 
    if (paramInt == 3)
      return "BAND_450"; 
    if (paramInt == 4)
      return "BAND_480"; 
    if (paramInt == 5)
      return "BAND_710"; 
    if (paramInt == 6)
      return "BAND_750"; 
    if (paramInt == 7)
      return "BAND_T810"; 
    if (paramInt == 8)
      return "BAND_850"; 
    if (paramInt == 9)
      return "BAND_P900"; 
    if (paramInt == 10)
      return "BAND_E900"; 
    if (paramInt == 11)
      return "BAND_R900"; 
    if (paramInt == 12)
      return "BAND_DCS1800"; 
    if (paramInt == 13)
      return "BAND_PCS1900"; 
    if (paramInt == 14)
      return "BAND_ER900"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/GeranBands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */