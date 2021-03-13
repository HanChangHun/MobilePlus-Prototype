package android.hardware.camera2.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

class null implements Parcelable.Creator<SessionConfiguration> {
  public SessionConfiguration createFromParcel(Parcel paramParcel) {
    try {
      return new SessionConfiguration(paramParcel, null);
    } catch (Exception exception) {
      Log.e("SessionConfiguration", "Exception creating SessionConfiguration from parcel", exception);
      return null;
    } 
  }
  
  public SessionConfiguration[] newArray(int paramInt) {
    return new SessionConfiguration[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/SessionConfiguration$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */