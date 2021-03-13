package android.graphics;

public class SumPathEffect extends PathEffect {
  public SumPathEffect(PathEffect paramPathEffect1, PathEffect paramPathEffect2) {
    this.native_instance = nativeCreate(paramPathEffect1.native_instance, paramPathEffect2.native_instance);
  }
  
  private static native long nativeCreate(long paramLong1, long paramLong2);
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/SumPathEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */