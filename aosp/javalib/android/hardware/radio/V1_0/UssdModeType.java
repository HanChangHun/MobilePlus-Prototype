package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class UssdModeType {
  public static final int LOCAL_CLIENT = 3;
  
  public static final int NOTIFY = 0;
  
  public static final int NOT_SUPPORTED = 4;
  
  public static final int NW_RELEASE = 2;
  
  public static final int NW_TIMEOUT = 5;
  
  public static final int REQUEST = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("NOTIFY");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("REQUEST");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("NW_RELEASE");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("LOCAL_CLIENT");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("NOT_SUPPORTED");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("NW_TIMEOUT");
      i = j | 0x5;
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
      return "NOTIFY"; 
    if (paramInt == 1)
      return "REQUEST"; 
    if (paramInt == 2)
      return "NW_RELEASE"; 
    if (paramInt == 3)
      return "LOCAL_CLIENT"; 
    if (paramInt == 4)
      return "NOT_SUPPORTED"; 
    if (paramInt == 5)
      return "NW_TIMEOUT"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/UssdModeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */