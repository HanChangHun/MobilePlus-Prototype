package android.graphics;

public final class BlendModeColorFilter extends ColorFilter {
  final int mColor;
  
  private final BlendMode mMode;
  
  public BlendModeColorFilter(int paramInt, BlendMode paramBlendMode) {
    this.mColor = paramInt;
    this.mMode = paramBlendMode;
  }
  
  private static native long native_CreateBlendModeFilter(int paramInt1, int paramInt2);
  
  long createNativeInstance() {
    return native_CreateBlendModeFilter(this.mColor, (this.mMode.getXfermode()).porterDuffMode);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (((BlendModeColorFilter)paramObject).mMode != this.mMode)
      bool = false; 
    return bool;
  }
  
  public int getColor() {
    return this.mColor;
  }
  
  public BlendMode getMode() {
    return this.mMode;
  }
  
  public int hashCode() {
    return this.mMode.hashCode() * 31 + this.mColor;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BlendModeColorFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */