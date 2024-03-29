package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssMax {
  public static final int SVS_COUNT = 64;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x40) == 64) {
      arrayList.add("SVS_COUNT");
      i = 0x0 | 0x40;
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
    if (paramInt == 64)
      return "SVS_COUNT"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/GnssMax.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */