package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssNiNotifyFlags {
  public static final int NEED_NOTIFY = 1;
  
  public static final int NEED_VERIFY = 2;
  
  public static final int PRIVACY_OVERRIDE = 4;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("NEED_NOTIFY");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("NEED_VERIFY");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("PRIVACY_OVERRIDE");
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
      return "NEED_NOTIFY"; 
    if (paramInt == 2)
      return "NEED_VERIFY"; 
    if (paramInt == 4)
      return "PRIVACY_OVERRIDE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNiCallback$GnssNiNotifyFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */