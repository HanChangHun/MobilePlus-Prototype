package android.hardware.camera2.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

class null implements Parcelable.Creator<VendorTagDescriptor> {
  public VendorTagDescriptor createFromParcel(Parcel paramParcel) {
    try {
      return new VendorTagDescriptor(paramParcel, null);
    } catch (Exception exception) {
      Log.e("VendorTagDescriptor", "Exception creating VendorTagDescriptor from parcel", exception);
      return null;
    } 
  }
  
  public VendorTagDescriptor[] newArray(int paramInt) {
    return new VendorTagDescriptor[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/VendorTagDescriptor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */