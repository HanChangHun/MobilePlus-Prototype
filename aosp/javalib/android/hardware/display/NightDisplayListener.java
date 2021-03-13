package android.hardware.display;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import java.time.LocalTime;

public class NightDisplayListener {
  private Callback mCallback;
  
  private final ContentObserver mContentObserver;
  
  private final Context mContext;
  
  private final Handler mHandler;
  
  private final ColorDisplayManager mManager;
  
  private final int mUserId;
  
  public NightDisplayListener(Context paramContext) {
    this(paramContext, ActivityManager.getCurrentUser(), new Handler(Looper.getMainLooper()));
  }
  
  public NightDisplayListener(Context paramContext, int paramInt, Handler paramHandler) {
    paramContext = paramContext.getApplicationContext();
    this.mContext = paramContext;
    this.mManager = (ColorDisplayManager)paramContext.getSystemService(ColorDisplayManager.class);
    this.mUserId = paramInt;
    this.mHandler = paramHandler;
    this.mContentObserver = new ContentObserver(paramHandler) {
        public void onChange(boolean param1Boolean, Uri param1Uri) {
          String str;
          super.onChange(param1Boolean, param1Uri);
          if (param1Uri == null) {
            param1Uri = null;
          } else {
            str = param1Uri.getLastPathSegment();
          } 
          if (str != null && NightDisplayListener.this.mCallback != null) {
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
                      NightDisplayListener.this.mCallback.onColorTemperatureChanged(NightDisplayListener.this.mManager.getNightDisplayColorTemperature()); 
                  } else {
                    NightDisplayListener.this.mCallback.onCustomEndTimeChanged(NightDisplayListener.this.mManager.getNightDisplayCustomEndTime());
                  } 
                } else {
                  NightDisplayListener.this.mCallback.onCustomStartTimeChanged(NightDisplayListener.this.mManager.getNightDisplayCustomStartTime());
                } 
              } else {
                NightDisplayListener.this.mCallback.onAutoModeChanged(NightDisplayListener.this.mManager.getNightDisplayAutoMode());
              } 
            } else {
              NightDisplayListener.this.mCallback.onActivated(NightDisplayListener.this.mManager.isNightDisplayActivated());
            } 
          } 
        }
      };
  }
  
  public NightDisplayListener(Context paramContext, Handler paramHandler) {
    this(paramContext, ActivityManager.getCurrentUser(), paramHandler);
  }
  
  private void setCallbackInternal(Callback paramCallback) {
    Callback callback = this.mCallback;
    if (callback != paramCallback) {
      this.mCallback = paramCallback;
      if (paramCallback == null) {
        this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
      } else if (callback == null) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("night_display_activated"), false, this.mContentObserver, this.mUserId);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("night_display_auto_mode"), false, this.mContentObserver, this.mUserId);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("night_display_custom_start_time"), false, this.mContentObserver, this.mUserId);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("night_display_custom_end_time"), false, this.mContentObserver, this.mUserId);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("night_display_color_temperature"), false, this.mContentObserver, this.mUserId);
      } 
    } 
  }
  
  public void setCallback(Callback paramCallback) {
    if (Looper.myLooper() != this.mHandler.getLooper())
      this.mHandler.post(new _$$Lambda$NightDisplayListener$sOK1HmSbMnFLzc4SdDD1WpVWJiI(this, paramCallback)); 
    setCallbackInternal(paramCallback);
  }
  
  public static interface Callback {
    default void onActivated(boolean param1Boolean) {}
    
    default void onAutoModeChanged(int param1Int) {}
    
    default void onColorTemperatureChanged(int param1Int) {}
    
    default void onCustomEndTimeChanged(LocalTime param1LocalTime) {}
    
    default void onCustomStartTimeChanged(LocalTime param1LocalTime) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/NightDisplayListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */