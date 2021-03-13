package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class SdpMasRecord implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public SdpMasRecord createFromParcel(Parcel param1Parcel) {
        return new SdpMasRecord(param1Parcel);
      }
      
      public SdpRecord[] newArray(int param1Int) {
        return new SdpRecord[param1Int];
      }
    };
  
  private final int mL2capPsm;
  
  private final int mMasInstanceId;
  
  private final int mProfileVersion;
  
  private final int mRfcommChannelNumber;
  
  private final String mServiceName;
  
  private final int mSupportedFeatures;
  
  private final int mSupportedMessageTypes;
  
  public SdpMasRecord(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, String paramString) {
    this.mMasInstanceId = paramInt1;
    this.mL2capPsm = paramInt2;
    this.mRfcommChannelNumber = paramInt3;
    this.mProfileVersion = paramInt4;
    this.mSupportedFeatures = paramInt5;
    this.mSupportedMessageTypes = paramInt6;
    this.mServiceName = paramString;
  }
  
  public SdpMasRecord(Parcel paramParcel) {
    this.mMasInstanceId = paramParcel.readInt();
    this.mL2capPsm = paramParcel.readInt();
    this.mRfcommChannelNumber = paramParcel.readInt();
    this.mProfileVersion = paramParcel.readInt();
    this.mSupportedFeatures = paramParcel.readInt();
    this.mSupportedMessageTypes = paramParcel.readInt();
    this.mServiceName = paramParcel.readString();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getL2capPsm() {
    return this.mL2capPsm;
  }
  
  public int getMasInstanceId() {
    return this.mMasInstanceId;
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
  
  public int getSupportedFeatures() {
    return this.mSupportedFeatures;
  }
  
  public int getSupportedMessageTypes() {
    return this.mSupportedMessageTypes;
  }
  
  public boolean msgSupported(int paramInt) {
    boolean bool;
    if ((this.mSupportedMessageTypes & paramInt) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    String str1 = "Bluetooth MAS SDP Record:\n";
    if (this.mMasInstanceId != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Bluetooth MAS SDP Record:\n");
      stringBuilder.append("Mas Instance Id: ");
      stringBuilder.append(this.mMasInstanceId);
      stringBuilder.append("\n");
      str1 = stringBuilder.toString();
    } 
    String str2 = str1;
    if (this.mRfcommChannelNumber != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("RFCOMM Chan Number: ");
      stringBuilder.append(this.mRfcommChannelNumber);
      stringBuilder.append("\n");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if (this.mL2capPsm != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("L2CAP PSM: ");
      stringBuilder.append(this.mL2capPsm);
      stringBuilder.append("\n");
      str1 = stringBuilder.toString();
    } 
    str2 = str1;
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
    str2 = str1;
    if (this.mSupportedMessageTypes != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append("Supported msg types: ");
      stringBuilder.append(this.mSupportedMessageTypes);
      stringBuilder.append("\n");
      str2 = stringBuilder.toString();
    } 
    str1 = str2;
    if (this.mSupportedFeatures != -1) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str2);
      stringBuilder.append("Supported features: ");
      stringBuilder.append(this.mSupportedFeatures);
      stringBuilder.append("\n");
      str1 = stringBuilder.toString();
    } 
    return str1;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mMasInstanceId);
    paramParcel.writeInt(this.mL2capPsm);
    paramParcel.writeInt(this.mRfcommChannelNumber);
    paramParcel.writeInt(this.mProfileVersion);
    paramParcel.writeInt(this.mSupportedFeatures);
    paramParcel.writeInt(this.mSupportedMessageTypes);
    paramParcel.writeString(this.mServiceName);
  }
  
  public static final class MessageType {
    public static final int EMAIL = 1;
    
    public static final int MMS = 8;
    
    public static final int SMS_CDMA = 4;
    
    public static final int SMS_GSM = 2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpMasRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */