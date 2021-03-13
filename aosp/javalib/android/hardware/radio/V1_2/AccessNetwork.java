package android.hardware.radio.V1_2;

import java.util.ArrayList;

public final class AccessNetwork {
  public static final int CDMA2000 = 4;
  
  public static final int EUTRAN = 3;
  
  public static final int GERAN = 1;
  
  public static final int IWLAN = 5;
  
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
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("CDMA2000");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("IWLAN");
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
    if (paramInt == 1)
      return "GERAN"; 
    if (paramInt == 2)
      return "UTRAN"; 
    if (paramInt == 3)
      return "EUTRAN"; 
    if (paramInt == 4)
      return "CDMA2000"; 
    if (paramInt == 5)
      return "IWLAN"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/AccessNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */