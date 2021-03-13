package android.hardware.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;

class Reaper implements IBinder.DeathRecipient {
  private IGeofenceHardwareCallback mCallback;
  
  private IGeofenceHardwareMonitorCallback mMonitorCallback;
  
  private int mMonitoringType;
  
  Reaper(IGeofenceHardwareCallback paramIGeofenceHardwareCallback, int paramInt) {
    this.mCallback = paramIGeofenceHardwareCallback;
    this.mMonitoringType = paramInt;
  }
  
  Reaper(IGeofenceHardwareMonitorCallback paramIGeofenceHardwareMonitorCallback, int paramInt) {
    this.mMonitorCallback = paramIGeofenceHardwareMonitorCallback;
    this.mMonitoringType = paramInt;
  }
  
  private boolean binderEquals(IInterface paramIInterface1, IInterface paramIInterface2) {
    boolean bool = true;
    null = true;
    if (paramIInterface1 == null) {
      if (paramIInterface2 != null)
        null = false; 
      return null;
    } 
    return (paramIInterface2 != null && paramIInterface1.asBinder() == paramIInterface2.asBinder()) ? bool : false;
  }
  
  private boolean callbackEquals(IGeofenceHardwareCallback paramIGeofenceHardwareCallback) {
    boolean bool;
    IGeofenceHardwareCallback iGeofenceHardwareCallback = this.mCallback;
    if (iGeofenceHardwareCallback != null && iGeofenceHardwareCallback.asBinder() == paramIGeofenceHardwareCallback.asBinder()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean unlinkToDeath() {
    IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = this.mMonitorCallback;
    if (iGeofenceHardwareMonitorCallback != null)
      return iGeofenceHardwareMonitorCallback.asBinder().unlinkToDeath(this, 0); 
    IGeofenceHardwareCallback iGeofenceHardwareCallback = this.mCallback;
    return (iGeofenceHardwareCallback != null) ? iGeofenceHardwareCallback.asBinder().unlinkToDeath(this, 0) : true;
  }
  
  public void binderDied() {
    if (this.mCallback != null) {
      Message message1 = GeofenceHardwareImpl.access$1200(GeofenceHardwareImpl.this).obtainMessage(6, this.mCallback);
      message1.arg1 = this.mMonitoringType;
      GeofenceHardwareImpl.access$1200(GeofenceHardwareImpl.this).sendMessage(message1);
    } else if (this.mMonitorCallback != null) {
      Message message1 = GeofenceHardwareImpl.access$1300(GeofenceHardwareImpl.this).obtainMessage(4, this.mMonitorCallback);
      message1.arg1 = this.mMonitoringType;
      GeofenceHardwareImpl.access$1300(GeofenceHardwareImpl.this).sendMessage(message1);
    } 
    Message message = GeofenceHardwareImpl.access$1400(GeofenceHardwareImpl.this).obtainMessage(3, this);
    GeofenceHardwareImpl.access$1400(GeofenceHardwareImpl.this).sendMessage(message);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (paramObject == this)
      return true; 
    paramObject = paramObject;
    if (binderEquals(((Reaper)paramObject).mCallback, this.mCallback) && binderEquals(((Reaper)paramObject).mMonitorCallback, this.mMonitorCallback) && ((Reaper)paramObject).mMonitoringType == this.mMonitoringType)
      bool = true; 
    return bool;
  }
  
  public int hashCode() {
    byte b;
    IGeofenceHardwareCallback iGeofenceHardwareCallback = this.mCallback;
    int i = 0;
    if (iGeofenceHardwareCallback != null) {
      b = iGeofenceHardwareCallback.asBinder().hashCode();
    } else {
      b = 0;
    } 
    IGeofenceHardwareMonitorCallback iGeofenceHardwareMonitorCallback = this.mMonitorCallback;
    if (iGeofenceHardwareMonitorCallback != null)
      i = iGeofenceHardwareMonitorCallback.asBinder().hashCode(); 
    return ((17 * 31 + b) * 31 + i) * 31 + this.mMonitoringType;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/GeofenceHardwareImpl$Reaper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */