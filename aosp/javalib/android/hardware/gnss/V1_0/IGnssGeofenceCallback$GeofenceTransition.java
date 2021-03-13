package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GeofenceTransition {
  public static final int ENTERED = 1;
  
  public static final int EXITED = 2;
  
  public static final int UNCERTAIN = 4;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ENTERED");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("EXITED");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("UNCERTAIN");
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
      return "ENTERED"; 
    if (paramInt == 2)
      return "EXITED"; 
    if (paramInt == 4)
      return "UNCERTAIN"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssGeofenceCallback$GeofenceTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */