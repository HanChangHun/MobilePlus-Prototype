package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssNiType {
  public static final byte EMERGENCY_SUPL = 4;
  
  public static final byte UMTS_CTRL_PLANE = 3;
  
  public static final byte UMTS_SUPL = 2;
  
  public static final byte VOICE = 1;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    if ((paramByte & 0x1) == 1) {
      arrayList.add("VOICE");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("UMTS_SUPL");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("UMTS_CTRL_PLANE");
      b1 = (byte)(b2 | 0x3);
    } 
    b2 = b1;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("EMERGENCY_SUPL");
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
    if (paramByte == 1)
      return "VOICE"; 
    if (paramByte == 2)
      return "UMTS_SUPL"; 
    if (paramByte == 3)
      return "UMTS_CTRL_PLANE"; 
    if (paramByte == 4)
      return "EMERGENCY_SUPL"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNiCallback$GnssNiType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */