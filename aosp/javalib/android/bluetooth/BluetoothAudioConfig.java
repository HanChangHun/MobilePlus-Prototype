package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothAudioConfig implements Parcelable {
  public static final Parcelable.Creator<BluetoothAudioConfig> CREATOR = new Parcelable.Creator<BluetoothAudioConfig>() {
      public BluetoothAudioConfig createFromParcel(Parcel param1Parcel) {
        return new BluetoothAudioConfig(param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt());
      }
      
      public BluetoothAudioConfig[] newArray(int param1Int) {
        return new BluetoothAudioConfig[param1Int];
      }
    };
  
  private final int mAudioFormat;
  
  private final int mChannelConfig;
  
  private final int mSampleRate;
  
  public BluetoothAudioConfig(int paramInt1, int paramInt2, int paramInt3) {
    this.mSampleRate = paramInt1;
    this.mChannelConfig = paramInt2;
    this.mAudioFormat = paramInt3;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof BluetoothAudioConfig;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      bool = bool1;
      if (((BluetoothAudioConfig)paramObject).mSampleRate == this.mSampleRate) {
        bool = bool1;
        if (((BluetoothAudioConfig)paramObject).mChannelConfig == this.mChannelConfig) {
          bool = bool1;
          if (((BluetoothAudioConfig)paramObject).mAudioFormat == this.mAudioFormat)
            bool = true; 
        } 
      } 
      return bool;
    } 
    return false;
  }
  
  public int getAudioFormat() {
    return this.mAudioFormat;
  }
  
  public int getChannelConfig() {
    return this.mChannelConfig;
  }
  
  public int getSampleRate() {
    return this.mSampleRate;
  }
  
  public int hashCode() {
    return this.mSampleRate | this.mChannelConfig << 24 | this.mAudioFormat << 28;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{mSampleRate:");
    stringBuilder.append(this.mSampleRate);
    stringBuilder.append(",mChannelConfig:");
    stringBuilder.append(this.mChannelConfig);
    stringBuilder.append(",mAudioFormat:");
    stringBuilder.append(this.mAudioFormat);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mSampleRate);
    paramParcel.writeInt(this.mChannelConfig);
    paramParcel.writeInt(this.mAudioFormat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAudioConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */