package android.app;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ResultInfo> {
  public ResultInfo createFromParcel(Parcel paramParcel) {
    return new ResultInfo(paramParcel);
  }
  
  public ResultInfo[] newArray(int paramInt) {
    return new ResultInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ResultInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */