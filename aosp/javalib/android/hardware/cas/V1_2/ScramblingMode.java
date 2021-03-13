package android.hardware.cas.V1_2;

import java.util.ArrayList;

public final class ScramblingMode {
  public static final int AES128 = 9;
  
  public static final int AES_ECB = 10;
  
  public static final int AES_SCTE52 = 11;
  
  public static final int DVB_CISSA_V1 = 6;
  
  public static final int DVB_CSA1 = 1;
  
  public static final int DVB_CSA2 = 2;
  
  public static final int DVB_CSA3_ENHANCE = 5;
  
  public static final int DVB_CSA3_MINIMAL = 4;
  
  public static final int DVB_CSA3_STANDARD = 3;
  
  public static final int DVB_IDSA = 7;
  
  public static final int MULTI2 = 8;
  
  public static final int RESERVED = 0;
  
  public static final int TDES_ECB = 12;
  
  public static final int TDES_SCTE52 = 13;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("RESERVED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("DVB_CSA1");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("DVB_CSA2");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("DVB_CSA3_STANDARD");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("DVB_CSA3_MINIMAL");
      j = i | 0x4;
    } 
    i = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("DVB_CSA3_ENHANCE");
      i = j | 0x5;
    } 
    j = i;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("DVB_CISSA_V1");
      j = i | 0x6;
    } 
    i = j;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("DVB_IDSA");
      i = j | 0x7;
    } 
    j = i;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("MULTI2");
      j = i | 0x8;
    } 
    i = j;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("AES128");
      i = j | 0x9;
    } 
    j = i;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("AES_ECB");
      j = i | 0xA;
    } 
    i = j;
    if ((paramInt & 0xB) == 11) {
      arrayList.add("AES_SCTE52");
      i = j | 0xB;
    } 
    j = i;
    if ((paramInt & 0xC) == 12) {
      arrayList.add("TDES_ECB");
      j = i | 0xC;
    } 
    i = j;
    if ((paramInt & 0xD) == 13) {
      arrayList.add("TDES_SCTE52");
      i = j | 0xD;
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
      return "RESERVED"; 
    if (paramInt == 1)
      return "DVB_CSA1"; 
    if (paramInt == 2)
      return "DVB_CSA2"; 
    if (paramInt == 3)
      return "DVB_CSA3_STANDARD"; 
    if (paramInt == 4)
      return "DVB_CSA3_MINIMAL"; 
    if (paramInt == 5)
      return "DVB_CSA3_ENHANCE"; 
    if (paramInt == 6)
      return "DVB_CISSA_V1"; 
    if (paramInt == 7)
      return "DVB_IDSA"; 
    if (paramInt == 8)
      return "MULTI2"; 
    if (paramInt == 9)
      return "AES128"; 
    if (paramInt == 10)
      return "AES_ECB"; 
    if (paramInt == 11)
      return "AES_SCTE52"; 
    if (paramInt == 12)
      return "TDES_ECB"; 
    if (paramInt == 13)
      return "TDES_SCTE52"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_2/ScramblingMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */