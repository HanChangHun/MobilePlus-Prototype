package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class TtyMode {
  public static final int FULL = 1;
  
  public static final int HCO = 2;
  
  public static final int OFF = 0;
  
  public static final int VCO = 3;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("OFF");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("FULL");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("HCO");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("VCO");
      i = j | 0x3;
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
      return "OFF"; 
    if (paramInt == 1)
      return "FULL"; 
    if (paramInt == 2)
      return "HCO"; 
    if (paramInt == 3)
      return "VCO"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/TtyMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */