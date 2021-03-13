package android.hardware.gnss.V2_0;

import java.util.ArrayList;

public final class AGnssStatusValue {
  public static final byte AGNSS_DATA_CONNECTED = 3;
  
  public static final byte AGNSS_DATA_CONN_DONE = 4;
  
  public static final byte AGNSS_DATA_CONN_FAILED = 5;
  
  public static final byte RELEASE_AGNSS_DATA_CONN = 2;
  
  public static final byte REQUEST_AGNSS_DATA_CONN = 1;
  
  public static final String dumpBitfield(byte paramByte) {
    ArrayList<String> arrayList = new ArrayList();
    byte b1 = 0;
    if ((paramByte & 0x1) == 1) {
      arrayList.add("REQUEST_AGNSS_DATA_CONN");
      b1 = (byte)(false | true);
    } 
    byte b2 = b1;
    if ((paramByte & 0x2) == 2) {
      arrayList.add("RELEASE_AGNSS_DATA_CONN");
      b2 = (byte)(b1 | 0x2);
    } 
    b1 = b2;
    if ((paramByte & 0x3) == 3) {
      arrayList.add("AGNSS_DATA_CONNECTED");
      b1 = (byte)(b2 | 0x3);
    } 
    b2 = b1;
    if ((paramByte & 0x4) == 4) {
      arrayList.add("AGNSS_DATA_CONN_DONE");
      b2 = (byte)(b1 | 0x4);
    } 
    b1 = b2;
    if ((paramByte & 0x5) == 5) {
      arrayList.add("AGNSS_DATA_CONN_FAILED");
      b1 = (byte)(b2 | 0x5);
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
      return "REQUEST_AGNSS_DATA_CONN"; 
    if (paramByte == 2)
      return "RELEASE_AGNSS_DATA_CONN"; 
    if (paramByte == 3)
      return "AGNSS_DATA_CONNECTED"; 
    if (paramByte == 4)
      return "AGNSS_DATA_CONN_DONE"; 
    if (paramByte == 5)
      return "AGNSS_DATA_CONN_FAILED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(paramByte)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IAGnssCallback$AGnssStatusValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */