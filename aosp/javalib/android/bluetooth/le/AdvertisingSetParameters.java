package android.bluetooth.le;

import android.os.Parcel;
import android.os.Parcelable;

public final class AdvertisingSetParameters implements Parcelable {
  public static final Parcelable.Creator<AdvertisingSetParameters> CREATOR = new Parcelable.Creator<AdvertisingSetParameters>() {
      public AdvertisingSetParameters createFromParcel(Parcel param1Parcel) {
        return new AdvertisingSetParameters(param1Parcel);
      }
      
      public AdvertisingSetParameters[] newArray(int param1Int) {
        return new AdvertisingSetParameters[param1Int];
      }
    };
  
  public static final int INTERVAL_HIGH = 1600;
  
  public static final int INTERVAL_LOW = 160;
  
  public static final int INTERVAL_MAX = 16777215;
  
  public static final int INTERVAL_MEDIUM = 400;
  
  public static final int INTERVAL_MIN = 160;
  
  private static final int LIMITED_ADVERTISING_MAX_MILLIS = 180000;
  
  public static final int TX_POWER_HIGH = 1;
  
  public static final int TX_POWER_LOW = -15;
  
  public static final int TX_POWER_MAX = 1;
  
  public static final int TX_POWER_MEDIUM = -7;
  
  public static final int TX_POWER_MIN = -127;
  
  public static final int TX_POWER_ULTRA_LOW = -21;
  
  private final boolean mConnectable;
  
  private final boolean mIncludeTxPower;
  
  private final int mInterval;
  
  private final boolean mIsAnonymous;
  
  private final boolean mIsLegacy;
  
  private final int mPrimaryPhy;
  
  private final boolean mScannable;
  
  private final int mSecondaryPhy;
  
  private final int mTxPowerLevel;
  
  private AdvertisingSetParameters(Parcel paramParcel) {
    boolean bool2;
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mConnectable = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mScannable = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsLegacy = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mIsAnonymous = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mIncludeTxPower = bool2;
    this.mPrimaryPhy = paramParcel.readInt();
    this.mSecondaryPhy = paramParcel.readInt();
    this.mInterval = paramParcel.readInt();
    this.mTxPowerLevel = paramParcel.readInt();
  }
  
  private AdvertisingSetParameters(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mConnectable = paramBoolean1;
    this.mScannable = paramBoolean2;
    this.mIsLegacy = paramBoolean3;
    this.mIsAnonymous = paramBoolean4;
    this.mIncludeTxPower = paramBoolean5;
    this.mPrimaryPhy = paramInt1;
    this.mSecondaryPhy = paramInt2;
    this.mInterval = paramInt3;
    this.mTxPowerLevel = paramInt4;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int getInterval() {
    return this.mInterval;
  }
  
  public int getPrimaryPhy() {
    return this.mPrimaryPhy;
  }
  
  public int getSecondaryPhy() {
    return this.mSecondaryPhy;
  }
  
  public int getTxPowerLevel() {
    return this.mTxPowerLevel;
  }
  
  public boolean includeTxPower() {
    return this.mIncludeTxPower;
  }
  
  public boolean isAnonymous() {
    return this.mIsAnonymous;
  }
  
  public boolean isConnectable() {
    return this.mConnectable;
  }
  
  public boolean isLegacy() {
    return this.mIsLegacy;
  }
  
  public boolean isScannable() {
    return this.mScannable;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AdvertisingSetParameters [connectable=");
    stringBuilder.append(this.mConnectable);
    stringBuilder.append(", isLegacy=");
    stringBuilder.append(this.mIsLegacy);
    stringBuilder.append(", isAnonymous=");
    stringBuilder.append(this.mIsAnonymous);
    stringBuilder.append(", includeTxPower=");
    stringBuilder.append(this.mIncludeTxPower);
    stringBuilder.append(", primaryPhy=");
    stringBuilder.append(this.mPrimaryPhy);
    stringBuilder.append(", secondaryPhy=");
    stringBuilder.append(this.mSecondaryPhy);
    stringBuilder.append(", interval=");
    stringBuilder.append(this.mInterval);
    stringBuilder.append(", txPowerLevel=");
    stringBuilder.append(this.mTxPowerLevel);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mConnectable);
    paramParcel.writeInt(this.mScannable);
    paramParcel.writeInt(this.mIsLegacy);
    paramParcel.writeInt(this.mIsAnonymous);
    paramParcel.writeInt(this.mIncludeTxPower);
    paramParcel.writeInt(this.mPrimaryPhy);
    paramParcel.writeInt(this.mSecondaryPhy);
    paramParcel.writeInt(this.mInterval);
    paramParcel.writeInt(this.mTxPowerLevel);
  }
  
  public static final class Builder {
    private boolean mConnectable = false;
    
    private boolean mIncludeTxPower = false;
    
    private int mInterval = 160;
    
    private boolean mIsAnonymous = false;
    
    private boolean mIsLegacy = false;
    
    private int mPrimaryPhy = 1;
    
    private boolean mScannable = false;
    
    private int mSecondaryPhy = 1;
    
    private int mTxPowerLevel = -7;
    
    public AdvertisingSetParameters build() {
      if (this.mIsLegacy) {
        if (!this.mIsAnonymous) {
          if (!this.mConnectable || this.mScannable) {
            if (this.mIncludeTxPower)
              throw new IllegalStateException("Legacy advertising can't include TX power level in header"); 
            return new AdvertisingSetParameters(this.mConnectable, this.mScannable, this.mIsLegacy, this.mIsAnonymous, this.mIncludeTxPower, this.mPrimaryPhy, this.mSecondaryPhy, this.mInterval, this.mTxPowerLevel);
          } 
          throw new IllegalStateException("Legacy advertisement can't be connectable and non-scannable");
        } 
        throw new IllegalArgumentException("Legacy advertising can't be anonymous");
      } 
      if (!this.mConnectable || !this.mScannable) {
        if (this.mIsAnonymous && this.mConnectable)
          throw new IllegalStateException("Advertising can't be both connectable and anonymous"); 
        return new AdvertisingSetParameters(this.mConnectable, this.mScannable, this.mIsLegacy, this.mIsAnonymous, this.mIncludeTxPower, this.mPrimaryPhy, this.mSecondaryPhy, this.mInterval, this.mTxPowerLevel);
      } 
      throw new IllegalStateException("Advertising can't be both connectable and scannable");
    }
    
    public Builder setAnonymous(boolean param1Boolean) {
      this.mIsAnonymous = param1Boolean;
      return this;
    }
    
    public Builder setConnectable(boolean param1Boolean) {
      this.mConnectable = param1Boolean;
      return this;
    }
    
    public Builder setIncludeTxPower(boolean param1Boolean) {
      this.mIncludeTxPower = param1Boolean;
      return this;
    }
    
    public Builder setInterval(int param1Int) {
      if (param1Int >= 160 && param1Int <= 16777215) {
        this.mInterval = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("unknown interval ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder setLegacyMode(boolean param1Boolean) {
      this.mIsLegacy = param1Boolean;
      return this;
    }
    
    public Builder setPrimaryPhy(int param1Int) {
      if (param1Int == 1 || param1Int == 3) {
        this.mPrimaryPhy = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("bad primaryPhy ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder setScannable(boolean param1Boolean) {
      this.mScannable = param1Boolean;
      return this;
    }
    
    public Builder setSecondaryPhy(int param1Int) {
      if (param1Int == 1 || param1Int == 2 || param1Int == 3) {
        this.mSecondaryPhy = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("bad secondaryPhy ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder setTxPowerLevel(int param1Int) {
      if (param1Int >= -127 && param1Int <= 1) {
        this.mTxPowerLevel = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("unknown txPowerLevel ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertisingSetParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */