package android.graphics;

public class DrawFilter {
  public long mNativeInt;
  
  private static native void nativeDestructor(long paramLong);
  
  protected void finalize() throws Throwable {
    try {
      nativeDestructor(this.mNativeInt);
      this.mNativeInt = 0L;
      return;
    } finally {
      super.finalize();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/DrawFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */