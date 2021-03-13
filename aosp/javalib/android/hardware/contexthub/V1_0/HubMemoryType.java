package android.hardware.contexthub.V1_0;

import java.util.ArrayList;

public final class HubMemoryType {
  public static final int MAIN = 0;
  
  public static final int SECONDARY = 1;
  
  public static final int TCM = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("MAIN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("SECONDARY");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("TCM");
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
      return "MAIN"; 
    if (paramInt == 1)
      return "SECONDARY"; 
    if (paramInt == 2)
      return "TCM"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/HubMemoryType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */