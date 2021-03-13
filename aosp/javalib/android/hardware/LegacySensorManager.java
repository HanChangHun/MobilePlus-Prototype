package android.hardware;

import android.view.IRotationWatcher;
import android.view.IWindowManager;
import java.util.HashMap;
import java.util.Iterator;

final class LegacySensorManager {
  private static boolean sInitialized;
  
  private static int sRotation = 0;
  
  private static IWindowManager sWindowManager;
  
  private final HashMap<SensorListener, LegacyListener> mLegacyListenersMap;
  
  private final SensorManager mSensorManager;
  
  public LegacySensorManager(SensorManager paramSensorManager) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: new java/util/HashMap
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: putfield mLegacyListenersMap : Ljava/util/HashMap;
    //   15: aload_0
    //   16: aload_1
    //   17: putfield mSensorManager : Landroid/hardware/SensorManager;
    //   20: ldc android/hardware/SensorManager
    //   22: monitorenter
    //   23: getstatic android/hardware/LegacySensorManager.sInitialized : Z
    //   26: ifne -> 70
    //   29: ldc 'window'
    //   31: invokestatic getService : (Ljava/lang/String;)Landroid/os/IBinder;
    //   34: invokestatic asInterface : (Landroid/os/IBinder;)Landroid/view/IWindowManager;
    //   37: astore_2
    //   38: aload_2
    //   39: putstatic android/hardware/LegacySensorManager.sWindowManager : Landroid/view/IWindowManager;
    //   42: aload_2
    //   43: ifnull -> 70
    //   46: new android/hardware/LegacySensorManager$1
    //   49: astore_1
    //   50: aload_1
    //   51: aload_0
    //   52: invokespecial <init> : (Landroid/hardware/LegacySensorManager;)V
    //   55: aload_2
    //   56: aload_1
    //   57: iconst_0
    //   58: invokeinterface watchRotation : (Landroid/view/IRotationWatcher;I)I
    //   63: putstatic android/hardware/LegacySensorManager.sRotation : I
    //   66: goto -> 70
    //   69: astore_1
    //   70: ldc android/hardware/SensorManager
    //   72: monitorexit
    //   73: return
    //   74: astore_1
    //   75: ldc android/hardware/SensorManager
    //   77: monitorexit
    //   78: aload_1
    //   79: athrow
    // Exception table:
    //   from	to	target	type
    //   23	42	74	finally
    //   46	66	69	android/os/RemoteException
    //   46	66	74	finally
    //   70	73	74	finally
    //   75	78	74	finally
  }
  
  static int getRotation() {
    // Byte code:
    //   0: ldc android/hardware/SensorManager
    //   2: monitorenter
    //   3: getstatic android/hardware/LegacySensorManager.sRotation : I
    //   6: istore_0
    //   7: ldc android/hardware/SensorManager
    //   9: monitorexit
    //   10: iload_0
    //   11: ireturn
    //   12: astore_1
    //   13: ldc android/hardware/SensorManager
    //   15: monitorexit
    //   16: aload_1
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   3	10	12	finally
    //   13	16	12	finally
  }
  
  static void onRotationChanged(int paramInt) {
    // Byte code:
    //   0: ldc android/hardware/SensorManager
    //   2: monitorenter
    //   3: iload_0
    //   4: putstatic android/hardware/LegacySensorManager.sRotation : I
    //   7: ldc android/hardware/SensorManager
    //   9: monitorexit
    //   10: return
    //   11: astore_1
    //   12: ldc android/hardware/SensorManager
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   3	10	11	finally
    //   12	15	11	finally
  }
  
  private boolean registerLegacyListener(int paramInt1, int paramInt2, SensorListener paramSensorListener, int paramInt3, int paramInt4) {
    boolean bool1 = false;
    boolean bool2 = bool1;
    if ((paramInt3 & paramInt1) != 0) {
      Sensor sensor = this.mSensorManager.getDefaultSensor(paramInt2);
      bool2 = bool1;
      if (sensor != null)
        synchronized (this.mLegacyListenersMap) {
          LegacyListener legacyListener1 = this.mLegacyListenersMap.get(paramSensorListener);
          LegacyListener legacyListener2 = legacyListener1;
          if (legacyListener1 == null) {
            legacyListener2 = new LegacyListener();
            this(paramSensorListener);
            this.mLegacyListenersMap.put(paramSensorListener, legacyListener2);
          } 
          if (legacyListener2.registerSensor(paramInt1)) {
            bool2 = this.mSensorManager.registerListener(legacyListener2, sensor, paramInt4);
          } else {
            bool2 = true;
          } 
        }  
    } 
    return bool2;
  }
  
  private void unregisterLegacyListener(int paramInt1, int paramInt2, SensorListener paramSensorListener, int paramInt3) {
    if ((paramInt3 & paramInt1) != 0) {
      Sensor sensor = this.mSensorManager.getDefaultSensor(paramInt2);
      if (sensor != null)
        synchronized (this.mLegacyListenersMap) {
          LegacyListener legacyListener = this.mLegacyListenersMap.get(paramSensorListener);
          if (legacyListener != null && legacyListener.unregisterSensor(paramInt1)) {
            this.mSensorManager.unregisterListener(legacyListener, sensor);
            if (!legacyListener.hasSensors())
              this.mLegacyListenersMap.remove(paramSensorListener); 
          } 
        }  
    } 
  }
  
  public int getSensors() {
    int i = 0;
    Iterator<Sensor> iterator = this.mSensorManager.getFullSensorList().iterator();
    while (iterator.hasNext()) {
      int j = ((Sensor)iterator.next()).getType();
      if (j != 1) {
        if (j != 2) {
          if (j != 3)
            continue; 
          i |= 0x81;
          continue;
        } 
        i |= 0x8;
        continue;
      } 
      i |= 0x2;
    } 
    return i;
  }
  
  public boolean registerListener(SensorListener paramSensorListener, int paramInt1, int paramInt2) {
    boolean bool2;
    boolean bool1 = false;
    if (paramSensorListener == null)
      return false; 
    if (registerLegacyListener(2, 1, paramSensorListener, paramInt1, paramInt2) || false) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (registerLegacyListener(8, 2, paramSensorListener, paramInt1, paramInt2) || bool2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (registerLegacyListener(128, 3, paramSensorListener, paramInt1, paramInt2) || bool2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (registerLegacyListener(1, 3, paramSensorListener, paramInt1, paramInt2) || bool2) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (registerLegacyListener(4, 7, paramSensorListener, paramInt1, paramInt2) || bool2)
      bool1 = true; 
    return bool1;
  }
  
  public void unregisterListener(SensorListener paramSensorListener, int paramInt) {
    if (paramSensorListener == null)
      return; 
    unregisterLegacyListener(2, 1, paramSensorListener, paramInt);
    unregisterLegacyListener(8, 2, paramSensorListener, paramInt);
    unregisterLegacyListener(128, 3, paramSensorListener, paramInt);
    unregisterLegacyListener(1, 3, paramSensorListener, paramInt);
    unregisterLegacyListener(4, 7, paramSensorListener, paramInt);
  }
  
  private static final class LegacyListener implements SensorEventListener {
    private int mSensors;
    
    private SensorListener mTarget;
    
    private float[] mValues = new float[6];
    
    private final LegacySensorManager.LmsFilter mYawfilter = new LegacySensorManager.LmsFilter();
    
    LegacyListener(SensorListener param1SensorListener) {
      this.mTarget = param1SensorListener;
      this.mSensors = 0;
    }
    
    private static int getLegacySensorType(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 7) ? 0 : 4) : 128) : 8) : 2;
    }
    
    private static boolean hasOrientationSensor(int param1Int) {
      boolean bool;
      if ((param1Int & 0x81) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    private void mapSensorDataToWindow(int param1Int1, float[] param1ArrayOffloat, int param1Int2) {
      // Byte code:
      //   0: aload_2
      //   1: iconst_0
      //   2: faload
      //   3: fstore #4
      //   5: aload_2
      //   6: iconst_1
      //   7: faload
      //   8: fstore #5
      //   10: aload_2
      //   11: iconst_2
      //   12: faload
      //   13: fstore #6
      //   15: iload_1
      //   16: iconst_1
      //   17: if_icmpeq -> 72
      //   20: iload_1
      //   21: iconst_2
      //   22: if_icmpeq -> 54
      //   25: iload_1
      //   26: bipush #8
      //   28: if_icmpeq -> 41
      //   31: iload_1
      //   32: sipush #128
      //   35: if_icmpeq -> 72
      //   38: goto -> 77
      //   41: fload #4
      //   43: fneg
      //   44: fstore #4
      //   46: fload #5
      //   48: fneg
      //   49: fstore #5
      //   51: goto -> 77
      //   54: fload #4
      //   56: fneg
      //   57: fstore #4
      //   59: fload #5
      //   61: fneg
      //   62: fstore #5
      //   64: fload #6
      //   66: fneg
      //   67: fstore #6
      //   69: goto -> 77
      //   72: fload #6
      //   74: fneg
      //   75: fstore #6
      //   77: aload_2
      //   78: iconst_0
      //   79: fload #4
      //   81: fastore
      //   82: aload_2
      //   83: iconst_1
      //   84: fload #5
      //   86: fastore
      //   87: aload_2
      //   88: iconst_2
      //   89: fload #6
      //   91: fastore
      //   92: aload_2
      //   93: iconst_3
      //   94: fload #4
      //   96: fastore
      //   97: aload_2
      //   98: iconst_4
      //   99: fload #5
      //   101: fastore
      //   102: aload_2
      //   103: iconst_5
      //   104: fload #6
      //   106: fastore
      //   107: iload_3
      //   108: iconst_1
      //   109: iand
      //   110: ifeq -> 197
      //   113: iload_1
      //   114: iconst_1
      //   115: if_icmpeq -> 158
      //   118: iload_1
      //   119: iconst_2
      //   120: if_icmpeq -> 139
      //   123: iload_1
      //   124: bipush #8
      //   126: if_icmpeq -> 139
      //   129: iload_1
      //   130: sipush #128
      //   133: if_icmpeq -> 158
      //   136: goto -> 197
      //   139: aload_2
      //   140: iconst_0
      //   141: fload #5
      //   143: fneg
      //   144: fastore
      //   145: aload_2
      //   146: iconst_1
      //   147: fload #4
      //   149: fastore
      //   150: aload_2
      //   151: iconst_2
      //   152: fload #6
      //   154: fastore
      //   155: goto -> 197
      //   158: fload #4
      //   160: ldc 270.0
      //   162: fcmpg
      //   163: ifge -> 173
      //   166: bipush #90
      //   168: istore #7
      //   170: goto -> 178
      //   173: sipush #-270
      //   176: istore #7
      //   178: aload_2
      //   179: iconst_0
      //   180: iload #7
      //   182: i2f
      //   183: fload #4
      //   185: fadd
      //   186: fastore
      //   187: aload_2
      //   188: iconst_1
      //   189: fload #6
      //   191: fastore
      //   192: aload_2
      //   193: iconst_2
      //   194: fload #5
      //   196: fastore
      //   197: iload_3
      //   198: iconst_2
      //   199: iand
      //   200: ifeq -> 306
      //   203: aload_2
      //   204: iconst_0
      //   205: faload
      //   206: fstore #4
      //   208: aload_2
      //   209: iconst_1
      //   210: faload
      //   211: fstore #5
      //   213: aload_2
      //   214: iconst_2
      //   215: faload
      //   216: fstore #6
      //   218: iload_1
      //   219: iconst_1
      //   220: if_icmpeq -> 264
      //   223: iload_1
      //   224: iconst_2
      //   225: if_icmpeq -> 244
      //   228: iload_1
      //   229: bipush #8
      //   231: if_icmpeq -> 244
      //   234: iload_1
      //   235: sipush #128
      //   238: if_icmpeq -> 264
      //   241: goto -> 306
      //   244: aload_2
      //   245: iconst_0
      //   246: fload #4
      //   248: fneg
      //   249: fastore
      //   250: aload_2
      //   251: iconst_1
      //   252: fload #5
      //   254: fneg
      //   255: fastore
      //   256: aload_2
      //   257: iconst_2
      //   258: fload #6
      //   260: fastore
      //   261: goto -> 306
      //   264: fload #4
      //   266: ldc 180.0
      //   268: fcmpl
      //   269: iflt -> 282
      //   272: fload #4
      //   274: ldc 180.0
      //   276: fsub
      //   277: fstore #4
      //   279: goto -> 289
      //   282: ldc 180.0
      //   284: fload #4
      //   286: fadd
      //   287: fstore #4
      //   289: aload_2
      //   290: iconst_0
      //   291: fload #4
      //   293: fastore
      //   294: aload_2
      //   295: iconst_1
      //   296: fload #5
      //   298: fneg
      //   299: fastore
      //   300: aload_2
      //   301: iconst_2
      //   302: fload #6
      //   304: fneg
      //   305: fastore
      //   306: return
    }
    
    boolean hasSensors() {
      boolean bool;
      if (this.mSensors != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onAccuracyChanged(Sensor param1Sensor, int param1Int) {
      try {
        this.mTarget.onAccuracyChanged(getLegacySensorType(param1Sensor.getType()), param1Int);
      } catch (AbstractMethodError abstractMethodError) {}
    }
    
    public void onSensorChanged(SensorEvent param1SensorEvent) {
      float[] arrayOfFloat = this.mValues;
      arrayOfFloat[0] = param1SensorEvent.values[0];
      arrayOfFloat[1] = param1SensorEvent.values[1];
      arrayOfFloat[2] = param1SensorEvent.values[2];
      int i = param1SensorEvent.sensor.getType();
      int j = getLegacySensorType(i);
      mapSensorDataToWindow(j, arrayOfFloat, LegacySensorManager.getRotation());
      if (i == 3) {
        if ((this.mSensors & 0x80) != 0)
          this.mTarget.onSensorChanged(128, arrayOfFloat); 
        if ((this.mSensors & 0x1) != 0) {
          arrayOfFloat[0] = this.mYawfilter.filter(param1SensorEvent.timestamp, arrayOfFloat[0]);
          this.mTarget.onSensorChanged(1, arrayOfFloat);
        } 
      } else {
        this.mTarget.onSensorChanged(j, arrayOfFloat);
      } 
    }
    
    boolean registerSensor(int param1Int) {
      int i = this.mSensors;
      if ((i & param1Int) != 0)
        return false; 
      boolean bool = hasOrientationSensor(i);
      this.mSensors |= param1Int;
      return !(bool && hasOrientationSensor(param1Int));
    }
    
    boolean unregisterSensor(int param1Int) {
      int i = this.mSensors;
      if ((i & param1Int) == 0)
        return false; 
      this.mSensors = i & param1Int;
      return !(hasOrientationSensor(param1Int) && hasOrientationSensor(this.mSensors));
    }
  }
  
  private static final class LmsFilter {
    private static final int COUNT = 12;
    
    private static final float PREDICTION_RATIO = 0.33333334F;
    
    private static final float PREDICTION_TIME = 0.08F;
    
    private static final int SENSORS_RATE_MS = 20;
    
    private int mIndex = 12;
    
    private long[] mT = new long[24];
    
    private float[] mV = new float[24];
    
    public float filter(long param1Long, float param1Float) {
      float f1 = param1Float;
      float f2 = this.mV[this.mIndex];
      if (f1 - f2 > 180.0F) {
        param1Float = f1 - 360.0F;
      } else {
        param1Float = f1;
        if (f2 - f1 > 180.0F)
          param1Float = f1 + 360.0F; 
      } 
      int i = this.mIndex + 1;
      this.mIndex = i;
      if (i >= 24)
        this.mIndex = 12; 
      float[] arrayOfFloat = this.mV;
      i = this.mIndex;
      arrayOfFloat[i] = param1Float;
      long[] arrayOfLong = this.mT;
      arrayOfLong[i] = param1Long;
      arrayOfFloat[i - 12] = param1Float;
      arrayOfLong[i - 12] = param1Long;
      f2 = 0.0F;
      float f3 = 0.0F;
      f1 = 0.0F;
      float f4 = 0.0F;
      param1Float = 0.0F;
      for (i = 0; i < 11; i++) {
        int j = this.mIndex - 1 - i;
        float f5 = this.mV[j];
        long[] arrayOfLong1 = this.mT;
        float f6 = (float)(arrayOfLong1[j] / 2L + arrayOfLong1[j + 1] / 2L - param1Long) * 1.0E-9F;
        float f7 = (float)(arrayOfLong1[j] - arrayOfLong1[j + 1]) * 1.0E-9F;
        f7 *= f7;
        param1Float += f5 * f7;
        f4 += f6 * f7 * f6;
        f1 += f6 * f7;
        f3 += f6 * f7 * f5;
        f2 += f7;
      } 
      f4 = (param1Float * f4 + f1 * f3) / (f2 * f4 + f1 * f1);
      f1 = (0.08F * (f2 * f4 - param1Float) / f1 + f4) * 0.0027777778F;
      if (f1 >= 0.0F) {
        f2 = f1;
      } else {
        f2 = -f1;
      } 
      param1Float = f1;
      if (f2 >= 0.5F)
        param1Float = f1 - (float)Math.ceil((0.5F + f1)) + 1.0F; 
      f1 = param1Float;
      if (param1Float < 0.0F)
        f1 = param1Float + 1.0F; 
      return f1 * 360.0F;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/LegacySensorManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */