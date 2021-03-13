package android.hardware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.MemoryFile;
import android.os.MessageQueue;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import dalvik.system.CloseGuard;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SystemSensorManager extends SensorManager {
  private static final boolean DEBUG_DYNAMIC_SENSOR = true;
  
  private static final int MAX_LISTENER_COUNT = 128;
  
  private static final int MIN_DIRECT_CHANNEL_BUFFER_SIZE = 104;
  
  private static InjectEventQueue sInjectEventQueue;
  
  private static final Object sLock = new Object();
  
  private static boolean sNativeClassInited = false;
  
  private final Context mContext;
  
  private BroadcastReceiver mDynamicSensorBroadcastReceiver;
  
  private HashMap<SensorManager.DynamicSensorCallback, Handler> mDynamicSensorCallbacks = new HashMap<>();
  
  private boolean mDynamicSensorListDirty = true;
  
  private List<Sensor> mFullDynamicSensorsList = new ArrayList<>();
  
  private final ArrayList<Sensor> mFullSensorsList = new ArrayList<>();
  
  private final HashMap<Integer, Sensor> mHandleToSensor = new HashMap<>();
  
  private final Looper mMainLooper;
  
  private final long mNativeInstance;
  
  private final HashMap<SensorEventListener, SensorEventQueue> mSensorListeners = new HashMap<>();
  
  private final int mTargetSdkLevel;
  
  private final HashMap<TriggerEventListener, TriggerEventQueue> mTriggerListeners = new HashMap<>();
  
  static {
    sInjectEventQueue = null;
  }
  
  public SystemSensorManager(Context paramContext, Looper paramLooper) {
    synchronized (sLock) {
      if (!sNativeClassInited) {
        sNativeClassInited = true;
        nativeClassInit();
      } 
      this.mMainLooper = paramLooper;
      this.mTargetSdkLevel = (paramContext.getApplicationInfo()).targetSdkVersion;
      this.mContext = paramContext;
      this.mNativeInstance = nativeCreate(paramContext.getOpPackageName());
      for (byte b = 0;; b++) {
        Sensor sensor = new Sensor();
        if (!nativeGetSensorAtIndex(this.mNativeInstance, sensor, b))
          return; 
        this.mFullSensorsList.add(sensor);
        this.mHandleToSensor.put(Integer.valueOf(sensor.getHandle()), sensor);
      } 
    } 
  }
  
  private void cleanupSensorConnection(Sensor paramSensor) {
    this.mHandleToSensor.remove(Integer.valueOf(paramSensor.getHandle()));
    if (paramSensor.getReportingMode() == 2) {
      synchronized (this.mTriggerListeners) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        this((Map)this.mTriggerListeners);
        for (TriggerEventListener triggerEventListener : hashMap.keySet()) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("removed trigger listener");
          stringBuilder.append(triggerEventListener.toString());
          stringBuilder.append(" due to sensor disconnection");
          Log.i("SensorManager", stringBuilder.toString());
          cancelTriggerSensorImpl(triggerEventListener, paramSensor, true);
        } 
      } 
    } else {
      synchronized (this.mSensorListeners) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        this((Map)this.mSensorListeners);
        for (SensorEventListener sensorEventListener : hashMap.keySet()) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("removed event listener");
          stringBuilder.append(sensorEventListener.toString());
          stringBuilder.append(" due to sensor disconnection");
          Log.i("SensorManager", stringBuilder.toString());
          unregisterListenerImpl(sensorEventListener, paramSensor);
        } 
        return;
      } 
    } 
  }
  
  private static boolean diffSortedSensorList(List<Sensor> paramList1, List<Sensor> paramList2, List<Sensor> paramList3, List<Sensor> paramList4, List<Sensor> paramList5) {
    boolean bool = false;
    byte b1 = 0;
    byte b2 = 0;
    while (true) {
      if (b2 < paramList1.size() && (b1 >= paramList2.size() || ((Sensor)paramList2.get(b1)).getHandle() > ((Sensor)paramList1.get(b2)).getHandle())) {
        bool = true;
        if (paramList5 != null)
          paramList5.add(paramList1.get(b2)); 
        b2++;
        continue;
      } 
      if (b1 < paramList2.size() && (b2 >= paramList1.size() || ((Sensor)paramList2.get(b1)).getHandle() < ((Sensor)paramList1.get(b2)).getHandle())) {
        bool = true;
        if (paramList4 != null)
          paramList4.add(paramList2.get(b1)); 
        if (paramList3 != null)
          paramList3.add(paramList2.get(b1)); 
        b1++;
        continue;
      } 
      if (b1 < paramList2.size() && b2 < paramList1.size() && ((Sensor)paramList2.get(b1)).getHandle() == ((Sensor)paramList1.get(b2)).getHandle()) {
        if (paramList3 != null)
          paramList3.add(paramList1.get(b2)); 
        b1++;
        b2++;
        continue;
      } 
      break;
    } 
    return bool;
  }
  
  private static native void nativeClassInit();
  
  private static native int nativeConfigDirectChannel(long paramLong, int paramInt1, int paramInt2, int paramInt3);
  
  private static native long nativeCreate(String paramString);
  
  private static native int nativeCreateDirectChannel(long paramLong1, long paramLong2, int paramInt1, int paramInt2, HardwareBuffer paramHardwareBuffer);
  
  private static native void nativeDestroyDirectChannel(long paramLong, int paramInt);
  
  private static native void nativeGetDynamicSensors(long paramLong, List<Sensor> paramList);
  
  private static native boolean nativeGetSensorAtIndex(long paramLong, Sensor paramSensor, int paramInt);
  
  private static native boolean nativeIsDataInjectionEnabled(long paramLong);
  
  private static native int nativeSetOperationParameter(long paramLong, int paramInt1, int paramInt2, float[] paramArrayOffloat, int[] paramArrayOfint);
  
  private void setupDynamicSensorBroadcastReceiver() {
    if (this.mDynamicSensorBroadcastReceiver == null) {
      this.mDynamicSensorBroadcastReceiver = new BroadcastReceiver() {
          public void onReceive(Context param1Context, Intent param1Intent) {
            if (param1Intent.getAction() == "android.intent.action.DYNAMIC_SENSOR_CHANGED") {
              Log.i("SensorManager", "DYNS received DYNAMIC_SENSOR_CHANED broadcast");
              SystemSensorManager.access$002(SystemSensorManager.this, true);
              SystemSensorManager.this.updateDynamicSensorList();
            } 
          }
        };
      IntentFilter intentFilter = new IntentFilter("dynamic_sensor_change");
      intentFilter.addAction("android.intent.action.DYNAMIC_SENSOR_CHANGED");
      this.mContext.registerReceiver(this.mDynamicSensorBroadcastReceiver, intentFilter);
    } 
  }
  
  private void teardownDynamicSensorBroadcastReceiver() {
    this.mDynamicSensorCallbacks.clear();
    this.mContext.unregisterReceiver(this.mDynamicSensorBroadcastReceiver);
    this.mDynamicSensorBroadcastReceiver = null;
  }
  
  private void updateDynamicSensorList() {
    synchronized (this.mFullDynamicSensorsList) {
      if (this.mDynamicSensorListDirty) {
        ArrayList<Sensor> arrayList1 = new ArrayList();
        this();
        nativeGetDynamicSensors(this.mNativeInstance, arrayList1);
        ArrayList<Sensor> arrayList2 = new ArrayList();
        this();
        ArrayList<Sensor> arrayList3 = new ArrayList();
        this();
        ArrayList<Sensor> arrayList4 = new ArrayList();
        this();
        if (diffSortedSensorList(this.mFullDynamicSensorsList, arrayList1, arrayList2, arrayList3, arrayList4)) {
          Log.i("SensorManager", "DYNS dynamic sensor list cached should be updated");
          this.mFullDynamicSensorsList = arrayList2;
          for (Sensor sensor : arrayList3)
            this.mHandleToSensor.put(Integer.valueOf(sensor.getHandle()), sensor); 
          Handler handler = new Handler();
          this(this.mContext.getMainLooper());
          for (Map.Entry<SensorManager.DynamicSensorCallback, Handler> entry : this.mDynamicSensorCallbacks.entrySet()) {
            Handler handler1;
            SensorManager.DynamicSensorCallback dynamicSensorCallback = (SensorManager.DynamicSensorCallback)entry.getKey();
            if (entry.getValue() == null) {
              handler1 = handler;
            } else {
              handler1 = (Handler)handler1.getValue();
            } 
            Runnable runnable = new Runnable() {
                public void run() {
                  for (Sensor sensor : addedList)
                    callback.onDynamicSensorConnected(sensor); 
                  for (Sensor sensor : removedList)
                    callback.onDynamicSensorDisconnected(sensor); 
                }
              };
            super(this, arrayList3, dynamicSensorCallback, arrayList4);
            handler1.post(runnable);
          } 
          Iterator<Sensor> iterator = arrayList4.iterator();
          while (iterator.hasNext())
            cleanupSensorConnection(iterator.next()); 
        } 
        this.mDynamicSensorListDirty = false;
      } 
      return;
    } 
  }
  
  protected boolean cancelTriggerSensorImpl(TriggerEventListener paramTriggerEventListener, Sensor paramSensor, boolean paramBoolean) {
    if (paramSensor != null && paramSensor.getReportingMode() != 2)
      return false; 
    synchronized (this.mTriggerListeners) {
      TriggerEventQueue triggerEventQueue = this.mTriggerListeners.get(paramTriggerEventListener);
      if (triggerEventQueue != null) {
        if (paramSensor == null) {
          paramBoolean = triggerEventQueue.removeAllSensors();
        } else {
          paramBoolean = triggerEventQueue.removeSensor(paramSensor, paramBoolean);
        } 
        if (paramBoolean && !triggerEventQueue.hasSensors()) {
          this.mTriggerListeners.remove(paramTriggerEventListener);
          triggerEventQueue.dispose();
        } 
        return paramBoolean;
      } 
      return false;
    } 
  }
  
  protected int configureDirectChannelImpl(SensorDirectChannel paramSensorDirectChannel, Sensor paramSensor, int paramInt) {
    if (paramSensorDirectChannel.isOpen()) {
      if (paramInt >= 0 && paramInt <= 3) {
        if (paramSensor != null || paramInt == 0) {
          if (paramSensor == null) {
            i = -1;
          } else {
            i = paramSensor.getHandle();
          } 
          int i = nativeConfigDirectChannel(this.mNativeInstance, paramSensorDirectChannel.getNativeHandle(), i, paramInt);
          boolean bool1 = false;
          boolean bool2 = false;
          if (paramInt == 0) {
            paramInt = bool2;
            if (i == 0)
              paramInt = 1; 
            return paramInt;
          } 
          paramInt = bool1;
          if (i > 0)
            paramInt = i; 
          return paramInt;
        } 
        throw new IllegalArgumentException("when sensor is null, rate can only be DIRECT_RATE_STOP");
      } 
      throw new IllegalArgumentException("rate parameter invalid");
    } 
    throw new IllegalStateException("channel is closed");
  }
  
  protected SensorDirectChannel createDirectChannelImpl(MemoryFile paramMemoryFile, HardwareBuffer paramHardwareBuffer) {
    int i;
    long l;
    byte b;
    if (paramMemoryFile != null) {
      try {
        i = paramMemoryFile.getFileDescriptor().getInt$();
        if (paramMemoryFile.length() >= 104) {
          l = paramMemoryFile.length();
          i = nativeCreateDirectChannel(this.mNativeInstance, l, 1, i, null);
          if (i > 0) {
            b = 1;
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("create MemoryFile direct channel failed ");
            stringBuilder.append(i);
            throw new UncheckedIOException(new IOException(stringBuilder.toString()));
          } 
        } else {
          throw new IllegalArgumentException("Size of MemoryFile has to be greater than 104");
        } 
      } catch (IOException iOException) {
        throw new IllegalArgumentException("MemoryFile object is not valid");
      } 
    } else {
      if (paramHardwareBuffer != null) {
        if (paramHardwareBuffer.getFormat() == 33) {
          if (paramHardwareBuffer.getHeight() == 1) {
            if (paramHardwareBuffer.getWidth() >= 104) {
              if ((paramHardwareBuffer.getUsage() & 0x800000L) != 0L) {
                l = paramHardwareBuffer.getWidth();
                i = nativeCreateDirectChannel(this.mNativeInstance, l, 2, -1, paramHardwareBuffer);
                if (i > 0) {
                  b = 2;
                  return new SensorDirectChannel(this, i, b, l);
                } 
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("create HardwareBuffer direct channel failed ");
                stringBuilder.append(i);
                throw new UncheckedIOException(new IOException(stringBuilder.toString()));
              } 
              throw new IllegalArgumentException("HardwareBuffer must set usage flag USAGE_SENSOR_DIRECT_DATA");
            } 
            throw new IllegalArgumentException("Width if HaradwareBuffer must be greater than 104");
          } 
          throw new IllegalArgumentException("Height of HardwareBuffer must be 1");
        } 
        throw new IllegalArgumentException("Format of HardwareBuffer must be BLOB");
      } 
      throw new NullPointerException("shared memory object cannot be null");
    } 
    return new SensorDirectChannel(this, i, b, l);
  }
  
  protected void destroyDirectChannelImpl(SensorDirectChannel paramSensorDirectChannel) {
    if (paramSensorDirectChannel != null)
      nativeDestroyDirectChannel(this.mNativeInstance, paramSensorDirectChannel.getNativeHandle()); 
  }
  
  protected boolean flushImpl(SensorEventListener paramSensorEventListener) {
    if (paramSensorEventListener != null)
      synchronized (this.mSensorListeners) {
        SensorEventQueue sensorEventQueue = this.mSensorListeners.get(paramSensorEventListener);
        boolean bool = false;
        if (sensorEventQueue == null)
          return false; 
        if (sensorEventQueue.flush() == 0)
          bool = true; 
        return bool;
      }  
    throw new IllegalArgumentException("listener cannot be null");
  }
  
  protected List<Sensor> getFullDynamicSensorList() {
    setupDynamicSensorBroadcastReceiver();
    updateDynamicSensorList();
    return this.mFullDynamicSensorsList;
  }
  
  protected List<Sensor> getFullSensorList() {
    return this.mFullSensorsList;
  }
  
  protected boolean initDataInjectionImpl(boolean paramBoolean) {
    // Byte code:
    //   0: getstatic android/hardware/SystemSensorManager.sLock : Ljava/lang/Object;
    //   3: astore_2
    //   4: aload_2
    //   5: monitorenter
    //   6: iconst_1
    //   7: istore_3
    //   8: iload_1
    //   9: ifeq -> 133
    //   12: aload_0
    //   13: getfield mNativeInstance : J
    //   16: invokestatic nativeIsDataInjectionEnabled : (J)Z
    //   19: ifne -> 35
    //   22: ldc 'SensorManager'
    //   24: ldc_w 'Data Injection mode not enabled'
    //   27: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   30: pop
    //   31: aload_2
    //   32: monitorexit
    //   33: iconst_0
    //   34: ireturn
    //   35: getstatic android/hardware/SystemSensorManager.sInjectEventQueue : Landroid/hardware/SystemSensorManager$InjectEventQueue;
    //   38: astore #4
    //   40: aload #4
    //   42: ifnonnull -> 116
    //   45: new android/hardware/SystemSensorManager$InjectEventQueue
    //   48: astore #4
    //   50: aload #4
    //   52: aload_0
    //   53: aload_0
    //   54: getfield mMainLooper : Landroid/os/Looper;
    //   57: aload_0
    //   58: aload_0
    //   59: getfield mContext : Landroid/content/Context;
    //   62: invokevirtual getPackageName : ()Ljava/lang/String;
    //   65: invokespecial <init> : (Landroid/hardware/SystemSensorManager;Landroid/os/Looper;Landroid/hardware/SystemSensorManager;Ljava/lang/String;)V
    //   68: aload #4
    //   70: putstatic android/hardware/SystemSensorManager.sInjectEventQueue : Landroid/hardware/SystemSensorManager$InjectEventQueue;
    //   73: goto -> 116
    //   76: astore #4
    //   78: new java/lang/StringBuilder
    //   81: astore #5
    //   83: aload #5
    //   85: invokespecial <init> : ()V
    //   88: aload #5
    //   90: ldc_w 'Cannot create InjectEventQueue: '
    //   93: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload #5
    //   99: aload #4
    //   101: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: ldc 'SensorManager'
    //   107: aload #5
    //   109: invokevirtual toString : ()Ljava/lang/String;
    //   112: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   115: pop
    //   116: getstatic android/hardware/SystemSensorManager.sInjectEventQueue : Landroid/hardware/SystemSensorManager$InjectEventQueue;
    //   119: ifnull -> 127
    //   122: iload_3
    //   123: istore_1
    //   124: goto -> 129
    //   127: iconst_0
    //   128: istore_1
    //   129: aload_2
    //   130: monitorexit
    //   131: iload_1
    //   132: ireturn
    //   133: getstatic android/hardware/SystemSensorManager.sInjectEventQueue : Landroid/hardware/SystemSensorManager$InjectEventQueue;
    //   136: ifnull -> 149
    //   139: getstatic android/hardware/SystemSensorManager.sInjectEventQueue : Landroid/hardware/SystemSensorManager$InjectEventQueue;
    //   142: invokevirtual dispose : ()V
    //   145: aconst_null
    //   146: putstatic android/hardware/SystemSensorManager.sInjectEventQueue : Landroid/hardware/SystemSensorManager$InjectEventQueue;
    //   149: aload_2
    //   150: monitorexit
    //   151: iconst_1
    //   152: ireturn
    //   153: astore #4
    //   155: aload_2
    //   156: monitorexit
    //   157: aload #4
    //   159: athrow
    // Exception table:
    //   from	to	target	type
    //   12	33	153	finally
    //   35	40	153	finally
    //   45	73	76	java/lang/RuntimeException
    //   45	73	153	finally
    //   78	116	153	finally
    //   116	122	153	finally
    //   129	131	153	finally
    //   133	149	153	finally
    //   149	151	153	finally
    //   155	157	153	finally
  }
  
  protected boolean injectSensorDataImpl(Sensor paramSensor, float[] paramArrayOffloat, int paramInt, long paramLong) {
    synchronized (sLock) {
      InjectEventQueue injectEventQueue = sInjectEventQueue;
      boolean bool = false;
      if (injectEventQueue == null) {
        Log.e("SensorManager", "Data injection mode not activated before calling injectSensorData");
        return false;
      } 
      paramInt = sInjectEventQueue.injectSensorData(paramSensor.getHandle(), paramArrayOffloat, paramInt, paramLong);
      if (paramInt != 0) {
        sInjectEventQueue.dispose();
        sInjectEventQueue = null;
      } 
      if (paramInt == 0)
        bool = true; 
      return bool;
    } 
  }
  
  protected void registerDynamicSensorCallbackImpl(SensorManager.DynamicSensorCallback paramDynamicSensorCallback, Handler paramHandler) {
    Log.i("SensorManager", "DYNS Register dynamic sensor callback");
    if (paramDynamicSensorCallback != null) {
      if (this.mDynamicSensorCallbacks.containsKey(paramDynamicSensorCallback))
        return; 
      setupDynamicSensorBroadcastReceiver();
      this.mDynamicSensorCallbacks.put(paramDynamicSensorCallback, paramHandler);
      return;
    } 
    throw new IllegalArgumentException("callback cannot be null");
  }
  
  protected boolean registerListenerImpl(SensorEventListener paramSensorEventListener, Sensor paramSensor, int paramInt1, Handler paramHandler, int paramInt2, int paramInt3) {
    if (paramSensorEventListener == null || paramSensor == null) {
      Log.e("SensorManager", "sensor or listener is null");
      return false;
    } 
    if (paramSensor.getReportingMode() == 2) {
      Log.e("SensorManager", "Trigger Sensors should use the requestTriggerSensor.");
      return false;
    } 
    if (paramInt2 < 0 || paramInt1 < 0) {
      Log.e("SensorManager", "maxBatchReportLatencyUs and delayUs should be non-negative");
      return false;
    } 
    if (this.mSensorListeners.size() < 128)
      synchronized (this.mSensorListeners) {
        String str;
        SensorEventQueue sensorEventQueue = this.mSensorListeners.get(paramSensorEventListener);
        if (sensorEventQueue == null) {
          Looper looper;
          if (paramHandler != null) {
            looper = paramHandler.getLooper();
          } else {
            looper = this.mMainLooper;
          } 
          if (paramSensorEventListener.getClass().getEnclosingClass() != null) {
            str = paramSensorEventListener.getClass().getEnclosingClass().getName();
          } else {
            str = paramSensorEventListener.getClass().getName();
          } 
          SensorEventQueue sensorEventQueue1 = new SensorEventQueue();
          this(paramSensorEventListener, looper, this, str);
          if (!sensorEventQueue1.addSensor(paramSensor, paramInt1, paramInt2)) {
            sensorEventQueue1.dispose();
            return false;
          } 
          this.mSensorListeners.put(paramSensorEventListener, sensorEventQueue1);
          return true;
        } 
        return str.addSensor(paramSensor, paramInt1, paramInt2);
      }  
    throw new IllegalStateException("register failed, the sensor listeners size has exceeded the maximum limit 128");
  }
  
  protected boolean requestTriggerSensorImpl(TriggerEventListener paramTriggerEventListener, Sensor paramSensor) {
    if (paramSensor != null) {
      if (paramTriggerEventListener != null) {
        if (paramSensor.getReportingMode() != 2)
          return false; 
        if (this.mTriggerListeners.size() < 128)
          synchronized (this.mTriggerListeners) {
            String str;
            TriggerEventQueue triggerEventQueue = this.mTriggerListeners.get(paramTriggerEventListener);
            if (triggerEventQueue == null) {
              if (paramTriggerEventListener.getClass().getEnclosingClass() != null) {
                str = paramTriggerEventListener.getClass().getEnclosingClass().getName();
              } else {
                str = paramTriggerEventListener.getClass().getName();
              } 
              TriggerEventQueue triggerEventQueue1 = new TriggerEventQueue();
              this(paramTriggerEventListener, this.mMainLooper, this, str);
              if (!triggerEventQueue1.addSensor(paramSensor, 0, 0)) {
                triggerEventQueue1.dispose();
                return false;
              } 
              this.mTriggerListeners.put(paramTriggerEventListener, triggerEventQueue1);
              return true;
            } 
            return str.addSensor(paramSensor, 0, 0);
          }  
        throw new IllegalStateException("request failed, the trigger listeners size has exceeded the maximum limit 128");
      } 
      throw new IllegalArgumentException("listener cannot be null");
    } 
    throw new IllegalArgumentException("sensor cannot be null");
  }
  
  protected boolean setOperationParameterImpl(SensorAdditionalInfo paramSensorAdditionalInfo) {
    boolean bool;
    int i = -1;
    if (paramSensorAdditionalInfo.sensor != null)
      i = paramSensorAdditionalInfo.sensor.getHandle(); 
    if (nativeSetOperationParameter(this.mNativeInstance, i, paramSensorAdditionalInfo.type, paramSensorAdditionalInfo.floatValues, paramSensorAdditionalInfo.intValues) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected void unregisterDynamicSensorCallbackImpl(SensorManager.DynamicSensorCallback paramDynamicSensorCallback) {
    Log.i("SensorManager", "Removing dynamic sensor listerner");
    this.mDynamicSensorCallbacks.remove(paramDynamicSensorCallback);
  }
  
  protected void unregisterListenerImpl(SensorEventListener paramSensorEventListener, Sensor paramSensor) {
    if (paramSensor != null && paramSensor.getReportingMode() == 2)
      return; 
    synchronized (this.mSensorListeners) {
      SensorEventQueue sensorEventQueue = this.mSensorListeners.get(paramSensorEventListener);
      if (sensorEventQueue != null) {
        boolean bool;
        if (paramSensor == null) {
          bool = sensorEventQueue.removeAllSensors();
        } else {
          bool = sensorEventQueue.removeSensor(paramSensor, true);
        } 
        if (bool && !sensorEventQueue.hasSensors()) {
          this.mSensorListeners.remove(paramSensorEventListener);
          sensorEventQueue.dispose();
        } 
      } 
      return;
    } 
  }
  
  private static abstract class BaseEventQueue {
    protected static final int OPERATING_MODE_DATA_INJECTION = 1;
    
    protected static final int OPERATING_MODE_NORMAL = 0;
    
    private final SparseBooleanArray mActiveSensors = new SparseBooleanArray();
    
    private final CloseGuard mCloseGuard = CloseGuard.get();
    
    protected final SystemSensorManager mManager;
    
    private long mNativeSensorEventQueue;
    
    protected final SparseIntArray mSensorAccuracies = new SparseIntArray();
    
    BaseEventQueue(Looper param1Looper, SystemSensorManager param1SystemSensorManager, int param1Int, String param1String) {
      String str = param1String;
      if (param1String == null)
        str = ""; 
      this.mNativeSensorEventQueue = nativeInitBaseEventQueue(param1SystemSensorManager.mNativeInstance, new WeakReference<>(this), param1Looper.getQueue(), str, param1Int, param1SystemSensorManager.mContext.getOpPackageName());
      this.mCloseGuard.open("dispose");
      this.mManager = param1SystemSensorManager;
    }
    
    private int disableSensor(Sensor param1Sensor) {
      long l = this.mNativeSensorEventQueue;
      if (l != 0L) {
        if (param1Sensor != null)
          return nativeDisableSensor(l, param1Sensor.getHandle()); 
        throw null;
      } 
      throw null;
    }
    
    private void dispose(boolean param1Boolean) {
      CloseGuard closeGuard = this.mCloseGuard;
      if (closeGuard != null) {
        if (param1Boolean)
          closeGuard.warnIfOpen(); 
        this.mCloseGuard.close();
      } 
      long l = this.mNativeSensorEventQueue;
      if (l != 0L) {
        nativeDestroySensorEventQueue(l);
        this.mNativeSensorEventQueue = 0L;
      } 
    }
    
    private int enableSensor(Sensor param1Sensor, int param1Int1, int param1Int2) {
      long l = this.mNativeSensorEventQueue;
      if (l != 0L) {
        if (param1Sensor != null)
          return nativeEnableSensor(l, param1Sensor.getHandle(), param1Int1, param1Int2); 
        throw null;
      } 
      throw null;
    }
    
    private static native void nativeDestroySensorEventQueue(long param1Long);
    
    private static native int nativeDisableSensor(long param1Long, int param1Int);
    
    private static native int nativeEnableSensor(long param1Long, int param1Int1, int param1Int2, int param1Int3);
    
    private static native int nativeFlushSensor(long param1Long);
    
    private static native long nativeInitBaseEventQueue(long param1Long, WeakReference<BaseEventQueue> param1WeakReference, MessageQueue param1MessageQueue, String param1String1, int param1Int, String param1String2);
    
    private static native int nativeInjectSensorData(long param1Long1, int param1Int1, float[] param1ArrayOffloat, int param1Int2, long param1Long2);
    
    public boolean addSensor(Sensor param1Sensor, int param1Int1, int param1Int2) {
      int i = param1Sensor.getHandle();
      if (this.mActiveSensors.get(i))
        return false; 
      this.mActiveSensors.put(i, true);
      addSensorEvent(param1Sensor);
      if (enableSensor(param1Sensor, param1Int1, param1Int2) != 0 && (param1Int2 == 0 || (param1Int2 > 0 && enableSensor(param1Sensor, param1Int1, 0) != 0))) {
        removeSensor(param1Sensor, false);
        return false;
      } 
      return true;
    }
    
    protected abstract void addSensorEvent(Sensor param1Sensor);
    
    protected void dispatchAdditionalInfoEvent(int param1Int1, int param1Int2, int param1Int3, float[] param1ArrayOffloat, int[] param1ArrayOfint) {}
    
    protected abstract void dispatchFlushCompleteEvent(int param1Int);
    
    protected abstract void dispatchSensorEvent(int param1Int1, float[] param1ArrayOffloat, int param1Int2, long param1Long);
    
    public void dispose() {
      dispose(false);
    }
    
    protected void finalize() throws Throwable {
      try {
        dispose(true);
        return;
      } finally {
        super.finalize();
      } 
    }
    
    public int flush() {
      long l = this.mNativeSensorEventQueue;
      if (l != 0L)
        return nativeFlushSensor(l); 
      throw null;
    }
    
    public boolean hasSensors() {
      SparseBooleanArray sparseBooleanArray = this.mActiveSensors;
      boolean bool = true;
      if (sparseBooleanArray.indexOfValue(true) < 0)
        bool = false; 
      return bool;
    }
    
    protected int injectSensorDataBase(int param1Int1, float[] param1ArrayOffloat, int param1Int2, long param1Long) {
      return nativeInjectSensorData(this.mNativeSensorEventQueue, param1Int1, param1ArrayOffloat, param1Int2, param1Long);
    }
    
    public boolean removeAllSensors() {
      for (byte b = 0; b < this.mActiveSensors.size(); b++) {
        if (this.mActiveSensors.valueAt(b) == true) {
          int i = this.mActiveSensors.keyAt(b);
          Sensor sensor = (Sensor)this.mManager.mHandleToSensor.get(Integer.valueOf(i));
          if (sensor != null) {
            disableSensor(sensor);
            this.mActiveSensors.put(i, false);
            removeSensorEvent(sensor);
          } 
        } 
      } 
      return true;
    }
    
    public boolean removeSensor(Sensor param1Sensor, boolean param1Boolean) {
      int i = param1Sensor.getHandle();
      if (this.mActiveSensors.get(i)) {
        if (param1Boolean)
          disableSensor(param1Sensor); 
        this.mActiveSensors.put(param1Sensor.getHandle(), false);
        removeSensorEvent(param1Sensor);
        return true;
      } 
      return false;
    }
    
    protected abstract void removeSensorEvent(Sensor param1Sensor);
  }
  
  final class InjectEventQueue extends BaseEventQueue {
    public InjectEventQueue(Looper param1Looper, SystemSensorManager param1SystemSensorManager1, String param1String) {
      super(param1Looper, param1SystemSensorManager1, 1, param1String);
    }
    
    protected void addSensorEvent(Sensor param1Sensor) {}
    
    protected void dispatchFlushCompleteEvent(int param1Int) {}
    
    protected void dispatchSensorEvent(int param1Int1, float[] param1ArrayOffloat, int param1Int2, long param1Long) {}
    
    int injectSensorData(int param1Int1, float[] param1ArrayOffloat, int param1Int2, long param1Long) {
      return injectSensorDataBase(param1Int1, param1ArrayOffloat, param1Int2, param1Long);
    }
    
    protected void removeSensorEvent(Sensor param1Sensor) {}
  }
  
  static final class SensorEventQueue extends BaseEventQueue {
    private final SensorEventListener mListener;
    
    private final SparseArray<SensorEvent> mSensorsEvents = new SparseArray();
    
    public SensorEventQueue(SensorEventListener param1SensorEventListener, Looper param1Looper, SystemSensorManager param1SystemSensorManager, String param1String) {
      super(param1Looper, param1SystemSensorManager, 0, param1String);
      this.mListener = param1SensorEventListener;
    }
    
    public void addSensorEvent(Sensor param1Sensor) {
      SensorEvent sensorEvent = new SensorEvent(Sensor.getMaxLengthValuesArray(param1Sensor, this.mManager.mTargetSdkLevel));
      synchronized (this.mSensorsEvents) {
        this.mSensorsEvents.put(param1Sensor.getHandle(), sensorEvent);
        return;
      } 
    }
    
    protected void dispatchAdditionalInfoEvent(int param1Int1, int param1Int2, int param1Int3, float[] param1ArrayOffloat, int[] param1ArrayOfint) {
      if (this.mListener instanceof SensorEventCallback) {
        Sensor sensor = (Sensor)this.mManager.mHandleToSensor.get(Integer.valueOf(param1Int1));
        if (sensor == null)
          return; 
        SensorAdditionalInfo sensorAdditionalInfo = new SensorAdditionalInfo(sensor, param1Int2, param1Int3, param1ArrayOfint, param1ArrayOffloat);
        ((SensorEventCallback)this.mListener).onSensorAdditionalInfo(sensorAdditionalInfo);
      } 
    }
    
    protected void dispatchFlushCompleteEvent(int param1Int) {
      if (this.mListener instanceof SensorEventListener2) {
        Sensor sensor = (Sensor)this.mManager.mHandleToSensor.get(Integer.valueOf(param1Int));
        if (sensor == null)
          return; 
        ((SensorEventListener2)this.mListener).onFlushCompleted(sensor);
      } 
    }
    
    protected void dispatchSensorEvent(int param1Int1, float[] param1ArrayOffloat, int param1Int2, long param1Long) {
      Sensor sensor = (Sensor)this.mManager.mHandleToSensor.get(Integer.valueOf(param1Int1));
      if (sensor == null)
        return; 
      synchronized (this.mSensorsEvents) {
        SensorEvent sensorEvent = (SensorEvent)this.mSensorsEvents.get(param1Int1);
        if (sensorEvent == null)
          return; 
        System.arraycopy(param1ArrayOffloat, 0, sensorEvent.values, 0, sensorEvent.values.length);
        sensorEvent.timestamp = param1Long;
        sensorEvent.accuracy = param1Int2;
        sensorEvent.sensor = sensor;
        param1Int2 = this.mSensorAccuracies.get(param1Int1);
        if (sensorEvent.accuracy >= 0 && param1Int2 != sensorEvent.accuracy) {
          this.mSensorAccuracies.put(param1Int1, sensorEvent.accuracy);
          this.mListener.onAccuracyChanged(sensorEvent.sensor, sensorEvent.accuracy);
        } 
        this.mListener.onSensorChanged(sensorEvent);
        return;
      } 
    }
    
    public void removeSensorEvent(Sensor param1Sensor) {
      synchronized (this.mSensorsEvents) {
        this.mSensorsEvents.delete(param1Sensor.getHandle());
        return;
      } 
    }
  }
  
  static final class TriggerEventQueue extends BaseEventQueue {
    private final TriggerEventListener mListener;
    
    private final SparseArray<TriggerEvent> mTriggerEvents = new SparseArray();
    
    public TriggerEventQueue(TriggerEventListener param1TriggerEventListener, Looper param1Looper, SystemSensorManager param1SystemSensorManager, String param1String) {
      super(param1Looper, param1SystemSensorManager, 0, param1String);
      this.mListener = param1TriggerEventListener;
    }
    
    public void addSensorEvent(Sensor param1Sensor) {
      TriggerEvent triggerEvent = new TriggerEvent(Sensor.getMaxLengthValuesArray(param1Sensor, this.mManager.mTargetSdkLevel));
      synchronized (this.mTriggerEvents) {
        this.mTriggerEvents.put(param1Sensor.getHandle(), triggerEvent);
        return;
      } 
    }
    
    protected void dispatchFlushCompleteEvent(int param1Int) {}
    
    protected void dispatchSensorEvent(int param1Int1, float[] param1ArrayOffloat, int param1Int2, long param1Long) {
      Sensor sensor = (Sensor)this.mManager.mHandleToSensor.get(Integer.valueOf(param1Int1));
      if (sensor == null)
        return; 
      synchronized (this.mTriggerEvents) {
        StringBuilder stringBuilder;
        TriggerEvent triggerEvent = (TriggerEvent)this.mTriggerEvents.get(param1Int1);
        if (triggerEvent == null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Error: Trigger Event is null for Sensor: ");
          stringBuilder.append(sensor);
          Log.e("SensorManager", stringBuilder.toString());
          return;
        } 
        System.arraycopy(stringBuilder, 0, triggerEvent.values, 0, triggerEvent.values.length);
        triggerEvent.timestamp = param1Long;
        triggerEvent.sensor = sensor;
        this.mManager.cancelTriggerSensorImpl(this.mListener, sensor, false);
        this.mListener.onTrigger(triggerEvent);
        return;
      } 
    }
    
    public void removeSensorEvent(Sensor param1Sensor) {
      synchronized (this.mTriggerEvents) {
        this.mTriggerEvents.delete(param1Sensor.getHandle());
        return;
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SystemSensorManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */