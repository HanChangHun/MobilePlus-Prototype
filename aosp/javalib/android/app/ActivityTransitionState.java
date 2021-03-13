package android.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.transition.Transition;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.android.internal.view.OneShotPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

class ActivityTransitionState {
  private static final String EXITING_MAPPED_FROM = "android:exitingMappedFrom";
  
  private static final String EXITING_MAPPED_TO = "android:exitingMappedTo";
  
  private static final String PENDING_EXIT_SHARED_ELEMENTS = "android:pendingExitSharedElements";
  
  private ExitTransitionCoordinator mCalledExitCoordinator;
  
  private ActivityOptions mEnterActivityOptions;
  
  private EnterTransitionCoordinator mEnterTransitionCoordinator;
  
  private SparseArray<WeakReference<ExitTransitionCoordinator>> mExitTransitionCoordinators;
  
  private int mExitTransitionCoordinatorsKey = 1;
  
  private ArrayList<String> mExitingFrom;
  
  private ArrayList<String> mExitingTo;
  
  private ArrayList<View> mExitingToView;
  
  private boolean mHasExited;
  
  private boolean mIsEnterPostponed;
  
  private boolean mIsEnterTriggered;
  
  private ArrayList<String> mPendingExitNames;
  
  private ExitTransitionCoordinator mReturnExitCoordinator;
  
  private ArrayList<String> getPendingExitNames() {
    if (this.mPendingExitNames == null) {
      EnterTransitionCoordinator enterTransitionCoordinator = this.mEnterTransitionCoordinator;
      if (enterTransitionCoordinator != null)
        this.mPendingExitNames = enterTransitionCoordinator.getPendingExitSharedElementNames(); 
    } 
    return this.mPendingExitNames;
  }
  
  private void restoreExitedViews() {
    ExitTransitionCoordinator exitTransitionCoordinator = this.mCalledExitCoordinator;
    if (exitTransitionCoordinator != null) {
      exitTransitionCoordinator.resetViews();
      this.mCalledExitCoordinator = null;
    } 
  }
  
  private void restoreReenteringViews() {
    EnterTransitionCoordinator enterTransitionCoordinator = this.mEnterTransitionCoordinator;
    if (enterTransitionCoordinator != null && enterTransitionCoordinator.isReturning() && !this.mEnterTransitionCoordinator.isCrossTask()) {
      this.mEnterTransitionCoordinator.forceViewsToAppear();
      this.mExitingFrom = null;
      this.mExitingTo = null;
      this.mExitingToView = null;
    } 
  }
  
  private void startEnter() {
    if (this.mEnterTransitionCoordinator.isReturning()) {
      ArrayList<View> arrayList = this.mExitingToView;
      if (arrayList != null) {
        this.mEnterTransitionCoordinator.viewInstancesReady(this.mExitingFrom, this.mExitingTo, arrayList);
      } else {
        this.mEnterTransitionCoordinator.namedViewsReady(this.mExitingFrom, this.mExitingTo);
      } 
    } else {
      this.mEnterTransitionCoordinator.namedViewsReady((ArrayList<String>)null, (ArrayList<String>)null);
      this.mPendingExitNames = null;
    } 
    this.mExitingFrom = null;
    this.mExitingTo = null;
    this.mExitingToView = null;
    this.mEnterActivityOptions = null;
  }
  
  public int addExitTransitionCoordinator(ExitTransitionCoordinator paramExitTransitionCoordinator) {
    if (this.mExitTransitionCoordinators == null)
      this.mExitTransitionCoordinators = new SparseArray(); 
    WeakReference<ExitTransitionCoordinator> weakReference = new WeakReference<>(paramExitTransitionCoordinator);
    int i;
    for (i = this.mExitTransitionCoordinators.size() - 1; i >= 0; i--) {
      if (((WeakReference)this.mExitTransitionCoordinators.valueAt(i)).get() == null)
        this.mExitTransitionCoordinators.removeAt(i); 
    } 
    i = this.mExitTransitionCoordinatorsKey;
    this.mExitTransitionCoordinatorsKey = i + 1;
    this.mExitTransitionCoordinators.append(i, weakReference);
    return i;
  }
  
  public void clear() {
    this.mPendingExitNames = null;
    this.mExitingFrom = null;
    this.mExitingTo = null;
    this.mExitingToView = null;
    this.mCalledExitCoordinator = null;
    this.mEnterTransitionCoordinator = null;
    this.mEnterActivityOptions = null;
    this.mExitTransitionCoordinators = null;
  }
  
  public void enterReady(Activity paramActivity) {
    ActivityOptions activityOptions = this.mEnterActivityOptions;
    if (activityOptions == null || this.mIsEnterTriggered)
      return; 
    this.mIsEnterTriggered = true;
    this.mHasExited = false;
    ArrayList<String> arrayList = activityOptions.getSharedElementNames();
    ResultReceiver resultReceiver = this.mEnterActivityOptions.getResultReceiver();
    if (this.mEnterActivityOptions.isReturning()) {
      restoreExitedViews();
      paramActivity.getWindow().getDecorView().setVisibility(0);
    } 
    this.mEnterTransitionCoordinator = new EnterTransitionCoordinator(paramActivity, resultReceiver, arrayList, this.mEnterActivityOptions.isReturning(), this.mEnterActivityOptions.isCrossTask());
    if (this.mEnterActivityOptions.isCrossTask()) {
      this.mExitingFrom = new ArrayList<>(this.mEnterActivityOptions.getSharedElementNames());
      this.mExitingTo = new ArrayList<>(this.mEnterActivityOptions.getSharedElementNames());
    } 
    if (!this.mIsEnterPostponed)
      startEnter(); 
  }
  
  public boolean isTransitionRunning() {
    EnterTransitionCoordinator enterTransitionCoordinator = this.mEnterTransitionCoordinator;
    if (enterTransitionCoordinator != null && enterTransitionCoordinator.isTransitionRunning())
      return true; 
    ExitTransitionCoordinator exitTransitionCoordinator = this.mCalledExitCoordinator;
    if (exitTransitionCoordinator != null && exitTransitionCoordinator.isTransitionRunning())
      return true; 
    exitTransitionCoordinator = this.mReturnExitCoordinator;
    return (exitTransitionCoordinator != null && exitTransitionCoordinator.isTransitionRunning());
  }
  
  public void onResume(Activity paramActivity) {
    if (this.mEnterTransitionCoordinator == null || paramActivity.isTopOfTask()) {
      restoreExitedViews();
      restoreReenteringViews();
      return;
    } 
    paramActivity.mHandler.postDelayed(new Runnable() {
          public void run() {
            if (ActivityTransitionState.this.mEnterTransitionCoordinator == null || ActivityTransitionState.this.mEnterTransitionCoordinator.isWaitingForRemoteExit()) {
              ActivityTransitionState.this.restoreExitedViews();
              ActivityTransitionState.this.restoreReenteringViews();
              return;
            } 
            if (ActivityTransitionState.this.mEnterTransitionCoordinator.isReturning())
              ActivityTransitionState.this.mEnterTransitionCoordinator.runAfterTransitionsComplete(new _$$Lambda$ActivityTransitionState$1$fqoR3vg9Harhjtb0p87HpKinLK8(this)); 
          }
        },  1000L);
  }
  
  public void onStop() {
    restoreExitedViews();
    EnterTransitionCoordinator enterTransitionCoordinator = this.mEnterTransitionCoordinator;
    if (enterTransitionCoordinator != null) {
      enterTransitionCoordinator.stop();
      this.mEnterTransitionCoordinator = null;
    } 
    ExitTransitionCoordinator exitTransitionCoordinator = this.mReturnExitCoordinator;
    if (exitTransitionCoordinator != null) {
      exitTransitionCoordinator.stop();
      this.mReturnExitCoordinator = null;
    } 
  }
  
  public void postponeEnterTransition() {
    this.mIsEnterPostponed = true;
  }
  
  public void readState(Bundle paramBundle) {
    if (paramBundle != null) {
      EnterTransitionCoordinator enterTransitionCoordinator = this.mEnterTransitionCoordinator;
      if (enterTransitionCoordinator == null || enterTransitionCoordinator.isReturning())
        this.mPendingExitNames = paramBundle.getStringArrayList("android:pendingExitSharedElements"); 
      if (this.mEnterTransitionCoordinator == null) {
        this.mExitingFrom = paramBundle.getStringArrayList("android:exitingMappedFrom");
        this.mExitingTo = paramBundle.getStringArrayList("android:exitingMappedTo");
      } 
    } 
  }
  
  public void saveState(Bundle paramBundle) {
    ArrayList<String> arrayList = getPendingExitNames();
    if (arrayList != null)
      paramBundle.putStringArrayList("android:pendingExitSharedElements", arrayList); 
    arrayList = this.mExitingFrom;
    if (arrayList != null) {
      paramBundle.putStringArrayList("android:exitingMappedFrom", arrayList);
      paramBundle.putStringArrayList("android:exitingMappedTo", this.mExitingTo);
    } 
  }
  
  public void setEnterActivityOptions(Activity paramActivity, ActivityOptions paramActivityOptions) {
    Window window = paramActivity.getWindow();
    if (window == null)
      return; 
    window.getDecorView();
    if (window.hasFeature(13) && paramActivityOptions != null && this.mEnterActivityOptions == null && this.mEnterTransitionCoordinator == null && paramActivityOptions.getAnimationType() == 5) {
      this.mEnterActivityOptions = paramActivityOptions;
      this.mIsEnterTriggered = false;
      if (paramActivityOptions.isReturning()) {
        restoreExitedViews();
        int i = this.mEnterActivityOptions.getResultCode();
        if (i != 0) {
          Intent intent = this.mEnterActivityOptions.getResultData();
          if (intent != null)
            intent.setExtrasClassLoader(paramActivity.getClassLoader()); 
          paramActivity.onActivityReenter(i, intent);
        } 
      } 
    } 
  }
  
  public boolean startExitBackTransition(Activity paramActivity) {
    ArrayList<String> arrayList = getPendingExitNames();
    if (arrayList == null || this.mCalledExitCoordinator != null)
      return false; 
    if (!this.mHasExited) {
      Transition transition;
      boolean bool;
      this.mHasExited = true;
      EnterTransitionCoordinator enterTransitionCoordinator = this.mEnterTransitionCoordinator;
      if (enterTransitionCoordinator != null) {
        transition = enterTransitionCoordinator.getEnterViewsTransition();
        ViewGroup viewGroup = this.mEnterTransitionCoordinator.getDecor();
        bool = this.mEnterTransitionCoordinator.cancelEnter();
        this.mEnterTransitionCoordinator = null;
        if (transition != null && viewGroup != null)
          transition.pause((View)viewGroup); 
      } else {
        transition = null;
        enterTransitionCoordinator = null;
        bool = false;
      } 
      this.mReturnExitCoordinator = new ExitTransitionCoordinator(paramActivity, paramActivity.getWindow(), paramActivity.mEnterTransitionListener, arrayList, null, null, true);
      if (transition != null && enterTransitionCoordinator != null)
        transition.resume((View)enterTransitionCoordinator); 
      if (bool && enterTransitionCoordinator != null) {
        OneShotPreDrawListener.add((View)enterTransitionCoordinator, new _$$Lambda$ActivityTransitionState$yioLR6wQWjZ9DcWK5bibElIbsXc(this, paramActivity));
      } else {
        this.mReturnExitCoordinator.startExit(paramActivity.mResultCode, paramActivity.mResultData);
      } 
    } 
    return true;
  }
  
  public void startExitOutTransition(Activity paramActivity, Bundle paramBundle) {
    this.mEnterTransitionCoordinator = null;
    if (!paramActivity.getWindow().hasFeature(13) || this.mExitTransitionCoordinators == null)
      return; 
    ActivityOptions activityOptions = new ActivityOptions(paramBundle);
    if (activityOptions.getAnimationType() == 5) {
      int i = activityOptions.getExitCoordinatorKey();
      i = this.mExitTransitionCoordinators.indexOfKey(i);
      if (i >= 0) {
        this.mCalledExitCoordinator = ((WeakReference<ExitTransitionCoordinator>)this.mExitTransitionCoordinators.valueAt(i)).get();
        this.mExitTransitionCoordinators.removeAt(i);
        ExitTransitionCoordinator exitTransitionCoordinator = this.mCalledExitCoordinator;
        if (exitTransitionCoordinator != null) {
          this.mExitingFrom = exitTransitionCoordinator.getAcceptedNames();
          this.mExitingTo = this.mCalledExitCoordinator.getMappedNames();
          this.mExitingToView = this.mCalledExitCoordinator.copyMappedViews();
          this.mCalledExitCoordinator.startExit();
        } 
      } 
    } 
  }
  
  public void startPostponedEnterTransition() {
    if (this.mIsEnterPostponed) {
      this.mIsEnterPostponed = false;
      if (this.mEnterTransitionCoordinator != null)
        startEnter(); 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityTransitionState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */