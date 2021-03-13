package android.hardware.gnss.V2_0;

import java.util.ArrayList;

public final class GnssConstellationType {
  public static final byte BEIDOU = 5;
  
  public static final byte GALILEO = 6;
  
  public static final byte GLONASS = 3;
  
  public static final byte GPS = 1;
  
  public static final byte IRNSS = 7;
  
  public static final byte QZSS = 4;
  
  public static final byte SBAS = 2;
  
  public static final byte UNKNOWN = 0;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("UNKNOWN");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("GPS");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("SBAS");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("GLONASS");
      b1 = (byte)(b2 | 0x3);
    } 
    b2 = b1;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("QZSS");
      b2 = (byte)(b1 | 0x4);
    } 
    b1 = b2;
    if ((paramByte & 0x5) == 5) {
      arrayList.add("BEIDOU");
      b1 = (byte)(b2 | 0x5);
    } 
    b2 = b1;
    if ((paramByte & 0x6) == 6) {
      arrayList.add("GALILEO");
      b2 = (byte)(b1 | 0x6);
    } 
    b1 = b2;
    if ((paramByte & 0x7) == 7) {
      arrayList.add("IRNSS");
      b1 = (byte)(b2 | 0x7);
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
      return "UNKNOWN"; 
    if (paramByte == 1)
      return "GPS"; 
    if (paramByte == 2)
      return "SBAS"; 
    if (paramByte == 3)
      return "GLONASS"; 
    if (paramByte == 4)
      return "QZSS"; 
    if (paramByte == 5)
      return "BEIDOU"; 
    if (paramByte == 6)
      return "GALILEO"; 
    if (paramByte == 7)
      return "IRNSS"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/GnssConstellationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */