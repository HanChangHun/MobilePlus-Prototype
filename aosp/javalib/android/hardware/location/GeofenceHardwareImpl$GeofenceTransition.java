package android.hardware.location;

import android.location.Location;

class GeofenceTransition {
  private int mGeofenceId;
  
  private Location mLocation;
  
  private int mMonitoringType;
  
  private int mSourcesUsed;
  
  private long mTimestamp;
  
  private int mTransition;
  
  GeofenceTransition(int paramInt1, int paramInt2, long paramLong, Location paramLocation, int paramInt3, int paramInt4) {
    this.mGeofenceId = paramInt1;
    this.mTransition = paramInt2;
    this.mTimestamp = paramLong;
    this.mLocation = paramLocation;
    this.mMonitoringType = paramInt3;
    this.mSourcesUsed = paramInt4;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareImpl$GeofenceTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */