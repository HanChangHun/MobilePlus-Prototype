package android.app;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.Parcelable;
import android.os.PerformanceCollector;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.TestLooperManager;
import android.os.UserHandle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.IWindowManager;
import android.view.InputEvent;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManagerGlobal;
import com.android.internal.content.ReferrerIntent;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class Instrumentation {
  public static final String REPORT_KEY_IDENTIFIER = "id";
  
  public static final String REPORT_KEY_STREAMRESULT = "stream";
  
  private static final String TAG = "Instrumentation";
  
  private List<ActivityMonitor> mActivityMonitors;
  
  private final Object mAnimationCompleteLock = new Object();
  
  private Context mAppContext;
  
  private boolean mAutomaticPerformanceSnapshots = false;
  
  private ComponentName mComponent;
  
  private Context mInstrContext;
  
  private MessageQueue mMessageQueue = null;
  
  private Bundle mPerfMetrics = new Bundle();
  
  private PerformanceCollector mPerformanceCollector;
  
  private Thread mRunner;
  
  private final Object mSync = new Object();
  
  private ActivityThread mThread = null;
  
  private UiAutomation mUiAutomation;
  
  private IUiAutomationConnection mUiAutomationConnection;
  
  private List<ActivityWaiter> mWaitingActivities;
  
  private IInstrumentationWatcher mWatcher;
  
  private void addValue(String paramString, int paramInt, Bundle paramBundle) {
    ArrayList<Integer> arrayList;
    if (paramBundle.containsKey(paramString)) {
      arrayList = paramBundle.getIntegerArrayList(paramString);
      if (arrayList != null)
        arrayList.add(Integer.valueOf(paramInt)); 
    } else {
      ArrayList<Integer> arrayList1 = new ArrayList();
      arrayList1.add(Integer.valueOf(paramInt));
      paramBundle.putIntegerArrayList((String)arrayList, arrayList1);
    } 
  }
  
  private void checkInstrumenting(String paramString) {
    if (this.mInstrContext != null)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" cannot be called outside of instrumented processes");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public static void checkStartActivityResult(int paramInt, Object paramObject) {
    StringBuilder stringBuilder;
    if (!ActivityManager.isStartResultFatalError(paramInt))
      return; 
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown error code ");
        stringBuilder.append(paramInt);
        stringBuilder.append(" when starting ");
        stringBuilder.append(paramObject);
        throw new AndroidRuntimeException(stringBuilder.toString());
      case -89:
        throw new IllegalStateException("Session calling startAssistantActivity does not match active session");
      case -90:
        throw new IllegalStateException("Cannot start assistant activity on a hidden session");
      case -92:
      case -91:
        if (paramObject instanceof Intent && ((Intent)paramObject).getComponent() != null) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unable to find explicit activity class ");
          stringBuilder.append(((Intent)paramObject).getComponent().toShortString());
          stringBuilder.append("; have you declared this activity in your AndroidManifest.xml?");
          throw new ActivityNotFoundException(stringBuilder.toString());
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append("No Activity found to handle ");
        stringBuilder.append(paramObject);
        throw new ActivityNotFoundException(stringBuilder.toString());
      case -93:
        throw new AndroidRuntimeException("FORWARD_RESULT_FLAG used while also requesting a result");
      case -94:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Not allowed to start activity ");
        stringBuilder.append(paramObject);
        throw new SecurityException(stringBuilder.toString());
      case -95:
        throw new IllegalArgumentException("PendingIntent is not an activity");
      case -96:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Activity could not be started for ");
        stringBuilder.append(paramObject);
        throw new AndroidRuntimeException(stringBuilder.toString());
      case -97:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Starting under voice control not allowed for: ");
        stringBuilder.append(paramObject);
        throw new SecurityException(stringBuilder.toString());
      case -99:
        throw new IllegalStateException("Session calling startVoiceActivity does not match active session");
      case -100:
        break;
    } 
    throw new IllegalStateException("Cannot start voice activity on a hidden session");
  }
  
  private AppComponentFactory getFactory(String paramString) {
    if (paramString == null) {
      Log.e("Instrumentation", "No pkg specified, disabling AppComponentFactory");
      return AppComponentFactory.DEFAULT;
    } 
    ActivityThread activityThread = this.mThread;
    if (activityThread == null) {
      Log.e("Instrumentation", "Uninitialized ActivityThread, likely app-created Instrumentation, disabling AppComponentFactory", new Throwable());
      return AppComponentFactory.DEFAULT;
    } 
    LoadedApk loadedApk2 = activityThread.peekPackageInfo(paramString, true);
    LoadedApk loadedApk1 = loadedApk2;
    if (loadedApk2 == null)
      loadedApk1 = (this.mThread.getSystemContext()).mPackageInfo; 
    return loadedApk1.getAppFactory();
  }
  
  public static Application newApplication(Class<?> paramClass, Context paramContext) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    Application application = (Application)paramClass.newInstance();
    application.attach(paramContext);
    return application;
  }
  
  private void postPerformCreate(Activity paramActivity) {
    if (this.mActivityMonitors != null)
      synchronized (this.mSync) {
        int i = this.mActivityMonitors.size();
        for (byte b = 0; b < i; b++)
          ((ActivityMonitor)this.mActivityMonitors.get(b)).match((Context)paramActivity, paramActivity, paramActivity.getIntent()); 
      }  
  }
  
  private void prePerformCreate(Activity paramActivity) {
    if (this.mWaitingActivities != null)
      synchronized (this.mSync) {
        int i = this.mWaitingActivities.size();
        for (byte b = 0; b < i; b++) {
          ActivityWaiter activityWaiter = this.mWaitingActivities.get(b);
          if (activityWaiter.intent.filterEquals(paramActivity.getIntent())) {
            activityWaiter.activity = paramActivity;
            MessageQueue messageQueue = this.mMessageQueue;
            ActivityGoing activityGoing = new ActivityGoing();
            this(this, activityWaiter);
            messageQueue.addIdleHandler(activityGoing);
          } 
        } 
      }  
  }
  
  private final void validateNotAppThread() {
    if (Looper.myLooper() != Looper.getMainLooper())
      return; 
    throw new RuntimeException("This method can not be called from the main application thread");
  }
  
  private void waitForEnterAnimationComplete(Activity paramActivity) {
    Object object = this.mAnimationCompleteLock;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    long l = 5000L;
    while (true) {
      if (l > 0L)
        try {
          if (!paramActivity.mEnterAnimationComplete) {
            long l1 = System.currentTimeMillis();
            this.mAnimationCompleteLock.wait(l);
            long l2 = System.currentTimeMillis();
            l -= l2 - l1;
            continue;
          } 
        } catch (InterruptedException interruptedException) {
        
        } finally {} 
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      return;
    } 
  }
  
  public TestLooperManager acquireLooperManager(Looper paramLooper) {
    checkInstrumenting("acquireLooperManager");
    return new TestLooperManager(paramLooper);
  }
  
  public ActivityMonitor addMonitor(IntentFilter paramIntentFilter, ActivityResult paramActivityResult, boolean paramBoolean) {
    ActivityMonitor activityMonitor = new ActivityMonitor(paramIntentFilter, paramActivityResult, paramBoolean);
    addMonitor(activityMonitor);
    return activityMonitor;
  }
  
  public ActivityMonitor addMonitor(String paramString, ActivityResult paramActivityResult, boolean paramBoolean) {
    ActivityMonitor activityMonitor = new ActivityMonitor(paramString, paramActivityResult, paramBoolean);
    addMonitor(activityMonitor);
    return activityMonitor;
  }
  
  public void addMonitor(ActivityMonitor paramActivityMonitor) {
    synchronized (this.mSync) {
      if (this.mActivityMonitors == null) {
        ArrayList<ActivityMonitor> arrayList = new ArrayList();
        this();
        this.mActivityMonitors = arrayList;
      } 
      this.mActivityMonitors.add(paramActivityMonitor);
      return;
    } 
  }
  
  public void addResults(Bundle paramBundle) {
    IActivityManager iActivityManager = ActivityManager.getService();
    try {
      iActivityManager.addInstrumentationResults(this.mThread.getApplicationThread(), paramBundle);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  final void basicInit(ActivityThread paramActivityThread) {
    this.mThread = paramActivityThread;
  }
  
  public void callActivityOnCreate(Activity paramActivity, Bundle paramBundle) {
    prePerformCreate(paramActivity);
    paramActivity.performCreate(paramBundle);
    postPerformCreate(paramActivity);
  }
  
  public void callActivityOnCreate(Activity paramActivity, Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    prePerformCreate(paramActivity);
    paramActivity.performCreate(paramBundle, paramPersistableBundle);
    postPerformCreate(paramActivity);
  }
  
  public void callActivityOnDestroy(Activity paramActivity) {
    paramActivity.performDestroy();
  }
  
  public void callActivityOnNewIntent(Activity paramActivity, Intent paramIntent) {
    paramActivity.performNewIntent(paramIntent);
  }
  
  public void callActivityOnNewIntent(Activity paramActivity, ReferrerIntent paramReferrerIntent) {
    String str = paramActivity.mReferrer;
    if (paramReferrerIntent != null)
      try {
        paramActivity.mReferrer = paramReferrerIntent.mReferrer;
      } finally {} 
    if (paramReferrerIntent != null) {
      Intent intent2 = new Intent();
      this((Intent)paramReferrerIntent);
      Intent intent1 = intent2;
    } else {
      paramReferrerIntent = null;
    } 
    callActivityOnNewIntent(paramActivity, (Intent)paramReferrerIntent);
    paramActivity.mReferrer = str;
  }
  
  public void callActivityOnPause(Activity paramActivity) {
    paramActivity.performPause();
  }
  
  public void callActivityOnPictureInPictureRequested(Activity paramActivity) {
    paramActivity.onPictureInPictureRequested();
  }
  
  public void callActivityOnPostCreate(Activity paramActivity, Bundle paramBundle) {
    paramActivity.onPostCreate(paramBundle);
  }
  
  public void callActivityOnPostCreate(Activity paramActivity, Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    paramActivity.onPostCreate(paramBundle, paramPersistableBundle);
  }
  
  public void callActivityOnRestart(Activity paramActivity) {
    paramActivity.onRestart();
  }
  
  public void callActivityOnRestoreInstanceState(Activity paramActivity, Bundle paramBundle) {
    paramActivity.performRestoreInstanceState(paramBundle);
  }
  
  public void callActivityOnRestoreInstanceState(Activity paramActivity, Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    paramActivity.performRestoreInstanceState(paramBundle, paramPersistableBundle);
  }
  
  public void callActivityOnResume(Activity paramActivity) {
    paramActivity.mResumed = true;
    paramActivity.onResume();
    if (this.mActivityMonitors != null)
      synchronized (this.mSync) {
        int i = this.mActivityMonitors.size();
        for (byte b = 0; b < i; b++)
          ((ActivityMonitor)this.mActivityMonitors.get(b)).match((Context)paramActivity, paramActivity, paramActivity.getIntent()); 
      }  
  }
  
  public void callActivityOnSaveInstanceState(Activity paramActivity, Bundle paramBundle) {
    paramActivity.performSaveInstanceState(paramBundle);
  }
  
  public void callActivityOnSaveInstanceState(Activity paramActivity, Bundle paramBundle, PersistableBundle paramPersistableBundle) {
    paramActivity.performSaveInstanceState(paramBundle, paramPersistableBundle);
  }
  
  public void callActivityOnStart(Activity paramActivity) {
    paramActivity.onStart();
  }
  
  public void callActivityOnStop(Activity paramActivity) {
    paramActivity.onStop();
  }
  
  public void callActivityOnUserLeaving(Activity paramActivity) {
    paramActivity.performUserLeaving();
  }
  
  public void callApplicationOnCreate(Application paramApplication) {
    paramApplication.onCreate();
  }
  
  public boolean checkMonitorHit(ActivityMonitor paramActivityMonitor, int paramInt) {
    waitForIdleSync();
    synchronized (this.mSync) {
      if (paramActivityMonitor.getHits() < paramInt)
        return false; 
      this.mActivityMonitors.remove(paramActivityMonitor);
      return true;
    } 
  }
  
  public void endPerformanceSnapshot() {
    if (!isProfiling())
      this.mPerfMetrics = this.mPerformanceCollector.endSnapshot(); 
  }
  
  public void execStartActivities(Context paramContext, IBinder paramIBinder1, IBinder paramIBinder2, Activity paramActivity, Intent[] paramArrayOfIntent, Bundle paramBundle) {
    execStartActivitiesAsUser(paramContext, paramIBinder1, paramIBinder2, paramActivity, paramArrayOfIntent, paramBundle, paramContext.getUserId());
  }
  
  public int execStartActivitiesAsUser(Context paramContext, IBinder paramIBinder1, IBinder paramIBinder2, Activity paramActivity, Intent[] paramArrayOfIntent, Bundle paramBundle, int paramInt) {
    IApplicationThread iApplicationThread = (IApplicationThread)paramIBinder1;
    if (this.mActivityMonitors != null)
      synchronized (this.mSync) {
        int i = this.mActivityMonitors.size();
        for (byte b = 0; b < i; b++) {
          ActivityResult activityResult;
          ActivityMonitor activityMonitor = this.mActivityMonitors.get(b);
          paramIBinder1 = null;
          if (activityMonitor.ignoreMatchingSpecificIntents())
            activityResult = activityMonitor.onStartActivity(paramArrayOfIntent[0]); 
          if (activityResult != null) {
            activityMonitor.mHits++;
            return -96;
          } 
          if (activityMonitor.match(paramContext, null, paramArrayOfIntent[0])) {
            activityMonitor.mHits++;
            if (activityMonitor.isBlocking())
              return -96; 
            break;
          } 
        } 
      }  
    try {
      String[] arrayOfString = new String[paramArrayOfIntent.length];
      for (byte b = 0; b < paramArrayOfIntent.length; b++) {
        paramArrayOfIntent[b].migrateExtraStreamToClipData(paramContext);
        paramArrayOfIntent[b].prepareToLeaveProcess(paramContext);
        arrayOfString[b] = paramArrayOfIntent[b].resolveTypeIfNeeded(paramContext.getContentResolver());
      } 
      paramInt = ActivityTaskManager.getService().startActivities(iApplicationThread, paramContext.getBasePackageName(), paramContext.getAttributionTag(), paramArrayOfIntent, arrayOfString, paramIBinder2, paramBundle, paramInt);
      checkStartActivityResult(paramInt, paramArrayOfIntent[0]);
      return paramInt;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("Failure from system", remoteException);
    } 
  }
  
  public ActivityResult execStartActivity(Context paramContext, IBinder paramIBinder1, IBinder paramIBinder2, Activity paramActivity, Intent paramIntent, int paramInt, Bundle paramBundle) {
    IApplicationThread iApplicationThread = (IApplicationThread)paramIBinder1;
    Context context = null;
    if (paramActivity != null) {
      Uri uri = paramActivity.onProvideReferrer();
    } else {
      paramIBinder1 = null;
    } 
    if (paramIBinder1 != null)
      paramIntent.putExtra("android.intent.extra.REFERRER", (Parcelable)paramIBinder1); 
    if (this.mActivityMonitors != null)
      synchronized (this.mSync) {
        int i = this.mActivityMonitors.size();
        for (byte b = 0; b < i; b++) {
          ActivityResult activityResult;
          ActivityMonitor activityMonitor = this.mActivityMonitors.get(b);
          paramIBinder1 = null;
          if (activityMonitor.ignoreMatchingSpecificIntents())
            activityResult = activityMonitor.onStartActivity(paramIntent); 
          if (activityResult != null) {
            activityMonitor.mHits++;
            return activityResult;
          } 
          if (activityMonitor.match(paramContext, null, paramIntent)) {
            activityMonitor.mHits++;
            if (activityMonitor.isBlocking()) {
              ActivityResult activityResult1;
              paramContext = context;
              if (paramInt >= 0)
                activityResult1 = activityMonitor.getResult(); 
              return activityResult1;
            } 
            break;
          } 
        } 
      }  
    try {
      paramIntent.migrateExtraStreamToClipData(paramContext);
      paramIntent.prepareToLeaveProcess(paramContext);
      IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
      String str2 = paramContext.getBasePackageName();
      String str1 = paramContext.getAttributionTag();
      String str3 = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
      if (paramActivity != null) {
        try {
          String str = paramActivity.mEmbeddedID;
        } catch (RemoteException null) {}
      } else {
        paramContext = null;
      } 
      try {
        checkStartActivityResult(iActivityTaskManager.startActivity(iApplicationThread, str2, str1, paramIntent, str3, paramIBinder2, (String)paramContext, paramInt, 0, null, paramBundle), paramIntent);
        return null;
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    throw new RuntimeException("Failure from system", remoteException);
  }
  
  public ActivityResult execStartActivity(Context paramContext, IBinder paramIBinder1, IBinder paramIBinder2, String paramString, Intent paramIntent, int paramInt, Bundle paramBundle) {
    IApplicationThread iApplicationThread = (IApplicationThread)paramIBinder1;
    List<ActivityMonitor> list = this.mActivityMonitors;
    Context context = null;
    if (list != null)
      synchronized (this.mSync) {
        int i = this.mActivityMonitors.size();
        for (byte b = 0; b < i; b++) {
          ActivityResult activityResult;
          ActivityMonitor activityMonitor = this.mActivityMonitors.get(b);
          list = null;
          if (activityMonitor.ignoreMatchingSpecificIntents())
            activityResult = activityMonitor.onStartActivity(paramIntent); 
          if (activityResult != null) {
            activityMonitor.mHits++;
            return activityResult;
          } 
          if (activityMonitor.match(paramContext, null, paramIntent)) {
            activityMonitor.mHits++;
            if (activityMonitor.isBlocking()) {
              ActivityResult activityResult1;
              paramContext = context;
              if (paramInt >= 0)
                activityResult1 = activityMonitor.getResult(); 
              return activityResult1;
            } 
            break;
          } 
        } 
      }  
    try {
      paramIntent.migrateExtraStreamToClipData(paramContext);
      paramIntent.prepareToLeaveProcess(paramContext);
      checkStartActivityResult(ActivityTaskManager.getService().startActivity(iApplicationThread, paramContext.getBasePackageName(), paramContext.getAttributionTag(), paramIntent, paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver()), paramIBinder2, paramString, paramInt, 0, null, paramBundle), paramIntent);
      return null;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("Failure from system", remoteException);
    } 
  }
  
  public ActivityResult execStartActivity(Context paramContext, IBinder paramIBinder1, IBinder paramIBinder2, String paramString, Intent paramIntent, int paramInt, Bundle paramBundle, UserHandle paramUserHandle) {
    IApplicationThread iApplicationThread = (IApplicationThread)paramIBinder1;
    List<ActivityMonitor> list = this.mActivityMonitors;
    Context context = null;
    if (list != null)
      synchronized (this.mSync) {
        int i = this.mActivityMonitors.size();
        for (byte b = 0; b < i; b++) {
          ActivityResult activityResult;
          ActivityMonitor activityMonitor = this.mActivityMonitors.get(b);
          list = null;
          if (activityMonitor.ignoreMatchingSpecificIntents())
            activityResult = activityMonitor.onStartActivity(paramIntent); 
          if (activityResult != null) {
            activityMonitor.mHits++;
            return activityResult;
          } 
          if (activityMonitor.match(paramContext, null, paramIntent)) {
            activityMonitor.mHits++;
            if (activityMonitor.isBlocking()) {
              ActivityResult activityResult1;
              paramContext = context;
              if (paramInt >= 0)
                activityResult1 = activityMonitor.getResult(); 
              return activityResult1;
            } 
            break;
          } 
        } 
      }  
    try {
      paramIntent.migrateExtraStreamToClipData(paramContext);
      paramIntent.prepareToLeaveProcess(paramContext);
      IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
      String str2 = paramContext.getBasePackageName();
      String str3 = paramContext.getAttributionTag();
      String str1 = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
      int i = paramUserHandle.getIdentifier();
      try {
        checkStartActivityResult(iActivityTaskManager.startActivityAsUser(iApplicationThread, str2, str3, paramIntent, str1, paramIBinder2, paramString, paramInt, 0, null, paramBundle, i), paramIntent);
        return null;
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    throw new RuntimeException("Failure from system", remoteException);
  }
  
  public ActivityResult execStartActivityAsCaller(Context paramContext, IBinder paramIBinder1, IBinder paramIBinder2, Activity paramActivity, Intent paramIntent, int paramInt1, Bundle paramBundle, IBinder paramIBinder3, boolean paramBoolean, int paramInt2) {
    IApplicationThread iApplicationThread = (IApplicationThread)paramIBinder1;
    List<ActivityMonitor> list = this.mActivityMonitors;
    Context context = null;
    if (list != null)
      synchronized (this.mSync) {
        int i = this.mActivityMonitors.size();
        for (byte b = 0; b < i; b++) {
          ActivityResult activityResult;
          ActivityMonitor activityMonitor = this.mActivityMonitors.get(b);
          list = null;
          if (activityMonitor.ignoreMatchingSpecificIntents())
            activityResult = activityMonitor.onStartActivity(paramIntent); 
          if (activityResult != null) {
            activityMonitor.mHits++;
            return activityResult;
          } 
          if (activityMonitor.match(paramContext, null, paramIntent)) {
            activityMonitor.mHits++;
            if (activityMonitor.isBlocking()) {
              ActivityResult activityResult1;
              paramContext = context;
              if (paramInt1 >= 0)
                activityResult1 = activityMonitor.getResult(); 
              return activityResult1;
            } 
            break;
          } 
        } 
      }  
    try {
      paramIntent.migrateExtraStreamToClipData(paramContext);
      paramIntent.prepareToLeaveProcess(paramContext);
      IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
      String str1 = paramContext.getBasePackageName();
      String str2 = paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver());
      if (paramActivity != null) {
        String str = paramActivity.mEmbeddedID;
      } else {
        paramContext = null;
      } 
      try {
        paramInt1 = iActivityTaskManager.startActivityAsCaller(iApplicationThread, str1, paramIntent, str2, paramIBinder2, (String)paramContext, paramInt1, 0, null, paramBundle, paramIBinder3, paramBoolean, paramInt2);
        try {
          checkStartActivityResult(paramInt1, paramIntent);
          return null;
        } catch (RemoteException null) {}
      } catch (RemoteException null) {}
    } catch (RemoteException remoteException) {}
    throw new RuntimeException("Failure from system", remoteException);
  }
  
  public void execStartActivityFromAppTask(Context paramContext, IBinder paramIBinder, IAppTask paramIAppTask, Intent paramIntent, Bundle paramBundle) {
    IApplicationThread iApplicationThread = (IApplicationThread)paramIBinder;
    if (this.mActivityMonitors != null)
      synchronized (this.mSync) {
        int i = this.mActivityMonitors.size();
        for (byte b = 0; b < i; b++) {
          ActivityResult activityResult;
          ActivityMonitor activityMonitor = this.mActivityMonitors.get(b);
          paramIBinder = null;
          if (activityMonitor.ignoreMatchingSpecificIntents())
            activityResult = activityMonitor.onStartActivity(paramIntent); 
          if (activityResult != null) {
            activityMonitor.mHits++;
            return;
          } 
          if (activityMonitor.match(paramContext, null, paramIntent)) {
            activityMonitor.mHits++;
            if (activityMonitor.isBlocking())
              return; 
            break;
          } 
        } 
      }  
    try {
      paramIntent.migrateExtraStreamToClipData(paramContext);
      paramIntent.prepareToLeaveProcess(paramContext);
      checkStartActivityResult(paramIAppTask.startActivity(iApplicationThread.asBinder(), paramContext.getBasePackageName(), paramContext.getAttributionTag(), paramIntent, paramIntent.resolveTypeIfNeeded(paramContext.getContentResolver()), paramBundle), paramIntent);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeException("Failure from system", remoteException);
    } 
  }
  
  public void finish(int paramInt, Bundle paramBundle) {
    if (this.mAutomaticPerformanceSnapshots)
      endPerformanceSnapshot(); 
    Bundle bundle = paramBundle;
    if (this.mPerfMetrics != null) {
      bundle = paramBundle;
      if (paramBundle == null)
        bundle = new Bundle(); 
      bundle.putAll(this.mPerfMetrics);
    } 
    UiAutomation uiAutomation = this.mUiAutomation;
    if (uiAutomation != null && !uiAutomation.isDestroyed()) {
      this.mUiAutomation.disconnect();
      this.mUiAutomation = null;
    } 
    this.mThread.finishInstrumentation(paramInt, bundle);
  }
  
  public Bundle getAllocCounts() {
    Bundle bundle = new Bundle();
    bundle.putLong("global_alloc_count", Debug.getGlobalAllocCount());
    bundle.putLong("global_alloc_size", Debug.getGlobalAllocSize());
    bundle.putLong("global_freed_count", Debug.getGlobalFreedCount());
    bundle.putLong("global_freed_size", Debug.getGlobalFreedSize());
    bundle.putLong("gc_invocation_count", Debug.getGlobalGcInvocationCount());
    return bundle;
  }
  
  public Bundle getBinderCounts() {
    Bundle bundle = new Bundle();
    bundle.putLong("sent_transactions", Debug.getBinderSentTransactions());
    bundle.putLong("received_transactions", Debug.getBinderReceivedTransactions());
    return bundle;
  }
  
  public ComponentName getComponentName() {
    return this.mComponent;
  }
  
  public Context getContext() {
    return this.mInstrContext;
  }
  
  public String getProcessName() {
    return this.mThread.getProcessName();
  }
  
  public Context getTargetContext() {
    return this.mAppContext;
  }
  
  public UiAutomation getUiAutomation() {
    return getUiAutomation(0);
  }
  
  public UiAutomation getUiAutomation(int paramInt) {
    boolean bool;
    UiAutomation uiAutomation = this.mUiAutomation;
    if (uiAutomation == null || uiAutomation.isDestroyed()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (this.mUiAutomationConnection != null) {
      if (!bool && this.mUiAutomation.getFlags() == paramInt)
        return this.mUiAutomation; 
      if (bool) {
        this.mUiAutomation = new UiAutomation(getTargetContext().getMainLooper(), this.mUiAutomationConnection);
      } else {
        this.mUiAutomation.disconnect();
      } 
      this.mUiAutomation.connect(paramInt);
      return this.mUiAutomation;
    } 
    return null;
  }
  
  final void init(ActivityThread paramActivityThread, Context paramContext1, Context paramContext2, ComponentName paramComponentName, IInstrumentationWatcher paramIInstrumentationWatcher, IUiAutomationConnection paramIUiAutomationConnection) {
    this.mThread = paramActivityThread;
    paramActivityThread.getLooper();
    this.mMessageQueue = Looper.myQueue();
    this.mInstrContext = paramContext1;
    this.mAppContext = paramContext2;
    this.mComponent = paramComponentName;
    this.mWatcher = paramIInstrumentationWatcher;
    this.mUiAutomationConnection = paramIUiAutomationConnection;
  }
  
  public boolean invokeContextMenuAction(Activity paramActivity, int paramInt1, int paramInt2) {
    validateNotAppThread();
    sendKeySync(new KeyEvent(0, 23));
    waitForIdleSync();
    try {
      Thread.sleep(ViewConfiguration.getLongPressTimeout());
      sendKeySync(new KeyEvent(1, 23));
      waitForIdleSync();
      class ContextMenuRunnable implements Runnable {
        private final Activity activity;
        
        private final int flags;
        
        private final int identifier;
        
        boolean returnValue;
        
        public ContextMenuRunnable(Activity param1Activity, int param1Int1, int param1Int2) {
          this.activity = param1Activity;
          this.identifier = param1Int1;
          this.flags = param1Int2;
        }
        
        public void run() {
          this.returnValue = this.activity.getWindow().performContextMenuIdentifierAction(this.identifier, this.flags);
        }
      };
      ContextMenuRunnable contextMenuRunnable = new ContextMenuRunnable(paramActivity, paramInt1, paramInt2);
      runOnMainSync(contextMenuRunnable);
      return contextMenuRunnable.returnValue;
    } catch (InterruptedException interruptedException) {
      Log.e("Instrumentation", "Could not sleep for long press timeout", interruptedException);
      return false;
    } 
  }
  
  public boolean invokeMenuActionSync(Activity paramActivity, int paramInt1, int paramInt2) {
    class MenuRunnable implements Runnable {
      private final Activity activity;
      
      private final int flags;
      
      private final int identifier;
      
      boolean returnValue;
      
      public MenuRunnable(Activity param1Activity, int param1Int1, int param1Int2) {
        this.activity = param1Activity;
        this.identifier = param1Int1;
        this.flags = param1Int2;
      }
      
      public void run() {
        this.returnValue = this.activity.getWindow().performPanelIdentifierAction(0, this.identifier, this.flags);
      }
    };
    MenuRunnable menuRunnable = new MenuRunnable(paramActivity, paramInt1, paramInt2);
    runOnMainSync(menuRunnable);
    return menuRunnable.returnValue;
  }
  
  public boolean isProfiling() {
    return this.mThread.isProfiling();
  }
  
  public Activity newActivity(Class<?> paramClass, Context paramContext, IBinder paramIBinder, Application paramApplication, Intent paramIntent, ActivityInfo paramActivityInfo, CharSequence paramCharSequence, Activity paramActivity, String paramString, Object paramObject) throws InstantiationException, IllegalAccessException {
    Activity activity = (Activity)paramClass.newInstance();
    if (paramApplication == null)
      paramApplication = new Application(); 
    activity.attach(paramContext, null, this, paramIBinder, 0, paramApplication, paramIntent, paramActivityInfo, paramCharSequence, paramActivity, paramString, (Activity.NonConfigurationInstances)paramObject, new Configuration(), null, null, null, null, null);
    return activity;
  }
  
  public Activity newActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    String str;
    if (paramIntent != null && paramIntent.getComponent() != null) {
      str = paramIntent.getComponent().getPackageName();
    } else {
      str = null;
    } 
    return getFactory(str).instantiateActivity(paramClassLoader, paramString, paramIntent);
  }
  
  public Application newApplication(ClassLoader paramClassLoader, String paramString, Context paramContext) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    Application application = getFactory(paramContext.getPackageName()).instantiateApplication(paramClassLoader, paramString);
    application.attach(paramContext);
    return application;
  }
  
  public void onCreate(Bundle paramBundle) {}
  
  public void onDestroy() {}
  
  public void onEnterAnimationComplete() {
    synchronized (this.mAnimationCompleteLock) {
      this.mAnimationCompleteLock.notifyAll();
      return;
    } 
  }
  
  public boolean onException(Object paramObject, Throwable paramThrowable) {
    return false;
  }
  
  public void onStart() {}
  
  public void removeMonitor(ActivityMonitor paramActivityMonitor) {
    synchronized (this.mSync) {
      this.mActivityMonitors.remove(paramActivityMonitor);
      return;
    } 
  }
  
  public void runOnMainSync(Runnable paramRunnable) {
    validateNotAppThread();
    paramRunnable = new SyncRunnable(paramRunnable);
    this.mThread.getHandler().post(paramRunnable);
    paramRunnable.waitForComplete();
  }
  
  public void sendCharacterSync(int paramInt) {
    sendKeySync(new KeyEvent(0, paramInt));
    sendKeySync(new KeyEvent(1, paramInt));
  }
  
  public void sendKeyDownUpSync(int paramInt) {
    sendKeySync(new KeyEvent(0, paramInt));
    sendKeySync(new KeyEvent(1, paramInt));
  }
  
  public void sendKeySync(KeyEvent paramKeyEvent) {
    validateNotAppThread();
    long l1 = paramKeyEvent.getDownTime();
    long l2 = paramKeyEvent.getEventTime();
    int i = paramKeyEvent.getSource();
    int j = i;
    if (i == 0)
      j = 257; 
    long l3 = l2;
    if (l2 == 0L)
      l3 = SystemClock.uptimeMillis(); 
    l2 = l1;
    if (l1 == 0L)
      l2 = l3; 
    KeyEvent keyEvent = new KeyEvent(paramKeyEvent);
    keyEvent.setTime(l2, l3);
    keyEvent.setSource(j);
    keyEvent.setFlags(paramKeyEvent.getFlags() | 0x8);
    InputManager.getInstance().injectInputEvent((InputEvent)keyEvent, 2);
  }
  
  public void sendPointerSync(MotionEvent paramMotionEvent) {
    validateNotAppThread();
    if ((paramMotionEvent.getSource() & 0x2) == 0)
      paramMotionEvent.setSource(4098); 
    try {
      WindowManagerGlobal.getWindowManagerService().injectInputAfterTransactionsApplied((InputEvent)paramMotionEvent, 2);
    } catch (RemoteException remoteException) {}
  }
  
  public void sendStatus(int paramInt, Bundle paramBundle) {
    IInstrumentationWatcher iInstrumentationWatcher = this.mWatcher;
    if (iInstrumentationWatcher != null)
      try {
        iInstrumentationWatcher.instrumentationStatus(this.mComponent, paramInt, paramBundle);
      } catch (RemoteException remoteException) {
        this.mWatcher = null;
      }  
  }
  
  public void sendStringSync(String paramString) {
    if (paramString == null)
      return; 
    KeyEvent[] arrayOfKeyEvent = KeyCharacterMap.load(-1).getEvents(paramString.toCharArray());
    if (arrayOfKeyEvent != null)
      for (byte b = 0; b < arrayOfKeyEvent.length; b++)
        sendKeySync(KeyEvent.changeTimeRepeat(arrayOfKeyEvent[b], SystemClock.uptimeMillis(), 0));  
  }
  
  public void sendTrackballEventSync(MotionEvent paramMotionEvent) {
    validateNotAppThread();
    if ((paramMotionEvent.getSource() & 0x4) == 0)
      paramMotionEvent.setSource(65540); 
    InputManager.getInstance().injectInputEvent((InputEvent)paramMotionEvent, 2);
  }
  
  public void setAutomaticPerformanceSnapshots() {
    this.mAutomaticPerformanceSnapshots = true;
    this.mPerformanceCollector = new PerformanceCollector();
  }
  
  public void setInTouchMode(boolean paramBoolean) {
    try {
      IWindowManager.Stub.asInterface(ServiceManager.getService("window")).setInTouchMode(paramBoolean);
    } catch (RemoteException remoteException) {}
  }
  
  public void start() {
    if (this.mRunner == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Instr: ");
      stringBuilder.append(getClass().getName());
      InstrumentationThread instrumentationThread = new InstrumentationThread(stringBuilder.toString());
      this.mRunner = instrumentationThread;
      instrumentationThread.start();
      return;
    } 
    throw new RuntimeException("Instrumentation already started");
  }
  
  public Activity startActivitySync(Intent paramIntent) {
    return startActivitySync(paramIntent, null);
  }
  
  public Activity startActivitySync(Intent paramIntent, Bundle paramBundle) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial validateNotAppThread : ()V
    //   4: aload_0
    //   5: getfield mSync : Ljava/lang/Object;
    //   8: astore_3
    //   9: aload_3
    //   10: monitorenter
    //   11: new android/content/Intent
    //   14: astore #4
    //   16: aload #4
    //   18: aload_1
    //   19: invokespecial <init> : (Landroid/content/Intent;)V
    //   22: aload #4
    //   24: aload_0
    //   25: invokevirtual getTargetContext : ()Landroid/content/Context;
    //   28: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   31: iconst_0
    //   32: invokevirtual resolveActivityInfo : (Landroid/content/pm/PackageManager;I)Landroid/content/pm/ActivityInfo;
    //   35: astore_1
    //   36: aload_1
    //   37: ifnull -> 287
    //   40: aload_0
    //   41: getfield mThread : Landroid/app/ActivityThread;
    //   44: invokevirtual getProcessName : ()Ljava/lang/String;
    //   47: astore #5
    //   49: aload_1
    //   50: getfield processName : Ljava/lang/String;
    //   53: aload #5
    //   55: invokevirtual equals : (Ljava/lang/Object;)Z
    //   58: ifeq -> 215
    //   61: new android/content/ComponentName
    //   64: astore #5
    //   66: aload #5
    //   68: aload_1
    //   69: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   72: getfield packageName : Ljava/lang/String;
    //   75: aload_1
    //   76: getfield name : Ljava/lang/String;
    //   79: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   82: aload #4
    //   84: aload #5
    //   86: invokevirtual setComponent : (Landroid/content/ComponentName;)Landroid/content/Intent;
    //   89: pop
    //   90: new android/app/Instrumentation$ActivityWaiter
    //   93: astore_1
    //   94: aload_1
    //   95: aload #4
    //   97: invokespecial <init> : (Landroid/content/Intent;)V
    //   100: aload_0
    //   101: getfield mWaitingActivities : Ljava/util/List;
    //   104: ifnonnull -> 123
    //   107: new java/util/ArrayList
    //   110: astore #5
    //   112: aload #5
    //   114: invokespecial <init> : ()V
    //   117: aload_0
    //   118: aload #5
    //   120: putfield mWaitingActivities : Ljava/util/List;
    //   123: aload_0
    //   124: getfield mWaitingActivities : Ljava/util/List;
    //   127: aload_1
    //   128: invokeinterface add : (Ljava/lang/Object;)Z
    //   133: pop
    //   134: aload_0
    //   135: invokevirtual getTargetContext : ()Landroid/content/Context;
    //   138: aload #4
    //   140: aload_2
    //   141: invokevirtual startActivity : (Landroid/content/Intent;Landroid/os/Bundle;)V
    //   144: aload_0
    //   145: getfield mSync : Ljava/lang/Object;
    //   148: invokevirtual wait : ()V
    //   151: goto -> 155
    //   154: astore_2
    //   155: aload_0
    //   156: getfield mWaitingActivities : Ljava/util/List;
    //   159: aload_1
    //   160: invokeinterface contains : (Ljava/lang/Object;)Z
    //   165: ifne -> 144
    //   168: aload_1
    //   169: getfield activity : Landroid/app/Activity;
    //   172: astore_1
    //   173: aload_3
    //   174: monitorexit
    //   175: aload_0
    //   176: aload_1
    //   177: invokespecial waitForEnterAnimationComplete : (Landroid/app/Activity;)V
    //   180: new android/view/SurfaceControl$Transaction
    //   183: dup
    //   184: invokespecial <init> : ()V
    //   187: astore_2
    //   188: aload_2
    //   189: iconst_1
    //   190: invokevirtual apply : (Z)V
    //   193: aload_2
    //   194: invokevirtual close : ()V
    //   197: aload_1
    //   198: areturn
    //   199: astore_1
    //   200: aload_2
    //   201: invokevirtual close : ()V
    //   204: goto -> 213
    //   207: astore_2
    //   208: aload_1
    //   209: aload_2
    //   210: invokevirtual addSuppressed : (Ljava/lang/Throwable;)V
    //   213: aload_1
    //   214: athrow
    //   215: new java/lang/RuntimeException
    //   218: astore #6
    //   220: new java/lang/StringBuilder
    //   223: astore_2
    //   224: aload_2
    //   225: invokespecial <init> : ()V
    //   228: aload_2
    //   229: ldc_w 'Intent in process '
    //   232: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: pop
    //   236: aload_2
    //   237: aload #5
    //   239: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: pop
    //   243: aload_2
    //   244: ldc_w ' resolved to different process '
    //   247: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: aload_2
    //   252: aload_1
    //   253: getfield processName : Ljava/lang/String;
    //   256: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: aload_2
    //   261: ldc_w ': '
    //   264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload_2
    //   269: aload #4
    //   271: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   274: pop
    //   275: aload #6
    //   277: aload_2
    //   278: invokevirtual toString : ()Ljava/lang/String;
    //   281: invokespecial <init> : (Ljava/lang/String;)V
    //   284: aload #6
    //   286: athrow
    //   287: new java/lang/RuntimeException
    //   290: astore_2
    //   291: new java/lang/StringBuilder
    //   294: astore_1
    //   295: aload_1
    //   296: invokespecial <init> : ()V
    //   299: aload_1
    //   300: ldc_w 'Unable to resolve activity for: '
    //   303: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: pop
    //   307: aload_1
    //   308: aload #4
    //   310: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   313: pop
    //   314: aload_2
    //   315: aload_1
    //   316: invokevirtual toString : ()Ljava/lang/String;
    //   319: invokespecial <init> : (Ljava/lang/String;)V
    //   322: aload_2
    //   323: athrow
    //   324: astore_1
    //   325: aload_3
    //   326: monitorexit
    //   327: aload_1
    //   328: athrow
    // Exception table:
    //   from	to	target	type
    //   11	22	324	finally
    //   22	36	324	finally
    //   40	123	324	finally
    //   123	144	324	finally
    //   144	151	154	java/lang/InterruptedException
    //   144	151	324	finally
    //   155	175	324	finally
    //   188	193	199	finally
    //   200	204	207	finally
    //   215	287	324	finally
    //   287	324	324	finally
    //   325	327	324	finally
  }
  
  @Deprecated
  public void startAllocCounting() {
    Runtime.getRuntime().gc();
    Runtime.getRuntime().runFinalization();
    Runtime.getRuntime().gc();
    Debug.resetAllCounts();
    Debug.startAllocCounting();
  }
  
  public void startPerformanceSnapshot() {
    if (!isProfiling())
      this.mPerformanceCollector.beginSnapshot(null); 
  }
  
  public void startProfiling() {
    if (this.mThread.isProfiling()) {
      File file = new File(this.mThread.getProfileFilePath());
      file.getParentFile().mkdirs();
      Debug.startMethodTracing(file.toString(), 8388608);
    } 
  }
  
  @Deprecated
  public void stopAllocCounting() {
    Runtime.getRuntime().gc();
    Runtime.getRuntime().runFinalization();
    Runtime.getRuntime().gc();
    Debug.stopAllocCounting();
  }
  
  public void stopProfiling() {
    if (this.mThread.isProfiling())
      Debug.stopMethodTracing(); 
  }
  
  public void waitForIdle(Runnable paramRunnable) {
    this.mMessageQueue.addIdleHandler(new Idler(paramRunnable));
    this.mThread.getHandler().post(new EmptyRunnable());
  }
  
  public void waitForIdleSync() {
    validateNotAppThread();
    Idler idler = new Idler(null);
    this.mMessageQueue.addIdleHandler(idler);
    this.mThread.getHandler().post(new EmptyRunnable());
    idler.waitForIdle();
  }
  
  public Activity waitForMonitor(ActivityMonitor paramActivityMonitor) {
    Activity activity = paramActivityMonitor.waitForActivity();
    synchronized (this.mSync) {
      this.mActivityMonitors.remove(paramActivityMonitor);
      return activity;
    } 
  }
  
  public Activity waitForMonitorWithTimeout(ActivityMonitor paramActivityMonitor, long paramLong) {
    Activity activity = paramActivityMonitor.waitForActivityWithTimeout(paramLong);
    synchronized (this.mSync) {
      this.mActivityMonitors.remove(paramActivityMonitor);
      return activity;
    } 
  }
  
  private final class ActivityGoing implements MessageQueue.IdleHandler {
    private final Instrumentation.ActivityWaiter mWaiter;
    
    public ActivityGoing(Instrumentation.ActivityWaiter param1ActivityWaiter) {
      this.mWaiter = param1ActivityWaiter;
    }
    
    public final boolean queueIdle() {
      synchronized (Instrumentation.this.mSync) {
        Instrumentation.this.mWaitingActivities.remove(this.mWaiter);
        Instrumentation.this.mSync.notifyAll();
        return false;
      } 
    }
  }
  
  public static class ActivityMonitor {
    private final boolean mBlock = false;
    
    private final String mClass = null;
    
    int mHits = 0;
    
    private final boolean mIgnoreMatchingSpecificIntents = true;
    
    Activity mLastActivity = null;
    
    private final Instrumentation.ActivityResult mResult = null;
    
    private final IntentFilter mWhich = null;
    
    public ActivityMonitor() {}
    
    public ActivityMonitor(IntentFilter param1IntentFilter, Instrumentation.ActivityResult param1ActivityResult, boolean param1Boolean) {}
    
    public ActivityMonitor(String param1String, Instrumentation.ActivityResult param1ActivityResult, boolean param1Boolean) {}
    
    public final IntentFilter getFilter() {
      return this.mWhich;
    }
    
    public final int getHits() {
      return this.mHits;
    }
    
    public final Activity getLastActivity() {
      return this.mLastActivity;
    }
    
    public final Instrumentation.ActivityResult getResult() {
      return this.mResult;
    }
    
    final boolean ignoreMatchingSpecificIntents() {
      return this.mIgnoreMatchingSpecificIntents;
    }
    
    public final boolean isBlocking() {
      return this.mBlock;
    }
    
    final boolean match(Context param1Context, Activity param1Activity, Intent param1Intent) {
      // Byte code:
      //   0: aload_0
      //   1: getfield mIgnoreMatchingSpecificIntents : Z
      //   4: ifeq -> 9
      //   7: iconst_0
      //   8: ireturn
      //   9: aload_0
      //   10: monitorenter
      //   11: aload_0
      //   12: getfield mWhich : Landroid/content/IntentFilter;
      //   15: ifnull -> 40
      //   18: aload_0
      //   19: getfield mWhich : Landroid/content/IntentFilter;
      //   22: aload_1
      //   23: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
      //   26: aload_3
      //   27: iconst_1
      //   28: ldc 'Instrumentation'
      //   30: invokevirtual match : (Landroid/content/ContentResolver;Landroid/content/Intent;ZLjava/lang/String;)I
      //   33: ifge -> 40
      //   36: aload_0
      //   37: monitorexit
      //   38: iconst_0
      //   39: ireturn
      //   40: aload_0
      //   41: getfield mClass : Ljava/lang/String;
      //   44: ifnull -> 98
      //   47: aconst_null
      //   48: astore_1
      //   49: aload_2
      //   50: ifnull -> 64
      //   53: aload_2
      //   54: invokevirtual getClass : ()Ljava/lang/Class;
      //   57: invokevirtual getName : ()Ljava/lang/String;
      //   60: astore_1
      //   61: goto -> 79
      //   64: aload_3
      //   65: invokevirtual getComponent : ()Landroid/content/ComponentName;
      //   68: ifnull -> 79
      //   71: aload_3
      //   72: invokevirtual getComponent : ()Landroid/content/ComponentName;
      //   75: invokevirtual getClassName : ()Ljava/lang/String;
      //   78: astore_1
      //   79: aload_1
      //   80: ifnull -> 94
      //   83: aload_0
      //   84: getfield mClass : Ljava/lang/String;
      //   87: aload_1
      //   88: invokevirtual equals : (Ljava/lang/Object;)Z
      //   91: ifne -> 98
      //   94: aload_0
      //   95: monitorexit
      //   96: iconst_0
      //   97: ireturn
      //   98: aload_2
      //   99: ifnull -> 111
      //   102: aload_0
      //   103: aload_2
      //   104: putfield mLastActivity : Landroid/app/Activity;
      //   107: aload_0
      //   108: invokevirtual notifyAll : ()V
      //   111: aload_0
      //   112: monitorexit
      //   113: iconst_1
      //   114: ireturn
      //   115: astore_1
      //   116: aload_0
      //   117: monitorexit
      //   118: aload_1
      //   119: athrow
      // Exception table:
      //   from	to	target	type
      //   11	38	115	finally
      //   40	47	115	finally
      //   53	61	115	finally
      //   64	79	115	finally
      //   83	94	115	finally
      //   94	96	115	finally
      //   102	111	115	finally
      //   111	113	115	finally
      //   116	118	115	finally
    }
    
    public Instrumentation.ActivityResult onStartActivity(Intent param1Intent) {
      return null;
    }
    
    public final Activity waitForActivity() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mLastActivity : Landroid/app/Activity;
      //   6: astore_1
      //   7: aload_1
      //   8: ifnonnull -> 22
      //   11: aload_0
      //   12: invokevirtual wait : ()V
      //   15: goto -> 2
      //   18: astore_1
      //   19: goto -> 15
      //   22: aload_0
      //   23: getfield mLastActivity : Landroid/app/Activity;
      //   26: astore_1
      //   27: aload_0
      //   28: aconst_null
      //   29: putfield mLastActivity : Landroid/app/Activity;
      //   32: aload_0
      //   33: monitorexit
      //   34: aload_1
      //   35: areturn
      //   36: astore_1
      //   37: aload_0
      //   38: monitorexit
      //   39: aload_1
      //   40: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	36	finally
      //   11	15	18	java/lang/InterruptedException
      //   11	15	36	finally
      //   22	34	36	finally
      //   37	39	36	finally
    }
    
    public final Activity waitForActivityWithTimeout(long param1Long) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mLastActivity : Landroid/app/Activity;
      //   6: astore_3
      //   7: aload_3
      //   8: ifnonnull -> 20
      //   11: aload_0
      //   12: lload_1
      //   13: invokevirtual wait : (J)V
      //   16: goto -> 20
      //   19: astore_3
      //   20: aload_0
      //   21: getfield mLastActivity : Landroid/app/Activity;
      //   24: ifnonnull -> 31
      //   27: aload_0
      //   28: monitorexit
      //   29: aconst_null
      //   30: areturn
      //   31: aload_0
      //   32: getfield mLastActivity : Landroid/app/Activity;
      //   35: astore_3
      //   36: aload_0
      //   37: aconst_null
      //   38: putfield mLastActivity : Landroid/app/Activity;
      //   41: aload_0
      //   42: monitorexit
      //   43: aload_3
      //   44: areturn
      //   45: astore_3
      //   46: aload_0
      //   47: monitorexit
      //   48: aload_3
      //   49: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	45	finally
      //   11	16	19	java/lang/InterruptedException
      //   11	16	45	finally
      //   20	29	45	finally
      //   31	43	45	finally
      //   46	48	45	finally
    }
  }
  
  public static final class ActivityResult {
    private final int mResultCode;
    
    private final Intent mResultData;
    
    public ActivityResult(int param1Int, Intent param1Intent) {
      this.mResultCode = param1Int;
      this.mResultData = param1Intent;
    }
    
    public int getResultCode() {
      return this.mResultCode;
    }
    
    public Intent getResultData() {
      return this.mResultData;
    }
  }
  
  private static final class ActivityWaiter {
    public Activity activity;
    
    public final Intent intent;
    
    public ActivityWaiter(Intent param1Intent) {
      this.intent = param1Intent;
    }
  }
  
  private static final class EmptyRunnable implements Runnable {
    private EmptyRunnable() {}
    
    public void run() {}
  }
  
  private static final class Idler implements MessageQueue.IdleHandler {
    private final Runnable mCallback;
    
    private boolean mIdle;
    
    public Idler(Runnable param1Runnable) {
      this.mCallback = param1Runnable;
      this.mIdle = false;
    }
    
    public final boolean queueIdle() {
      // Byte code:
      //   0: aload_0
      //   1: getfield mCallback : Ljava/lang/Runnable;
      //   4: astore_1
      //   5: aload_1
      //   6: ifnull -> 15
      //   9: aload_1
      //   10: invokeinterface run : ()V
      //   15: aload_0
      //   16: monitorenter
      //   17: aload_0
      //   18: iconst_1
      //   19: putfield mIdle : Z
      //   22: aload_0
      //   23: invokevirtual notifyAll : ()V
      //   26: aload_0
      //   27: monitorexit
      //   28: iconst_0
      //   29: ireturn
      //   30: astore_1
      //   31: aload_0
      //   32: monitorexit
      //   33: aload_1
      //   34: athrow
      // Exception table:
      //   from	to	target	type
      //   17	28	30	finally
      //   31	33	30	finally
    }
    
    public void waitForIdle() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mIdle : Z
      //   6: istore_1
      //   7: iload_1
      //   8: ifne -> 22
      //   11: aload_0
      //   12: invokevirtual wait : ()V
      //   15: goto -> 2
      //   18: astore_2
      //   19: goto -> 15
      //   22: aload_0
      //   23: monitorexit
      //   24: return
      //   25: astore_2
      //   26: aload_0
      //   27: monitorexit
      //   28: aload_2
      //   29: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	25	finally
      //   11	15	18	java/lang/InterruptedException
      //   11	15	25	finally
      //   22	24	25	finally
      //   26	28	25	finally
    }
  }
  
  private final class InstrumentationThread extends Thread {
    public InstrumentationThread(String param1String) {
      super(param1String);
    }
    
    public void run() {
      try {
        Process.setThreadPriority(-8);
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Exception setting priority of instrumentation thread ");
        stringBuilder.append(Process.myTid());
        Log.w("Instrumentation", stringBuilder.toString(), runtimeException);
      } 
      if (Instrumentation.this.mAutomaticPerformanceSnapshots)
        Instrumentation.this.startPerformanceSnapshot(); 
      Instrumentation.this.onStart();
    }
  }
  
  private static final class SyncRunnable implements Runnable {
    private boolean mComplete;
    
    private final Runnable mTarget;
    
    public SyncRunnable(Runnable param1Runnable) {
      this.mTarget = param1Runnable;
    }
    
    public void run() {
      // Byte code:
      //   0: aload_0
      //   1: getfield mTarget : Ljava/lang/Runnable;
      //   4: invokeinterface run : ()V
      //   9: aload_0
      //   10: monitorenter
      //   11: aload_0
      //   12: iconst_1
      //   13: putfield mComplete : Z
      //   16: aload_0
      //   17: invokevirtual notifyAll : ()V
      //   20: aload_0
      //   21: monitorexit
      //   22: return
      //   23: astore_1
      //   24: aload_0
      //   25: monitorexit
      //   26: aload_1
      //   27: athrow
      // Exception table:
      //   from	to	target	type
      //   11	22	23	finally
      //   24	26	23	finally
    }
    
    public void waitForComplete() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mComplete : Z
      //   6: istore_1
      //   7: iload_1
      //   8: ifne -> 22
      //   11: aload_0
      //   12: invokevirtual wait : ()V
      //   15: goto -> 2
      //   18: astore_2
      //   19: goto -> 15
      //   22: aload_0
      //   23: monitorexit
      //   24: return
      //   25: astore_2
      //   26: aload_0
      //   27: monitorexit
      //   28: aload_2
      //   29: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	25	finally
      //   11	15	18	java/lang/InterruptedException
      //   11	15	25	finally
      //   22	24	25	finally
      //   26	28	25	finally
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface UiAutomationFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Instrumentation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */