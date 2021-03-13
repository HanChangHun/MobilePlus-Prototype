package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class UusDcs {
  public static final int IA5C = 4;
  
  public static final int OSIHLP = 1;
  
  public static final int RMCF = 3;
  
  public static final int USP = 0;
  
  public static final int X244 = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("USP");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("OSIHLP");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("X244");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("RMCF");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("IA5C");
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
      return "USP"; 
    if (paramInt == 1)
      return "OSIHLP"; 
    if (paramInt == 2)
      return "X244"; 
    if (paramInt == 3)
      return "RMCF"; 
    if (paramInt == 4)
      return "IA5C"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/UusDcs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */