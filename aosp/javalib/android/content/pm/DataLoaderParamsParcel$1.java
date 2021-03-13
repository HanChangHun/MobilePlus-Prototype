package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<DataLoaderParamsParcel> {
  public DataLoaderParamsParcel createFromParcel(Parcel paramParcel) {
    DataLoaderParamsParcel dataLoaderParamsParcel = new DataLoaderParamsParcel();
    dataLoaderParamsParcel.readFromParcel(paramParcel);
    return dataLoaderParamsParcel;
  }
  
  public DataLoaderParamsParcel[] newArray(int paramInt) {
    return new DataLoaderParamsParcel[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/DataLoaderParamsParcel$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */