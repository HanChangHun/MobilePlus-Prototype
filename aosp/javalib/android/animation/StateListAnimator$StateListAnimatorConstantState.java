package android.animation;

import android.content.res.ConstantState;

class StateListAnimatorConstantState extends ConstantState<StateListAnimator> {
  final StateListAnimator mAnimator;
  
  int mChangingConf;
  
  public StateListAnimatorConstantState(StateListAnimator paramStateListAnimator) {
    this.mAnimator = paramStateListAnimator;
    StateListAnimator.access$202(paramStateListAnimator, this);
    this.mChangingConf = this.mAnimator.getChangingConfigurations();
  }
  
  public int getChangingConfigurations() {
    return this.mChangingConf;
  }
  
  public StateListAnimator newInstance() {
    StateListAnimator stateListAnimator = this.mAnimator.clone();
    StateListAnimator.access$202(stateListAnimator, this);
    return stateListAnimator;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/StateListAnimator$StateListAnimatorConstantState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */