package android.content.rollback;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<PackageRollbackInfo> {
  public PackageRollbackInfo createFromParcel(Parcel paramParcel) {
    return new PackageRollbackInfo(paramParcel, null);
  }
  
  public PackageRollbackInfo[] newArray(int paramInt) {
    return new PackageRollbackInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/PackageRollbackInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */