package android.hardware.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<GeofenceHardwareMonitorEvent> {
  public GeofenceHardwareMonitorEvent createFromParcel(Parcel paramParcel) {
    ClassLoader classLoader = GeofenceHardwareMonitorEvent.class.getClassLoader();
    return new GeofenceHardwareMonitorEvent(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), (Location)paramParcel.readParcelable(classLoader));
  }
  
  public GeofenceHardwareMonitorEvent[] newArray(int paramInt) {
    return new GeofenceHardwareMonitorEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareMonitorEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */