package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class NavigationMessageStatus {
  public static final short PARITY_PASSED = 1;
  
  public static final short PARITY_REBUILT = 2;
  
  public static final short UNKNOWN = 0;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    if ((paramShort & 0x1) == 1) {
      arrayList.add("PARITY_PASSED");
      s1 = (short)(false | true);
    } 
    short s2 = s1;
    if ((paramShort & 0x2) == 2) {
      arrayList.add("PARITY_REBUILT");
      s2 = (short)(s1 | 0x2);
    } 
    arrayList.add("UNKNOWN");
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
      return "PARITY_PASSED"; 
    if (paramShort == 2)
      return "PARITY_REBUILT"; 
    if (paramShort == 0)
      return "UNKNOWN"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNavigationMessageCallback$NavigationMessageStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */