package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class AGnssType {
  public static final byte TYPE_C2K = 2;
  
  public static final byte TYPE_SUPL = 1;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    if ((paramByte & 0x1) == 1) {
      arrayList.add("TYPE_SUPL");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("TYPE_C2K");
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
    if (paramByte == 1)
      return "TYPE_SUPL"; 
    if (paramByte == 2)
      return "TYPE_C2K"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssCallback$AGnssType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */