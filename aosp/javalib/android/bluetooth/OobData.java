package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public class OobData implements Parcelable {
  public static final Parcelable.Creator<OobData> CREATOR = new Parcelable.Creator<OobData>() {
      public OobData createFromParcel(Parcel param1Parcel) {
        return new OobData(param1Parcel);
      }
      
      public OobData[] newArray(int param1Int) {
        return new OobData[param1Int];
      }
    };
  
  private byte[] mLeBluetoothDeviceAddress;
  
  private byte[] mLeSecureConnectionsConfirmation;
  
  private byte[] mLeSecureConnectionsRandom;
  
  private byte[] mSecurityManagerTk;
  
  public OobData() {}
  
  private OobData(Parcel paramParcel) {
    this.mLeBluetoothDeviceAddress = paramParcel.createByteArray();
    this.mSecurityManagerTk = paramParcel.createByteArray();
    this.mLeSecureConnectionsConfirmation = paramParcel.createByteArray();
    this.mLeSecureConnectionsRandom = paramParcel.createByteArray();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public byte[] getLeBluetoothDeviceAddress() {
    return this.mLeBluetoothDeviceAddress;
  }
  
  public byte[] getLeSecureConnectionsConfirmation() {
    return this.mLeSecureConnectionsConfirmation;
  }
  
  public byte[] getLeSecureConnectionsRandom() {
    return this.mLeSecureConnectionsRandom;
  }
  
  public byte[] getSecurityManagerTk() {
    return this.mSecurityManagerTk;
  }
  
  public void setLeBluetoothDeviceAddress(byte[] paramArrayOfbyte) {
    this.mLeBluetoothDeviceAddress = paramArrayOfbyte;
  }
  
  public void setLeSecureConnectionsConfirmation(byte[] paramArrayOfbyte) {
    this.mLeSecureConnectionsConfirmation = paramArrayOfbyte;
  }
  
  public void setLeSecureConnectionsRandom(byte[] paramArrayOfbyte) {
    this.mLeSecureConnectionsRandom = paramArrayOfbyte;
  }
  
  public void setSecurityManagerTk(byte[] paramArrayOfbyte) {
    this.mSecurityManagerTk = paramArrayOfbyte;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeByteArray(this.mLeBluetoothDeviceAddress);
    paramParcel.writeByteArray(this.mSecurityManagerTk);
    paramParcel.writeByteArray(this.mLeSecureConnectionsConfirmation);
    paramParcel.writeByteArray(this.mLeSecureConnectionsRandom);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/OobData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */