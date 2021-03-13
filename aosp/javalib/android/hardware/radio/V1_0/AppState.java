package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class AppState {
  public static final int DETECTED = 1;
  
  public static final int PIN = 2;
  
  public static final int PUK = 3;
  
  public static final int READY = 5;
  
  public static final int SUBSCRIPTION_PERSO = 4;
  
  public static final int UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("DETECTED");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("PIN");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("PUK");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("SUBSCRIPTION_PERSO");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("READY");
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
      return "UNKNOWN"; 
    if (paramInt == 1)
      return "DETECTED"; 
    if (paramInt == 2)
      return "PIN"; 
    if (paramInt == 3)
      return "PUK"; 
    if (paramInt == 4)
      return "SUBSCRIPTION_PERSO"; 
    if (paramInt == 5)
      return "READY"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/AppState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */