package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<AsyncNotedAppOp> {
  public AsyncNotedAppOp createFromParcel(Parcel paramParcel) {
    return new AsyncNotedAppOp(paramParcel);
  }
  
  public AsyncNotedAppOp[] newArray(int paramInt) {
    return new AsyncNotedAppOp[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AsyncNotedAppOp$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */