package android.graphics;

public class EmbossMaskFilter extends MaskFilter {
  @Deprecated
  public EmbossMaskFilter(float[] paramArrayOffloat, float paramFloat1, float paramFloat2, float paramFloat3) {
    if (paramArrayOffloat.length >= 3) {
      this.native_instance = nativeConstructor(paramArrayOffloat, paramFloat1, paramFloat2, paramFloat3);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  private static native long nativeConstructor(float[] paramArrayOffloat, float paramFloat1, float paramFloat2, float paramFloat3);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/EmbossMaskFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */