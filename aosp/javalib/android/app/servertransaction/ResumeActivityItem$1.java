package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ResumeActivityItem> {
  public ResumeActivityItem createFromParcel(Parcel paramParcel) {
    return new ResumeActivityItem(paramParcel, null);
  }
  
  public ResumeActivityItem[] newArray(int paramInt) {
    return new ResumeActivityItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ResumeActivityItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */