package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

class null implements Parcelable.Creator<GeofenceHardwareRequestParcelable> {
  public GeofenceHardwareRequestParcelable createFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i != 0) {
      Log.e("GeofenceHardwareRequest", String.format("Invalid Geofence type: %d", new Object[] { Integer.valueOf(i) }));
      return null;
    } 
    GeofenceHardwareRequest geofenceHardwareRequest = GeofenceHardwareRequest.createCircularGeofence(paramParcel.readDouble(), paramParcel.readDouble(), paramParcel.readDouble());
    geofenceHardwareRequest.setLastTransition(paramParcel.readInt());
    geofenceHardwareRequest.setMonitorTransitions(paramParcel.readInt());
    geofenceHardwareRequest.setUnknownTimer(paramParcel.readInt());
    geofenceHardwareRequest.setNotificationResponsiveness(paramParcel.readInt());
    geofenceHardwareRequest.setSourceTechnologies(paramParcel.readInt());
    return new GeofenceHardwareRequestParcelable(paramParcel.readInt(), geofenceHardwareRequest);
  }
  
  public GeofenceHardwareRequestParcelable[] newArray(int paramInt) {
    return new GeofenceHardwareRequestParcelable[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareRequestParcelable$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */