package android.hardware;

final class LegacyListener implements SensorEventListener {
  private int mSensors;
  
  private SensorListener mTarget;
  
  private float[] mValues = new float[6];
  
  private final LegacySensorManager.LmsFilter mYawfilter = new LegacySensorManager.LmsFilter();
  
  LegacyListener(SensorListener paramSensorListener) {
    this.mTarget = paramSensorListener;
    this.mSensors = 0;
  }
  
  private static int getLegacySensorType(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 7) ? 0 : 4) : 128) : 8) : 2;
  }
  
  private static boolean hasOrientationSensor(int paramInt) {
    boolean bool;
    if ((paramInt & 0x81) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void mapSensorDataToWindow(int paramInt1, float[] paramArrayOffloat, int paramInt2) {
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
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {
    try {
      this.mTarget.onAccuracyChanged(getLegacySensorType(paramSensor.getType()), paramInt);
    } catch (AbstractMethodError abstractMethodError) {}
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent) {
    float[] arrayOfFloat = this.mValues;
    arrayOfFloat[0] = paramSensorEvent.values[0];
    arrayOfFloat[1] = paramSensorEvent.values[1];
    arrayOfFloat[2] = paramSensorEvent.values[2];
    int i = paramSensorEvent.sensor.getType();
    int j = getLegacySensorType(i);
    mapSensorDataToWindow(j, arrayOfFloat, LegacySensorManager.getRotation());
    if (i == 3) {
      if ((this.mSensors & 0x80) != 0)
        this.mTarget.onSensorChanged(128, arrayOfFloat); 
      if ((this.mSensors & 0x1) != 0) {
        arrayOfFloat[0] = this.mYawfilter.filter(paramSensorEvent.timestamp, arrayOfFloat[0]);
        this.mTarget.onSensorChanged(1, arrayOfFloat);
      } 
    } else {
      this.mTarget.onSensorChanged(j, arrayOfFloat);
    } 
  }
  
  boolean registerSensor(int paramInt) {
    int i = this.mSensors;
    if ((i & paramInt) != 0)
      return false; 
    boolean bool = hasOrientationSensor(i);
    this.mSensors |= paramInt;
    return !(bool && hasOrientationSensor(paramInt));
  }
  
  boolean unregisterSensor(int paramInt) {
    int i = this.mSensors;
    if ((i & paramInt) == 0)
      return false; 
    this.mSensors = i & paramInt;
    return !(hasOrientationSensor(paramInt) && hasOrientationSensor(this.mSensors));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/LegacySensorManager$LegacyListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */