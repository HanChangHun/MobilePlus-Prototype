package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CarrierMatchType {
  public static final int ALL = 0;
  
  public static final int GID1 = 3;
  
  public static final int GID2 = 4;
  
  public static final int IMSI_PREFIX = 2;
  
  public static final int SPN = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("ALL");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("SPN");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("IMSI_PREFIX");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("GID1");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("GID2");
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
      return "ALL"; 
    if (paramInt == 1)
      return "SPN"; 
    if (paramInt == 2)
      return "IMSI_PREFIX"; 
    if (paramInt == 3)
      return "GID1"; 
    if (paramInt == 4)
      return "GID2"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CarrierMatchType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */