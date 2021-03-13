package android.hardware;

public final class CarrierFrequencyRange {
  private final int mMaxFrequency;
  
  private final int mMinFrequency;
  
  public CarrierFrequencyRange(int paramInt1, int paramInt2) {
    this.mMinFrequency = paramInt1;
    this.mMaxFrequency = paramInt2;
  }
  
  public int getMaxFrequency() {
    return this.mMaxFrequency;
  }
  
  public int getMinFrequency() {
    return this.mMinFrequency;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ConsumerIrManager$CarrierFrequencyRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */