package android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewTreeObserver;
import android.view.Window;
import com.android.internal.view.OneShotPreDrawListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class EnterTransitionCoordinator extends ActivityTransitionCoordinator {
  private static final int MIN_ANIMATION_FRAMES = 2;
  
  private static final String TAG = "EnterTransitionCoordinator";
  
  private Activity mActivity;
  
  private boolean mAreViewsReady;
  
  private ObjectAnimator mBackgroundAnimator;
  
  private Transition mEnterViewsTransition;
  
  private boolean mHasStopped;
  
  private boolean mIsCanceled;
  
  private final boolean mIsCrossTask;
  
  private boolean mIsExitTransitionComplete;
  
  private boolean mIsReadyForTransition;
  
  private boolean mIsViewsTransitionStarted;
  
  private Runnable mOnTransitionComplete;
  
  private ArrayList<String> mPendingExitNames;
  
  private Drawable mReplacedBackground;
  
  private boolean mSharedElementTransitionStarted;
  
  private Bundle mSharedElementsBundle;
  
  private OneShotPreDrawListener mViewsReadyListener;
  
  private boolean mWasOpaque;
  
  EnterTransitionCoordinator(Activity paramActivity, ResultReceiver paramResultReceiver, ArrayList<String> paramArrayList, boolean paramBoolean1, boolean paramBoolean2) {
    super(window, paramArrayList, getListener(paramActivity, bool), paramBoolean1);
    boolean bool;
    this.mActivity = paramActivity;
    this.mIsCrossTask = paramBoolean2;
    setResultReceiver(paramResultReceiver);
    prepareEnter();
    Bundle bundle = new Bundle();
    bundle.putParcelable("android:remoteReceiver", (Parcelable)this);
    this.mResultReceiver.send(100, bundle);
    final ViewGroup decorView = getDecor();
    if (viewGroup != null) {
      final ViewTreeObserver viewTreeObserver = viewGroup.getViewTreeObserver();
      viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
              if (EnterTransitionCoordinator.this.mIsReadyForTransition)
                if (viewTreeObserver.isAlive()) {
                  viewTreeObserver.removeOnPreDrawListener(this);
                } else {
                  decorView.getViewTreeObserver().removeOnPreDrawListener(this);
                }  
              return false;
            }
          });
    } 
  }
  
  private boolean allowOverlappingTransitions() {
    boolean bool;
    if (this.mIsReturning) {
      bool = getWindow().getAllowReturnTransitionOverlap();
    } else {
      bool = getWindow().getAllowEnterTransitionOverlap();
    } 
    return bool;
  }
  
  private Transition beginTransition(ViewGroup paramViewGroup, boolean paramBoolean1, boolean paramBoolean2) {
    Transition transition1 = null;
    Transition transition2 = null;
    if (paramBoolean2) {
      if (!this.mSharedElementNames.isEmpty())
        transition2 = configureTransition(getSharedElementTransition(), false); 
      if (transition2 == null) {
        sharedElementTransitionStarted();
        sharedElementTransitionComplete();
        transition1 = transition2;
      } else {
        transition2.addListener((Transition.TransitionListener)new TransitionListenerAdapter() {
              public void onTransitionEnd(Transition param1Transition) {
                param1Transition.removeListener((Transition.TransitionListener)this);
                EnterTransitionCoordinator.this.sharedElementTransitionComplete();
              }
              
              public void onTransitionStart(Transition param1Transition) {
                EnterTransitionCoordinator.this.sharedElementTransitionStarted();
              }
            });
        transition1 = transition2;
      } 
    } 
    transition2 = null;
    Transition transition3 = null;
    if (paramBoolean1) {
      this.mIsViewsTransitionStarted = true;
      transition2 = transition3;
      if (this.mTransitioningViews != null) {
        transition2 = transition3;
        if (!this.mTransitioningViews.isEmpty())
          transition2 = configureTransition(getViewsTransition(), true); 
      } 
      if (transition2 == null) {
        viewsTransitionComplete();
      } else {
        transition2.addListener((Transition.TransitionListener)new ActivityTransitionCoordinator.ContinueTransitionListener() {
              public void onTransitionEnd(Transition param1Transition) {
                EnterTransitionCoordinator.access$302(EnterTransitionCoordinator.this, (Transition)null);
                param1Transition.removeListener((Transition.TransitionListener)this);
                EnterTransitionCoordinator.this.viewsTransitionComplete();
                super.onTransitionEnd(param1Transition);
              }
              
              public void onTransitionStart(Transition param1Transition) {
                EnterTransitionCoordinator.access$302(EnterTransitionCoordinator.this, param1Transition);
                ArrayList<View> arrayList = transitioningViews;
                if (arrayList != null)
                  EnterTransitionCoordinator.this.showViews(arrayList, false); 
                super.onTransitionStart(param1Transition);
              }
            });
      } 
    } 
    transition2 = mergeTransitions(transition1, transition2);
    if (transition2 != null) {
      transition2.addListener((Transition.TransitionListener)new ActivityTransitionCoordinator.ContinueTransitionListener(this));
      if (paramBoolean1)
        setTransitioningViewsVisiblity(4, false); 
      TransitionManager.beginDelayedTransition(paramViewGroup, transition2);
      if (paramBoolean1)
        setTransitioningViewsVisiblity(0, false); 
      paramViewGroup.invalidate();
    } else {
      transitionStarted();
    } 
    return transition2;
  }
  
  private void cancel() {
    if (!this.mIsCanceled) {
      this.mIsCanceled = true;
      if (getViewsTransition() == null || this.mIsViewsTransitionStarted) {
        showViews(this.mSharedElements, true);
      } else if (this.mTransitioningViews != null) {
        this.mTransitioningViews.addAll(this.mSharedElements);
      } 
      moveSharedElementsFromOverlay();
      this.mSharedElementNames.clear();
      this.mSharedElements.clear();
      this.mAllSharedElementNames.clear();
      startSharedElementTransition((Bundle)null);
      onRemoteExitTransitionComplete();
    } 
  }
  
  private static SharedElementCallback getListener(Activity paramActivity, boolean paramBoolean) {
    SharedElementCallback sharedElementCallback;
    if (paramBoolean) {
      sharedElementCallback = paramActivity.mExitTransitionListener;
    } else {
      sharedElementCallback = ((Activity)sharedElementCallback).mEnterTransitionListener;
    } 
    return sharedElementCallback;
  }
  
  private void makeOpaque() {
    if (!this.mHasStopped) {
      Activity activity = this.mActivity;
      if (activity != null) {
        if (this.mWasOpaque)
          activity.convertFromTranslucent(); 
        this.mActivity = null;
      } 
    } 
  }
  
  private ArrayMap<String, View> mapNamedElements(ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2) {
    ArrayMap<String, View> arrayMap = new ArrayMap();
    ViewGroup viewGroup = getDecor();
    if (viewGroup != null)
      viewGroup.findNamedViews((Map)arrayMap); 
    if (paramArrayList1 != null)
      for (byte b = 0; b < paramArrayList2.size(); b++) {
        String str2 = paramArrayList2.get(b);
        String str1 = paramArrayList1.get(b);
        if (str2 != null && !str2.equals(str1)) {
          View view = (View)arrayMap.get(str2);
          if (view != null)
            arrayMap.put(str1, view); 
        } 
      }  
    return arrayMap;
  }
  
  private void onTakeSharedElements() {
    if (!this.mIsReadyForTransition || this.mSharedElementsBundle == null)
      return; 
    final Bundle sharedElementState = this.mSharedElementsBundle;
    this.mSharedElementsBundle = null;
    SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener = new SharedElementCallback.OnSharedElementsReadyListener() {
        public void onSharedElementsReady() {
          ViewGroup viewGroup = EnterTransitionCoordinator.this.getDecor();
          if (viewGroup != null) {
            OneShotPreDrawListener.add((View)viewGroup, false, new _$$Lambda$EnterTransitionCoordinator$3$I_t9rJUkrW7bwRLQtTrE8DgvPZs(this, sharedElementState));
            viewGroup.invalidate();
          } 
        }
      };
    if (this.mListener == null) {
      onSharedElementsReadyListener.onSharedElementsReady();
    } else {
      this.mListener.onSharedElementsArrived(this.mSharedElementNames, this.mSharedElements, onSharedElementsReadyListener);
    } 
  }
  
  private static void removeNullViews(ArrayList<View> paramArrayList) {
    if (paramArrayList != null)
      for (int i = paramArrayList.size() - 1; i >= 0; i--) {
        if (paramArrayList.get(i) == null)
          paramArrayList.remove(i); 
      }  
  }
  
  private void requestLayoutForSharedElements() {
    int i = this.mSharedElements.size();
    for (byte b = 0; b < i; b++)
      ((View)this.mSharedElements.get(b)).requestLayout(); 
  }
  
  private void sendSharedElementDestination() {
    Bundle bundle;
    int i;
    ViewGroup viewGroup = getDecor();
    if (allowOverlappingTransitions() && getEnterViewsTransition() != null) {
      i = 0;
    } else if (viewGroup == null) {
      i = 1;
    } else {
      int j = viewGroup.isLayoutRequested() ^ true;
      i = j;
      if (j != 0) {
        byte b = 0;
        while (true) {
          i = j;
          if (b < this.mSharedElements.size()) {
            if (((View)this.mSharedElements.get(b)).isLayoutRequested()) {
              i = 0;
              break;
            } 
            b++;
            continue;
          } 
          break;
        } 
      } 
    } 
    if (i != 0) {
      bundle = captureSharedElementState();
      moveSharedElementsToOverlay();
      this.mResultReceiver.send(107, bundle);
    } else if (bundle != null) {
      OneShotPreDrawListener.add((View)bundle, new _$$Lambda$EnterTransitionCoordinator$dV8bqDBqB_WsCnMyvajWuP4ArwA(this));
    } 
    if (allowOverlappingTransitions())
      startEnterTransitionOnly(); 
  }
  
  private void sharedElementTransitionStarted() {
    this.mSharedElementTransitionStarted = true;
    if (this.mIsExitTransitionComplete)
      send(104, null); 
  }
  
  private void startEnterTransition(Transition paramTransition) {
    ViewGroup viewGroup = getDecor();
    if (!this.mIsReturning && viewGroup != null) {
      ObjectAnimator objectAnimator;
      Drawable drawable = viewGroup.getBackground();
      if (drawable != null) {
        Drawable drawable1 = drawable.mutate();
        getWindow().setBackgroundDrawable(drawable1);
        objectAnimator = ObjectAnimator.ofInt(drawable1, "alpha", new int[] { 255 });
        this.mBackgroundAnimator = objectAnimator;
        objectAnimator.setDuration(getFadeDuration());
        this.mBackgroundAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
              public void onAnimationEnd(Animator param1Animator) {
                EnterTransitionCoordinator.this.makeOpaque();
                EnterTransitionCoordinator.this.backgroundAnimatorComplete();
              }
            });
        this.mBackgroundAnimator.start();
      } else if (objectAnimator != null) {
        objectAnimator.addListener((Transition.TransitionListener)new TransitionListenerAdapter() {
              public void onTransitionEnd(Transition param1Transition) {
                param1Transition.removeListener((Transition.TransitionListener)this);
                EnterTransitionCoordinator.this.makeOpaque();
              }
            });
        backgroundAnimatorComplete();
      } else {
        makeOpaque();
        backgroundAnimatorComplete();
      } 
    } else {
      backgroundAnimatorComplete();
    } 
  }
  
  private void startEnterTransitionOnly() {
    startTransition(new Runnable() {
          public void run() {
            ViewGroup viewGroup = EnterTransitionCoordinator.this.getDecor();
            if (viewGroup != null) {
              Transition transition = EnterTransitionCoordinator.this.beginTransition(viewGroup, true, false);
              EnterTransitionCoordinator.this.startEnterTransition(transition);
            } 
          }
        });
  }
  
  private void startRejectedAnimations(final ArrayList<View> rejectedSnapshots) {
    if (rejectedSnapshots == null || rejectedSnapshots.isEmpty())
      return; 
    final ViewGroup decorView = getDecor();
    if (viewGroup != null) {
      ObjectAnimator objectAnimator;
      ViewGroupOverlay viewGroupOverlay = viewGroup.getOverlay();
      View view = null;
      int i = rejectedSnapshots.size();
      for (byte b = 0; b < i; b++) {
        view = rejectedSnapshots.get(b);
        viewGroupOverlay.add(view);
        objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, new float[] { 1.0F, 0.0F });
        objectAnimator.start();
      } 
      objectAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator param1Animator) {
              ViewGroupOverlay viewGroupOverlay = decorView.getOverlay();
              int i = rejectedSnapshots.size();
              for (byte b = 0; b < i; b++)
                viewGroupOverlay.remove(rejectedSnapshots.get(b)); 
            }
          });
    } 
  }
  
  private void startSharedElementTransition(Bundle paramBundle) {
    ViewGroup viewGroup = getDecor();
    if (viewGroup == null)
      return; 
    ArrayList<String> arrayList = new ArrayList<>(this.mAllSharedElementNames);
    arrayList.removeAll(this.mSharedElementNames);
    arrayList = (ArrayList)createSnapshots(paramBundle, arrayList);
    if (this.mListener != null)
      this.mListener.onRejectSharedElements((List)arrayList); 
    removeNullViews((ArrayList)arrayList);
    startRejectedAnimations((ArrayList)arrayList);
    ArrayList<View> arrayList2 = createSnapshots(paramBundle, this.mSharedElementNames);
    ArrayList<View> arrayList1 = this.mSharedElements;
    boolean bool = true;
    showViews(arrayList1, true);
    scheduleSetSharedElementEnd(arrayList2);
    arrayList1 = (ArrayList)setSharedElementState(paramBundle, arrayList2);
    requestLayoutForSharedElements();
    if (!allowOverlappingTransitions() || this.mIsReturning)
      bool = false; 
    setGhostVisibility(4);
    scheduleGhostVisibilityChange(4);
    pauseInput();
    Transition transition = beginTransition(viewGroup, bool, true);
    scheduleGhostVisibilityChange(0);
    setGhostVisibility(0);
    if (bool)
      startEnterTransition(transition); 
    setOriginalSharedElementState(this.mSharedElements, (ArrayList)arrayList1);
    if (this.mResultReceiver != null)
      viewGroup.postOnAnimation(new Runnable() {
            int mAnimations;
            
            public void run() {
              int i = this.mAnimations;
              this.mAnimations = i + 1;
              if (i < 2) {
                ViewGroup viewGroup = EnterTransitionCoordinator.this.getDecor();
                if (viewGroup != null)
                  viewGroup.postOnAnimation(this); 
              } else if (EnterTransitionCoordinator.this.mResultReceiver != null) {
                EnterTransitionCoordinator.this.mResultReceiver.send(101, null);
                EnterTransitionCoordinator.this.mResultReceiver = null;
              } 
            }
          }); 
  }
  
  private void triggerViewsReady(ArrayMap<String, View> paramArrayMap) {
    if (this.mAreViewsReady)
      return; 
    this.mAreViewsReady = true;
    ViewGroup viewGroup = getDecor();
    if (viewGroup == null || (viewGroup.isAttachedToWindow() && (paramArrayMap.isEmpty() || !((View)paramArrayMap.valueAt(0)).isLayoutRequested()))) {
      viewsReady(paramArrayMap);
      return;
    } 
    this.mViewsReadyListener = OneShotPreDrawListener.add((View)viewGroup, new _$$Lambda$EnterTransitionCoordinator$wYWFlx9zS3bxJYkN44Bpwx_EKis(this, paramArrayMap));
    viewGroup.invalidate();
  }
  
  public boolean cancelEnter() {
    setGhostVisibility(4);
    this.mHasStopped = true;
    this.mIsCanceled = true;
    clearState();
    return cancelPendingTransitions();
  }
  
  protected void clearState() {
    this.mSharedElementsBundle = null;
    this.mEnterViewsTransition = null;
    this.mResultReceiver = null;
    ObjectAnimator objectAnimator = this.mBackgroundAnimator;
    if (objectAnimator != null) {
      objectAnimator.cancel();
      this.mBackgroundAnimator = null;
    } 
    Runnable runnable = this.mOnTransitionComplete;
    if (runnable != null) {
      runnable.run();
      this.mOnTransitionComplete = null;
    } 
    super.clearState();
  }
  
  public void forceViewsToAppear() {
    if (!this.mIsReturning)
      return; 
    if (!this.mIsReadyForTransition) {
      this.mIsReadyForTransition = true;
      if (getDecor() != null) {
        OneShotPreDrawListener oneShotPreDrawListener = this.mViewsReadyListener;
        if (oneShotPreDrawListener != null) {
          oneShotPreDrawListener.removeListener();
          this.mViewsReadyListener = null;
        } 
      } 
      showViews(this.mTransitioningViews, true);
      setTransitioningViewsVisiblity(0, true);
      this.mSharedElements.clear();
      this.mAllSharedElementNames.clear();
      this.mTransitioningViews.clear();
      this.mIsReadyForTransition = true;
      viewsTransitionComplete();
      sharedElementTransitionComplete();
    } else {
      if (!this.mSharedElementTransitionStarted) {
        moveSharedElementsFromOverlay();
        this.mSharedElementTransitionStarted = true;
        showViews(this.mSharedElements, true);
        this.mSharedElements.clear();
        sharedElementTransitionComplete();
      } 
      if (!this.mIsViewsTransitionStarted) {
        this.mIsViewsTransitionStarted = true;
        showViews(this.mTransitioningViews, true);
        setTransitioningViewsVisiblity(0, true);
        this.mTransitioningViews.clear();
        viewsTransitionComplete();
      } 
      cancelPendingTransitions();
    } 
    this.mAreViewsReady = true;
    if (this.mResultReceiver != null) {
      this.mResultReceiver.send(106, null);
      this.mResultReceiver = null;
    } 
  }
  
  public Transition getEnterViewsTransition() {
    return this.mEnterViewsTransition;
  }
  
  public ArrayList<String> getPendingExitSharedElementNames() {
    return this.mPendingExitNames;
  }
  
  protected Transition getSharedElementTransition() {
    Window window = getWindow();
    return (window == null) ? null : (this.mIsReturning ? window.getSharedElementReenterTransition() : window.getSharedElementEnterTransition());
  }
  
  protected Transition getViewsTransition() {
    Window window = getWindow();
    return (window == null) ? null : (this.mIsReturning ? window.getReenterTransition() : window.getEnterTransition());
  }
  
  boolean isCrossTask() {
    return this.mIsCrossTask;
  }
  
  public boolean isReturning() {
    return this.mIsReturning;
  }
  
  public boolean isWaitingForRemoteExit() {
    boolean bool;
    if (this.mIsReturning && this.mResultReceiver != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void namedViewsReady(ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2) {
    triggerViewsReady(mapNamedElements(paramArrayList1, paramArrayList2));
  }
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle) {
    if (paramInt != 103) {
      if (paramInt != 104) {
        if (paramInt != 106) {
          if (paramInt == 108 && !this.mIsCanceled)
            this.mPendingExitNames = this.mAllSharedElementNames; 
        } else {
          cancel();
        } 
      } else if (!this.mIsCanceled) {
        this.mIsExitTransitionComplete = true;
        if (this.mSharedElementTransitionStarted)
          onRemoteExitTransitionComplete(); 
      } 
    } else if (!this.mIsCanceled) {
      this.mSharedElementsBundle = paramBundle;
      onTakeSharedElements();
    } 
  }
  
  protected void onRemoteExitTransitionComplete() {
    if (!allowOverlappingTransitions())
      startEnterTransitionOnly(); 
  }
  
  protected void onTransitionsComplete() {
    moveSharedElementsFromOverlay();
    ViewGroup viewGroup = getDecor();
    if (viewGroup != null) {
      viewGroup.sendAccessibilityEvent(2048);
      Window window = getWindow();
      if (window != null && this.mReplacedBackground == viewGroup.getBackground())
        window.setBackgroundDrawable(null); 
    } 
    Runnable runnable = this.mOnTransitionComplete;
    if (runnable != null) {
      runnable.run();
      this.mOnTransitionComplete = null;
    } 
  }
  
  protected void prepareEnter() {
    ViewGroup viewGroup = getDecor();
    if (this.mActivity == null || viewGroup == null)
      return; 
    if (!isCrossTask())
      this.mActivity.overridePendingTransition(0, 0); 
    if (!this.mIsReturning) {
      ColorDrawable colorDrawable;
      Drawable drawable1;
      this.mWasOpaque = this.mActivity.convertToTranslucent(null, null);
      Drawable drawable2 = viewGroup.getBackground();
      if (drawable2 == null) {
        colorDrawable = new ColorDrawable(0);
        this.mReplacedBackground = (Drawable)colorDrawable;
      } else {
        getWindow().setBackgroundDrawable(null);
        drawable1 = colorDrawable.mutate();
        drawable1.setAlpha(0);
      } 
      getWindow().setBackgroundDrawable(drawable1);
    } else {
      this.mActivity = null;
    } 
  }
  
  public void runAfterTransitionsComplete(Runnable paramRunnable) {
    if (!isTransitionRunning()) {
      onTransitionsComplete();
    } else {
      this.mOnTransitionComplete = paramRunnable;
    } 
  }
  
  public void stop() {
    ObjectAnimator objectAnimator = this.mBackgroundAnimator;
    if (objectAnimator != null) {
      objectAnimator.end();
      this.mBackgroundAnimator = null;
    } else if (this.mWasOpaque) {
      ViewGroup viewGroup = getDecor();
      if (viewGroup != null) {
        Drawable drawable = viewGroup.getBackground();
        if (drawable != null)
          drawable.setAlpha(1); 
      } 
    } 
    makeOpaque();
    this.mIsCanceled = true;
    this.mResultReceiver = null;
    this.mActivity = null;
    moveSharedElementsFromOverlay();
    if (this.mTransitioningViews != null) {
      showViews(this.mTransitioningViews, true);
      setTransitioningViewsVisiblity(0, true);
    } 
    showViews(this.mSharedElements, true);
    clearState();
  }
  
  public void viewInstancesReady(ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2, ArrayList<View> paramArrayList) {
    boolean bool2;
    boolean bool1 = false;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (b < paramArrayList.size()) {
        View view = paramArrayList.get(b);
        if (!TextUtils.equals(view.getTransitionName(), paramArrayList2.get(b)) || !view.isAttachedToWindow()) {
          bool2 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    if (bool2) {
      triggerViewsReady(mapNamedElements(paramArrayList1, paramArrayList2));
    } else {
      triggerViewsReady(mapSharedElements(paramArrayList1, paramArrayList));
    } 
  }
  
  protected void viewsReady(ArrayMap<String, View> paramArrayMap) {
    super.viewsReady(paramArrayMap);
    this.mIsReadyForTransition = true;
    hideViews(this.mSharedElements);
    Transition transition = getViewsTransition();
    if (transition != null && this.mTransitioningViews != null) {
      removeExcludedViews(transition, this.mTransitioningViews);
      stripOffscreenViews();
      hideViews(this.mTransitioningViews);
    } 
    if (this.mIsReturning) {
      sendSharedElementDestination();
    } else {
      moveSharedElementsToOverlay();
    } 
    if (this.mSharedElementsBundle != null)
      onTakeSharedElements(); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */