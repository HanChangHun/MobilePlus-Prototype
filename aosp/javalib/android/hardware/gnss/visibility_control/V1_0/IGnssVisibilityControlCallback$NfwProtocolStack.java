package android.hardware.gnss.visibility_control.V1_0;

import java.util.ArrayList;

public final class NfwProtocolStack {
  public static final byte CTRL_PLANE = 0;
  
  public static final byte IMS = 10;
  
  public static final byte OTHER_PROTOCOL_STACK = 100;
  
  public static final byte SIM = 11;
  
  public static final byte SUPL = 1;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("CTRL_PLANE");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("SUPL");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0xA) == 10) {
      arrayList.add("IMS");
      b2 = (byte)(b1 | 0xA);
    } 
    b1 = b2;
    if ((paramByte & 0xB) == 11) {
      arrayList.add("SIM");
      b1 = (byte)(b2 | 0xB);
    } 
    b2 = b1;
    if ((paramByte & 0x64) == 100) {
      arrayList.add("OTHER_PROTOCOL_STACK");
      b2 = (byte)(b1 | 0x64);
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
      return "CTRL_PLANE"; 
    if (paramByte == 1)
      return "SUPL"; 
    if (paramByte == 10)
      return "IMS"; 
    if (paramByte == 11)
      return "SIM"; 
    if (paramByte == 100)
      return "OTHER_PROTOCOL_STACK"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/visibility_control/V1_0/IGnssVisibilityControlCallback$NfwProtocolStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */