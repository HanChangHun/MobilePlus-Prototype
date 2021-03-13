package android.graphics;

import libcore.util.NativeAllocationRegistry;

public class Shader {
  private Runnable mCleaner;
  
  private final ColorSpace mColorSpace = null;
  
  private Matrix mLocalMatrix;
  
  private long mNativeInstance;
  
  @Deprecated
  public Shader() {}
  
  public Shader(ColorSpace paramColorSpace) {
    if (paramColorSpace != null) {
      paramColorSpace.getNativeInstance();
      return;
    } 
    throw new IllegalArgumentException("Use Shader() to create a Shader with no ColorSpace");
  }
  
  public static long[] convertColors(int[] paramArrayOfint) {
    if (paramArrayOfint.length >= 2) {
      long[] arrayOfLong = new long[paramArrayOfint.length];
      for (byte b = 0; b < paramArrayOfint.length; b++)
        arrayOfLong[b] = Color.pack(paramArrayOfint[b]); 
      return arrayOfLong;
    } 
    throw new IllegalArgumentException("needs >= 2 number of colors");
  }
  
  public static ColorSpace detectColorSpace(long[] paramArrayOflong) {
    if (paramArrayOflong.length >= 2) {
      ColorSpace colorSpace = Color.colorSpace(paramArrayOflong[0]);
      byte b = 1;
      while (b < paramArrayOflong.length) {
        if (Color.colorSpace(paramArrayOflong[b]) == colorSpace) {
          b++;
          continue;
        } 
        throw new IllegalArgumentException("All colors must be in the same ColorSpace!");
      } 
      return colorSpace;
    } 
    throw new IllegalArgumentException("needs >= 2 number of colors");
  }
  
  private static native long nativeGetFinalizer();
  
  protected ColorSpace colorSpace() {
    return this.mColorSpace;
  }
  
  long createNativeInstance(long paramLong) {
    return 0L;
  }
  
  protected final void discardNativeInstance() {
    if (this.mNativeInstance != 0L) {
      this.mCleaner.run();
      this.mCleaner = null;
      this.mNativeInstance = 0L;
    } 
  }
  
  public boolean getLocalMatrix(Matrix paramMatrix) {
    Matrix matrix = this.mLocalMatrix;
    if (matrix != null) {
      paramMatrix.set(matrix);
      return true;
    } 
    return false;
  }
  
  public final long getNativeInstance() {
    verifyNativeInstance();
    if (this.mNativeInstance == 0L) {
      Matrix matrix = this.mLocalMatrix;
      if (matrix == null) {
        l = 0L;
      } else {
        l = matrix.native_instance;
      } 
      long l = createNativeInstance(l);
      this.mNativeInstance = l;
      if (l != 0L)
        this.mCleaner = NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeInstance); 
    } 
    return this.mNativeInstance;
  }
  
  public void setLocalMatrix(Matrix paramMatrix) {
    if (paramMatrix == null || paramMatrix.isIdentity()) {
      if (this.mLocalMatrix != null) {
        this.mLocalMatrix = null;
        discardNativeInstance();
      } 
      return;
    } 
    Matrix matrix = this.mLocalMatrix;
    if (matrix == null) {
      this.mLocalMatrix = new Matrix(paramMatrix);
      discardNativeInstance();
    } else if (!matrix.equals(paramMatrix)) {
      this.mLocalMatrix.set(paramMatrix);
      discardNativeInstance();
    } 
  }
  
  protected void verifyNativeInstance() {}
  
  private static class NoImagePreloadHolder {
    public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Shader.class.getClassLoader(), Shader.nativeGetFinalizer());
  }
  
  public enum TileMode {
    CLAMP(0),
    MIRROR(0),
    REPEAT(1);
    
    final int nativeInt;
    
    static {
      TileMode tileMode = new TileMode("MIRROR", 2, 2);
      MIRROR = tileMode;
      $VALUES = new TileMode[] { CLAMP, REPEAT, tileMode };
    }
    
    TileMode(int param1Int1) {
      this.nativeInt = param1Int1;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Shader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */