package android.hardware.contexthub.V1_0;

import java.util.ArrayList;

public final class SensorType {
  public static final int ACCELEROMETER = 1;
  
  public static final int AMBIENT_LIGHT_SENSOR = 6;
  
  public static final int AUDIO = 768;
  
  public static final int BAROMETER = 4;
  
  public static final int BLE = 1280;
  
  public static final int CAMERA = 1024;
  
  public static final int GPS = 256;
  
  public static final int GYROSCOPE = 2;
  
  public static final int INSTANT_MOTION_DETECT = 8;
  
  public static final int MAGNETOMETER = 3;
  
  public static final int PRIVATE_SENSOR_BASE = 65536;
  
  public static final int PROXIMITY_SENSOR = 5;
  
  public static final int RESERVED = 0;
  
  public static final int STATIONARY_DETECT = 7;
  
  public static final int WIFI = 512;
  
  public static final int WWAN = 1536;
  
  public static final String dumpBitfield(int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    arrayList.add("RESERVED");
    if ((paramInt & 0x1) == 1) {
      arrayList.add("ACCELEROMETER");
      i = false | true;
    } 
    int j = i;
    if ((paramInt & 0x2) == 2) {
      arrayList.add("GYROSCOPE");
      j = i | 0x2;
    } 
    i = j;
    if ((paramInt & 0x3) == 3) {
      arrayList.add("MAGNETOMETER");
      i = j | 0x3;
    } 
    int k = i;
    if ((paramInt & 0x4) == 4) {
      arrayList.add("BAROMETER");
      k = i | 0x4;
    } 
    j = k;
    if ((paramInt & 0x5) == 5) {
      arrayList.add("PROXIMITY_SENSOR");
      j = k | 0x5;
    } 
    i = j;
    if ((paramInt & 0x6) == 6) {
      arrayList.add("AMBIENT_LIGHT_SENSOR");
      i = j | 0x6;
    } 
    j = i;
    if ((paramInt & 0x7) == 7) {
      arrayList.add("STATIONARY_DETECT");
      j = i | 0x7;
    } 
    k = j;
    if ((paramInt & 0x8) == 8) {
      arrayList.add("INSTANT_MOTION_DETECT");
      k = j | 0x8;
    } 
    i = k;
    if ((paramInt & 0x100) == 256) {
      arrayList.add("GPS");
      i = k | 0x100;
    } 
    j = i;
    if ((paramInt & 0x200) == 512) {
      arrayList.add("WIFI");
      j = i | 0x200;
    } 
    k = j;
    if ((paramInt & 0x300) == 768) {
      arrayList.add("AUDIO");
      k = j | 0x300;
    } 
    i = k;
    if ((paramInt & 0x400) == 1024) {
      arrayList.add("CAMERA");
      i = k | 0x400;
    } 
    j = i;
    if ((paramInt & 0x500) == 1280) {
      arrayList.add("BLE");
      j = i | 0x500;
    } 
    i = j;
    if ((paramInt & 0x600) == 1536) {
      arrayList.add("WWAN");
      i = j | 0x600;
    } 
    j = i;
    if ((paramInt & 0x10000) == 65536) {
      arrayList.add("PRIVATE_SENSOR_BASE");
      j = i | 0x10000;
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
      return "RESERVED"; 
    if (paramInt == 1)
      return "ACCELEROMETER"; 
    if (paramInt == 2)
      return "GYROSCOPE"; 
    if (paramInt == 3)
      return "MAGNETOMETER"; 
    if (paramInt == 4)
      return "BAROMETER"; 
    if (paramInt == 5)
      return "PROXIMITY_SENSOR"; 
    if (paramInt == 6)
      return "AMBIENT_LIGHT_SENSOR"; 
    if (paramInt == 7)
      return "STATIONARY_DETECT"; 
    if (paramInt == 8)
      return "INSTANT_MOTION_DETECT"; 
    if (paramInt == 256)
      return "GPS"; 
    if (paramInt == 512)
      return "WIFI"; 
    if (paramInt == 768)
      return "AUDIO"; 
    if (paramInt == 1024)
      return "CAMERA"; 
    if (paramInt == 1280)
      return "BLE"; 
    if (paramInt == 1536)
      return "WWAN"; 
    if (paramInt == 65536)
      return "PRIVATE_SENSOR_BASE"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/contexthub/V1_0/SensorType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */