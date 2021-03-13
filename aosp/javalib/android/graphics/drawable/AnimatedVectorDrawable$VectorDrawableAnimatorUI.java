package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Canvas;
import java.util.ArrayList;

class VectorDrawableAnimatorUI implements AnimatedVectorDrawable.VectorDrawableAnimator {
  private final Drawable mDrawable;
  
  private boolean mIsInfinite = false;
  
  private ArrayList<Animator.AnimatorListener> mListenerArray = null;
  
  private AnimatorSet mSet = null;
  
  VectorDrawableAnimatorUI(AnimatedVectorDrawable paramAnimatedVectorDrawable) {
    this.mDrawable = paramAnimatedVectorDrawable;
  }
  
  private void invalidateOwningView() {
    this.mDrawable.invalidateSelf();
  }
  
  public boolean canReverse() {
    boolean bool;
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet != null && animatorSet.canReverse()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void end() {
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet == null)
      return; 
    animatorSet.end();
  }
  
  public void init(AnimatorSet paramAnimatorSet) {
    if (this.mSet == null) {
      boolean bool;
      paramAnimatorSet = paramAnimatorSet.clone();
      this.mSet = paramAnimatorSet;
      if (paramAnimatorSet.getTotalDuration() == -1L) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mIsInfinite = bool;
      ArrayList<Animator.AnimatorListener> arrayList = this.mListenerArray;
      if (arrayList != null && !arrayList.isEmpty()) {
        for (byte b = 0; b < this.mListenerArray.size(); b++)
          this.mSet.addListener(this.mListenerArray.get(b)); 
        this.mListenerArray.clear();
        this.mListenerArray = null;
      } 
      return;
    } 
    throw new UnsupportedOperationException("VectorDrawableAnimator cannot be re-initialized");
  }
  
  public boolean isInfinite() {
    return this.mIsInfinite;
  }
  
  public boolean isRunning() {
    boolean bool;
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet != null && animatorSet.isRunning()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStarted() {
    boolean bool;
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet != null && animatorSet.isStarted()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void onDraw(Canvas paramCanvas) {
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet != null && animatorSet.isStarted())
      invalidateOwningView(); 
  }
  
  public void pause() {
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet == null)
      return; 
    animatorSet.pause();
  }
  
  public void removeListener(Animator.AnimatorListener paramAnimatorListener) {
    ArrayList<Animator.AnimatorListener> arrayList;
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet == null) {
      arrayList = this.mListenerArray;
      if (arrayList == null)
        return; 
      arrayList.remove(paramAnimatorListener);
    } else {
      arrayList.removeListener(paramAnimatorListener);
    } 
  }
  
  public void reset() {
    if (this.mSet == null)
      return; 
    start();
    this.mSet.cancel();
  }
  
  public void resume() {
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet == null)
      return; 
    animatorSet.resume();
  }
  
  public void reverse() {
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet == null)
      return; 
    animatorSet.reverse();
    invalidateOwningView();
  }
  
  public void setListener(Animator.AnimatorListener paramAnimatorListener) {
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet == null) {
      if (this.mListenerArray == null)
        this.mListenerArray = new ArrayList<>(); 
      this.mListenerArray.add(paramAnimatorListener);
    } else {
      animatorSet.addListener(paramAnimatorListener);
    } 
  }
  
  public void start() {
    AnimatorSet animatorSet = this.mSet;
    if (animatorSet == null || animatorSet.isStarted())
      return; 
    this.mSet.start();
    invalidateOwningView();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedVectorDrawable$VectorDrawableAnimatorUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */