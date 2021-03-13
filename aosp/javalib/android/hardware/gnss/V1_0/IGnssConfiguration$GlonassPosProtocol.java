package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GlonassPosProtocol {
  public static final byte LPP_UPLANE = 4;
  
  public static final byte RRC_CPLANE = 1;
  
  public static final byte RRLP_CPLANE = 2;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    if ((paramByte & 0x1) == 1) {
      arrayList.add("RRC_CPLANE");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("RRLP_CPLANE");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("LPP_UPLANE");
      b1 = (byte)(b2 | 0x4);
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
      return "RRC_CPLANE"; 
    if (paramByte == 2)
      return "RRLP_CPLANE"; 
    if (paramByte == 4)
      return "LPP_UPLANE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssConfiguration$GlonassPosProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */