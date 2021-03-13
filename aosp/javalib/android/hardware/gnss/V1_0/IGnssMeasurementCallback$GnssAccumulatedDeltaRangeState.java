package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssAccumulatedDeltaRangeState {
  public static final short ADR_STATE_CYCLE_SLIP = 4;
  
  public static final short ADR_STATE_RESET = 2;
  
  public static final short ADR_STATE_UNKNOWN = 0;
  
  public static final short ADR_STATE_VALID = 1;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    arrayList.add("ADR_STATE_UNKNOWN");
    if ((paramShort & 0x1) == 1) {
      arrayList.add("ADR_STATE_VALID");
      s1 = (short)(false | true);
    } 
    short s2 = s1;
    if ((paramShort & 0x2) == 2) {
      arrayList.add("ADR_STATE_RESET");
      s2 = (short)(s1 | 0x2);
    } 
    s1 = s2;
    if ((paramShort & 0x4) == 4) {
      arrayList.add("ADR_STATE_CYCLE_SLIP");
      s1 = (short)(s2 | 0x4);
    } 
    if (paramShort != s1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Short.toUnsignedInt((short)(s1 & paramShort))));
      arrayList.add(stringBuilder.toString());
    } 
    return String.join(" | ", (Iterable)arrayList);
  }
  
  public static final String toString(short paramShort) {
    if (paramShort == 0)
      return "ADR_STATE_UNKNOWN"; 
    if (paramShort == 1)
      return "ADR_STATE_VALID"; 
    if (paramShort == 2)
      return "ADR_STATE_RESET"; 
    if (paramShort == 4)
      return "ADR_STATE_CYCLE_SLIP"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssMeasurementCallback$GnssAccumulatedDeltaRangeState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */