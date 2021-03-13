package android.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LayoutTransition {
  private static TimeInterpolator ACCEL_DECEL_INTERPOLATOR;
  
  public static final int APPEARING = 2;
  
  public static final int CHANGE_APPEARING = 0;
  
  public static final int CHANGE_DISAPPEARING = 1;
  
  public static final int CHANGING = 4;
  
  private static TimeInterpolator DECEL_INTERPOLATOR;
  
  private static long DEFAULT_DURATION = 300L;
  
  public static final int DISAPPEARING = 3;
  
  private static final int FLAG_APPEARING = 1;
  
  private static final int FLAG_CHANGE_APPEARING = 4;
  
  private static final int FLAG_CHANGE_DISAPPEARING = 8;
  
  private static final int FLAG_CHANGING = 16;
  
  private static final int FLAG_DISAPPEARING = 2;
  
  private static ObjectAnimator defaultChange;
  
  private static ObjectAnimator defaultChangeIn;
  
  private static ObjectAnimator defaultChangeOut;
  
  private static ObjectAnimator defaultFadeIn;
  
  private static ObjectAnimator defaultFadeOut;
  
  private static TimeInterpolator sAppearingInterpolator;
  
  private static TimeInterpolator sChangingAppearingInterpolator;
  
  private static TimeInterpolator sChangingDisappearingInterpolator;
  
  private static TimeInterpolator sChangingInterpolator;
  
  private static TimeInterpolator sDisappearingInterpolator;
  
  private final LinkedHashMap<View, Animator> currentAppearingAnimations;
  
  private final LinkedHashMap<View, Animator> currentChangingAnimations;
  
  private final LinkedHashMap<View, Animator> currentDisappearingAnimations;
  
  private final HashMap<View, View.OnLayoutChangeListener> layoutChangeListenerMap;
  
  private boolean mAnimateParentHierarchy;
  
  private Animator mAppearingAnim = null;
  
  private long mAppearingDelay;
  
  private long mAppearingDuration;
  
  private TimeInterpolator mAppearingInterpolator;
  
  private Animator mChangingAnim = null;
  
  private Animator mChangingAppearingAnim = null;
  
  private long mChangingAppearingDelay;
  
  private long mChangingAppearingDuration;
  
  private TimeInterpolator mChangingAppearingInterpolator;
  
  private long mChangingAppearingStagger;
  
  private long mChangingDelay;
  
  private Animator mChangingDisappearingAnim = null;
  
  private long mChangingDisappearingDelay;
  
  private long mChangingDisappearingDuration;
  
  private TimeInterpolator mChangingDisappearingInterpolator;
  
  private long mChangingDisappearingStagger;
  
  private long mChangingDuration;
  
  private TimeInterpolator mChangingInterpolator;
  
  private long mChangingStagger;
  
  private Animator mDisappearingAnim = null;
  
  private long mDisappearingDelay;
  
  private long mDisappearingDuration;
  
  private TimeInterpolator mDisappearingInterpolator;
  
  private ArrayList<TransitionListener> mListeners;
  
  private int mTransitionTypes;
  
  private final HashMap<View, Animator> pendingAnimations;
  
  private long staggerDelay;
  
  static {
    ACCEL_DECEL_INTERPOLATOR = (TimeInterpolator)new AccelerateDecelerateInterpolator();
    DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
    DECEL_INTERPOLATOR = (TimeInterpolator)decelerateInterpolator;
    TimeInterpolator timeInterpolator = ACCEL_DECEL_INTERPOLATOR;
    sAppearingInterpolator = timeInterpolator;
    sDisappearingInterpolator = timeInterpolator;
    sChangingAppearingInterpolator = (TimeInterpolator)decelerateInterpolator;
    sChangingDisappearingInterpolator = (TimeInterpolator)decelerateInterpolator;
    sChangingInterpolator = (TimeInterpolator)decelerateInterpolator;
  }
  
  public LayoutTransition() {
    long l = DEFAULT_DURATION;
    this.mChangingAppearingDuration = l;
    this.mChangingDisappearingDuration = l;
    this.mChangingDuration = l;
    this.mAppearingDuration = l;
    this.mDisappearingDuration = l;
    this.mAppearingDelay = l;
    this.mDisappearingDelay = 0L;
    this.mChangingAppearingDelay = 0L;
    this.mChangingDisappearingDelay = l;
    this.mChangingDelay = 0L;
    this.mChangingAppearingStagger = 0L;
    this.mChangingDisappearingStagger = 0L;
    this.mChangingStagger = 0L;
    this.mAppearingInterpolator = sAppearingInterpolator;
    this.mDisappearingInterpolator = sDisappearingInterpolator;
    this.mChangingAppearingInterpolator = sChangingAppearingInterpolator;
    this.mChangingDisappearingInterpolator = sChangingDisappearingInterpolator;
    this.mChangingInterpolator = sChangingInterpolator;
    this.pendingAnimations = new HashMap<>();
    this.currentChangingAnimations = new LinkedHashMap<>();
    this.currentAppearingAnimations = new LinkedHashMap<>();
    this.currentDisappearingAnimations = new LinkedHashMap<>();
    this.layoutChangeListenerMap = new HashMap<>();
    this.mTransitionTypes = 15;
    this.mAnimateParentHierarchy = true;
    if (defaultChangeIn == null) {
      ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder((Object)null, new PropertyValuesHolder[] { PropertyValuesHolder.ofInt("left", new int[] { 0, 1 }), PropertyValuesHolder.ofInt("top", new int[] { 0, 1 }), PropertyValuesHolder.ofInt("right", new int[] { 0, 1 }), PropertyValuesHolder.ofInt("bottom", new int[] { 0, 1 }), PropertyValuesHolder.ofInt("scrollX", new int[] { 0, 1 }), PropertyValuesHolder.ofInt("scrollY", new int[] { 0, 1 }) });
      defaultChangeIn = objectAnimator;
      objectAnimator.setDuration(DEFAULT_DURATION);
      defaultChangeIn.setStartDelay(this.mChangingAppearingDelay);
      defaultChangeIn.setInterpolator(this.mChangingAppearingInterpolator);
      objectAnimator = defaultChangeIn.clone();
      defaultChangeOut = objectAnimator;
      objectAnimator.setStartDelay(this.mChangingDisappearingDelay);
      defaultChangeOut.setInterpolator(this.mChangingDisappearingInterpolator);
      objectAnimator = defaultChangeIn.clone();
      defaultChange = objectAnimator;
      objectAnimator.setStartDelay(this.mChangingDelay);
      defaultChange.setInterpolator(this.mChangingInterpolator);
      objectAnimator = ObjectAnimator.ofFloat((Object)null, "alpha", new float[] { 0.0F, 1.0F });
      defaultFadeIn = objectAnimator;
      objectAnimator.setDuration(DEFAULT_DURATION);
      defaultFadeIn.setStartDelay(this.mAppearingDelay);
      defaultFadeIn.setInterpolator(this.mAppearingInterpolator);
      objectAnimator = ObjectAnimator.ofFloat((Object)null, "alpha", new float[] { 1.0F, 0.0F });
      defaultFadeOut = objectAnimator;
      objectAnimator.setDuration(DEFAULT_DURATION);
      defaultFadeOut.setStartDelay(this.mDisappearingDelay);
      defaultFadeOut.setInterpolator(this.mDisappearingInterpolator);
    } 
    this.mChangingAppearingAnim = defaultChangeIn;
    this.mChangingDisappearingAnim = defaultChangeOut;
    this.mChangingAnim = defaultChange;
    this.mAppearingAnim = defaultFadeIn;
    this.mDisappearingAnim = defaultFadeOut;
  }
  
  private void addChild(ViewGroup paramViewGroup, View paramView, boolean paramBoolean) {
    if (paramViewGroup.getWindowVisibility() != 0)
      return; 
    if ((this.mTransitionTypes & 0x1) == 1)
      cancel(3); 
    if (paramBoolean && (this.mTransitionTypes & 0x4) == 4) {
      cancel(0);
      cancel(4);
    } 
    if (hasListeners() && (this.mTransitionTypes & 0x1) == 1) {
      Iterator<TransitionListener> iterator = ((ArrayList)this.mListeners.clone()).iterator();
      while (iterator.hasNext())
        ((TransitionListener)iterator.next()).startTransition(this, paramViewGroup, paramView, 2); 
    } 
    if (paramBoolean && (this.mTransitionTypes & 0x4) == 4)
      runChangeTransition(paramViewGroup, paramView, 2); 
    if ((this.mTransitionTypes & 0x1) == 1)
      runAppearingTransition(paramViewGroup, paramView); 
  }
  
  private boolean hasListeners() {
    boolean bool;
    ArrayList<TransitionListener> arrayList = this.mListeners;
    if (arrayList != null && arrayList.size() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void removeChild(ViewGroup paramViewGroup, View paramView, boolean paramBoolean) {
    if (paramViewGroup.getWindowVisibility() != 0)
      return; 
    if ((this.mTransitionTypes & 0x2) == 2)
      cancel(2); 
    if (paramBoolean && (this.mTransitionTypes & 0x8) == 8) {
      cancel(1);
      cancel(4);
    } 
    if (hasListeners() && (this.mTransitionTypes & 0x2) == 2) {
      Iterator<TransitionListener> iterator = ((ArrayList)this.mListeners.clone()).iterator();
      while (iterator.hasNext())
        ((TransitionListener)iterator.next()).startTransition(this, paramViewGroup, paramView, 3); 
    } 
    if (paramBoolean && (this.mTransitionTypes & 0x8) == 8)
      runChangeTransition(paramViewGroup, paramView, 3); 
    if ((this.mTransitionTypes & 0x2) == 2)
      runDisappearingTransition(paramViewGroup, paramView); 
  }
  
  private void runAppearingTransition(final ViewGroup parent, final View child) {
    Iterator<TransitionListener> iterator;
    Animator animator2 = this.currentDisappearingAnimations.get(child);
    if (animator2 != null)
      animator2.cancel(); 
    animator2 = this.mAppearingAnim;
    if (animator2 == null) {
      if (hasListeners()) {
        iterator = ((ArrayList)this.mListeners.clone()).iterator();
        while (iterator.hasNext())
          ((TransitionListener)iterator.next()).endTransition(this, parent, child, 2); 
      } 
      return;
    } 
    Animator animator1 = iterator.clone();
    animator1.setTarget(child);
    animator1.setStartDelay(this.mAppearingDelay);
    animator1.setDuration(this.mAppearingDuration);
    TimeInterpolator timeInterpolator = this.mAppearingInterpolator;
    if (timeInterpolator != sAppearingInterpolator)
      animator1.setInterpolator(timeInterpolator); 
    if (animator1 instanceof ObjectAnimator)
      ((ObjectAnimator)animator1).setCurrentPlayTime(0L); 
    animator1.addListener(new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            LayoutTransition.this.currentAppearingAnimations.remove(child);
            if (LayoutTransition.this.hasListeners()) {
              Iterator<LayoutTransition.TransitionListener> iterator = ((ArrayList)LayoutTransition.this.mListeners.clone()).iterator();
              while (iterator.hasNext())
                ((LayoutTransition.TransitionListener)iterator.next()).endTransition(LayoutTransition.this, parent, child, 2); 
            } 
          }
        });
    this.currentAppearingAnimations.put(child, animator1);
    animator1.start();
  }
  
  private void runChangeTransition(ViewGroup paramViewGroup, View paramView, int paramInt) {
    Animator animator;
    ObjectAnimator objectAnimator;
    long l;
    if (paramInt != 2) {
      if (paramInt != 3) {
        if (paramInt != 4) {
          animator = null;
          objectAnimator = null;
          l = 0L;
        } else {
          animator = this.mChangingAnim;
          l = this.mChangingDuration;
          objectAnimator = defaultChange;
        } 
      } else {
        animator = this.mChangingDisappearingAnim;
        l = this.mChangingDisappearingDuration;
        objectAnimator = defaultChangeOut;
      } 
    } else {
      animator = this.mChangingAppearingAnim;
      l = this.mChangingAppearingDuration;
      objectAnimator = defaultChangeIn;
    } 
    if (animator == null)
      return; 
    this.staggerDelay = 0L;
    ViewTreeObserver viewTreeObserver = paramViewGroup.getViewTreeObserver();
    if (!viewTreeObserver.isAlive())
      return; 
    int i = paramViewGroup.getChildCount();
    for (byte b = 0; b < i; b++) {
      View view = paramViewGroup.getChildAt(b);
      if (view != paramView)
        setupChangeAnimation(paramViewGroup, paramInt, animator, l, view); 
    } 
    if (this.mAnimateParentHierarchy)
      for (ViewGroup viewGroup = paramViewGroup; viewGroup != null; viewGroup = null) {
        ViewParent viewParent = viewGroup.getParent();
        if (viewParent instanceof ViewGroup) {
          setupChangeAnimation((ViewGroup)viewParent, paramInt, objectAnimator, l, (View)viewGroup);
          viewGroup = (ViewGroup)viewParent;
          continue;
        } 
      }  
    CleanupCallback cleanupCallback = new CleanupCallback(this.layoutChangeListenerMap, paramViewGroup);
    viewTreeObserver.addOnPreDrawListener(cleanupCallback);
    paramViewGroup.addOnAttachStateChangeListener(cleanupCallback);
  }
  
  private void runDisappearingTransition(final ViewGroup parent, final View child) {
    Iterator<TransitionListener> iterator;
    Animator animator1 = this.currentAppearingAnimations.get(child);
    if (animator1 != null)
      animator1.cancel(); 
    animator1 = this.mDisappearingAnim;
    if (animator1 == null) {
      if (hasListeners()) {
        iterator = ((ArrayList)this.mListeners.clone()).iterator();
        while (iterator.hasNext())
          ((TransitionListener)iterator.next()).endTransition(this, parent, child, 3); 
      } 
      return;
    } 
    Animator animator2 = iterator.clone();
    animator2.setStartDelay(this.mDisappearingDelay);
    animator2.setDuration(this.mDisappearingDuration);
    TimeInterpolator timeInterpolator = this.mDisappearingInterpolator;
    if (timeInterpolator != sDisappearingInterpolator)
      animator2.setInterpolator(timeInterpolator); 
    animator2.setTarget(child);
    animator2.addListener(new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            LayoutTransition.this.currentDisappearingAnimations.remove(child);
            child.setAlpha(preAnimAlpha);
            if (LayoutTransition.this.hasListeners()) {
              Iterator<LayoutTransition.TransitionListener> iterator = ((ArrayList)LayoutTransition.this.mListeners.clone()).iterator();
              while (iterator.hasNext())
                ((LayoutTransition.TransitionListener)iterator.next()).endTransition(LayoutTransition.this, parent, child, 3); 
            } 
          }
        });
    if (animator2 instanceof ObjectAnimator)
      ((ObjectAnimator)animator2).setCurrentPlayTime(0L); 
    this.currentDisappearingAnimations.put(child, animator2);
    animator2.start();
  }
  
  private void setupChangeAnimation(final ViewGroup parent, final int changeReason, final Animator anim, final long duration, final View child) {
    if (this.layoutChangeListenerMap.get(child) != null)
      return; 
    if (child.getWidth() == 0 && child.getHeight() == 0)
      return; 
    anim = anim.clone();
    anim.setTarget(child);
    anim.setupStartValues();
    Animator animator = this.pendingAnimations.get(child);
    if (animator != null) {
      animator.cancel();
      this.pendingAnimations.remove(child);
    } 
    this.pendingAnimations.put(child, anim);
    animator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F }).setDuration(duration + 100L);
    animator.addListener(new AnimatorListenerAdapter() {
          public void onAnimationEnd(Animator param1Animator) {
            LayoutTransition.this.pendingAnimations.remove(child);
          }
        });
    animator.start();
    final View.OnLayoutChangeListener listener = new View.OnLayoutChangeListener() {
        public void onLayoutChange(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, int param1Int7, int param1Int8) {
          anim.setupEndValues();
          Animator animator = anim;
          if (animator instanceof ValueAnimator) {
            param1Int1 = 0;
            PropertyValuesHolder[] arrayOfPropertyValuesHolder = ((ValueAnimator)animator).getValues();
            for (param1Int2 = 0; param1Int2 < arrayOfPropertyValuesHolder.length; param1Int2++) {
              KeyframeSet keyframeSet;
              PropertyValuesHolder propertyValuesHolder = arrayOfPropertyValuesHolder[param1Int2];
              if (propertyValuesHolder.mKeyframes instanceof KeyframeSet) {
                keyframeSet = (KeyframeSet)propertyValuesHolder.mKeyframes;
                if (keyframeSet.mFirstKeyframe == null || keyframeSet.mLastKeyframe == null || !keyframeSet.mFirstKeyframe.getValue().equals(keyframeSet.mLastKeyframe.getValue()))
                  param1Int1 = 1; 
              } else if (!((PropertyValuesHolder)keyframeSet).mKeyframes.getValue(0.0F).equals(((PropertyValuesHolder)keyframeSet).mKeyframes.getValue(1.0F))) {
                param1Int1 = 1;
              } 
            } 
            if (param1Int1 == 0)
              return; 
          } 
          long l = 0L;
          param1Int1 = changeReason;
          if (param1Int1 != 2) {
            if (param1Int1 != 3) {
              if (param1Int1 == 4) {
                long l1 = LayoutTransition.this.mChangingDelay + LayoutTransition.this.staggerDelay;
                LayoutTransition layoutTransition = LayoutTransition.this;
                LayoutTransition.access$214(layoutTransition, layoutTransition.mChangingStagger);
                l = l1;
                if (LayoutTransition.this.mChangingInterpolator != LayoutTransition.sChangingInterpolator) {
                  anim.setInterpolator(LayoutTransition.this.mChangingInterpolator);
                  l = l1;
                } 
              } 
            } else {
              long l1 = LayoutTransition.this.mChangingDisappearingDelay + LayoutTransition.this.staggerDelay;
              LayoutTransition layoutTransition = LayoutTransition.this;
              LayoutTransition.access$214(layoutTransition, layoutTransition.mChangingDisappearingStagger);
              l = l1;
              if (LayoutTransition.this.mChangingDisappearingInterpolator != LayoutTransition.sChangingDisappearingInterpolator) {
                anim.setInterpolator(LayoutTransition.this.mChangingDisappearingInterpolator);
                l = l1;
              } 
            } 
          } else {
            long l1 = LayoutTransition.this.mChangingAppearingDelay + LayoutTransition.this.staggerDelay;
            LayoutTransition layoutTransition = LayoutTransition.this;
            LayoutTransition.access$214(layoutTransition, layoutTransition.mChangingAppearingStagger);
            l = l1;
            if (LayoutTransition.this.mChangingAppearingInterpolator != LayoutTransition.sChangingAppearingInterpolator) {
              anim.setInterpolator(LayoutTransition.this.mChangingAppearingInterpolator);
              l = l1;
            } 
          } 
          anim.setStartDelay(l);
          anim.setDuration(duration);
          animator = (Animator)LayoutTransition.this.currentChangingAnimations.get(child);
          if (animator != null)
            animator.cancel(); 
          if ((Animator)LayoutTransition.this.pendingAnimations.get(child) != null)
            LayoutTransition.this.pendingAnimations.remove(child); 
          LayoutTransition.this.currentChangingAnimations.put(child, anim);
          parent.requestTransitionStart(LayoutTransition.this);
          child.removeOnLayoutChangeListener(this);
          LayoutTransition.this.layoutChangeListenerMap.remove(child);
        }
      };
    anim.addListener(new AnimatorListenerAdapter() {
          public void onAnimationCancel(Animator param1Animator) {
            child.removeOnLayoutChangeListener(listener);
            LayoutTransition.this.layoutChangeListenerMap.remove(child);
          }
          
          public void onAnimationEnd(Animator param1Animator) {
            LayoutTransition.this.currentChangingAnimations.remove(child);
            if (LayoutTransition.this.hasListeners())
              for (LayoutTransition.TransitionListener transitionListener : LayoutTransition.this.mListeners.clone()) {
                LayoutTransition layoutTransition = LayoutTransition.this;
                ViewGroup viewGroup = parent;
                View view = child;
                int i = changeReason;
                if (i == 2) {
                  i = 0;
                } else if (i == 3) {
                  i = 1;
                } else {
                  i = 4;
                } 
                transitionListener.endTransition(layoutTransition, viewGroup, view, i);
              }  
          }
          
          public void onAnimationStart(Animator param1Animator) {
            if (LayoutTransition.this.hasListeners())
              for (LayoutTransition.TransitionListener transitionListener : LayoutTransition.this.mListeners.clone()) {
                LayoutTransition layoutTransition = LayoutTransition.this;
                ViewGroup viewGroup = parent;
                View view = child;
                int i = changeReason;
                if (i == 2) {
                  i = 0;
                } else if (i == 3) {
                  i = 1;
                } else {
                  i = 4;
                } 
                transitionListener.startTransition(layoutTransition, viewGroup, view, i);
              }  
          }
        });
    child.addOnLayoutChangeListener(onLayoutChangeListener);
    this.layoutChangeListenerMap.put(child, onLayoutChangeListener);
  }
  
  public void addChild(ViewGroup paramViewGroup, View paramView) {
    addChild(paramViewGroup, paramView, true);
  }
  
  public void addTransitionListener(TransitionListener paramTransitionListener) {
    if (this.mListeners == null)
      this.mListeners = new ArrayList<>(); 
    this.mListeners.add(paramTransitionListener);
  }
  
  public void cancel() {
    if (this.currentChangingAnimations.size() > 0) {
      Iterator<Animator> iterator = ((LinkedHashMap)this.currentChangingAnimations.clone()).values().iterator();
      while (iterator.hasNext())
        ((Animator)iterator.next()).cancel(); 
      this.currentChangingAnimations.clear();
    } 
    if (this.currentAppearingAnimations.size() > 0) {
      Iterator<Animator> iterator = ((LinkedHashMap)this.currentAppearingAnimations.clone()).values().iterator();
      while (iterator.hasNext())
        ((Animator)iterator.next()).end(); 
      this.currentAppearingAnimations.clear();
    } 
    if (this.currentDisappearingAnimations.size() > 0) {
      Iterator<Animator> iterator = ((LinkedHashMap)this.currentDisappearingAnimations.clone()).values().iterator();
      while (iterator.hasNext())
        ((Animator)iterator.next()).end(); 
      this.currentDisappearingAnimations.clear();
    } 
  }
  
  public void cancel(int paramInt) {
    if (paramInt != 0 && paramInt != 1)
      if (paramInt != 2) {
        if (paramInt != 3) {
          if (paramInt != 4)
            return; 
        } else {
          if (this.currentDisappearingAnimations.size() > 0) {
            Iterator<Animator> iterator = ((LinkedHashMap)this.currentDisappearingAnimations.clone()).values().iterator();
            while (iterator.hasNext())
              ((Animator)iterator.next()).end(); 
            this.currentDisappearingAnimations.clear();
          } 
          return;
        } 
      } else {
        if (this.currentAppearingAnimations.size() > 0) {
          Iterator<Animator> iterator = ((LinkedHashMap)this.currentAppearingAnimations.clone()).values().iterator();
          while (iterator.hasNext())
            ((Animator)iterator.next()).end(); 
          this.currentAppearingAnimations.clear();
        } 
        return;
      }  
    if (this.currentChangingAnimations.size() > 0) {
      Iterator<Animator> iterator = ((LinkedHashMap)this.currentChangingAnimations.clone()).values().iterator();
      while (iterator.hasNext())
        ((Animator)iterator.next()).cancel(); 
      this.currentChangingAnimations.clear();
    } 
  }
  
  public void disableTransitionType(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 4)
              this.mTransitionTypes &= 0xFFFFFFEF; 
          } else {
            this.mTransitionTypes &= 0xFFFFFFFD;
          } 
        } else {
          this.mTransitionTypes &= 0xFFFFFFFE;
        } 
      } else {
        this.mTransitionTypes &= 0xFFFFFFF7;
      } 
    } else {
      this.mTransitionTypes &= 0xFFFFFFFB;
    } 
  }
  
  public void enableTransitionType(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 4)
              this.mTransitionTypes |= 0x10; 
          } else {
            this.mTransitionTypes |= 0x2;
          } 
        } else {
          this.mTransitionTypes |= 0x1;
        } 
      } else {
        this.mTransitionTypes |= 0x8;
      } 
    } else {
      this.mTransitionTypes = 0x4 | this.mTransitionTypes;
    } 
  }
  
  public void endChangingAnimations() {
    for (Animator animator : ((LinkedHashMap)this.currentChangingAnimations.clone()).values()) {
      animator.start();
      animator.end();
    } 
    this.currentChangingAnimations.clear();
  }
  
  public Animator getAnimator(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? null : this.mChangingAnim) : this.mDisappearingAnim) : this.mAppearingAnim) : this.mChangingDisappearingAnim) : this.mChangingAppearingAnim;
  }
  
  public long getDuration(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? 0L : this.mChangingDuration) : this.mDisappearingDuration) : this.mAppearingDuration) : this.mChangingDisappearingDuration) : this.mChangingAppearingDuration;
  }
  
  public TimeInterpolator getInterpolator(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? null : this.mChangingInterpolator) : this.mDisappearingInterpolator) : this.mAppearingInterpolator) : this.mChangingDisappearingInterpolator) : this.mChangingAppearingInterpolator;
  }
  
  public long getStagger(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 4) ? 0L : this.mChangingStagger) : this.mChangingDisappearingStagger) : this.mChangingAppearingStagger;
  }
  
  public long getStartDelay(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? 0L : this.mChangingDelay) : this.mDisappearingDelay) : this.mAppearingDelay) : this.mChangingDisappearingDelay) : this.mChangingAppearingDelay;
  }
  
  public List<TransitionListener> getTransitionListeners() {
    return this.mListeners;
  }
  
  @Deprecated
  public void hideChild(ViewGroup paramViewGroup, View paramView) {
    removeChild(paramViewGroup, paramView, true);
  }
  
  public void hideChild(ViewGroup paramViewGroup, View paramView, int paramInt) {
    boolean bool;
    if (paramInt == 8) {
      bool = true;
    } else {
      bool = false;
    } 
    removeChild(paramViewGroup, paramView, bool);
  }
  
  public boolean isChangingLayout() {
    boolean bool;
    if (this.currentChangingAnimations.size() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isRunning() {
    return (this.currentChangingAnimations.size() > 0 || this.currentAppearingAnimations.size() > 0 || this.currentDisappearingAnimations.size() > 0);
  }
  
  public boolean isTransitionTypeEnabled(int paramInt) {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt != 4)
              return false; 
            if ((this.mTransitionTypes & 0x10) == 16)
              bool5 = true; 
            return bool5;
          } 
          bool5 = bool1;
          if ((this.mTransitionTypes & 0x2) == 2)
            bool5 = true; 
          return bool5;
        } 
        bool5 = bool2;
        if ((this.mTransitionTypes & 0x1) == 1)
          bool5 = true; 
        return bool5;
      } 
      bool5 = bool3;
      if ((this.mTransitionTypes & 0x8) == 8)
        bool5 = true; 
      return bool5;
    } 
    bool5 = bool4;
    if ((this.mTransitionTypes & 0x4) == 4)
      bool5 = true; 
    return bool5;
  }
  
  public void layoutChange(ViewGroup paramViewGroup) {
    if (paramViewGroup.getWindowVisibility() != 0)
      return; 
    if ((this.mTransitionTypes & 0x10) == 16 && !isRunning())
      runChangeTransition(paramViewGroup, null, 4); 
  }
  
  public void removeChild(ViewGroup paramViewGroup, View paramView) {
    removeChild(paramViewGroup, paramView, true);
  }
  
  public void removeTransitionListener(TransitionListener paramTransitionListener) {
    ArrayList<TransitionListener> arrayList = this.mListeners;
    if (arrayList == null)
      return; 
    arrayList.remove(paramTransitionListener);
  }
  
  public void setAnimateParentHierarchy(boolean paramBoolean) {
    this.mAnimateParentHierarchy = paramBoolean;
  }
  
  public void setAnimator(int paramInt, Animator paramAnimator) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 4)
              this.mChangingAnim = paramAnimator; 
          } else {
            this.mDisappearingAnim = paramAnimator;
          } 
        } else {
          this.mAppearingAnim = paramAnimator;
        } 
      } else {
        this.mChangingDisappearingAnim = paramAnimator;
      } 
    } else {
      this.mChangingAppearingAnim = paramAnimator;
    } 
  }
  
  public void setDuration(int paramInt, long paramLong) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 4)
              this.mChangingDuration = paramLong; 
          } else {
            this.mDisappearingDuration = paramLong;
          } 
        } else {
          this.mAppearingDuration = paramLong;
        } 
      } else {
        this.mChangingDisappearingDuration = paramLong;
      } 
    } else {
      this.mChangingAppearingDuration = paramLong;
    } 
  }
  
  public void setDuration(long paramLong) {
    this.mChangingAppearingDuration = paramLong;
    this.mChangingDisappearingDuration = paramLong;
    this.mChangingDuration = paramLong;
    this.mAppearingDuration = paramLong;
    this.mDisappearingDuration = paramLong;
  }
  
  public void setInterpolator(int paramInt, TimeInterpolator paramTimeInterpolator) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 4)
              this.mChangingInterpolator = paramTimeInterpolator; 
          } else {
            this.mDisappearingInterpolator = paramTimeInterpolator;
          } 
        } else {
          this.mAppearingInterpolator = paramTimeInterpolator;
        } 
      } else {
        this.mChangingDisappearingInterpolator = paramTimeInterpolator;
      } 
    } else {
      this.mChangingAppearingInterpolator = paramTimeInterpolator;
    } 
  }
  
  public void setStagger(int paramInt, long paramLong) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt == 4)
          this.mChangingStagger = paramLong; 
      } else {
        this.mChangingDisappearingStagger = paramLong;
      } 
    } else {
      this.mChangingAppearingStagger = paramLong;
    } 
  }
  
  public void setStartDelay(int paramInt, long paramLong) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 4)
              this.mChangingDelay = paramLong; 
          } else {
            this.mDisappearingDelay = paramLong;
          } 
        } else {
          this.mAppearingDelay = paramLong;
        } 
      } else {
        this.mChangingDisappearingDelay = paramLong;
      } 
    } else {
      this.mChangingAppearingDelay = paramLong;
    } 
  }
  
  @Deprecated
  public void showChild(ViewGroup paramViewGroup, View paramView) {
    addChild(paramViewGroup, paramView, true);
  }
  
  public void showChild(ViewGroup paramViewGroup, View paramView, int paramInt) {
    boolean bool;
    if (paramInt == 8) {
      bool = true;
    } else {
      bool = false;
    } 
    addChild(paramViewGroup, paramView, bool);
  }
  
  public void startChangingAnimations() {
    for (Animator animator : ((LinkedHashMap)this.currentChangingAnimations.clone()).values()) {
      if (animator instanceof ObjectAnimator)
        ((ObjectAnimator)animator).setCurrentPlayTime(0L); 
      animator.start();
    } 
  }
  
  private static final class CleanupCallback implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    final Map<View, View.OnLayoutChangeListener> layoutChangeListenerMap;
    
    final ViewGroup parent;
    
    CleanupCallback(Map<View, View.OnLayoutChangeListener> param1Map, ViewGroup param1ViewGroup) {
      this.layoutChangeListenerMap = param1Map;
      this.parent = param1ViewGroup;
    }
    
    private void cleanup() {
      this.parent.getViewTreeObserver().removeOnPreDrawListener(this);
      this.parent.removeOnAttachStateChangeListener(this);
      if (this.layoutChangeListenerMap.size() > 0) {
        for (View view : this.layoutChangeListenerMap.keySet())
          view.removeOnLayoutChangeListener(this.layoutChangeListenerMap.get(view)); 
        this.layoutChangeListenerMap.clear();
      } 
    }
    
    public boolean onPreDraw() {
      cleanup();
      return true;
    }
    
    public void onViewAttachedToWindow(View param1View) {}
    
    public void onViewDetachedFromWindow(View param1View) {
      cleanup();
    }
  }
  
  public static interface TransitionListener {
    void endTransition(LayoutTransition param1LayoutTransition, ViewGroup param1ViewGroup, View param1View, int param1Int);
    
    void startTransition(LayoutTransition param1LayoutTransition, ViewGroup param1ViewGroup, View param1View, int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/LayoutTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */