package android.hardware.contexthub.V1_0;

import java.util.ArrayList;

public final class HubMemoryFlag {
  public static final int EXEC = 4;
  
  public static final int READ = 1;
  
  public static final int WRITE = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("READ");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("WRITE");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("EXEC");
      i = j | 0x4;
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
      return "READ"; 
    if (paramInt == 2)
      return "WRITE"; 
    if (paramInt == 4)
      return "EXEC"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/HubMemoryFlag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */