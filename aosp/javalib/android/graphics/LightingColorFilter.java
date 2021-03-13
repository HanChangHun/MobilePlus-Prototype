package android.graphics;

public class LightingColorFilter extends ColorFilter {
  private int mAdd;
  
  private int mMul;
  
  public LightingColorFilter(int paramInt1, int paramInt2) {
    this.mMul = paramInt1;
    this.mAdd = paramInt2;
  }
  
  private static native long native_CreateLightingFilter(int paramInt1, int paramInt2);
  
  long createNativeInstance() {
    return native_CreateLightingFilter(this.mMul, this.mAdd);
  }
  
  public int getColorAdd() {
    return this.mAdd;
  }
  
  public int getColorMultiply() {
    return this.mMul;
  }
  
  public void setColorAdd(int paramInt) {
    if (this.mAdd != paramInt) {
      this.mAdd = paramInt;
      discardNativeInstance();
    } 
  }
  
  public void setColorMultiply(int paramInt) {
    if (this.mMul != paramInt) {
      this.mMul = paramInt;
      discardNativeInstance();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/LightingColorFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */