package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class AGnssRefLocationType {
  public static final byte GSM_CELLID = 1;
  
  public static final byte LTE_CELLID = 4;
  
  public static final byte UMTS_CELLID = 2;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    if ((paramByte & 0x1) == 1) {
      arrayList.add("GSM_CELLID");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("UMTS_CELLID");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("LTE_CELLID");
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
      return "GSM_CELLID"; 
    if (paramByte == 2)
      return "UMTS_CELLID"; 
    if (paramByte == 4)
      return "LTE_CELLID"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssRil$AGnssRefLocationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */