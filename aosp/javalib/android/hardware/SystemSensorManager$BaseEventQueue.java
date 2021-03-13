package android.hardware;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import dalvik.system.CloseGuard;
import java.lang.ref.WeakReference;

abstract class BaseEventQueue {
  protected static final int OPERATING_MODE_DATA_INJECTION = 1;
  
  protected static final int OPERATING_MODE_NORMAL = 0;
  
  private final SparseBooleanArray mActiveSensors = new SparseBooleanArray();
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  protected final SystemSensorManager mManager;
  
  private long mNativeSensorEventQueue;
  
  protected final SparseIntArray mSensorAccuracies = new SparseIntArray();
  
  BaseEventQueue(Looper paramLooper, SystemSensorManager paramSystemSensorManager, int paramInt, String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    this.mNativeSensorEventQueue = nativeInitBaseEventQueue(SystemSensorManager.access$200(paramSystemSensorManager), new WeakReference<>(this), paramLooper.getQueue(), str, paramInt, SystemSensorManager.access$300(paramSystemSensorManager).getOpPackageName());
    this.mCloseGuard.open("dispose");
    this.mManager = paramSystemSensorManager;
  }
  
  private int disableSensor(Sensor paramSensor) {
    long l = this.mNativeSensorEventQueue;
    if (l != 0L) {
      if (paramSensor != null)
        return nativeDisableSensor(l, paramSensor.getHandle()); 
      throw null;
    } 
    throw null;
  }
  
  private void dispose(boolean paramBoolean) {
    CloseGuard closeGuard = this.mCloseGuard;
    if (closeGuard != null) {
      if (paramBoolean)
        closeGuard.warnIfOpen(); 
      this.mCloseGuard.close();
    } 
    long l = this.mNativeSensorEventQueue;
    if (l != 0L) {
      nativeDestroySensorEventQueue(l);
      this.mNativeSensorEventQueue = 0L;
    } 
  }
  
  private int enableSensor(Sensor paramSensor, int paramInt1, int paramInt2) {
    long l = this.mNativeSensorEventQueue;
    if (l != 0L) {
      if (paramSensor != null)
        return nativeEnableSensor(l, paramSensor.getHandle(), paramInt1, paramInt2); 
      throw null;
    } 
    throw null;
  }
  
  private static native void nativeDestroySensorEventQueue(long paramLong);
  
  private static native int nativeDisableSensor(long paramLong, int paramInt);
  
  private static native int nativeEnableSensor(long paramLong, int paramInt1, int paramInt2, int paramInt3);
  
  private static native int nativeFlushSensor(long paramLong);
  
  private static native long nativeInitBaseEventQueue(long paramLong, WeakReference<BaseEventQueue> paramWeakReference, MessageQueue paramMessageQueue, String paramString1, int paramInt, String paramString2);
  
  private static native int nativeInjectSensorData(long paramLong1, int paramInt1, float[] paramArrayOffloat, int paramInt2, long paramLong2);
  
  public boolean addSensor(Sensor paramSensor, int paramInt1, int paramInt2) {
    int i = paramSensor.getHandle();
    if (this.mActiveSensors.get(i))
      return false; 
    this.mActiveSensors.put(i, true);
    addSensorEvent(paramSensor);
    if (enableSensor(paramSensor, paramInt1, paramInt2) != 0 && (paramInt2 == 0 || (paramInt2 > 0 && enableSensor(paramSensor, paramInt1, 0) != 0))) {
      removeSensor(paramSensor, false);
      return false;
    } 
    return true;
  }
  
  protected abstract void addSensorEvent(Sensor paramSensor);
  
  protected void dispatchAdditionalInfoEvent(int paramInt1, int paramInt2, int paramInt3, float[] paramArrayOffloat, int[] paramArrayOfint) {}
  
  protected abstract void dispatchFlushCompleteEvent(int paramInt);
  
  protected abstract void dispatchSensorEvent(int paramInt1, float[] paramArrayOffloat, int paramInt2, long paramLong);
  
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
  
  protected int injectSensorDataBase(int paramInt1, float[] paramArrayOffloat, int paramInt2, long paramLong) {
    return nativeInjectSensorData(this.mNativeSensorEventQueue, paramInt1, paramArrayOffloat, paramInt2, paramLong);
  }
  
  public boolean removeAllSensors() {
    for (byte b = 0; b < this.mActiveSensors.size(); b++) {
      if (this.mActiveSensors.valueAt(b) == true) {
        int i = this.mActiveSensors.keyAt(b);
        Sensor sensor = (Sensor)SystemSensorManager.access$400(this.mManager).get(Integer.valueOf(i));
        if (sensor != null) {
          disableSensor(sensor);
          this.mActiveSensors.put(i, false);
          removeSensorEvent(sensor);
        } 
      } 
    } 
    return true;
  }
  
  public boolean removeSensor(Sensor paramSensor, boolean paramBoolean) {
    int i = paramSensor.getHandle();
    if (this.mActiveSensors.get(i)) {
      if (paramBoolean)
        disableSensor(paramSensor); 
      this.mActiveSensors.put(paramSensor.getHandle(), false);
      removeSensorEvent(paramSensor);
      return true;
    } 
    return false;
  }
  
  protected abstract void removeSensorEvent(Sensor paramSensor);
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SystemSensorManager$BaseEventQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */