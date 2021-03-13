package android.graphics.drawable;

import android.content.res.Resources;

class TransitionState extends LayerDrawable.LayerState {
  TransitionState(TransitionState paramTransitionState, TransitionDrawable paramTransitionDrawable, Resources paramResources) {
    super(paramTransitionState, paramTransitionDrawable, paramResources);
  }
  
  public int getChangingConfigurations() {
    return this.mChangingConfigurations;
  }
  
  public Drawable newDrawable() {
    return new TransitionDrawable(this, (Resources)null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new TransitionDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/TransitionDrawable$TransitionState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */