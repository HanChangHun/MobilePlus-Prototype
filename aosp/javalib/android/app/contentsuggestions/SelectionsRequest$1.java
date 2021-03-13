package android.app.contentsuggestions;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SelectionsRequest> {
  public SelectionsRequest createFromParcel(Parcel paramParcel) {
    return new SelectionsRequest(paramParcel.readInt(), (Point)paramParcel.readTypedObject(Point.CREATOR), paramParcel.readBundle(), null);
  }
  
  public SelectionsRequest[] newArray(int paramInt) {
    return new SelectionsRequest[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/SelectionsRequest$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */