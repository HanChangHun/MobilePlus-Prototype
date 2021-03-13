package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class PreferredNetworkType {
  public static final int CDMA_EVDO_AUTO = 4;
  
  public static final int CDMA_ONLY = 5;
  
  public static final int EVDO_ONLY = 6;
  
  public static final int GSM_ONLY = 1;
  
  public static final int GSM_WCDMA = 0;
  
  public static final int GSM_WCDMA_AUTO = 3;
  
  public static final int GSM_WCDMA_CDMA_EVDO_AUTO = 7;
  
  public static final int LTE_CDMA_EVDO = 8;
  
  public static final int LTE_CMDA_EVDO_GSM_WCDMA = 10;
  
  public static final int LTE_GSM_WCDMA = 9;
  
  public static final int LTE_ONLY = 11;
  
  public static final int LTE_WCDMA = 12;
  
  public static final int TD_SCDMA_GSM = 16;
  
  public static final int TD_SCDMA_GSM_LTE = 17;
  
  public static final int TD_SCDMA_GSM_WCDMA = 18;
  
  public static final int TD_SCDMA_GSM_WCDMA_CDMA_EVDO_AUTO = 21;
  
  public static final int TD_SCDMA_GSM_WCDMA_LTE = 20;
  
  public static final int TD_SCDMA_LTE = 15;
  
  public static final int TD_SCDMA_LTE_CDMA_EVDO_GSM_WCDMA = 22;
  
  public static final int TD_SCDMA_ONLY = 13;
  
  public static final int TD_SCDMA_WCDMA = 14;
  
  public static final int TD_SCDMA_WCDMA_LTE = 19;
  
  public static final int WCDMA = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("GSM_WCDMA");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("GSM_ONLY");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("WCDMA");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("GSM_WCDMA_AUTO");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("CDMA_EVDO_AUTO");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("CDMA_ONLY");
      i = j | 0x5;
    } 
    int k = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("EVDO_ONLY");
      k = i | 0x6;
    } 
    j = k;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("GSM_WCDMA_CDMA_EVDO_AUTO");
      j = k | 0x7;
    } 
    i = j;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("LTE_CDMA_EVDO");
      i = j | 0x8;
    } 
    j = i;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("LTE_GSM_WCDMA");
      j = i | 0x9;
    } 
    i = j;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("LTE_CMDA_EVDO_GSM_WCDMA");
      i = j | 0xA;
    } 
    j = i;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("LTE_ONLY");
      j = i | 0xB;
    } 
    k = j;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("LTE_WCDMA");
      k = j | 0xC;
    } 
    i = k;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("TD_SCDMA_ONLY");
      i = k | 0xD;
    } 
    j = i;
    if ((paramInt & 0xE) == 14) {
      arrayList.add("TD_SCDMA_WCDMA");
      j = i | 0xE;
    } 
    i = j;
    if ((paramInt & 0xF) == 15) {
      arrayList.add("TD_SCDMA_LTE");
      i = j | 0xF;
    } 
    j = i;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("TD_SCDMA_GSM");
      j = i | 0x10;
    } 
    i = j;
    if ((paramInt & 0x11) == 17) {
      arrayList.add("TD_SCDMA_GSM_LTE");
      i = j | 0x11;
    } 
    j = i;
    if ((paramInt & 0x12) == 18) {
      arrayList.add("TD_SCDMA_GSM_WCDMA");
      j = i | 0x12;
    } 
    i = j;
    if ((paramInt & 0x13) == 19) {
      arrayList.add("TD_SCDMA_WCDMA_LTE");
      i = j | 0x13;
    } 
    j = i;
    if ((paramInt & 0x14) == 20) {
      arrayList.add("TD_SCDMA_GSM_WCDMA_LTE");
      j = i | 0x14;
    } 
    i = j;
    if ((paramInt & 0x15) == 21) {
      arrayList.add("TD_SCDMA_GSM_WCDMA_CDMA_EVDO_AUTO");
      i = j | 0x15;
    } 
    j = i;
    if ((paramInt & 0x16) == 22) {
      arrayList.add("TD_SCDMA_LTE_CDMA_EVDO_GSM_WCDMA");
      j = i | 0x16;
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
      return "GSM_WCDMA"; 
    if (paramInt == 1)
      return "GSM_ONLY"; 
    if (paramInt == 2)
      return "WCDMA"; 
    if (paramInt == 3)
      return "GSM_WCDMA_AUTO"; 
    if (paramInt == 4)
      return "CDMA_EVDO_AUTO"; 
    if (paramInt == 5)
      return "CDMA_ONLY"; 
    if (paramInt == 6)
      return "EVDO_ONLY"; 
    if (paramInt == 7)
      return "GSM_WCDMA_CDMA_EVDO_AUTO"; 
    if (paramInt == 8)
      return "LTE_CDMA_EVDO"; 
    if (paramInt == 9)
      return "LTE_GSM_WCDMA"; 
    if (paramInt == 10)
      return "LTE_CMDA_EVDO_GSM_WCDMA"; 
    if (paramInt == 11)
      return "LTE_ONLY"; 
    if (paramInt == 12)
      return "LTE_WCDMA"; 
    if (paramInt == 13)
      return "TD_SCDMA_ONLY"; 
    if (paramInt == 14)
      return "TD_SCDMA_WCDMA"; 
    if (paramInt == 15)
      return "TD_SCDMA_LTE"; 
    if (paramInt == 16)
      return "TD_SCDMA_GSM"; 
    if (paramInt == 17)
      return "TD_SCDMA_GSM_LTE"; 
    if (paramInt == 18)
      return "TD_SCDMA_GSM_WCDMA"; 
    if (paramInt == 19)
      return "TD_SCDMA_WCDMA_LTE"; 
    if (paramInt == 20)
      return "TD_SCDMA_GSM_WCDMA_LTE"; 
    if (paramInt == 21)
      return "TD_SCDMA_GSM_WCDMA_CDMA_EVDO_AUTO"; 
    if (paramInt == 22)
      return "TD_SCDMA_LTE_CDMA_EVDO_GSM_WCDMA"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/PreferredNetworkType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */