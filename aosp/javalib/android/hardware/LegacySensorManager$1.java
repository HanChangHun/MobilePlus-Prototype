package android.hardware;

import android.view.IRotationWatcher;

class null extends IRotationWatcher.Stub {
  public void onRotationChanged(int paramInt) {
    LegacySensorManager.onRotationChanged(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/LegacySensorManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */