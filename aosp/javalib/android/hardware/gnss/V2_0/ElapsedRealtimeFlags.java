package android.hardware.gnss.V2_0;

import java.util.ArrayList;

public final class ElapsedRealtimeFlags {
  public static final short HAS_TIMESTAMP_NS = 1;
  
  public static final short HAS_TIME_UNCERTAINTY_NS = 2;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    if ((paramShort & 0x1) == 1) {
      arrayList.add("HAS_TIMESTAMP_NS");
      s1 = (short)(false | true);
    } 
    short s2 = s1;
    if ((paramShort & 0x2) == 2) {
      arrayList.add("HAS_TIME_UNCERTAINTY_NS");
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
      return "HAS_TIMESTAMP_NS"; 
    if (paramShort == 2)
      return "HAS_TIME_UNCERTAINTY_NS"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/ElapsedRealtimeFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */