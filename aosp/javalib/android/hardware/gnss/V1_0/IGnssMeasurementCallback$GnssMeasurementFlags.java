package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssMeasurementFlags {
  public static final int HAS_AUTOMATIC_GAIN_CONTROL = 8192;
  
  public static final int HAS_CARRIER_CYCLES = 1024;
  
  public static final int HAS_CARRIER_FREQUENCY = 512;
  
  public static final int HAS_CARRIER_PHASE = 2048;
  
  public static final int HAS_CARRIER_PHASE_UNCERTAINTY = 4096;
  
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
    if (paramInt != j) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(j & paramInt));
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
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssMeasurementCallback$GnssMeasurementFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */