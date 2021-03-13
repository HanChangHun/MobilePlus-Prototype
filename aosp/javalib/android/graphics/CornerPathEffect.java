package android.graphics;

public class CornerPathEffect extends PathEffect {
  public CornerPathEffect(float paramFloat) {
    this.native_instance = nativeCreate(paramFloat);
  }
  
  private static native long nativeCreate(float paramFloat);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/CornerPathEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */