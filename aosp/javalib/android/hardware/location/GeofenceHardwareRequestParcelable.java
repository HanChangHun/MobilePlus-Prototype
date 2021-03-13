package android.hardware.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public final class GeofenceHardwareRequestParcelable implements Parcelable {
  public static final Parcelable.Creator<GeofenceHardwareRequestParcelable> CREATOR = new Parcelable.Creator<GeofenceHardwareRequestParcelable>() {
      public GeofenceHardwareRequestParcelable createFromParcel(Parcel param1Parcel) {
        int i = param1Parcel.readInt();
        if (i != 0) {
          Log.e("GeofenceHardwareRequest", String.format("Invalid Geofence type: %d", new Object[] { Integer.valueOf(i) }));
          return null;
        } 
        GeofenceHardwareRequest geofenceHardwareRequest = GeofenceHardwareRequest.createCircularGeofence(param1Parcel.readDouble(), param1Parcel.readDouble(), param1Parcel.readDouble());
        geofenceHardwareRequest.setLastTransition(param1Parcel.readInt());
        geofenceHardwareRequest.setMonitorTransitions(param1Parcel.readInt());
        geofenceHardwareRequest.setUnknownTimer(param1Parcel.readInt());
        geofenceHardwareRequest.setNotificationResponsiveness(param1Parcel.readInt());
        geofenceHardwareRequest.setSourceTechnologies(param1Parcel.readInt());
        return new GeofenceHardwareRequestParcelable(param1Parcel.readInt(), geofenceHardwareRequest);
      }
      
      public GeofenceHardwareRequestParcelable[] newArray(int param1Int) {
        return new GeofenceHardwareRequestParcelable[param1Int];
      }
    };
  
  private int mId;
  
  private GeofenceHardwareRequest mRequest;
  
  public GeofenceHardwareRequestParcelable(int paramInt, GeofenceHardwareRequest paramGeofenceHardwareRequest) {
    this.mId = paramInt;
    this.mRequest = paramGeofenceHardwareRequest;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getId() {
    return this.mId;
  }
  
  public int getLastTransition() {
    return this.mRequest.getLastTransition();
  }
  
  public double getLatitude() {
    return this.mRequest.getLatitude();
  }
  
  public double getLongitude() {
    return this.mRequest.getLongitude();
  }
  
  public int getMonitorTransitions() {
    return this.mRequest.getMonitorTransitions();
  }
  
  public int getNotificationResponsiveness() {
    return this.mRequest.getNotificationResponsiveness();
  }
  
  public double getRadius() {
    return this.mRequest.getRadius();
  }
  
  int getSourceTechnologies() {
    return this.mRequest.getSourceTechnologies();
  }
  
  int getType() {
    return this.mRequest.getType();
  }
  
  public int getUnknownTimer() {
    return this.mRequest.getUnknownTimer();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("id=");
    stringBuilder.append(this.mId);
    stringBuilder.append(", type=");
    stringBuilder.append(this.mRequest.getType());
    stringBuilder.append(", latitude=");
    stringBuilder.append(this.mRequest.getLatitude());
    stringBuilder.append(", longitude=");
    stringBuilder.append(this.mRequest.getLongitude());
    stringBuilder.append(", radius=");
    stringBuilder.append(this.mRequest.getRadius());
    stringBuilder.append(", lastTransition=");
    stringBuilder.append(this.mRequest.getLastTransition());
    stringBuilder.append(", unknownTimer=");
    stringBuilder.append(this.mRequest.getUnknownTimer());
    stringBuilder.append(", monitorTransitions=");
    stringBuilder.append(this.mRequest.getMonitorTransitions());
    stringBuilder.append(", notificationResponsiveness=");
    stringBuilder.append(this.mRequest.getNotificationResponsiveness());
    stringBuilder.append(", sourceTechnologies=");
    stringBuilder.append(this.mRequest.getSourceTechnologies());
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(getType());
    paramParcel.writeDouble(getLatitude());
    paramParcel.writeDouble(getLongitude());
    paramParcel.writeDouble(getRadius());
    paramParcel.writeInt(getLastTransition());
    paramParcel.writeInt(getMonitorTransitions());
    paramParcel.writeInt(getUnknownTimer());
    paramParcel.writeInt(getNotificationResponsiveness());
    paramParcel.writeInt(getSourceTechnologies());
    paramParcel.writeInt(getId());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareRequestParcelable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */