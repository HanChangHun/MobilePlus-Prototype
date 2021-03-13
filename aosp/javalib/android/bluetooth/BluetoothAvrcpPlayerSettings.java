package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class BluetoothAvrcpPlayerSettings implements Parcelable {
  public static final Parcelable.Creator<BluetoothAvrcpPlayerSettings> CREATOR = new Parcelable.Creator<BluetoothAvrcpPlayerSettings>() {
      public BluetoothAvrcpPlayerSettings createFromParcel(Parcel param1Parcel) {
        return new BluetoothAvrcpPlayerSettings(param1Parcel);
      }
      
      public BluetoothAvrcpPlayerSettings[] newArray(int param1Int) {
        return new BluetoothAvrcpPlayerSettings[param1Int];
      }
    };
  
  public static final int SETTING_EQUALIZER = 1;
  
  public static final int SETTING_REPEAT = 2;
  
  public static final int SETTING_SCAN = 8;
  
  public static final int SETTING_SHUFFLE = 4;
  
  public static final int STATE_ALL_TRACK = 3;
  
  public static final int STATE_GROUP = 4;
  
  public static final int STATE_INVALID = -1;
  
  public static final int STATE_OFF = 0;
  
  public static final int STATE_ON = 1;
  
  public static final int STATE_SINGLE_TRACK = 2;
  
  public static final String TAG = "BluetoothAvrcpPlayerSettings";
  
  private int mSettings;
  
  private Map<Integer, Integer> mSettingsValue = new HashMap<>();
  
  public BluetoothAvrcpPlayerSettings(int paramInt) {
    this.mSettings = paramInt;
  }
  
  private BluetoothAvrcpPlayerSettings(Parcel paramParcel) {
    this.mSettings = paramParcel.readInt();
    int i = paramParcel.readInt();
    for (byte b = 0; b < i; b++)
      this.mSettingsValue.put(Integer.valueOf(paramParcel.readInt()), Integer.valueOf(paramParcel.readInt())); 
  }
  
  public void addSettingValue(int paramInt1, int paramInt2) {
    if ((this.mSettings & paramInt1) != 0) {
      this.mSettingsValue.put(Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Setting not supported: ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" ");
    stringBuilder.append(this.mSettings);
    Log.e("BluetoothAvrcpPlayerSettings", stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append("Setting not supported: ");
    stringBuilder.append(paramInt1);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getSettingValue(int paramInt) {
    if ((this.mSettings & paramInt) != 0) {
      Integer integer = this.mSettingsValue.get(Integer.valueOf(paramInt));
      return (integer == null) ? -1 : integer.intValue();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Setting not supported: ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" ");
    stringBuilder.append(this.mSettings);
    Log.e("BluetoothAvrcpPlayerSettings", stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append("Setting not supported: ");
    stringBuilder.append(paramInt);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public int getSettings() {
    return this.mSettings;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mSettings);
    paramParcel.writeInt(this.mSettingsValue.size());
    Iterator<Integer> iterator = this.mSettingsValue.keySet().iterator();
    while (iterator.hasNext()) {
      paramInt = ((Integer)iterator.next()).intValue();
      paramParcel.writeInt(paramInt);
      paramParcel.writeInt(((Integer)this.mSettingsValue.get(Integer.valueOf(paramInt))).intValue());
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothAvrcpPlayerSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */