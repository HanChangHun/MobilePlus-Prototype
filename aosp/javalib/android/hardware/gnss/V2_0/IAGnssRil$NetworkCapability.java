package android.hardware.gnss.V2_0;

import java.util.ArrayList;

public final class NetworkCapability {
  public static final short NOT_METERED = 1;
  
  public static final short NOT_ROAMING = 2;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    if ((paramShort & 0x1) == 1) {
      arrayList.add("NOT_METERED");
      s1 = (short)(false | true);
    } 
    short s2 = s1;
    if ((paramShort & 0x2) == 2) {
      arrayList.add("NOT_ROAMING");
      s2 = (short)(s1 | 0x2);
    } 
    if (paramShort != s2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Short.toUnsignedInt((short)(s2 & paramShort))));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(short paramShort) {
    if (paramShort == 1)
      return "NOT_METERED"; 
    if (paramShort == 2)
      return "NOT_ROAMING"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IAGnssRil$NetworkCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */