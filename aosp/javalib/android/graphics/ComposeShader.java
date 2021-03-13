package android.graphics;

public class ComposeShader extends Shader {
  private long mNativeInstanceShaderA;
  
  private long mNativeInstanceShaderB;
  
  private int mPorterDuffMode;
  
  Shader mShaderA;
  
  Shader mShaderB;
  
  private ComposeShader(Shader paramShader1, Shader paramShader2, int paramInt) {
    if (paramShader1 != null && paramShader2 != null) {
      this.mShaderA = paramShader1;
      this.mShaderB = paramShader2;
      this.mPorterDuffMode = paramInt;
      return;
    } 
    throw new IllegalArgumentException("Shader parameters must not be null");
  }
  
  public ComposeShader(Shader paramShader1, Shader paramShader2, BlendMode paramBlendMode) {
    this(paramShader1, paramShader2, (paramBlendMode.getXfermode()).porterDuffMode);
  }
  
  public ComposeShader(Shader paramShader1, Shader paramShader2, PorterDuff.Mode paramMode) {
    this(paramShader1, paramShader2, paramMode.nativeInt);
  }
  
  @Deprecated
  public ComposeShader(Shader paramShader1, Shader paramShader2, Xfermode paramXfermode) {
    this(paramShader1, paramShader2, paramXfermode.porterDuffMode);
  }
  
  private static native long nativeCreate(long paramLong1, long paramLong2, long paramLong3, int paramInt);
  
  long createNativeInstance(long paramLong) {
    this.mNativeInstanceShaderA = this.mShaderA.getNativeInstance();
    this.mNativeInstanceShaderB = this.mShaderB.getNativeInstance();
    return nativeCreate(paramLong, this.mShaderA.getNativeInstance(), this.mShaderB.getNativeInstance(), this.mPorterDuffMode);
  }
  
  protected void verifyNativeInstance() {
    if (this.mShaderA.getNativeInstance() != this.mNativeInstanceShaderA || this.mShaderB.getNativeInstance() != this.mNativeInstanceShaderB)
      discardNativeInstance(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ComposeShader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */