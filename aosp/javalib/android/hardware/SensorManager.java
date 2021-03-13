package android.hardware;

import android.annotation.SystemApi;
import android.os.Handler;
import android.os.MemoryFile;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class SensorManager {
  public static final int AXIS_MINUS_X = 129;
  
  public static final int AXIS_MINUS_Y = 130;
  
  public static final int AXIS_MINUS_Z = 131;
  
  public static final int AXIS_X = 1;
  
  public static final int AXIS_Y = 2;
  
  public static final int AXIS_Z = 3;
  
  @Deprecated
  public static final int DATA_X = 0;
  
  @Deprecated
  public static final int DATA_Y = 1;
  
  @Deprecated
  public static final int DATA_Z = 2;
  
  public static final float GRAVITY_DEATH_STAR_I = 3.5303614E-7F;
  
  public static final float GRAVITY_EARTH = 9.80665F;
  
  public static final float GRAVITY_JUPITER = 23.12F;
  
  public static final float GRAVITY_MARS = 3.71F;
  
  public static final float GRAVITY_MERCURY = 3.7F;
  
  public static final float GRAVITY_MOON = 1.6F;
  
  public static final float GRAVITY_NEPTUNE = 11.0F;
  
  public static final float GRAVITY_PLUTO = 0.6F;
  
  public static final float GRAVITY_SATURN = 8.96F;
  
  public static final float GRAVITY_SUN = 275.0F;
  
  public static final float GRAVITY_THE_ISLAND = 4.815162F;
  
  public static final float GRAVITY_URANUS = 8.69F;
  
  public static final float GRAVITY_VENUS = 8.87F;
  
  public static final float LIGHT_CLOUDY = 100.0F;
  
  public static final float LIGHT_FULLMOON = 0.25F;
  
  public static final float LIGHT_NO_MOON = 0.001F;
  
  public static final float LIGHT_OVERCAST = 10000.0F;
  
  public static final float LIGHT_SHADE = 20000.0F;
  
  public static final float LIGHT_SUNLIGHT = 110000.0F;
  
  public static final float LIGHT_SUNLIGHT_MAX = 120000.0F;
  
  public static final float LIGHT_SUNRISE = 400.0F;
  
  public static final float MAGNETIC_FIELD_EARTH_MAX = 60.0F;
  
  public static final float MAGNETIC_FIELD_EARTH_MIN = 30.0F;
  
  public static final float PRESSURE_STANDARD_ATMOSPHERE = 1013.25F;
  
  @Deprecated
  public static final int RAW_DATA_INDEX = 3;
  
  @Deprecated
  public static final int RAW_DATA_X = 3;
  
  @Deprecated
  public static final int RAW_DATA_Y = 4;
  
  @Deprecated
  public static final int RAW_DATA_Z = 5;
  
  @Deprecated
  public static final int SENSOR_ACCELEROMETER = 2;
  
  @Deprecated
  public static final int SENSOR_ALL = 127;
  
  public static final int SENSOR_DELAY_FASTEST = 0;
  
  public static final int SENSOR_DELAY_GAME = 1;
  
  public static final int SENSOR_DELAY_NORMAL = 3;
  
  public static final int SENSOR_DELAY_UI = 2;
  
  @Deprecated
  public static final int SENSOR_LIGHT = 16;
  
  @Deprecated
  public static final int SENSOR_MAGNETIC_FIELD = 8;
  
  @Deprecated
  public static final int SENSOR_MAX = 64;
  
  @Deprecated
  public static final int SENSOR_MIN = 1;
  
  @Deprecated
  public static final int SENSOR_ORIENTATION = 1;
  
  @Deprecated
  public static final int SENSOR_ORIENTATION_RAW = 128;
  
  @Deprecated
  public static final int SENSOR_PROXIMITY = 32;
  
  public static final int SENSOR_STATUS_ACCURACY_HIGH = 3;
  
  public static final int SENSOR_STATUS_ACCURACY_LOW = 1;
  
  public static final int SENSOR_STATUS_ACCURACY_MEDIUM = 2;
  
  public static final int SENSOR_STATUS_NO_CONTACT = -1;
  
  public static final int SENSOR_STATUS_UNRELIABLE = 0;
  
  @Deprecated
  public static final int SENSOR_TEMPERATURE = 4;
  
  @Deprecated
  public static final int SENSOR_TRICORDER = 64;
  
  public static final float STANDARD_GRAVITY = 9.80665F;
  
  protected static final String TAG = "SensorManager";
  
  private static final float[] sTempMatrix = new float[16];
  
  private LegacySensorManager mLegacySensorManager;
  
  private final SparseArray<List<Sensor>> mSensorListByType = new SparseArray();
  
  public static float getAltitude(float paramFloat1, float paramFloat2) {
    return (1.0F - (float)Math.pow((paramFloat2 / paramFloat1), 0.19029495120048523D)) * 44330.0F;
  }
  
  public static void getAngleChange(float[] paramArrayOffloat1, float[] paramArrayOffloat2, float[] paramArrayOffloat3) {
    float f1 = 0.0F;
    float f2 = 0.0F;
    float f3 = 0.0F;
    float f4 = 0.0F;
    float f5 = 0.0F;
    float f6 = 0.0F;
    float f7 = 0.0F;
    float f8 = 0.0F;
    float f9 = 0.0F;
    float f10 = 0.0F;
    float f11 = 0.0F;
    float f12 = 0.0F;
    float f13 = 0.0F;
    float f14 = 0.0F;
    float f15 = 0.0F;
    float f16 = 0.0F;
    float f17 = 0.0F;
    float f18 = 0.0F;
    if (paramArrayOffloat2.length == 9) {
      f1 = paramArrayOffloat2[0];
      f2 = paramArrayOffloat2[1];
      f3 = paramArrayOffloat2[2];
      f4 = paramArrayOffloat2[3];
      f5 = paramArrayOffloat2[4];
      f6 = paramArrayOffloat2[5];
      f7 = paramArrayOffloat2[6];
      f8 = paramArrayOffloat2[7];
      f9 = paramArrayOffloat2[8];
    } else if (paramArrayOffloat2.length == 16) {
      f1 = paramArrayOffloat2[0];
      f2 = paramArrayOffloat2[1];
      f3 = paramArrayOffloat2[2];
      f4 = paramArrayOffloat2[4];
      f5 = paramArrayOffloat2[5];
      f6 = paramArrayOffloat2[6];
      f7 = paramArrayOffloat2[8];
      f8 = paramArrayOffloat2[9];
      f9 = paramArrayOffloat2[10];
    } 
    if (paramArrayOffloat3.length == 9) {
      f10 = paramArrayOffloat3[0];
      f11 = paramArrayOffloat3[1];
      f12 = paramArrayOffloat3[2];
      f13 = paramArrayOffloat3[3];
      f14 = paramArrayOffloat3[4];
      f15 = paramArrayOffloat3[5];
      f16 = paramArrayOffloat3[6];
      f17 = paramArrayOffloat3[7];
      f18 = paramArrayOffloat3[8];
    } else if (paramArrayOffloat3.length == 16) {
      f10 = paramArrayOffloat3[0];
      f11 = paramArrayOffloat3[1];
      f12 = paramArrayOffloat3[2];
      f13 = paramArrayOffloat3[4];
      f14 = paramArrayOffloat3[5];
      f15 = paramArrayOffloat3[6];
      f16 = paramArrayOffloat3[8];
      f17 = paramArrayOffloat3[9];
      f18 = paramArrayOffloat3[10];
    } 
    paramArrayOffloat1[0] = (float)Math.atan2((f10 * f2 + f13 * f5 + f16 * f8), (f11 * f2 + f14 * f5 + f17 * f8));
    paramArrayOffloat1[1] = (float)Math.asin(-(f12 * f2 + f15 * f5 + f18 * f8));
    paramArrayOffloat1[2] = (float)Math.atan2(-(f12 * f1 + f15 * f4 + f18 * f7), (f12 * f3 + f15 * f6 + f18 * f9));
  }
  
  private static int getDelay(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt == 3)
            paramInt = 200000; 
        } else {
          paramInt = 66667;
        } 
      } else {
        paramInt = 20000;
      } 
    } else {
      paramInt = 0;
    } 
    return paramInt;
  }
  
  public static float getInclination(float[] paramArrayOffloat) {
    return (paramArrayOffloat.length == 9) ? (float)Math.atan2(paramArrayOffloat[5], paramArrayOffloat[4]) : (float)Math.atan2(paramArrayOffloat[6], paramArrayOffloat[5]);
  }
  
  private LegacySensorManager getLegacySensorManager() {
    synchronized (this.mSensorListByType) {
      if (this.mLegacySensorManager == null) {
        Log.i("SensorManager", "This application is using deprecated SensorManager API which will be removed someday.  Please consider switching to the new API.");
        LegacySensorManager legacySensorManager = new LegacySensorManager();
        this(this);
        this.mLegacySensorManager = legacySensorManager;
      } 
      return this.mLegacySensorManager;
    } 
  }
  
  public static float[] getOrientation(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if (paramArrayOffloat1.length == 9) {
      paramArrayOffloat2[0] = (float)Math.atan2(paramArrayOffloat1[1], paramArrayOffloat1[4]);
      paramArrayOffloat2[1] = (float)Math.asin(-paramArrayOffloat1[7]);
      paramArrayOffloat2[2] = (float)Math.atan2(-paramArrayOffloat1[6], paramArrayOffloat1[8]);
    } else {
      paramArrayOffloat2[0] = (float)Math.atan2(paramArrayOffloat1[1], paramArrayOffloat1[5]);
      paramArrayOffloat2[1] = (float)Math.asin(-paramArrayOffloat1[9]);
      paramArrayOffloat2[2] = (float)Math.atan2(-paramArrayOffloat1[8], paramArrayOffloat1[10]);
    } 
    return paramArrayOffloat2;
  }
  
  public static void getQuaternionFromVector(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    if (paramArrayOffloat2.length >= 4) {
      paramArrayOffloat1[0] = paramArrayOffloat2[3];
    } else {
      paramArrayOffloat1[0] = 1.0F - paramArrayOffloat2[0] * paramArrayOffloat2[0] - paramArrayOffloat2[1] * paramArrayOffloat2[1] - paramArrayOffloat2[2] * paramArrayOffloat2[2];
      float f1 = paramArrayOffloat1[0];
      float f2 = 0.0F;
      if (f1 > 0.0F)
        f2 = (float)Math.sqrt(paramArrayOffloat1[0]); 
      paramArrayOffloat1[0] = f2;
    } 
    paramArrayOffloat1[1] = paramArrayOffloat2[0];
    paramArrayOffloat1[2] = paramArrayOffloat2[1];
    paramArrayOffloat1[3] = paramArrayOffloat2[2];
  }
  
  public static boolean getRotationMatrix(float[] paramArrayOffloat1, float[] paramArrayOffloat2, float[] paramArrayOffloat3, float[] paramArrayOffloat4) {
    float f1 = paramArrayOffloat3[0];
    float f2 = paramArrayOffloat3[1];
    float f3 = paramArrayOffloat3[2];
    if (f1 * f1 + f2 * f2 + f3 * f3 < 0.96236104F)
      return false; 
    float f4 = paramArrayOffloat4[0];
    float f5 = paramArrayOffloat4[1];
    float f6 = paramArrayOffloat4[2];
    float f7 = f5 * f3 - f6 * f2;
    float f8 = f6 * f1 - f4 * f3;
    float f9 = f4 * f2 - f5 * f1;
    float f10 = (float)Math.sqrt((f7 * f7 + f8 * f8 + f9 * f9));
    if (f10 < 0.1F)
      return false; 
    f10 = 1.0F / f10;
    f7 *= f10;
    f8 *= f10;
    f9 *= f10;
    f10 = 1.0F / (float)Math.sqrt((f1 * f1 + f2 * f2 + f3 * f3));
    f1 *= f10;
    f2 *= f10;
    f3 *= f10;
    float f11 = f2 * f9 - f3 * f8;
    f10 = f3 * f7 - f1 * f9;
    float f12 = f1 * f8 - f2 * f7;
    if (paramArrayOffloat1 != null)
      if (paramArrayOffloat1.length == 9) {
        paramArrayOffloat1[0] = f7;
        paramArrayOffloat1[1] = f8;
        paramArrayOffloat1[2] = f9;
        paramArrayOffloat1[3] = f11;
        paramArrayOffloat1[4] = f10;
        paramArrayOffloat1[5] = f12;
        paramArrayOffloat1[6] = f1;
        paramArrayOffloat1[7] = f2;
        paramArrayOffloat1[8] = f3;
      } else if (paramArrayOffloat1.length == 16) {
        paramArrayOffloat1[0] = f7;
        paramArrayOffloat1[1] = f8;
        paramArrayOffloat1[2] = f9;
        paramArrayOffloat1[3] = 0.0F;
        paramArrayOffloat1[4] = f11;
        paramArrayOffloat1[5] = f10;
        paramArrayOffloat1[6] = f12;
        paramArrayOffloat1[7] = 0.0F;
        paramArrayOffloat1[8] = f1;
        paramArrayOffloat1[9] = f2;
        paramArrayOffloat1[10] = f3;
        paramArrayOffloat1[11] = 0.0F;
        paramArrayOffloat1[12] = 0.0F;
        paramArrayOffloat1[13] = 0.0F;
        paramArrayOffloat1[14] = 0.0F;
        paramArrayOffloat1[15] = 1.0F;
      }  
    if (paramArrayOffloat2 != null) {
      f7 = 1.0F / (float)Math.sqrt((f4 * f4 + f5 * f5 + f6 * f6));
      f8 = (f4 * f11 + f5 * f10 + f6 * f12) * f7;
      f4 = (f4 * f1 + f5 * f2 + f6 * f3) * f7;
      if (paramArrayOffloat2.length == 9) {
        paramArrayOffloat2[0] = 1.0F;
        paramArrayOffloat2[1] = 0.0F;
        paramArrayOffloat2[2] = 0.0F;
        paramArrayOffloat2[3] = 0.0F;
        paramArrayOffloat2[4] = f8;
        paramArrayOffloat2[5] = f4;
        paramArrayOffloat2[6] = 0.0F;
        paramArrayOffloat2[7] = -f4;
        paramArrayOffloat2[8] = f8;
      } else if (paramArrayOffloat2.length == 16) {
        paramArrayOffloat2[0] = 1.0F;
        paramArrayOffloat2[1] = 0.0F;
        paramArrayOffloat2[2] = 0.0F;
        paramArrayOffloat2[4] = 0.0F;
        paramArrayOffloat2[5] = f8;
        paramArrayOffloat2[6] = f4;
        paramArrayOffloat2[8] = 0.0F;
        paramArrayOffloat2[9] = -f4;
        paramArrayOffloat2[10] = f8;
        paramArrayOffloat2[14] = 0.0F;
        paramArrayOffloat2[13] = 0.0F;
        paramArrayOffloat2[12] = 0.0F;
        paramArrayOffloat2[11] = 0.0F;
        paramArrayOffloat2[7] = 0.0F;
        paramArrayOffloat2[3] = 0.0F;
        paramArrayOffloat2[15] = 1.0F;
      } 
    } 
    return true;
  }
  
  public static void getRotationMatrixFromVector(float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
    float f1 = paramArrayOffloat2[0];
    float f2 = paramArrayOffloat2[1];
    float f3 = paramArrayOffloat2[2];
    if (paramArrayOffloat2.length >= 4) {
      f4 = paramArrayOffloat2[3];
    } else {
      f4 = 1.0F - f1 * f1 - f2 * f2 - f3 * f3;
      if (f4 > 0.0F) {
        f4 = (float)Math.sqrt(f4);
      } else {
        f4 = 0.0F;
      } 
    } 
    float f5 = f1 * 2.0F * f1;
    float f6 = f2 * 2.0F * f2;
    float f7 = f3 * 2.0F * f3;
    float f8 = f1 * 2.0F * f2;
    float f9 = f3 * 2.0F * f4;
    float f10 = f1 * 2.0F * f3;
    float f11 = f2 * 2.0F * f4;
    f3 = f2 * 2.0F * f3;
    float f4 = 2.0F * f1 * f4;
    if (paramArrayOffloat1.length == 9) {
      paramArrayOffloat1[0] = 1.0F - f6 - f7;
      paramArrayOffloat1[1] = f8 - f9;
      paramArrayOffloat1[2] = f10 + f11;
      paramArrayOffloat1[3] = f8 + f9;
      paramArrayOffloat1[4] = 1.0F - f5 - f7;
      paramArrayOffloat1[5] = f3 - f4;
      paramArrayOffloat1[6] = f10 - f11;
      paramArrayOffloat1[7] = f3 + f4;
      paramArrayOffloat1[8] = 1.0F - f5 - f6;
    } else if (paramArrayOffloat1.length == 16) {
      paramArrayOffloat1[0] = 1.0F - f6 - f7;
      paramArrayOffloat1[1] = f8 - f9;
      paramArrayOffloat1[2] = f10 + f11;
      paramArrayOffloat1[3] = 0.0F;
      paramArrayOffloat1[4] = f8 + f9;
      paramArrayOffloat1[5] = 1.0F - f5 - f7;
      paramArrayOffloat1[6] = f3 - f4;
      paramArrayOffloat1[7] = 0.0F;
      paramArrayOffloat1[8] = f10 - f11;
      paramArrayOffloat1[9] = f3 + f4;
      paramArrayOffloat1[10] = 1.0F - f5 - f6;
      paramArrayOffloat1[11] = 0.0F;
      paramArrayOffloat1[14] = 0.0F;
      paramArrayOffloat1[13] = 0.0F;
      paramArrayOffloat1[12] = 0.0F;
      paramArrayOffloat1[15] = 1.0F;
    } 
  }
  
  public static boolean remapCoordinateSystem(float[] paramArrayOffloat1, int paramInt1, int paramInt2, float[] paramArrayOffloat2) {
    if (paramArrayOffloat1 == paramArrayOffloat2)
      synchronized (sTempMatrix) {
        if (remapCoordinateSystemImpl(paramArrayOffloat1, paramInt1, paramInt2, null)) {
          paramInt2 = paramArrayOffloat2.length;
          for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++)
            paramArrayOffloat2[paramInt1] = null[paramInt1]; 
          return true;
        } 
      }  
    return remapCoordinateSystemImpl(paramArrayOffloat1, paramInt1, paramInt2, paramArrayOffloat2);
  }
  
  private static boolean remapCoordinateSystemImpl(float[] paramArrayOffloat1, int paramInt1, int paramInt2, float[] paramArrayOffloat2) {
    int i = paramArrayOffloat2.length;
    int j = paramArrayOffloat1.length;
    boolean bool = false;
    if (j != i)
      return false; 
    if ((paramInt1 & 0x7C) != 0 || (paramInt2 & 0x7C) != 0)
      return false; 
    if ((paramInt1 & 0x3) == 0 || (paramInt2 & 0x3) == 0)
      return false; 
    if ((paramInt1 & 0x3) == (paramInt2 & 0x3))
      return false; 
    int k = paramInt1 ^ paramInt2;
    int m = (paramInt1 & 0x3) - 1;
    int n = (paramInt2 & 0x3) - 1;
    int i1 = (k & 0x3) - 1;
    j = k;
    if ((m ^ (i1 + 1) % 3 | n ^ (i1 + 2) % 3) != 0)
      j = k ^ 0x80; 
    if (paramInt1 >= 128) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    if (paramInt2 >= 128) {
      paramInt2 = 1;
    } else {
      paramInt2 = 0;
    } 
    if (j >= 128)
      bool = true; 
    if (i == 16) {
      j = 4;
    } else {
      j = 3;
    } 
    for (k = 0; k < 3; k++) {
      int i2 = k * j;
      for (byte b = 0; b < 3; b++) {
        if (m == b) {
          float f;
          if (paramInt1 != 0) {
            f = -paramArrayOffloat1[i2 + 0];
          } else {
            f = paramArrayOffloat1[i2 + 0];
          } 
          paramArrayOffloat2[i2 + b] = f;
        } 
        if (n == b) {
          float f;
          if (paramInt2 != 0) {
            f = -paramArrayOffloat1[i2 + 1];
          } else {
            f = paramArrayOffloat1[i2 + 1];
          } 
          paramArrayOffloat2[i2 + b] = f;
        } 
        if (i1 == b) {
          float f;
          int i3 = i2 + 2;
          if (bool) {
            f = -paramArrayOffloat1[i3];
          } else {
            f = paramArrayOffloat1[i3];
          } 
          paramArrayOffloat2[i2 + b] = f;
        } 
      } 
    } 
    if (i == 16) {
      paramArrayOffloat2[14] = 0.0F;
      paramArrayOffloat2[13] = 0.0F;
      paramArrayOffloat2[12] = 0.0F;
      paramArrayOffloat2[11] = 0.0F;
      paramArrayOffloat2[7] = 0.0F;
      paramArrayOffloat2[3] = 0.0F;
      paramArrayOffloat2[15] = 1.0F;
    } 
    return true;
  }
  
  public boolean cancelTriggerSensor(TriggerEventListener paramTriggerEventListener, Sensor paramSensor) {
    return cancelTriggerSensorImpl(paramTriggerEventListener, paramSensor, true);
  }
  
  protected abstract boolean cancelTriggerSensorImpl(TriggerEventListener paramTriggerEventListener, Sensor paramSensor, boolean paramBoolean);
  
  protected abstract int configureDirectChannelImpl(SensorDirectChannel paramSensorDirectChannel, Sensor paramSensor, int paramInt);
  
  public SensorDirectChannel createDirectChannel(HardwareBuffer paramHardwareBuffer) {
    return createDirectChannelImpl(null, paramHardwareBuffer);
  }
  
  public SensorDirectChannel createDirectChannel(MemoryFile paramMemoryFile) {
    return createDirectChannelImpl(paramMemoryFile, null);
  }
  
  protected abstract SensorDirectChannel createDirectChannelImpl(MemoryFile paramMemoryFile, HardwareBuffer paramHardwareBuffer);
  
  void destroyDirectChannel(SensorDirectChannel paramSensorDirectChannel) {
    destroyDirectChannelImpl(paramSensorDirectChannel);
  }
  
  protected abstract void destroyDirectChannelImpl(SensorDirectChannel paramSensorDirectChannel);
  
  public boolean flush(SensorEventListener paramSensorEventListener) {
    return flushImpl(paramSensorEventListener);
  }
  
  protected abstract boolean flushImpl(SensorEventListener paramSensorEventListener);
  
  public Sensor getDefaultSensor(int paramInt) {
    List<Sensor> list = getSensorList(paramInt);
    boolean bool = false;
    if (paramInt == 8 || paramInt == 17 || paramInt == 22 || paramInt == 23 || paramInt == 24 || paramInt == 25 || paramInt == 26 || paramInt == 32 || paramInt == 36)
      bool = true; 
    for (Sensor sensor : list) {
      if (sensor.isWakeUpSensor() == bool)
        return sensor; 
    } 
    return null;
  }
  
  public Sensor getDefaultSensor(int paramInt, boolean paramBoolean) {
    for (Sensor sensor : getSensorList(paramInt)) {
      if (sensor.isWakeUpSensor() == paramBoolean)
        return sensor; 
    } 
    return null;
  }
  
  public List<Sensor> getDynamicSensorList(int paramInt) {
    List<Sensor> list = getFullDynamicSensorList();
    if (paramInt == -1)
      return Collections.unmodifiableList(list); 
    ArrayList<Sensor> arrayList = new ArrayList();
    for (Sensor sensor : list) {
      if (sensor.getType() == paramInt)
        arrayList.add(sensor); 
    } 
    return Collections.unmodifiableList(arrayList);
  }
  
  protected abstract List<Sensor> getFullDynamicSensorList();
  
  protected abstract List<Sensor> getFullSensorList();
  
  public List<Sensor> getSensorList(int paramInt) {
    List<Sensor> list = getFullSensorList();
    synchronized (this.mSensorListByType) {
      List<Sensor> list1 = (List)this.mSensorListByType.get(paramInt);
      List<Sensor> list2 = list1;
      if (list1 == null) {
        Sensor sensor;
        if (paramInt != -1) {
          list2 = new ArrayList();
          super();
          Iterator<Sensor> iterator = list.iterator();
          while (true) {
            list = list2;
            if (iterator.hasNext()) {
              sensor = iterator.next();
              if (sensor.getType() == paramInt)
                list2.add(sensor); 
              continue;
            } 
            break;
          } 
        } 
        list2 = Collections.unmodifiableList((List<? extends Sensor>)sensor);
        this.mSensorListByType.append(paramInt, list2);
      } 
      return list2;
    } 
  }
  
  @Deprecated
  public int getSensors() {
    return getLegacySensorManager().getSensors();
  }
  
  @SystemApi
  public boolean initDataInjection(boolean paramBoolean) {
    return initDataInjectionImpl(paramBoolean);
  }
  
  protected abstract boolean initDataInjectionImpl(boolean paramBoolean);
  
  @SystemApi
  public boolean injectSensorData(Sensor paramSensor, float[] paramArrayOffloat, int paramInt, long paramLong) {
    if (paramSensor != null) {
      if (paramSensor.isDataInjectionSupported()) {
        if (paramArrayOffloat != null) {
          int i = Sensor.getMaxLengthValuesArray(paramSensor, 23);
          if (paramArrayOffloat.length == i) {
            if (paramInt >= -1 && paramInt <= 3) {
              if (paramLong > 0L)
                return injectSensorDataImpl(paramSensor, paramArrayOffloat, paramInt, paramLong); 
              throw new IllegalArgumentException("Negative or zero sensor timestamp");
            } 
            throw new IllegalArgumentException("Invalid sensor accuracy");
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Wrong number of values for sensor ");
          stringBuilder.append(paramSensor.getName());
          stringBuilder.append(" actual=");
          stringBuilder.append(paramArrayOffloat.length);
          stringBuilder.append(" expected=");
          stringBuilder.append(i);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
        throw new IllegalArgumentException("sensor data cannot be null");
      } 
      throw new IllegalArgumentException("sensor does not support data injection");
    } 
    throw new IllegalArgumentException("sensor cannot be null");
  }
  
  protected abstract boolean injectSensorDataImpl(Sensor paramSensor, float[] paramArrayOffloat, int paramInt, long paramLong);
  
  public boolean isDynamicSensorDiscoverySupported() {
    boolean bool;
    if (getSensorList(32).size() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void registerDynamicSensorCallback(DynamicSensorCallback paramDynamicSensorCallback) {
    registerDynamicSensorCallback(paramDynamicSensorCallback, null);
  }
  
  public void registerDynamicSensorCallback(DynamicSensorCallback paramDynamicSensorCallback, Handler paramHandler) {
    registerDynamicSensorCallbackImpl(paramDynamicSensorCallback, paramHandler);
  }
  
  protected abstract void registerDynamicSensorCallbackImpl(DynamicSensorCallback paramDynamicSensorCallback, Handler paramHandler);
  
  public boolean registerListener(SensorEventListener paramSensorEventListener, Sensor paramSensor, int paramInt) {
    return registerListener(paramSensorEventListener, paramSensor, paramInt, (Handler)null);
  }
  
  public boolean registerListener(SensorEventListener paramSensorEventListener, Sensor paramSensor, int paramInt1, int paramInt2) {
    return registerListenerImpl(paramSensorEventListener, paramSensor, getDelay(paramInt1), null, paramInt2, 0);
  }
  
  public boolean registerListener(SensorEventListener paramSensorEventListener, Sensor paramSensor, int paramInt1, int paramInt2, Handler paramHandler) {
    return registerListenerImpl(paramSensorEventListener, paramSensor, getDelay(paramInt1), paramHandler, paramInt2, 0);
  }
  
  public boolean registerListener(SensorEventListener paramSensorEventListener, Sensor paramSensor, int paramInt, Handler paramHandler) {
    return registerListenerImpl(paramSensorEventListener, paramSensor, getDelay(paramInt), paramHandler, 0, 0);
  }
  
  @Deprecated
  public boolean registerListener(SensorListener paramSensorListener, int paramInt) {
    return registerListener(paramSensorListener, paramInt, 3);
  }
  
  @Deprecated
  public boolean registerListener(SensorListener paramSensorListener, int paramInt1, int paramInt2) {
    return getLegacySensorManager().registerListener(paramSensorListener, paramInt1, paramInt2);
  }
  
  protected abstract boolean registerListenerImpl(SensorEventListener paramSensorEventListener, Sensor paramSensor, int paramInt1, Handler paramHandler, int paramInt2, int paramInt3);
  
  public boolean requestTriggerSensor(TriggerEventListener paramTriggerEventListener, Sensor paramSensor) {
    return requestTriggerSensorImpl(paramTriggerEventListener, paramSensor);
  }
  
  protected abstract boolean requestTriggerSensorImpl(TriggerEventListener paramTriggerEventListener, Sensor paramSensor);
  
  public boolean setOperationParameter(SensorAdditionalInfo paramSensorAdditionalInfo) {
    return setOperationParameterImpl(paramSensorAdditionalInfo);
  }
  
  protected abstract boolean setOperationParameterImpl(SensorAdditionalInfo paramSensorAdditionalInfo);
  
  public void unregisterDynamicSensorCallback(DynamicSensorCallback paramDynamicSensorCallback) {
    unregisterDynamicSensorCallbackImpl(paramDynamicSensorCallback);
  }
  
  protected abstract void unregisterDynamicSensorCallbackImpl(DynamicSensorCallback paramDynamicSensorCallback);
  
  public void unregisterListener(SensorEventListener paramSensorEventListener) {
    if (paramSensorEventListener == null)
      return; 
    unregisterListenerImpl(paramSensorEventListener, null);
  }
  
  public void unregisterListener(SensorEventListener paramSensorEventListener, Sensor paramSensor) {
    if (paramSensorEventListener == null || paramSensor == null)
      return; 
    unregisterListenerImpl(paramSensorEventListener, paramSensor);
  }
  
  @Deprecated
  public void unregisterListener(SensorListener paramSensorListener) {
    unregisterListener(paramSensorListener, 255);
  }
  
  @Deprecated
  public void unregisterListener(SensorListener paramSensorListener, int paramInt) {
    getLegacySensorManager().unregisterListener(paramSensorListener, paramInt);
  }
  
  protected abstract void unregisterListenerImpl(SensorEventListener paramSensorEventListener, Sensor paramSensor);
  
  public static abstract class DynamicSensorCallback {
    public void onDynamicSensorConnected(Sensor param1Sensor) {}
    
    public void onDynamicSensorDisconnected(Sensor param1Sensor) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SensorManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */