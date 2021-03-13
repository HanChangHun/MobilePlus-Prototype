package android.hardware.camera2.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

class null implements Parcelable.Creator<OutputConfiguration> {
  public OutputConfiguration createFromParcel(Parcel paramParcel) {
    try {
      return new OutputConfiguration(paramParcel, null);
    } catch (Exception exception) {
      Log.e("OutputConfiguration", "Exception creating OutputConfiguration from parcel", exception);
      return null;
    } 
  }
  
  public OutputConfiguration[] newArray(int paramInt) {
    return new OutputConfiguration[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/OutputConfiguration$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */