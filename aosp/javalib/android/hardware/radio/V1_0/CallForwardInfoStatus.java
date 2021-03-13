package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CallForwardInfoStatus {
  public static final int DISABLE = 0;
  
  public static final int ENABLE = 1;
  
  public static final int ERASURE = 4;
  
  public static final int INTERROGATE = 2;
  
  public static final int REGISTRATION = 3;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("DISABLE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ENABLE");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("INTERROGATE");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("REGISTRATION");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("ERASURE");
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
      return "DISABLE"; 
    if (paramInt == 1)
      return "ENABLE"; 
    if (paramInt == 2)
      return "INTERROGATE"; 
    if (paramInt == 3)
      return "REGISTRATION"; 
    if (paramInt == 4)
      return "ERASURE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CallForwardInfoStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */