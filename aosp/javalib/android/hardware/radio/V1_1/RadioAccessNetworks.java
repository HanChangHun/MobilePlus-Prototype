package android.hardware.radio.V1_1;

import java.util.ArrayList;

public final class RadioAccessNetworks {
  public static final int EUTRAN = 3;
  
  public static final int GERAN = 1;
  
  public static final int UTRAN = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("GERAN");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("UTRAN");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("EUTRAN");
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
    if (paramInt == 1)
      return "GERAN"; 
    if (paramInt == 2)
      return "UTRAN"; 
    if (paramInt == 3)
      return "EUTRAN"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_1/RadioAccessNetworks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */