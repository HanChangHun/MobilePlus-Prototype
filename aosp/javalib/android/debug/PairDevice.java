package android.debug;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

public class PairDevice implements Parcelable {
  public static final Parcelable.Creator<PairDevice> CREATOR = new Parcelable.Creator<PairDevice>() {
      public PairDevice createFromParcel(Parcel param1Parcel) {
        return new PairDevice(param1Parcel.readString(), param1Parcel.readString(), param1Parcel.readBoolean());
      }
      
      public PairDevice[] newArray(int param1Int) {
        return new PairDevice[param1Int];
      }
    };
  
  private final boolean mConnected;
  
  private final String mGuid;
  
  private final String mName;
  
  public PairDevice(String paramString1, String paramString2, boolean paramBoolean) {
    Preconditions.checkStringNotEmpty(paramString1);
    Preconditions.checkStringNotEmpty(paramString2);
    this.mName = paramString1;
    this.mGuid = paramString2;
    this.mConnected = paramBoolean;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getDeviceName() {
    return this.mName;
  }
  
  public String getGuid() {
    return this.mGuid;
  }
  
  public boolean isConnected() {
    return this.mConnected;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\n");
    stringBuilder.append(this.mName);
    stringBuilder.append("\n");
    stringBuilder.append(this.mGuid);
    stringBuilder.append("\n");
    stringBuilder.append(this.mConnected);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mName);
    paramParcel.writeString(this.mGuid);
    paramParcel.writeBoolean(this.mConnected);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/debug/PairDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */