package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DnsEvent extends NetworkEvent implements Parcelable {
  public static final Parcelable.Creator<DnsEvent> CREATOR = new Parcelable.Creator<DnsEvent>() {
      public DnsEvent createFromParcel(Parcel param1Parcel) {
        return (param1Parcel.readInt() != 1) ? null : new DnsEvent(param1Parcel);
      }
      
      public DnsEvent[] newArray(int param1Int) {
        return new DnsEvent[param1Int];
      }
    };
  
  private final String mHostname;
  
  private final String[] mIpAddresses;
  
  private final int mIpAddressesCount;
  
  private DnsEvent(Parcel paramParcel) {
    this.mHostname = paramParcel.readString();
    this.mIpAddresses = paramParcel.createStringArray();
    this.mIpAddressesCount = paramParcel.readInt();
    this.mPackageName = paramParcel.readString();
    this.mTimestamp = paramParcel.readLong();
    this.mId = paramParcel.readLong();
  }
  
  public DnsEvent(String paramString1, String[] paramArrayOfString, int paramInt, String paramString2, long paramLong) {
    super(paramString2, paramLong);
    this.mHostname = paramString1;
    this.mIpAddresses = paramArrayOfString;
    this.mIpAddressesCount = paramInt;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getHostname() {
    return this.mHostname;
  }
  
  public List<InetAddress> getInetAddresses() {
    String[] arrayOfString = this.mIpAddresses;
    if (arrayOfString == null || arrayOfString.length == 0)
      return Collections.emptyList(); 
    ArrayList<InetAddress> arrayList = new ArrayList(this.mIpAddresses.length);
    for (String str : this.mIpAddresses) {
      try {
        arrayList.add(InetAddress.getByName(str));
      } catch (UnknownHostException unknownHostException) {}
    } 
    return arrayList;
  }
  
  public int getTotalResolvedAddressCount() {
    return this.mIpAddressesCount;
  }
  
  public String toString() {
    String str2;
    long l = this.mId;
    String str1 = this.mHostname;
    String[] arrayOfString = this.mIpAddresses;
    if (arrayOfString == null) {
      str2 = "NONE";
    } else {
      str2 = String.join(" ", (CharSequence[])str2);
    } 
    return String.format("DnsEvent(%d, %s, %s, %d, %d, %s)", new Object[] { Long.valueOf(l), str1, str2, Integer.valueOf(this.mIpAddressesCount), Long.valueOf(this.mTimestamp), this.mPackageName });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(1);
    paramParcel.writeString(this.mHostname);
    paramParcel.writeStringArray(this.mIpAddresses);
    paramParcel.writeInt(this.mIpAddressesCount);
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeLong(this.mTimestamp);
    paramParcel.writeLong(this.mId);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DnsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */