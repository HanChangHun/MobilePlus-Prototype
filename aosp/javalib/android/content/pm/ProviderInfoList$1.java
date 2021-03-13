package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProviderInfoList> {
  public ProviderInfoList createFromParcel(Parcel paramParcel) {
    return new ProviderInfoList(paramParcel, null);
  }
  
  public ProviderInfoList[] newArray(int paramInt) {
    return new ProviderInfoList[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ProviderInfoList$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */