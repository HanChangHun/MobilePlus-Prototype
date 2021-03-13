package android.hardware.cas.V1_2;

import java.util.ArrayList;

public final class StatusEvent {
  public static final byte PLUGIN_PHYSICAL_MODULE_CHANGED = 0;
  
  public static final byte PLUGIN_SESSION_NUMBER_CHANGED = 1;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b = 0;
    arrayList.add("PLUGIN_PHYSICAL_MODULE_CHANGED");
    if ((paramByte & 0x1) == 1) {
      arrayList.add("PLUGIN_SESSION_NUMBER_CHANGED");
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
      return "PLUGIN_PHYSICAL_MODULE_CHANGED"; 
    if (paramByte == 1)
      return "PLUGIN_SESSION_NUMBER_CHANGED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_2/StatusEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */