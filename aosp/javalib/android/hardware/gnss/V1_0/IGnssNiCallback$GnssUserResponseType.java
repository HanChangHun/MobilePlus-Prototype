package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssUserResponseType {
  public static final byte RESPONSE_ACCEPT = 1;
  
  public static final byte RESPONSE_DENY = 2;
  
  public static final byte RESPONSE_NORESP = 3;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    if ((paramByte & 0x1) == 1) {
      arrayList.add("RESPONSE_ACCEPT");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("RESPONSE_DENY");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("RESPONSE_NORESP");
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
    if (paramByte == 1)
      return "RESPONSE_ACCEPT"; 
    if (paramByte == 2)
      return "RESPONSE_DENY"; 
    if (paramByte == 3)
      return "RESPONSE_NORESP"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNiCallback$GnssUserResponseType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */