package android.hardware.gnss.V2_0;

import java.util.ArrayList;

public final class AGnssType {
  public static final byte C2K = 2;
  
  public static final byte SUPL = 1;
  
  public static final byte SUPL_EIMS = 3;
  
  public static final byte SUPL_IMS = 4;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    if ((paramByte & 0x1) == 1) {
      arrayList.add("SUPL");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("C2K");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("SUPL_EIMS");
      b1 = (byte)(b2 | 0x3);
    } 
    b2 = b1;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("SUPL_IMS");
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
      return "SUPL"; 
    if (paramByte == 2)
      return "C2K"; 
    if (paramByte == 3)
      return "SUPL_EIMS"; 
    if (paramByte == 4)
      return "SUPL_IMS"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IAGnssCallback$AGnssType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */