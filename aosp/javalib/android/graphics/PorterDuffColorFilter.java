package android.graphics;

public class PorterDuffColorFilter extends ColorFilter {
  private int mColor;
  
  private PorterDuff.Mode mMode;
  
  public PorterDuffColorFilter(int paramInt, PorterDuff.Mode paramMode) {
    this.mColor = paramInt;
    this.mMode = paramMode;
  }
  
  private static native long native_CreateBlendModeFilter(int paramInt1, int paramInt2);
  
  long createNativeInstance() {
    return native_CreateBlendModeFilter(this.mColor, this.mMode.nativeInt);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mColor != ((PorterDuffColorFilter)paramObject).mColor || this.mMode.nativeInt != ((PorterDuffColorFilter)paramObject).mMode.nativeInt)
      bool = false; 
    return bool;
  }
  
  public int getColor() {
    return this.mColor;
  }
  
  public PorterDuff.Mode getMode() {
    return this.mMode;
  }
  
  public int hashCode() {
    return this.mMode.hashCode() * 31 + this.mColor;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/PorterDuffColorFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */