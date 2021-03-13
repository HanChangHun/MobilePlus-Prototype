package android.hardware.camera2.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

class null implements Parcelable.Creator<VendorTagDescriptorCache> {
  public VendorTagDescriptorCache createFromParcel(Parcel paramParcel) {
    try {
      return new VendorTagDescriptorCache(paramParcel, null);
    } catch (Exception exception) {
      Log.e("VendorTagDescriptorCache", "Exception creating VendorTagDescriptorCache from parcel", exception);
      return null;
    } 
  }
  
  public VendorTagDescriptorCache[] newArray(int paramInt) {
    return new VendorTagDescriptorCache[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/VendorTagDescriptorCache$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */