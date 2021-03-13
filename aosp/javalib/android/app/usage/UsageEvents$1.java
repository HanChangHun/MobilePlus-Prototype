package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<UsageEvents> {
  public UsageEvents createFromParcel(Parcel paramParcel) {
    return new UsageEvents(paramParcel);
  }
  
  public UsageEvents[] newArray(int paramInt) {
    return new UsageEvents[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/UsageEvents$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */