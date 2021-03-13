package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class SmsWriteArgsStatus {
  public static final int REC_READ = 1;
  
  public static final int REC_UNREAD = 0;
  
  public static final int STO_SENT = 3;
  
  public static final int STO_UNSENT = 2;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("REC_UNREAD");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("REC_READ");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("STO_UNSENT");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("STO_SENT");
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
      return "REC_UNREAD"; 
    if (paramInt == 1)
      return "REC_READ"; 
    if (paramInt == 2)
      return "STO_UNSENT"; 
    if (paramInt == 3)
      return "STO_SENT"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/SmsWriteArgsStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */