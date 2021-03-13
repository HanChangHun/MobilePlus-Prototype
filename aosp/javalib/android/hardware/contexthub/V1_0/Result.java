package android.hardware.contexthub.V1_0;

import java.util.ArrayList;

public final class Result {
  public static final int BAD_PARAMS = 2;
  
  public static final int NOT_INIT = 3;
  
  public static final int OK = 0;
  
  public static final int TRANSACTION_FAILED = 4;
  
  public static final int TRANSACTION_PENDING = 5;
  
  public static final int UNKNOWN_FAILURE = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("OK");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("UNKNOWN_FAILURE");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("BAD_PARAMS");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("NOT_INIT");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("TRANSACTION_FAILED");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("TRANSACTION_PENDING");
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
      return "OK"; 
    if (paramInt == 1)
      return "UNKNOWN_FAILURE"; 
    if (paramInt == 2)
      return "BAD_PARAMS"; 
    if (paramInt == 3)
      return "NOT_INIT"; 
    if (paramInt == 4)
      return "TRANSACTION_FAILED"; 
    if (paramInt == 5)
      return "TRANSACTION_PENDING"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/Result.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */