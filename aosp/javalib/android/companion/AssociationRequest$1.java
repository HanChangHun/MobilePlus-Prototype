package android.companion;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AssociationRequest> {
  public AssociationRequest createFromParcel(Parcel paramParcel) {
    return new AssociationRequest(paramParcel, null);
  }
  
  public AssociationRequest[] newArray(int paramInt) {
    return new AssociationRequest[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/AssociationRequest$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */