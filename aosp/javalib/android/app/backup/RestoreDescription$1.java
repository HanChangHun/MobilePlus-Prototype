package android.app.backup;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RestoreDescription> {
  public RestoreDescription createFromParcel(Parcel paramParcel) {
    RestoreDescription restoreDescription = new RestoreDescription(paramParcel, null);
    if ("NO_MORE_PACKAGES".equals(RestoreDescription.access$100(restoreDescription)))
      restoreDescription = RestoreDescription.NO_MORE_PACKAGES; 
    return restoreDescription;
  }
  
  public RestoreDescription[] newArray(int paramInt) {
    return new RestoreDescription[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/RestoreDescription$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */