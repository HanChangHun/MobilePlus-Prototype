package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaSmsErrorClass {
  public static final int ERROR = 1;
  
  public static final int NO_ERROR = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NO_ERROR");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ERROR");
      i = false | true;
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
      return "NO_ERROR"; 
    if (paramInt == 1)
      return "ERROR"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsErrorClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */