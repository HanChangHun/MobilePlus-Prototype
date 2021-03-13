package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class TimeStampType {
  public static final int ANTENNA = 1;
  
  public static final int JAVA_RIL = 4;
  
  public static final int MODEM = 2;
  
  public static final int OEM_RIL = 3;
  
  public static final int UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ANTENNA");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("MODEM");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("OEM_RIL");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("JAVA_RIL");
      j = i | 0x4;
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
    if (paramInt == 0)
      return "UNKNOWN"; 
    if (paramInt == 1)
      return "ANTENNA"; 
    if (paramInt == 2)
      return "MODEM"; 
    if (paramInt == 3)
      return "OEM_RIL"; 
    if (paramInt == 4)
      return "JAVA_RIL"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/TimeStampType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */