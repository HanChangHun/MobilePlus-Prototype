package android.animation;

class AnimationEvent {
  static final int ANIMATION_DELAY_ENDED = 1;
  
  static final int ANIMATION_END = 2;
  
  static final int ANIMATION_START = 0;
  
  final int mEvent;
  
  final AnimatorSet.Node mNode;
  
  AnimationEvent(AnimatorSet.Node paramNode, int paramInt) {
    this.mNode = paramNode;
    this.mEvent = paramInt;
  }
  
  long getTime() {
    int i = this.mEvent;
    if (i == 0)
      return this.mNode.mStartTime; 
    if (i == 1) {
      long l1 = this.mNode.mStartTime;
      long l2 = -1L;
      if (l1 != -1L) {
        l2 = this.mNode.mStartTime;
        l2 = this.mNode.mAnimation.getStartDelay() + l2;
      } 
      return l2;
    } 
    return this.mNode.mEndTime;
  }
  
  public String toString() {
    String str;
    int i = this.mEvent;
    if (i == 0) {
      str = "start";
    } else if (i == 1) {
      str = "delay ended";
    } else {
      str = "end";
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append(" ");
    stringBuilder.append(this.mNode.mAnimation.toString());
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorSet$AnimationEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */