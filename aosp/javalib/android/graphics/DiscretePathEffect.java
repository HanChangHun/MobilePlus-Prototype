package android.graphics;

public class DiscretePathEffect extends PathEffect {
  public DiscretePathEffect(float paramFloat1, float paramFloat2) {
    this.native_instance = nativeCreate(paramFloat1, paramFloat2);
  }
  
  private static native long nativeCreate(float paramFloat1, float paramFloat2);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/DiscretePathEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */