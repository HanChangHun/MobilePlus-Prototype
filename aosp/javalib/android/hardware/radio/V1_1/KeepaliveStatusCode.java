package android.hardware.radio.V1_1;

import java.util.ArrayList;

public final class KeepaliveStatusCode {
  public static final int ACTIVE = 0;
  
  public static final int INACTIVE = 1;
  
  public static final int PENDING = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("ACTIVE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("INACTIVE");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("PENDING");
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
      return "ACTIVE"; 
    if (paramInt == 1)
      return "INACTIVE"; 
    if (paramInt == 2)
      return "PENDING"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/KeepaliveStatusCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */