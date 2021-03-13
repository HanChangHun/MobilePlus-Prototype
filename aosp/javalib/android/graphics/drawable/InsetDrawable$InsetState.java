package android.graphics.drawable;

import android.content.res.Resources;

final class InsetState extends DrawableWrapper.DrawableWrapperState {
  InsetDrawable.InsetValue mInsetBottom;
  
  InsetDrawable.InsetValue mInsetLeft;
  
  InsetDrawable.InsetValue mInsetRight;
  
  InsetDrawable.InsetValue mInsetTop;
  
  private int[] mThemeAttrs;
  
  InsetState(InsetState paramInsetState, Resources paramResources) {
    super(paramInsetState, paramResources);
    if (paramInsetState != null) {
      this.mInsetLeft = paramInsetState.mInsetLeft.clone();
      this.mInsetTop = paramInsetState.mInsetTop.clone();
      this.mInsetRight = paramInsetState.mInsetRight.clone();
      this.mInsetBottom = paramInsetState.mInsetBottom.clone();
      if (paramInsetState.mDensity != this.mDensity)
        applyDensityScaling(paramInsetState.mDensity, this.mDensity); 
    } else {
      this.mInsetLeft = new InsetDrawable.InsetValue();
      this.mInsetTop = new InsetDrawable.InsetValue();
      this.mInsetRight = new InsetDrawable.InsetValue();
      this.mInsetBottom = new InsetDrawable.InsetValue();
    } 
  }
  
  private void applyDensityScaling(int paramInt1, int paramInt2) {
    this.mInsetLeft.scaleFromDensity(paramInt1, paramInt2);
    this.mInsetTop.scaleFromDensity(paramInt1, paramInt2);
    this.mInsetRight.scaleFromDensity(paramInt1, paramInt2);
    this.mInsetBottom.scaleFromDensity(paramInt1, paramInt2);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    InsetState insetState;
    if (paramResources != null) {
      int i = (paramResources.getDisplayMetrics()).densityDpi;
      if (i == 0)
        i = 160; 
      if (i != this.mDensity) {
        insetState = new InsetState(this, paramResources);
      } else {
        insetState = this;
      } 
    } else {
      insetState = this;
    } 
    return new InsetDrawable(insetState, paramResources, null);
  }
  
  void onDensityChanged(int paramInt1, int paramInt2) {
    super.onDensityChanged(paramInt1, paramInt2);
    applyDensityScaling(paramInt1, paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/InsetDrawable$InsetState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */