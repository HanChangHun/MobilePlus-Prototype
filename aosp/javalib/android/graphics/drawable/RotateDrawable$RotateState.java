package android.graphics.drawable;

import android.content.res.Resources;

final class RotateState extends DrawableWrapper.DrawableWrapperState {
  float mCurrentDegrees = 0.0F;
  
  float mFromDegrees = 0.0F;
  
  float mPivotX = 0.5F;
  
  boolean mPivotXRel = true;
  
  float mPivotY = 0.5F;
  
  boolean mPivotYRel = true;
  
  private int[] mThemeAttrs;
  
  float mToDegrees = 360.0F;
  
  RotateState(RotateState paramRotateState, Resources paramResources) {
    super(paramRotateState, paramResources);
    if (paramRotateState != null) {
      this.mPivotXRel = paramRotateState.mPivotXRel;
      this.mPivotX = paramRotateState.mPivotX;
      this.mPivotYRel = paramRotateState.mPivotYRel;
      this.mPivotY = paramRotateState.mPivotY;
      this.mFromDegrees = paramRotateState.mFromDegrees;
      this.mToDegrees = paramRotateState.mToDegrees;
      this.mCurrentDegrees = paramRotateState.mCurrentDegrees;
    } 
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new RotateDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RotateDrawable$RotateState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */