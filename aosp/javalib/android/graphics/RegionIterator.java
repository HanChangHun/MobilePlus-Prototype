package android.graphics;

public class RegionIterator {
  private long mNativeIter;
  
  public RegionIterator(Region paramRegion) {
    this.mNativeIter = nativeConstructor(paramRegion.ni());
  }
  
  private static native long nativeConstructor(long paramLong);
  
  private static native void nativeDestructor(long paramLong);
  
  private static native boolean nativeNext(long paramLong, Rect paramRect);
  
  protected void finalize() throws Throwable {
    nativeDestructor(this.mNativeIter);
    this.mNativeIter = 0L;
  }
  
  public final boolean next(Rect paramRect) {
    if (paramRect != null)
      return nativeNext(this.mNativeIter, paramRect); 
    throw new NullPointerException("The Rect must be provided");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RegionIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */