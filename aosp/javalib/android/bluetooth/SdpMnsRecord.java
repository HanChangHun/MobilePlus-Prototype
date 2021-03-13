package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class SdpMnsRecord implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public SdpMnsRecord createFromParcel(Parcel param1Parcel) {
        return new SdpMnsRecord(param1Parcel);
      }
      
      public SdpMnsRecord[] newArray(int param1Int) {
        return new SdpMnsRecord[param1Int];
      }
    };
  
  private final int mL2capPsm;
  
  private final int mProfileVersion;
  
  private final int mRfcommChannelNumber;
  
  private final String mServiceName;
  
  private final int mSupportedFeatures;
  
  public SdpMnsRecord(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString) {
    this.mL2capPsm = paramInt1;
    this.mRfcommChannelNumber = paramInt2;
    this.mSupportedFeatures = paramInt4;
    this.mServiceName = paramString;
    this.mProfileVersion = paramInt3;
  }
  
  public SdpMnsRecord(Parcel paramParcel) {
    this.mRfcommChannelNumber = paramParcel.readInt();
    this.mL2capPsm = paramParcel.readInt();
    this.mServiceName = paramParcel.readString();
    this.mSupportedFeatures = paramParcel.readInt();
    this.mProfileVersion = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getL2capPsm() {
    return this.mL2capPsm;
  }
  
  public int getProfileVersion() {
    return this.mProfileVersion;
  }
  
  public int getRfcommChannelNumber() {
    return this.mRfcommChannelNumber;
  }
  
  public String getServiceName() {
    return this.mServiceName;
  }
  
  public int getSupportedFeatures() {
    return this.mSupportedFeatures;
  }
  
  public String toString() {
    String str1 = "Bluetooth MNS SDP Record:\n";
    if (this.mRfcommChannelNumber != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bluetooth MNS SDP Record:\n");
      stringBuilder.append("RFCOMM Chan Number: ");
      stringBuilder.append(this.mRfcommChannelNumber);
      stringBuilder.append("\n");
      str1 = stringBuilder.toString();
    } 
    String str2 = str1;
    if (this.mL2capPsm != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("L2CAP PSM: ");
      stringBuilder.append(this.mL2capPsm);
      stringBuilder.append("\n");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if (this.mServiceName != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("Service Name: ");
      stringBuilder.append(this.mServiceName);
      stringBuilder.append("\n");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
    if (this.mSupportedFeatures != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("Supported features: ");
      stringBuilder.append(this.mSupportedFeatures);
      stringBuilder.append("\n");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if (this.mProfileVersion != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("Profile_version: ");
      stringBuilder.append(this.mProfileVersion);
      stringBuilder.append("\n");
      str1 = stringBuilder.toString();
    } 
    return str1;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRfcommChannelNumber);
    paramParcel.writeInt(this.mL2capPsm);
    paramParcel.writeString(this.mServiceName);
    paramParcel.writeInt(this.mSupportedFeatures);
    paramParcel.writeInt(this.mProfileVersion);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpMnsRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */