package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssNiEncodingType {
  public static final int ENC_NONE = 0;
  
  public static final int ENC_SUPL_GSM_DEFAULT = 1;
  
  public static final int ENC_SUPL_UCS2 = 3;
  
  public static final int ENC_SUPL_UTF8 = 2;
  
  public static final int ENC_UNKNOWN = -1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("ENC_NONE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ENC_SUPL_GSM_DEFAULT");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("ENC_SUPL_UTF8");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("ENC_SUPL_UCS2");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0xFFFFFFFF) == -1) {
      arrayList.add("ENC_UNKNOWN");
      j = i | 0xFFFFFFFF;
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
      return "ENC_NONE"; 
    if (paramInt == 1)
      return "ENC_SUPL_GSM_DEFAULT"; 
    if (paramInt == 2)
      return "ENC_SUPL_UTF8"; 
    if (paramInt == 3)
      return "ENC_SUPL_UCS2"; 
    if (paramInt == -1)
      return "ENC_UNKNOWN"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNiCallback$GnssNiEncodingType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */