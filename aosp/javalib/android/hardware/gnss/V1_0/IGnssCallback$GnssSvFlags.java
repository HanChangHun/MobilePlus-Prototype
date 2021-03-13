package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssSvFlags {
  public static final byte HAS_ALMANAC_DATA = 2;
  
  public static final byte HAS_CARRIER_FREQUENCY = 8;
  
  public static final byte HAS_EPHEMERIS_DATA = 1;
  
  public static final byte NONE = 0;
  
  public static final byte USED_IN_FIX = 4;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("NONE");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("HAS_EPHEMERIS_DATA");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("HAS_ALMANAC_DATA");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("USED_IN_FIX");
      b1 = (byte)(b2 | 0x4);
    } 
    b2 = b1;
    if ((paramByte & 0x8) == 8) {
      arrayList.add("HAS_CARRIER_FREQUENCY");
      b2 = (byte)(b1 | 0x8);
    } 
    if (paramByte != b2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b2 & paramByte))));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(byte paramByte) {
    if (paramByte == 0)
      return "NONE"; 
    if (paramByte == 1)
      return "HAS_EPHEMERIS_DATA"; 
    if (paramByte == 2)
      return "HAS_ALMANAC_DATA"; 
    if (paramByte == 4)
      return "USED_IN_FIX"; 
    if (paramByte == 8)
      return "HAS_CARRIER_FREQUENCY"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssCallback$GnssSvFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */