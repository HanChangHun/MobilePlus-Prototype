package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class ConnectEvent extends NetworkEvent implements Parcelable {
  public static final Parcelable.Creator<ConnectEvent> CREATOR = new Parcelable.Creator<ConnectEvent>() {
      public ConnectEvent createFromParcel(Parcel param1Parcel) {
        return (param1Parcel.readInt() != 2) ? null : new ConnectEvent(param1Parcel);
      }
      
      public ConnectEvent[] newArray(int param1Int) {
        return new ConnectEvent[param1Int];
      }
    };
  
  private final String mIpAddress;
  
  private final int mPort;
  
  private ConnectEvent(Parcel paramParcel) {
    this.mIpAddress = paramParcel.readString();
    this.mPort = paramParcel.readInt();
    this.mPackageName = paramParcel.readString();
    this.mTimestamp = paramParcel.readLong();
    this.mId = paramParcel.readLong();
  }
  
  public ConnectEvent(String paramString1, int paramInt, String paramString2, long paramLong) {
    super(paramString2, paramLong);
    this.mIpAddress = paramString1;
    this.mPort = paramInt;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public InetAddress getInetAddress() {
    try {
      return InetAddress.getByName(this.mIpAddress);
    } catch (UnknownHostException unknownHostException) {
      return InetAddress.getLoopbackAddress();
    } 
  }
  
  public int getPort() {
    return this.mPort;
  }
  
  public String toString() {
    return String.format("ConnectEvent(%d, %s, %d, %d, %s)", new Object[] { Long.valueOf(this.mId), this.mIpAddress, Integer.valueOf(this.mPort), Long.valueOf(this.mTimestamp), this.mPackageName });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(2);
    paramParcel.writeString(this.mIpAddress);
    paramParcel.writeInt(this.mPort);
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeLong(this.mTimestamp);
    paramParcel.writeLong(this.mId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/ConnectEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */