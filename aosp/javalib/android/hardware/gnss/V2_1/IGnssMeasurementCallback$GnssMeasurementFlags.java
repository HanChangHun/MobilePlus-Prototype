package android.hardware.gnss.V2_1;

import java.util.ArrayList;

public final class GnssMeasurementFlags {
  public static final int HAS_AUTOMATIC_GAIN_CONTROL = 8192;
  
  public static final int HAS_CARRIER_CYCLES = 1024;
  
  public static final int HAS_CARRIER_FREQUENCY = 512;
  
  public static final int HAS_CARRIER_PHASE = 2048;
  
  public static final int HAS_CARRIER_PHASE_UNCERTAINTY = 4096;
  
  public static final int HAS_FULL_ISB = 65536;
  
  public static final int HAS_FULL_ISB_UNCERTAINTY = 131072;
  
  public static final int HAS_SATELLITE_ISB = 262144;
  
  public static final int HAS_SATELLITE_ISB_UNCERTAINTY = 524288;
  
  public static final int HAS_SNR = 1;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    if ((paramInt & 0x1) == 1) {
      arrayList.add("HAS_SNR");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x200) == 512) {
      arrayList.add("HAS_CARRIER_FREQUENCY");
      j = i | 0x200;
    } 
    i = j;
    if ((paramInt & 0x400) == 1024) {
      arrayList.add("HAS_CARRIER_CYCLES");
      i = j | 0x400;
    } 
    j = i;
    if ((paramInt & 0x800) == 2048) {
      arrayList.add("HAS_CARRIER_PHASE");
      j = i | 0x800;
    } 
    i = j;
    if ((paramInt & 0x1000) == 4096) {
      arrayList.add("HAS_CARRIER_PHASE_UNCERTAINTY");
      i = j | 0x1000;
    } 
    j = i;
    if ((paramInt & 0x2000) == 8192) {
      arrayList.add("HAS_AUTOMATIC_GAIN_CONTROL");
      j = i | 0x2000;
    } 
    i = j;
    if ((paramInt & 0x10000) == 65536) {
      arrayList.add("HAS_FULL_ISB");
      i = j | 0x10000;
    } 
    int k = i;
    if ((paramInt & 0x20000) == 131072) {
      arrayList.add("HAS_FULL_ISB_UNCERTAINTY");
      k = i | 0x20000;
    } 
    j = k;
    if ((paramInt & 0x40000) == 262144) {
      arrayList.add("HAS_SATELLITE_ISB");
      j = k | 0x40000;
    } 
    i = j;
    if ((paramInt & 0x80000) == 524288) {
      arrayList.add("HAS_SATELLITE_ISB_UNCERTAINTY");
      i = j | 0x80000;
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
    if (paramInt == 1)
      return "HAS_SNR"; 
    if (paramInt == 512)
      return "HAS_CARRIER_FREQUENCY"; 
    if (paramInt == 1024)
      return "HAS_CARRIER_CYCLES"; 
    if (paramInt == 2048)
      return "HAS_CARRIER_PHASE"; 
    if (paramInt == 4096)
      return "HAS_CARRIER_PHASE_UNCERTAINTY"; 
    if (paramInt == 8192)
      return "HAS_AUTOMATIC_GAIN_CONTROL"; 
    if (paramInt == 65536)
      return "HAS_FULL_ISB"; 
    if (paramInt == 131072)
      return "HAS_FULL_ISB_UNCERTAINTY"; 
    if (paramInt == 262144)
      return "HAS_SATELLITE_ISB"; 
    if (paramInt == 524288)
      return "HAS_SATELLITE_ISB_UNCERTAINTY"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnssMeasurementCallback$GnssMeasurementFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */