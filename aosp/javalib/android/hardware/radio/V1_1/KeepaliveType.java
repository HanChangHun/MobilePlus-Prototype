package android.hardware.radio.V1_1;

import java.util.ArrayList;

public final class KeepaliveType {
  public static final int NATT_IPV4 = 0;
  
  public static final int NATT_IPV6 = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NATT_IPV4");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("NATT_IPV6");
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
      return "NATT_IPV4"; 
    if (paramInt == 1)
      return "NATT_IPV6"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/KeepaliveType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */