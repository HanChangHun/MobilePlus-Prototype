package android.graphics;

public class DashPathEffect extends PathEffect {
  public DashPathEffect(float[] paramArrayOffloat, float paramFloat) {
    if (paramArrayOffloat.length >= 2) {
      this.native_instance = nativeCreate(paramArrayOffloat, paramFloat);
      return;
    } 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  private static native long nativeCreate(float[] paramArrayOffloat, float paramFloat);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/DashPathEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */