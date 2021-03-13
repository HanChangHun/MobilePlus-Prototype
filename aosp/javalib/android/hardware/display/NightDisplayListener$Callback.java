package android.hardware.display;

import java.time.LocalTime;

public interface Callback {
  default void onActivated(boolean paramBoolean) {}
  
  default void onAutoModeChanged(int paramInt) {}
  
  default void onColorTemperatureChanged(int paramInt) {}
  
  default void onCustomEndTimeChanged(LocalTime paramLocalTime) {}
  
  default void onCustomStartTimeChanged(LocalTime paramLocalTime) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/NightDisplayListener$Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */