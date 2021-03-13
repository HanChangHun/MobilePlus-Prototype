package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaOtaProvisionStatus {
  public static final int A_KEY_EXCHANGED = 2;
  
  public static final int COMMITTED = 8;
  
  public static final int IMSI_DOWNLOADED = 6;
  
  public static final int MDN_DOWNLOADED = 5;
  
  public static final int NAM_DOWNLOADED = 4;
  
  public static final int OTAPA_ABORTED = 11;
  
  public static final int OTAPA_STARTED = 9;
  
  public static final int OTAPA_STOPPED = 10;
  
  public static final int PRL_DOWNLOADED = 7;
  
  public static final int SPC_RETRIES_EXCEEDED = 1;
  
  public static final int SPL_UNLOCKED = 0;
  
  public static final int SSD_UPDATED = 3;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("SPL_UNLOCKED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("SPC_RETRIES_EXCEEDED");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("A_KEY_EXCHANGED");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("SSD_UPDATED");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("NAM_DOWNLOADED");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("MDN_DOWNLOADED");
      i = j | 0x5;
    } 
    j = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("IMSI_DOWNLOADED");
      j = i | 0x6;
    } 
    i = j;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("PRL_DOWNLOADED");
      i = j | 0x7;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("COMMITTED");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("OTAPA_STARTED");
      i = j | 0x9;
    } 
    j = i;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("OTAPA_STOPPED");
      j = i | 0xA;
    } 
    i = j;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("OTAPA_ABORTED");
      i = j | 0xB;
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
      return "SPL_UNLOCKED"; 
    if (paramInt == 1)
      return "SPC_RETRIES_EXCEEDED"; 
    if (paramInt == 2)
      return "A_KEY_EXCHANGED"; 
    if (paramInt == 3)
      return "SSD_UPDATED"; 
    if (paramInt == 4)
      return "NAM_DOWNLOADED"; 
    if (paramInt == 5)
      return "MDN_DOWNLOADED"; 
    if (paramInt == 6)
      return "IMSI_DOWNLOADED"; 
    if (paramInt == 7)
      return "PRL_DOWNLOADED"; 
    if (paramInt == 8)
      return "COMMITTED"; 
    if (paramInt == 9)
      return "OTAPA_STARTED"; 
    if (paramInt == 10)
      return "OTAPA_STOPPED"; 
    if (paramInt == 11)
      return "OTAPA_ABORTED"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaOtaProvisionStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */