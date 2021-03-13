package android.app.backup;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public class RestoreSet implements Parcelable {
  public static final Parcelable.Creator<RestoreSet> CREATOR = new Parcelable.Creator<RestoreSet>() {
      public RestoreSet createFromParcel(Parcel param1Parcel) {
        return new RestoreSet(param1Parcel);
      }
      
      public RestoreSet[] newArray(int param1Int) {
        return new RestoreSet[param1Int];
      }
    };
  
  public String device;
  
  public String name;
  
  public long token;
  
  public RestoreSet() {}
  
  private RestoreSet(Parcel paramParcel) {
    this.name = paramParcel.readString();
    this.device = paramParcel.readString();
    this.token = paramParcel.readLong();
  }
  
  public RestoreSet(String paramString1, String paramString2, long paramLong) {
    this.name = paramString1;
    this.device = paramString2;
    this.token = paramLong;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.name);
    paramParcel.writeString(this.device);
    paramParcel.writeLong(this.token);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/RestoreSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */