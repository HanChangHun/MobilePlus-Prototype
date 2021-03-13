package android.graphics.text;

public final class Builder {
  private int mBreakStrategy = 0;
  
  private int mHyphenationFrequency = 0;
  
  private int[] mIndents = null;
  
  private int mJustificationMode = 0;
  
  public LineBreaker build() {
    return new LineBreaker(this.mBreakStrategy, this.mHyphenationFrequency, this.mJustificationMode, this.mIndents, null);
  }
  
  public Builder setBreakStrategy(int paramInt) {
    this.mBreakStrategy = paramInt;
    return this;
  }
  
  public Builder setHyphenationFrequency(int paramInt) {
    this.mHyphenationFrequency = paramInt;
    return this;
  }
  
  public Builder setIndents(int[] paramArrayOfint) {
    this.mIndents = paramArrayOfint;
    return this;
  }
  
  public Builder setJustificationMode(int paramInt) {
    this.mJustificationMode = paramInt;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/text/LineBreaker$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */