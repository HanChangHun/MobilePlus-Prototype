package android.graphics;

public class PathEffect {
  long native_instance;
  
  private static native void nativeDestructor(long paramLong);
  
  protected void finalize() throws Throwable {
    nativeDestructor(this.native_instance);
    this.native_instance = 0L;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PathEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */