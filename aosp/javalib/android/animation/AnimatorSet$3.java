package android.animation;

import java.util.Comparator;

class null implements Comparator<AnimatorSet.AnimationEvent> {
  public int compare(AnimatorSet.AnimationEvent paramAnimationEvent1, AnimatorSet.AnimationEvent paramAnimationEvent2) {
    long l1 = paramAnimationEvent1.getTime();
    long l2 = paramAnimationEvent2.getTime();
    return (l1 == l2) ? ((paramAnimationEvent2.mEvent + paramAnimationEvent1.mEvent == 1) ? (paramAnimationEvent1.mEvent - paramAnimationEvent2.mEvent) : (paramAnimationEvent2.mEvent - paramAnimationEvent1.mEvent)) : ((l2 == -1L) ? -1 : ((l1 == -1L) ? 1 : (int)(l1 - l2)));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorSet$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */