package android.hardware.gnss.visibility_control.V1_0;

import java.util.ArrayList;

public final class NfwRequestor {
  public static final byte AUTOMOBILE_CLIENT = 20;
  
  public static final byte CARRIER = 0;
  
  public static final byte GNSS_CHIPSET_VENDOR = 12;
  
  public static final byte MODEM_CHIPSET_VENDOR = 11;
  
  public static final byte OEM = 10;
  
  public static final byte OTHER_CHIPSET_VENDOR = 13;
  
  public static final byte OTHER_REQUESTOR = 100;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    arrayList.add("CARRIER");
    if ((paramByte & 0xA) == 10) {
      arrayList.add("OEM");
      b1 = (byte)(0x0 | 0xA);
    } 
    byte b2 = b1;
    if ((paramByte & 0xB) == 11) {
      arrayList.add("MODEM_CHIPSET_VENDOR");
      b2 = (byte)(b1 | 0xB);
    } 
    b1 = b2;
    if ((paramByte & 0xC) == 12) {
      arrayList.add("GNSS_CHIPSET_VENDOR");
      b1 = (byte)(b2 | 0xC);
    } 
    b2 = b1;
    if ((paramByte & 0xD) == 13) {
      arrayList.add("OTHER_CHIPSET_VENDOR");
      b2 = (byte)(b1 | 0xD);
    } 
    b1 = b2;
    if ((paramByte & 0x14) == 20) {
      arrayList.add("AUTOMOBILE_CLIENT");
      b1 = (byte)(b2 | 0x14);
    } 
    b2 = b1;
    if ((paramByte & 0x64) == 100) {
      arrayList.add("OTHER_REQUESTOR");
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
      return "CARRIER"; 
    if (paramByte == 10)
      return "OEM"; 
    if (paramByte == 11)
      return "MODEM_CHIPSET_VENDOR"; 
    if (paramByte == 12)
      return "GNSS_CHIPSET_VENDOR"; 
    if (paramByte == 13)
      return "OTHER_CHIPSET_VENDOR"; 
    if (paramByte == 20)
      return "AUTOMOBILE_CLIENT"; 
    if (paramByte == 100)
      return "OTHER_REQUESTOR"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/visibility_control/V1_0/IGnssVisibilityControlCallback$NfwRequestor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */