package android.graphics.drawable;

import android.content.res.Resources;

final class AnimationState extends DrawableContainer.DrawableContainerState {
  private int[] mDurations;
  
  private boolean mOneShot = false;
  
  AnimationState(AnimationState paramAnimationState, AnimationDrawable paramAnimationDrawable, Resources paramResources) {
    super(paramAnimationState, paramAnimationDrawable, paramResources);
    if (paramAnimationState != null) {
      this.mDurations = paramAnimationState.mDurations;
      this.mOneShot = paramAnimationState.mOneShot;
    } else {
      this.mDurations = new int[getCapacity()];
      this.mOneShot = false;
    } 
  }
  
  private void mutate() {
    this.mDurations = (int[])this.mDurations.clone();
  }
  
  public void addFrame(Drawable paramDrawable, int paramInt) {
    int i = addChild(paramDrawable);
    this.mDurations[i] = paramInt;
  }
  
  public void growArray(int paramInt1, int paramInt2) {
    super.growArray(paramInt1, paramInt2);
    int[] arrayOfInt = new int[paramInt2];
    System.arraycopy(this.mDurations, 0, arrayOfInt, 0, paramInt1);
    this.mDurations = arrayOfInt;
  }
  
  public Drawable newDrawable() {
    return new AnimationDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new AnimationDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimationDrawable$AnimationState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */