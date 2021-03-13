package android.hardware.display;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

class null extends ContentObserver {
  null(Handler paramHandler) {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri) {
    String str;
    super.onChange(paramBoolean, paramUri);
    if (paramUri == null) {
      paramUri = null;
    } else {
      str = paramUri.getLastPathSegment();
    } 
    if (str != null && NightDisplayListener.access$000(NightDisplayListener.this) != null) {
      byte b = -1;
      switch (str.hashCode()) {
        case 1578271348:
          if (str.equals("night_display_custom_start_time"))
            b = 2; 
          break;
        case 800115245:
          if (str.equals("night_display_activated"))
            b = 0; 
          break;
        case -969458956:
          if (str.equals("night_display_color_temperature"))
            b = 4; 
          break;
        case -1761668069:
          if (str.equals("night_display_custom_end_time"))
            b = 3; 
          break;
        case -2038150513:
          if (str.equals("night_display_auto_mode"))
            b = 1; 
          break;
      } 
      if (b != 0) {
        if (b != 1) {
          if (b != 2) {
            if (b != 3) {
              if (b == 4)
                NightDisplayListener.access$000(NightDisplayListener.this).onColorTemperatureChanged(NightDisplayListener.access$100(NightDisplayListener.this).getNightDisplayColorTemperature()); 
            } else {
              NightDisplayListener.access$000(NightDisplayListener.this).onCustomEndTimeChanged(NightDisplayListener.access$100(NightDisplayListener.this).getNightDisplayCustomEndTime());
            } 
          } else {
            NightDisplayListener.access$000(NightDisplayListener.this).onCustomStartTimeChanged(NightDisplayListener.access$100(NightDisplayListener.this).getNightDisplayCustomStartTime());
          } 
        } else {
          NightDisplayListener.access$000(NightDisplayListener.this).onAutoModeChanged(NightDisplayListener.access$100(NightDisplayListener.this).getNightDisplayAutoMode());
        } 
      } else {
        NightDisplayListener.access$000(NightDisplayListener.this).onActivated(NightDisplayListener.access$100(NightDisplayListener.this).isNightDisplayActivated());
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/NightDisplayListener$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */