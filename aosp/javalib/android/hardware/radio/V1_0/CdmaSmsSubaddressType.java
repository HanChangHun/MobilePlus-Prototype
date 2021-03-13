package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaSmsSubaddressType {
  public static final int NSAP = 0;
  
  public static final int USER_SPECIFIED = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NSAP");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("USER_SPECIFIED");
      i = false | true;
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
      return "NSAP"; 
    if (paramInt == 1)
      return "USER_SPECIFIED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaSmsSubaddressType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */