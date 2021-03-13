package android.hardware;

@Deprecated
public interface SensorListener {
  void onAccuracyChanged(int paramInt1, int paramInt2);
  
  void onSensorChanged(int paramInt, float[] paramArrayOffloat);
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SensorListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */