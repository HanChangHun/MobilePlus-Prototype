package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

public final class BluetoothHidDeviceAppQosSettings implements Parcelable {
  public static final Parcelable.Creator<BluetoothHidDeviceAppQosSettings> CREATOR = new Parcelable.Creator<BluetoothHidDeviceAppQosSettings>() {
      public BluetoothHidDeviceAppQosSettings createFromParcel(Parcel param1Parcel) {
        return new BluetoothHidDeviceAppQosSettings(param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt());
      }
      
      public BluetoothHidDeviceAppQosSettings[] newArray(int param1Int) {
        return new BluetoothHidDeviceAppQosSettings[param1Int];
      }
    };
  
  public static final int MAX = -1;
  
  public static final int SERVICE_BEST_EFFORT = 1;
  
  public static final int SERVICE_GUARANTEED = 2;
  
  public static final int SERVICE_NO_TRAFFIC = 0;
  
  private final int mDelayVariation;
  
  private final int mLatency;
  
  private final int mPeakBandwidth;
  
  private final int mServiceType;
  
  private final int mTokenBucketSize;
  
  private final int mTokenRate;
  
  public BluetoothHidDeviceAppQosSettings(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    this.mServiceType = paramInt1;
    this.mTokenRate = paramInt2;
    this.mTokenBucketSize = paramInt3;
    this.mPeakBandwidth = paramInt4;
    this.mLatency = paramInt5;
    this.mDelayVariation = paramInt6;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getDelayVariation() {
    return this.mDelayVariation;
  }
  
  public int getLatency() {
    return this.mLatency;
  }
  
  public int getPeakBandwidth() {
    return this.mPeakBandwidth;
  }
  
  public int getServiceType() {
    return this.mServiceType;
  }
  
  public int getTokenBucketSize() {
    return this.mTokenBucketSize;
  }
  
  public int getTokenRate() {
    return this.mTokenRate;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mServiceType);
    paramParcel.writeInt(this.mTokenRate);
    paramParcel.writeInt(this.mTokenBucketSize);
    paramParcel.writeInt(this.mPeakBandwidth);
    paramParcel.writeInt(this.mLatency);
    paramParcel.writeInt(this.mDelayVariation);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHidDeviceAppQosSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */