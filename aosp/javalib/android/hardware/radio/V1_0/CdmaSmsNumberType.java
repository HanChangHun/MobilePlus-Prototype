package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaSmsNumberType {
  public static final int ABBREVIATED = 6;
  
  public static final int ALPHANUMERIC = 5;
  
  public static final int INTERNATIONAL_OR_DATA_IP = 1;
  
  public static final int NATIONAL_OR_INTERNET_MAIL = 2;
  
  public static final int NETWORK = 3;
  
  public static final int RESERVED_7 = 7;
  
  public static final int SUBSCRIBER = 4;
  
  public static final int UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("INTERNATIONAL_OR_DATA_IP");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("NATIONAL_OR_INTERNET_MAIL");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("NETWORK");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("SUBSCRIBER");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("ALPHANUMERIC");
      i = j | 0x5;
    } 
    j = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("ABBREVIATED");
      j = i | 0x6;
    } 
    i = j;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("RESERVED_7");
      i = j | 0x7;
    } 
    if (paramInt != i) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(i & paramInt));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(int paramInt) {
    if (paramInt == 0)
      return "UNKNOWN"; 
    if (paramInt == 1)
      return "INTERNATIONAL_OR_DATA_IP"; 
    if (paramInt == 2)
      return "NATIONAL_OR_INTERNET_MAIL"; 
    if (paramInt == 3)
      return "NETWORK"; 
    if (paramInt == 4)
      return "SUBSCRIBER"; 
    if (paramInt == 5)
      return "ALPHANUMERIC"; 
    if (paramInt == 6)
      return "ABBREVIATED"; 
    if (paramInt == 7)
      return "RESERVED_7"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsNumberType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */