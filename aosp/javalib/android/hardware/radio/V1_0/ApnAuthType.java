package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class ApnAuthType {
  public static final int NO_PAP_CHAP = 2;
  
  public static final int NO_PAP_NO_CHAP = 0;
  
  public static final int PAP_CHAP = 3;
  
  public static final int PAP_NO_CHAP = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NO_PAP_NO_CHAP");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("PAP_NO_CHAP");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("NO_PAP_CHAP");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("PAP_CHAP");
      i = j | 0x3;
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
      return "NO_PAP_NO_CHAP"; 
    if (paramInt == 1)
      return "PAP_NO_CHAP"; 
    if (paramInt == 2)
      return "NO_PAP_CHAP"; 
    if (paramInt == 3)
      return "PAP_CHAP"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/ApnAuthType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */