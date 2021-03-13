package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class RadioState {
  public static final int OFF = 0;
  
  public static final int ON = 10;
  
  public static final int UNAVAILABLE = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("OFF");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("UNAVAILABLE");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("ON");
      j = i | 0xA;
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
      return "OFF"; 
    if (paramInt == 1)
      return "UNAVAILABLE"; 
    if (paramInt == 10)
      return "ON"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */