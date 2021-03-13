package android.app.admin;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

public abstract class NetworkEvent implements Parcelable {
  public static final Parcelable.Creator<NetworkEvent> CREATOR = new Parcelable.Creator<NetworkEvent>() {
      public NetworkEvent createFromParcel(Parcel param1Parcel) {
        StringBuilder stringBuilder;
        int i = param1Parcel.dataPosition();
        int j = param1Parcel.readInt();
        param1Parcel.setDataPosition(i);
        if (j != 1) {
          if (j == 2)
            return (NetworkEvent)ConnectEvent.CREATOR.createFromParcel(param1Parcel); 
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unexpected NetworkEvent token in parcel: ");
          stringBuilder.append(j);
          throw new ParcelFormatException(stringBuilder.toString());
        } 
        return (NetworkEvent)DnsEvent.CREATOR.createFromParcel((Parcel)stringBuilder);
      }
      
      public NetworkEvent[] newArray(int param1Int) {
        return new NetworkEvent[param1Int];
      }
    };
  
  static final int PARCEL_TOKEN_CONNECT_EVENT = 2;
  
  static final int PARCEL_TOKEN_DNS_EVENT = 1;
  
  long mId;
  
  String mPackageName;
  
  long mTimestamp;
  
  NetworkEvent() {}
  
  NetworkEvent(String paramString, long paramLong) {
    this.mPackageName = paramString;
    this.mTimestamp = paramLong;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getId() {
    return this.mId;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public long getTimestamp() {
    return this.mTimestamp;
  }
  
  public void setId(long paramLong) {
    this.mId = paramLong;
  }
  
  public abstract void writeToParcel(Parcel paramParcel, int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/NetworkEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */