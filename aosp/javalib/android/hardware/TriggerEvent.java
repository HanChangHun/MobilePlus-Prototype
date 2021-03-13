package android.hardware;

public final class TriggerEvent {
  public Sensor sensor;
  
  public long timestamp;
  
  public final float[] values;
  
  TriggerEvent(int paramInt) {
    this.values = new float[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/TriggerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */