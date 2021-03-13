package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class SdpOppOpsRecord implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public SdpOppOpsRecord createFromParcel(Parcel param1Parcel) {
        return new SdpOppOpsRecord(param1Parcel);
      }
      
      public SdpOppOpsRecord[] newArray(int param1Int) {
        return new SdpOppOpsRecord[param1Int];
      }
    };
  
  private final byte[] mFormatsList;
  
  private final int mL2capPsm;
  
  private final int mProfileVersion;
  
  private final int mRfcommChannel;
  
  private final String mServiceName;
  
  public SdpOppOpsRecord(Parcel paramParcel) {
    this.mRfcommChannel = paramParcel.readInt();
    this.mL2capPsm = paramParcel.readInt();
    this.mProfileVersion = paramParcel.readInt();
    this.mServiceName = paramParcel.readString();
    int i = paramParcel.readInt();
    if (i > 0) {
      byte[] arrayOfByte = new byte[i];
      paramParcel.readByteArray(arrayOfByte);
      this.mFormatsList = arrayOfByte;
    } else {
      this.mFormatsList = null;
    } 
  }
  
  public SdpOppOpsRecord(String paramString, int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) {
    this.mServiceName = paramString;
    this.mRfcommChannel = paramInt1;
    this.mL2capPsm = paramInt2;
    this.mProfileVersion = paramInt3;
    this.mFormatsList = paramArrayOfbyte;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public byte[] getFormatsList() {
    return this.mFormatsList;
  }
  
  public int getL2capPsm() {
    return this.mL2capPsm;
  }
  
  public int getProfileVersion() {
    return this.mProfileVersion;
  }
  
  public int getRfcommChannel() {
    return this.mRfcommChannel;
  }
  
  public String getServiceName() {
    return this.mServiceName;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("Bluetooth OPP Server SDP Record:\n");
    stringBuilder.append("  RFCOMM Chan Number: ");
    stringBuilder.append(this.mRfcommChannel);
    stringBuilder.append("\n  L2CAP PSM: ");
    stringBuilder.append(this.mL2capPsm);
    stringBuilder.append("\n  Profile version: ");
    stringBuilder.append(this.mProfileVersion);
    stringBuilder.append("\n  Service Name: ");
    stringBuilder.append(this.mServiceName);
    stringBuilder.append("\n  Formats List: ");
    stringBuilder.append(Arrays.toString(this.mFormatsList));
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRfcommChannel);
    paramParcel.writeInt(this.mL2capPsm);
    paramParcel.writeInt(this.mProfileVersion);
    paramParcel.writeString(this.mServiceName);
    byte[] arrayOfByte = this.mFormatsList;
    if (arrayOfByte != null && arrayOfByte.length > 0) {
      paramParcel.writeInt(arrayOfByte.length);
      paramParcel.writeByteArray(this.mFormatsList);
    } else {
      paramParcel.writeInt(0);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpOppOpsRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */