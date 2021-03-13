package android.hardware;

import java.util.List;

class null implements Runnable {
  public void run() {
    for (Sensor sensor : addedList)
      callback.onDynamicSensorConnected(sensor); 
    for (Sensor sensor : removedList)
      callback.onDynamicSensorDisconnected(sensor); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SystemSensorManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */