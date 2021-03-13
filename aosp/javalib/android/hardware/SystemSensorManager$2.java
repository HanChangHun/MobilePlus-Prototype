package android.hardware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class null extends BroadcastReceiver {
  public void onReceive(Context paramContext, Intent paramIntent) {
    if (paramIntent.getAction() == "android.intent.action.DYNAMIC_SENSOR_CHANGED") {
      Log.i("SensorManager", "DYNS received DYNAMIC_SENSOR_CHANED broadcast");
      SystemSensorManager.access$002(SystemSensorManager.this, true);
      SystemSensorManager.access$100(SystemSensorManager.this);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SystemSensorManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */