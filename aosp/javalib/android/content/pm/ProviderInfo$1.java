package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ProviderInfo> {
  public ProviderInfo createFromParcel(Parcel paramParcel) {
    return new ProviderInfo(paramParcel, null);
  }
  
  public ProviderInfo[] newArray(int paramInt) {
    return new ProviderInfo[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ProviderInfo$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */