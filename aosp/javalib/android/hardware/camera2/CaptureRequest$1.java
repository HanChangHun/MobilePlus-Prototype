package android.hardware.camera2;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<CaptureRequest> {
  public CaptureRequest createFromParcel(Parcel paramParcel) {
    CaptureRequest captureRequest = new CaptureRequest(null);
    CaptureRequest.access$100(captureRequest, paramParcel);
    return captureRequest;
  }
  
  public CaptureRequest[] newArray(int paramInt) {
    return new CaptureRequest[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/CaptureRequest$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */