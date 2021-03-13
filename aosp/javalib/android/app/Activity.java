package android.app;

import android.annotation.SystemApi;
import android.app.assist.AssistContent;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.IntentSender;
import android.content.LocusId;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.GraphicsEnvironment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.StrictMode;
import android.os.Trace;
import android.os.UserHandle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.TextKeyListener;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.EventLog;
import android.util.Log;
import android.util.SparseArray;
import android.util.SuperNotCalledException;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.KeyboardShortcutInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.RemoteAnimationDefinition;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityEvent;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillPopupWindow;
import android.view.autofill.Helper;
import android.view.autofill.IAutofillWindowPresenter;
import android.view.contentcapture.ContentCaptureContext;
import android.view.contentcapture.ContentCaptureManager;
import android.widget.Toast;
import android.widget.Toolbar;
import com.android.internal.R;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.app.ToolbarActionBar;
import com.android.internal.app.WindowDecorActionBar;
import com.android.internal.policy.PhoneWindow;
import dalvik.system.VMRuntime;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Activity extends ContextThemeWrapper implements LayoutInflater.Factory2, Window.Callback, KeyEvent.Callback, View.OnCreateContextMenuListener, ComponentCallbacks2, Window.OnWindowDismissedCallback, AutofillManager.AutofillClient, ContentCaptureManager.ContentCaptureClient {
  private static final String AUTOFILL_RESET_NEEDED = "@android:autofillResetNeeded";
  
  private static final String AUTO_FILL_AUTH_WHO_PREFIX = "@android:autoFillAuth:";
  
  private static final int CONTENT_CAPTURE_PAUSE = 3;
  
  private static final int CONTENT_CAPTURE_RESUME = 2;
  
  private static final int CONTENT_CAPTURE_START = 1;
  
  private static final int CONTENT_CAPTURE_STOP = 4;
  
  private static final boolean DEBUG_LIFECYCLE = false;
  
  public static final int DEFAULT_KEYS_DIALER = 1;
  
  public static final int DEFAULT_KEYS_DISABLE = 0;
  
  public static final int DEFAULT_KEYS_SEARCH_GLOBAL = 4;
  
  public static final int DEFAULT_KEYS_SEARCH_LOCAL = 3;
  
  public static final int DEFAULT_KEYS_SHORTCUT = 2;
  
  public static final int DONT_FINISH_TASK_WITH_ACTIVITY = 0;
  
  public static final int FINISH_TASK_WITH_ACTIVITY = 2;
  
  public static final int FINISH_TASK_WITH_ROOT_ACTIVITY = 1;
  
  protected static final int[] FOCUSED_STATE_SET = new int[] { 16842908 };
  
  static final String FRAGMENTS_TAG = "android:fragments";
  
  private static final String HAS_CURENT_PERMISSIONS_REQUEST_KEY = "android:hasCurrentPermissionsRequest";
  
  private static final String KEYBOARD_SHORTCUTS_RECEIVER_PKG_NAME = "com.android.systemui";
  
  private static final String LAST_AUTOFILL_ID = "android:lastAutofillId";
  
  private static final int LOG_AM_ON_ACTIVITY_RESULT_CALLED = 30062;
  
  private static final int LOG_AM_ON_CREATE_CALLED = 30057;
  
  private static final int LOG_AM_ON_DESTROY_CALLED = 30060;
  
  private static final int LOG_AM_ON_PAUSE_CALLED = 30021;
  
  private static final int LOG_AM_ON_RESTART_CALLED = 30058;
  
  private static final int LOG_AM_ON_RESUME_CALLED = 30022;
  
  private static final int LOG_AM_ON_START_CALLED = 30059;
  
  private static final int LOG_AM_ON_STOP_CALLED = 30049;
  
  private static final int LOG_AM_ON_TOP_RESUMED_GAINED_CALLED = 30064;
  
  private static final int LOG_AM_ON_TOP_RESUMED_LOST_CALLED = 30065;
  
  private static final String REQUEST_PERMISSIONS_WHO_PREFIX = "@android:requestPermissions:";
  
  public static final int RESULT_CANCELED = 0;
  
  public static final int RESULT_FIRST_USER = 1;
  
  public static final int RESULT_OK = -1;
  
  private static final String SAVED_DIALOGS_TAG = "android:savedDialogs";
  
  private static final String SAVED_DIALOG_ARGS_KEY_PREFIX = "android:dialog_args_";
  
  private static final String SAVED_DIALOG_IDS_KEY = "android:savedDialogIds";
  
  private static final String SAVED_DIALOG_KEY_PREFIX = "android:dialog_";
  
  private static final String TAG = "Activity";
  
  private static final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
  
  ActionBar mActionBar = null;
  
  private int mActionModeTypeStarting = 0;
  
  ActivityInfo mActivityInfo;
  
  private final ArrayList<Application.ActivityLifecycleCallbacks> mActivityLifecycleCallbacks = new ArrayList<>();
  
  ActivityTransitionState mActivityTransitionState = new ActivityTransitionState();
  
  private Application mApplication;
  
  private IBinder mAssistToken;
  
  private boolean mAutoFillIgnoreFirstResumePause;
  
  private boolean mAutoFillResetNeeded;
  
  private AutofillManager mAutofillManager;
  
  private AutofillPopupWindow mAutofillPopupWindow;
  
  boolean mCalled;
  
  private boolean mCanEnterPictureInPicture = false;
  
  private boolean mChangeCanvasToTranslucent;
  
  boolean mChangingConfigurations = false;
  
  private ComponentName mComponent;
  
  int mConfigChangeFlags;
  
  private ContentCaptureManager mContentCaptureManager;
  
  Configuration mCurrentConfig;
  
  View mDecor = null;
  
  private int mDefaultKeyMode = 0;
  
  private SpannableStringBuilder mDefaultKeySsb = null;
  
  private boolean mDestroyed;
  
  private boolean mDoReportFullyDrawn = true;
  
  String mEmbeddedID;
  
  private boolean mEnableDefaultActionBarUp;
  
  boolean mEnterAnimationComplete;
  
  SharedElementCallback mEnterTransitionListener = SharedElementCallback.NULL_CALLBACK;
  
  SharedElementCallback mExitTransitionListener = SharedElementCallback.NULL_CALLBACK;
  
  boolean mFinished;
  
  final FragmentController mFragments = FragmentController.createController(new HostCallbacks());
  
  final Handler mHandler = new Handler();
  
  private boolean mHasCurrentPermissionsRequest;
  
  private int mIdent;
  
  private final Object mInstanceTracker = StrictMode.trackActivity(this);
  
  private Instrumentation mInstrumentation;
  
  Intent mIntent;
  
  private boolean mIsInMultiWindowMode;
  
  private boolean mIsInPictureInPictureMode;
  
  private int mLastAutofillId = 1073741823;
  
  NonConfigurationInstances mLastNonConfigurationInstances;
  
  ActivityThread mMainThread;
  
  private final ArrayList<ManagedCursor> mManagedCursors = new ArrayList<>();
  
  private SparseArray<ManagedDialog> mManagedDialogs;
  
  private MenuInflater mMenuInflater;
  
  Activity mParent;
  
  String mReferrer;
  
  private boolean mRestoredFromBundle;
  
  int mResultCode = 0;
  
  Intent mResultData = null;
  
  boolean mResumed;
  
  private SearchEvent mSearchEvent;
  
  private SearchManager mSearchManager;
  
  boolean mStartedActivity;
  
  boolean mStopped;
  
  private ActivityManager.TaskDescription mTaskDescription = new ActivityManager.TaskDescription();
  
  private CharSequence mTitle;
  
  private int mTitleColor = 0;
  
  private boolean mTitleReady = false;
  
  private IBinder mToken;
  
  private TranslucentConversionListener mTranslucentCallback;
  
  private Thread mUiThread;
  
  boolean mVisibleFromClient = true;
  
  boolean mVisibleFromServer = false;
  
  VoiceInteractor mVoiceInteractor;
  
  private Window mWindow;
  
  boolean mWindowAdded = false;
  
  private final Window.WindowControllerCallback mWindowControllerCallback = new Window.WindowControllerCallback() {
      public void enterPictureInPictureModeIfPossible() {
        if (Activity.this.mActivityInfo.supportsPictureInPicture())
          Activity.this.enterPictureInPictureMode(); 
      }
      
      public boolean isTaskRoot() {
        boolean bool = false;
        try {
          int i = ActivityTaskManager.getService().getTaskForActivity(Activity.this.mToken, true);
          if (i >= 0)
            bool = true; 
          return bool;
        } catch (RemoteException remoteException) {
          return false;
        } 
      }
      
      public void toggleFreeformWindowingMode() throws RemoteException {
        ActivityTaskManager.getService().toggleFreeformWindowingMode(Activity.this.mToken);
      }
      
      public void updateNavigationBarColor(int param1Int) {
        Activity.this.mTaskDescription.setNavigationBarColor(param1Int);
        Activity activity = Activity.this;
        activity.setTaskDescription(activity.mTaskDescription);
      }
      
      public void updateStatusBarColor(int param1Int) {
        Activity.this.mTaskDescription.setStatusBarColor(param1Int);
        Activity activity = Activity.this;
        activity.setTaskDescription(activity.mTaskDescription);
      }
    };
  
  private WindowManager mWindowManager;
  
  private void cancelInputsAndStartExitTransition(Bundle paramBundle) {
    Window window = this.mWindow;
    if (window != null) {
      View view = window.peekDecorView();
    } else {
      window = null;
    } 
    if (window != null)
      window.cancelPendingInputEvents(); 
    if (paramBundle != null)
      this.mActivityTransitionState.startExitOutTransition(this, paramBundle); 
  }
  
  private Object[] collectActivityLifecycleCallbacks() {
    null = null;
    synchronized (this.mActivityLifecycleCallbacks) {
      if (this.mActivityLifecycleCallbacks.size() > 0)
        null = this.mActivityLifecycleCallbacks.toArray(); 
      return null;
    } 
  }
  
  private boolean convertFromTranslucentInternal() {
    try {
      this.mTranslucentCallback = null;
      if (ActivityTaskManager.getService().convertFromTranslucent(this.mToken)) {
        WindowManagerGlobal.getInstance().changeCanvasOpacity(this.mToken, true);
        return true;
      } 
    } catch (RemoteException remoteException) {}
    return false;
  }
  
  private Dialog createDialog(Integer paramInteger, Bundle paramBundle1, Bundle paramBundle2) {
    Dialog dialog = onCreateDialog(paramInteger.intValue(), paramBundle2);
    if (dialog == null)
      return null; 
    dialog.dispatchOnCreate(paramBundle1);
    return dialog;
  }
  
  private boolean deviceSupportsPictureInPictureMode() {
    return getPackageManager().hasSystemFeature("android.software.picture_in_picture");
  }
  
  private void dispatchActivityCreated(Bundle paramBundle) {
    getApplication().dispatchActivityCreated(this, paramBundle);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityCreated(this, paramBundle);  
  }
  
  private void dispatchActivityDestroyed() {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityDestroyed(this);  
    getApplication().dispatchActivityDestroyed(this);
  }
  
  private void dispatchActivityPaused() {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPaused(this);  
    getApplication().dispatchActivityPaused(this);
  }
  
  private void dispatchActivityPostCreated(Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostCreated(this, paramBundle);  
    getApplication().dispatchActivityPostCreated(this, paramBundle);
  }
  
  private void dispatchActivityPostDestroyed() {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPostDestroyed(this);  
    getApplication().dispatchActivityPostDestroyed(this);
  }
  
  private void dispatchActivityPostPaused() {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPostPaused(this);  
    getApplication().dispatchActivityPostPaused(this);
  }
  
  private void dispatchActivityPostResumed() {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostResumed(this);  
    getApplication().dispatchActivityPostResumed(this);
  }
  
  private void dispatchActivityPostSaveInstanceState(Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPostSaveInstanceState(this, paramBundle);  
    getApplication().dispatchActivityPostSaveInstanceState(this, paramBundle);
  }
  
  private void dispatchActivityPostStarted() {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPostStarted(this);  
    getApplication().dispatchActivityPostStarted(this);
  }
  
  private void dispatchActivityPostStopped() {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPostStopped(this);  
    getApplication().dispatchActivityPostStopped(this);
  }
  
  private void dispatchActivityPreCreated(Bundle paramBundle) {
    getApplication().dispatchActivityPreCreated(this, paramBundle);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreCreated(this, paramBundle);  
  }
  
  private void dispatchActivityPreDestroyed() {
    getApplication().dispatchActivityPreDestroyed(this);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPreDestroyed(this);  
  }
  
  private void dispatchActivityPrePaused() {
    getApplication().dispatchActivityPrePaused(this);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPrePaused(this);  
  }
  
  private void dispatchActivityPreResumed() {
    getApplication().dispatchActivityPreResumed(this);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreResumed(this);  
  }
  
  private void dispatchActivityPreSaveInstanceState(Bundle paramBundle) {
    getApplication().dispatchActivityPreSaveInstanceState(this, paramBundle);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPreSaveInstanceState(this, paramBundle);  
  }
  
  private void dispatchActivityPreStarted() {
    getApplication().dispatchActivityPreStarted(this);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityPreStarted(this);  
  }
  
  private void dispatchActivityPreStopped() {
    getApplication().dispatchActivityPreStopped(this);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityPreStopped(this);  
  }
  
  private void dispatchActivityResumed() {
    getApplication().dispatchActivityResumed(this);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityResumed(this);  
  }
  
  private void dispatchActivitySaveInstanceState(Bundle paramBundle) {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivitySaveInstanceState(this, paramBundle);  
    getApplication().dispatchActivitySaveInstanceState(this, paramBundle);
  }
  
  private void dispatchActivityStarted() {
    getApplication().dispatchActivityStarted(this);
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (byte b = 0; b < arrayOfObject.length; b++)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[b]).onActivityStarted(this);  
  }
  
  private void dispatchActivityStopped() {
    Object[] arrayOfObject = collectActivityLifecycleCallbacks();
    if (arrayOfObject != null)
      for (int i = arrayOfObject.length - 1; i >= 0; i--)
        ((Application.ActivityLifecycleCallbacks)arrayOfObject[i]).onActivityStopped(this);  
    getApplication().dispatchActivityStopped(this);
  }
  
  private void dispatchRequestPermissionsResult(int paramInt, Intent paramIntent) {
    int[] arrayOfInt;
    String[] arrayOfString;
    this.mHasCurrentPermissionsRequest = false;
    if (paramIntent != null) {
      arrayOfString = paramIntent.getStringArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_NAMES");
    } else {
      arrayOfString = new String[0];
    } 
    if (paramIntent != null) {
      arrayOfInt = paramIntent.getIntArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS");
    } else {
      arrayOfInt = new int[0];
    } 
    onRequestPermissionsResult(paramInt, arrayOfString, arrayOfInt);
  }
  
  private void dispatchRequestPermissionsResultToFragment(int paramInt, Intent paramIntent, Fragment paramFragment) {
    int[] arrayOfInt;
    String[] arrayOfString;
    if (paramIntent != null) {
      arrayOfString = paramIntent.getStringArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_NAMES");
    } else {
      arrayOfString = new String[0];
    } 
    if (paramIntent != null) {
      arrayOfInt = paramIntent.getIntArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS");
    } else {
      arrayOfInt = new int[0];
    } 
    paramFragment.onRequestPermissionsResult(paramInt, arrayOfString, arrayOfInt);
  }
  
  private void enableAutofillCompatibilityIfNeeded() {
    if (isAutofillCompatibilityEnabled()) {
      AutofillManager autofillManager = (AutofillManager)getSystemService(AutofillManager.class);
      if (autofillManager != null)
        autofillManager.enableCompatibilityMode(); 
    } 
  }
  
  private void ensureSearchManager() {
    if (this.mSearchManager != null)
      return; 
    try {
      SearchManager searchManager = new SearchManager();
      this((Context)this, null);
      this.mSearchManager = searchManager;
      return;
    } catch (android.os.ServiceManager.ServiceNotFoundException serviceNotFoundException) {
      throw new IllegalStateException(serviceNotFoundException);
    } 
  }
  
  private void finish(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mParent : Landroid/app/Activity;
    //   4: astore_2
    //   5: aload_2
    //   6: ifnonnull -> 67
    //   9: aload_0
    //   10: monitorenter
    //   11: aload_0
    //   12: getfield mResultCode : I
    //   15: istore_3
    //   16: aload_0
    //   17: getfield mResultData : Landroid/content/Intent;
    //   20: astore_2
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_2
    //   24: ifnull -> 32
    //   27: aload_2
    //   28: aload_0
    //   29: invokevirtual prepareToLeaveProcess : (Landroid/content/Context;)V
    //   32: invokestatic getService : ()Landroid/app/IActivityTaskManager;
    //   35: aload_0
    //   36: getfield mToken : Landroid/os/IBinder;
    //   39: iload_3
    //   40: aload_2
    //   41: iload_1
    //   42: invokeinterface finishActivity : (Landroid/os/IBinder;ILandroid/content/Intent;I)Z
    //   47: ifeq -> 55
    //   50: aload_0
    //   51: iconst_1
    //   52: putfield mFinished : Z
    //   55: goto -> 59
    //   58: astore_2
    //   59: goto -> 72
    //   62: astore_2
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_2
    //   66: athrow
    //   67: aload_2
    //   68: aload_0
    //   69: invokevirtual finishFromChild : (Landroid/app/Activity;)V
    //   72: aload_0
    //   73: getfield mIntent : Landroid/content/Intent;
    //   76: astore_2
    //   77: aload_2
    //   78: ifnull -> 95
    //   81: aload_2
    //   82: ldc_w 'android.view.autofill.extra.RESTORE_SESSION_TOKEN'
    //   85: invokevirtual hasExtra : (Ljava/lang/String;)Z
    //   88: ifeq -> 95
    //   91: aload_0
    //   92: invokespecial restoreAutofillSaveUi : ()V
    //   95: return
    // Exception table:
    //   from	to	target	type
    //   11	23	62	finally
    //   27	32	58	android/os/RemoteException
    //   32	55	58	android/os/RemoteException
    //   63	65	62	finally
  }
  
  private AutofillManager getAutofillManager() {
    if (this.mAutofillManager == null)
      this.mAutofillManager = (AutofillManager)getSystemService(AutofillManager.class); 
    return this.mAutofillManager;
  }
  
  private ContentCaptureManager getContentCaptureManager() {
    if (!UserHandle.isApp(Process.myUid()))
      return null; 
    if (this.mContentCaptureManager == null)
      this.mContentCaptureManager = (ContentCaptureManager)getSystemService(ContentCaptureManager.class); 
    return this.mContentCaptureManager;
  }
  
  private String getContentCaptureTypeAsString(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          if (paramInt != 4) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UNKNOW-");
            stringBuilder.append(paramInt);
            return stringBuilder.toString();
          } 
          return "STOP";
        } 
        return "PAUSE";
      } 
      return "RESUME";
    } 
    return "START";
  }
  
  private static native String getDlWarning();
  
  private void initWindowDecorActionBar() {
    Window window = getWindow();
    window.getDecorView();
    if (isChild() || !window.hasFeature(8) || this.mActionBar != null)
      return; 
    WindowDecorActionBar windowDecorActionBar = new WindowDecorActionBar(this);
    this.mActionBar = (ActionBar)windowDecorActionBar;
    windowDecorActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
    this.mWindow.setDefaultIcon(this.mActivityInfo.getIconResource());
    this.mWindow.setDefaultLogo(this.mActivityInfo.getLogoResource());
  }
  
  private IllegalArgumentException missingDialog(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("no dialog with id ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" was ever shown via Activity#showDialog");
    return new IllegalArgumentException(stringBuilder.toString());
  }
  
  private void notifyContentCaptureManagerIfNeeded(int paramInt) {
    if (Trace.isTagEnabled(64L)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("notifyContentCapture(");
      stringBuilder.append(getContentCaptureTypeAsString(paramInt));
      stringBuilder.append(") for ");
      stringBuilder.append(this.mComponent.toShortString());
      Trace.traceBegin(64L, stringBuilder.toString());
    } 
    try {
      ContentCaptureManager contentCaptureManager = getContentCaptureManager();
      if (contentCaptureManager == null)
        return; 
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt != 4) {
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("Invalid @ContentCaptureNotificationType: ");
              stringBuilder.append(paramInt);
              Log.wtf("Activity", stringBuilder.toString());
            } else {
              contentCaptureManager.onActivityDestroyed();
            } 
          } else {
            contentCaptureManager.onActivityPaused();
          } 
        } else {
          contentCaptureManager.onActivityResumed();
        } 
      } else {
        Window window = getWindow();
        if (window != null)
          contentCaptureManager.updateWindowAttributes(window.getAttributes()); 
        contentCaptureManager.onActivityCreated(this.mToken, getComponentName());
      } 
      return;
    } finally {
      Trace.traceEnd(64L);
    } 
  }
  
  private void restoreAutofillSaveUi() {
    IBinder iBinder = this.mIntent.getIBinderExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN");
    this.mIntent.removeExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN");
    this.mIntent.removeExtra("android.view.autofill.extra.RESTORE_CROSS_ACTIVITY");
    getAutofillManager().onPendingSaveUi(2, iBinder);
  }
  
  private void restoreHasCurrentPermissionRequest(Bundle paramBundle) {
    if (paramBundle != null)
      this.mHasCurrentPermissionsRequest = paramBundle.getBoolean("android:hasCurrentPermissionsRequest", false); 
  }
  
  private void restoreManagedDialogs(Bundle paramBundle) {
    Bundle bundle = paramBundle.getBundle("android:savedDialogs");
    if (bundle == null)
      return; 
    int[] arrayOfInt = bundle.getIntArray("android:savedDialogIds");
    int i = arrayOfInt.length;
    this.mManagedDialogs = new SparseArray(i);
    for (byte b = 0; b < i; b++) {
      Integer integer = Integer.valueOf(arrayOfInt[b]);
      Bundle bundle1 = bundle.getBundle(savedDialogKeyFor(integer.intValue()));
      if (bundle1 != null) {
        ManagedDialog managedDialog = new ManagedDialog();
        managedDialog.mArgs = bundle.getBundle(savedDialogArgsKeyFor(integer.intValue()));
        managedDialog.mDialog = createDialog(integer, bundle1, managedDialog.mArgs);
        if (managedDialog.mDialog != null) {
          this.mManagedDialogs.put(integer.intValue(), managedDialog);
          onPrepareDialog(integer.intValue(), managedDialog.mDialog, managedDialog.mArgs);
          managedDialog.mDialog.onRestoreInstanceState(bundle1);
        } 
      } 
    } 
  }
  
  private void saveManagedDialogs(Bundle paramBundle) {
    SparseArray<ManagedDialog> sparseArray = this.mManagedDialogs;
    if (sparseArray == null)
      return; 
    int i = sparseArray.size();
    if (i == 0)
      return; 
    Bundle bundle = new Bundle();
    int[] arrayOfInt = new int[this.mManagedDialogs.size()];
    for (byte b = 0; b < i; b++) {
      int j = this.mManagedDialogs.keyAt(b);
      arrayOfInt[b] = j;
      ManagedDialog managedDialog = (ManagedDialog)this.mManagedDialogs.valueAt(b);
      bundle.putBundle(savedDialogKeyFor(j), managedDialog.mDialog.onSaveInstanceState());
      if (managedDialog.mArgs != null)
        bundle.putBundle(savedDialogArgsKeyFor(j), managedDialog.mArgs); 
    } 
    bundle.putIntArray("android:savedDialogIds", arrayOfInt);
    paramBundle.putBundle("android:savedDialogs", bundle);
  }
  
  private static String savedDialogArgsKeyFor(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("android:dialog_args_");
    stringBuilder.append(paramInt);
    return stringBuilder.toString();
  }
  
  private static String savedDialogKeyFor(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("android:dialog_");
    stringBuilder.append(paramInt);
    return stringBuilder.toString();
  }
  
  private void setLocusContextToContentCapture(LocusId paramLocusId, Bundle paramBundle) {
    ContentCaptureManager contentCaptureManager = getContentCaptureManager();
    if (contentCaptureManager == null)
      return; 
    ContentCaptureContext.Builder builder = new ContentCaptureContext.Builder(paramLocusId);
    if (paramBundle != null)
      builder.setExtras(paramBundle); 
    contentCaptureManager.getMainContentCaptureSession().setContentCaptureContext(builder.build());
  }
  
  private void startActivityAsUserFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle, UserHandle paramUserHandle) {
    startActivityForResultAsUser(paramIntent, paramFragment.mWho, paramInt, paramBundle, paramUserHandle);
  }
  
  private void startIntentSenderForResultInner(IntentSender paramIntentSender, String paramString, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, Bundle paramBundle) throws IntentSender.SendIntentException {
    // Byte code:
    //   0: aload_0
    //   1: aload #7
    //   3: invokespecial transferSpringboardActivityOptions : (Landroid/os/Bundle;)Landroid/os/Bundle;
    //   6: astore #8
    //   8: aconst_null
    //   9: astore #7
    //   11: aload #4
    //   13: ifnull -> 40
    //   16: aload #4
    //   18: aload_0
    //   19: invokevirtual migrateExtraStreamToClipData : (Landroid/content/Context;)Z
    //   22: pop
    //   23: aload #4
    //   25: aload_0
    //   26: invokevirtual prepareToLeaveProcess : (Landroid/content/Context;)V
    //   29: aload #4
    //   31: aload_0
    //   32: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   35: invokevirtual resolveTypeIfNeeded : (Landroid/content/ContentResolver;)Ljava/lang/String;
    //   38: astore #7
    //   40: invokestatic getService : ()Landroid/app/IActivityTaskManager;
    //   43: astore #9
    //   45: aload_0
    //   46: getfield mMainThread : Landroid/app/ActivityThread;
    //   49: invokevirtual getApplicationThread : ()Landroid/app/ActivityThread$ApplicationThread;
    //   52: astore #10
    //   54: aload_1
    //   55: ifnull -> 67
    //   58: aload_1
    //   59: invokevirtual getTarget : ()Landroid/content/IIntentSender;
    //   62: astore #11
    //   64: goto -> 70
    //   67: aconst_null
    //   68: astore #11
    //   70: aload_1
    //   71: ifnull -> 82
    //   74: aload_1
    //   75: invokevirtual getWhitelistToken : ()Landroid/os/IBinder;
    //   78: astore_1
    //   79: goto -> 84
    //   82: aconst_null
    //   83: astore_1
    //   84: aload #9
    //   86: aload #10
    //   88: aload #11
    //   90: aload_1
    //   91: aload #4
    //   93: aload #7
    //   95: aload_0
    //   96: getfield mToken : Landroid/os/IBinder;
    //   99: aload_2
    //   100: iload_3
    //   101: iload #5
    //   103: iload #6
    //   105: aload #8
    //   107: invokeinterface startActivityIntentSender : (Landroid/app/IApplicationThread;Landroid/content/IIntentSender;Landroid/os/IBinder;Landroid/content/Intent;Ljava/lang/String;Landroid/os/IBinder;Ljava/lang/String;IIILandroid/os/Bundle;)I
    //   112: istore #5
    //   114: iload #5
    //   116: bipush #-96
    //   118: if_icmpeq -> 141
    //   121: iload #5
    //   123: aconst_null
    //   124: invokestatic checkStartActivityResult : (ILjava/lang/Object;)V
    //   127: aload #8
    //   129: ifnull -> 138
    //   132: aload_0
    //   133: aload #8
    //   135: invokespecial cancelInputsAndStartExitTransition : (Landroid/os/Bundle;)V
    //   138: goto -> 156
    //   141: new android/content/IntentSender$SendIntentException
    //   144: astore_1
    //   145: aload_1
    //   146: invokespecial <init> : ()V
    //   149: aload_1
    //   150: athrow
    //   151: astore_1
    //   152: goto -> 156
    //   155: astore_1
    //   156: iload_3
    //   157: iflt -> 165
    //   160: aload_0
    //   161: iconst_1
    //   162: putfield mStartedActivity : Z
    //   165: return
    // Exception table:
    //   from	to	target	type
    //   0	8	155	android/os/RemoteException
    //   16	40	151	android/os/RemoteException
    //   40	54	151	android/os/RemoteException
    //   58	64	151	android/os/RemoteException
    //   74	79	151	android/os/RemoteException
    //   84	114	151	android/os/RemoteException
    //   121	127	151	android/os/RemoteException
    //   132	138	151	android/os/RemoteException
    //   141	151	151	android/os/RemoteException
  }
  
  private void startIntentSenderFromFragment(Fragment paramFragment, IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, Bundle paramBundle) throws IntentSender.SendIntentException {
    startIntentSenderForResultInner(paramIntentSender, paramFragment.mWho, paramInt1, paramIntent, paramInt2, paramInt3, paramBundle);
  }
  
  private void storeHasCurrentPermissionRequest(Bundle paramBundle) {
    if (paramBundle != null && this.mHasCurrentPermissionsRequest)
      paramBundle.putBoolean("android:hasCurrentPermissionsRequest", true); 
  }
  
  private Bundle transferSpringboardActivityOptions(Bundle paramBundle) {
    if (paramBundle == null) {
      Window window = this.mWindow;
      if (window != null && !window.isActive()) {
        ActivityOptions activityOptions = getActivityOptions();
        if (activityOptions != null && activityOptions.getAnimationType() == 5)
          return activityOptions.toBundle(); 
      } 
    } 
    return paramBundle;
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    getWindow().addContentView(paramView, paramLayoutParams);
    initWindowDecorActionBar();
  }
  
  final void attach(Context paramContext, ActivityThread paramActivityThread, Instrumentation paramInstrumentation, IBinder paramIBinder1, int paramInt, Application paramApplication, Intent paramIntent, ActivityInfo paramActivityInfo, CharSequence paramCharSequence, Activity paramActivity, String paramString1, NonConfigurationInstances paramNonConfigurationInstances, Configuration paramConfiguration, String paramString2, IVoiceInteractor paramIVoiceInteractor, Window paramWindow, ViewRootImpl.ActivityConfigCallback paramActivityConfigCallback, IBinder paramIBinder2) {
    boolean bool;
    attachBaseContext(paramContext);
    this.mFragments.attachHost(null);
    PhoneWindow phoneWindow = new PhoneWindow((Context)this, paramWindow, paramActivityConfigCallback);
    this.mWindow = (Window)phoneWindow;
    phoneWindow.setWindowControllerCallback(this.mWindowControllerCallback);
    this.mWindow.setCallback(this);
    this.mWindow.setOnWindowDismissedCallback(this);
    this.mWindow.getLayoutInflater().setPrivateFactory(this);
    if (paramActivityInfo.softInputMode != 0)
      this.mWindow.setSoftInputMode(paramActivityInfo.softInputMode); 
    if (paramActivityInfo.uiOptions != 0)
      this.mWindow.setUiOptions(paramActivityInfo.uiOptions); 
    this.mUiThread = Thread.currentThread();
    this.mMainThread = paramActivityThread;
    this.mInstrumentation = paramInstrumentation;
    this.mToken = paramIBinder1;
    this.mAssistToken = paramIBinder2;
    this.mIdent = paramInt;
    this.mApplication = paramApplication;
    this.mIntent = paramIntent;
    this.mReferrer = paramString2;
    this.mComponent = paramIntent.getComponent();
    this.mActivityInfo = paramActivityInfo;
    this.mTitle = paramCharSequence;
    this.mParent = paramActivity;
    this.mEmbeddedID = paramString1;
    this.mLastNonConfigurationInstances = paramNonConfigurationInstances;
    if (paramIVoiceInteractor != null)
      if (paramNonConfigurationInstances != null) {
        this.mVoiceInteractor = paramNonConfigurationInstances.voiceInteractor;
      } else {
        this.mVoiceInteractor = new VoiceInteractor(paramIVoiceInteractor, (Context)this, this, Looper.myLooper());
      }  
    Window window2 = this.mWindow;
    WindowManager windowManager = (WindowManager)paramContext.getSystemService("window");
    IBinder iBinder = this.mToken;
    String str = this.mComponent.flattenToString();
    if ((paramActivityInfo.flags & 0x200) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    window2.setWindowManager(windowManager, iBinder, str, bool);
    Activity activity = this.mParent;
    if (activity != null)
      this.mWindow.setContainer(activity.getWindow()); 
    this.mWindowManager = this.mWindow.getWindowManager();
    this.mCurrentConfig = paramConfiguration;
    this.mWindow.setColorMode(paramActivityInfo.colorMode);
    Window window1 = this.mWindow;
    if ((paramActivityInfo.flags & 0x2000000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    window1.setPreferMinimalPostProcessing(bool);
    setAutofillOptions(paramApplication.getAutofillOptions());
    setContentCaptureOptions(paramApplication.getContentCaptureOptions());
  }
  
  protected void attachBaseContext(Context paramContext) {
    super.attachBaseContext(paramContext);
    if (paramContext != null) {
      paramContext.setAutofillClient(this);
      paramContext.setContentCaptureOptions(getContentCaptureOptions());
    } 
  }
  
  public final void autofillClientAuthenticate(int paramInt, IntentSender paramIntentSender, Intent paramIntent, boolean paramBoolean) {
    try {
      startIntentSenderForResultInner(paramIntentSender, "@android:autoFillAuth:", paramInt, paramIntent, 0, 0, (Bundle)null);
    } catch (android.content.IntentSender.SendIntentException sendIntentException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("authenticate() failed for intent:");
      stringBuilder.append(paramIntentSender);
      Log.e("Activity", stringBuilder.toString(), (Throwable)sendIntentException);
    } 
  }
  
  public final void autofillClientDispatchUnhandledKey(View paramView, KeyEvent paramKeyEvent) {
    ViewRootImpl viewRootImpl = paramView.getViewRootImpl();
    if (viewRootImpl != null)
      viewRootImpl.dispatchKeyFromAutofill(paramKeyEvent); 
  }
  
  public final View autofillClientFindViewByAccessibilityIdTraversal(int paramInt1, int paramInt2) {
    ArrayList<ViewRootImpl> arrayList = WindowManagerGlobal.getInstance().getRootViews(getActivityToken());
    for (byte b = 0; b < arrayList.size(); b++) {
      View view = ((ViewRootImpl)arrayList.get(b)).getView();
      if (view != null && view.getAccessibilityWindowId() == paramInt2) {
        view = view.findViewByAccessibilityIdTraversal(paramInt1);
        if (view != null)
          return view; 
      } 
    } 
    return null;
  }
  
  public final View autofillClientFindViewByAutofillIdTraversal(AutofillId paramAutofillId) {
    ArrayList<ViewRootImpl> arrayList = WindowManagerGlobal.getInstance().getRootViews(getActivityToken());
    for (byte b = 0; b < arrayList.size(); b++) {
      View view = ((ViewRootImpl)arrayList.get(b)).getView();
      if (view != null) {
        view = view.findViewByAutofillIdTraversal(paramAutofillId.getViewId());
        if (view != null)
          return view; 
      } 
    } 
    return null;
  }
  
  public final View[] autofillClientFindViewsByAutofillIdTraversal(AutofillId[] paramArrayOfAutofillId) {
    View[] arrayOfView = new View[paramArrayOfAutofillId.length];
    ArrayList<ViewRootImpl> arrayList = WindowManagerGlobal.getInstance().getRootViews(getActivityToken());
    for (byte b = 0; b < arrayList.size(); b++) {
      View view = ((ViewRootImpl)arrayList.get(b)).getView();
      if (view != null) {
        int i = paramArrayOfAutofillId.length;
        for (byte b1 = 0; b1 < i; b1++) {
          if (arrayOfView[b1] == null)
            arrayOfView[b1] = view.findViewByAutofillIdTraversal(paramArrayOfAutofillId[b1].getViewId()); 
        } 
      } 
    } 
    return arrayOfView;
  }
  
  public final IBinder autofillClientGetActivityToken() {
    return getActivityToken();
  }
  
  public final ComponentName autofillClientGetComponentName() {
    return getComponentName();
  }
  
  public AutofillId autofillClientGetNextAutofillId() {
    return new AutofillId(getNextAutofillId());
  }
  
  public final boolean[] autofillClientGetViewVisibility(AutofillId[] paramArrayOfAutofillId) {
    int i = paramArrayOfAutofillId.length;
    boolean[] arrayOfBoolean = new boolean[i];
    for (byte b = 0; b < i; b++) {
      AutofillId autofillId = paramArrayOfAutofillId[b];
      View view = autofillClientFindViewByAutofillIdTraversal(autofillId);
      if (view != null)
        if (!autofillId.isVirtualInt()) {
          arrayOfBoolean[b] = view.isVisibleToUser();
        } else {
          arrayOfBoolean[b] = view.isVisibleToUserForAutofill(autofillId.getVirtualChildIntId());
        }  
    } 
    if (Helper.sVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("autofillClientGetViewVisibility(): ");
      stringBuilder.append(Arrays.toString(arrayOfBoolean));
      Log.v("Activity", stringBuilder.toString());
    } 
    return arrayOfBoolean;
  }
  
  public final boolean autofillClientIsCompatibilityModeEnabled() {
    return isAutofillCompatibilityEnabled();
  }
  
  public final boolean autofillClientIsFillUiShowing() {
    boolean bool;
    AutofillPopupWindow autofillPopupWindow = this.mAutofillPopupWindow;
    if (autofillPopupWindow != null && autofillPopupWindow.isShowing()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean autofillClientIsVisibleForAutofill() {
    return this.mStopped ^ true;
  }
  
  public final boolean autofillClientRequestHideFillUi() {
    AutofillPopupWindow autofillPopupWindow = this.mAutofillPopupWindow;
    if (autofillPopupWindow == null)
      return false; 
    autofillPopupWindow.dismiss();
    this.mAutofillPopupWindow = null;
    return true;
  }
  
  public final boolean autofillClientRequestShowFillUi(View paramView, int paramInt1, int paramInt2, Rect paramRect, IAutofillWindowPresenter paramIAutofillWindowPresenter) {
    boolean bool;
    AutofillPopupWindow autofillPopupWindow = this.mAutofillPopupWindow;
    if (autofillPopupWindow == null) {
      bool = false;
      this.mAutofillPopupWindow = new AutofillPopupWindow(paramIAutofillWindowPresenter);
    } else {
      bool = autofillPopupWindow.isShowing();
    } 
    this.mAutofillPopupWindow.update(paramView, 0, 0, paramInt1, paramInt2, paramRect);
    if (!bool && this.mAutofillPopupWindow.isShowing()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final void autofillClientResetableStateAvailable() {
    this.mAutoFillResetNeeded = true;
  }
  
  public final void autofillClientRunOnUiThread(Runnable paramRunnable) {
    runOnUiThread(paramRunnable);
  }
  
  public boolean canStartActivityForResult() {
    return true;
  }
  
  public void closeContextMenu() {
    if (this.mWindow.hasFeature(6))
      this.mWindow.closePanel(6); 
  }
  
  public void closeOptionsMenu() {
    if (this.mWindow.hasFeature(0)) {
      ActionBar actionBar = this.mActionBar;
      if (actionBar == null || !actionBar.closeOptionsMenu())
        this.mWindow.closePanel(0); 
    } 
  }
  
  public final ComponentName contentCaptureClientGetComponentName() {
    return getComponentName();
  }
  
  @SystemApi
  public void convertFromTranslucent() {
    convertFromTranslucentInternal();
  }
  
  @SystemApi
  public boolean convertToTranslucent(TranslucentConversionListener paramTranslucentConversionListener, ActivityOptions paramActivityOptions) {
    boolean bool;
    try {
      Bundle bundle;
      this.mTranslucentCallback = paramTranslucentConversionListener;
      IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
      IBinder iBinder = this.mToken;
      if (paramActivityOptions == null) {
        paramTranslucentConversionListener = null;
      } else {
        bundle = paramActivityOptions.toBundle();
      } 
      this.mChangeCanvasToTranslucent = iActivityTaskManager.convertToTranslucent(iBinder, bundle);
      WindowManagerGlobal.getInstance().changeCanvasOpacity(this.mToken, false);
      bool = true;
    } catch (RemoteException remoteException) {
      this.mChangeCanvasToTranslucent = false;
      bool = false;
    } 
    if (!this.mChangeCanvasToTranslucent) {
      paramTranslucentConversionListener = this.mTranslucentCallback;
      if (paramTranslucentConversionListener != null)
        paramTranslucentConversionListener.onTranslucentConversionComplete(bool); 
    } 
    return this.mChangeCanvasToTranslucent;
  }
  
  public PendingIntent createPendingResult(int paramInt1, Intent paramIntent, int paramInt2) {
    String str = getPackageName();
    Intent intent = null;
    try {
      PendingIntent pendingIntent;
      IBinder iBinder;
      paramIntent.prepareToLeaveProcess((Context)this);
      IActivityManager iActivityManager = ActivityManager.getService();
      String str1 = getAttributionTag();
      if (this.mParent == null) {
        iBinder = this.mToken;
      } else {
        iBinder = this.mParent.mToken;
      } 
      String str2 = this.mEmbeddedID;
      int i = getUserId();
      IIntentSender iIntentSender = iActivityManager.getIntentSenderWithFeature(3, str, str1, iBinder, str2, paramInt1, new Intent[] { paramIntent }, null, paramInt2, null, i);
      paramIntent = intent;
      if (iIntentSender != null)
        pendingIntent = new PendingIntent(iIntentSender); 
      return pendingIntent;
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  @Deprecated
  public final void dismissDialog(int paramInt) {
    SparseArray<ManagedDialog> sparseArray = this.mManagedDialogs;
    if (sparseArray != null) {
      ManagedDialog managedDialog = (ManagedDialog)sparseArray.get(paramInt);
      if (managedDialog != null) {
        managedDialog.mDialog.dismiss();
        return;
      } 
      throw missingDialog(paramInt);
    } 
    throw missingDialog(paramInt);
  }
  
  public final void dismissKeyboardShortcutsHelper() {
    ComponentName componentName = ComponentName.unflattenFromString(getResources().getString(17039950));
    Intent intent = new Intent("com.android.intent.action.DISMISS_KEYBOARD_SHORTCUTS");
    intent.setPackage(componentName.getPackageName());
    sendBroadcastAsUser(intent, Process.myUserHandle());
  }
  
  void dispatchActivityResult(String paramString1, int paramInt1, int paramInt2, Intent paramIntent, String paramString2) {
    this.mFragments.noteStateNotSaved();
    if (paramString1 == null) {
      onActivityResult(paramInt1, paramInt2, paramIntent);
    } else {
      Fragment fragment;
      if (paramString1.startsWith("@android:requestPermissions:")) {
        paramString1 = paramString1.substring("@android:requestPermissions:".length());
        if (TextUtils.isEmpty(paramString1)) {
          dispatchRequestPermissionsResult(paramInt1, paramIntent);
        } else {
          fragment = this.mFragments.findFragmentByWho(paramString1);
          if (fragment != null)
            dispatchRequestPermissionsResultToFragment(paramInt1, paramIntent, fragment); 
        } 
      } else if (fragment.startsWith("@android:view:")) {
        for (ViewRootImpl viewRootImpl : WindowManagerGlobal.getInstance().getRootViews(getActivityToken())) {
          if (viewRootImpl.getView() != null && viewRootImpl.getView().dispatchActivityResult((String)fragment, paramInt1, paramInt2, paramIntent))
            return; 
        } 
      } else if (fragment.startsWith("@android:autoFillAuth:")) {
        if (paramInt2 == -1) {
          Intent intent = paramIntent;
        } else {
          fragment = null;
        } 
        getAutofillManager().onAuthenticationResult(paramInt1, (Intent)fragment, getCurrentFocus());
      } else {
        fragment = this.mFragments.findFragmentByWho((String)fragment);
        if (fragment != null)
          fragment.onActivityResult(paramInt1, paramInt2, paramIntent); 
      } 
    } 
    EventLogTags.writeWmOnActivityResultCalled(this.mIdent, getComponentName().getClassName(), paramString2);
  }
  
  public void dispatchEnterAnimationComplete() {
    this.mEnterAnimationComplete = true;
    this.mInstrumentation.onEnterAnimationComplete();
    onEnterAnimationComplete();
    if (getWindow() != null && getWindow().getDecorView() != null)
      getWindow().getDecorView().getViewTreeObserver().dispatchOnEnterAnimationComplete(); 
  }
  
  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent) {
    onUserInteraction();
    return getWindow().superDispatchGenericMotionEvent(paramMotionEvent) ? true : onGenericMotionEvent(paramMotionEvent);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    onUserInteraction();
    if (paramKeyEvent.getKeyCode() == 82) {
      ActionBar actionBar = this.mActionBar;
      if (actionBar != null && actionBar.onMenuKeyEvent(paramKeyEvent))
        return true; 
    } 
    Window window = getWindow();
    if (window.superDispatchKeyEvent(paramKeyEvent))
      return true; 
    View view2 = this.mDecor;
    View view1 = view2;
    if (view2 == null)
      view1 = window.getDecorView(); 
    if (view1 != null) {
      KeyEvent.DispatcherState dispatcherState = view1.getKeyDispatcherState();
    } else {
      view1 = null;
    } 
    return paramKeyEvent.dispatch(this, (KeyEvent.DispatcherState)view1, this);
  }
  
  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent) {
    onUserInteraction();
    return getWindow().superDispatchKeyShortcutEvent(paramKeyEvent) ? true : onKeyShortcut(paramKeyEvent.getKeyCode(), paramKeyEvent);
  }
  
  void dispatchMovedToDisplay(int paramInt, Configuration paramConfiguration) {
    updateDisplay(paramInt);
    onMovedToDisplay(paramInt, paramConfiguration);
  }
  
  final void dispatchMultiWindowModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    this.mFragments.dispatchMultiWindowModeChanged(paramBoolean, paramConfiguration);
    Window window = this.mWindow;
    if (window != null)
      window.onMultiWindowModeChanged(); 
    this.mIsInMultiWindowMode = paramBoolean;
    onMultiWindowModeChanged(paramBoolean, paramConfiguration);
  }
  
  final void dispatchPictureInPictureModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    this.mFragments.dispatchPictureInPictureModeChanged(paramBoolean, paramConfiguration);
    Window window = this.mWindow;
    if (window != null)
      window.onPictureInPictureModeChanged(paramBoolean); 
    this.mIsInPictureInPictureMode = paramBoolean;
    onPictureInPictureModeChanged(paramBoolean, paramConfiguration);
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    boolean bool;
    paramAccessibilityEvent.setClassName(getClass().getName());
    paramAccessibilityEvent.setPackageName(getPackageName());
    WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
    if (((ViewGroup.LayoutParams)layoutParams).width == -1 && ((ViewGroup.LayoutParams)layoutParams).height == -1) {
      bool = true;
    } else {
      bool = false;
    } 
    paramAccessibilityEvent.setFullScreen(bool);
    CharSequence charSequence = getTitle();
    if (!TextUtils.isEmpty(charSequence))
      paramAccessibilityEvent.getText().add(charSequence); 
    return true;
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    if (paramMotionEvent.getAction() == 0)
      onUserInteraction(); 
    return getWindow().superDispatchTouchEvent(paramMotionEvent) ? true : onTouchEvent(paramMotionEvent);
  }
  
  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent) {
    onUserInteraction();
    return getWindow().superDispatchTrackballEvent(paramMotionEvent) ? true : onTrackballEvent(paramMotionEvent);
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    dumpInner(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  void dumpAutofillManager(String paramString, PrintWriter paramPrintWriter) {
    AutofillManager autofillManager = getAutofillManager();
    if (autofillManager != null) {
      autofillManager.dump(paramString, paramPrintWriter);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("Autofill Compat Mode: ");
      paramPrintWriter.println(isAutofillCompatibilityEnabled());
    } else {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("No AutofillManager");
    } 
  }
  
  void dumpContentCaptureManager(String paramString, PrintWriter paramPrintWriter) {
    ContentCaptureManager contentCaptureManager = getContentCaptureManager();
    if (contentCaptureManager != null) {
      contentCaptureManager.dump(paramString, paramPrintWriter);
    } else {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("No ContentCaptureManager");
    } 
  }
  
  void dumpInner(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: aload #4
    //   2: ifnull -> 108
    //   5: aload #4
    //   7: arraylength
    //   8: ifle -> 108
    //   11: iconst_0
    //   12: istore #5
    //   14: aload #4
    //   16: iconst_0
    //   17: aaload
    //   18: astore #6
    //   20: aload #6
    //   22: invokevirtual hashCode : ()I
    //   25: istore #7
    //   27: iload #7
    //   29: ldc_w 1159329357
    //   32: if_icmpeq -> 60
    //   35: iload #7
    //   37: ldc_w 1455016274
    //   40: if_icmpeq -> 46
    //   43: goto -> 77
    //   46: aload #6
    //   48: ldc_w '--autofill'
    //   51: invokevirtual equals : (Ljava/lang/Object;)Z
    //   54: ifeq -> 43
    //   57: goto -> 80
    //   60: aload #6
    //   62: ldc_w '--contentcapture'
    //   65: invokevirtual equals : (Ljava/lang/Object;)Z
    //   68: ifeq -> 43
    //   71: iconst_1
    //   72: istore #5
    //   74: goto -> 80
    //   77: iconst_m1
    //   78: istore #5
    //   80: iload #5
    //   82: ifeq -> 101
    //   85: iload #5
    //   87: iconst_1
    //   88: if_icmpeq -> 94
    //   91: goto -> 108
    //   94: aload_0
    //   95: aload_1
    //   96: aload_3
    //   97: invokevirtual dumpContentCaptureManager : (Ljava/lang/String;Ljava/io/PrintWriter;)V
    //   100: return
    //   101: aload_0
    //   102: aload_1
    //   103: aload_3
    //   104: invokevirtual dumpAutofillManager : (Ljava/lang/String;Ljava/io/PrintWriter;)V
    //   107: return
    //   108: aload_3
    //   109: aload_1
    //   110: invokevirtual print : (Ljava/lang/String;)V
    //   113: aload_3
    //   114: ldc_w 'Local Activity '
    //   117: invokevirtual print : (Ljava/lang/String;)V
    //   120: aload_3
    //   121: aload_0
    //   122: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   125: invokestatic toHexString : (I)Ljava/lang/String;
    //   128: invokevirtual print : (Ljava/lang/String;)V
    //   131: aload_3
    //   132: ldc_w ' State:'
    //   135: invokevirtual println : (Ljava/lang/String;)V
    //   138: new java/lang/StringBuilder
    //   141: dup
    //   142: invokespecial <init> : ()V
    //   145: astore #6
    //   147: aload #6
    //   149: aload_1
    //   150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload #6
    //   156: ldc_w '  '
    //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload #6
    //   165: invokevirtual toString : ()Ljava/lang/String;
    //   168: astore #6
    //   170: aload_3
    //   171: aload #6
    //   173: invokevirtual print : (Ljava/lang/String;)V
    //   176: aload_3
    //   177: ldc_w 'mResumed='
    //   180: invokevirtual print : (Ljava/lang/String;)V
    //   183: aload_3
    //   184: aload_0
    //   185: getfield mResumed : Z
    //   188: invokevirtual print : (Z)V
    //   191: aload_3
    //   192: ldc_w ' mStopped='
    //   195: invokevirtual print : (Ljava/lang/String;)V
    //   198: aload_3
    //   199: aload_0
    //   200: getfield mStopped : Z
    //   203: invokevirtual print : (Z)V
    //   206: aload_3
    //   207: ldc_w ' mFinished='
    //   210: invokevirtual print : (Ljava/lang/String;)V
    //   213: aload_3
    //   214: aload_0
    //   215: getfield mFinished : Z
    //   218: invokevirtual println : (Z)V
    //   221: aload_3
    //   222: aload #6
    //   224: invokevirtual print : (Ljava/lang/String;)V
    //   227: aload_3
    //   228: ldc_w 'mIsInMultiWindowMode='
    //   231: invokevirtual print : (Ljava/lang/String;)V
    //   234: aload_3
    //   235: aload_0
    //   236: getfield mIsInMultiWindowMode : Z
    //   239: invokevirtual print : (Z)V
    //   242: aload_3
    //   243: ldc_w ' mIsInPictureInPictureMode='
    //   246: invokevirtual print : (Ljava/lang/String;)V
    //   249: aload_3
    //   250: aload_0
    //   251: getfield mIsInPictureInPictureMode : Z
    //   254: invokevirtual println : (Z)V
    //   257: aload_3
    //   258: aload #6
    //   260: invokevirtual print : (Ljava/lang/String;)V
    //   263: aload_3
    //   264: ldc_w 'mChangingConfigurations='
    //   267: invokevirtual print : (Ljava/lang/String;)V
    //   270: aload_3
    //   271: aload_0
    //   272: getfield mChangingConfigurations : Z
    //   275: invokevirtual println : (Z)V
    //   278: aload_3
    //   279: aload #6
    //   281: invokevirtual print : (Ljava/lang/String;)V
    //   284: aload_3
    //   285: ldc_w 'mCurrentConfig='
    //   288: invokevirtual print : (Ljava/lang/String;)V
    //   291: aload_3
    //   292: aload_0
    //   293: getfield mCurrentConfig : Landroid/content/res/Configuration;
    //   296: invokevirtual println : (Ljava/lang/Object;)V
    //   299: aload_0
    //   300: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   303: invokevirtual hasOverrideDisplayAdjustments : ()Z
    //   306: ifeq -> 336
    //   309: aload_3
    //   310: aload #6
    //   312: invokevirtual print : (Ljava/lang/String;)V
    //   315: aload_3
    //   316: ldc_w 'FixedRotationAdjustments='
    //   319: invokevirtual print : (Ljava/lang/String;)V
    //   322: aload_3
    //   323: aload_0
    //   324: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   327: invokevirtual getDisplayAdjustments : ()Landroid/view/DisplayAdjustments;
    //   330: invokevirtual getFixedRotationAdjustments : ()Landroid/view/DisplayAdjustments$FixedRotationAdjustments;
    //   333: invokevirtual println : (Ljava/lang/Object;)V
    //   336: aload_0
    //   337: getfield mFragments : Landroid/app/FragmentController;
    //   340: aload #6
    //   342: aload_2
    //   343: aload_3
    //   344: aload #4
    //   346: invokevirtual dumpLoaders : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   349: aload_0
    //   350: getfield mFragments : Landroid/app/FragmentController;
    //   353: invokevirtual getFragmentManager : ()Landroid/app/FragmentManager;
    //   356: aload #6
    //   358: aload_2
    //   359: aload_3
    //   360: aload #4
    //   362: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   365: aload_0
    //   366: getfield mVoiceInteractor : Landroid/app/VoiceInteractor;
    //   369: astore #8
    //   371: aload #8
    //   373: ifnull -> 387
    //   376: aload #8
    //   378: aload #6
    //   380: aload_2
    //   381: aload_3
    //   382: aload #4
    //   384: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   387: aload_0
    //   388: invokevirtual getWindow : ()Landroid/view/Window;
    //   391: ifnull -> 435
    //   394: aload_0
    //   395: invokevirtual getWindow : ()Landroid/view/Window;
    //   398: invokevirtual peekDecorView : ()Landroid/view/View;
    //   401: ifnull -> 435
    //   404: aload_0
    //   405: invokevirtual getWindow : ()Landroid/view/Window;
    //   408: invokevirtual peekDecorView : ()Landroid/view/View;
    //   411: invokevirtual getViewRootImpl : ()Landroid/view/ViewRootImpl;
    //   414: ifnull -> 435
    //   417: aload_0
    //   418: invokevirtual getWindow : ()Landroid/view/Window;
    //   421: invokevirtual peekDecorView : ()Landroid/view/View;
    //   424: invokevirtual getViewRootImpl : ()Landroid/view/ViewRootImpl;
    //   427: aload_1
    //   428: aload_2
    //   429: aload_3
    //   430: aload #4
    //   432: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   435: aload_0
    //   436: getfield mHandler : Landroid/os/Handler;
    //   439: invokevirtual getLooper : ()Landroid/os/Looper;
    //   442: new android/util/PrintWriterPrinter
    //   445: dup
    //   446: aload_3
    //   447: invokespecial <init> : (Ljava/io/PrintWriter;)V
    //   450: aload_1
    //   451: invokevirtual dump : (Landroid/util/Printer;Ljava/lang/String;)V
    //   454: aload_0
    //   455: aload_1
    //   456: aload_3
    //   457: invokevirtual dumpAutofillManager : (Ljava/lang/String;Ljava/io/PrintWriter;)V
    //   460: aload_0
    //   461: aload_1
    //   462: aload_3
    //   463: invokevirtual dumpContentCaptureManager : (Ljava/lang/String;Ljava/io/PrintWriter;)V
    //   466: invokestatic getInstance : ()Landroid/app/ResourcesManager;
    //   469: aload_1
    //   470: aload_3
    //   471: invokevirtual dump : (Ljava/lang/String;Ljava/io/PrintWriter;)V
    //   474: return
  }
  
  @Deprecated
  public void enterPictureInPictureMode() {
    enterPictureInPictureMode((new PictureInPictureParams.Builder()).build());
  }
  
  public boolean enterPictureInPictureMode(PictureInPictureParams paramPictureInPictureParams) {
    try {
      if (!deviceSupportsPictureInPictureMode())
        return false; 
      if (paramPictureInPictureParams != null) {
        if (this.mCanEnterPictureInPicture) {
          boolean bool = ActivityTaskManager.getService().enterPictureInPictureMode(this.mToken, paramPictureInPictureParams);
          this.mIsInPictureInPictureMode = bool;
          return bool;
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Activity must be resumed to enter picture-in-picture");
        throw illegalStateException;
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("Expected non-null picture-in-picture params");
      throw illegalArgumentException;
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public <T extends View> T findViewById(int paramInt) {
    return (T)getWindow().findViewById(paramInt);
  }
  
  public void finish() {
    finish(0);
  }
  
  public void finishActivity(int paramInt) {
    Activity activity = this.mParent;
    if (activity == null) {
      try {
        ActivityTaskManager.getService().finishSubActivity(this.mToken, this.mEmbeddedID, paramInt);
      } catch (RemoteException remoteException) {}
    } else {
      remoteException.finishActivityFromChild(this, paramInt);
    } 
  }
  
  @Deprecated
  public void finishActivityFromChild(Activity paramActivity, int paramInt) {
    try {
      ActivityTaskManager.getService().finishSubActivity(this.mToken, paramActivity.mEmbeddedID, paramInt);
    } catch (RemoteException remoteException) {}
  }
  
  public void finishAffinity() {
    if (this.mParent == null) {
      if (this.mResultCode == 0 && this.mResultData == null) {
        try {
          if (ActivityTaskManager.getService().finishActivityAffinity(this.mToken))
            this.mFinished = true; 
        } catch (RemoteException remoteException) {}
        return;
      } 
      throw new IllegalStateException("Can not be called to deliver a result");
    } 
    throw new IllegalStateException("Can not be called from an embedded activity");
  }
  
  public void finishAfterTransition() {
    if (!this.mActivityTransitionState.startExitBackTransition(this))
      finish(); 
  }
  
  public void finishAndRemoveTask() {
    finish(1);
  }
  
  @Deprecated
  public void finishFromChild(Activity paramActivity) {
    finish();
  }
  
  public ActionBar getActionBar() {
    initWindowDecorActionBar();
    return this.mActionBar;
  }
  
  ActivityOptions getActivityOptions() {
    try {
      return ActivityOptions.fromBundle(ActivityTaskManager.getService().getActivityOptions(this.mToken));
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public final ActivityThread getActivityThread() {
    return this.mMainThread;
  }
  
  public final IBinder getActivityToken() {
    IBinder iBinder;
    Activity activity = this.mParent;
    if (activity != null) {
      iBinder = activity.getActivityToken();
    } else {
      iBinder = this.mToken;
    } 
    return iBinder;
  }
  
  public final Application getApplication() {
    return this.mApplication;
  }
  
  public final IBinder getAssistToken() {
    IBinder iBinder;
    Activity activity = this.mParent;
    if (activity != null) {
      iBinder = activity.getAssistToken();
    } else {
      iBinder = this.mAssistToken;
    } 
    return iBinder;
  }
  
  public final AutofillManager.AutofillClient getAutofillClient() {
    return this;
  }
  
  public ComponentName getCallingActivity() {
    try {
      return ActivityTaskManager.getService().getCallingActivity(this.mToken);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public String getCallingPackage() {
    try {
      return ActivityTaskManager.getService().getCallingPackage(this.mToken);
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public int getChangingConfigurations() {
    return this.mConfigChangeFlags;
  }
  
  public ComponentName getComponentName() {
    return this.mComponent;
  }
  
  public final ContentCaptureManager.ContentCaptureClient getContentCaptureClient() {
    return this;
  }
  
  public Scene getContentScene() {
    return getWindow().getContentScene();
  }
  
  public TransitionManager getContentTransitionManager() {
    return getWindow().getTransitionManager();
  }
  
  public View getCurrentFocus() {
    Window window = this.mWindow;
    if (window != null) {
      View view = window.getCurrentFocus();
    } else {
      window = null;
    } 
    return (View)window;
  }
  
  @Deprecated
  public FragmentManager getFragmentManager() {
    return this.mFragments.getFragmentManager();
  }
  
  public Intent getIntent() {
    return this.mIntent;
  }
  
  HashMap<String, Object> getLastNonConfigurationChildInstances() {
    NonConfigurationInstances nonConfigurationInstances = this.mLastNonConfigurationInstances;
    if (nonConfigurationInstances != null) {
      HashMap<String, Object> hashMap = nonConfigurationInstances.children;
    } else {
      nonConfigurationInstances = null;
    } 
    return (HashMap<String, Object>)nonConfigurationInstances;
  }
  
  public Object getLastNonConfigurationInstance() {
    NonConfigurationInstances nonConfigurationInstances = this.mLastNonConfigurationInstances;
    if (nonConfigurationInstances != null) {
      Object object = nonConfigurationInstances.activity;
    } else {
      nonConfigurationInstances = null;
    } 
    return nonConfigurationInstances;
  }
  
  public LayoutInflater getLayoutInflater() {
    return getWindow().getLayoutInflater();
  }
  
  @Deprecated
  public LoaderManager getLoaderManager() {
    return this.mFragments.getLoaderManager();
  }
  
  public String getLocalClassName() {
    String str1 = getPackageName();
    String str2 = this.mComponent.getClassName();
    int i = str1.length();
    return (!str2.startsWith(str1) || str2.length() <= i || str2.charAt(i) != '.') ? str2 : str2.substring(i + 1);
  }
  
  public int getMaxNumPictureInPictureActions() {
    try {
      return ActivityTaskManager.getService().getMaxNumPictureInPictureActions(this.mToken);
    } catch (RemoteException remoteException) {
      return 0;
    } 
  }
  
  public final MediaController getMediaController() {
    return getWindow().getMediaController();
  }
  
  public MenuInflater getMenuInflater() {
    if (this.mMenuInflater == null) {
      initWindowDecorActionBar();
      if (this.mActionBar != null) {
        this.mMenuInflater = new MenuInflater(this.mActionBar.getThemedContext(), this);
      } else {
        this.mMenuInflater = new MenuInflater((Context)this);
      } 
    } 
    return this.mMenuInflater;
  }
  
  public int getNextAutofillId() {
    if (this.mLastAutofillId == 2147483646)
      this.mLastAutofillId = 1073741823; 
    int i = this.mLastAutofillId + 1;
    this.mLastAutofillId = i;
    return i;
  }
  
  public final Activity getParent() {
    return this.mParent;
  }
  
  public Intent getParentActivityIntent() {
    String str = this.mActivityInfo.parentActivityName;
    if (TextUtils.isEmpty(str))
      return null; 
    ComponentName componentName = new ComponentName((Context)this, str);
    try {
      Intent intent;
      if ((getPackageManager().getActivityInfo(componentName, 0)).parentActivityName == null) {
        intent = Intent.makeMainActivity(componentName);
      } else {
        intent = new Intent();
        this();
        intent = intent.setComponent(componentName);
      } 
      return intent;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("getParentActivityIntent: bad parentActivityName '");
      stringBuilder.append(str);
      stringBuilder.append("' in manifest");
      Log.e("Activity", stringBuilder.toString());
      return null;
    } 
  }
  
  public SharedPreferences getPreferences(int paramInt) {
    return getSharedPreferences(getLocalClassName(), paramInt);
  }
  
  public Uri getReferrer() {
    Intent intent = getIntent();
    try {
      Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.REFERRER");
      if (uri != null)
        return uri; 
      String str = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
      if (str != null)
        return Uri.parse(str); 
    } catch (BadParcelableException badParcelableException) {
      Log.w("Activity", "Cannot read referrer from intent; intent extras contain unknown custom Parcelable objects");
    } 
    return (this.mReferrer != null) ? (new Uri.Builder()).scheme("android-app").authority(this.mReferrer).build() : null;
  }
  
  public int getRequestedOrientation() {
    Activity activity = this.mParent;
    if (activity == null)
      try {
        return ActivityTaskManager.getService().getRequestedOrientation(this.mToken);
      } catch (RemoteException remoteException) {
        return -1;
      }  
    return remoteException.getRequestedOrientation();
  }
  
  public final SearchEvent getSearchEvent() {
    return this.mSearchEvent;
  }
  
  public Object getSystemService(String paramString) {
    if (getBaseContext() != null) {
      if ("window".equals(paramString))
        return this.mWindowManager; 
      if ("search".equals(paramString)) {
        ensureSearchManager();
        return this.mSearchManager;
      } 
      return super.getSystemService(paramString);
    } 
    throw new IllegalStateException("System services not available to Activities before onCreate()");
  }
  
  public int getTaskId() {
    try {
      return ActivityTaskManager.getService().getTaskForActivity(this.mToken, false);
    } catch (RemoteException remoteException) {
      return -1;
    } 
  }
  
  public final CharSequence getTitle() {
    return this.mTitle;
  }
  
  public final int getTitleColor() {
    return this.mTitleColor;
  }
  
  public VoiceInteractor getVoiceInteractor() {
    return this.mVoiceInteractor;
  }
  
  public final int getVolumeControlStream() {
    return getWindow().getVolumeControlStream();
  }
  
  public Window getWindow() {
    return this.mWindow;
  }
  
  public WindowManager getWindowManager() {
    return this.mWindowManager;
  }
  
  public boolean hasWindowFocus() {
    Window window = getWindow();
    if (window != null) {
      View view = window.getDecorView();
      if (view != null)
        return view.hasWindowFocus(); 
    } 
    return false;
  }
  
  public void invalidateOptionsMenu() {
    if (this.mWindow.hasFeature(0)) {
      ActionBar actionBar = this.mActionBar;
      if (actionBar == null || !actionBar.invalidateOptionsMenu())
        this.mWindow.invalidatePanelMenu(0); 
    } 
  }
  
  public boolean isActivityTransitionRunning() {
    return this.mActivityTransitionState.isTransitionRunning();
  }
  
  @SystemApi
  @Deprecated
  public boolean isBackgroundVisibleBehind() {
    return false;
  }
  
  public boolean isChangingConfigurations() {
    return this.mChangingConfigurations;
  }
  
  public final boolean isChild() {
    boolean bool;
    if (this.mParent != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isDestroyed() {
    return this.mDestroyed;
  }
  
  public final boolean isDisablingEnterExitEventForAutofill() {
    return (this.mAutoFillIgnoreFirstResumePause || !this.mResumed);
  }
  
  public boolean isFinishing() {
    return this.mFinished;
  }
  
  public boolean isImmersive() {
    try {
      return ActivityTaskManager.getService().isImmersive(this.mToken);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean isInMultiWindowMode() {
    return this.mIsInMultiWindowMode;
  }
  
  public boolean isInPictureInPictureMode() {
    return this.mIsInPictureInPictureMode;
  }
  
  public boolean isLocalVoiceInteractionSupported() {
    try {
      return ActivityTaskManager.getService().supportsLocalVoiceInteraction();
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean isOverlayWithDecorCaptionEnabled() {
    return this.mWindow.isOverlayWithDecorCaptionEnabled();
  }
  
  public final boolean isResumed() {
    return this.mResumed;
  }
  
  public boolean isTaskRoot() {
    return this.mWindowControllerCallback.isTaskRoot();
  }
  
  final boolean isTopOfTask() {
    if (this.mToken == null || this.mWindow == null)
      return false; 
    try {
      return ActivityTaskManager.getService().isTopOfTask(getActivityToken());
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean isVoiceInteraction() {
    boolean bool;
    if (this.mVoiceInteractor != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isVoiceInteractionRoot() {
    boolean bool = false;
    try {
      if (this.mVoiceInteractor != null) {
        boolean bool1 = ActivityTaskManager.getService().isRootVoiceInteraction(this.mToken);
        if (bool1)
          bool = true; 
      } 
      return bool;
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  void makeVisible() {
    if (!this.mWindowAdded) {
      getWindowManager().addView(this.mDecor, (ViewGroup.LayoutParams)getWindow().getAttributes());
      this.mWindowAdded = true;
    } 
    this.mDecor.setVisibility(0);
  }
  
  @Deprecated
  public final Cursor managedQuery(Uri paramUri, String[] paramArrayOfString, String paramString1, String paramString2) {
    Cursor cursor = getContentResolver().query(paramUri, paramArrayOfString, paramString1, null, paramString2);
    if (cursor != null)
      startManagingCursor(cursor); 
    return cursor;
  }
  
  @Deprecated
  public final Cursor managedQuery(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
    Cursor cursor = getContentResolver().query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
    if (cursor != null)
      startManagingCursor(cursor); 
    return cursor;
  }
  
  public boolean moveTaskToBack(boolean paramBoolean) {
    try {
      return ActivityTaskManager.getService().moveActivityTaskToBack(this.mToken, paramBoolean);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  public boolean navigateUpTo(Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mParent : Landroid/app/Activity;
    //   4: astore_2
    //   5: aload_2
    //   6: ifnonnull -> 105
    //   9: aload_1
    //   10: invokevirtual getComponent : ()Landroid/content/ComponentName;
    //   13: ifnonnull -> 49
    //   16: aload_1
    //   17: aload_0
    //   18: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   21: invokevirtual resolveActivity : (Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;
    //   24: astore_2
    //   25: aload_2
    //   26: ifnonnull -> 31
    //   29: iconst_0
    //   30: ireturn
    //   31: new android/content/Intent
    //   34: dup
    //   35: aload_1
    //   36: invokespecial <init> : (Landroid/content/Intent;)V
    //   39: astore_1
    //   40: aload_1
    //   41: aload_2
    //   42: invokevirtual setComponent : (Landroid/content/ComponentName;)Landroid/content/Intent;
    //   45: pop
    //   46: goto -> 49
    //   49: aload_0
    //   50: monitorenter
    //   51: aload_0
    //   52: getfield mResultCode : I
    //   55: istore_3
    //   56: aload_0
    //   57: getfield mResultData : Landroid/content/Intent;
    //   60: astore_2
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_2
    //   64: ifnull -> 72
    //   67: aload_2
    //   68: aload_0
    //   69: invokevirtual prepareToLeaveProcess : (Landroid/content/Context;)V
    //   72: aload_1
    //   73: aload_0
    //   74: invokevirtual prepareToLeaveProcess : (Landroid/content/Context;)V
    //   77: invokestatic getService : ()Landroid/app/IActivityTaskManager;
    //   80: aload_0
    //   81: getfield mToken : Landroid/os/IBinder;
    //   84: aload_1
    //   85: iload_3
    //   86: aload_2
    //   87: invokeinterface navigateUpTo : (Landroid/os/IBinder;Landroid/content/Intent;ILandroid/content/Intent;)Z
    //   92: istore #4
    //   94: iload #4
    //   96: ireturn
    //   97: astore_1
    //   98: iconst_0
    //   99: ireturn
    //   100: astore_1
    //   101: aload_0
    //   102: monitorexit
    //   103: aload_1
    //   104: athrow
    //   105: aload_2
    //   106: aload_0
    //   107: aload_1
    //   108: invokevirtual navigateUpToFromChild : (Landroid/app/Activity;Landroid/content/Intent;)Z
    //   111: ireturn
    // Exception table:
    //   from	to	target	type
    //   51	63	100	finally
    //   72	94	97	android/os/RemoteException
    //   101	103	100	finally
  }
  
  @Deprecated
  public boolean navigateUpToFromChild(Activity paramActivity, Intent paramIntent) {
    return navigateUpTo(paramIntent);
  }
  
  public void onActionModeFinished(ActionMode paramActionMode) {}
  
  public void onActionModeStarted(ActionMode paramActionMode) {}
  
  public void onActivityReenter(int paramInt, Intent paramIntent) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  protected void onApplyThemeResource(Resources.Theme paramTheme, int paramInt, boolean paramBoolean) {
    Activity activity = this.mParent;
    if (activity == null) {
      super.onApplyThemeResource(paramTheme, paramInt, paramBoolean);
    } else {
      try {
        paramTheme.setTo(activity.getTheme());
      } catch (Exception exception) {}
      paramTheme.applyStyle(paramInt, false);
    } 
    TypedArray typedArray = paramTheme.obtainStyledAttributes(R.styleable.ActivityTaskDescription);
    if (this.mTaskDescription.getPrimaryColor() == 0) {
      paramInt = typedArray.getColor(1, 0);
      if (paramInt != 0 && Color.alpha(paramInt) == 255)
        this.mTaskDescription.setPrimaryColor(paramInt); 
    } 
    paramInt = typedArray.getColor(0, 0);
    if (paramInt != 0 && Color.alpha(paramInt) == 255)
      this.mTaskDescription.setBackgroundColor(paramInt); 
    paramInt = typedArray.getColor(2, 0);
    if (paramInt != 0)
      this.mTaskDescription.setStatusBarColor(paramInt); 
    paramInt = typedArray.getColor(3, 0);
    if (paramInt != 0)
      this.mTaskDescription.setNavigationBarColor(paramInt); 
    if ((getApplicationInfo()).targetSdkVersion < 29) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    if (paramInt == 0) {
      this.mTaskDescription.setEnsureStatusBarContrastWhenTransparent(typedArray.getBoolean(4, false));
      this.mTaskDescription.setEnsureNavigationBarContrastWhenTransparent(typedArray.getBoolean(5, true));
    } 
    typedArray.recycle();
    setTaskDescription(this.mTaskDescription);
  }
  
  @Deprecated
  public void onAttachFragment(Fragment paramFragment) {}
  
  public void onAttachedToWindow() {}
  
  public void onBackPressed() {
    ActionBar actionBar = this.mActionBar;
    if (actionBar != null && actionBar.collapseActionView())
      return; 
    FragmentManager fragmentManager = this.mFragments.getFragmentManager();
    if (!fragmentManager.isStateSaved() && fragmentManager.popBackStackImmediate())
      return; 
    if (!isTaskRoot()) {
      finishAfterTransition();
      return;
    } 
    try {
      IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
      IBinder iBinder = this.mToken;
      RequestFinishCallback requestFinishCallback = new RequestFinishCallback();
      WeakReference<Activity> weakReference = new WeakReference();
      this((T)this);
      this(weakReference);
      iActivityTaskManager.onBackPressedOnTaskRoot(iBinder, requestFinishCallback);
    } catch (RemoteException remoteException) {
      finishAfterTransition();
    } 
  }
  
  @SystemApi
  @Deprecated
  public void onBackgroundVisibleBehindChanged(boolean paramBoolean) {}
  
  protected void onChildTitleChanged(Activity paramActivity, CharSequence paramCharSequence) {}
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    this.mCalled = true;
    this.mFragments.dispatchConfigurationChanged(paramConfiguration);
    Window window = this.mWindow;
    if (window != null)
      window.onConfigurationChanged(paramConfiguration); 
    ActionBar actionBar = this.mActionBar;
    if (actionBar != null)
      actionBar.onConfigurationChanged(paramConfiguration); 
  }
  
  public void onContentChanged() {}
  
  public boolean onContextItemSelected(MenuItem paramMenuItem) {
    Activity activity = this.mParent;
    return (activity != null) ? activity.onContextItemSelected(paramMenuItem) : false;
  }
  
  public void onContextMenuClosed(Menu paramMenu) {
    Activity activity = this.mParent;
    if (activity != null)
      activity.onContextMenuClosed(paramMenu); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    NonConfigurationInstances nonConfigurationInstances = this.mLastNonConfigurationInstances;
    if (nonConfigurationInstances != null)
      this.mFragments.restoreLoaderNonConfig(nonConfigurationInstances.loaders); 
    if (this.mActivityInfo.parentActivityName != null) {
      ActionBar actionBar = this.mActionBar;
      if (actionBar == null) {
        this.mEnableDefaultActionBarUp = true;
      } else {
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
      } 
    } 
    boolean bool = false;
    if (paramBundle != null) {
      this.mAutoFillResetNeeded = paramBundle.getBoolean("@android:autofillResetNeeded", false);
      this.mLastAutofillId = paramBundle.getInt("android:lastAutofillId", 1073741823);
      if (this.mAutoFillResetNeeded)
        getAutofillManager().onCreate(paramBundle); 
      Parcelable parcelable = paramBundle.getParcelable("android:fragments");
      FragmentController fragmentController = this.mFragments;
      nonConfigurationInstances = this.mLastNonConfigurationInstances;
      if (nonConfigurationInstances != null) {
        FragmentManagerNonConfig fragmentManagerNonConfig = nonConfigurationInstances.fragments;
      } else {
        nonConfigurationInstances = null;
      } 
      fragmentController.restoreAllState(parcelable, (FragmentManagerNonConfig)nonConfigurationInstances);
    } 
    this.mFragments.dispatchCreate();
    dispatchActivityCreated(paramBundle);
    VoiceInteractor voiceInteractor = this.mVoiceInteractor;
    if (voiceInteractor != null)
      voiceInteractor.attachActivity(this); 
    if (paramBundle != null)
      bool = true; 
    this.mRestoredFromBundle = bool;
    this.mCalled = true;
  }
  
  public void onCreate(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    onCreate(paramBundle);
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {}
  
  public CharSequence onCreateDescription() {
    return null;
  }
  
  @Deprecated
  protected Dialog onCreateDialog(int paramInt) {
    return null;
  }
  
  @Deprecated
  protected Dialog onCreateDialog(int paramInt, Bundle paramBundle) {
    return onCreateDialog(paramInt);
  }
  
  public void onCreateNavigateUpTaskStack(TaskStackBuilder paramTaskStackBuilder) {
    paramTaskStackBuilder.addParentStack(this);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    Activity activity = this.mParent;
    return (activity != null) ? activity.onCreateOptionsMenu(paramMenu) : true;
  }
  
  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu) {
    return (paramInt == 0) ? (onCreateOptionsMenu(paramMenu) | this.mFragments.dispatchCreateOptionsMenu(paramMenu, getMenuInflater())) : false;
  }
  
  public View onCreatePanelView(int paramInt) {
    return null;
  }
  
  @Deprecated
  public boolean onCreateThumbnail(Bitmap paramBitmap, Canvas paramCanvas) {
    return false;
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return !"fragment".equals(paramString) ? onCreateView(paramString, paramContext, paramAttributeSet) : this.mFragments.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return null;
  }
  
  protected void onDestroy() {
    ArrayList<ManagedCursor> arrayList;
    ActionBar actionBar;
    this.mCalled = true;
    if (isFinishing() && this.mAutoFillResetNeeded)
      getAutofillManager().onActivityFinishing(); 
    SparseArray<ManagedDialog> sparseArray = this.mManagedDialogs;
    if (sparseArray != null) {
      int i = sparseArray.size();
      for (byte b = 0; b < i; b++) {
        ManagedDialog managedDialog = (ManagedDialog)this.mManagedDialogs.valueAt(b);
        if (managedDialog.mDialog.isShowing())
          managedDialog.mDialog.dismiss(); 
      } 
      this.mManagedDialogs = null;
    } 
    synchronized (this.mManagedCursors) {
      int i = this.mManagedCursors.size();
      for (byte b = 0; b < i; b++) {
        ManagedCursor managedCursor = this.mManagedCursors.get(b);
        if (managedCursor != null)
          managedCursor.mCursor.close(); 
      } 
      this.mManagedCursors.clear();
      SearchManager searchManager = this.mSearchManager;
      if (searchManager != null)
        searchManager.stopSearch(); 
      actionBar = this.mActionBar;
      if (actionBar != null)
        actionBar.onDestroy(); 
      dispatchActivityDestroyed();
      notifyContentCaptureManagerIfNeeded(4);
      return;
    } 
  }
  
  public void onDetachedFromWindow() {}
  
  public void onEnterAnimationComplete() {}
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent) {
    return false;
  }
  
  public void onGetDirectActions(CancellationSignal paramCancellationSignal, Consumer<List<DirectAction>> paramConsumer) {
    paramConsumer.accept(Collections.emptyList());
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool;
    if (paramInt == 4) {
      if ((getApplicationInfo()).targetSdkVersion >= 5) {
        paramKeyEvent.startTracking();
      } else {
        onBackPressed();
      } 
      return true;
    } 
    int i = this.mDefaultKeyMode;
    if (i == 0)
      return false; 
    if (i == 2) {
      Window window = getWindow();
      return (window.hasFeature(0) && window.performPanelShortcut(0, paramInt, paramKeyEvent, 2));
    } 
    if (paramInt == 61)
      return false; 
    i = 0;
    if (paramKeyEvent.getRepeatCount() != 0 || paramKeyEvent.isSystem()) {
      paramInt = 1;
      bool = false;
    } else {
      boolean bool1 = TextKeyListener.getInstance().onKeyDown(null, (Editable)this.mDefaultKeySsb, paramInt, paramKeyEvent);
      paramInt = i;
      bool = bool1;
      if (bool1) {
        paramInt = i;
        bool = bool1;
        if (this.mDefaultKeySsb.length() > 0) {
          String str = this.mDefaultKeySsb.toString();
          paramInt = 1;
          i = this.mDefaultKeyMode;
          if (i != 1) {
            if (i != 3) {
              if (i != 4) {
                bool = bool1;
              } else {
                startSearch(str, false, (Bundle)null, true);
                bool = bool1;
              } 
            } else {
              startSearch(str, false, (Bundle)null, false);
              bool = bool1;
            } 
          } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("tel:");
            stringBuilder.append(str);
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(stringBuilder.toString()));
            intent.addFlags(268435456);
            startActivity(intent);
            bool = bool1;
          } 
        } 
      } 
    } 
    if (paramInt != 0) {
      this.mDefaultKeySsb.clear();
      this.mDefaultKeySsb.clearSpans();
      Selection.setSelection((Spannable)this.mDefaultKeySsb, 0);
    } 
    return bool;
  }
  
  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent) {
    return false;
  }
  
  public boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent) {
    return false;
  }
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool;
    ActionBar actionBar = getActionBar();
    if (actionBar != null && actionBar.onKeyShortcut(paramInt, paramKeyEvent)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    if ((getApplicationInfo()).targetSdkVersion >= 5 && paramInt == 4 && paramKeyEvent.isTracking() && !paramKeyEvent.isCanceled()) {
      onBackPressed();
      return true;
    } 
    return false;
  }
  
  public void onLocalVoiceInteractionStarted() {}
  
  public void onLocalVoiceInteractionStopped() {}
  
  public void onLowMemory() {
    this.mCalled = true;
    this.mFragments.dispatchLowMemory();
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem) {
    CharSequence charSequence = paramMenuItem.getTitleCondensed();
    if (paramInt != 0) {
      if (paramInt != 6)
        return false; 
      if (charSequence != null)
        EventLog.writeEvent(50000, new Object[] { Integer.valueOf(1), charSequence.toString() }); 
      return onContextItemSelected(paramMenuItem) ? true : this.mFragments.dispatchContextItemSelected(paramMenuItem);
    } 
    if (charSequence != null)
      EventLog.writeEvent(50000, new Object[] { Integer.valueOf(0), charSequence.toString() }); 
    if (onOptionsItemSelected(paramMenuItem))
      return true; 
    if (this.mFragments.dispatchOptionsItemSelected(paramMenuItem))
      return true; 
    if (paramMenuItem.getItemId() == 16908332) {
      ActionBar actionBar = this.mActionBar;
      if (actionBar != null && (actionBar.getDisplayOptions() & 0x4) != 0) {
        Activity activity = this.mParent;
        return (activity == null) ? onNavigateUp() : activity.onNavigateUpFromChild(this);
      } 
    } 
    return false;
  }
  
  public boolean onMenuOpened(int paramInt, Menu paramMenu) {
    if (paramInt == 8) {
      initWindowDecorActionBar();
      ActionBar actionBar = this.mActionBar;
      if (actionBar != null) {
        actionBar.dispatchMenuVisibilityChanged(true);
      } else {
        Log.e("Activity", "Tried to open action bar menu with no action bar");
      } 
    } 
    return true;
  }
  
  public void onMovedToDisplay(int paramInt, Configuration paramConfiguration) {}
  
  @Deprecated
  public void onMultiWindowModeChanged(boolean paramBoolean) {}
  
  public void onMultiWindowModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    onMultiWindowModeChanged(paramBoolean);
  }
  
  public boolean onNavigateUp() {
    Intent intent = getParentActivityIntent();
    if (intent != null) {
      if (this.mActivityInfo.taskAffinity == null) {
        finish();
      } else {
        TaskStackBuilder taskStackBuilder;
        if (shouldUpRecreateTask(intent)) {
          taskStackBuilder = TaskStackBuilder.create((Context)this);
          onCreateNavigateUpTaskStack(taskStackBuilder);
          onPrepareNavigateUpTaskStack(taskStackBuilder);
          taskStackBuilder.startActivities();
          if (this.mResultCode != 0 || this.mResultData != null) {
            Log.i("Activity", "onNavigateUp only finishing topmost activity to return a result");
            finish();
            return true;
          } 
          finishAffinity();
        } else {
          navigateUpTo((Intent)taskStackBuilder);
        } 
      } 
      return true;
    } 
    return false;
  }
  
  @Deprecated
  public boolean onNavigateUpFromChild(Activity paramActivity) {
    return onNavigateUp();
  }
  
  public void onNewActivityOptions(ActivityOptions paramActivityOptions) {
    this.mActivityTransitionState.setEnterActivityOptions(this, paramActivityOptions);
    if (!this.mStopped)
      this.mActivityTransitionState.enterReady(this); 
  }
  
  protected void onNewIntent(Intent paramIntent) {}
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    Activity activity = this.mParent;
    return (activity != null) ? activity.onOptionsItemSelected(paramMenuItem) : false;
  }
  
  public void onOptionsMenuClosed(Menu paramMenu) {
    Activity activity = this.mParent;
    if (activity != null)
      activity.onOptionsMenuClosed(paramMenu); 
  }
  
  public void onPanelClosed(int paramInt, Menu paramMenu) {
    if (paramInt != 0) {
      if (paramInt != 6) {
        if (paramInt == 8) {
          initWindowDecorActionBar();
          this.mActionBar.dispatchMenuVisibilityChanged(false);
        } 
      } else {
        onContextMenuClosed(paramMenu);
      } 
    } else {
      this.mFragments.dispatchOptionsMenuClosed(paramMenu);
      onOptionsMenuClosed(paramMenu);
    } 
  }
  
  protected void onPause() {
    dispatchActivityPaused();
    if (this.mAutoFillResetNeeded)
      if (!this.mAutoFillIgnoreFirstResumePause) {
        View view = getCurrentFocus();
        if (view != null && view.canNotifyAutofillEnterExitEvent())
          getAutofillManager().notifyViewExited(view); 
      } else {
        this.mAutoFillIgnoreFirstResumePause = false;
      }  
    notifyContentCaptureManagerIfNeeded(3);
    this.mCalled = true;
  }
  
  public void onPerformDirectAction(String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal, Consumer<Bundle> paramConsumer) {}
  
  @Deprecated
  public void onPictureInPictureModeChanged(boolean paramBoolean) {}
  
  public void onPictureInPictureModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    onPictureInPictureModeChanged(paramBoolean);
  }
  
  public boolean onPictureInPictureRequested() {
    return false;
  }
  
  protected void onPostCreate(Bundle paramBundle) {
    if (!isChild()) {
      this.mTitleReady = true;
      onTitleChanged(getTitle(), getTitleColor());
    } 
    this.mCalled = true;
    notifyContentCaptureManagerIfNeeded(1);
  }
  
  public void onPostCreate(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    onPostCreate(paramBundle);
  }
  
  protected void onPostResume() {
    Window window = getWindow();
    if (window != null)
      window.makeActive(); 
    ActionBar actionBar = this.mActionBar;
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(true); 
    this.mCalled = true;
  }
  
  @Deprecated
  protected void onPrepareDialog(int paramInt, Dialog paramDialog) {
    paramDialog.setOwnerActivity(this);
  }
  
  @Deprecated
  protected void onPrepareDialog(int paramInt, Dialog paramDialog, Bundle paramBundle) {
    onPrepareDialog(paramInt, paramDialog);
  }
  
  public void onPrepareNavigateUpTaskStack(TaskStackBuilder paramTaskStackBuilder) {}
  
  public boolean onPrepareOptionsMenu(Menu paramMenu) {
    Activity activity = this.mParent;
    return (activity != null) ? activity.onPrepareOptionsMenu(paramMenu) : true;
  }
  
  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu) {
    return (paramInt == 0) ? (onPrepareOptionsMenu(paramMenu) | this.mFragments.dispatchPrepareOptionsMenu(paramMenu)) : true;
  }
  
  public void onProvideAssistContent(AssistContent paramAssistContent) {}
  
  public void onProvideAssistData(Bundle paramBundle) {}
  
  public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> paramList, Menu paramMenu, int paramInt) {
    KeyboardShortcutGroup keyboardShortcutGroup;
    if (paramMenu == null)
      return; 
    MenuItem menuItem = null;
    int i = paramMenu.size();
    paramInt = 0;
    while (paramInt < i) {
      KeyboardShortcutGroup keyboardShortcutGroup1;
      MenuItem menuItem1 = paramMenu.getItem(paramInt);
      CharSequence charSequence = menuItem1.getTitle();
      char c = menuItem1.getAlphabeticShortcut();
      int j = menuItem1.getAlphabeticModifiers();
      menuItem1 = menuItem;
      if (charSequence != null) {
        menuItem1 = menuItem;
        if (c != '\000') {
          menuItem1 = menuItem;
          if (menuItem == null) {
            int k = (this.mApplication.getApplicationInfo()).labelRes;
            if (k != 0) {
              String str = getString(k);
            } else {
              menuItem = null;
            } 
            keyboardShortcutGroup1 = new KeyboardShortcutGroup((CharSequence)menuItem);
          } 
          keyboardShortcutGroup1.addItem(new KeyboardShortcutInfo(charSequence, c, j));
        } 
      } 
      paramInt++;
      keyboardShortcutGroup = keyboardShortcutGroup1;
    } 
    if (keyboardShortcutGroup != null)
      paramList.add(keyboardShortcutGroup); 
  }
  
  public Uri onProvideReferrer() {
    return null;
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {}
  
  protected void onRestart() {
    this.mCalled = true;
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    if (this.mWindow != null) {
      paramBundle = paramBundle.getBundle("android:viewHierarchyState");
      if (paramBundle != null)
        this.mWindow.restoreHierarchyState(paramBundle); 
    } 
  }
  
  public void onRestoreInstanceState(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    if (paramBundle != null)
      onRestoreInstanceState(paramBundle); 
  }
  
  protected void onResume() {
    dispatchActivityResumed();
    this.mActivityTransitionState.onResume(this);
    enableAutofillCompatibilityIfNeeded();
    if (this.mAutoFillResetNeeded && !this.mAutoFillIgnoreFirstResumePause) {
      View view = getCurrentFocus();
      if (view != null && view.canNotifyAutofillEnterExitEvent())
        getAutofillManager().notifyViewEntered(view); 
    } 
    notifyContentCaptureManagerIfNeeded(2);
    this.mCalled = true;
  }
  
  HashMap<String, Object> onRetainNonConfigurationChildInstances() {
    return null;
  }
  
  public Object onRetainNonConfigurationInstance() {
    return null;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    paramBundle.putBundle("android:viewHierarchyState", this.mWindow.saveHierarchyState());
    paramBundle.putInt("android:lastAutofillId", this.mLastAutofillId);
    Parcelable parcelable = this.mFragments.saveAllState();
    if (parcelable != null)
      paramBundle.putParcelable("android:fragments", parcelable); 
    if (this.mAutoFillResetNeeded) {
      paramBundle.putBoolean("@android:autofillResetNeeded", true);
      getAutofillManager().onSaveInstanceState(paramBundle);
    } 
    dispatchActivitySaveInstanceState(paramBundle);
  }
  
  public void onSaveInstanceState(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    onSaveInstanceState(paramBundle);
  }
  
  public boolean onSearchRequested() {
    int i = (getResources().getConfiguration()).uiMode & 0xF;
    if (i != 4 && i != 6) {
      startSearch((String)null, false, (Bundle)null, false);
      return true;
    } 
    return false;
  }
  
  public boolean onSearchRequested(SearchEvent paramSearchEvent) {
    this.mSearchEvent = paramSearchEvent;
    boolean bool = onSearchRequested();
    this.mSearchEvent = null;
    return bool;
  }
  
  protected void onStart() {
    this.mCalled = true;
    this.mFragments.doLoaderStart();
    dispatchActivityStarted();
    if (this.mAutoFillResetNeeded)
      getAutofillManager().onVisibleForAutofill(); 
  }
  
  @Deprecated
  public void onStateNotSaved() {}
  
  protected void onStop() {
    ActionBar actionBar = this.mActionBar;
    if (actionBar != null)
      actionBar.setShowHideAnimationEnabled(false); 
    this.mActivityTransitionState.onStop();
    dispatchActivityStopped();
    this.mTranslucentCallback = null;
    this.mCalled = true;
    if (this.mAutoFillResetNeeded) {
      getAutofillManager().onInvisibleForAutofill(true ^ this.mChangingConfigurations);
    } else {
      Intent intent = this.mIntent;
      if (intent != null && intent.hasExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN") && this.mIntent.hasExtra("android.view.autofill.extra.RESTORE_CROSS_ACTIVITY"))
        restoreAutofillSaveUi(); 
    } 
    this.mEnterAnimationComplete = false;
  }
  
  protected void onTitleChanged(CharSequence paramCharSequence, int paramInt) {
    if (this.mTitleReady) {
      Window window = getWindow();
      if (window != null) {
        window.setTitle(paramCharSequence);
        if (paramInt != 0)
          window.setTitleColor(paramInt); 
      } 
      ActionBar actionBar = this.mActionBar;
      if (actionBar != null)
        actionBar.setWindowTitle(paramCharSequence); 
    } 
  }
  
  public void onTopResumedActivityChanged(boolean paramBoolean) {}
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    if (this.mWindow.shouldCloseOnTouch((Context)this, paramMotionEvent)) {
      finish();
      return true;
    } 
    return false;
  }
  
  public boolean onTrackballEvent(MotionEvent paramMotionEvent) {
    return false;
  }
  
  void onTranslucentConversionComplete(boolean paramBoolean) {
    TranslucentConversionListener translucentConversionListener = this.mTranslucentCallback;
    if (translucentConversionListener != null) {
      translucentConversionListener.onTranslucentConversionComplete(paramBoolean);
      this.mTranslucentCallback = null;
    } 
    if (this.mChangeCanvasToTranslucent)
      WindowManagerGlobal.getInstance().changeCanvasOpacity(this.mToken, false); 
  }
  
  public void onTrimMemory(int paramInt) {
    this.mCalled = true;
    this.mFragments.dispatchTrimMemory(paramInt);
  }
  
  public void onUserInteraction() {}
  
  protected void onUserLeaveHint() {}
  
  @Deprecated
  public void onVisibleBehindCanceled() {
    this.mCalled = true;
  }
  
  public void onWindowAttributesChanged(WindowManager.LayoutParams paramLayoutParams) {
    if (this.mParent == null) {
      View view = this.mDecor;
      if (view != null && view.getParent() != null) {
        getWindowManager().updateViewLayout(view, (ViewGroup.LayoutParams)paramLayoutParams);
        ContentCaptureManager contentCaptureManager = this.mContentCaptureManager;
        if (contentCaptureManager != null)
          contentCaptureManager.updateWindowAttributes(paramLayoutParams); 
      } 
    } 
  }
  
  public void onWindowDismissed(boolean paramBoolean1, boolean paramBoolean2) {
    boolean bool;
    if (paramBoolean1) {
      bool = true;
    } else {
      bool = false;
    } 
    finish(bool);
    if (paramBoolean2)
      overridePendingTransition(0, 0); 
  }
  
  public void onWindowFocusChanged(boolean paramBoolean) {}
  
  public ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback) {
    if (this.mActionModeTypeStarting == 0) {
      initWindowDecorActionBar();
      ActionBar actionBar = this.mActionBar;
      if (actionBar != null)
        return actionBar.startActionMode(paramCallback); 
    } 
    return null;
  }
  
  public ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback, int paramInt) {
    try {
      this.mActionModeTypeStarting = paramInt;
      return onWindowStartingActionMode(paramCallback);
    } finally {
      this.mActionModeTypeStarting = 0;
    } 
  }
  
  public void openContextMenu(View paramView) {
    paramView.showContextMenu();
  }
  
  public void openOptionsMenu() {
    if (this.mWindow.hasFeature(0)) {
      ActionBar actionBar = this.mActionBar;
      if (actionBar == null || !actionBar.openOptionsMenu())
        this.mWindow.openPanel(0, null); 
    } 
  }
  
  public void overridePendingTransition(int paramInt1, int paramInt2) {
    try {
      ActivityTaskManager.getService().overridePendingTransition(this.mToken, getPackageName(), paramInt1, paramInt2);
    } catch (RemoteException remoteException) {}
  }
  
  final void performCreate(Bundle paramBundle) {
    performCreate(paramBundle, (PersistableBundle)null);
  }
  
  final void performCreate(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    boolean bool;
    dispatchActivityPreCreated(paramBundle);
    this.mCanEnterPictureInPicture = true;
    int i = (getResources().getConfiguration()).windowConfiguration.getWindowingMode();
    this.mIsInMultiWindowMode = WindowConfiguration.inMultiWindowMode(i);
    if (i == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mIsInPictureInPictureMode = bool;
    restoreHasCurrentPermissionRequest(paramBundle);
    if (paramPersistableBundle != null) {
      onCreate(paramBundle, paramPersistableBundle);
    } else {
      onCreate(paramBundle);
    } 
    EventLogTags.writeWmOnCreateCalled(this.mIdent, getComponentName().getClassName(), "performCreate");
    this.mActivityTransitionState.readState(paramBundle);
    this.mVisibleFromClient = true ^ this.mWindow.getWindowStyle().getBoolean(10, false);
    this.mFragments.dispatchActivityCreated();
    this.mActivityTransitionState.setEnterActivityOptions(this, getActivityOptions());
    dispatchActivityPostCreated(paramBundle);
  }
  
  final void performDestroy() {
    dispatchActivityPreDestroyed();
    this.mDestroyed = true;
    this.mWindow.destroy();
    this.mFragments.dispatchDestroy();
    onDestroy();
    EventLogTags.writeWmOnDestroyCalled(this.mIdent, getComponentName().getClassName(), "performDestroy");
    this.mFragments.doLoaderDestroy();
    VoiceInteractor voiceInteractor = this.mVoiceInteractor;
    if (voiceInteractor != null)
      voiceInteractor.detachActivity(); 
    dispatchActivityPostDestroyed();
  }
  
  final void performNewIntent(Intent paramIntent) {
    this.mCanEnterPictureInPicture = true;
    onNewIntent(paramIntent);
  }
  
  final void performPause() {
    dispatchActivityPrePaused();
    this.mDoReportFullyDrawn = false;
    this.mFragments.dispatchPause();
    this.mCalled = false;
    onPause();
    EventLogTags.writeWmOnPausedCalled(this.mIdent, getComponentName().getClassName(), "performPause");
    this.mResumed = false;
    if (this.mCalled || (getApplicationInfo()).targetSdkVersion < 9) {
      dispatchActivityPostPaused();
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Activity ");
    stringBuilder.append(this.mComponent.toShortString());
    stringBuilder.append(" did not call through to super.onPause()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  final void performRestart(boolean paramBoolean, String paramString) {
    this.mCanEnterPictureInPicture = true;
    this.mFragments.noteStateNotSaved();
    if (this.mToken != null && this.mParent == null)
      WindowManagerGlobal.getInstance().setStoppedState(this.mToken, false); 
    if (this.mStopped) {
      this.mStopped = false;
      synchronized (this.mManagedCursors) {
        StringBuilder stringBuilder;
        int i = this.mManagedCursors.size();
        for (byte b = 0; b < i; b++) {
          ManagedCursor managedCursor = this.mManagedCursors.get(b);
          if (managedCursor.mReleased || managedCursor.mUpdated)
            if (managedCursor.mCursor.requery() || (getApplicationInfo()).targetSdkVersion < 14) {
              ManagedCursor.access$402(managedCursor, false);
              ManagedCursor.access$502(managedCursor, false);
            } else {
              IllegalStateException illegalStateException = new IllegalStateException();
              stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("trying to requery an already closed cursor  ");
              stringBuilder.append(managedCursor.mCursor);
              this(stringBuilder.toString());
              throw illegalStateException;
            }  
        } 
        this.mCalled = false;
        this.mInstrumentation.callActivityOnRestart(this);
        EventLogTags.writeWmOnRestartCalled(this.mIdent, getComponentName().getClassName(), (String)stringBuilder);
        if (this.mCalled) {
          if (paramBoolean)
            performStart((String)stringBuilder); 
        } else {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Activity ");
          stringBuilder.append(this.mComponent.toShortString());
          stringBuilder.append(" did not call through to super.onRestart()");
          throw new SuperNotCalledException(stringBuilder.toString());
        } 
      } 
    } 
  }
  
  final void performRestoreInstanceState(Bundle paramBundle) {
    onRestoreInstanceState(paramBundle);
    restoreManagedDialogs(paramBundle);
  }
  
  final void performRestoreInstanceState(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    onRestoreInstanceState(paramBundle, paramPersistableBundle);
    if (paramBundle != null)
      restoreManagedDialogs(paramBundle); 
  }
  
  final void performResume(boolean paramBoolean, String paramString) {
    dispatchActivityPreResumed();
    performRestart(true, paramString);
    this.mFragments.execPendingActions();
    this.mLastNonConfigurationInstances = null;
    if (this.mAutoFillResetNeeded)
      this.mAutoFillIgnoreFirstResumePause = paramBoolean; 
    this.mCalled = false;
    this.mInstrumentation.callActivityOnResume(this);
    EventLogTags.writeWmOnResumeCalled(this.mIdent, getComponentName().getClassName(), paramString);
    if (this.mCalled) {
      if (!this.mVisibleFromClient && !this.mFinished) {
        Log.w("Activity", "An activity without a UI must call finish() before onResume() completes");
        if ((getApplicationInfo()).targetSdkVersion > 22) {
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Activity ");
          stringBuilder2.append(this.mComponent.toShortString());
          stringBuilder2.append(" did not call finish() prior to onResume() completing");
          throw new IllegalStateException(stringBuilder2.toString());
        } 
      } 
      this.mCalled = false;
      this.mFragments.dispatchResume();
      this.mFragments.execPendingActions();
      onPostResume();
      if (this.mCalled) {
        dispatchActivityPostResumed();
        return;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Activity ");
      stringBuilder1.append(this.mComponent.toShortString());
      stringBuilder1.append(" did not call through to super.onPostResume()");
      throw new SuperNotCalledException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Activity ");
    stringBuilder.append(this.mComponent.toShortString());
    stringBuilder.append(" did not call through to super.onResume()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  final void performSaveInstanceState(Bundle paramBundle) {
    dispatchActivityPreSaveInstanceState(paramBundle);
    onSaveInstanceState(paramBundle);
    saveManagedDialogs(paramBundle);
    this.mActivityTransitionState.saveState(paramBundle);
    storeHasCurrentPermissionRequest(paramBundle);
    dispatchActivityPostSaveInstanceState(paramBundle);
  }
  
  final void performSaveInstanceState(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    dispatchActivityPreSaveInstanceState(paramBundle);
    onSaveInstanceState(paramBundle, paramPersistableBundle);
    saveManagedDialogs(paramBundle);
    storeHasCurrentPermissionRequest(paramBundle);
    dispatchActivityPostSaveInstanceState(paramBundle);
  }
  
  final void performStart(String paramString) {
    dispatchActivityPreStarted();
    this.mActivityTransitionState.setEnterActivityOptions(this, getActivityOptions());
    this.mFragments.noteStateNotSaved();
    this.mCalled = false;
    this.mFragments.execPendingActions();
    this.mInstrumentation.callActivityOnStart(this);
    EventLogTags.writeWmOnStartCalled(this.mIdent, getComponentName().getClassName(), paramString);
    if (this.mCalled) {
      boolean bool;
      this.mFragments.dispatchStart();
      this.mFragments.reportLoaderStart();
      if (((this.mApplication.getApplicationInfo()).flags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool) {
        String str = getDlWarning();
        if (str != null) {
          paramString = getApplicationInfo().loadLabel(getPackageManager()).toString();
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Detected problems with app native libraries\n(please consult log for detail):\n");
          stringBuilder1.append(str);
          String str1 = stringBuilder1.toString();
          if (bool) {
            (new AlertDialog.Builder((Context)this)).setTitle(paramString).setMessage(str1).setPositiveButton(17039370, (DialogInterface.OnClickListener)null).setCancelable(false).show();
          } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(paramString);
            stringBuilder2.append("\n");
            stringBuilder2.append(str1);
            Toast.makeText((Context)this, stringBuilder2.toString(), 1).show();
          } 
        } 
      } 
      GraphicsEnvironment.getInstance().showAngleInUseDialogBox((Context)this);
      this.mActivityTransitionState.enterReady(this);
      dispatchActivityPostStarted();
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Activity ");
    stringBuilder.append(this.mComponent.toShortString());
    stringBuilder.append(" did not call through to super.onStart()");
    throw new SuperNotCalledException(stringBuilder.toString());
  }
  
  final void performStop(boolean paramBoolean, String paramString) {
    this.mDoReportFullyDrawn = false;
    this.mFragments.doLoaderStop(this.mChangingConfigurations);
    this.mCanEnterPictureInPicture = false;
    if (!this.mStopped) {
      dispatchActivityPreStopped();
      Window window = this.mWindow;
      if (window != null)
        window.closeAllPanels(); 
      if (!paramBoolean && this.mToken != null && this.mParent == null)
        WindowManagerGlobal.getInstance().setStoppedState(this.mToken, true); 
      this.mFragments.dispatchStop();
      this.mCalled = false;
      this.mInstrumentation.callActivityOnStop(this);
      EventLogTags.writeWmOnStopCalled(this.mIdent, getComponentName().getClassName(), paramString);
      if (this.mCalled) {
        synchronized (this.mManagedCursors) {
          int i = this.mManagedCursors.size();
          for (byte b = 0; b < i; b++) {
            ManagedCursor managedCursor = this.mManagedCursors.get(b);
            if (!managedCursor.mReleased) {
              managedCursor.mCursor.deactivate();
              ManagedCursor.access$402(managedCursor, true);
            } 
          } 
          this.mStopped = true;
          dispatchActivityPostStopped();
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Activity ");
        stringBuilder.append(this.mComponent.toShortString());
        stringBuilder.append(" did not call through to super.onStop()");
        throw new SuperNotCalledException(stringBuilder.toString());
      } 
    } 
    this.mResumed = false;
  }
  
  final void performTopResumedActivityChanged(boolean paramBoolean, String paramString) {
    onTopResumedActivityChanged(paramBoolean);
    if (paramBoolean) {
      EventLogTags.writeWmOnTopResumedGainedCalled(this.mIdent, getComponentName().getClassName(), paramString);
    } else {
      EventLogTags.writeWmOnTopResumedLostCalled(this.mIdent, getComponentName().getClassName(), paramString);
    } 
  }
  
  final void performUserLeaving() {
    onUserInteraction();
    onUserLeaveHint();
  }
  
  public void postponeEnterTransition() {
    this.mActivityTransitionState.postponeEnterTransition();
  }
  
  public void recreate() {
    if (this.mParent == null) {
      if (Looper.myLooper() == this.mMainThread.getLooper()) {
        this.mMainThread.scheduleRelaunchActivity(this.mToken);
        return;
      } 
      throw new IllegalStateException("Must be called from main thread");
    } 
    throw new IllegalStateException("Can only be called on top-level activity");
  }
  
  public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks paramActivityLifecycleCallbacks) {
    synchronized (this.mActivityLifecycleCallbacks) {
      this.mActivityLifecycleCallbacks.add(paramActivityLifecycleCallbacks);
      return;
    } 
  }
  
  public void registerForContextMenu(View paramView) {
    paramView.setOnCreateContextMenuListener(this);
  }
  
  public void registerRemoteAnimations(RemoteAnimationDefinition paramRemoteAnimationDefinition) {
    try {
      ActivityTaskManager.getService().registerRemoteAnimations(this.mToken, paramRemoteAnimationDefinition);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean releaseInstance() {
    try {
      return ActivityTaskManager.getService().releaseActivityInstance(this.mToken);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  @Deprecated
  public final void removeDialog(int paramInt) {
    SparseArray<ManagedDialog> sparseArray = this.mManagedDialogs;
    if (sparseArray != null) {
      ManagedDialog managedDialog = (ManagedDialog)sparseArray.get(paramInt);
      if (managedDialog != null) {
        managedDialog.mDialog.dismiss();
        this.mManagedDialogs.remove(paramInt);
      } 
    } 
  }
  
  public void reportFullyDrawn() {
    if (this.mDoReportFullyDrawn) {
      this.mDoReportFullyDrawn = false;
      try {
        ActivityTaskManager.getService().reportActivityFullyDrawn(this.mToken, this.mRestoredFromBundle);
        VMRuntime.getRuntime().notifyStartupCompleted();
      } catch (RemoteException remoteException) {}
    } 
  }
  
  public DragAndDropPermissions requestDragAndDropPermissions(DragEvent paramDragEvent) {
    DragAndDropPermissions dragAndDropPermissions = DragAndDropPermissions.obtain(paramDragEvent);
    return (dragAndDropPermissions != null && dragAndDropPermissions.take(getActivityToken())) ? dragAndDropPermissions : null;
  }
  
  public final void requestPermissions(String[] paramArrayOfString, int paramInt) {
    if (paramInt >= 0) {
      if (this.mHasCurrentPermissionsRequest) {
        Log.w("Activity", "Can request only one set of permissions at a time");
        onRequestPermissionsResult(paramInt, new String[0], new int[0]);
        return;
      } 
      startActivityForResult("@android:requestPermissions:", getPackageManager().buildRequestPermissionsIntent(paramArrayOfString), paramInt, (Bundle)null);
      this.mHasCurrentPermissionsRequest = true;
      return;
    } 
    throw new IllegalArgumentException("requestCode should be >= 0");
  }
  
  public final void requestShowKeyboardShortcuts() {
    ComponentName componentName = ComponentName.unflattenFromString(getResources().getString(17039950));
    Intent intent = new Intent("com.android.intent.action.SHOW_KEYBOARD_SHORTCUTS");
    intent.setPackage(componentName.getPackageName());
    sendBroadcastAsUser(intent, Process.myUserHandle());
  }
  
  @Deprecated
  public boolean requestVisibleBehind(boolean paramBoolean) {
    return false;
  }
  
  public final boolean requestWindowFeature(int paramInt) {
    return getWindow().requestFeature(paramInt);
  }
  
  public final <T extends View> T requireViewById(int paramInt) {
    T t = (T)findViewById(paramInt);
    if (t != null)
      return t; 
    throw new IllegalArgumentException("ID does not reference a View inside this Activity");
  }
  
  NonConfigurationInstances retainNonConfigurationInstances() {
    Object object = onRetainNonConfigurationInstance();
    HashMap<String, Object> hashMap = onRetainNonConfigurationChildInstances();
    FragmentManagerNonConfig fragmentManagerNonConfig = this.mFragments.retainNestedNonConfig();
    this.mFragments.doLoaderStart();
    this.mFragments.doLoaderStop(true);
    ArrayMap<String, LoaderManager> arrayMap = this.mFragments.retainLoaderNonConfig();
    if (object == null && hashMap == null && fragmentManagerNonConfig == null && arrayMap == null && this.mVoiceInteractor == null)
      return null; 
    NonConfigurationInstances nonConfigurationInstances = new NonConfigurationInstances();
    nonConfigurationInstances.activity = object;
    nonConfigurationInstances.children = hashMap;
    nonConfigurationInstances.fragments = fragmentManagerNonConfig;
    nonConfigurationInstances.loaders = arrayMap;
    VoiceInteractor voiceInteractor = this.mVoiceInteractor;
    if (voiceInteractor != null) {
      voiceInteractor.retainInstance();
      nonConfigurationInstances.voiceInteractor = this.mVoiceInteractor;
    } 
    return nonConfigurationInstances;
  }
  
  public final void runOnUiThread(Runnable paramRunnable) {
    if (Thread.currentThread() != this.mUiThread) {
      this.mHandler.post(paramRunnable);
    } else {
      paramRunnable.run();
    } 
  }
  
  public void setActionBar(Toolbar paramToolbar) {
    ActionBar actionBar = getActionBar();
    if (!(actionBar instanceof WindowDecorActionBar)) {
      this.mMenuInflater = null;
      if (actionBar != null)
        actionBar.onDestroy(); 
      if (paramToolbar != null) {
        ToolbarActionBar toolbarActionBar = new ToolbarActionBar(paramToolbar, getTitle(), this);
        this.mActionBar = (ActionBar)toolbarActionBar;
        this.mWindow.setCallback(toolbarActionBar.getWrappedWindowCallback());
      } else {
        this.mActionBar = null;
        this.mWindow.setCallback(this);
      } 
      invalidateOptionsMenu();
      return;
    } 
    throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_ACTION_BAR and set android:windowActionBar to false in your theme to use a Toolbar instead.");
  }
  
  public void setContentTransitionManager(TransitionManager paramTransitionManager) {
    getWindow().setTransitionManager(paramTransitionManager);
  }
  
  public void setContentView(int paramInt) {
    getWindow().setContentView(paramInt);
    initWindowDecorActionBar();
  }
  
  public void setContentView(View paramView) {
    getWindow().setContentView(paramView);
    initWindowDecorActionBar();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
    getWindow().setContentView(paramView, paramLayoutParams);
    initWindowDecorActionBar();
  }
  
  public final void setDefaultKeyMode(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: putfield mDefaultKeyMode : I
    //   5: iload_1
    //   6: ifeq -> 61
    //   9: iload_1
    //   10: iconst_1
    //   11: if_icmpeq -> 40
    //   14: iload_1
    //   15: iconst_2
    //   16: if_icmpeq -> 61
    //   19: iload_1
    //   20: iconst_3
    //   21: if_icmpeq -> 40
    //   24: iload_1
    //   25: iconst_4
    //   26: if_icmpne -> 32
    //   29: goto -> 40
    //   32: new java/lang/IllegalArgumentException
    //   35: dup
    //   36: invokespecial <init> : ()V
    //   39: athrow
    //   40: new android/text/SpannableStringBuilder
    //   43: dup
    //   44: invokespecial <init> : ()V
    //   47: astore_2
    //   48: aload_0
    //   49: aload_2
    //   50: putfield mDefaultKeySsb : Landroid/text/SpannableStringBuilder;
    //   53: aload_2
    //   54: iconst_0
    //   55: invokestatic setSelection : (Landroid/text/Spannable;I)V
    //   58: goto -> 66
    //   61: aload_0
    //   62: aconst_null
    //   63: putfield mDefaultKeySsb : Landroid/text/SpannableStringBuilder;
    //   66: return
  }
  
  public void setDisablePreviewScreenshots(boolean paramBoolean) {
    try {
      ActivityTaskManager.getService().setDisablePreviewScreenshots(this.mToken, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setEnterSharedElementCallback(SharedElementCallback paramSharedElementCallback) {
    SharedElementCallback sharedElementCallback = paramSharedElementCallback;
    if (paramSharedElementCallback == null)
      sharedElementCallback = SharedElementCallback.NULL_CALLBACK; 
    this.mEnterTransitionListener = sharedElementCallback;
  }
  
  public void setExitSharedElementCallback(SharedElementCallback paramSharedElementCallback) {
    SharedElementCallback sharedElementCallback = paramSharedElementCallback;
    if (paramSharedElementCallback == null)
      sharedElementCallback = SharedElementCallback.NULL_CALLBACK; 
    this.mExitTransitionListener = sharedElementCallback;
  }
  
  public final void setFeatureDrawable(int paramInt, Drawable paramDrawable) {
    getWindow().setFeatureDrawable(paramInt, paramDrawable);
  }
  
  public final void setFeatureDrawableAlpha(int paramInt1, int paramInt2) {
    getWindow().setFeatureDrawableAlpha(paramInt1, paramInt2);
  }
  
  public final void setFeatureDrawableResource(int paramInt1, int paramInt2) {
    getWindow().setFeatureDrawableResource(paramInt1, paramInt2);
  }
  
  public final void setFeatureDrawableUri(int paramInt, Uri paramUri) {
    getWindow().setFeatureDrawableUri(paramInt, paramUri);
  }
  
  public void setFinishOnTouchOutside(boolean paramBoolean) {
    this.mWindow.setCloseOnTouchOutside(paramBoolean);
  }
  
  public void setImmersive(boolean paramBoolean) {
    try {
      ActivityTaskManager.getService().setImmersive(this.mToken, paramBoolean);
    } catch (RemoteException remoteException) {}
  }
  
  public void setInheritShowWhenLocked(boolean paramBoolean) {
    try {
      ActivityTaskManager.getService().setInheritShowWhenLocked(this.mToken, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setIntent(Intent paramIntent) {
    this.mIntent = paramIntent;
  }
  
  public void setLocusContext(LocusId paramLocusId, Bundle paramBundle) {
    try {
      ActivityManager.getService().setActivityLocusContext(this.mComponent, paramLocusId, this.mToken);
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
    if (paramLocusId != null)
      setLocusContextToContentCapture(paramLocusId, paramBundle); 
  }
  
  public final void setMediaController(MediaController paramMediaController) {
    getWindow().setMediaController(paramMediaController);
  }
  
  public void setOverlayWithDecorCaptionEnabled(boolean paramBoolean) {
    this.mWindow.setOverlayWithDecorCaptionEnabled(paramBoolean);
  }
  
  final void setParent(Activity paramActivity) {
    this.mParent = paramActivity;
  }
  
  @Deprecated
  public void setPersistent(boolean paramBoolean) {}
  
  public void setPictureInPictureParams(PictureInPictureParams paramPictureInPictureParams) {
    try {
      if (!deviceSupportsPictureInPictureMode())
        return; 
      if (paramPictureInPictureParams != null) {
        ActivityTaskManager.getService().setPictureInPictureParams(this.mToken, paramPictureInPictureParams);
      } else {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        this("Expected non-null picture-in-picture params");
        throw illegalArgumentException;
      } 
    } catch (RemoteException remoteException) {}
  }
  
  @Deprecated
  public final void setProgress(int paramInt) {
    getWindow().setFeatureInt(2, paramInt + 0);
  }
  
  @Deprecated
  public final void setProgressBarIndeterminate(boolean paramBoolean) {
    byte b;
    Window window = getWindow();
    if (paramBoolean) {
      b = -3;
    } else {
      b = -4;
    } 
    window.setFeatureInt(2, b);
  }
  
  @Deprecated
  public final void setProgressBarIndeterminateVisibility(boolean paramBoolean) {
    byte b;
    Window window = getWindow();
    if (paramBoolean) {
      b = -1;
    } else {
      b = -2;
    } 
    window.setFeatureInt(5, b);
  }
  
  @Deprecated
  public final void setProgressBarVisibility(boolean paramBoolean) {
    byte b;
    Window window = getWindow();
    if (paramBoolean) {
      b = -1;
    } else {
      b = -2;
    } 
    window.setFeatureInt(2, b);
  }
  
  public void setRequestedOrientation(int paramInt) {
    Activity activity = this.mParent;
    if (activity == null) {
      try {
        ActivityTaskManager.getService().setRequestedOrientation(this.mToken, paramInt);
      } catch (RemoteException remoteException) {}
    } else {
      remoteException.setRequestedOrientation(paramInt);
    } 
  }
  
  public final void setResult(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield mResultCode : I
    //   7: aload_0
    //   8: aconst_null
    //   9: putfield mResultData : Landroid/content/Intent;
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_2
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_2
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	15	finally
    //   16	18	15	finally
  }
  
  public final void setResult(int paramInt, Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield mResultCode : I
    //   7: aload_0
    //   8: aload_2
    //   9: putfield mResultData : Landroid/content/Intent;
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_2
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_2
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	15	finally
    //   16	18	15	finally
  }
  
  @Deprecated
  public final void setSecondaryProgress(int paramInt) {
    getWindow().setFeatureInt(2, paramInt + 20000);
  }
  
  public void setShowWhenLocked(boolean paramBoolean) {
    try {
      ActivityTaskManager.getService().setShowWhenLocked(this.mToken, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setTaskDescription(ActivityManager.TaskDescription paramTaskDescription) {
    ActivityManager.TaskDescription taskDescription = this.mTaskDescription;
    if (taskDescription != paramTaskDescription) {
      taskDescription.copyFromPreserveHiddenFields(paramTaskDescription);
      if (paramTaskDescription.getIconFilename() == null && paramTaskDescription.getIcon() != null) {
        int i = ActivityManager.getLauncherLargeIconSizeInner((Context)this);
        Bitmap bitmap = Bitmap.createScaledBitmap(paramTaskDescription.getIcon(), i, i, true);
        this.mTaskDescription.setIcon(Icon.createWithBitmap(bitmap));
      } 
    } 
    try {
      ActivityTaskManager.getService().setTaskDescription(this.mToken, this.mTaskDescription);
    } catch (RemoteException remoteException) {}
  }
  
  public void setTheme(int paramInt) {
    super.setTheme(paramInt);
    this.mWindow.setTheme(paramInt);
  }
  
  public void setTitle(int paramInt) {
    setTitle(getText(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence) {
    this.mTitle = paramCharSequence;
    onTitleChanged(paramCharSequence, this.mTitleColor);
    Activity activity = this.mParent;
    if (activity != null)
      activity.onChildTitleChanged(this, paramCharSequence); 
  }
  
  @Deprecated
  public void setTitleColor(int paramInt) {
    this.mTitleColor = paramInt;
    onTitleChanged(this.mTitle, paramInt);
  }
  
  public boolean setTranslucent(boolean paramBoolean) {
    return paramBoolean ? convertToTranslucent((TranslucentConversionListener)null, (ActivityOptions)null) : convertFromTranslucentInternal();
  }
  
  public void setTurnScreenOn(boolean paramBoolean) {
    try {
      ActivityTaskManager.getService().setTurnScreenOn(this.mToken, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setVisible(boolean paramBoolean) {
    if (this.mVisibleFromClient != paramBoolean) {
      this.mVisibleFromClient = paramBoolean;
      if (this.mVisibleFromServer)
        if (paramBoolean) {
          makeVisible();
        } else {
          this.mDecor.setVisibility(4);
        }  
    } 
  }
  
  void setVoiceInteractor(IVoiceInteractor paramIVoiceInteractor) {
    VoiceInteractor voiceInteractor = this.mVoiceInteractor;
    if (voiceInteractor != null && voiceInteractor.getActiveRequests() != null)
      for (VoiceInteractor.Request request : this.mVoiceInteractor.getActiveRequests()) {
        request.cancel();
        request.clear();
      }  
    if (paramIVoiceInteractor == null) {
      this.mVoiceInteractor = null;
    } else {
      this.mVoiceInteractor = new VoiceInteractor(paramIVoiceInteractor, (Context)this, this, Looper.myLooper());
    } 
  }
  
  public final void setVolumeControlStream(int paramInt) {
    getWindow().setVolumeControlStream(paramInt);
  }
  
  public void setVrModeEnabled(boolean paramBoolean, ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    try {
      if (ActivityTaskManager.getService().setVrMode(this.mToken, paramBoolean, paramComponentName) != 0) {
        PackageManager.NameNotFoundException nameNotFoundException = new PackageManager.NameNotFoundException();
        this(paramComponentName.flattenToString());
        throw nameNotFoundException;
      } 
    } catch (RemoteException remoteException) {}
  }
  
  public boolean shouldShowRequestPermissionRationale(String paramString) {
    return getPackageManager().shouldShowRequestPermissionRationale(paramString);
  }
  
  public boolean shouldUpRecreateTask(Intent paramIntent) {
    try {
      PackageManager packageManager = getPackageManager();
      ComponentName componentName1 = paramIntent.getComponent();
      ComponentName componentName2 = componentName1;
      if (componentName1 == null)
        componentName2 = paramIntent.resolveActivity(packageManager); 
      ActivityInfo activityInfo = packageManager.getActivityInfo(componentName2, 0);
      return (activityInfo.taskAffinity == null) ? false : ActivityTaskManager.getService().shouldUpRecreateTask(this.mToken, activityInfo.taskAffinity);
    } catch (RemoteException remoteException) {
      return false;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return false;
    } 
  }
  
  public boolean showAssist(Bundle paramBundle) {
    try {
      return ActivityTaskManager.getService().showAssistFromActivity(this.mToken, paramBundle);
    } catch (RemoteException remoteException) {
      return false;
    } 
  }
  
  @Deprecated
  public final void showDialog(int paramInt) {
    showDialog(paramInt, (Bundle)null);
  }
  
  @Deprecated
  public final boolean showDialog(int paramInt, Bundle paramBundle) {
    if (this.mManagedDialogs == null)
      this.mManagedDialogs = new SparseArray(); 
    ManagedDialog managedDialog1 = (ManagedDialog)this.mManagedDialogs.get(paramInt);
    ManagedDialog managedDialog2 = managedDialog1;
    if (managedDialog1 == null) {
      managedDialog2 = new ManagedDialog();
      managedDialog2.mDialog = createDialog(Integer.valueOf(paramInt), (Bundle)null, paramBundle);
      if (managedDialog2.mDialog == null)
        return false; 
      this.mManagedDialogs.put(paramInt, managedDialog2);
    } 
    managedDialog2.mArgs = paramBundle;
    onPrepareDialog(paramInt, managedDialog2.mDialog, paramBundle);
    managedDialog2.mDialog.show();
    return true;
  }
  
  public void showLockTaskEscapeMessage() {
    try {
      ActivityTaskManager.getService().showLockTaskEscapeMessage(this.mToken);
    } catch (RemoteException remoteException) {}
  }
  
  public ActionMode startActionMode(ActionMode.Callback paramCallback) {
    return this.mWindow.getDecorView().startActionMode(paramCallback);
  }
  
  public ActionMode startActionMode(ActionMode.Callback paramCallback, int paramInt) {
    return this.mWindow.getDecorView().startActionMode(paramCallback, paramInt);
  }
  
  public void startActivities(Intent[] paramArrayOfIntent) {
    startActivities(paramArrayOfIntent, (Bundle)null);
  }
  
  public void startActivities(Intent[] paramArrayOfIntent, Bundle paramBundle) {
    this.mInstrumentation.execStartActivities((Context)this, (IBinder)this.mMainThread.getApplicationThread(), this.mToken, this, paramArrayOfIntent, paramBundle);
  }
  
  public void startActivity(Intent paramIntent) {
    startActivity(paramIntent, (Bundle)null);
  }
  
  public void startActivity(Intent paramIntent, Bundle paramBundle) {
    Intent intent = this.mIntent;
    if (intent != null && intent.hasExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN") && this.mIntent.hasExtra("android.view.autofill.extra.RESTORE_CROSS_ACTIVITY") && TextUtils.equals(getPackageName(), paramIntent.resolveActivity(getPackageManager()).getPackageName())) {
      IBinder iBinder = this.mIntent.getIBinderExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN");
      this.mIntent.removeExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN");
      this.mIntent.removeExtra("android.view.autofill.extra.RESTORE_CROSS_ACTIVITY");
      paramIntent.putExtra("android.view.autofill.extra.RESTORE_SESSION_TOKEN", iBinder);
      paramIntent.putExtra("android.view.autofill.extra.RESTORE_CROSS_ACTIVITY", true);
    } 
    if (paramBundle != null) {
      startActivityForResult(paramIntent, -1, paramBundle);
    } else {
      startActivityForResult(paramIntent, -1);
    } 
  }
  
  public void startActivityAsCaller(Intent paramIntent, Bundle paramBundle, IBinder paramIBinder, boolean paramBoolean, int paramInt) {
    if (this.mParent == null) {
      paramBundle = transferSpringboardActivityOptions(paramBundle);
      Instrumentation.ActivityResult activityResult = this.mInstrumentation.execStartActivityAsCaller((Context)this, (IBinder)this.mMainThread.getApplicationThread(), this.mToken, this, paramIntent, -1, paramBundle, paramIBinder, paramBoolean, paramInt);
      if (activityResult != null)
        this.mMainThread.sendActivityResult(this.mToken, this.mEmbeddedID, -1, activityResult.getResultCode(), activityResult.getResultData()); 
      cancelInputsAndStartExitTransition(paramBundle);
      return;
    } 
    throw new RuntimeException("Can't be called from a child");
  }
  
  public void startActivityAsUser(Intent paramIntent, Bundle paramBundle, UserHandle paramUserHandle) {
    if (this.mParent == null) {
      paramBundle = transferSpringboardActivityOptions(paramBundle);
      Instrumentation.ActivityResult activityResult = this.mInstrumentation.execStartActivity((Context)this, (IBinder)this.mMainThread.getApplicationThread(), this.mToken, this.mEmbeddedID, paramIntent, -1, paramBundle, paramUserHandle);
      if (activityResult != null)
        this.mMainThread.sendActivityResult(this.mToken, this.mEmbeddedID, -1, activityResult.getResultCode(), activityResult.getResultData()); 
      cancelInputsAndStartExitTransition(paramBundle);
      return;
    } 
    throw new RuntimeException("Can't be called from a child");
  }
  
  public void startActivityAsUser(Intent paramIntent, UserHandle paramUserHandle) {
    startActivityAsUser(paramIntent, (Bundle)null, paramUserHandle);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt) {
    startActivityForResult(paramIntent, paramInt, (Bundle)null);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, Bundle paramBundle) {
    Instrumentation.ActivityResult activityResult;
    Activity activity = this.mParent;
    if (activity == null) {
      paramBundle = transferSpringboardActivityOptions(paramBundle);
      activityResult = this.mInstrumentation.execStartActivity((Context)this, (IBinder)this.mMainThread.getApplicationThread(), this.mToken, this, paramIntent, paramInt, paramBundle);
      if (activityResult != null)
        this.mMainThread.sendActivityResult(this.mToken, this.mEmbeddedID, paramInt, activityResult.getResultCode(), activityResult.getResultData()); 
      if (paramInt >= 0)
        this.mStartedActivity = true; 
      cancelInputsAndStartExitTransition(paramBundle);
    } else if (paramBundle != null) {
      activity.startActivityFromChild(this, (Intent)activityResult, paramInt, paramBundle);
    } else {
      activity.startActivityFromChild(this, (Intent)activityResult, paramInt);
    } 
  }
  
  public void startActivityForResult(String paramString, Intent paramIntent, int paramInt, Bundle paramBundle) {
    Uri uri = onProvideReferrer();
    if (uri != null)
      paramIntent.putExtra("android.intent.extra.REFERRER", (Parcelable)uri); 
    paramBundle = transferSpringboardActivityOptions(paramBundle);
    Instrumentation.ActivityResult activityResult = this.mInstrumentation.execStartActivity((Context)this, (IBinder)this.mMainThread.getApplicationThread(), this.mToken, paramString, paramIntent, paramInt, paramBundle);
    if (activityResult != null)
      this.mMainThread.sendActivityResult(this.mToken, paramString, paramInt, activityResult.getResultCode(), activityResult.getResultData()); 
    cancelInputsAndStartExitTransition(paramBundle);
  }
  
  public void startActivityForResultAsUser(Intent paramIntent, int paramInt, Bundle paramBundle, UserHandle paramUserHandle) {
    startActivityForResultAsUser(paramIntent, this.mEmbeddedID, paramInt, paramBundle, paramUserHandle);
  }
  
  public void startActivityForResultAsUser(Intent paramIntent, int paramInt, UserHandle paramUserHandle) {
    startActivityForResultAsUser(paramIntent, paramInt, (Bundle)null, paramUserHandle);
  }
  
  public void startActivityForResultAsUser(Intent paramIntent, String paramString, int paramInt, Bundle paramBundle, UserHandle paramUserHandle) {
    if (this.mParent == null) {
      paramBundle = transferSpringboardActivityOptions(paramBundle);
      Instrumentation.ActivityResult activityResult = this.mInstrumentation.execStartActivity((Context)this, (IBinder)this.mMainThread.getApplicationThread(), this.mToken, paramString, paramIntent, paramInt, paramBundle, paramUserHandle);
      if (activityResult != null)
        this.mMainThread.sendActivityResult(this.mToken, this.mEmbeddedID, paramInt, activityResult.getResultCode(), activityResult.getResultData()); 
      if (paramInt >= 0)
        this.mStartedActivity = true; 
      cancelInputsAndStartExitTransition(paramBundle);
      return;
    } 
    throw new RuntimeException("Can't be called from a child");
  }
  
  @Deprecated
  public void startActivityFromChild(Activity paramActivity, Intent paramIntent, int paramInt) {
    startActivityFromChild(paramActivity, paramIntent, paramInt, (Bundle)null);
  }
  
  @Deprecated
  public void startActivityFromChild(Activity paramActivity, Intent paramIntent, int paramInt, Bundle paramBundle) {
    paramBundle = transferSpringboardActivityOptions(paramBundle);
    Instrumentation.ActivityResult activityResult = this.mInstrumentation.execStartActivity((Context)this, (IBinder)this.mMainThread.getApplicationThread(), this.mToken, paramActivity, paramIntent, paramInt, paramBundle);
    if (activityResult != null)
      this.mMainThread.sendActivityResult(this.mToken, paramActivity.mEmbeddedID, paramInt, activityResult.getResultCode(), activityResult.getResultData()); 
    cancelInputsAndStartExitTransition(paramBundle);
  }
  
  @Deprecated
  public void startActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt) {
    startActivityFromFragment(paramFragment, paramIntent, paramInt, (Bundle)null);
  }
  
  @Deprecated
  public void startActivityFromFragment(Fragment paramFragment, Intent paramIntent, int paramInt, Bundle paramBundle) {
    startActivityForResult(paramFragment.mWho, paramIntent, paramInt, paramBundle);
  }
  
  public boolean startActivityIfNeeded(Intent paramIntent, int paramInt) {
    return startActivityIfNeeded(paramIntent, paramInt, (Bundle)null);
  }
  
  public boolean startActivityIfNeeded(Intent paramIntent, int paramInt, Bundle paramBundle) {
    if (this.mParent == null) {
      boolean bool2;
      boolean bool1 = true;
      try {
        Uri uri = onProvideReferrer();
        if (uri != null)
          paramIntent.putExtra("android.intent.extra.REFERRER", (Parcelable)uri); 
        paramIntent.migrateExtraStreamToClipData((Context)this);
        paramIntent.prepareToLeaveProcess((Context)this);
        bool2 = ActivityTaskManager.getService().startActivity(this.mMainThread.getApplicationThread(), getBasePackageName(), getAttributionTag(), paramIntent, paramIntent.resolveTypeIfNeeded(getContentResolver()), this.mToken, this.mEmbeddedID, paramInt, 1, null, paramBundle);
      } catch (RemoteException remoteException) {
        bool2 = bool1;
      } 
      Instrumentation.checkStartActivityResult(bool2, paramIntent);
      boolean bool3 = true;
      if (paramInt >= 0)
        this.mStartedActivity = true; 
      if (bool2 == true)
        bool3 = false; 
      return bool3;
    } 
    throw new UnsupportedOperationException("startActivityIfNeeded can only be called from a top-level activity");
  }
  
  public void startIntentSender(IntentSender paramIntentSender, Intent paramIntent, int paramInt1, int paramInt2, int paramInt3) throws IntentSender.SendIntentException {
    startIntentSender(paramIntentSender, paramIntent, paramInt1, paramInt2, paramInt3, (Bundle)null);
  }
  
  public void startIntentSender(IntentSender paramIntentSender, Intent paramIntent, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) throws IntentSender.SendIntentException {
    if (paramBundle != null) {
      startIntentSenderForResult(paramIntentSender, -1, paramIntent, paramInt1, paramInt2, paramInt3, paramBundle);
    } else {
      startIntentSenderForResult(paramIntentSender, -1, paramIntent, paramInt1, paramInt2, paramInt3);
    } 
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4) throws IntentSender.SendIntentException {
    startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, (Bundle)null);
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    Activity activity = this.mParent;
    if (activity == null) {
      startIntentSenderForResultInner(paramIntentSender, this.mEmbeddedID, paramInt1, paramIntent, paramInt2, paramInt3, paramBundle);
    } else if (paramBundle != null) {
      activity.startIntentSenderFromChild(this, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
    } else {
      activity.startIntentSenderFromChild(this, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4);
    } 
  }
  
  @Deprecated
  public void startIntentSenderFromChild(Activity paramActivity, IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4) throws IntentSender.SendIntentException {
    startIntentSenderFromChild(paramActivity, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, (Bundle)null);
  }
  
  @Deprecated
  public void startIntentSenderFromChild(Activity paramActivity, IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle) throws IntentSender.SendIntentException {
    startIntentSenderForResultInner(paramIntentSender, paramActivity.mEmbeddedID, paramInt1, paramIntent, paramInt2, paramInt3, paramBundle);
  }
  
  public void startLocalVoiceInteraction(Bundle paramBundle) {
    try {
      ActivityTaskManager.getService().startLocalVoiceInteraction(this.mToken, paramBundle);
    } catch (RemoteException remoteException) {}
  }
  
  public void startLockTask() {
    try {
      ActivityTaskManager.getService().startLockTaskModeByToken(this.mToken);
    } catch (RemoteException remoteException) {}
  }
  
  @Deprecated
  public void startManagingCursor(Cursor paramCursor) {
    synchronized (this.mManagedCursors) {
      ArrayList<ManagedCursor> arrayList = this.mManagedCursors;
      ManagedCursor managedCursor = new ManagedCursor();
      this(paramCursor);
      arrayList.add(managedCursor);
      return;
    } 
  }
  
  public boolean startNextMatchingActivity(Intent paramIntent) {
    return startNextMatchingActivity(paramIntent, (Bundle)null);
  }
  
  public boolean startNextMatchingActivity(Intent paramIntent, Bundle paramBundle) {
    if (this.mParent == null)
      try {
        paramIntent.migrateExtraStreamToClipData((Context)this);
        paramIntent.prepareToLeaveProcess((Context)this);
        return ActivityTaskManager.getService().startNextMatchingActivity(this.mToken, paramIntent, paramBundle);
      } catch (RemoteException remoteException) {
        return false;
      }  
    throw new UnsupportedOperationException("startNextMatchingActivity can only be called from a top-level activity");
  }
  
  public void startPostponedEnterTransition() {
    this.mActivityTransitionState.startPostponedEnterTransition();
  }
  
  public void startSearch(String paramString, boolean paramBoolean1, Bundle paramBundle, boolean paramBoolean2) {
    ensureSearchManager();
    this.mSearchManager.startSearch(paramString, paramBoolean1, getComponentName(), paramBundle, paramBoolean2);
  }
  
  public void stopLocalVoiceInteraction() {
    try {
      ActivityTaskManager.getService().stopLocalVoiceInteraction(this.mToken);
    } catch (RemoteException remoteException) {}
  }
  
  public void stopLockTask() {
    try {
      ActivityTaskManager.getService().stopLockTaskModeByToken(this.mToken);
    } catch (RemoteException remoteException) {}
  }
  
  @Deprecated
  public void stopManagingCursor(Cursor paramCursor) {
    synchronized (this.mManagedCursors) {
      int i = this.mManagedCursors.size();
      for (byte b = 0; b < i; b++) {
        if ((this.mManagedCursors.get(b)).mCursor == paramCursor) {
          this.mManagedCursors.remove(b);
          break;
        } 
      } 
      return;
    } 
  }
  
  public void takeKeyEvents(boolean paramBoolean) {
    getWindow().takeKeyEvents(paramBoolean);
  }
  
  public void triggerSearch(String paramString, Bundle paramBundle) {
    ensureSearchManager();
    this.mSearchManager.triggerSearch(paramString, getComponentName(), paramBundle);
  }
  
  public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks paramActivityLifecycleCallbacks) {
    synchronized (this.mActivityLifecycleCallbacks) {
      this.mActivityLifecycleCallbacks.remove(paramActivityLifecycleCallbacks);
      return;
    } 
  }
  
  public void unregisterForContextMenu(View paramView) {
    paramView.setOnCreateContextMenuListener(null);
  }
  
  public void unregisterRemoteAnimations() {
    try {
      ActivityTaskManager.getService().unregisterRemoteAnimations(this.mToken);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface ContentCaptureNotificationType {}
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface DefaultKeyMode {}
  
  class HostCallbacks extends FragmentHostCallback<Activity> {
    public HostCallbacks() {
      super(Activity.this);
    }
    
    public void onAttachFragment(Fragment param1Fragment) {
      Activity.this.onAttachFragment(param1Fragment);
    }
    
    public void onDump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      Activity.this.dump(param1String, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
    }
    
    public <T extends View> T onFindViewById(int param1Int) {
      return Activity.this.findViewById(param1Int);
    }
    
    public Activity onGetHost() {
      return Activity.this;
    }
    
    public LayoutInflater onGetLayoutInflater() {
      LayoutInflater layoutInflater = Activity.this.getLayoutInflater();
      return onUseFragmentManagerInflaterFactory() ? layoutInflater.cloneInContext((Context)Activity.this) : layoutInflater;
    }
    
    public int onGetWindowAnimations() {
      int i;
      Window window = Activity.this.getWindow();
      if (window == null) {
        i = 0;
      } else {
        i = (window.getAttributes()).windowAnimations;
      } 
      return i;
    }
    
    public boolean onHasView() {
      boolean bool;
      Window window = Activity.this.getWindow();
      if (window != null && window.peekDecorView() != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean onHasWindowAnimations() {
      boolean bool;
      if (Activity.this.getWindow() != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onInvalidateOptionsMenu() {
      Activity.this.invalidateOptionsMenu();
    }
    
    public void onRequestPermissionsFromFragment(Fragment param1Fragment, String[] param1ArrayOfString, int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("@android:requestPermissions:");
      stringBuilder.append(param1Fragment.mWho);
      String str = stringBuilder.toString();
      Intent intent = Activity.this.getPackageManager().buildRequestPermissionsIntent(param1ArrayOfString);
      Activity.this.startActivityForResult(str, intent, param1Int, (Bundle)null);
    }
    
    public boolean onShouldSaveFragmentState(Fragment param1Fragment) {
      return Activity.this.isFinishing() ^ true;
    }
    
    public void onStartActivityAsUserFromFragment(Fragment param1Fragment, Intent param1Intent, int param1Int, Bundle param1Bundle, UserHandle param1UserHandle) {
      Activity.this.startActivityAsUserFromFragment(param1Fragment, param1Intent, param1Int, param1Bundle, param1UserHandle);
    }
    
    public void onStartActivityFromFragment(Fragment param1Fragment, Intent param1Intent, int param1Int, Bundle param1Bundle) {
      Activity.this.startActivityFromFragment(param1Fragment, param1Intent, param1Int, param1Bundle);
    }
    
    public void onStartIntentSenderFromFragment(Fragment param1Fragment, IntentSender param1IntentSender, int param1Int1, Intent param1Intent, int param1Int2, int param1Int3, int param1Int4, Bundle param1Bundle) throws IntentSender.SendIntentException {
      if (Activity.this.mParent == null) {
        Activity.this.startIntentSenderForResultInner(param1IntentSender, param1Fragment.mWho, param1Int1, param1Intent, param1Int2, param1Int3, param1Bundle);
      } else if (param1Bundle != null) {
        Activity.this.mParent.startIntentSenderFromFragment(param1Fragment, param1IntentSender, param1Int1, param1Intent, param1Int2, param1Int3, param1Bundle);
      } 
    }
    
    public boolean onUseFragmentManagerInflaterFactory() {
      boolean bool;
      if ((Activity.this.getApplicationInfo()).targetSdkVersion >= 21) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
  }
  
  private static final class ManagedCursor {
    private final Cursor mCursor;
    
    private boolean mReleased;
    
    private boolean mUpdated;
    
    ManagedCursor(Cursor param1Cursor) {
      this.mCursor = param1Cursor;
      this.mReleased = false;
      this.mUpdated = false;
    }
  }
  
  private static class ManagedDialog {
    Bundle mArgs;
    
    Dialog mDialog;
    
    private ManagedDialog() {}
  }
  
  static final class NonConfigurationInstances {
    Object activity;
    
    HashMap<String, Object> children;
    
    FragmentManagerNonConfig fragments;
    
    ArrayMap<String, LoaderManager> loaders;
    
    VoiceInteractor voiceInteractor;
  }
  
  private static final class RequestFinishCallback extends IRequestFinishCallback.Stub {
    private final WeakReference<Activity> mActivityRef;
    
    RequestFinishCallback(WeakReference<Activity> param1WeakReference) {
      this.mActivityRef = param1WeakReference;
    }
    
    public void requestFinish() {
      Activity activity = this.mActivityRef.get();
      if (activity != null) {
        Handler handler = activity.mHandler;
        Objects.requireNonNull(activity);
        handler.post(new _$$Lambda$thfU5Zh_cKOR8p7IfITtlg111Go(activity));
      } 
    }
  }
  
  @SystemApi
  public static interface TranslucentConversionListener {
    void onTranslucentConversionComplete(boolean param1Boolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Activity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */