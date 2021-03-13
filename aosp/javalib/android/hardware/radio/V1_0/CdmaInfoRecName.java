package android.hardware.radio.V1_0;

import java.util.ArrayList;

public final class CdmaInfoRecName {
  public static final int CALLED_PARTY_NUMBER = 1;
  
  public static final int CALLING_PARTY_NUMBER = 2;
  
  public static final int CONNECTED_NUMBER = 3;
  
  public static final int DISPLAY = 0;
  
  public static final int EXTENDED_DISPLAY = 7;
  
  public static final int LINE_CONTROL = 6;
  
  public static final int REDIRECTING_NUMBER = 5;
  
  public static final int SIGNAL = 4;
  
  public static final int T53_AUDIO_CONTROL = 10;
  
  public static final int T53_CLIR = 8;
  
  public static final int T53_RELEASE = 9;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("DISPLAY");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("CALLED_PARTY_NUMBER");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("CALLING_PARTY_NUMBER");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("CONNECTED_NUMBER");
      i = j | 0x3;
    } 
    j = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("SIGNAL");
      j = i | 0x4;
    } 
    int k = j;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("REDIRECTING_NUMBER");
      k = j | 0x5;
    } 
    i = k;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("LINE_CONTROL");
      i = k | 0x6;
    } 
    j = i;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("EXTENDED_DISPLAY");
      j = i | 0x7;
    } 
    i = j;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("T53_CLIR");
      i = j | 0x8;
    } 
    j = i;
    if ((paramInt & 0x9) == 9) {
      arrayList.add("T53_RELEASE");
      j = i | 0x9;
    } 
    i = j;
    if ((paramInt & 0xA) == 10) {
      arrayList.add("T53_AUDIO_CONTROL");
      i = j | 0xA;
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
      return "DISPLAY"; 
    if (paramInt == 1)
      return "CALLED_PARTY_NUMBER"; 
    if (paramInt == 2)
      return "CALLING_PARTY_NUMBER"; 
    if (paramInt == 3)
      return "CONNECTED_NUMBER"; 
    if (paramInt == 4)
      return "SIGNAL"; 
    if (paramInt == 5)
      return "REDIRECTING_NUMBER"; 
    if (paramInt == 6)
      return "LINE_CONTROL"; 
    if (paramInt == 7)
      return "EXTENDED_DISPLAY"; 
    if (paramInt == 8)
      return "T53_CLIR"; 
    if (paramInt == 9)
      return "T53_RELEASE"; 
    if (paramInt == 10)
      return "T53_AUDIO_CONTROL"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/CdmaInfoRecName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */