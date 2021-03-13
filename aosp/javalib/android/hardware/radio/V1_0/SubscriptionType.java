package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SubscriptionType {
  public static final int SUBSCRIPTION_1 = 0;
  
  public static final int SUBSCRIPTION_2 = 1;
  
  public static final int SUBSCRIPTION_3 = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("SUBSCRIPTION_1");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("SUBSCRIPTION_2");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("SUBSCRIPTION_3");
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
      return "SUBSCRIPTION_1"; 
    if (paramInt == 1)
      return "SUBSCRIPTION_2"; 
    if (paramInt == 2)
      return "SUBSCRIPTION_3"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SubscriptionType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */