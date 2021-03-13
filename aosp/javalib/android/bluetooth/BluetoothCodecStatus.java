package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

public final class BluetoothCodecStatus implements Parcelable {
  public static final Parcelable.Creator<BluetoothCodecStatus> CREATOR = new Parcelable.Creator<BluetoothCodecStatus>() {
      public BluetoothCodecStatus createFromParcel(Parcel param1Parcel) {
        return new BluetoothCodecStatus((BluetoothCodecConfig)param1Parcel.readTypedObject(BluetoothCodecConfig.CREATOR), (BluetoothCodecConfig[])param1Parcel.createTypedArray(BluetoothCodecConfig.CREATOR), (BluetoothCodecConfig[])param1Parcel.createTypedArray(BluetoothCodecConfig.CREATOR));
      }
      
      public BluetoothCodecStatus[] newArray(int param1Int) {
        return new BluetoothCodecStatus[param1Int];
      }
    };
  
  public static final String EXTRA_CODEC_STATUS = "android.bluetooth.extra.CODEC_STATUS";
  
  private final BluetoothCodecConfig mCodecConfig;
  
  private final BluetoothCodecConfig[] mCodecsLocalCapabilities;
  
  private final BluetoothCodecConfig[] mCodecsSelectableCapabilities;
  
  public BluetoothCodecStatus(BluetoothCodecConfig paramBluetoothCodecConfig, BluetoothCodecConfig[] paramArrayOfBluetoothCodecConfig1, BluetoothCodecConfig[] paramArrayOfBluetoothCodecConfig2) {
    this.mCodecConfig = paramBluetoothCodecConfig;
    this.mCodecsLocalCapabilities = paramArrayOfBluetoothCodecConfig1;
    this.mCodecsSelectableCapabilities = paramArrayOfBluetoothCodecConfig2;
  }
  
  public static boolean sameCapabilities(BluetoothCodecConfig[] paramArrayOfBluetoothCodecConfig1, BluetoothCodecConfig[] paramArrayOfBluetoothCodecConfig2) {
    boolean bool = false;
    if (paramArrayOfBluetoothCodecConfig1 == null) {
      if (paramArrayOfBluetoothCodecConfig2 == null)
        bool = true; 
      return bool;
    } 
    return (paramArrayOfBluetoothCodecConfig2 == null) ? false : ((paramArrayOfBluetoothCodecConfig1.length != paramArrayOfBluetoothCodecConfig2.length) ? false : Arrays.<BluetoothCodecConfig>asList(paramArrayOfBluetoothCodecConfig1).containsAll(Arrays.asList((Object[])paramArrayOfBluetoothCodecConfig2)));
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof BluetoothCodecStatus;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      if (Objects.equals(((BluetoothCodecStatus)paramObject).mCodecConfig, this.mCodecConfig) && sameCapabilities(((BluetoothCodecStatus)paramObject).mCodecsLocalCapabilities, this.mCodecsLocalCapabilities) && sameCapabilities(((BluetoothCodecStatus)paramObject).mCodecsSelectableCapabilities, this.mCodecsSelectableCapabilities))
        bool1 = true; 
      return bool1;
    } 
    return false;
  }
  
  public BluetoothCodecConfig getCodecConfig() {
    return this.mCodecConfig;
  }
  
  public BluetoothCodecConfig[] getCodecsLocalCapabilities() {
    return this.mCodecsLocalCapabilities;
  }
  
  public BluetoothCodecConfig[] getCodecsSelectableCapabilities() {
    return this.mCodecsSelectableCapabilities;
  }
  
  public int hashCode() {
    BluetoothCodecConfig bluetoothCodecConfig = this.mCodecConfig;
    BluetoothCodecConfig[] arrayOfBluetoothCodecConfig = this.mCodecsLocalCapabilities;
    return Objects.hash(new Object[] { bluetoothCodecConfig, arrayOfBluetoothCodecConfig, arrayOfBluetoothCodecConfig });
  }
  
  public boolean isCodecConfigSelectable(BluetoothCodecConfig paramBluetoothCodecConfig) {
    if (paramBluetoothCodecConfig == null || !paramBluetoothCodecConfig.hasSingleSampleRate() || !paramBluetoothCodecConfig.hasSingleBitsPerSample() || !paramBluetoothCodecConfig.hasSingleChannelMode())
      return false; 
    for (BluetoothCodecConfig bluetoothCodecConfig : this.mCodecsSelectableCapabilities) {
      if (paramBluetoothCodecConfig.getCodecType() == bluetoothCodecConfig.getCodecType()) {
        int i = paramBluetoothCodecConfig.getSampleRate();
        if ((bluetoothCodecConfig.getSampleRate() & i) != 0 || i == 0) {
          i = paramBluetoothCodecConfig.getBitsPerSample();
          if ((bluetoothCodecConfig.getBitsPerSample() & i) != 0 || i == 0) {
            i = paramBluetoothCodecConfig.getChannelMode();
            if ((bluetoothCodecConfig.getChannelMode() & i) != 0 || i == 0)
              return true; 
          } 
        } 
      } 
    } 
    return false;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{mCodecConfig:");
    stringBuilder.append(this.mCodecConfig);
    stringBuilder.append(",mCodecsLocalCapabilities:");
    stringBuilder.append(Arrays.toString((Object[])this.mCodecsLocalCapabilities));
    stringBuilder.append(",mCodecsSelectableCapabilities:");
    stringBuilder.append(Arrays.toString((Object[])this.mCodecsSelectableCapabilities));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedObject(this.mCodecConfig, 0);
    paramParcel.writeTypedArray((Parcelable[])this.mCodecsLocalCapabilities, 0);
    paramParcel.writeTypedArray((Parcelable[])this.mCodecsSelectableCapabilities, 0);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothCodecStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */