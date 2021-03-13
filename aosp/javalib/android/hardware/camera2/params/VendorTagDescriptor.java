package android.hardware.camera2.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public final class VendorTagDescriptor implements Parcelable {
  public static final Parcelable.Creator<VendorTagDescriptor> CREATOR = new Parcelable.Creator<VendorTagDescriptor>() {
      public VendorTagDescriptor createFromParcel(Parcel param1Parcel) {
        try {
          return new VendorTagDescriptor(param1Parcel);
        } catch (Exception exception) {
          Log.e("VendorTagDescriptor", "Exception creating VendorTagDescriptor from parcel", exception);
          return null;
        } 
      }
      
      public VendorTagDescriptor[] newArray(int param1Int) {
        return new VendorTagDescriptor[param1Int];
      }
    };
  
  private static final String TAG = "VendorTagDescriptor";
  
  private VendorTagDescriptor(Parcel paramParcel) {}
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (paramParcel != null)
      return; 
    throw new IllegalArgumentException("dest must not be null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/VendorTagDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */