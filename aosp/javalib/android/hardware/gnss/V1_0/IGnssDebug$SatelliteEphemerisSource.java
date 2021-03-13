package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class SatelliteEphemerisSource {
  public static final byte DEMODULATED = 0;
  
  public static final byte OTHER = 3;
  
  public static final byte OTHER_SERVER_PROVIDED = 2;
  
  public static final byte SUPL_PROVIDED = 1;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("DEMODULATED");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("SUPL_PROVIDED");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("OTHER_SERVER_PROVIDED");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("OTHER");
      b1 = (byte)(b2 | 0x3);
    } 
    if (paramByte != b1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b1 & paramByte))));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(byte paramByte) {
    if (paramByte == 0)
      return "DEMODULATED"; 
    if (paramByte == 1)
      return "SUPL_PROVIDED"; 
    if (paramByte == 2)
      return "OTHER_SERVER_PROVIDED"; 
    if (paramByte == 3)
      return "OTHER"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssDebug$SatelliteEphemerisSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */