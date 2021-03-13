package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssPositionMode {
  public static final byte MS_ASSISTED = 2;
  
  public static final byte MS_BASED = 1;
  
  public static final byte STANDALONE = 0;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("STANDALONE");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("MS_BASED");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("MS_ASSISTED");
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
      return "STANDALONE"; 
    if (paramByte == 1)
      return "MS_BASED"; 
    if (paramByte == 2)
      return "MS_ASSISTED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnss$GnssPositionMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */