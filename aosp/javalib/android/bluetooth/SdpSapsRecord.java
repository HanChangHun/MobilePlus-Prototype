package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class SdpSapsRecord implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public SdpSapsRecord createFromParcel(Parcel param1Parcel) {
        return new SdpSapsRecord(param1Parcel);
      }
      
      public SdpRecord[] newArray(int param1Int) {
        return new SdpRecord[param1Int];
      }
    };
  
  private final int mProfileVersion;
  
  private final int mRfcommChannelNumber;
  
  private final String mServiceName;
  
  public SdpSapsRecord(int paramInt1, int paramInt2, String paramString) {
    this.mRfcommChannelNumber = paramInt1;
    this.mProfileVersion = paramInt2;
    this.mServiceName = paramString;
  }
  
  public SdpSapsRecord(Parcel paramParcel) {
    this.mRfcommChannelNumber = paramParcel.readInt();
    this.mProfileVersion = paramParcel.readInt();
    this.mServiceName = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getProfileVersion() {
    return this.mProfileVersion;
  }
  
  public int getRfcommCannelNumber() {
    return this.mRfcommChannelNumber;
  }
  
  public String getServiceName() {
    return this.mServiceName;
  }
  
  public String toString() {
    String str1 = "Bluetooth MAS SDP Record:\n";
    if (this.mRfcommChannelNumber != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bluetooth MAS SDP Record:\n");
      stringBuilder.append("RFCOMM Chan Number: ");
      stringBuilder.append(this.mRfcommChannelNumber);
      stringBuilder.append("\n");
      str1 = stringBuilder.toString();
    } 
    String str2 = str1;
    if (this.mServiceName != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("Service Name: ");
      stringBuilder.append(this.mServiceName);
      stringBuilder.append("\n");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if (this.mProfileVersion != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("Profile version: ");
      stringBuilder.append(this.mProfileVersion);
      stringBuilder.append("\n");
      str1 = stringBuilder.toString();
    } 
    return str1;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRfcommChannelNumber);
    paramParcel.writeInt(this.mProfileVersion);
    paramParcel.writeString(this.mServiceName);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpSapsRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */