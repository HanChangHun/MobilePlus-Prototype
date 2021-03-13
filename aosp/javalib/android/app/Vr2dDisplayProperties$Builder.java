package android.app;

public final class Builder {
  private int mAddedFlags = 0;
  
  private int mDpi = -1;
  
  private int mHeight = -1;
  
  private int mRemovedFlags = 0;
  
  private int mWidth = -1;
  
  public Builder addFlags(int paramInt) {
    this.mAddedFlags |= paramInt;
    this.mRemovedFlags &= paramInt;
    return this;
  }
  
  public Vr2dDisplayProperties build() {
    return new Vr2dDisplayProperties(this.mWidth, this.mHeight, this.mDpi, this.mAddedFlags, this.mRemovedFlags, null);
  }
  
  public Builder removeFlags(int paramInt) {
    this.mRemovedFlags |= paramInt;
    this.mAddedFlags &= paramInt;
    return this;
  }
  
  public Builder setDimensions(int paramInt1, int paramInt2, int paramInt3) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mDpi = paramInt3;
    return this;
  }
  
  public Builder setEnabled(boolean paramBoolean) {
    if (paramBoolean) {
      addFlags(1);
    } else {
      removeFlags(1);
    } 
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Vr2dDisplayProperties$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */