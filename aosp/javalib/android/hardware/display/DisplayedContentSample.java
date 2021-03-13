package android.hardware.display;

public final class DisplayedContentSample {
  private long mNumFrames;
  
  private long[] mSamplesComponent0;
  
  private long[] mSamplesComponent1;
  
  private long[] mSamplesComponent2;
  
  private long[] mSamplesComponent3;
  
  public DisplayedContentSample(long paramLong, long[] paramArrayOflong1, long[] paramArrayOflong2, long[] paramArrayOflong3, long[] paramArrayOflong4) {
    this.mNumFrames = paramLong;
    this.mSamplesComponent0 = paramArrayOflong1;
    this.mSamplesComponent1 = paramArrayOflong2;
    this.mSamplesComponent2 = paramArrayOflong3;
    this.mSamplesComponent3 = paramArrayOflong4;
  }
  
  public long getNumFrames() {
    return this.mNumFrames;
  }
  
  public long[] getSampleComponent(ColorComponent paramColorComponent) {
    int i = null.$SwitchMap$android$hardware$display$DisplayedContentSample$ColorComponent[paramColorComponent.ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 4)
            return this.mSamplesComponent3; 
          throw new ArrayIndexOutOfBoundsException();
        } 
        return this.mSamplesComponent2;
      } 
      return this.mSamplesComponent1;
    } 
    return this.mSamplesComponent0;
  }
  
  public enum ColorComponent {
    CHANNEL0, CHANNEL1, CHANNEL2, CHANNEL3;
    
    static {
      ColorComponent colorComponent = new ColorComponent("CHANNEL3", 3);
      CHANNEL3 = colorComponent;
      $VALUES = new ColorComponent[] { CHANNEL0, CHANNEL1, CHANNEL2, colorComponent };
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayedContentSample.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */