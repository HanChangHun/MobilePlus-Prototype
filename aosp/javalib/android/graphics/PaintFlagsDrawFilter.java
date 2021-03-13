package android.graphics;

public class PaintFlagsDrawFilter extends DrawFilter {
  public PaintFlagsDrawFilter(int paramInt1, int paramInt2) {
    this.mNativeInt = nativeConstructor(paramInt1, paramInt2);
  }
  
  private static native long nativeConstructor(int paramInt1, int paramInt2);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PaintFlagsDrawFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */