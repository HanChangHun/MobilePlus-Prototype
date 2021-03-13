package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssMeasurementState {
  public static final int STATE_BDS_D2_BIT_SYNC = 256;
  
  public static final int STATE_BDS_D2_SUBFRAME_SYNC = 512;
  
  public static final int STATE_BIT_SYNC = 2;
  
  public static final int STATE_CODE_LOCK = 1;
  
  public static final int STATE_GAL_E1BC_CODE_LOCK = 1024;
  
  public static final int STATE_GAL_E1B_PAGE_SYNC = 4096;
  
  public static final int STATE_GAL_E1C_2ND_CODE_LOCK = 2048;
  
  public static final int STATE_GLO_STRING_SYNC = 64;
  
  public static final int STATE_GLO_TOD_DECODED = 128;
  
  public static final int STATE_GLO_TOD_KNOWN = 32768;
  
  public static final int STATE_MSEC_AMBIGUOUS = 16;
  
  public static final int STATE_SBAS_SYNC = 8192;
  
  public static final int STATE_SUBFRAME_SYNC = 4;
  
  public static final int STATE_SYMBOL_SYNC = 32;
  
  public static final int STATE_TOW_DECODED = 8;
  
  public static final int STATE_TOW_KNOWN = 16384;
  
  public static final int STATE_UNKNOWN = 0;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("STATE_UNKNOWN");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("STATE_CODE_LOCK");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("STATE_BIT_SYNC");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("STATE_SUBFRAME_SYNC");
      i = j | 0x4;
    } 
    int k = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("STATE_TOW_DECODED");
      k = i | 0x8;
    } 
    j = k;
    if ((paramInt & 0x10) == 16) {
      arrayList.add("STATE_MSEC_AMBIGUOUS");
      j = k | 0x10;
    } 
    i = j;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("STATE_SYMBOL_SYNC");
      i = j | 0x20;
    } 
    j = i;
    if ((paramInt & 0x40) == 64) {
      arrayList.add("STATE_GLO_STRING_SYNC");
      j = i | 0x40;
    } 
    i = j;
    if ((paramInt & 0x80) == 128) {
      arrayList.add("STATE_GLO_TOD_DECODED");
      i = j | 0x80;
    } 
    j = i;
    if ((paramInt & 0x100) == 256) {
      arrayList.add("STATE_BDS_D2_BIT_SYNC");
      j = i | 0x100;
    } 
    k = j;
    if ((paramInt & 0x200) == 512) {
      arrayList.add("STATE_BDS_D2_SUBFRAME_SYNC");
      k = j | 0x200;
    } 
    i = k;
    if ((paramInt & 0x400) == 1024) {
      arrayList.add("STATE_GAL_E1BC_CODE_LOCK");
      i = k | 0x400;
    } 
    j = i;
    if ((paramInt & 0x800) == 2048) {
      arrayList.add("STATE_GAL_E1C_2ND_CODE_LOCK");
      j = i | 0x800;
    } 
    i = j;
    if ((paramInt & 0x1000) == 4096) {
      arrayList.add("STATE_GAL_E1B_PAGE_SYNC");
      i = j | 0x1000;
    } 
    j = i;
    if ((paramInt & 0x2000) == 8192) {
      arrayList.add("STATE_SBAS_SYNC");
      j = i | 0x2000;
    } 
    i = j;
    if ((paramInt & 0x4000) == 16384) {
      arrayList.add("STATE_TOW_KNOWN");
      i = j | 0x4000;
    } 
    j = i;
    if ((paramInt & 0x8000) == 32768) {
      arrayList.add("STATE_GLO_TOD_KNOWN");
      j = i | 0x8000;
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
      return "STATE_UNKNOWN"; 
    if (paramInt == 1)
      return "STATE_CODE_LOCK"; 
    if (paramInt == 2)
      return "STATE_BIT_SYNC"; 
    if (paramInt == 4)
      return "STATE_SUBFRAME_SYNC"; 
    if (paramInt == 8)
      return "STATE_TOW_DECODED"; 
    if (paramInt == 16)
      return "STATE_MSEC_AMBIGUOUS"; 
    if (paramInt == 32)
      return "STATE_SYMBOL_SYNC"; 
    if (paramInt == 64)
      return "STATE_GLO_STRING_SYNC"; 
    if (paramInt == 128)
      return "STATE_GLO_TOD_DECODED"; 
    if (paramInt == 256)
      return "STATE_BDS_D2_BIT_SYNC"; 
    if (paramInt == 512)
      return "STATE_BDS_D2_SUBFRAME_SYNC"; 
    if (paramInt == 1024)
      return "STATE_GAL_E1BC_CODE_LOCK"; 
    if (paramInt == 2048)
      return "STATE_GAL_E1C_2ND_CODE_LOCK"; 
    if (paramInt == 4096)
      return "STATE_GAL_E1B_PAGE_SYNC"; 
    if (paramInt == 8192)
      return "STATE_SBAS_SYNC"; 
    if (paramInt == 16384)
      return "STATE_TOW_KNOWN"; 
    if (paramInt == 32768)
      return "STATE_GLO_TOD_KNOWN"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssMeasurementCallback$GnssMeasurementState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */