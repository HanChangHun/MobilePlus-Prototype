package android.hardware.display;

public final class DisplayedContentSamplingAttributes {
  private int mComponentMask;
  
  private int mDataspace;
  
  private int mPixelFormat;
  
  public DisplayedContentSamplingAttributes(int paramInt1, int paramInt2, int paramInt3) {
    this.mPixelFormat = paramInt1;
    this.mDataspace = paramInt2;
    this.mComponentMask = paramInt3;
  }
  
  public int getComponentMask() {
    return this.mComponentMask;
  }
  
  public int getDataspace() {
    return this.mDataspace;
  }
  
  public int getPixelFormat() {
    return this.mPixelFormat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayedContentSamplingAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */