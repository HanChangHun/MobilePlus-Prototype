package android.app;

import android.animation.Animator;
import android.transition.Transition;

class AnimationInfo {
  private Boolean mAllowEnterTransitionOverlap;
  
  private Boolean mAllowReturnTransitionOverlap;
  
  Animator mAnimatingAway;
  
  private Transition mEnterTransition = null;
  
  SharedElementCallback mEnterTransitionCallback = SharedElementCallback.NULL_CALLBACK;
  
  boolean mEnterTransitionPostponed;
  
  private Transition mExitTransition = null;
  
  SharedElementCallback mExitTransitionCallback = SharedElementCallback.NULL_CALLBACK;
  
  boolean mIsHideReplaced;
  
  int mNextAnim;
  
  int mNextTransition;
  
  int mNextTransitionStyle;
  
  private Transition mReenterTransition = Fragment.access$800();
  
  private Transition mReturnTransition = Fragment.access$800();
  
  private Transition mSharedElementEnterTransition = null;
  
  private Transition mSharedElementReturnTransition = Fragment.access$800();
  
  Fragment.OnStartEnterTransitionListener mStartEnterTransitionListener;
  
  int mStateAfterAnimating;
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Fragment$AnimationInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */