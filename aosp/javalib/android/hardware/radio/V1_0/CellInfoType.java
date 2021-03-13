package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CellInfoType {
  public static final int CDMA = 2;
  
  public static final int GSM = 1;
  
  public static final int LTE = 3;
  
  public static final int NONE = 0;
  
  public static final int TD_SCDMA = 5;
  
  public static final int WCDMA = 4;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NONE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("GSM");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("CDMA");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("LTE");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("WCDMA");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("TD_SCDMA");
      i = j | 0x5;
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
      return "NONE"; 
    if (paramInt == 1)
      return "GSM"; 
    if (paramInt == 2)
      return "CDMA"; 
    if (paramInt == 3)
      return "LTE"; 
    if (paramInt == 4)
      return "WCDMA"; 
    if (paramInt == 5)
      return "TD_SCDMA"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CellInfoType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */