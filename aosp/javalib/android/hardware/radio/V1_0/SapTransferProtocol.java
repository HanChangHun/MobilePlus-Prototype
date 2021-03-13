package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SapTransferProtocol {
  public static final int T0 = 0;
  
  public static final int T1 = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("T0");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("T1");
      i = false | true;
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
      return "T0"; 
    if (paramInt == 1)
      return "T1"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SapTransferProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */