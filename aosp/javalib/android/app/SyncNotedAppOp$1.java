package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SyncNotedAppOp> {
  public SyncNotedAppOp createFromParcel(Parcel paramParcel) {
    return new SyncNotedAppOp(paramParcel);
  }
  
  public SyncNotedAppOp[] newArray(int paramInt) {
    return new SyncNotedAppOp[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SyncNotedAppOp$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */