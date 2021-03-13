package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class AppType {
  public static final int CSIM = 4;
  
  public static final int ISIM = 5;
  
  public static final int RUIM = 3;
  
  public static final int SIM = 1;
  
  public static final int UNKNOWN = 0;
  
  public static final int USIM = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("SIM");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("USIM");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("RUIM");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("CSIM");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("ISIM");
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
      return "SIM"; 
    if (paramInt == 2)
      return "USIM"; 
    if (paramInt == 3)
      return "RUIM"; 
    if (paramInt == 4)
      return "CSIM"; 
    if (paramInt == 5)
      return "ISIM"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/AppType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */