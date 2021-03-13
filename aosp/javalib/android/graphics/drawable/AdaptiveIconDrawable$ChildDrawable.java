package android.graphics.drawable;

import android.content.res.Resources;

class ChildDrawable {
  public int mDensity;
  
  public Drawable mDrawable;
  
  public int[] mThemeAttrs;
  
  ChildDrawable(int paramInt) {
    this.mDensity = 160;
    this.mDensity = paramInt;
  }
  
  ChildDrawable(ChildDrawable paramChildDrawable, AdaptiveIconDrawable paramAdaptiveIconDrawable, Resources paramResources) {
    Drawable drawable2;
    this.mDensity = 160;
    Drawable drawable1 = paramChildDrawable.mDrawable;
    if (drawable1 != null) {
      Drawable.ConstantState constantState = drawable1.getConstantState();
      if (constantState == null) {
        drawable2 = drawable1;
      } else if (paramResources != null) {
        drawable2 = drawable2.newDrawable(paramResources);
      } else {
        drawable2 = drawable2.newDrawable();
      } 
      drawable2.setCallback(paramAdaptiveIconDrawable);
      drawable2.setBounds(drawable1.getBounds());
      drawable2.setLevel(drawable1.getLevel());
    } else {
      drawable2 = null;
    } 
    this.mDrawable = drawable2;
    this.mThemeAttrs = paramChildDrawable.mThemeAttrs;
    this.mDensity = Drawable.resolveDensity(paramResources, paramChildDrawable.mDensity);
  }
  
  public boolean canApplyTheme() {
    if (this.mThemeAttrs == null) {
      Drawable drawable = this.mDrawable;
      return (drawable != null && drawable.canApplyTheme());
    } 
    return true;
  }
  
  public final void setDensity(int paramInt) {
    if (this.mDensity != paramInt)
      this.mDensity = paramInt; 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AdaptiveIconDrawable$ChildDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */