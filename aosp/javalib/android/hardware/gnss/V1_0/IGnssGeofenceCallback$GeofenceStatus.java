package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GeofenceStatus {
  public static final int ERROR_GENERIC = -149;
  
  public static final int ERROR_ID_EXISTS = -101;
  
  public static final int ERROR_ID_UNKNOWN = -102;
  
  public static final int ERROR_INVALID_TRANSITION = -103;
  
  public static final int ERROR_TOO_MANY_GEOFENCES = -100;
  
  public static final int OPERATION_SUCCESS = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("OPERATION_SUCCESS");
    if ((paramInt & 0xFFFFFF9C) == -100) {
      arrayList.add("ERROR_TOO_MANY_GEOFENCES");
      i = 0x0 | 0xFFFFFF9C;
    } 
    int j = i;
    if ((paramInt & 0xFFFFFF9B) == -101) {
      arrayList.add("ERROR_ID_EXISTS");
      j = i | 0xFFFFFF9B;
    } 
    i = j;
    if ((paramInt & 0xFFFFFF9A) == -102) {
      arrayList.add("ERROR_ID_UNKNOWN");
      i = j | 0xFFFFFF9A;
    } 
    j = i;
    if ((paramInt & 0xFFFFFF99) == -103) {
      arrayList.add("ERROR_INVALID_TRANSITION");
      j = i | 0xFFFFFF99;
    } 
    i = j;
    if ((paramInt & 0xFFFFFF6B) == -149) {
      arrayList.add("ERROR_GENERIC");
      i = j | 0xFFFFFF6B;
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
      return "OPERATION_SUCCESS"; 
    if (paramInt == -100)
      return "ERROR_TOO_MANY_GEOFENCES"; 
    if (paramInt == -101)
      return "ERROR_ID_EXISTS"; 
    if (paramInt == -102)
      return "ERROR_ID_UNKNOWN"; 
    if (paramInt == -103)
      return "ERROR_INVALID_TRANSITION"; 
    if (paramInt == -149)
      return "ERROR_GENERIC"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssGeofenceCallback$GeofenceStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */