package android.hardware;

public abstract class SensorEventCallback implements SensorEventListener2 {
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onFlushCompleted(Sensor paramSensor) {}
  
  public void onSensorAdditionalInfo(SensorAdditionalInfo paramSensorAdditionalInfo) {}
  
  public void onSensorChanged(SensorEvent paramSensorEvent) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SensorEventCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */