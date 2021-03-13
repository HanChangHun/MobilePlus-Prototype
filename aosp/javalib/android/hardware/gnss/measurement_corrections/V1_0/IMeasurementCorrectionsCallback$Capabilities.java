package android.hardware.gnss.measurement_corrections.V1_0;

import java.util.ArrayList;

public final class Capabilities {
  public static final int EXCESS_PATH_LENGTH = 2;
  
  public static final int LOS_SATS = 1;
  
  public static final int REFLECTING_PLANE = 4;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("LOS_SATS");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("EXCESS_PATH_LENGTH");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("REFLECTING_PLANE");
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
      return "LOS_SATS"; 
    if (paramInt == 2)
      return "EXCESS_PATH_LENGTH"; 
    if (paramInt == 4)
      return "REFLECTING_PLANE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/measurement_corrections/V1_0/IMeasurementCorrectionsCallback$Capabilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */