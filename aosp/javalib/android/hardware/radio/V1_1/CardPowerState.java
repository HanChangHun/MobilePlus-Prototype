package android.hardware.radio.V1_1;

import java.util.ArrayList;

public final class CardPowerState {
  public static final int POWER_DOWN = 0;
  
  public static final int POWER_UP = 1;
  
  public static final int POWER_UP_PASS_THROUGH = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("POWER_DOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("POWER_UP");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("POWER_UP_PASS_THROUGH");
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
      return "POWER_DOWN"; 
    if (paramInt == 1)
      return "POWER_UP"; 
    if (paramInt == 2)
      return "POWER_UP_PASS_THROUGH"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/CardPowerState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */