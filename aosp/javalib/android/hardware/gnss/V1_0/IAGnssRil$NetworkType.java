package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class NetworkType {
  public static final byte DUN = 4;
  
  public static final byte HIPRI = 5;
  
  public static final byte MMS = 2;
  
  public static final byte MOBILE = 0;
  
  public static final byte SUPL = 3;
  
  public static final byte WIFI = 1;
  
  public static final byte WIMAX = 6;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("MOBILE");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("WIFI");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("MMS");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("SUPL");
      b1 = (byte)(b2 | 0x3);
    } 
    b2 = b1;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("DUN");
      b2 = (byte)(b1 | 0x4);
    } 
    b1 = b2;
    if ((paramByte & 0x5) == 5) {
      arrayList.add("HIPRI");
      b1 = (byte)(b2 | 0x5);
    } 
    b2 = b1;
    if ((paramByte & 0x6) == 6) {
      arrayList.add("WIMAX");
      b2 = (byte)(b1 | 0x6);
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
      return "MOBILE"; 
    if (paramByte == 1)
      return "WIFI"; 
    if (paramByte == 2)
      return "MMS"; 
    if (paramByte == 3)
      return "SUPL"; 
    if (paramByte == 4)
      return "DUN"; 
    if (paramByte == 5)
      return "HIPRI"; 
    if (paramByte == 6)
      return "WIMAX"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssRil$NetworkType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */