package android.graphics.drawable;

import android.content.res.Resources;

class LayerState extends Drawable.ConstantState {
  static final int N_CHILDREN = 2;
  
  private boolean mAutoMirrored;
  
  int mChangingConfigurations;
  
  private boolean mCheckedOpacity;
  
  private boolean mCheckedStateful;
  
  AdaptiveIconDrawable.ChildDrawable[] mChildren;
  
  int mChildrenChangingConfigurations;
  
  int mDensity;
  
  private boolean mIsStateful;
  
  private int mOpacity;
  
  int mOpacityOverride;
  
  int mSourceDrawableId;
  
  int mSrcDensityOverride;
  
  private int[] mThemeAttrs;
  
  LayerState(LayerState paramLayerState, AdaptiveIconDrawable paramAdaptiveIconDrawable, Resources paramResources) {
    int i = 0;
    this.mSrcDensityOverride = 0;
    this.mOpacityOverride = 0;
    this.mSourceDrawableId = 0;
    this.mAutoMirrored = false;
    if (paramLayerState != null)
      i = paramLayerState.mDensity; 
    this.mDensity = Drawable.resolveDensity(paramResources, i);
    this.mChildren = new AdaptiveIconDrawable.ChildDrawable[2];
    if (paramLayerState != null) {
      AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = paramLayerState.mChildren;
      this.mChangingConfigurations = paramLayerState.mChangingConfigurations;
      this.mChildrenChangingConfigurations = paramLayerState.mChildrenChangingConfigurations;
      this.mSourceDrawableId = paramLayerState.mSourceDrawableId;
      for (i = 0; i < 2; i++) {
        AdaptiveIconDrawable.ChildDrawable childDrawable = arrayOfChildDrawable[i];
        this.mChildren[i] = new AdaptiveIconDrawable.ChildDrawable(childDrawable, paramAdaptiveIconDrawable, paramResources);
      } 
      this.mCheckedOpacity = paramLayerState.mCheckedOpacity;
      this.mOpacity = paramLayerState.mOpacity;
      this.mCheckedStateful = paramLayerState.mCheckedStateful;
      this.mIsStateful = paramLayerState.mIsStateful;
      this.mAutoMirrored = paramLayerState.mAutoMirrored;
      this.mThemeAttrs = paramLayerState.mThemeAttrs;
      this.mOpacityOverride = paramLayerState.mOpacityOverride;
      this.mSrcDensityOverride = paramLayerState.mSrcDensityOverride;
    } else {
      for (i = 0; i < 2; i++)
        this.mChildren[i] = new AdaptiveIconDrawable.ChildDrawable(this.mDensity); 
    } 
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs != null || super.canApplyTheme())
      return true; 
    AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    for (byte b = 0; b < 2; b++) {
      if (arrayOfChildDrawable[b].canApplyTheme())
        return true; 
    } 
    return false;
  }
  
  public final boolean canConstantState() {
    AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    for (byte b = 0; b < 2; b++) {
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
    int k;
    if (this.mCheckedOpacity)
      return this.mOpacity; 
    AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    int i = -1;
    int j = 0;
    while (true) {
      k = i;
      if (j < 2) {
        if ((arrayOfChildDrawable[j]).mDrawable != null) {
          k = j;
          break;
        } 
        j++;
        continue;
      } 
      break;
    } 
    if (k >= 0) {
      j = (arrayOfChildDrawable[k]).mDrawable.getOpacity();
    } else {
      j = -2;
    } 
    k++;
    for (i = j; k < 2; i = j) {
      Drawable drawable = (arrayOfChildDrawable[k]).mDrawable;
      j = i;
      if (drawable != null)
        j = Drawable.resolveOpacity(i, drawable.getOpacity()); 
      k++;
    } 
    this.mOpacity = i;
    this.mCheckedOpacity = true;
    return i;
  }
  
  public final boolean hasFocusStateSpecified() {
    AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    for (byte b = 0; b < 2; b++) {
      Drawable drawable = (arrayOfChildDrawable[b]).mDrawable;
      if (drawable != null && drawable.hasFocusStateSpecified())
        return true; 
    } 
    return false;
  }
  
  public void invalidateCache() {
    this.mCheckedOpacity = false;
    this.mCheckedStateful = false;
  }
  
  public final boolean isStateful() {
    boolean bool2;
    if (this.mCheckedStateful)
      return this.mIsStateful; 
    AdaptiveIconDrawable.ChildDrawable[] arrayOfChildDrawable = this.mChildren;
    boolean bool1 = false;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (b < 2) {
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
    return new AdaptiveIconDrawable(this, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new AdaptiveIconDrawable(this, paramResources);
  }
  
  public final void setDensity(int paramInt) {
    if (this.mDensity != paramInt)
      this.mDensity = paramInt; 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AdaptiveIconDrawable$LayerState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */