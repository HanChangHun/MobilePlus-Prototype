package android.graphics;

public class ColorMatrixColorFilter extends ColorFilter {
  private final ColorMatrix mMatrix;
  
  public ColorMatrixColorFilter(ColorMatrix paramColorMatrix) {
    ColorMatrix colorMatrix = new ColorMatrix();
    this.mMatrix = colorMatrix;
    colorMatrix.set(paramColorMatrix);
  }
  
  public ColorMatrixColorFilter(float[] paramArrayOffloat) {
    ColorMatrix colorMatrix = new ColorMatrix();
    this.mMatrix = colorMatrix;
    if (paramArrayOffloat.length >= 20) {
      colorMatrix.set(paramArrayOffloat);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  private static native long nativeColorMatrixFilter(float[] paramArrayOffloat);
  
  long createNativeInstance() {
    return nativeColorMatrixFilter(this.mMatrix.getArray());
  }
  
  public void getColorMatrix(ColorMatrix paramColorMatrix) {
    paramColorMatrix.set(this.mMatrix);
  }
  
  public void setColorMatrix(ColorMatrix paramColorMatrix) {
    discardNativeInstance();
    if (paramColorMatrix == null) {
      this.mMatrix.reset();
    } else {
      this.mMatrix.set(paramColorMatrix);
    } 
  }
  
  public void setColorMatrixArray(float[] paramArrayOffloat) {
    discardNativeInstance();
    if (paramArrayOffloat == null) {
      this.mMatrix.reset();
    } else {
      if (paramArrayOffloat.length >= 20) {
        this.mMatrix.set(paramArrayOffloat);
        return;
      } 
      throw new ArrayIndexOutOfBoundsException();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorMatrixColorFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */