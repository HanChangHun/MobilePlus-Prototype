package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class Flag {
  public static final byte WAKEUP_ON_FIFO_FULL = 1;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b = 0;
    if ((paramByte & 0x1) == 1) {
      arrayList.add("WAKEUP_ON_FIFO_FULL");
      b = (byte)(false | true);
    } 
    if (paramByte != b) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b & paramByte))));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(byte paramByte) {
    if (paramByte == 1)
      return "WAKEUP_ON_FIFO_FULL"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssBatching$Flag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */