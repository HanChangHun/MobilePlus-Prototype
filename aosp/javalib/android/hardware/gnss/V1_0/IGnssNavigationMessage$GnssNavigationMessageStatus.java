package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssNavigationMessageStatus {
  public static final int ERROR_ALREADY_INIT = -100;
  
  public static final int ERROR_GENERIC = -101;
  
  public static final int SUCCESS = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("SUCCESS");
    if ((paramInt & 0xFFFFFF9C) == -100) {
      arrayList.add("ERROR_ALREADY_INIT");
      i = 0x0 | 0xFFFFFF9C;
    } 
    int j = i;
    if ((paramInt & 0xFFFFFF9B) == -101) {
      arrayList.add("ERROR_GENERIC");
      j = i | 0xFFFFFF9B;
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
      return "SUCCESS"; 
    if (paramInt == -100)
      return "ERROR_ALREADY_INIT"; 
    if (paramInt == -101)
      return "ERROR_GENERIC"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNavigationMessage$GnssNavigationMessageStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */