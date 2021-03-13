package android.app.contentsuggestions;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ClassificationsRequest> {
  public ClassificationsRequest createFromParcel(Parcel paramParcel) {
    return new ClassificationsRequest(paramParcel.createTypedArrayList(ContentSelection.CREATOR), paramParcel.readBundle(), null);
  }
  
  public ClassificationsRequest[] newArray(int paramInt) {
    return new ClassificationsRequest[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/ClassificationsRequest$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */