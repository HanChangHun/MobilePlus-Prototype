package android.graphics.drawable;

import android.content.res.Resources;
import android.util.Log;

class ChildDrawable {
  public int mDensity;
  
  public Drawable mDrawable;
  
  public int mGravity;
  
  public int mHeight;
  
  public int mId;
  
  public int mInsetB;
  
  public int mInsetE;
  
  public int mInsetL;
  
  public int mInsetR;
  
  public int mInsetS;
  
  public int mInsetT;
  
  public int[] mThemeAttrs;
  
  public int mWidth;
  
  ChildDrawable(int paramInt) {
    this.mDensity = 160;
    this.mInsetS = Integer.MIN_VALUE;
    this.mInsetE = Integer.MIN_VALUE;
    this.mWidth = -1;
    this.mHeight = -1;
    this.mGravity = 0;
    this.mId = -1;
    this.mDensity = paramInt;
  }
  
  ChildDrawable(ChildDrawable paramChildDrawable, LayerDrawable paramLayerDrawable, Resources paramResources) {
    Drawable drawable2;
    this.mDensity = 160;
    this.mInsetS = Integer.MIN_VALUE;
    this.mInsetE = Integer.MIN_VALUE;
    this.mWidth = -1;
    this.mHeight = -1;
    this.mGravity = 0;
    this.mId = -1;
    Drawable drawable1 = paramChildDrawable.mDrawable;
    if (drawable1 != null) {
      Drawable.ConstantState constantState = drawable1.getConstantState();
      if (constantState == null) {
        Drawable drawable = drawable1;
        drawable2 = drawable;
        if (drawable1.getCallback() != null) {
          Log.w("LayerDrawable", "Invalid drawable added to LayerDrawable! Drawable already belongs to another owner but does not expose a constant state.", new RuntimeException());
          drawable2 = drawable;
        } 
      } else if (paramResources != null) {
        drawable2 = drawable2.newDrawable(paramResources);
      } else {
        drawable2 = drawable2.newDrawable();
      } 
      drawable2.setLayoutDirection(drawable1.getLayoutDirection());
      drawable2.setBounds(drawable1.getBounds());
      drawable2.setLevel(drawable1.getLevel());
      drawable2.setCallback(paramLayerDrawable);
    } else {
      drawable2 = null;
    } 
    this.mDrawable = drawable2;
    this.mThemeAttrs = paramChildDrawable.mThemeAttrs;
    this.mInsetL = paramChildDrawable.mInsetL;
    this.mInsetT = paramChildDrawable.mInsetT;
    this.mInsetR = paramChildDrawable.mInsetR;
    this.mInsetB = paramChildDrawable.mInsetB;
    this.mInsetS = paramChildDrawable.mInsetS;
    this.mInsetE = paramChildDrawable.mInsetE;
    this.mWidth = paramChildDrawable.mWidth;
    this.mHeight = paramChildDrawable.mHeight;
    this.mGravity = paramChildDrawable.mGravity;
    this.mId = paramChildDrawable.mId;
    int i = Drawable.resolveDensity(paramResources, paramChildDrawable.mDensity);
    this.mDensity = i;
    int j = paramChildDrawable.mDensity;
    if (j != i)
      applyDensityScaling(j, i); 
  }
  
  private void applyDensityScaling(int paramInt1, int paramInt2) {
    this.mInsetL = Drawable.scaleFromDensity(this.mInsetL, paramInt1, paramInt2, false);
    this.mInsetT = Drawable.scaleFromDensity(this.mInsetT, paramInt1, paramInt2, false);
    this.mInsetR = Drawable.scaleFromDensity(this.mInsetR, paramInt1, paramInt2, false);
    this.mInsetB = Drawable.scaleFromDensity(this.mInsetB, paramInt1, paramInt2, false);
    int i = this.mInsetS;
    if (i != Integer.MIN_VALUE)
      this.mInsetS = Drawable.scaleFromDensity(i, paramInt1, paramInt2, false); 
    i = this.mInsetE;
    if (i != Integer.MIN_VALUE)
      this.mInsetE = Drawable.scaleFromDensity(i, paramInt1, paramInt2, false); 
    i = this.mWidth;
    if (i > 0)
      this.mWidth = Drawable.scaleFromDensity(i, paramInt1, paramInt2, true); 
    i = this.mHeight;
    if (i > 0)
      this.mHeight = Drawable.scaleFromDensity(i, paramInt1, paramInt2, true); 
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs == null) {
      Drawable drawable = this.mDrawable;
      return (drawable != null && drawable.canApplyTheme());
    } 
    return true;
  }
  
  public final void setDensity(int paramInt) {
    if (this.mDensity != paramInt) {
      int i = this.mDensity;
      this.mDensity = paramInt;
      applyDensityScaling(i, paramInt);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/LayerDrawable$ChildDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */