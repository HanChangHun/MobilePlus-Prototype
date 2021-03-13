package android.animation;

public class Tuple {
  final Animator mAnimator;
  
  final int[] mSpecs;
  
  private Tuple(int[] paramArrayOfint, Animator paramAnimator) {
    this.mSpecs = paramArrayOfint;
    this.mAnimator = paramAnimator;
  }
  
  public Animator getAnimator() {
    return this.mAnimator;
  }
  
  public int[] getSpecs() {
    return this.mSpecs;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/StateListAnimator$Tuple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */