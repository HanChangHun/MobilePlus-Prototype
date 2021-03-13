package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

@SystemApi
public final class NanoAppState implements Parcelable {
  public static final Parcelable.Creator<NanoAppState> CREATOR = new Parcelable.Creator<NanoAppState>() {
      public NanoAppState createFromParcel(Parcel param1Parcel) {
        return new NanoAppState(param1Parcel);
      }
      
      public NanoAppState[] newArray(int param1Int) {
        return new NanoAppState[param1Int];
      }
    };
  
  private boolean mIsEnabled;
  
  private long mNanoAppId;
  
  private int mNanoAppVersion;
  
  public NanoAppState(long paramLong, int paramInt, boolean paramBoolean) {
    this.mNanoAppId = paramLong;
    this.mNanoAppVersion = paramInt;
    this.mIsEnabled = paramBoolean;
  }
  
  private NanoAppState(Parcel paramParcel) {
    this.mNanoAppId = paramParcel.readLong();
    this.mNanoAppVersion = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    this.mIsEnabled = bool;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getNanoAppId() {
    return this.mNanoAppId;
  }
  
  public long getNanoAppVersion() {
    return this.mNanoAppVersion;
  }
  
  public boolean isEnabled() {
    return this.mIsEnabled;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mNanoAppId);
    paramParcel.writeInt(this.mNanoAppVersion);
    paramParcel.writeInt(this.mIsEnabled);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/NanoAppState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */