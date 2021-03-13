package android.hardware.radio;

public class Builder {
  private final RadioManager.BandDescriptor mDescriptor;
  
  private boolean mStereo;
  
  public Builder(RadioManager.AmBandConfig paramAmBandConfig) {
    this.mDescriptor = new RadioManager.BandDescriptor(paramAmBandConfig.getRegion(), paramAmBandConfig.getType(), paramAmBandConfig.getLowerLimit(), paramAmBandConfig.getUpperLimit(), paramAmBandConfig.getSpacing());
    this.mStereo = paramAmBandConfig.getStereo();
  }
  
  public Builder(RadioManager.AmBandDescriptor paramAmBandDescriptor) {
    this.mDescriptor = new RadioManager.BandDescriptor(paramAmBandDescriptor.getRegion(), paramAmBandDescriptor.getType(), paramAmBandDescriptor.getLowerLimit(), paramAmBandDescriptor.getUpperLimit(), paramAmBandDescriptor.getSpacing());
    this.mStereo = paramAmBandDescriptor.isStereoSupported();
  }
  
  public RadioManager.AmBandConfig build() {
    return new RadioManager.AmBandConfig(this.mDescriptor.getRegion(), this.mDescriptor.getType(), this.mDescriptor.getLowerLimit(), this.mDescriptor.getUpperLimit(), this.mDescriptor.getSpacing(), this.mStereo);
  }
  
  public Builder setStereo(boolean paramBoolean) {
    this.mStereo = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager$AmBandConfig$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */