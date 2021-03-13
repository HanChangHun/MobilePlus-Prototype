package android.hardware.camera2.params;

final class StreamTemplate {
  public int mFormat;
  
  public boolean mIsInput;
  
  public MandatoryStreamCombination.SizeThreshold mSizeThreshold;
  
  public StreamTemplate(int paramInt, MandatoryStreamCombination.SizeThreshold paramSizeThreshold) {
    this(paramInt, paramSizeThreshold, false);
  }
  
  public StreamTemplate(int paramInt, MandatoryStreamCombination.SizeThreshold paramSizeThreshold, boolean paramBoolean) {
    this.mFormat = paramInt;
    this.mSizeThreshold = paramSizeThreshold;
    this.mIsInput = paramBoolean;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/MandatoryStreamCombination$StreamTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */