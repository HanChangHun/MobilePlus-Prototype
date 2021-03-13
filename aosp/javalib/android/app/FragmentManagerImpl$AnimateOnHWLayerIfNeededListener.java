package android.app;

import android.animation.Animator;
import android.view.View;

class AnimateOnHWLayerIfNeededListener implements Animator.AnimatorListener {
  private boolean mShouldRunOnHWLayer = false;
  
  private View mView;
  
  public AnimateOnHWLayerIfNeededListener(View paramView) {
    if (paramView == null)
      return; 
    this.mView = paramView;
  }
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator) {
    if (this.mShouldRunOnHWLayer)
      this.mView.setLayerType(0, null); 
    this.mView = null;
    paramAnimator.removeListener(this);
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {
    boolean bool = FragmentManagerImpl.shouldRunOnHWLayer(this.mView, paramAnimator);
    this.mShouldRunOnHWLayer = bool;
    if (bool)
      this.mView.setLayerType(2, null); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManagerImpl$AnimateOnHWLayerIfNeededListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */