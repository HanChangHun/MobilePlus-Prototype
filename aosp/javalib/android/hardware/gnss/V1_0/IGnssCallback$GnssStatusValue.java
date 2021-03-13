package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssStatusValue {
  public static final byte ENGINE_OFF = 4;
  
  public static final byte ENGINE_ON = 3;
  
  public static final byte NONE = 0;
  
  public static final byte SESSION_BEGIN = 1;
  
  public static final byte SESSION_END = 2;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("NONE");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("SESSION_BEGIN");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("SESSION_END");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("ENGINE_ON");
      b1 = (byte)(b2 | 0x3);
    } 
    b2 = b1;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("ENGINE_OFF");
      b2 = (byte)(b1 | 0x4);
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
      return "SESSION_BEGIN"; 
    if (paramByte == 2)
      return "SESSION_END"; 
    if (paramByte == 3)
      return "ENGINE_ON"; 
    if (paramByte == 4)
      return "ENGINE_OFF"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssCallback$GnssStatusValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */