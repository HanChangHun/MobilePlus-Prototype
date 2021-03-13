package android.graphics.drawable;

import android.content.res.Resources;
import android.util.LongSparseLongArray;
import android.util.SparseIntArray;
import android.util.StateSet;

class AnimatedStateListState extends StateListDrawable.StateListState {
  private static final long REVERSED_BIT = 4294967296L;
  
  private static final long REVERSIBLE_FLAG_BIT = 8589934592L;
  
  int[] mAnimThemeAttrs;
  
  SparseIntArray mStateIds;
  
  LongSparseLongArray mTransitions;
  
  AnimatedStateListState(AnimatedStateListState paramAnimatedStateListState, AnimatedStateListDrawable paramAnimatedStateListDrawable, Resources paramResources) {
    super(paramAnimatedStateListState, paramAnimatedStateListDrawable, paramResources);
    if (paramAnimatedStateListState != null) {
      this.mAnimThemeAttrs = paramAnimatedStateListState.mAnimThemeAttrs;
      this.mTransitions = paramAnimatedStateListState.mTransitions;
      this.mStateIds = paramAnimatedStateListState.mStateIds;
    } else {
      this.mTransitions = new LongSparseLongArray();
      this.mStateIds = new SparseIntArray();
    } 
  }
  
  private static long generateTransitionKey(int paramInt1, int paramInt2) {
    return paramInt1 << 32L | paramInt2;
  }
  
  int addStateSet(int[] paramArrayOfint, Drawable paramDrawable, int paramInt) {
    int i = addStateSet(paramArrayOfint, paramDrawable);
    this.mStateIds.put(i, paramInt);
    return i;
  }
  
  int addTransition(int paramInt1, int paramInt2, Drawable paramDrawable, boolean paramBoolean) {
    int i = addChild(paramDrawable);
    long l1 = generateTransitionKey(paramInt1, paramInt2);
    long l2 = 0L;
    if (paramBoolean)
      l2 = 8589934592L; 
    this.mTransitions.append(l1, i | l2);
    if (paramBoolean) {
      l1 = generateTransitionKey(paramInt2, paramInt1);
      this.mTransitions.append(l1, i | 0x100000000L | l2);
    } 
    return i;
  }
  
  public boolean canApplyTheme() {
    return (this.mAnimThemeAttrs != null || super.canApplyTheme());
  }
  
  int getKeyframeIdAt(int paramInt) {
    boolean bool = false;
    if (paramInt < 0) {
      paramInt = bool;
    } else {
      paramInt = this.mStateIds.get(paramInt, 0);
    } 
    return paramInt;
  }
  
  int indexOfKeyframe(int[] paramArrayOfint) {
    int i = indexOfStateSet(paramArrayOfint);
    return (i >= 0) ? i : indexOfStateSet(StateSet.WILD_CARD);
  }
  
  int indexOfTransition(int paramInt1, int paramInt2) {
    long l = generateTransitionKey(paramInt1, paramInt2);
    return (int)this.mTransitions.get(l, -1L);
  }
  
  boolean isTransitionReversed(int paramInt1, int paramInt2) {
    boolean bool;
    long l = generateTransitionKey(paramInt1, paramInt2);
    if ((this.mTransitions.get(l, -1L) & 0x100000000L) != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void mutate() {
    this.mTransitions = this.mTransitions.clone();
    this.mStateIds = this.mStateIds.clone();
  }
  
  public Drawable newDrawable() {
    return new AnimatedStateListDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new AnimatedStateListDrawable(this, paramResources, null);
  }
  
  boolean transitionHasReversibleFlag(int paramInt1, int paramInt2) {
    boolean bool;
    long l = generateTransitionKey(paramInt1, paramInt2);
    if ((this.mTransitions.get(l, -1L) & 0x200000000L) != 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedStateListDrawable$AnimatedStateListState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */