package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SsTeleserviceType {
  public static final int ALL_DATA_TELESERVICES = 3;
  
  public static final int ALL_TELESERVICES_EXCEPT_SMS = 5;
  
  public static final int ALL_TELESEVICES = 1;
  
  public static final int ALL_TELE_AND_BEARER_SERVICES = 0;
  
  public static final int SMS_SERVICES = 4;
  
  public static final int TELEPHONY = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("ALL_TELE_AND_BEARER_SERVICES");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ALL_TELESEVICES");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("TELEPHONY");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("ALL_DATA_TELESERVICES");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("SMS_SERVICES");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("ALL_TELESERVICES_EXCEPT_SMS");
      i = j | 0x5;
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
      return "ALL_TELE_AND_BEARER_SERVICES"; 
    if (paramInt == 1)
      return "ALL_TELESEVICES"; 
    if (paramInt == 2)
      return "TELEPHONY"; 
    if (paramInt == 3)
      return "ALL_DATA_TELESERVICES"; 
    if (paramInt == 4)
      return "SMS_SERVICES"; 
    if (paramInt == 5)
      return "ALL_TELESERVICES_EXCEPT_SMS"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SsTeleserviceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */