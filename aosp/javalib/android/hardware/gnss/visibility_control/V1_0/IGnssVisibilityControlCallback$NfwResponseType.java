package android.hardware.gnss.visibility_control.V1_0;

import java.util.ArrayList;

public final class NfwResponseType {
  public static final byte ACCEPTED_LOCATION_PROVIDED = 2;
  
  public static final byte ACCEPTED_NO_LOCATION_PROVIDED = 1;
  
  public static final byte REJECTED = 0;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("REJECTED");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("ACCEPTED_NO_LOCATION_PROVIDED");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("ACCEPTED_LOCATION_PROVIDED");
      b2 = (byte)(b1 | 0x2);
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
      return "REJECTED"; 
    if (paramByte == 1)
      return "ACCEPTED_NO_LOCATION_PROVIDED"; 
    if (paramByte == 2)
      return "ACCEPTED_LOCATION_PROVIDED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/visibility_control/V1_0/IGnssVisibilityControlCallback$NfwResponseType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */