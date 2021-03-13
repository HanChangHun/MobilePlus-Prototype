package android.hardware.radio;

public class Builder {
  private boolean mAf;
  
  private final RadioManager.BandDescriptor mDescriptor;
  
  private boolean mEa;
  
  private boolean mRds;
  
  private boolean mStereo;
  
  private boolean mTa;
  
  public Builder(RadioManager.FmBandConfig paramFmBandConfig) {
    this.mDescriptor = new RadioManager.BandDescriptor(paramFmBandConfig.getRegion(), paramFmBandConfig.getType(), paramFmBandConfig.getLowerLimit(), paramFmBandConfig.getUpperLimit(), paramFmBandConfig.getSpacing());
    this.mStereo = paramFmBandConfig.getStereo();
    this.mRds = paramFmBandConfig.getRds();
    this.mTa = paramFmBandConfig.getTa();
    this.mAf = paramFmBandConfig.getAf();
    this.mEa = paramFmBandConfig.getEa();
  }
  
  public Builder(RadioManager.FmBandDescriptor paramFmBandDescriptor) {
    this.mDescriptor = new RadioManager.BandDescriptor(paramFmBandDescriptor.getRegion(), paramFmBandDescriptor.getType(), paramFmBandDescriptor.getLowerLimit(), paramFmBandDescriptor.getUpperLimit(), paramFmBandDescriptor.getSpacing());
    this.mStereo = paramFmBandDescriptor.isStereoSupported();
    this.mRds = paramFmBandDescriptor.isRdsSupported();
    this.mTa = paramFmBandDescriptor.isTaSupported();
    this.mAf = paramFmBandDescriptor.isAfSupported();
    this.mEa = paramFmBandDescriptor.isEaSupported();
  }
  
  public RadioManager.FmBandConfig build() {
    return new RadioManager.FmBandConfig(this.mDescriptor.getRegion(), this.mDescriptor.getType(), this.mDescriptor.getLowerLimit(), this.mDescriptor.getUpperLimit(), this.mDescriptor.getSpacing(), this.mStereo, this.mRds, this.mTa, this.mAf, this.mEa);
  }
  
  public Builder setAf(boolean paramBoolean) {
    this.mAf = paramBoolean;
    return this;
  }
  
  public Builder setEa(boolean paramBoolean) {
    this.mEa = paramBoolean;
    return this;
  }
  
  public Builder setRds(boolean paramBoolean) {
    this.mRds = paramBoolean;
    return this;
  }
  
  public Builder setStereo(boolean paramBoolean) {
    this.mStereo = paramBoolean;
    return this;
  }
  
  public Builder setTa(boolean paramBoolean) {
    this.mTa = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$FmBandConfig$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */