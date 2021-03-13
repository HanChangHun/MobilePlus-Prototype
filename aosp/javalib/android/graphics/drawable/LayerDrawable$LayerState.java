package android.graphics.drawable;

import android.content.res.Resources;

class LayerState extends Drawable.ConstantState {
  private boolean mAutoMirrored;
  
  int mChangingConfigurations;
  
  private boolean mCheckedOpacity;
  
  private boolean mCheckedStateful;
  
  LayerDrawable.ChildDrawable[] mChildren;
  
  int mChildrenChangingConfigurations;
  
  int mDensity;
  
  private boolean mIsStateful;
  
  int mNumChildren;
  
  private int mOpacity;
  
  int mOpacityOverride;
  
  int mPaddingBottom;
  
  int mPaddingEnd;
  
  int mPaddingLeft;
  
  private int mPaddingMode;
  
  int mPaddingRight;
  
  int mPaddingStart;
  
  int mPaddingTop;
  
  private int[] mThemeAttrs;
  
  LayerState(LayerState paramLayerState, LayerDrawable paramLayerDrawable, Resources paramResources) {
    int i;
    this.mPaddingTop = -1;
    this.mPaddingBottom = -1;
    this.mPaddingLeft = -1;
    this.mPaddingRight = -1;
    this.mPaddingStart = -1;
    this.mPaddingEnd = -1;
    this.mOpacityOverride = 0;
    this.mAutoMirrored = false;
    this.mPaddingMode = 0;
    if (paramLayerState != null) {
      i = paramLayerState.mDensity;
    } else {
      i = 0;
    } 
    this.mDensity = Drawable.resolveDensity(paramResources, i);
    if (paramLayerState != null) {
      LayerDrawable.ChildDrawable[] arrayOfChildDrawable = paramLayerState.mChildren;
      int j = paramLayerState.mNumChildren;
      this.mNumChildren = j;
      this.mChildren = new LayerDrawable.ChildDrawable[j];
      this.mChangingConfigurations = paramLayerState.mChangingConfigurations;
      this.mChildrenChangingConfigurations = paramLayerState.mChildrenChangingConfigurations;
      for (i = 0; i < j; i++) {
        LayerDrawable.ChildDrawable childDrawable = arrayOfChildDrawable[i];
        this.mChildren[i] = new LayerDrawable.ChildDrawable(childDrawable, paramLayerDrawable, paramResources);
      } 
      this.mCheckedOpacity = paramLayerState.mCheckedOpacity;
      this.mOpacity = paramLayerState.mOpacity;
      this.mCheckedStateful = paramLayerState.mCheckedStateful;
      this.mIsStateful = paramLayerState.mIsStateful;
      this.mAutoMirrored = paramLayerState.mAutoMirrored;
      this.mPaddingMode = paramLayerState.mPaddingMode;
      this.mThemeAttrs = paramLayerState.mThemeAttrs;
      this.mPaddingTop = paramLayerState.mPaddingTop;
      this.mPaddingBottom = paramLayerState.mPaddingBottom;
      this.mPaddingLeft = paramLayerState.mPaddingLeft;
      this.mPaddingRight = paramLayerState.mPaddingRight;
      this.mPaddingStart = paramLayerState.mPaddingStart;
      this.mPaddingEnd = paramLayerState.mPaddingEnd;
      this.mOpacityOverride = paramLayerState.mOpacityOverride;
      i = paramLayerState.mDensity;
      j = this.mDensity;
      if (i != j)
        applyDensityScaling(i, j); 
    } else {
      this.mNumChildren = 0;
      this.mChildren = null;
    } 
  }
  
  private void applyDensityScaling(int paramInt1, int paramInt2) {
    int i = this.mPaddingLeft;
    if (i > 0)
      this.mPaddingLeft = Drawable.scaleFromDensity(i, paramInt1, paramInt2, false); 
    i = this.mPaddingTop;
    if (i > 0)
      this.mPaddingTop = Drawable.scaleFromDensity(i, paramInt1, paramInt2, false); 
    i = this.mPaddingRight;
    if (i > 0)
      this.mPaddingRight = Drawable.scaleFromDensity(i, paramInt1, paramInt2, false); 
    i = this.mPaddingBottom;
    if (i > 0)
      this.mPaddingBottom = Drawable.scaleFromDensity(i, paramInt1, paramInt2, false); 
    i = this.mPaddingStart;
    if (i > 0)
      this.mPaddingStart = Drawable.scaleFromDensity(i, paramInt1, paramInt2, false); 
    i = this.mPaddingEnd;
    if (i > 0)
      this.mPaddingEnd = Drawable.scaleFromDensity(i, paramInt1, paramInt2, false); 
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs != null || super.canApplyTheme())
      return true; 
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    int i = this.mNumChildren;
    for (byte b = 0; b < i; b++) {
      if (arrayOfChildDrawable[b].canApplyTheme())
        return true; 
    } 
    return false;
  }
  
  public final boolean canConstantState() {
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    int i = this.mNumChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null && drawable.getConstantState() == null)
        return false; 
    } 
    return true;
  }
  
  public int getChangingConfigurations() {
    return this.mChangingConfigurations | this.mChildrenChangingConfigurations;
  }
  
  public final int getOpacity() {
    int m;
    if (this.mCheckedOpacity)
      return this.mOpacity; 
    int i = this.mNumChildren;
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    int j = -1;
    int k = 0;
    while (true) {
      m = j;
      if (k < i) {
        if ((arrayOfChildDrawable[k]).mDrawable != null) {
          m = k;
          break;
        } 
        k++;
        continue;
      } 
      break;
    } 
    if (m >= 0) {
      k = (arrayOfChildDrawable[m]).mDrawable.getOpacity();
    } else {
      k = -2;
    } 
    while (++m < i) {
      Drawable drawable = (arrayOfChildDrawable[m]).mDrawable;
      j = k;
      if (drawable != null)
        j = Drawable.resolveOpacity(k, drawable.getOpacity()); 
      m++;
      k = j;
    } 
    this.mOpacity = k;
    this.mCheckedOpacity = true;
    return k;
  }
  
  public final boolean hasFocusStateSpecified() {
    int i = this.mNumChildren;
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    for (byte b = 0; b < i; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null && drawable.hasFocusStateSpecified())
        return true; 
    } 
    return false;
  }
  
  void invalidateCache() {
    this.mCheckedOpacity = false;
    this.mCheckedStateful = false;
  }
  
  public final boolean isStateful() {
    boolean bool2;
    if (this.mCheckedStateful)
      return this.mIsStateful; 
    int i = this.mNumChildren;
    LayerDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    boolean bool1 = false;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (b < i) {
        Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
        if (drawable != null && drawable.isStateful()) {
          bool2 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    this.mIsStateful = bool2;
    this.mCheckedStateful = true;
    return bool2;
  }
  
  public Drawable newDrawable() {
    return new LayerDrawable(this, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new LayerDrawable(this, paramResources);
  }
  
  protected void onDensityChanged(int paramInt1, int paramInt2) {
    applyDensityScaling(paramInt1, paramInt2);
  }
  
  public final void setDensity(int paramInt) {
    if (this.mDensity != paramInt) {
      int i = this.mDensity;
      this.mDensity = paramInt;
      onDensityChanged(i, paramInt);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/LayerDrawable$LayerState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */