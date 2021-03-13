package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class DataLoaderParamsParcel implements Parcelable {
  public static final Parcelable.Creator<DataLoaderParamsParcel> CREATOR = new Parcelable.Creator<DataLoaderParamsParcel>() {
      public DataLoaderParamsParcel createFromParcel(Parcel param1Parcel) {
        DataLoaderParamsParcel dataLoaderParamsParcel = new DataLoaderParamsParcel();
        dataLoaderParamsParcel.readFromParcel(param1Parcel);
        return dataLoaderParamsParcel;
      }
      
      public DataLoaderParamsParcel[] newArray(int param1Int) {
        return new DataLoaderParamsParcel[param1Int];
      }
    };
  
  public String arguments;
  
  public String className;
  
  public String packageName;
  
  public int type;
  
  public int describeContents() {
    return 0;
  }
  
  public final void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    if (j < 0)
      return; 
    try {
      this.type = paramParcel.readInt();
      int k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.packageName = paramParcel.readString();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.className = paramParcel.readString();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      this.arguments = paramParcel.readString();
      k = paramParcel.dataPosition();
      if (k - i >= j)
        return; 
      return;
    } finally {
      paramParcel.setDataPosition(i + j);
    } 
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = paramParcel.dataPosition();
    paramParcel.writeInt(0);
    paramParcel.writeInt(this.type);
    paramParcel.writeString(this.packageName);
    paramParcel.writeString(this.className);
    paramParcel.writeString(this.arguments);
    int i = paramParcel.dataPosition();
    paramParcel.setDataPosition(paramInt);
    paramParcel.writeInt(i - paramInt);
    paramParcel.setDataPosition(i);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/DataLoaderParamsParcel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */