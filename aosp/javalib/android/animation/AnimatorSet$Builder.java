package android.animation;

public class Builder {
  private AnimatorSet.Node mCurrentNode;
  
  Builder(Animator paramAnimator) {
    AnimatorSet.access$402(paramAnimatorSet, true);
    this.mCurrentNode = AnimatorSet.access$500(paramAnimatorSet, paramAnimator);
  }
  
  public Builder after(long paramLong) {
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
    valueAnimator.setDuration(paramLong);
    after(valueAnimator);
    return this;
  }
  
  public Builder after(Animator paramAnimator) {
    AnimatorSet.Node node = AnimatorSet.access$500(AnimatorSet.this, paramAnimator);
    this.mCurrentNode.addParent(node);
    return this;
  }
  
  public Builder before(Animator paramAnimator) {
    AnimatorSet.Node node = AnimatorSet.access$500(AnimatorSet.this, paramAnimator);
    this.mCurrentNode.addChild(node);
    return this;
  }
  
  public Builder with(Animator paramAnimator) {
    AnimatorSet.Node node = AnimatorSet.access$500(AnimatorSet.this, paramAnimator);
    this.mCurrentNode.addSibling(node);
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorSet$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */