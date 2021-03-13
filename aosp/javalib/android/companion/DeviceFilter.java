package android.companion;

import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface DeviceFilter<D extends Parcelable> extends Parcelable {
  public static final int MEDIUM_TYPE_BLUETOOTH = 0;
  
  public static final int MEDIUM_TYPE_BLUETOOTH_LE = 1;
  
  public static final int MEDIUM_TYPE_WIFI = 2;
  
  static <D extends Parcelable> boolean matches(DeviceFilter<D> paramDeviceFilter, D paramD) {
    return (paramDeviceFilter == null || paramDeviceFilter.matches(paramD));
  }
  
  String getDeviceDisplayName(D paramD);
  
  int getMediumType();
  
  boolean matches(D paramD);
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface MediumType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/DeviceFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */