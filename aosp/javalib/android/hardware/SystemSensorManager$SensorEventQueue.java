package android.hardware;

import android.os.Looper;
import android.util.SparseArray;

final class SensorEventQueue extends SystemSensorManager.BaseEventQueue {
  private final SensorEventListener mListener;
  
  private final SparseArray<SensorEvent> mSensorsEvents = new SparseArray();
  
  public SensorEventQueue(SensorEventListener paramSensorEventListener, Looper paramLooper, SystemSensorManager paramSystemSensorManager, String paramString) {
    super(paramLooper, paramSystemSensorManager, 0, paramString);
    this.mListener = paramSensorEventListener;
  }
  
  public void addSensorEvent(Sensor paramSensor) {
    SensorEvent sensorEvent = new SensorEvent(Sensor.getMaxLengthValuesArray(paramSensor, SystemSensorManager.access$500(this.mManager)));
    synchronized (this.mSensorsEvents) {
      this.mSensorsEvents.put(paramSensor.getHandle(), sensorEvent);
      return;
    } 
  }
  
  protected void dispatchAdditionalInfoEvent(int paramInt1, int paramInt2, int paramInt3, float[] paramArrayOffloat, int[] paramArrayOfint) {
    if (this.mListener instanceof SensorEventCallback) {
      Sensor sensor = (Sensor)SystemSensorManager.access$400(this.mManager).get(Integer.valueOf(paramInt1));
      if (sensor == null)
        return; 
      SensorAdditionalInfo sensorAdditionalInfo = new SensorAdditionalInfo(sensor, paramInt2, paramInt3, paramArrayOfint, paramArrayOffloat);
      ((SensorEventCallback)this.mListener).onSensorAdditionalInfo(sensorAdditionalInfo);
    } 
  }
  
  protected void dispatchFlushCompleteEvent(int paramInt) {
    if (this.mListener instanceof SensorEventListener2) {
      Sensor sensor = (Sensor)SystemSensorManager.access$400(this.mManager).get(Integer.valueOf(paramInt));
      if (sensor == null)
        return; 
      ((SensorEventListener2)this.mListener).onFlushCompleted(sensor);
    } 
  }
  
  protected void dispatchSensorEvent(int paramInt1, float[] paramArrayOffloat, int paramInt2, long paramLong) {
    Sensor sensor = (Sensor)SystemSensorManager.access$400(this.mManager).get(Integer.valueOf(paramInt1));
    if (sensor == null)
      return; 
    synchronized (this.mSensorsEvents) {
      SensorEvent sensorEvent = (SensorEvent)this.mSensorsEvents.get(paramInt1);
      if (sensorEvent == null)
        return; 
      System.arraycopy(paramArrayOffloat, 0, sensorEvent.values, 0, sensorEvent.values.length);
      sensorEvent.timestamp = paramLong;
      sensorEvent.accuracy = paramInt2;
      sensorEvent.sensor = sensor;
      paramInt2 = this.mSensorAccuracies.get(paramInt1);
      if (sensorEvent.accuracy >= 0 && paramInt2 != sensorEvent.accuracy) {
        this.mSensorAccuracies.put(paramInt1, sensorEvent.accuracy);
        this.mListener.onAccuracyChanged(sensorEvent.sensor, sensorEvent.accuracy);
      } 
      this.mListener.onSensorChanged(sensorEvent);
      return;
    } 
  }
  
  public void removeSensorEvent(Sensor paramSensor) {
    synchronized (this.mSensorsEvents) {
      this.mSensorsEvents.delete(paramSensor.getHandle());
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SystemSensorManager$SensorEventQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */