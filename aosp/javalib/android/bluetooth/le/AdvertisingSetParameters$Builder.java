package android.bluetooth.le;

public final class Builder {
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
          return new AdvertisingSetParameters(this.mConnectable, this.mScannable, this.mIsLegacy, this.mIsAnonymous, this.mIncludeTxPower, this.mPrimaryPhy, this.mSecondaryPhy, this.mInterval, this.mTxPowerLevel, null);
        } 
        throw new IllegalStateException("Legacy advertisement can't be connectable and non-scannable");
      } 
      throw new IllegalArgumentException("Legacy advertising can't be anonymous");
    } 
    if (!this.mConnectable || !this.mScannable) {
      if (this.mIsAnonymous && this.mConnectable)
        throw new IllegalStateException("Advertising can't be both connectable and anonymous"); 
      return new AdvertisingSetParameters(this.mConnectable, this.mScannable, this.mIsLegacy, this.mIsAnonymous, this.mIncludeTxPower, this.mPrimaryPhy, this.mSecondaryPhy, this.mInterval, this.mTxPowerLevel, null);
    } 
    throw new IllegalStateException("Advertising can't be both connectable and scannable");
  }
  
  public Builder setAnonymous(boolean paramBoolean) {
    this.mIsAnonymous = paramBoolean;
    return this;
  }
  
  public Builder setConnectable(boolean paramBoolean) {
    this.mConnectable = paramBoolean;
    return this;
  }
  
  public Builder setIncludeTxPower(boolean paramBoolean) {
    this.mIncludeTxPower = paramBoolean;
    return this;
  }
  
  public Builder setInterval(int paramInt) {
    if (paramInt >= 160 && paramInt <= 16777215) {
      this.mInterval = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unknown interval ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder setLegacyMode(boolean paramBoolean) {
    this.mIsLegacy = paramBoolean;
    return this;
  }
  
  public Builder setPrimaryPhy(int paramInt) {
    if (paramInt == 1 || paramInt == 3) {
      this.mPrimaryPhy = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("bad primaryPhy ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder setScannable(boolean paramBoolean) {
    this.mScannable = paramBoolean;
    return this;
  }
  
  public Builder setSecondaryPhy(int paramInt) {
    if (paramInt == 1 || paramInt == 2 || paramInt == 3) {
      this.mSecondaryPhy = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("bad secondaryPhy ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder setTxPowerLevel(int paramInt) {
    if (paramInt >= -127 && paramInt <= 1) {
      this.mTxPowerLevel = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unknown txPowerLevel ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertisingSetParameters$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */