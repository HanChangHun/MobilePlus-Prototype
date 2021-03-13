package android.hardware;

public class SensorEvent {
  public int accuracy;
  
  public Sensor sensor;
  
  public long timestamp;
  
  public final float[] values;
  
  SensorEvent(int paramInt) {
    this.values = new float[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SensorEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */