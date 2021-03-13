package android.graphics;

public class BlurMaskFilter extends MaskFilter {
  public BlurMaskFilter(float paramFloat, Blur paramBlur) {
    this.native_instance = nativeConstructor(paramFloat, paramBlur.native_int);
  }
  
  private static native long nativeConstructor(float paramFloat, int paramInt);
  
  public enum Blur {
    INNER,
    NORMAL(0),
    OUTER(0),
    SOLID(1);
    
    final int native_int;
    
    static {
      Blur blur = new Blur("INNER", 3, 3);
      INNER = blur;
      $VALUES = new Blur[] { NORMAL, SOLID, OUTER, blur };
    }
    
    Blur(int param1Int1) {
      this.native_int = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BlurMaskFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */