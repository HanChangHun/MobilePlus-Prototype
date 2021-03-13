package android.graphics.drawable;

import android.content.res.Resources;

final class ScaleState extends DrawableWrapper.DrawableWrapperState {
  private static final float DO_NOT_SCALE = -1.0F;
  
  int mGravity = 3;
  
  int mInitialLevel = 0;
  
  float mScaleHeight = -1.0F;
  
  float mScaleWidth = -1.0F;
  
  private int[] mThemeAttrs;
  
  boolean mUseIntrinsicSizeAsMin = false;
  
  ScaleState(ScaleState paramScaleState, Resources paramResources) {
    super(paramScaleState, paramResources);
    if (paramScaleState != null) {
      this.mScaleWidth = paramScaleState.mScaleWidth;
      this.mScaleHeight = paramScaleState.mScaleHeight;
      this.mGravity = paramScaleState.mGravity;
      this.mUseIntrinsicSizeAsMin = paramScaleState.mUseIntrinsicSizeAsMin;
      this.mInitialLevel = paramScaleState.mInitialLevel;
    } 
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new ScaleDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ScaleDrawable$ScaleState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */