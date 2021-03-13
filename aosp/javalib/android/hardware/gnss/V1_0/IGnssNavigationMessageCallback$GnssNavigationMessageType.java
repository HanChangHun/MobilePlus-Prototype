package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssNavigationMessageType {
  public static final short BDS_D1 = 1281;
  
  public static final short BDS_D2 = 1282;
  
  public static final short GAL_F = 1538;
  
  public static final short GAL_I = 1537;
  
  public static final short GLO_L1CA = 769;
  
  public static final short GPS_CNAV2 = 260;
  
  public static final short GPS_L1CA = 257;
  
  public static final short GPS_L2CNAV = 258;
  
  public static final short GPS_L5CNAV = 259;
  
  public static final short UNKNOWN = 0;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    arrayList.add("UNKNOWN");
    if ((paramShort & 0x101) == 257) {
      arrayList.add("GPS_L1CA");
      s1 = (short)(Character.MIN_VALUE | 0x101);
    } 
    short s2 = s1;
    if ((paramShort & 0x102) == 258) {
      arrayList.add("GPS_L2CNAV");
      s2 = (short)(s1 | 0x102);
    } 
    s1 = s2;
    if ((paramShort & 0x103) == 259) {
      arrayList.add("GPS_L5CNAV");
      s1 = (short)(s2 | 0x103);
    } 
    s2 = s1;
    if ((paramShort & 0x104) == 260) {
      arrayList.add("GPS_CNAV2");
      s2 = (short)(s1 | 0x104);
    } 
    s1 = s2;
    if ((paramShort & 0x301) == 769) {
      arrayList.add("GLO_L1CA");
      s1 = (short)(s2 | 0x301);
    } 
    s2 = s1;
    if ((paramShort & 0x501) == 1281) {
      arrayList.add("BDS_D1");
      s2 = (short)(s1 | 0x501);
    } 
    s1 = s2;
    if ((paramShort & 0x502) == 1282) {
      arrayList.add("BDS_D2");
      s1 = (short)(s2 | 0x502);
    } 
    s2 = s1;
    if ((paramShort & 0x601) == 1537) {
      arrayList.add("GAL_I");
      s2 = (short)(s1 | 0x601);
    } 
    s1 = s2;
    if ((paramShort & 0x602) == 1538) {
      arrayList.add("GAL_F");
      s1 = (short)(s2 | 0x602);
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
      return "UNKNOWN"; 
    if (paramShort == 257)
      return "GPS_L1CA"; 
    if (paramShort == 258)
      return "GPS_L2CNAV"; 
    if (paramShort == 259)
      return "GPS_L5CNAV"; 
    if (paramShort == 260)
      return "GPS_CNAV2"; 
    if (paramShort == 769)
      return "GLO_L1CA"; 
    if (paramShort == 1281)
      return "BDS_D1"; 
    if (paramShort == 1282)
      return "BDS_D2"; 
    if (paramShort == 1537)
      return "GAL_I"; 
    if (paramShort == 1538)
      return "GAL_F"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNavigationMessageCallback$GnssNavigationMessageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */