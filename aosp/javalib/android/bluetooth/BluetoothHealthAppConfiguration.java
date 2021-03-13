package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

@Deprecated
public final class BluetoothHealthAppConfiguration implements Parcelable {
  @Deprecated
  public static final Parcelable.Creator<BluetoothHealthAppConfiguration> CREATOR = new Parcelable.Creator<BluetoothHealthAppConfiguration>() {
      public BluetoothHealthAppConfiguration createFromParcel(Parcel param1Parcel) {
        return new BluetoothHealthAppConfiguration();
      }
      
      public BluetoothHealthAppConfiguration[] newArray(int param1Int) {
        return new BluetoothHealthAppConfiguration[param1Int];
      }
    };
  
  public int describeContents() {
    return 0;
  }
  
  @Deprecated
  public int getDataType() {
    return 0;
  }
  
  @Deprecated
  public String getName() {
    return null;
  }
  
  @Deprecated
  public int getRole() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothHealthAppConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */