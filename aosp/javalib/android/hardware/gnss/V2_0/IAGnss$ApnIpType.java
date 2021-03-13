package android.hardware.gnss.V2_0;

import java.util.ArrayList;

public final class ApnIpType {
  public static final byte INVALID = 0;
  
  public static final byte IPV4 = 1;
  
  public static final byte IPV4V6 = 3;
  
  public static final byte IPV6 = 2;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("INVALID");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("IPV4");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("IPV6");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("IPV4V6");
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
      return "INVALID"; 
    if (paramByte == 1)
      return "IPV4"; 
    if (paramByte == 2)
      return "IPV6"; 
    if (paramByte == 3)
      return "IPV4V6"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IAGnss$ApnIpType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */