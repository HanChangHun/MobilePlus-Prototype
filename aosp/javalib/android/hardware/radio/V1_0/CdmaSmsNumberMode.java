package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaSmsNumberMode {
  public static final int DATA_NETWORK = 1;
  
  public static final int NOT_DATA_NETWORK = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NOT_DATA_NETWORK");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("DATA_NETWORK");
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
      return "NOT_DATA_NETWORK"; 
    if (paramInt == 1)
      return "DATA_NETWORK"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsNumberMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */