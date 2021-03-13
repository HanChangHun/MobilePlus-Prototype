package android.graphics;

import libcore.util.NativeAllocationRegistry;

public class RuntimeShader extends Shader {
  private boolean mIsOpaque;
  
  private long mNativeInstanceRuntimeShaderFactory;
  
  private byte[] mUniforms;
  
  public RuntimeShader(String paramString, byte[] paramArrayOfbyte, boolean paramBoolean) {
    this(paramString, paramArrayOfbyte, paramBoolean, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  private RuntimeShader(String paramString, byte[] paramArrayOfbyte, boolean paramBoolean, ColorSpace paramColorSpace) {
    super(paramColorSpace);
    this.mUniforms = paramArrayOfbyte;
    this.mIsOpaque = paramBoolean;
    this.mNativeInstanceRuntimeShaderFactory = nativeCreateShaderFactory(paramString);
    NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, this.mNativeInstanceRuntimeShaderFactory);
  }
  
  private static native long nativeCreate(long paramLong1, long paramLong2, byte[] paramArrayOfbyte, long paramLong3, boolean paramBoolean);
  
  private static native long nativeCreateShaderFactory(String paramString);
  
  private static native long nativeGetFinalizer();
  
  long createNativeInstance(long paramLong) {
    return nativeCreate(this.mNativeInstanceRuntimeShaderFactory, paramLong, this.mUniforms, colorSpace().getNativeInstance(), this.mIsOpaque);
  }
  
  public void updateUniforms(byte[] paramArrayOfbyte) {
    this.mUniforms = paramArrayOfbyte;
    discardNativeInstance();
  }
  
  private static class NoImagePreloadHolder {
    public static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(RuntimeShader.class.getClassLoader(), RuntimeShader.nativeGetFinalizer());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RuntimeShader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */