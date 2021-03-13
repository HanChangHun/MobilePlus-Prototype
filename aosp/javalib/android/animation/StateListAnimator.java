package android.animation;

import android.content.res.ConstantState;
import android.util.StateSet;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class StateListAnimator implements Cloneable {
  private AnimatorListenerAdapter mAnimatorListener;
  
  private int mChangingConfigurations;
  
  private StateListAnimatorConstantState mConstantState;
  
  private Tuple mLastMatch = null;
  
  private Animator mRunningAnimator = null;
  
  private ArrayList<Tuple> mTuples = new ArrayList<>();
  
  private WeakReference<View> mViewRef;
  
  public StateListAnimator() {
    initAnimatorListener();
  }
  
  private void cancel() {
    Animator animator = this.mRunningAnimator;
    if (animator != null) {
      animator.cancel();
      this.mRunningAnimator = null;
    } 
  }
  
  private void clearTarget() {
    int i = this.mTuples.size();
    for (byte b = 0; b < i; b++)
      ((Tuple)this.mTuples.get(b)).mAnimator.setTarget(null); 
    this.mViewRef = null;
    this.mLastMatch = null;
    this.mRunningAnimator = null;
  }
  
  private void initAnimatorListener() {
    this.mAnimatorListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator param1Animator) {
          param1Animator.setTarget(null);
          if (StateListAnimator.this.mRunningAnimator == param1Animator)
            StateListAnimator.access$002(StateListAnimator.this, null); 
        }
      };
  }
  
  private void start(Tuple paramTuple) {
    paramTuple.mAnimator.setTarget(getTarget());
    Animator animator = paramTuple.mAnimator;
    this.mRunningAnimator = animator;
    animator.start();
  }
  
  public void addState(int[] paramArrayOfint, Animator paramAnimator) {
    Tuple tuple = new Tuple(paramArrayOfint, paramAnimator);
    tuple.mAnimator.addListener(this.mAnimatorListener);
    this.mTuples.add(tuple);
    this.mChangingConfigurations |= paramAnimator.getChangingConfigurations();
  }
  
  public void appendChangingConfigurations(int paramInt) {
    this.mChangingConfigurations |= paramInt;
  }
  
  public StateListAnimator clone() {
    try {
      StateListAnimator stateListAnimator = (StateListAnimator)super.clone();
      ArrayList<Tuple> arrayList = new ArrayList();
      this(this.mTuples.size());
      stateListAnimator.mTuples = arrayList;
      stateListAnimator.mLastMatch = null;
      stateListAnimator.mRunningAnimator = null;
      stateListAnimator.mViewRef = null;
      stateListAnimator.mAnimatorListener = null;
      stateListAnimator.initAnimatorListener();
      int i = this.mTuples.size();
      for (byte b = 0; b < i; b++) {
        Tuple tuple = this.mTuples.get(b);
        Animator animator = tuple.mAnimator.clone();
        animator.removeListener(this.mAnimatorListener);
        stateListAnimator.addState(tuple.mSpecs, animator);
      } 
      stateListAnimator.setChangingConfigurations(getChangingConfigurations());
      return stateListAnimator;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      throw new AssertionError("cannot clone state list animator", cloneNotSupportedException);
    } 
  }
  
  public ConstantState<StateListAnimator> createConstantState() {
    return new StateListAnimatorConstantState(this);
  }
  
  public int getChangingConfigurations() {
    return this.mChangingConfigurations;
  }
  
  public Animator getRunningAnimator() {
    return this.mRunningAnimator;
  }
  
  public View getTarget() {
    View view;
    WeakReference<View> weakReference = this.mViewRef;
    if (weakReference == null) {
      weakReference = null;
    } else {
      view = weakReference.get();
    } 
    return view;
  }
  
  public ArrayList<Tuple> getTuples() {
    return this.mTuples;
  }
  
  public void jumpToCurrentState() {
    Animator animator = this.mRunningAnimator;
    if (animator != null)
      animator.end(); 
  }
  
  public void setChangingConfigurations(int paramInt) {
    this.mChangingConfigurations = paramInt;
  }
  
  public void setState(int[] paramArrayOfint) {
    Tuple tuple3;
    Tuple tuple2 = null;
    int i = this.mTuples.size();
    byte b = 0;
    while (true) {
      tuple3 = tuple2;
      if (b < i) {
        tuple3 = this.mTuples.get(b);
        if (StateSet.stateSetMatches(tuple3.mSpecs, paramArrayOfint))
          break; 
        b++;
        continue;
      } 
      break;
    } 
    Tuple tuple1 = this.mLastMatch;
    if (tuple3 == tuple1)
      return; 
    if (tuple1 != null)
      cancel(); 
    this.mLastMatch = tuple3;
    if (tuple3 != null)
      start(tuple3); 
  }
  
  public void setTarget(View paramView) {
    View view = getTarget();
    if (view == paramView)
      return; 
    if (view != null)
      clearTarget(); 
    if (paramView != null)
      this.mViewRef = new WeakReference<>(paramView); 
  }
  
  private static class StateListAnimatorConstantState extends ConstantState<StateListAnimator> {
    final StateListAnimator mAnimator;
    
    int mChangingConf;
    
    public StateListAnimatorConstantState(StateListAnimator param1StateListAnimator) {
      this.mAnimator = param1StateListAnimator;
      StateListAnimator.access$202(param1StateListAnimator, this);
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
  
  public static class Tuple {
    final Animator mAnimator;
    
    final int[] mSpecs;
    
    private Tuple(int[] param1ArrayOfint, Animator param1Animator) {
      this.mSpecs = param1ArrayOfint;
      this.mAnimator = param1Animator;
    }
    
    public Animator getAnimator() {
      return this.mAnimator;
    }
    
    public int[] getSpecs() {
      return this.mSpecs;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/StateListAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */