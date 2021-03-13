package android.graphics.drawable;

import android.graphics.Rect;

abstract class RippleComponent {
  protected final Rect mBounds;
  
  protected float mDensityScale;
  
  private boolean mHasMaxRadius;
  
  protected final RippleDrawable mOwner;
  
  protected float mTargetRadius;
  
  public RippleComponent(RippleDrawable paramRippleDrawable, Rect paramRect) {
    this.mOwner = paramRippleDrawable;
    this.mBounds = paramRect;
  }
  
  private static float getTargetRadius(Rect paramRect) {
    float f1 = paramRect.width() / 2.0F;
    float f2 = paramRect.height() / 2.0F;
    return (float)Math.sqrt((f1 * f1 + f2 * f2));
  }
  
  public void getBounds(Rect paramRect) {
    int i = (int)Math.ceil(this.mTargetRadius);
    paramRect.set(-i, -i, i, i);
  }
  
  protected final void invalidateSelf() {
    this.mOwner.invalidateSelf(false);
  }
  
  public void onBoundsChange() {
    if (!this.mHasMaxRadius) {
      float f = getTargetRadius(this.mBounds);
      this.mTargetRadius = f;
      onTargetRadiusChanged(f);
    } 
  }
  
  protected final void onHotspotBoundsChanged() {
    if (!this.mHasMaxRadius) {
      float f = getTargetRadius(this.mBounds);
      this.mTargetRadius = f;
      onTargetRadiusChanged(f);
    } 
  }
  
  protected void onTargetRadiusChanged(float paramFloat) {}
  
  public final void setup(float paramFloat, int paramInt) {
    if (paramFloat >= 0.0F) {
      this.mHasMaxRadius = true;
      this.mTargetRadius = paramFloat;
    } else {
      this.mTargetRadius = getTargetRadius(this.mBounds);
    } 
    this.mDensityScale = paramInt * 0.00625F;
    onTargetRadiusChanged(this.mTargetRadius);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/RippleComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */