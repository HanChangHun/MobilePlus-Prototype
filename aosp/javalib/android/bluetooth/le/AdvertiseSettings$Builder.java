package android.bluetooth.le;

public final class Builder {
  private boolean mConnectable = true;
  
  private int mMode = 0;
  
  private int mTimeoutMillis = 0;
  
  private int mTxPowerLevel = 2;
  
  public AdvertiseSettings build() {
    return new AdvertiseSettings(this.mMode, this.mTxPowerLevel, this.mConnectable, this.mTimeoutMillis, null);
  }
  
  public Builder setAdvertiseMode(int paramInt) {
    if (paramInt >= 0 && paramInt <= 2) {
      this.mMode = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unknown mode ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder setConnectable(boolean paramBoolean) {
    this.mConnectable = paramBoolean;
    return this;
  }
  
  public Builder setTimeout(int paramInt) {
    if (paramInt >= 0 && paramInt <= 180000) {
      this.mTimeoutMillis = paramInt;
      return this;
    } 
    throw new IllegalArgumentException("timeoutMillis invalid (must be 0-180000 milliseconds)");
  }
  
  public Builder setTxPowerLevel(int paramInt) {
    if (paramInt >= 0 && paramInt <= 3) {
      this.mTxPowerLevel = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("unknown tx power level ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/AdvertiseSettings$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */