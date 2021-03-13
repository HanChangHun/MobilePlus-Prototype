package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class Capabilities {
  public static final int GEOFENCING = 32;
  
  public static final int MEASUREMENTS = 64;
  
  public static final int MSA = 4;
  
  public static final int MSB = 2;
  
  public static final int NAV_MESSAGES = 128;
  
  public static final int ON_DEMAND_TIME = 16;
  
  public static final int SCHEDULING = 1;
  
  public static final int SINGLE_SHOT = 8;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("SCHEDULING");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("MSB");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("MSA");
      i = j | 0x4;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("SINGLE_SHOT");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("ON_DEMAND_TIME");
      i = j | 0x10;
    } 
    j = i;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("GEOFENCING");
      j = i | 0x20;
    } 
    i = j;
    if ((paramInt & 0x40) == 64) {
      arrayList.add("MEASUREMENTS");
      i = j | 0x40;
    } 
    j = i;
    if ((paramInt & 0x80) == 128) {
      arrayList.add("NAV_MESSAGES");
      j = i | 0x80;
    } 
    if (paramInt != j) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(j & paramInt));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(int paramInt) {
    if (paramInt == 1)
      return "SCHEDULING"; 
    if (paramInt == 2)
      return "MSB"; 
    if (paramInt == 4)
      return "MSA"; 
    if (paramInt == 8)
      return "SINGLE_SHOT"; 
    if (paramInt == 16)
      return "ON_DEMAND_TIME"; 
    if (paramInt == 32)
      return "GEOFENCING"; 
    if (paramInt == 64)
      return "MEASUREMENTS"; 
    if (paramInt == 128)
      return "NAV_MESSAGES"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssCallback$Capabilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */