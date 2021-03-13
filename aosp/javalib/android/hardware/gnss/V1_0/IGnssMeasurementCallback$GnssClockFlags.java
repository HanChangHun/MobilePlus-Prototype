package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssClockFlags {
  public static final short HAS_BIAS = 8;
  
  public static final short HAS_BIAS_UNCERTAINTY = 16;
  
  public static final short HAS_DRIFT = 32;
  
  public static final short HAS_DRIFT_UNCERTAINTY = 64;
  
  public static final short HAS_FULL_BIAS = 4;
  
  public static final short HAS_LEAP_SECOND = 1;
  
  public static final short HAS_TIME_UNCERTAINTY = 2;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    if ((paramShort & 0x1) == 1) {
      arrayList.add("HAS_LEAP_SECOND");
      s1 = (short)(false | true);
    } 
    short s2 = s1;
    if ((paramShort & 0x2) == 2) {
      arrayList.add("HAS_TIME_UNCERTAINTY");
      s2 = (short)(s1 | 0x2);
    } 
    short s3 = s2;
    if ((paramShort & 0x4) == 4) {
      arrayList.add("HAS_FULL_BIAS");
      s3 = (short)(s2 | 0x4);
    } 
    s1 = s3;
    if ((paramShort & 0x8) == 8) {
      arrayList.add("HAS_BIAS");
      s1 = (short)(s3 | 0x8);
    } 
    s2 = s1;
    if ((paramShort & 0x10) == 16) {
      arrayList.add("HAS_BIAS_UNCERTAINTY");
      s2 = (short)(s1 | 0x10);
    } 
    s1 = s2;
    if ((paramShort & 0x20) == 32) {
      arrayList.add("HAS_DRIFT");
      s1 = (short)(s2 | 0x20);
    } 
    s2 = s1;
    if ((paramShort & 0x40) == 64) {
      arrayList.add("HAS_DRIFT_UNCERTAINTY");
      s2 = (short)(s1 | 0x40);
    } 
    if (paramShort != s2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Short.toUnsignedInt((short)(s2 & paramShort))));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(short paramShort) {
    if (paramShort == 1)
      return "HAS_LEAP_SECOND"; 
    if (paramShort == 2)
      return "HAS_TIME_UNCERTAINTY"; 
    if (paramShort == 4)
      return "HAS_FULL_BIAS"; 
    if (paramShort == 8)
      return "HAS_BIAS"; 
    if (paramShort == 16)
      return "HAS_BIAS_UNCERTAINTY"; 
    if (paramShort == 32)
      return "HAS_DRIFT"; 
    if (paramShort == 64)
      return "HAS_DRIFT_UNCERTAINTY"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssMeasurementCallback$GnssClockFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */