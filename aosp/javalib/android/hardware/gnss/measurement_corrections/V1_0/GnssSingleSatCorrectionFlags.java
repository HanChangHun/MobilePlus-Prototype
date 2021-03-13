package android.hardware.gnss.measurement_corrections.V1_0;

import java.util.ArrayList;

public final class GnssSingleSatCorrectionFlags {
  public static final short HAS_EXCESS_PATH_LENGTH = 2;
  
  public static final short HAS_EXCESS_PATH_LENGTH_UNC = 4;
  
  public static final short HAS_REFLECTING_PLANE = 8;
  
  public static final short HAS_SAT_IS_LOS_PROBABILITY = 1;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    if ((paramShort & 0x1) == 1) {
      arrayList.add("HAS_SAT_IS_LOS_PROBABILITY");
      s1 = (short)(false | true);
    } 
    short s2 = s1;
    if ((paramShort & 0x2) == 2) {
      arrayList.add("HAS_EXCESS_PATH_LENGTH");
      s2 = (short)(s1 | 0x2);
    } 
    s1 = s2;
    if ((paramShort & 0x4) == 4) {
      arrayList.add("HAS_EXCESS_PATH_LENGTH_UNC");
      s1 = (short)(s2 | 0x4);
    } 
    s2 = s1;
    if ((paramShort & 0x8) == 8) {
      arrayList.add("HAS_REFLECTING_PLANE");
      s2 = (short)(s1 | 0x8);
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
      return "HAS_SAT_IS_LOS_PROBABILITY"; 
    if (paramShort == 2)
      return "HAS_EXCESS_PATH_LENGTH"; 
    if (paramShort == 4)
      return "HAS_EXCESS_PATH_LENGTH_UNC"; 
    if (paramShort == 8)
      return "HAS_REFLECTING_PLANE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/measurement_corrections/V1_0/GnssSingleSatCorrectionFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */