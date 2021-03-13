package android.bluetooth.le;

public final class Builder {
  private boolean mIncludeTxPower = false;
  
  private int mInterval = 65519;
  
  public PeriodicAdvertisingParameters build() {
    return new PeriodicAdvertisingParameters(this.mIncludeTxPower, this.mInterval, null);
  }
  
  public Builder setIncludeTxPower(boolean paramBoolean) {
    this.mIncludeTxPower = paramBoolean;
    return this;
  }
  
  public Builder setInterval(int paramInt) {
    if (paramInt >= 80 && paramInt <= 65519) {
      this.mInterval = paramInt;
      return this;
    } 
    throw new IllegalArgumentException("Invalid interval (must be 80-65519)");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/PeriodicAdvertisingParameters$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */