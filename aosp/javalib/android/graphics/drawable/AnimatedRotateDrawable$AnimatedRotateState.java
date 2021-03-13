package android.graphics.drawable;

import android.content.res.Resources;

final class AnimatedRotateState extends DrawableWrapper.DrawableWrapperState {
  int mFrameDuration = 150;
  
  int mFramesCount = 12;
  
  float mPivotX = 0.0F;
  
  boolean mPivotXRel = false;
  
  float mPivotY = 0.0F;
  
  boolean mPivotYRel = false;
  
  private int[] mThemeAttrs;
  
  public AnimatedRotateState(AnimatedRotateState paramAnimatedRotateState, Resources paramResources) {
    super(paramAnimatedRotateState, paramResources);
    if (paramAnimatedRotateState != null) {
      this.mPivotXRel = paramAnimatedRotateState.mPivotXRel;
      this.mPivotX = paramAnimatedRotateState.mPivotX;
      this.mPivotYRel = paramAnimatedRotateState.mPivotYRel;
      this.mPivotY = paramAnimatedRotateState.mPivotY;
      this.mFramesCount = paramAnimatedRotateState.mFramesCount;
      this.mFrameDuration = paramAnimatedRotateState.mFrameDuration;
    } 
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new AnimatedRotateDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedRotateDrawable$AnimatedRotateState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */