package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public class SdpRecord implements Parcelable {
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public SdpRecord createFromParcel(Parcel param1Parcel) {
        return new SdpRecord(param1Parcel);
      }
      
      public SdpRecord[] newArray(int param1Int) {
        return new SdpRecord[param1Int];
      }
    };
  
  private final byte[] mRawData;
  
  private final int mRawSize;
  
  public SdpRecord(int paramInt, byte[] paramArrayOfbyte) {
    this.mRawData = paramArrayOfbyte;
    this.mRawSize = paramInt;
  }
  
  public SdpRecord(Parcel paramParcel) {
    int i = paramParcel.readInt();
    this.mRawSize = i;
    byte[] arrayOfByte = new byte[i];
    this.mRawData = arrayOfByte;
    paramParcel.readByteArray(arrayOfByte);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public byte[] getRawData() {
    return this.mRawData;
  }
  
  public int getRawSize() {
    return this.mRawSize;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BluetoothSdpRecord [rawData=");
    stringBuilder.append(Arrays.toString(this.mRawData));
    stringBuilder.append(", rawSize=");
    stringBuilder.append(this.mRawSize);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mRawSize);
    paramParcel.writeByteArray(this.mRawData);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/SdpRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */