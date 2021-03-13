package android.hardware.cas.V1_2;

import java.util.ArrayList;

public final class SessionIntent {
  public static final int LIVE = 0;
  
  public static final int PLAYBACK = 1;
  
  public static final int RECORD = 2;
  
  public static final int TIMESHIFT = 3;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("LIVE");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("PLAYBACK");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("RECORD");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("TIMESHIFT");
      i = j | 0x3;
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
      return "LIVE"; 
    if (paramInt == 1)
      return "PLAYBACK"; 
    if (paramInt == 2)
      return "RECORD"; 
    if (paramInt == 3)
      return "TIMESHIFT"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_2/SessionIntent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */