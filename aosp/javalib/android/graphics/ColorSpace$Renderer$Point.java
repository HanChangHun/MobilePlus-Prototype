package android.graphics;

class Point {
  final int mColor;
  
  final ColorSpace mColorSpace;
  
  final float[] mRgb;
  
  Point(ColorSpace paramColorSpace, float[] paramArrayOffloat, int paramInt) {
    this.mColorSpace = paramColorSpace;
    this.mRgb = paramArrayOffloat;
    this.mColor = paramInt;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Renderer$Point.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */