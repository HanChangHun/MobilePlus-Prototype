package android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.android.internal.view.OneShotPreDrawListener;
import java.util.ArrayList;

class ExitTransitionCoordinator extends ActivityTransitionCoordinator {
  private static final String TAG = "ExitTransitionCoordinator";
  
  static long sMaxWaitMillis = 1000L;
  
  private Activity mActivity;
  
  private ObjectAnimator mBackgroundAnimator;
  
  private boolean mExitNotified;
  
  private Bundle mExitSharedElementBundle;
  
  private Handler mHandler;
  
  private HideSharedElementsCallback mHideSharedElementsCallback;
  
  private boolean mIsBackgroundReady;
  
  private boolean mIsCanceled;
  
  private boolean mIsExitStarted;
  
  private boolean mIsHidden;
  
  private Bundle mSharedElementBundle;
  
  private boolean mSharedElementNotified;
  
  private boolean mSharedElementsHidden;
  
  public ExitTransitionCoordinator(Activity paramActivity, Window paramWindow, SharedElementCallback paramSharedElementCallback, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2, ArrayList<View> paramArrayList, boolean paramBoolean) {
    super(paramWindow, paramArrayList1, paramSharedElementCallback, paramBoolean);
    viewsReady(mapSharedElements(paramArrayList2, paramArrayList));
    stripOffscreenViews();
    this.mIsBackgroundReady = paramBoolean ^ true;
    this.mActivity = paramActivity;
  }
  
  private void beginTransitions() {
    Transition transition1 = getSharedElementExitTransition();
    Transition transition2 = getExitTransition();
    Transition transition3 = mergeTransitions(transition1, transition2);
    ViewGroup viewGroup = getDecor();
    if (transition3 != null && viewGroup != null) {
      setGhostVisibility(4);
      scheduleGhostVisibilityChange(4);
      if (transition2 != null)
        setTransitioningViewsVisiblity(0, false); 
      TransitionManager.beginDelayedTransition(viewGroup, transition3);
      scheduleGhostVisibilityChange(0);
      setGhostVisibility(0);
      if (transition2 != null)
        setTransitioningViewsVisiblity(4, false); 
      viewGroup.invalidate();
    } else {
      transitionStarted();
    } 
  }
  
  private Bundle captureExitSharedElementsState() {
    Bundle bundle = new Bundle();
    RectF rectF = new RectF();
    Matrix matrix = new Matrix();
    for (byte b = 0; b < this.mSharedElements.size(); b++) {
      String str = this.mSharedElementNames.get(b);
      Bundle bundle1 = this.mExitSharedElementBundle.getBundle(str);
      if (bundle1 != null) {
        bundle.putBundle(str, bundle1);
      } else {
        captureSharedElementState(this.mSharedElements.get(b), str, bundle, matrix, rectF);
      } 
    } 
    return bundle;
  }
  
  private void delayCancel() {
    Handler handler = this.mHandler;
    if (handler != null)
      handler.sendEmptyMessageDelayed(106, sMaxWaitMillis); 
  }
  
  private void fadeOutBackground() {
    if (this.mBackgroundAnimator == null) {
      ViewGroup viewGroup = getDecor();
      if (viewGroup != null) {
        Drawable drawable = viewGroup.getBackground();
        if (drawable != null) {
          drawable = drawable.mutate();
          getWindow().setBackgroundDrawable(drawable);
          ObjectAnimator objectAnimator = ObjectAnimator.ofInt(drawable, "alpha", new int[] { 0 });
          this.mBackgroundAnimator = objectAnimator;
          objectAnimator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator param1Animator) {
                  ExitTransitionCoordinator.access$802(ExitTransitionCoordinator.this, (ObjectAnimator)null);
                  if (!ExitTransitionCoordinator.this.mIsCanceled) {
                    ExitTransitionCoordinator.access$902(ExitTransitionCoordinator.this, true);
                    ExitTransitionCoordinator.this.notifyComplete();
                  } 
                  ExitTransitionCoordinator.this.backgroundAnimatorComplete();
                }
              });
          this.mBackgroundAnimator.setDuration(getFadeDuration());
          this.mBackgroundAnimator.start();
          return;
        } 
      } 
      backgroundAnimatorComplete();
      this.mIsBackgroundReady = true;
    } 
  }
  
  private void finish() {
    stopCancel();
    Activity activity = this.mActivity;
    if (activity != null) {
      activity.mActivityTransitionState.clear();
      this.mActivity.finish();
      this.mActivity.overridePendingTransition(0, 0);
      this.mActivity = null;
    } 
    clearState();
  }
  
  private void finishIfNecessary() {
    if (this.mIsReturning && this.mExitNotified && this.mActivity != null && (this.mSharedElements.isEmpty() || this.mSharedElementsHidden))
      finish(); 
    if (!this.mIsReturning && this.mExitNotified)
      this.mActivity = null; 
  }
  
  private Transition getExitTransition() {
    Transition transition1 = null;
    Transition transition2 = transition1;
    if (this.mTransitioningViews != null) {
      transition2 = transition1;
      if (!this.mTransitioningViews.isEmpty()) {
        transition2 = configureTransition(getViewsTransition(), true);
        removeExcludedViews(transition2, this.mTransitioningViews);
        if (this.mTransitioningViews.isEmpty())
          transition2 = null; 
      } 
    } 
    if (transition2 == null) {
      viewsTransitionComplete();
    } else {
      transition2.addListener((Transition.TransitionListener)new ActivityTransitionCoordinator.ContinueTransitionListener() {
            public void onTransitionEnd(Transition param1Transition) {
              ExitTransitionCoordinator.this.viewsTransitionComplete();
              if (ExitTransitionCoordinator.this.mIsHidden) {
                ArrayList<View> arrayList = transitioningViews;
                if (arrayList != null) {
                  ExitTransitionCoordinator.this.showViews(arrayList, true);
                  ExitTransitionCoordinator.this.setTransitioningViewsVisiblity(0, true);
                } 
              } 
              if (ExitTransitionCoordinator.this.mSharedElementBundle != null)
                ExitTransitionCoordinator.this.delayCancel(); 
              super.onTransitionEnd(param1Transition);
            }
          });
    } 
    return transition2;
  }
  
  private Transition getSharedElementExitTransition() {
    Transition transition = null;
    if (!this.mSharedElements.isEmpty())
      transition = configureTransition(getSharedElementTransition(), false); 
    if (transition == null) {
      sharedElementTransitionComplete();
    } else {
      transition.addListener((Transition.TransitionListener)new ActivityTransitionCoordinator.ContinueTransitionListener() {
            public void onTransitionEnd(Transition param1Transition) {
              ExitTransitionCoordinator.this.sharedElementTransitionComplete();
              if (ExitTransitionCoordinator.this.mIsHidden) {
                ExitTransitionCoordinator exitTransitionCoordinator = ExitTransitionCoordinator.this;
                exitTransitionCoordinator.showViews(exitTransitionCoordinator.mSharedElements, true);
              } 
              super.onTransitionEnd(param1Transition);
            }
          });
      ((View)this.mSharedElements.get(0)).invalidate();
    } 
    return transition;
  }
  
  private void hideSharedElements() {
    moveSharedElementsFromOverlay();
    HideSharedElementsCallback hideSharedElementsCallback = this.mHideSharedElementsCallback;
    if (hideSharedElementsCallback != null)
      hideSharedElementsCallback.hideSharedElements(); 
    if (!this.mIsHidden)
      hideViews(this.mSharedElements); 
    this.mSharedElementsHidden = true;
    finishIfNecessary();
  }
  
  private void notifyExitComplete() {
    if (!this.mExitNotified && isViewsTransitionComplete()) {
      this.mExitNotified = true;
      this.mResultReceiver.send(104, null);
      this.mResultReceiver = null;
      ViewGroup viewGroup = getDecor();
      if (!this.mIsReturning && viewGroup != null)
        viewGroup.suppressLayout(false); 
      finishIfNecessary();
    } 
  }
  
  private void sharedElementExitBack() {
    final ViewGroup decorView = getDecor();
    if (viewGroup != null)
      viewGroup.suppressLayout(true); 
    if (viewGroup != null) {
      Bundle bundle = this.mExitSharedElementBundle;
      if (bundle != null && !bundle.isEmpty() && !this.mSharedElements.isEmpty() && getSharedElementTransition() != null) {
        startTransition(new Runnable() {
              public void run() {
                ExitTransitionCoordinator.this.startSharedElementExit(decorView);
              }
            });
        return;
      } 
    } 
    sharedElementTransitionComplete();
  }
  
  private void startExitTransition() {
    Transition transition = getExitTransition();
    ViewGroup viewGroup = getDecor();
    if (transition != null && viewGroup != null && this.mTransitioningViews != null) {
      setTransitioningViewsVisiblity(0, false);
      TransitionManager.beginDelayedTransition(viewGroup, transition);
      setTransitioningViewsVisiblity(4, false);
      viewGroup.invalidate();
    } else {
      transitionStarted();
    } 
  }
  
  private void startSharedElementExit(ViewGroup paramViewGroup) {
    Transition transition = getSharedElementExitTransition();
    transition.addListener((Transition.TransitionListener)new TransitionListenerAdapter() {
          public void onTransitionEnd(Transition param1Transition) {
            param1Transition.removeListener((Transition.TransitionListener)this);
            if (ExitTransitionCoordinator.this.isViewsTransitionComplete())
              ExitTransitionCoordinator.this.delayCancel(); 
          }
        });
    ArrayList<View> arrayList = createSnapshots(this.mExitSharedElementBundle, this.mSharedElementNames);
    OneShotPreDrawListener.add((View)paramViewGroup, new _$$Lambda$ExitTransitionCoordinator$QSAvMs76ZWnO0eiLyXWkcGxkRIY(this, arrayList));
    setGhostVisibility(4);
    scheduleGhostVisibilityChange(4);
    if (this.mListener != null)
      this.mListener.onSharedElementEnd(this.mSharedElementNames, this.mSharedElements, arrayList); 
    TransitionManager.beginDelayedTransition(paramViewGroup, transition);
    scheduleGhostVisibilityChange(0);
    setGhostVisibility(0);
    paramViewGroup.invalidate();
  }
  
  private void stopCancel() {
    Handler handler = this.mHandler;
    if (handler != null)
      handler.removeMessages(106); 
  }
  
  protected void clearState() {
    this.mHandler = null;
    this.mSharedElementBundle = null;
    ObjectAnimator objectAnimator = this.mBackgroundAnimator;
    if (objectAnimator != null) {
      objectAnimator.cancel();
      this.mBackgroundAnimator = null;
    } 
    this.mExitSharedElementBundle = null;
    super.clearState();
  }
  
  protected Transition getSharedElementTransition() {
    return this.mIsReturning ? getWindow().getSharedElementReturnTransition() : getWindow().getSharedElementExitTransition();
  }
  
  protected Transition getViewsTransition() {
    return this.mIsReturning ? getWindow().getReturnTransition() : getWindow().getExitTransition();
  }
  
  protected boolean isReadyToNotify() {
    boolean bool;
    if (this.mSharedElementBundle != null && this.mResultReceiver != null && this.mIsBackgroundReady) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected boolean moveSharedElementWithParent() {
    return this.mIsReturning ^ true;
  }
  
  protected void notifyComplete() {
    if (isReadyToNotify())
      if (!this.mSharedElementNotified) {
        this.mSharedElementNotified = true;
        delayCancel();
        if (!this.mActivity.isTopOfTask())
          this.mResultReceiver.send(108, null); 
        if (this.mListener == null) {
          this.mResultReceiver.send(103, this.mSharedElementBundle);
          notifyExitComplete();
        } else {
          final ResultReceiver resultReceiver = this.mResultReceiver;
          final Bundle sharedElementBundle = this.mSharedElementBundle;
          this.mListener.onSharedElementsArrived(this.mSharedElementNames, this.mSharedElements, new SharedElementCallback.OnSharedElementsReadyListener() {
                public void onSharedElementsReady() {
                  resultReceiver.send(103, sharedElementBundle);
                  ExitTransitionCoordinator.this.notifyExitComplete();
                }
              });
        } 
      } else {
        notifyExitComplete();
      }  
  }
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle) {
    if (paramInt != 100) {
      if (paramInt != 101) {
        switch (paramInt) {
          default:
            return;
          case 107:
            this.mExitSharedElementBundle = paramBundle;
            sharedElementExitBack();
          case 106:
            this.mIsCanceled = true;
            finish();
          case 105:
            break;
        } 
        this.mHandler.removeMessages(106);
        startExit();
      } 
      stopCancel();
      if (!this.mIsCanceled)
        hideSharedElements(); 
    } 
    stopCancel();
    this.mResultReceiver = (ResultReceiver)paramBundle.getParcelable("android:remoteReceiver");
    if (this.mIsCanceled) {
      this.mResultReceiver.send(106, null);
      this.mResultReceiver = null;
    } 
    notifyComplete();
  }
  
  protected void onTransitionsComplete() {
    notifyComplete();
  }
  
  public void resetViews() {
    ViewGroup viewGroup = getDecor();
    if (viewGroup != null)
      TransitionManager.endTransitions(viewGroup); 
    if (this.mTransitioningViews != null) {
      showViews(this.mTransitioningViews, true);
      setTransitioningViewsVisiblity(0, true);
    } 
    showViews(this.mSharedElements, true);
    this.mIsHidden = true;
    if (!this.mIsReturning && viewGroup != null)
      viewGroup.suppressLayout(false); 
    moveSharedElementsFromOverlay();
    clearState();
  }
  
  void setHideSharedElementsCallback(HideSharedElementsCallback paramHideSharedElementsCallback) {
    this.mHideSharedElementsCallback = paramHideSharedElementsCallback;
  }
  
  protected void sharedElementTransitionComplete() {
    Bundle bundle;
    if (this.mExitSharedElementBundle == null) {
      bundle = captureSharedElementState();
    } else {
      bundle = captureExitSharedElementsState();
    } 
    this.mSharedElementBundle = bundle;
    super.sharedElementTransitionComplete();
  }
  
  public void startExit() {
    if (!this.mIsExitStarted) {
      backgroundAnimatorComplete();
      this.mIsExitStarted = true;
      pauseInput();
      ViewGroup viewGroup = getDecor();
      if (viewGroup != null)
        viewGroup.suppressLayout(true); 
      moveSharedElementsToOverlay();
      startTransition(new Runnable() {
            public void run() {
              if (ExitTransitionCoordinator.this.mActivity != null) {
                ExitTransitionCoordinator.this.beginTransitions();
              } else {
                ExitTransitionCoordinator.this.startExitTransition();
              } 
            }
          });
    } 
  }
  
  public void startExit(int paramInt, Intent paramIntent) {
    if (!this.mIsExitStarted) {
      ArrayList<String> arrayList;
      boolean bool = true;
      this.mIsExitStarted = true;
      pauseInput();
      ViewGroup viewGroup = getDecor();
      if (viewGroup != null)
        viewGroup.suppressLayout(true); 
      this.mHandler = new Handler() {
          public void handleMessage(Message param1Message) {
            ExitTransitionCoordinator.access$502(ExitTransitionCoordinator.this, true);
            ExitTransitionCoordinator.this.finish();
          }
        };
      delayCancel();
      moveSharedElementsToOverlay();
      if (viewGroup != null && viewGroup.getBackground() == null)
        getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0)); 
      if (viewGroup != null && (viewGroup.getContext().getApplicationInfo()).targetSdkVersion < 23)
        bool = false; 
      if (bool) {
        arrayList = this.mSharedElementNames;
      } else {
        arrayList = this.mAllSharedElementNames;
      } 
      ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this.mActivity, this, arrayList, paramInt, paramIntent);
      this.mActivity.convertToTranslucent(new Activity.TranslucentConversionListener() {
            public void onTranslucentConversionComplete(boolean param1Boolean) {
              if (!ExitTransitionCoordinator.this.mIsCanceled)
                ExitTransitionCoordinator.this.fadeOutBackground(); 
            }
          },  activityOptions);
      startTransition(new Runnable() {
            public void run() {
              ExitTransitionCoordinator.this.startExitTransition();
            }
          });
    } 
  }
  
  public void stop() {
    if (this.mIsReturning) {
      Activity activity = this.mActivity;
      if (activity != null) {
        activity.convertToTranslucent(null, null);
        finish();
      } 
    } 
  }
  
  static interface HideSharedElementsCallback {
    void hideSharedElements();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ExitTransitionCoordinator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */