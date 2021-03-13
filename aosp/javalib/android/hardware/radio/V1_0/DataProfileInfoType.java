package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class DataProfileInfoType {
  public static final int COMMON = 0;
  
  public static final int THREE_GPP = 1;
  
  public static final int THREE_GPP2 = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("COMMON");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("THREE_GPP");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("THREE_GPP2");
      j = i | 0x2;
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
      return "COMMON"; 
    if (paramInt == 1)
      return "THREE_GPP"; 
    if (paramInt == 2)
      return "THREE_GPP2"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/DataProfileInfoType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */