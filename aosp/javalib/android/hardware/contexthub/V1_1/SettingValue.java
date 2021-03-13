package android.hardware.contexthub.V1_1;

import java.util.ArrayList;

public final class SettingValue {
  public static final byte DISABLED = 0;
  
  public static final byte ENABLED = 1;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b = 0;
    arrayList.add("DISABLED");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("ENABLED");
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
    if (paramByte == 0)
      return "DISABLED"; 
    if (paramByte == 1)
      return "ENABLED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_1/SettingValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */