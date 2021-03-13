package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class RadioCdmaSmsConst {
  public static final int ADDRESS_MAX = 36;
  
  public static final int BEARER_DATA_MAX = 255;
  
  public static final int IP_ADDRESS_SIZE = 4;
  
  public static final int MAX_UD_HEADERS = 7;
  
  public static final int SUBADDRESS_MAX = 36;
  
  public static final int UDH_ANIM_NUM_BITMAPS = 4;
  
  public static final int UDH_EO_DATA_SEGMENT_MAX = 131;
  
  public static final int UDH_LARGE_BITMAP_SIZE = 32;
  
  public static final int UDH_LARGE_PIC_SIZE = 128;
  
  public static final int UDH_MAX_SND_SIZE = 128;
  
  public static final int UDH_OTHER_SIZE = 226;
  
  public static final int UDH_SMALL_BITMAP_SIZE = 8;
  
  public static final int UDH_SMALL_PIC_SIZE = 32;
  
  public static final int UDH_VAR_PIC_SIZE = 134;
  
  public static final int USER_DATA_MAX = 229;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x24) == 36) {
      arrayList.add("ADDRESS_MAX");
      i = 0x0 | 0x24;
    } 
    int j = i;
    if ((paramInt & 0x24) == 36) {
      arrayList.add("SUBADDRESS_MAX");
      j = i | 0x24;
    } 
    int k = j;
    if ((paramInt & 0xFF) == 255) {
      arrayList.add("BEARER_DATA_MAX");
      k = j | 0xFF;
    } 
    i = k;
    if ((paramInt & 0x80) == 128) {
      arrayList.add("UDH_MAX_SND_SIZE");
      i = k | 0x80;
    } 
    j = i;
    if ((paramInt & 0x83) == 131) {
      arrayList.add("UDH_EO_DATA_SEGMENT_MAX");
      j = i | 0x83;
    } 
    i = j;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("MAX_UD_HEADERS");
      i = j | 0x7;
    } 
    j = i;
    if ((paramInt & 0xE5) == 229) {
      arrayList.add("USER_DATA_MAX");
      j = i | 0xE5;
    } 
    i = j;
    if ((paramInt & 0x80) == 128) {
      arrayList.add("UDH_LARGE_PIC_SIZE");
      i = j | 0x80;
    } 
    j = i;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("UDH_SMALL_PIC_SIZE");
      j = i | 0x20;
    } 
    i = j;
    if ((paramInt & 0x86) == 134) {
      arrayList.add("UDH_VAR_PIC_SIZE");
      i = j | 0x86;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("UDH_ANIM_NUM_BITMAPS");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x20) == 32) {
      arrayList.add("UDH_LARGE_BITMAP_SIZE");
      i = j | 0x20;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("UDH_SMALL_BITMAP_SIZE");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0xE2) == 226) {
      arrayList.add("UDH_OTHER_SIZE");
      i = j | 0xE2;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("IP_ADDRESS_SIZE");
      j = i | 0x4;
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
    if (paramInt == 36)
      return "ADDRESS_MAX"; 
    if (paramInt == 36)
      return "SUBADDRESS_MAX"; 
    if (paramInt == 255)
      return "BEARER_DATA_MAX"; 
    if (paramInt == 128)
      return "UDH_MAX_SND_SIZE"; 
    if (paramInt == 131)
      return "UDH_EO_DATA_SEGMENT_MAX"; 
    if (paramInt == 7)
      return "MAX_UD_HEADERS"; 
    if (paramInt == 229)
      return "USER_DATA_MAX"; 
    if (paramInt == 128)
      return "UDH_LARGE_PIC_SIZE"; 
    if (paramInt == 32)
      return "UDH_SMALL_PIC_SIZE"; 
    if (paramInt == 134)
      return "UDH_VAR_PIC_SIZE"; 
    if (paramInt == 4)
      return "UDH_ANIM_NUM_BITMAPS"; 
    if (paramInt == 32)
      return "UDH_LARGE_BITMAP_SIZE"; 
    if (paramInt == 8)
      return "UDH_SMALL_BITMAP_SIZE"; 
    if (paramInt == 226)
      return "UDH_OTHER_SIZE"; 
    if (paramInt == 4)
      return "IP_ADDRESS_SIZE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/RadioCdmaSmsConst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */