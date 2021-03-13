package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class DeviceStateType {
  public static final int CHARGING_STATE = 1;
  
  public static final int LOW_DATA_EXPECTED = 2;
  
  public static final int POWER_SAVE_MODE = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("POWER_SAVE_MODE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("CHARGING_STATE");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("LOW_DATA_EXPECTED");
      j = i | 0x2;
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
      return "POWER_SAVE_MODE"; 
    if (paramInt == 1)
      return "CHARGING_STATE"; 
    if (paramInt == 2)
      return "LOW_DATA_EXPECTED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/DeviceStateType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */