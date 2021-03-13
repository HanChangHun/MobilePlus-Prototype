package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<SuspendDialogInfo> {
  public SuspendDialogInfo createFromParcel(Parcel paramParcel) {
    return new SuspendDialogInfo(paramParcel, null);
  }
  
  public SuspendDialogInfo[] newArray(int paramInt) {
    return new SuspendDialogInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/SuspendDialogInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */