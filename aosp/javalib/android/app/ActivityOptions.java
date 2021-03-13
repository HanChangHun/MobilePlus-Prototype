package android.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.GraphicBuffer;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.util.Pair;
import android.util.Slog;
import android.view.AppTransitionAnimationSpec;
import android.view.IAppTransitionAnimationSpecsFuture;
import android.view.RemoteAnimationAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.window.WindowContainerToken;
import java.util.ArrayList;

public class ActivityOptions {
  public static final int ANIM_CLIP_REVEAL = 11;
  
  public static final int ANIM_CUSTOM = 1;
  
  public static final int ANIM_CUSTOM_IN_PLACE = 10;
  
  public static final int ANIM_DEFAULT = 6;
  
  public static final int ANIM_LAUNCH_TASK_BEHIND = 7;
  
  public static final int ANIM_NONE = 0;
  
  public static final int ANIM_OPEN_CROSS_PROFILE_APPS = 12;
  
  public static final int ANIM_REMOTE_ANIMATION = 13;
  
  public static final int ANIM_SCALE_UP = 2;
  
  public static final int ANIM_SCENE_TRANSITION = 5;
  
  public static final int ANIM_THUMBNAIL_ASPECT_SCALE_DOWN = 9;
  
  public static final int ANIM_THUMBNAIL_ASPECT_SCALE_UP = 8;
  
  public static final int ANIM_THUMBNAIL_SCALE_DOWN = 4;
  
  public static final int ANIM_THUMBNAIL_SCALE_UP = 3;
  
  public static final int ANIM_UNDEFINED = -1;
  
  public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
  
  public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";
  
  private static final String KEY_ANIMATION_FINISHED_LISTENER = "android:activity.animationFinishedListener";
  
  public static final String KEY_ANIM_ENTER_RES_ID = "android:activity.animEnterRes";
  
  public static final String KEY_ANIM_EXIT_RES_ID = "android:activity.animExitRes";
  
  public static final String KEY_ANIM_HEIGHT = "android:activity.animHeight";
  
  public static final String KEY_ANIM_IN_PLACE_RES_ID = "android:activity.animInPlaceRes";
  
  private static final String KEY_ANIM_SPECS = "android:activity.animSpecs";
  
  public static final String KEY_ANIM_START_LISTENER = "android:activity.animStartListener";
  
  public static final String KEY_ANIM_START_X = "android:activity.animStartX";
  
  public static final String KEY_ANIM_START_Y = "android:activity.animStartY";
  
  public static final String KEY_ANIM_THUMBNAIL = "android:activity.animThumbnail";
  
  public static final String KEY_ANIM_TYPE = "android:activity.animType";
  
  public static final String KEY_ANIM_WIDTH = "android:activity.animWidth";
  
  private static final String KEY_APPLY_ACTIVITY_FLAGS_FOR_BUBBLES = "android:activity.applyActivityFlagsForBubbles";
  
  private static final String KEY_AVOID_MOVE_TO_FRONT = "android.activity.avoidMoveToFront";
  
  private static final String KEY_CALLER_DISPLAY_ID = "android.activity.callerDisplayId";
  
  private static final String KEY_DISALLOW_ENTER_PICTURE_IN_PICTURE_WHILE_LAUNCHING = "android:activity.disallowEnterPictureInPictureWhileLaunching";
  
  private static final String KEY_EXIT_COORDINATOR_INDEX = "android:activity.exitCoordinatorIndex";
  
  private static final String KEY_FREEZE_RECENT_TASKS_REORDERING = "android.activity.freezeRecentTasksReordering";
  
  private static final String KEY_INSTANT_APP_VERIFICATION_BUNDLE = "android:instantapps.installerbundle";
  
  private static final String KEY_LAUNCH_ACTIVITY_TYPE = "android.activity.activityType";
  
  public static final String KEY_LAUNCH_BOUNDS = "android:activity.launchBounds";
  
  private static final String KEY_LAUNCH_DISPLAY_ID = "android.activity.launchDisplayId";
  
  private static final String KEY_LAUNCH_TASK_DISPLAY_AREA_TOKEN = "android.activity.launchTaskDisplayAreaToken";
  
  private static final String KEY_LAUNCH_TASK_ID = "android.activity.launchTaskId";
  
  private static final String KEY_LAUNCH_WINDOWING_MODE = "android.activity.windowingMode";
  
  private static final String KEY_LOCK_TASK_MODE = "android:activity.lockTaskMode";
  
  public static final String KEY_PACKAGE_NAME = "android:activity.packageName";
  
  private static final String KEY_PENDING_INTENT_LAUNCH_FLAGS = "android.activity.pendingIntentLaunchFlags";
  
  private static final String KEY_REMOTE_ANIMATION_ADAPTER = "android:activity.remoteAnimationAdapter";
  
  private static final String KEY_RESULT_CODE = "android:activity.resultCode";
  
  private static final String KEY_RESULT_DATA = "android:activity.resultData";
  
  private static final String KEY_ROTATION_ANIMATION_HINT = "android:activity.rotationAnimationHint";
  
  private static final String KEY_SPECS_FUTURE = "android:activity.specsFuture";
  
  private static final String KEY_SPLIT_SCREEN_CREATE_MODE = "android:activity.splitScreenCreateMode";
  
  private static final String KEY_TASK_ALWAYS_ON_TOP = "android.activity.alwaysOnTop";
  
  private static final String KEY_TASK_OVERLAY = "android.activity.taskOverlay";
  
  private static final String KEY_TASK_OVERLAY_CAN_RESUME = "android.activity.taskOverlayCanResume";
  
  private static final String KEY_TRANSITION_COMPLETE_LISTENER = "android:activity.transitionCompleteListener";
  
  private static final String KEY_TRANSITION_IS_RETURNING = "android:activity.transitionIsReturning";
  
  private static final String KEY_TRANSITION_SHARED_ELEMENTS = "android:activity.sharedElementNames";
  
  private static final String KEY_USAGE_TIME_REPORT = "android:activity.usageTimeReport";
  
  private static final String TAG = "ActivityOptions";
  
  private AppTransitionAnimationSpec[] mAnimSpecs;
  
  private IRemoteCallback mAnimationFinishedListener;
  
  private IRemoteCallback mAnimationStartedListener;
  
  private int mAnimationType;
  
  private Bundle mAppVerificationBundle;
  
  private boolean mApplyActivityFlagsForBubbles;
  
  private boolean mAvoidMoveToFront;
  
  private int mCallerDisplayId;
  
  private int mCustomEnterResId;
  
  private int mCustomExitResId;
  
  private int mCustomInPlaceResId;
  
  private boolean mDisallowEnterPictureInPictureWhileLaunching;
  
  private int mExitCoordinatorIndex;
  
  private boolean mFreezeRecentTasksReordering;
  
  private int mHeight;
  
  private boolean mIsReturning;
  
  private int mLaunchActivityType;
  
  private Rect mLaunchBounds;
  
  private int mLaunchDisplayId;
  
  private WindowContainerToken mLaunchTaskDisplayArea;
  
  private int mLaunchTaskId;
  
  private int mLaunchWindowingMode;
  
  private boolean mLockTaskMode;
  
  private String mPackageName;
  
  private int mPendingIntentLaunchFlags;
  
  private RemoteAnimationAdapter mRemoteAnimationAdapter;
  
  private int mResultCode;
  
  private Intent mResultData;
  
  private int mRotationAnimationHint;
  
  private ArrayList<String> mSharedElementNames;
  
  private IAppTransitionAnimationSpecsFuture mSpecsFuture;
  
  private int mSplitScreenCreateMode;
  
  private int mStartX;
  
  private int mStartY;
  
  private boolean mTaskAlwaysOnTop;
  
  private boolean mTaskOverlay;
  
  private boolean mTaskOverlayCanResume;
  
  private Bitmap mThumbnail;
  
  private ResultReceiver mTransitionReceiver;
  
  private PendingIntent mUsageTimeReport;
  
  private int mWidth;
  
  private ActivityOptions() {
    this.mAnimationType = -1;
    this.mLaunchDisplayId = -1;
    this.mCallerDisplayId = -1;
    this.mLaunchWindowingMode = 0;
    this.mLaunchActivityType = 0;
    this.mLaunchTaskId = -1;
    this.mSplitScreenCreateMode = 0;
    this.mLockTaskMode = false;
    this.mRotationAnimationHint = -1;
  }
  
  public ActivityOptions(Bundle paramBundle) {
    GraphicBuffer graphicBuffer;
    this.mAnimationType = -1;
    this.mLaunchDisplayId = -1;
    this.mCallerDisplayId = -1;
    this.mLaunchWindowingMode = 0;
    this.mLaunchActivityType = 0;
    this.mLaunchTaskId = -1;
    this.mSplitScreenCreateMode = 0;
    this.mLockTaskMode = false;
    this.mRotationAnimationHint = -1;
    paramBundle.setDefusable(true);
    this.mPackageName = paramBundle.getString("android:activity.packageName");
    try {
      this.mUsageTimeReport = (PendingIntent)paramBundle.getParcelable("android:activity.usageTimeReport");
    } catch (RuntimeException runtimeException) {
      Slog.w("ActivityOptions", runtimeException);
    } 
    this.mLaunchBounds = (Rect)paramBundle.getParcelable("android:activity.launchBounds");
    int i = paramBundle.getInt("android:activity.animType", -1);
    this.mAnimationType = i;
    switch (i) {
      case 10:
        this.mCustomInPlaceResId = paramBundle.getInt("android:activity.animInPlaceRes", 0);
        break;
      case 5:
        this.mTransitionReceiver = (ResultReceiver)paramBundle.getParcelable("android:activity.transitionCompleteListener");
        this.mIsReturning = paramBundle.getBoolean("android:activity.transitionIsReturning", false);
        this.mSharedElementNames = paramBundle.getStringArrayList("android:activity.sharedElementNames");
        this.mResultData = (Intent)paramBundle.getParcelable("android:activity.resultData");
        this.mResultCode = paramBundle.getInt("android:activity.resultCode");
        this.mExitCoordinatorIndex = paramBundle.getInt("android:activity.exitCoordinatorIndex");
        break;
      case 3:
      case 4:
      case 8:
      case 9:
        graphicBuffer = (GraphicBuffer)paramBundle.getParcelable("android:activity.animThumbnail");
        if (graphicBuffer != null)
          this.mThumbnail = Bitmap.wrapHardwareBuffer(graphicBuffer, null); 
        this.mStartX = paramBundle.getInt("android:activity.animStartX", 0);
        this.mStartY = paramBundle.getInt("android:activity.animStartY", 0);
        this.mWidth = paramBundle.getInt("android:activity.animWidth", 0);
        this.mHeight = paramBundle.getInt("android:activity.animHeight", 0);
        this.mAnimationStartedListener = IRemoteCallback.Stub.asInterface(paramBundle.getBinder("android:activity.animStartListener"));
        break;
      case 2:
      case 11:
        this.mStartX = paramBundle.getInt("android:activity.animStartX", 0);
        this.mStartY = paramBundle.getInt("android:activity.animStartY", 0);
        this.mWidth = paramBundle.getInt("android:activity.animWidth", 0);
        this.mHeight = paramBundle.getInt("android:activity.animHeight", 0);
        break;
      case 1:
        this.mCustomEnterResId = paramBundle.getInt("android:activity.animEnterRes", 0);
        this.mCustomExitResId = paramBundle.getInt("android:activity.animExitRes", 0);
        this.mAnimationStartedListener = IRemoteCallback.Stub.asInterface(paramBundle.getBinder("android:activity.animStartListener"));
        break;
    } 
    this.mLockTaskMode = paramBundle.getBoolean("android:activity.lockTaskMode", false);
    this.mLaunchDisplayId = paramBundle.getInt("android.activity.launchDisplayId", -1);
    this.mCallerDisplayId = paramBundle.getInt("android.activity.callerDisplayId", -1);
    this.mLaunchTaskDisplayArea = (WindowContainerToken)paramBundle.getParcelable("android.activity.launchTaskDisplayAreaToken");
    this.mLaunchWindowingMode = paramBundle.getInt("android.activity.windowingMode", 0);
    this.mLaunchActivityType = paramBundle.getInt("android.activity.activityType", 0);
    this.mLaunchTaskId = paramBundle.getInt("android.activity.launchTaskId", -1);
    this.mPendingIntentLaunchFlags = paramBundle.getInt("android.activity.pendingIntentLaunchFlags", 0);
    this.mTaskAlwaysOnTop = paramBundle.getBoolean("android.activity.alwaysOnTop", false);
    this.mTaskOverlay = paramBundle.getBoolean("android.activity.taskOverlay", false);
    this.mTaskOverlayCanResume = paramBundle.getBoolean("android.activity.taskOverlayCanResume", false);
    this.mAvoidMoveToFront = paramBundle.getBoolean("android.activity.avoidMoveToFront", false);
    this.mFreezeRecentTasksReordering = paramBundle.getBoolean("android.activity.freezeRecentTasksReordering", false);
    this.mSplitScreenCreateMode = paramBundle.getInt("android:activity.splitScreenCreateMode", 0);
    this.mDisallowEnterPictureInPictureWhileLaunching = paramBundle.getBoolean("android:activity.disallowEnterPictureInPictureWhileLaunching", false);
    this.mApplyActivityFlagsForBubbles = paramBundle.getBoolean("android:activity.applyActivityFlagsForBubbles", false);
    if (paramBundle.containsKey("android:activity.animSpecs")) {
      Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("android:activity.animSpecs");
      this.mAnimSpecs = new AppTransitionAnimationSpec[arrayOfParcelable.length];
      for (i = arrayOfParcelable.length - 1; i >= 0; i--)
        this.mAnimSpecs[i] = (AppTransitionAnimationSpec)arrayOfParcelable[i]; 
    } 
    if (paramBundle.containsKey("android:activity.animationFinishedListener"))
      this.mAnimationFinishedListener = IRemoteCallback.Stub.asInterface(paramBundle.getBinder("android:activity.animationFinishedListener")); 
    this.mRotationAnimationHint = paramBundle.getInt("android:activity.rotationAnimationHint", -1);
    this.mAppVerificationBundle = paramBundle.getBundle("android:instantapps.installerbundle");
    if (paramBundle.containsKey("android:activity.specsFuture"))
      this.mSpecsFuture = IAppTransitionAnimationSpecsFuture.Stub.asInterface(paramBundle.getBinder("android:activity.specsFuture")); 
    this.mRemoteAnimationAdapter = (RemoteAnimationAdapter)paramBundle.getParcelable("android:activity.remoteAnimationAdapter");
  }
  
  public static void abort(ActivityOptions paramActivityOptions) {
    if (paramActivityOptions != null)
      paramActivityOptions.abort(); 
  }
  
  public static ActivityOptions fromBundle(Bundle paramBundle) {
    if (paramBundle != null) {
      ActivityOptions activityOptions = new ActivityOptions(paramBundle);
    } else {
      paramBundle = null;
    } 
    return (ActivityOptions)paramBundle;
  }
  
  private static ActivityOptions makeAspectScaledThumbnailAnimation(View paramView, Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Handler paramHandler, OnAnimationStartedListener paramOnAnimationStartedListener, boolean paramBoolean) {
    byte b;
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mPackageName = paramView.getContext().getPackageName();
    if (paramBoolean) {
      b = 8;
    } else {
      b = 9;
    } 
    activityOptions.mAnimationType = b;
    activityOptions.mThumbnail = paramBitmap;
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    activityOptions.mStartX = arrayOfInt[0] + paramInt1;
    activityOptions.mStartY = arrayOfInt[1] + paramInt2;
    activityOptions.mWidth = paramInt3;
    activityOptions.mHeight = paramInt4;
    activityOptions.setOnAnimationStartedListener(paramHandler, paramOnAnimationStartedListener);
    return activityOptions;
  }
  
  public static ActivityOptions makeBasic() {
    return new ActivityOptions();
  }
  
  public static ActivityOptions makeClipRevealAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mAnimationType = 11;
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    activityOptions.mStartX = arrayOfInt[0] + paramInt1;
    activityOptions.mStartY = arrayOfInt[1] + paramInt2;
    activityOptions.mWidth = paramInt3;
    activityOptions.mHeight = paramInt4;
    return activityOptions;
  }
  
  public static ActivityOptions makeCustomAnimation(Context paramContext, int paramInt1, int paramInt2) {
    return makeCustomAnimation(paramContext, paramInt1, paramInt2, null, null, null);
  }
  
  public static ActivityOptions makeCustomAnimation(Context paramContext, int paramInt1, int paramInt2, Handler paramHandler, OnAnimationStartedListener paramOnAnimationStartedListener) {
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mPackageName = paramContext.getPackageName();
    activityOptions.mAnimationType = 1;
    activityOptions.mCustomEnterResId = paramInt1;
    activityOptions.mCustomExitResId = paramInt2;
    activityOptions.setOnAnimationStartedListener(paramHandler, paramOnAnimationStartedListener);
    return activityOptions;
  }
  
  public static ActivityOptions makeCustomAnimation(Context paramContext, int paramInt1, int paramInt2, Handler paramHandler, OnAnimationStartedListener paramOnAnimationStartedListener, OnAnimationFinishedListener paramOnAnimationFinishedListener) {
    ActivityOptions activityOptions = makeCustomAnimation(paramContext, paramInt1, paramInt2, paramHandler, paramOnAnimationStartedListener);
    activityOptions.setOnAnimationFinishedListener(paramHandler, paramOnAnimationFinishedListener);
    return activityOptions;
  }
  
  public static ActivityOptions makeCustomInPlaceAnimation(Context paramContext, int paramInt) {
    if (paramInt != 0) {
      ActivityOptions activityOptions = new ActivityOptions();
      activityOptions.mPackageName = paramContext.getPackageName();
      activityOptions.mAnimationType = 10;
      activityOptions.mCustomInPlaceResId = paramInt;
      return activityOptions;
    } 
    throw new RuntimeException("You must specify a valid animation.");
  }
  
  public static ActivityOptions makeMultiThumbFutureAspectScaleAnimation(Context paramContext, Handler paramHandler, IAppTransitionAnimationSpecsFuture paramIAppTransitionAnimationSpecsFuture, OnAnimationStartedListener paramOnAnimationStartedListener, boolean paramBoolean) {
    byte b;
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mPackageName = paramContext.getPackageName();
    if (paramBoolean) {
      b = 8;
    } else {
      b = 9;
    } 
    activityOptions.mAnimationType = b;
    activityOptions.mSpecsFuture = paramIAppTransitionAnimationSpecsFuture;
    activityOptions.setOnAnimationStartedListener(paramHandler, paramOnAnimationStartedListener);
    return activityOptions;
  }
  
  public static ActivityOptions makeOpenCrossProfileAppsAnimation() {
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mAnimationType = 12;
    return activityOptions;
  }
  
  public static ActivityOptions makeRemoteAnimation(RemoteAnimationAdapter paramRemoteAnimationAdapter) {
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mRemoteAnimationAdapter = paramRemoteAnimationAdapter;
    activityOptions.mAnimationType = 13;
    return activityOptions;
  }
  
  public static ActivityOptions makeScaleUpAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mPackageName = paramView.getContext().getPackageName();
    activityOptions.mAnimationType = 2;
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    activityOptions.mStartX = arrayOfInt[0] + paramInt1;
    activityOptions.mStartY = arrayOfInt[1] + paramInt2;
    activityOptions.mWidth = paramInt3;
    activityOptions.mHeight = paramInt4;
    return activityOptions;
  }
  
  static ActivityOptions makeSceneTransitionAnimation(Activity paramActivity, ExitTransitionCoordinator paramExitTransitionCoordinator, ArrayList<String> paramArrayList, int paramInt, Intent paramIntent) {
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mAnimationType = 5;
    activityOptions.mSharedElementNames = paramArrayList;
    activityOptions.mTransitionReceiver = paramExitTransitionCoordinator;
    activityOptions.mIsReturning = true;
    activityOptions.mResultCode = paramInt;
    activityOptions.mResultData = paramIntent;
    activityOptions.mExitCoordinatorIndex = paramActivity.mActivityTransitionState.addExitTransitionCoordinator(paramExitTransitionCoordinator);
    return activityOptions;
  }
  
  public static ActivityOptions makeSceneTransitionAnimation(Activity paramActivity, View paramView, String paramString) {
    return makeSceneTransitionAnimation(paramActivity, (Pair<View, String>[])new Pair[] { Pair.create(paramView, paramString) });
  }
  
  @SafeVarargs
  public static ActivityOptions makeSceneTransitionAnimation(Activity paramActivity, Pair<View, String>... paramVarArgs) {
    ActivityOptions activityOptions = new ActivityOptions();
    makeSceneTransitionAnimation(paramActivity, paramActivity.getWindow(), activityOptions, paramActivity.mExitTransitionListener, paramVarArgs);
    return activityOptions;
  }
  
  static ExitTransitionCoordinator makeSceneTransitionAnimation(Activity paramActivity, Window paramWindow, ActivityOptions paramActivityOptions, SharedElementCallback paramSharedElementCallback, Pair<View, String>[] paramArrayOfPair) {
    boolean bool;
    if (!paramWindow.hasFeature(13)) {
      paramActivityOptions.mAnimationType = 6;
      return null;
    } 
    paramActivityOptions.mAnimationType = 5;
    ArrayList<String> arrayList = new ArrayList();
    ArrayList<View> arrayList1 = new ArrayList();
    if (paramArrayOfPair != null) {
      byte b = 0;
      while (b < paramArrayOfPair.length) {
        Pair<View, String> pair = paramArrayOfPair[b];
        String str = (String)pair.second;
        if (str != null) {
          arrayList.add(str);
          if ((View)pair.first != null) {
            arrayList1.add((View)pair.first);
            b++;
            continue;
          } 
          throw new IllegalArgumentException("Shared element must not be null");
        } 
        throw new IllegalArgumentException("Shared element name must not be null");
      } 
    } 
    ExitTransitionCoordinator exitTransitionCoordinator = new ExitTransitionCoordinator(paramActivity, paramWindow, paramSharedElementCallback, arrayList, arrayList, arrayList1, false);
    paramActivityOptions.mTransitionReceiver = exitTransitionCoordinator;
    paramActivityOptions.mSharedElementNames = arrayList;
    if (paramActivity == null) {
      bool = true;
    } else {
      bool = false;
    } 
    paramActivityOptions.mIsReturning = bool;
    if (paramActivity == null) {
      paramActivityOptions.mExitCoordinatorIndex = -1;
    } else {
      paramActivityOptions.mExitCoordinatorIndex = paramActivity.mActivityTransitionState.addExitTransitionCoordinator(exitTransitionCoordinator);
    } 
    return exitTransitionCoordinator;
  }
  
  public static ActivityOptions makeTaskLaunchBehind() {
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mAnimationType = 7;
    return activityOptions;
  }
  
  private static ActivityOptions makeThumbnailAnimation(View paramView, Bitmap paramBitmap, int paramInt1, int paramInt2, OnAnimationStartedListener paramOnAnimationStartedListener, boolean paramBoolean) {
    byte b;
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mPackageName = paramView.getContext().getPackageName();
    if (paramBoolean) {
      b = 3;
    } else {
      b = 4;
    } 
    activityOptions.mAnimationType = b;
    activityOptions.mThumbnail = paramBitmap;
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    activityOptions.mStartX = arrayOfInt[0] + paramInt1;
    activityOptions.mStartY = arrayOfInt[1] + paramInt2;
    activityOptions.setOnAnimationStartedListener(paramView.getHandler(), paramOnAnimationStartedListener);
    return activityOptions;
  }
  
  public static ActivityOptions makeThumbnailAspectScaleDownAnimation(View paramView, Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Handler paramHandler, OnAnimationStartedListener paramOnAnimationStartedListener) {
    return makeAspectScaledThumbnailAnimation(paramView, paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4, paramHandler, paramOnAnimationStartedListener, false);
  }
  
  public static ActivityOptions makeThumbnailAspectScaleDownAnimation(View paramView, AppTransitionAnimationSpec[] paramArrayOfAppTransitionAnimationSpec, Handler paramHandler, OnAnimationStartedListener paramOnAnimationStartedListener, OnAnimationFinishedListener paramOnAnimationFinishedListener) {
    ActivityOptions activityOptions = new ActivityOptions();
    activityOptions.mPackageName = paramView.getContext().getPackageName();
    activityOptions.mAnimationType = 9;
    activityOptions.mAnimSpecs = paramArrayOfAppTransitionAnimationSpec;
    activityOptions.setOnAnimationStartedListener(paramHandler, paramOnAnimationStartedListener);
    activityOptions.setOnAnimationFinishedListener(paramHandler, paramOnAnimationFinishedListener);
    return activityOptions;
  }
  
  public static ActivityOptions makeThumbnailScaleUpAnimation(View paramView, Bitmap paramBitmap, int paramInt1, int paramInt2) {
    return makeThumbnailScaleUpAnimation(paramView, paramBitmap, paramInt1, paramInt2, null);
  }
  
  private static ActivityOptions makeThumbnailScaleUpAnimation(View paramView, Bitmap paramBitmap, int paramInt1, int paramInt2, OnAnimationStartedListener paramOnAnimationStartedListener) {
    return makeThumbnailAnimation(paramView, paramBitmap, paramInt1, paramInt2, paramOnAnimationStartedListener, true);
  }
  
  public static void setExitTransitionTimeout(long paramLong) {
    ExitTransitionCoordinator.sMaxWaitMillis = paramLong;
  }
  
  private void setOnAnimationFinishedListener(final Handler handler, final OnAnimationFinishedListener listener) {
    if (listener != null)
      this.mAnimationFinishedListener = (IRemoteCallback)new IRemoteCallback.Stub() {
          public void sendResult(Bundle param1Bundle) throws RemoteException {
            handler.post(new Runnable() {
                  public void run() {
                    listener.onAnimationFinished();
                  }
                });
          }
        }; 
  }
  
  private void setOnAnimationStartedListener(final Handler handler, final OnAnimationStartedListener listener) {
    if (listener != null)
      this.mAnimationStartedListener = (IRemoteCallback)new IRemoteCallback.Stub() {
          public void sendResult(Bundle param1Bundle) throws RemoteException {
            handler.post(new Runnable() {
                  public void run() {
                    listener.onAnimationStarted();
                  }
                });
          }
        }; 
  }
  
  @SafeVarargs
  public static ActivityOptions startSharedElementAnimation(Window paramWindow, Pair<View, String>... paramVarArgs) {
    ActivityOptions activityOptions = new ActivityOptions();
    if (paramWindow.getDecorView() == null)
      return activityOptions; 
    ExitTransitionCoordinator exitTransitionCoordinator = makeSceneTransitionAnimation((Activity)null, paramWindow, activityOptions, (SharedElementCallback)null, paramVarArgs);
    if (exitTransitionCoordinator != null) {
      exitTransitionCoordinator.setHideSharedElementsCallback(new HideWindowListener(paramWindow, exitTransitionCoordinator));
      exitTransitionCoordinator.startExit();
    } 
    return activityOptions;
  }
  
  public static void stopSharedElementAnimation(Window paramWindow) {
    View view = paramWindow.getDecorView();
    if (view == null)
      return; 
    ExitTransitionCoordinator exitTransitionCoordinator = (ExitTransitionCoordinator)view.getTag(16908897);
    if (exitTransitionCoordinator != null) {
      exitTransitionCoordinator.cancelPendingTransitions();
      view.setTagInternal(16908897, null);
      TransitionManager.endTransitions((ViewGroup)view);
      exitTransitionCoordinator.resetViews();
      exitTransitionCoordinator.clearState();
      view.setVisibility(0);
    } 
  }
  
  public void abort() {
    IRemoteCallback iRemoteCallback = this.mAnimationStartedListener;
    if (iRemoteCallback != null)
      try {
        iRemoteCallback.sendResult(null);
      } catch (RemoteException remoteException) {} 
  }
  
  public boolean canTaskOverlayResume() {
    return this.mTaskOverlayCanResume;
  }
  
  public boolean disallowEnterPictureInPictureWhileLaunching() {
    return this.mDisallowEnterPictureInPictureWhileLaunching;
  }
  
  public ActivityOptions forTargetActivity() {
    if (this.mAnimationType == 5) {
      ActivityOptions activityOptions = new ActivityOptions();
      activityOptions.update(this);
      return activityOptions;
    } 
    return null;
  }
  
  public boolean freezeRecentTasksReordering() {
    return this.mFreezeRecentTasksReordering;
  }
  
  public AppTransitionAnimationSpec[] getAnimSpecs() {
    return this.mAnimSpecs;
  }
  
  public IRemoteCallback getAnimationFinishedListener() {
    return this.mAnimationFinishedListener;
  }
  
  public IRemoteCallback getAnimationStartedListener() {
    return this.mAnimationStartedListener;
  }
  
  public int getAnimationType() {
    return this.mAnimationType;
  }
  
  public boolean getAvoidMoveToFront() {
    return this.mAvoidMoveToFront;
  }
  
  public int getCallerDisplayId() {
    return this.mCallerDisplayId;
  }
  
  public int getCustomEnterResId() {
    return this.mCustomEnterResId;
  }
  
  public int getCustomExitResId() {
    return this.mCustomExitResId;
  }
  
  public int getCustomInPlaceResId() {
    return this.mCustomInPlaceResId;
  }
  
  public int getExitCoordinatorKey() {
    return this.mExitCoordinatorIndex;
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public int getLaunchActivityType() {
    return this.mLaunchActivityType;
  }
  
  public Rect getLaunchBounds() {
    return this.mLaunchBounds;
  }
  
  public int getLaunchDisplayId() {
    return this.mLaunchDisplayId;
  }
  
  public boolean getLaunchTaskBehind() {
    boolean bool;
    if (this.mAnimationType == 7) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public WindowContainerToken getLaunchTaskDisplayArea() {
    return this.mLaunchTaskDisplayArea;
  }
  
  public int getLaunchTaskId() {
    return this.mLaunchTaskId;
  }
  
  public int getLaunchWindowingMode() {
    return this.mLaunchWindowingMode;
  }
  
  public boolean getLockTaskMode() {
    return this.mLockTaskMode;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int getPendingIntentLaunchFlags() {
    return this.mPendingIntentLaunchFlags;
  }
  
  public RemoteAnimationAdapter getRemoteAnimationAdapter() {
    return this.mRemoteAnimationAdapter;
  }
  
  public int getResultCode() {
    return this.mResultCode;
  }
  
  public Intent getResultData() {
    return this.mResultData;
  }
  
  public ResultReceiver getResultReceiver() {
    return this.mTransitionReceiver;
  }
  
  public int getRotationAnimationHint() {
    return this.mRotationAnimationHint;
  }
  
  public ArrayList<String> getSharedElementNames() {
    return this.mSharedElementNames;
  }
  
  public IAppTransitionAnimationSpecsFuture getSpecsFuture() {
    return this.mSpecsFuture;
  }
  
  public int getSplitScreenCreateMode() {
    return this.mSplitScreenCreateMode;
  }
  
  public int getStartX() {
    return this.mStartX;
  }
  
  public int getStartY() {
    return this.mStartY;
  }
  
  public boolean getTaskAlwaysOnTop() {
    return this.mTaskAlwaysOnTop;
  }
  
  public boolean getTaskOverlay() {
    return this.mTaskOverlay;
  }
  
  public GraphicBuffer getThumbnail() {
    Bitmap bitmap = this.mThumbnail;
    if (bitmap != null) {
      GraphicBuffer graphicBuffer = bitmap.createGraphicBufferHandle();
    } else {
      bitmap = null;
    } 
    return (GraphicBuffer)bitmap;
  }
  
  public PendingIntent getUsageTimeReport() {
    return this.mUsageTimeReport;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public boolean isApplyActivityFlagsForBubbles() {
    return this.mApplyActivityFlagsForBubbles;
  }
  
  boolean isCrossTask() {
    boolean bool;
    if (this.mExitCoordinatorIndex < 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isReturning() {
    return this.mIsReturning;
  }
  
  public Bundle popAppVerificationBundle() {
    Bundle bundle = this.mAppVerificationBundle;
    this.mAppVerificationBundle = null;
    return bundle;
  }
  
  public void requestUsageTimeReport(PendingIntent paramPendingIntent) {
    this.mUsageTimeReport = paramPendingIntent;
  }
  
  public ActivityOptions setAppVerificationBundle(Bundle paramBundle) {
    this.mAppVerificationBundle = paramBundle;
    return this;
  }
  
  public void setApplyActivityFlagsForBubbles(boolean paramBoolean) {
    this.mApplyActivityFlagsForBubbles = paramBoolean;
  }
  
  public void setAvoidMoveToFront() {
    this.mAvoidMoveToFront = true;
  }
  
  public ActivityOptions setCallerDisplayId(int paramInt) {
    this.mCallerDisplayId = paramInt;
    return this;
  }
  
  public void setDisallowEnterPictureInPictureWhileLaunching(boolean paramBoolean) {
    this.mDisallowEnterPictureInPictureWhileLaunching = paramBoolean;
  }
  
  public void setFreezeRecentTasksReordering() {
    this.mFreezeRecentTasksReordering = true;
  }
  
  public void setLaunchActivityType(int paramInt) {
    this.mLaunchActivityType = paramInt;
  }
  
  public ActivityOptions setLaunchBounds(Rect paramRect) {
    if (paramRect != null) {
      paramRect = new Rect(paramRect);
    } else {
      paramRect = null;
    } 
    this.mLaunchBounds = paramRect;
    return this;
  }
  
  public ActivityOptions setLaunchDisplayId(int paramInt) {
    this.mLaunchDisplayId = paramInt;
    return this;
  }
  
  public ActivityOptions setLaunchTaskDisplayArea(WindowContainerToken paramWindowContainerToken) {
    this.mLaunchTaskDisplayArea = paramWindowContainerToken;
    return this;
  }
  
  public void setLaunchTaskId(int paramInt) {
    this.mLaunchTaskId = paramInt;
  }
  
  public void setLaunchWindowingMode(int paramInt) {
    this.mLaunchWindowingMode = paramInt;
  }
  
  public ActivityOptions setLockTaskEnabled(boolean paramBoolean) {
    this.mLockTaskMode = paramBoolean;
    return this;
  }
  
  public void setPendingIntentLaunchFlags(int paramInt) {
    this.mPendingIntentLaunchFlags = paramInt;
  }
  
  public void setRemoteAnimationAdapter(RemoteAnimationAdapter paramRemoteAnimationAdapter) {
    this.mRemoteAnimationAdapter = paramRemoteAnimationAdapter;
  }
  
  public void setRotationAnimationHint(int paramInt) {
    this.mRotationAnimationHint = paramInt;
  }
  
  public void setSplitScreenCreateMode(int paramInt) {
    this.mSplitScreenCreateMode = paramInt;
  }
  
  public void setTaskAlwaysOnTop(boolean paramBoolean) {
    this.mTaskAlwaysOnTop = paramBoolean;
  }
  
  public void setTaskOverlay(boolean paramBoolean1, boolean paramBoolean2) {
    this.mTaskOverlay = paramBoolean1;
    this.mTaskOverlayCanResume = paramBoolean2;
  }
  
  public Bundle toBundle() {
    ResultReceiver resultReceiver;
    IBinder iBinder2;
    IRemoteCallback iRemoteCallback2;
    IBinder iBinder1;
    IRemoteCallback iRemoteCallback3;
    IRemoteCallback iRemoteCallback4;
    Bundle bundle1 = new Bundle();
    String str = this.mPackageName;
    if (str != null)
      bundle1.putString("android:activity.packageName", str); 
    Rect rect = this.mLaunchBounds;
    if (rect != null)
      bundle1.putParcelable("android:activity.launchBounds", (Parcelable)rect); 
    int i = this.mAnimationType;
    if (i != -1)
      bundle1.putInt("android:activity.animType", i); 
    PendingIntent pendingIntent = this.mUsageTimeReport;
    if (pendingIntent != null)
      bundle1.putParcelable("android:activity.usageTimeReport", pendingIntent); 
    i = this.mAnimationType;
    Bitmap bitmap = null;
    pendingIntent = null;
    switch (i) {
      case 10:
        bundle1.putInt("android:activity.animInPlaceRes", this.mCustomInPlaceResId);
        break;
      case 5:
        resultReceiver = this.mTransitionReceiver;
        if (resultReceiver != null)
          bundle1.putParcelable("android:activity.transitionCompleteListener", (Parcelable)resultReceiver); 
        bundle1.putBoolean("android:activity.transitionIsReturning", this.mIsReturning);
        bundle1.putStringArrayList("android:activity.sharedElementNames", this.mSharedElementNames);
        bundle1.putParcelable("android:activity.resultData", (Parcelable)this.mResultData);
        bundle1.putInt("android:activity.resultCode", this.mResultCode);
        bundle1.putInt("android:activity.exitCoordinatorIndex", this.mExitCoordinatorIndex);
        break;
      case 3:
      case 4:
      case 8:
      case 9:
        bitmap = this.mThumbnail;
        if (bitmap != null) {
          bitmap = bitmap.copy(Bitmap.Config.HARDWARE, false);
          if (bitmap != null) {
            bundle1.putParcelable("android:activity.animThumbnail", (Parcelable)bitmap.createGraphicBufferHandle());
          } else {
            Slog.w("ActivityOptions", "Failed to copy thumbnail");
          } 
        } 
        bundle1.putInt("android:activity.animStartX", this.mStartX);
        bundle1.putInt("android:activity.animStartY", this.mStartY);
        bundle1.putInt("android:activity.animWidth", this.mWidth);
        bundle1.putInt("android:activity.animHeight", this.mHeight);
        iRemoteCallback3 = this.mAnimationStartedListener;
        if (iRemoteCallback3 != null)
          iBinder2 = iRemoteCallback3.asBinder(); 
        bundle1.putBinder("android:activity.animStartListener", iBinder2);
        break;
      case 2:
      case 11:
        bundle1.putInt("android:activity.animStartX", this.mStartX);
        bundle1.putInt("android:activity.animStartY", this.mStartY);
        bundle1.putInt("android:activity.animWidth", this.mWidth);
        bundle1.putInt("android:activity.animHeight", this.mHeight);
        break;
      case 1:
        bundle1.putInt("android:activity.animEnterRes", this.mCustomEnterResId);
        bundle1.putInt("android:activity.animExitRes", this.mCustomExitResId);
        iRemoteCallback4 = this.mAnimationStartedListener;
        iRemoteCallback2 = iRemoteCallback3;
        if (iRemoteCallback4 != null)
          iBinder1 = iRemoteCallback4.asBinder(); 
        bundle1.putBinder("android:activity.animStartListener", iBinder1);
        break;
    } 
    boolean bool = this.mLockTaskMode;
    if (bool)
      bundle1.putBoolean("android:activity.lockTaskMode", bool); 
    i = this.mLaunchDisplayId;
    if (i != -1)
      bundle1.putInt("android.activity.launchDisplayId", i); 
    i = this.mCallerDisplayId;
    if (i != -1)
      bundle1.putInt("android.activity.callerDisplayId", i); 
    WindowContainerToken windowContainerToken = this.mLaunchTaskDisplayArea;
    if (windowContainerToken != null)
      bundle1.putParcelable("android.activity.launchTaskDisplayAreaToken", (Parcelable)windowContainerToken); 
    i = this.mLaunchWindowingMode;
    if (i != 0)
      bundle1.putInt("android.activity.windowingMode", i); 
    i = this.mLaunchActivityType;
    if (i != 0)
      bundle1.putInt("android.activity.activityType", i); 
    i = this.mLaunchTaskId;
    if (i != -1)
      bundle1.putInt("android.activity.launchTaskId", i); 
    i = this.mPendingIntentLaunchFlags;
    if (i != 0)
      bundle1.putInt("android.activity.pendingIntentLaunchFlags", i); 
    bool = this.mTaskAlwaysOnTop;
    if (bool)
      bundle1.putBoolean("android.activity.alwaysOnTop", bool); 
    bool = this.mTaskOverlay;
    if (bool)
      bundle1.putBoolean("android.activity.taskOverlay", bool); 
    bool = this.mTaskOverlayCanResume;
    if (bool)
      bundle1.putBoolean("android.activity.taskOverlayCanResume", bool); 
    bool = this.mAvoidMoveToFront;
    if (bool)
      bundle1.putBoolean("android.activity.avoidMoveToFront", bool); 
    bool = this.mFreezeRecentTasksReordering;
    if (bool)
      bundle1.putBoolean("android.activity.freezeRecentTasksReordering", bool); 
    i = this.mSplitScreenCreateMode;
    if (i != 0)
      bundle1.putInt("android:activity.splitScreenCreateMode", i); 
    bool = this.mDisallowEnterPictureInPictureWhileLaunching;
    if (bool)
      bundle1.putBoolean("android:activity.disallowEnterPictureInPictureWhileLaunching", bool); 
    bool = this.mApplyActivityFlagsForBubbles;
    if (bool)
      bundle1.putBoolean("android:activity.applyActivityFlagsForBubbles", bool); 
    AppTransitionAnimationSpec[] arrayOfAppTransitionAnimationSpec = this.mAnimSpecs;
    if (arrayOfAppTransitionAnimationSpec != null)
      bundle1.putParcelableArray("android:activity.animSpecs", (Parcelable[])arrayOfAppTransitionAnimationSpec); 
    IRemoteCallback iRemoteCallback1 = this.mAnimationFinishedListener;
    if (iRemoteCallback1 != null)
      bundle1.putBinder("android:activity.animationFinishedListener", iRemoteCallback1.asBinder()); 
    IAppTransitionAnimationSpecsFuture iAppTransitionAnimationSpecsFuture = this.mSpecsFuture;
    if (iAppTransitionAnimationSpecsFuture != null)
      bundle1.putBinder("android:activity.specsFuture", iAppTransitionAnimationSpecsFuture.asBinder()); 
    i = this.mRotationAnimationHint;
    if (i != -1)
      bundle1.putInt("android:activity.rotationAnimationHint", i); 
    Bundle bundle2 = this.mAppVerificationBundle;
    if (bundle2 != null)
      bundle1.putBundle("android:instantapps.installerbundle", bundle2); 
    RemoteAnimationAdapter remoteAnimationAdapter = this.mRemoteAnimationAdapter;
    if (remoteAnimationAdapter != null)
      bundle1.putParcelable("android:activity.remoteAnimationAdapter", (Parcelable)remoteAnimationAdapter); 
    return bundle1;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ActivityOptions(");
    stringBuilder.append(hashCode());
    stringBuilder.append("), mPackageName=");
    stringBuilder.append(this.mPackageName);
    stringBuilder.append(", mAnimationType=");
    stringBuilder.append(this.mAnimationType);
    stringBuilder.append(", mStartX=");
    stringBuilder.append(this.mStartX);
    stringBuilder.append(", mStartY=");
    stringBuilder.append(this.mStartY);
    stringBuilder.append(", mWidth=");
    stringBuilder.append(this.mWidth);
    stringBuilder.append(", mHeight=");
    stringBuilder.append(this.mHeight);
    return stringBuilder.toString();
  }
  
  public void update(ActivityOptions paramActivityOptions) {
    IRemoteCallback iRemoteCallback;
    String str = paramActivityOptions.mPackageName;
    if (str != null)
      this.mPackageName = str; 
    this.mUsageTimeReport = paramActivityOptions.mUsageTimeReport;
    this.mTransitionReceiver = null;
    this.mSharedElementNames = null;
    this.mIsReturning = false;
    this.mResultData = null;
    this.mResultCode = 0;
    this.mExitCoordinatorIndex = 0;
    this.mAnimationType = paramActivityOptions.mAnimationType;
    switch (paramActivityOptions.mAnimationType) {
      case 10:
        this.mCustomInPlaceResId = paramActivityOptions.mCustomInPlaceResId;
        break;
      case 5:
        this.mTransitionReceiver = paramActivityOptions.mTransitionReceiver;
        this.mSharedElementNames = paramActivityOptions.mSharedElementNames;
        this.mIsReturning = paramActivityOptions.mIsReturning;
        this.mThumbnail = null;
        this.mAnimationStartedListener = null;
        this.mResultData = paramActivityOptions.mResultData;
        this.mResultCode = paramActivityOptions.mResultCode;
        this.mExitCoordinatorIndex = paramActivityOptions.mExitCoordinatorIndex;
        break;
      case 3:
      case 4:
      case 8:
      case 9:
        this.mThumbnail = paramActivityOptions.mThumbnail;
        this.mStartX = paramActivityOptions.mStartX;
        this.mStartY = paramActivityOptions.mStartY;
        this.mWidth = paramActivityOptions.mWidth;
        this.mHeight = paramActivityOptions.mHeight;
        iRemoteCallback = this.mAnimationStartedListener;
        if (iRemoteCallback != null)
          try {
            iRemoteCallback.sendResult(null);
          } catch (RemoteException remoteException) {} 
        this.mAnimationStartedListener = paramActivityOptions.mAnimationStartedListener;
        break;
      case 2:
        this.mStartX = paramActivityOptions.mStartX;
        this.mStartY = paramActivityOptions.mStartY;
        this.mWidth = paramActivityOptions.mWidth;
        this.mHeight = paramActivityOptions.mHeight;
        iRemoteCallback = this.mAnimationStartedListener;
        if (iRemoteCallback != null)
          try {
            iRemoteCallback.sendResult(null);
          } catch (RemoteException remoteException) {} 
        this.mAnimationStartedListener = null;
        break;
      case 1:
        this.mCustomEnterResId = paramActivityOptions.mCustomEnterResId;
        this.mCustomExitResId = paramActivityOptions.mCustomExitResId;
        this.mThumbnail = null;
        iRemoteCallback = this.mAnimationStartedListener;
        if (iRemoteCallback != null)
          try {
            iRemoteCallback.sendResult(null);
          } catch (RemoteException remoteException) {} 
        this.mAnimationStartedListener = paramActivityOptions.mAnimationStartedListener;
        break;
    } 
    this.mLockTaskMode = paramActivityOptions.mLockTaskMode;
    this.mAnimSpecs = paramActivityOptions.mAnimSpecs;
    this.mAnimationFinishedListener = paramActivityOptions.mAnimationFinishedListener;
    this.mSpecsFuture = paramActivityOptions.mSpecsFuture;
    this.mRemoteAnimationAdapter = paramActivityOptions.mRemoteAnimationAdapter;
  }
  
  private static class HideWindowListener extends TransitionListenerAdapter implements ExitTransitionCoordinator.HideSharedElementsCallback {
    private final ExitTransitionCoordinator mExit;
    
    private boolean mSharedElementHidden;
    
    private ArrayList<View> mSharedElements;
    
    private boolean mTransitionEnded;
    
    private final boolean mWaitingForTransition;
    
    private final Window mWindow;
    
    public HideWindowListener(Window param1Window, ExitTransitionCoordinator param1ExitTransitionCoordinator) {
      this.mWindow = param1Window;
      this.mExit = param1ExitTransitionCoordinator;
      this.mSharedElements = new ArrayList<>(param1ExitTransitionCoordinator.mSharedElements);
      Transition transition = this.mWindow.getExitTransition();
      if (transition != null) {
        transition.addListener((Transition.TransitionListener)this);
        this.mWaitingForTransition = true;
      } else {
        this.mWaitingForTransition = false;
      } 
      View view = this.mWindow.getDecorView();
      if (view != null)
        if (view.getTag(16908897) == null) {
          view.setTagInternal(16908897, param1ExitTransitionCoordinator);
        } else {
          throw new IllegalStateException("Cannot start a transition while one is running");
        }  
    }
    
    private void hideWhenDone() {
      if (this.mSharedElementHidden && (!this.mWaitingForTransition || this.mTransitionEnded)) {
        this.mExit.resetViews();
        int i = this.mSharedElements.size();
        for (byte b = 0; b < i; b++)
          ((View)this.mSharedElements.get(b)).requestLayout(); 
        View view = this.mWindow.getDecorView();
        if (view != null) {
          view.setTagInternal(16908897, null);
          view.setVisibility(8);
        } 
      } 
    }
    
    public void hideSharedElements() {
      this.mSharedElementHidden = true;
      hideWhenDone();
    }
    
    public void onTransitionEnd(Transition param1Transition) {
      this.mTransitionEnded = true;
      hideWhenDone();
      param1Transition.removeListener((Transition.TransitionListener)this);
    }
  }
  
  public static interface OnAnimationFinishedListener {
    void onAnimationFinished();
  }
  
  public static interface OnAnimationStartedListener {
    void onAnimationStarted();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */