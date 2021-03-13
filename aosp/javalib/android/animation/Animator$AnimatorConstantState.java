package android.animation;

import android.content.res.ConstantState;

class AnimatorConstantState extends ConstantState<Animator> {
  final Animator mAnimator;
  
  int mChangingConf;
  
  public AnimatorConstantState(Animator paramAnimator) {
    this.mAnimator = paramAnimator;
    Animator.access$002(paramAnimator, this);
    this.mChangingConf = this.mAnimator.getChangingConfigurations();
  }
  
  public int getChangingConfigurations() {
    return this.mChangingConf;
  }
  
  public Animator newInstance() {
    Animator animator = this.mAnimator.clone();
    Animator.access$002(animator, this);
    return animator;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/Animator$AnimatorConstantState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */