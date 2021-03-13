package android.hardware.radio.V1_2;

import java.util.ArrayList;

public final class IndicationFilter {
  public static final int ALL = -1;
  
  public static final int DATA_CALL_DORMANCY_CHANGED = 4;
  
  public static final int FULL_NETWORK_STATE = 2;
  
  public static final int LINK_CAPACITY_ESTIMATE = 8;
  
  public static final int NONE = 0;
  
  public static final int PHYSICAL_CHANNEL_CONFIG = 16;
  
  public static final int SIGNAL_STRENGTH = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NONE");
    if ((paramInt & 0xFFFFFFFF) == -1) {
      arrayList.add("ALL");
      i = 0x0 | 0xFFFFFFFF;
    } 
    int j = i;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("SIGNAL_STRENGTH");
      j = i | 0x1;
    } 
    i = j;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("FULL_NETWORK_STATE");
      i = j | 0x2;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("DATA_CALL_DORMANCY_CHANGED");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("LINK_CAPACITY_ESTIMATE");
      i = j | 0x8;
    } 
    j = i;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("PHYSICAL_CHANNEL_CONFIG");
      j = i | 0x10;
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
      return "NONE"; 
    if (paramInt == -1)
      return "ALL"; 
    if (paramInt == 1)
      return "SIGNAL_STRENGTH"; 
    if (paramInt == 2)
      return "FULL_NETWORK_STATE"; 
    if (paramInt == 4)
      return "DATA_CALL_DORMANCY_CHANGED"; 
    if (paramInt == 8)
      return "LINK_CAPACITY_ESTIMATE"; 
    if (paramInt == 16)
      return "PHYSICAL_CHANNEL_CONFIG"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/IndicationFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */