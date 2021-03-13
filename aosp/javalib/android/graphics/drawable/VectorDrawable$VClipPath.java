package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.PathParser;
import com.android.internal.R;

class VClipPath extends VectorDrawable.VPath {
  private static final int NATIVE_ALLOCATION_SIZE = 120;
  
  private final long mNativePtr = VectorDrawable.access$3700();
  
  public VClipPath() {}
  
  public VClipPath(VClipPath paramVClipPath) {
    super(paramVClipPath);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray) {
    this.mChangingConfigurations |= paramTypedArray.getChangingConfigurations();
    String str2 = paramTypedArray.getString(0);
    if (str2 != null) {
      this.mPathName = str2;
      VectorDrawable.access$2000(this.mNativePtr, this.mPathName);
    } 
    String str1 = paramTypedArray.getString(1);
    if (str1 != null) {
      this.mPathData = new PathParser.PathData(str1);
      VectorDrawable.access$3900(this.mNativePtr, str1, str1.length());
    } 
  }
  
  public void applyTheme(Resources.Theme paramTheme) {}
  
  public boolean canApplyTheme() {
    return false;
  }
  
  public long getNativePtr() {
    return this.mNativePtr;
  }
  
  int getNativeSize() {
    return 120;
  }
  
  public boolean hasFocusStateSpecified() {
    return false;
  }
  
  public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme) {
    TypedArray typedArray = Drawable.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.VectorDrawableClipPath);
    updateStateFromTypedArray(typedArray);
    typedArray.recycle();
  }
  
  public boolean isStateful() {
    return false;
  }
  
  public boolean onStateChange(int[] paramArrayOfint) {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/VectorDrawable$VClipPath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */