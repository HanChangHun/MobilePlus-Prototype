package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssLocationFlags {
  public static final short HAS_ALTITUDE = 2;
  
  public static final short HAS_BEARING = 8;
  
  public static final short HAS_BEARING_ACCURACY = 128;
  
  public static final short HAS_HORIZONTAL_ACCURACY = 16;
  
  public static final short HAS_LAT_LONG = 1;
  
  public static final short HAS_SPEED = 4;
  
  public static final short HAS_SPEED_ACCURACY = 64;
  
  public static final short HAS_VERTICAL_ACCURACY = 32;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    if ((paramShort & 0x1) == 1) {
      arrayList.add("HAS_LAT_LONG");
      s1 = (short)(false | true);
    } 
    short s2 = s1;
    if ((paramShort & 0x2) == 2) {
      arrayList.add("HAS_ALTITUDE");
      s2 = (short)(s1 | 0x2);
    } 
    s1 = s2;
    if ((paramShort & 0x4) == 4) {
      arrayList.add("HAS_SPEED");
      s1 = (short)(s2 | 0x4);
    } 
    s2 = s1;
    if ((paramShort & 0x8) == 8) {
      arrayList.add("HAS_BEARING");
      s2 = (short)(s1 | 0x8);
    } 
    s1 = s2;
    if ((paramShort & 0x10) == 16) {
      arrayList.add("HAS_HORIZONTAL_ACCURACY");
      s1 = (short)(s2 | 0x10);
    } 
    s2 = s1;
    if ((paramShort & 0x20) == 32) {
      arrayList.add("HAS_VERTICAL_ACCURACY");
      s2 = (short)(s1 | 0x20);
    } 
    s1 = s2;
    if ((paramShort & 0x40) == 64) {
      arrayList.add("HAS_SPEED_ACCURACY");
      s1 = (short)(s2 | 0x40);
    } 
    s2 = s1;
    if ((paramShort & 0x80) == 128) {
      arrayList.add("HAS_BEARING_ACCURACY");
      s2 = (short)(s1 | 0x80);
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
      return "HAS_LAT_LONG"; 
    if (paramShort == 2)
      return "HAS_ALTITUDE"; 
    if (paramShort == 4)
      return "HAS_SPEED"; 
    if (paramShort == 8)
      return "HAS_BEARING"; 
    if (paramShort == 16)
      return "HAS_HORIZONTAL_ACCURACY"; 
    if (paramShort == 32)
      return "HAS_VERTICAL_ACCURACY"; 
    if (paramShort == 64)
      return "HAS_SPEED_ACCURACY"; 
    if (paramShort == 128)
      return "HAS_BEARING_ACCURACY"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/GnssLocationFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */