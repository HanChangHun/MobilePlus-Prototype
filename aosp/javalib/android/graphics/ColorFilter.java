package android.graphics;

import libcore.util.NativeAllocationRegistry;

public class ColorFilter {
  private Runnable mCleaner;
  
  private long mNativeInstance;
  
  private static native long nativeGetFinalizer();
  
  long createNativeInstance() {
    return 0L;
  }
  
  void discardNativeInstance() {
    if (this.mNativeInstance != 0L) {
      this.mCleaner.run();
      this.mCleaner = null;
      this.mNativeInstance = 0L;
    } 
  }
  
  public long getNativeInstance() {
    if (this.mNativeInstance == 0L) {
      long l = createNativeInstance();
      this.mNativeInstance = l;
      if (l != 0L)
        this.mCleaner = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeInstance); 
    } 
    return this.mNativeInstance;
  }
  
  private static class NoImagePreloadHolder {
    public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(ColorFilter.class.getClassLoader(), ColorFilter.nativeGetFinalizer());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */