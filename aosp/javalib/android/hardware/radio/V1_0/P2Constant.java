package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class P2Constant {
  public static final int NO_P2 = -1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0xFFFFFFFF) == -1) {
      arrayList.add("NO_P2");
      i = 0x0 | 0xFFFFFFFF;
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
    if (paramInt == -1)
      return "NO_P2"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/P2Constant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */