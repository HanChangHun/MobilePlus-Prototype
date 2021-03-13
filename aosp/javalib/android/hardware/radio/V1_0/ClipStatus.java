package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class ClipStatus {
  public static final int CLIP_PROVISIONED = 0;
  
  public static final int CLIP_UNPROVISIONED = 1;
  
  public static final int UNKNOWN = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("CLIP_PROVISIONED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("CLIP_UNPROVISIONED");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("UNKNOWN");
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
      return "CLIP_PROVISIONED"; 
    if (paramInt == 1)
      return "CLIP_UNPROVISIONED"; 
    if (paramInt == 2)
      return "UNKNOWN"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/ClipStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */