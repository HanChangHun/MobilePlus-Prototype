package android.app.servertransaction;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<ConfigurationChangeItem> {
  public ConfigurationChangeItem createFromParcel(Parcel paramParcel) {
    return new ConfigurationChangeItem(paramParcel, null);
  }
  
  public ConfigurationChangeItem[] newArray(int paramInt) {
    return new ConfigurationChangeItem[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ConfigurationChangeItem$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */