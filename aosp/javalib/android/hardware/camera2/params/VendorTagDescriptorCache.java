package android.hardware.camera2.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public final class VendorTagDescriptorCache implements Parcelable {
  public static final Parcelable.Creator<VendorTagDescriptorCache> CREATOR = new Parcelable.Creator<VendorTagDescriptorCache>() {
      public VendorTagDescriptorCache createFromParcel(Parcel param1Parcel) {
        try {
          return new VendorTagDescriptorCache(param1Parcel);
        } catch (Exception exception) {
          Log.e("VendorTagDescriptorCache", "Exception creating VendorTagDescriptorCache from parcel", exception);
          return null;
        } 
      }
      
      public VendorTagDescriptorCache[] newArray(int param1Int) {
        return new VendorTagDescriptorCache[param1Int];
      }
    };
  
  private static final String TAG = "VendorTagDescriptorCache";
  
  private VendorTagDescriptorCache(Parcel paramParcel) {}
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (paramParcel != null)
      return; 
    throw new IllegalArgumentException("dest must not be null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/VendorTagDescriptorCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */