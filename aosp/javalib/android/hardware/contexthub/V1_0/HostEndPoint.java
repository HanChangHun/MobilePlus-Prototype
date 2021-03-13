package android.hardware.contexthub.V1_0;

import java.util.ArrayList;

public final class HostEndPoint {
  public static final short BROADCAST = -1;
  
  public static final short UNSPECIFIED = -2;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    if ((paramShort & 0xFFFFFFFF) == -1) {
      arrayList.add("BROADCAST");
      s1 = (short)(0x0 | 0xFFFFFFFF);
    } 
    short s2 = s1;
    if ((paramShort & 0xFFFFFFFE) == -2) {
      arrayList.add("UNSPECIFIED");
      s2 = (short)(s1 | 0xFFFFFFFE);
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
    if (paramShort == -1)
      return "BROADCAST"; 
    if (paramShort == -2)
      return "UNSPECIFIED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/HostEndPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */