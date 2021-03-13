package android.app.usage;

import android.os.Parcel;
import android.os.Parcelable;

public final class AppStandbyInfo implements Parcelable {
  public static final Parcelable.Creator<AppStandbyInfo> CREATOR = new Parcelable.Creator<AppStandbyInfo>() {
      public AppStandbyInfo createFromParcel(Parcel param1Parcel) {
        return new AppStandbyInfo(param1Parcel);
      }
      
      public AppStandbyInfo[] newArray(int param1Int) {
        return new AppStandbyInfo[param1Int];
      }
    };
  
  public String mPackageName;
  
  public int mStandbyBucket;
  
  private AppStandbyInfo(Parcel paramParcel) {
    this.mPackageName = paramParcel.readString();
    this.mStandbyBucket = paramParcel.readInt();
  }
  
  public AppStandbyInfo(String paramString, int paramInt) {
    this.mPackageName = paramString;
    this.mStandbyBucket = paramInt;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeInt(this.mStandbyBucket);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/AppStandbyInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */