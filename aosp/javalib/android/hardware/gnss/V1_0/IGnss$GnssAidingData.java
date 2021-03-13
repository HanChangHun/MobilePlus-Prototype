package android.hardware.gnss.V1_0;

import java.util.ArrayList;

public final class GnssAidingData {
  public static final short DELETE_ALL = -1;
  
  public static final short DELETE_ALMANAC = 2;
  
  public static final short DELETE_CELLDB_INFO = -32768;
  
  public static final short DELETE_EPHEMERIS = 1;
  
  public static final short DELETE_HEALTH = 64;
  
  public static final short DELETE_IONO = 16;
  
  public static final short DELETE_POSITION = 4;
  
  public static final short DELETE_RTI = 1024;
  
  public static final short DELETE_SADATA = 512;
  
  public static final short DELETE_SVDIR = 128;
  
  public static final short DELETE_SVSTEER = 256;
  
  public static final short DELETE_TIME = 8;
  
  public static final short DELETE_UTC = 32;
  
  public static final String dumpBitfield(short paramShort) {
    ArrayList<String> arrayList = new ArrayList();
    short s1 = 0;
    if ((paramShort & 0x1) == 1) {
      arrayList.add("DELETE_EPHEMERIS");
      s1 = (short)(false | true);
    } 
    short s2 = s1;
    if ((paramShort & 0x2) == 2) {
      arrayList.add("DELETE_ALMANAC");
      s2 = (short)(s1 | 0x2);
    } 
    s1 = s2;
    if ((paramShort & 0x4) == 4) {
      arrayList.add("DELETE_POSITION");
      s1 = (short)(s2 | 0x4);
    } 
    s2 = s1;
    if ((paramShort & 0x8) == 8) {
      arrayList.add("DELETE_TIME");
      s2 = (short)(s1 | 0x8);
    } 
    s1 = s2;
    if ((paramShort & 0x10) == 16) {
      arrayList.add("DELETE_IONO");
      s1 = (short)(s2 | 0x10);
    } 
    s2 = s1;
    if ((paramShort & 0x20) == 32) {
      arrayList.add("DELETE_UTC");
      s2 = (short)(s1 | 0x20);
    } 
    s1 = s2;
    if ((paramShort & 0x40) == 64) {
      arrayList.add("DELETE_HEALTH");
      s1 = (short)(s2 | 0x40);
    } 
    s2 = s1;
    if ((paramShort & 0x80) == 128) {
      arrayList.add("DELETE_SVDIR");
      s2 = (short)(s1 | 0x80);
    } 
    s1 = s2;
    if ((paramShort & 0x100) == 256) {
      arrayList.add("DELETE_SVSTEER");
      s1 = (short)(s2 | 0x100);
    } 
    short s3 = s1;
    if ((paramShort & 0x200) == 512) {
      arrayList.add("DELETE_SADATA");
      s3 = (short)(s1 | 0x200);
    } 
    s2 = s3;
    if ((paramShort & 0x400) == 1024) {
      arrayList.add("DELETE_RTI");
      s2 = (short)(s3 | 0x400);
    } 
    s1 = s2;
    if ((paramShort & Short.MIN_VALUE) == -32768) {
      arrayList.add("DELETE_CELLDB_INFO");
      s1 = (short)(s2 | 0xFFFF8000);
    } 
    s2 = s1;
    if ((paramShort & 0xFFFFFFFF) == -1) {
      arrayList.add("DELETE_ALL");
      s2 = (short)(s1 | 0xFFFFFFFF);
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
      return "DELETE_EPHEMERIS"; 
    if (paramShort == 2)
      return "DELETE_ALMANAC"; 
    if (paramShort == 4)
      return "DELETE_POSITION"; 
    if (paramShort == 8)
      return "DELETE_TIME"; 
    if (paramShort == 16)
      return "DELETE_IONO"; 
    if (paramShort == 32)
      return "DELETE_UTC"; 
    if (paramShort == 64)
      return "DELETE_HEALTH"; 
    if (paramShort == 128)
      return "DELETE_SVDIR"; 
    if (paramShort == 256)
      return "DELETE_SVSTEER"; 
    if (paramShort == 512)
      return "DELETE_SADATA"; 
    if (paramShort == 1024)
      return "DELETE_RTI"; 
    if (paramShort == Short.MIN_VALUE)
      return "DELETE_CELLDB_INFO"; 
    if (paramShort == -1)
      return "DELETE_ALL"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(paramShort)));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnss$GnssAidingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */