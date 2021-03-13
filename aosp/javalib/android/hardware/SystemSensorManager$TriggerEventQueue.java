package android.hardware;

import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;

final class TriggerEventQueue extends SystemSensorManager.BaseEventQueue {
  private final TriggerEventListener mListener;
  
  private final SparseArray<TriggerEvent> mTriggerEvents = new SparseArray();
  
  public TriggerEventQueue(TriggerEventListener paramTriggerEventListener, Looper paramLooper, SystemSensorManager paramSystemSensorManager, String paramString) {
    super(paramLooper, paramSystemSensorManager, 0, paramString);
    this.mListener = paramTriggerEventListener;
  }
  
  public void addSensorEvent(Sensor paramSensor) {
    TriggerEvent triggerEvent = new TriggerEvent(Sensor.getMaxLengthValuesArray(paramSensor, SystemSensorManager.access$500(this.mManager)));
    synchronized (this.mTriggerEvents) {
      this.mTriggerEvents.put(paramSensor.getHandle(), triggerEvent);
      return;
    } 
  }
  
  protected void dispatchFlushCompleteEvent(int paramInt) {}
  
  protected void dispatchSensorEvent(int paramInt1, float[] paramArrayOffloat, int paramInt2, long paramLong) {
    Sensor sensor = (Sensor)SystemSensorManager.access$400(this.mManager).get(Integer.valueOf(paramInt1));
    if (sensor == null)
      return; 
    synchronized (this.mTriggerEvents) {
      StringBuilder stringBuilder;
      TriggerEvent triggerEvent = (TriggerEvent)this.mTriggerEvents.get(paramInt1);
      if (triggerEvent == null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Error: Trigger Event is null for Sensor: ");
        stringBuilder.append(sensor);
        Log.e("SensorManager", stringBuilder.toString());
        return;
      } 
      System.arraycopy(stringBuilder, 0, triggerEvent.values, 0, triggerEvent.values.length);
      triggerEvent.timestamp = paramLong;
      triggerEvent.sensor = sensor;
      this.mManager.cancelTriggerSensorImpl(this.mListener, sensor, false);
      this.mListener.onTrigger(triggerEvent);
      return;
    } 
  }
  
  public void removeSensorEvent(Sensor paramSensor) {
    synchronized (this.mTriggerEvents) {
      this.mTriggerEvents.delete(paramSensor.getHandle());
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SystemSensorManager$TriggerEventQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */