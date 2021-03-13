package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

public final class AdvertiseSettings implements Parcelable {
  public static final int ADVERTISE_MODE_BALANCED = 1;
  
  public static final int ADVERTISE_MODE_LOW_LATENCY = 2;
  
  public static final int ADVERTISE_MODE_LOW_POWER = 0;
  
  public static final int ADVERTISE_TX_POWER_HIGH = 3;
  
  public static final int ADVERTISE_TX_POWER_LOW = 1;
  
  public static final int ADVERTISE_TX_POWER_MEDIUM = 2;
  
  public static final int ADVERTISE_TX_POWER_ULTRA_LOW = 0;
  
  public static final Parcelable.Creator<AdvertiseSettings> CREATOR = new Parcelable.Creator<AdvertiseSettings>() {
      public AdvertiseSettings createFromParcel(Parcel param1Parcel) {
        return new AdvertiseSettings(param1Parcel);
      }
      
      public AdvertiseSettings[] newArray(int param1Int) {
        return new AdvertiseSettings[param1Int];
      }
    };
  
  private static final int LIMITED_ADVERTISING_MAX_MILLIS = 180000;
  
  private final boolean mAdvertiseConnectable;
  
  private final int mAdvertiseMode;
  
  private final int mAdvertiseTimeoutMillis;
  
  private final int mAdvertiseTxPowerLevel;
  
  private AdvertiseSettings(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) {
    this.mAdvertiseMode = paramInt1;
    this.mAdvertiseTxPowerLevel = paramInt2;
    this.mAdvertiseConnectable = paramBoolean;
    this.mAdvertiseTimeoutMillis = paramInt3;
  }
  
  private AdvertiseSettings(Parcel paramParcel) {
    boolean bool;
    this.mAdvertiseMode = paramParcel.readInt();
    this.mAdvertiseTxPowerLevel = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mAdvertiseConnectable = bool;
    this.mAdvertiseTimeoutMillis = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getMode() {
    return this.mAdvertiseMode;
  }
  
  public int getTimeout() {
    return this.mAdvertiseTimeoutMillis;
  }
  
  public int getTxPowerLevel() {
    return this.mAdvertiseTxPowerLevel;
  }
  
  public boolean isConnectable() {
    return this.mAdvertiseConnectable;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Settings [mAdvertiseMode=");
    stringBuilder.append(this.mAdvertiseMode);
    stringBuilder.append(", mAdvertiseTxPowerLevel=");
    stringBuilder.append(this.mAdvertiseTxPowerLevel);
    stringBuilder.append(", mAdvertiseConnectable=");
    stringBuilder.append(this.mAdvertiseConnectable);
    stringBuilder.append(", mAdvertiseTimeoutMillis=");
    stringBuilder.append(this.mAdvertiseTimeoutMillis);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mAdvertiseMode);
    paramParcel.writeInt(this.mAdvertiseTxPowerLevel);
    paramParcel.writeInt(this.mAdvertiseConnectable);
    paramParcel.writeInt(this.mAdvertiseTimeoutMillis);
  }
  
  public static final class Builder {
    private boolean mConnectable = true;
    
    private int mMode = 0;
    
    private int mTimeoutMillis = 0;
    
    private int mTxPowerLevel = 2;
    
    public AdvertiseSettings build() {
      return new AdvertiseSettings(this.mMode, this.mTxPowerLevel, this.mConnectable, this.mTimeoutMillis);
    }
    
    public Builder setAdvertiseMode(int param1Int) {
      if (param1Int >= 0 && param1Int <= 2) {
        this.mMode = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("unknown mode ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder setConnectable(boolean param1Boolean) {
      this.mConnectable = param1Boolean;
      return this;
    }
    
    public Builder setTimeout(int param1Int) {
      if (param1Int >= 0 && param1Int <= 180000) {
        this.mTimeoutMillis = param1Int;
        return this;
      } 
      throw new IllegalArgumentException("timeoutMillis invalid (must be 0-180000 milliseconds)");
    }
    
    public Builder setTxPowerLevel(int param1Int) {
      if (param1Int >= 0 && param1Int <= 3) {
        this.mTxPowerLevel = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("unknown tx power level ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertiseSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */