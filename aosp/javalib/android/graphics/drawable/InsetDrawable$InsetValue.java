package android.graphics.drawable;

import android.graphics.Bitmap;

final class InsetValue implements Cloneable {
  int mDimension;
  
  final float mFraction;
  
  public InsetValue() {
    this(0.0F, 0);
  }
  
  public InsetValue(float paramFloat, int paramInt) {
    this.mFraction = paramFloat;
    this.mDimension = paramInt;
  }
  
  public InsetValue clone() {
    return new InsetValue(this.mFraction, this.mDimension);
  }
  
  int getDimension(int paramInt) {
    return (int)(paramInt * this.mFraction) + this.mDimension;
  }
  
  void scaleFromDensity(int paramInt1, int paramInt2) {
    int i = this.mDimension;
    if (i != 0)
      this.mDimension = Bitmap.scaleFromDensity(i, paramInt1, paramInt2); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/InsetDrawable$InsetValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */