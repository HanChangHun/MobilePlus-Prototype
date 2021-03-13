package android.app.admin;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

class null implements Parcelable.Creator<NetworkEvent> {
  public NetworkEvent createFromParcel(Parcel paramParcel) {
    StringBuilder stringBuilder;
    int i = paramParcel.dataPosition();
    int j = paramParcel.readInt();
    paramParcel.setDataPosition(i);
    if (j != 1) {
      if (j == 2)
        return (NetworkEvent)ConnectEvent.CREATOR.createFromParcel(paramParcel); 
      stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected NetworkEvent token in parcel: ");
      stringBuilder.append(j);
      throw new ParcelFormatException(stringBuilder.toString());
    } 
    return (NetworkEvent)DnsEvent.CREATOR.createFromParcel((Parcel)stringBuilder);
  }
  
  public NetworkEvent[] newArray(int paramInt) {
    return new NetworkEvent[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/NetworkEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */