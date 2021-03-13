package android.hardware.display;

public interface DisplayPowerCallbacks {
  void acquireSuspendBlocker();
  
  void onDisplayStateChange(int paramInt);
  
  void onProximityNegative();
  
  void onProximityPositive();
  
  void onStateChanged();
  
  void releaseSuspendBlocker();
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerInternal$DisplayPowerCallbacks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */