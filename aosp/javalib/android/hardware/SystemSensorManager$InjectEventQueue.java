package android.hardware;

import android.os.Looper;

final class InjectEventQueue extends SystemSensorManager.BaseEventQueue {
  public InjectEventQueue(Looper paramLooper, SystemSensorManager paramSystemSensorManager2, String paramString) {
    super(paramLooper, paramSystemSensorManager2, 1, paramString);
  }
  
  protected void addSensorEvent(Sensor paramSensor) {}
  
  protected void dispatchFlushCompleteEvent(int paramInt) {}
  
  protected void dispatchSensorEvent(int paramInt1, float[] paramArrayOffloat, int paramInt2, long paramLong) {}
  
  int injectSensorData(int paramInt1, float[] paramArrayOffloat, int paramInt2, long paramLong) {
    return injectSensorDataBase(paramInt1, paramArrayOffloat, paramInt2, paramLong);
  }
  
  protected void removeSensorEvent(Sensor paramSensor) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SystemSensorManager$InjectEventQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */