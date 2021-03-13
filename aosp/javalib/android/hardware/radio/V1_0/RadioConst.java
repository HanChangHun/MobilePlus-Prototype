package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class RadioConst {
  public static final int CARD_MAX_APPS = 8;
  
  public static final int CDMA_ALPHA_INFO_BUFFER_LENGTH = 64;
  
  public static final int CDMA_MAX_NUMBER_OF_INFO_RECS = 10;
  
  public static final int CDMA_NUMBER_INFO_BUFFER_LENGTH = 81;
  
  public static final int MAX_CLIENT_ID_LENGTH = 2;
  
  public static final int MAX_DEBUG_SOCKET_NAME_LENGTH = 12;
  
  public static final int MAX_QEMU_PIPE_NAME_LENGTH = 11;
  
  public static final int MAX_RILDS = 3;
  
  public static final int MAX_SOCKET_NAME_LENGTH = 6;
  
  public static final int MAX_UUID_LENGTH = 64;
  
  public static final int NUM_SERVICE_CLASSES = 7;
  
  public static final int NUM_TX_POWER_LEVELS = 5;
  
  public static final int SS_INFO_MAX = 4;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x40) == 64) {
      arrayList.add("CDMA_ALPHA_INFO_BUFFER_LENGTH");
      i = 0x0 | 0x40;
    } 
    int j = i;
    if ((paramInt & 0x51) == 81) {
      arrayList.add("CDMA_NUMBER_INFO_BUFFER_LENGTH");
      j = i | 0x51;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("MAX_RILDS");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("MAX_SOCKET_NAME_LENGTH");
      j = i | 0x6;
    } 
    int k = j;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("MAX_CLIENT_ID_LENGTH");
      k = j | 0x2;
    } 
    i = k;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("MAX_DEBUG_SOCKET_NAME_LENGTH");
      i = k | 0xC;
    } 
    j = i;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("MAX_QEMU_PIPE_NAME_LENGTH");
      j = i | 0xB;
    } 
    i = j;
    if ((paramInt & 0x40) == 64) {
      arrayList.add("MAX_UUID_LENGTH");
      i = j | 0x40;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("CARD_MAX_APPS");
      j = i | 0x8;
    } 
    k = j;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("CDMA_MAX_NUMBER_OF_INFO_RECS");
      k = j | 0xA;
    } 
    i = k;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("SS_INFO_MAX");
      i = k | 0x4;
    } 
    j = i;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("NUM_SERVICE_CLASSES");
      j = i | 0x7;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("NUM_TX_POWER_LEVELS");
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
    if (paramInt == 64)
      return "CDMA_ALPHA_INFO_BUFFER_LENGTH"; 
    if (paramInt == 81)
      return "CDMA_NUMBER_INFO_BUFFER_LENGTH"; 
    if (paramInt == 3)
      return "MAX_RILDS"; 
    if (paramInt == 6)
      return "MAX_SOCKET_NAME_LENGTH"; 
    if (paramInt == 2)
      return "MAX_CLIENT_ID_LENGTH"; 
    if (paramInt == 12)
      return "MAX_DEBUG_SOCKET_NAME_LENGTH"; 
    if (paramInt == 11)
      return "MAX_QEMU_PIPE_NAME_LENGTH"; 
    if (paramInt == 64)
      return "MAX_UUID_LENGTH"; 
    if (paramInt == 8)
      return "CARD_MAX_APPS"; 
    if (paramInt == 10)
      return "CDMA_MAX_NUMBER_OF_INFO_RECS"; 
    if (paramInt == 4)
      return "SS_INFO_MAX"; 
    if (paramInt == 7)
      return "NUM_SERVICE_CLASSES"; 
    if (paramInt == 5)
      return "NUM_TX_POWER_LEVELS"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioConst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */