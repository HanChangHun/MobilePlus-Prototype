package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CallState {
  public static final int ACTIVE = 0;
  
  public static final int ALERTING = 3;
  
  public static final int DIALING = 2;
  
  public static final int HOLDING = 1;
  
  public static final int INCOMING = 4;
  
  public static final int WAITING = 5;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("ACTIVE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("HOLDING");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("DIALING");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("ALERTING");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("INCOMING");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("WAITING");
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
      return "ACTIVE"; 
    if (paramInt == 1)
      return "HOLDING"; 
    if (paramInt == 2)
      return "DIALING"; 
    if (paramInt == 3)
      return "ALERTING"; 
    if (paramInt == 4)
      return "INCOMING"; 
    if (paramInt == 5)
      return "WAITING"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CallState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */