package android.content.rollback;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<RollbackInfo> {
  public RollbackInfo createFromParcel(Parcel paramParcel) {
    return new RollbackInfo(paramParcel, null);
  }
  
  public RollbackInfo[] newArray(int paramInt) {
    return new RollbackInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/RollbackInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */