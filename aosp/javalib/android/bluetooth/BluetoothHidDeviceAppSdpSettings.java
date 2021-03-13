package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.EventLog;

public final class BluetoothHidDeviceAppSdpSettings implements Parcelable {
  public static final Parcelable.Creator<BluetoothHidDeviceAppSdpSettings> CREATOR = new Parcelable.Creator<BluetoothHidDeviceAppSdpSettings>() {
      public BluetoothHidDeviceAppSdpSettings createFromParcel(Parcel param1Parcel) {
        return new BluetoothHidDeviceAppSdpSettings(param1Parcel.readString(), param1Parcel.readString(), param1Parcel.readString(), param1Parcel.readByte(), param1Parcel.createByteArray());
      }
      
      public BluetoothHidDeviceAppSdpSettings[] newArray(int param1Int) {
        return new BluetoothHidDeviceAppSdpSettings[param1Int];
      }
    };
  
  private static final int MAX_DESCRIPTOR_SIZE = 2048;
  
  private final String mDescription;
  
  private final byte[] mDescriptors;
  
  private final String mName;
  
  private final String mProvider;
  
  private final byte mSubclass;
  
  public BluetoothHidDeviceAppSdpSettings(String paramString1, String paramString2, String paramString3, byte paramByte, byte[] paramArrayOfbyte) {
    this.mName = paramString1;
    this.mDescription = paramString2;
    this.mProvider = paramString3;
    this.mSubclass = (byte)paramByte;
    if (paramArrayOfbyte != null && paramArrayOfbyte.length <= 2048) {
      this.mDescriptors = (byte[])paramArrayOfbyte.clone();
      return;
    } 
    EventLog.writeEvent(1397638484, new Object[] { "119819889", Integer.valueOf(-1), "" });
    throw new IllegalArgumentException("descriptors must be not null and shorter than 2048");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getDescription() {
    return this.mDescription;
  }
  
  public byte[] getDescriptors() {
    return this.mDescriptors;
  }
  
  public String getName() {
    return this.mName;
  }
  
  public String getProvider() {
    return this.mProvider;
  }
  
  public byte getSubclass() {
    return this.mSubclass;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mName);
    paramParcel.writeString(this.mDescription);
    paramParcel.writeString(this.mProvider);
    paramParcel.writeByte(this.mSubclass);
    paramParcel.writeByteArray(this.mDescriptors);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHidDeviceAppSdpSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */