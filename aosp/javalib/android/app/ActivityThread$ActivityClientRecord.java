package android.app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.view.DisplayAdjustments;
import android.view.ViewRootImpl;
import android.view.Window;
import android.view.WindowManager;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.content.ReferrerIntent;
import java.util.List;

public final class ActivityClientRecord {
  Activity activity;
  
  ActivityInfo activityInfo;
  
  public IBinder assistToken;
  
  CompatibilityInfo compatInfo;
  
  ViewRootImpl.ActivityConfigCallback configCallback;
  
  Configuration createdConfig;
  
  String embeddedID;
  
  boolean hideForNow;
  
  int ident;
  
  Intent intent;
  
  public final boolean isForward;
  
  boolean isTopResumedActivity;
  
  Activity.NonConfigurationInstances lastNonConfigurationInstances;
  
  boolean lastReportedTopResumedState;
  
  boolean mIsUserLeaving;
  
  private int mLifecycleState = 0;
  
  DisplayAdjustments.FixedRotationAdjustments mPendingFixedRotationAdjustments;
  
  private Configuration mPendingOverrideConfig;
  
  Window mPendingRemoveWindow;
  
  WindowManager mPendingRemoveWindowManager;
  
  boolean mPreserveWindow;
  
  Configuration newConfig;
  
  ActivityClientRecord nextIdle;
  
  Configuration overrideConfig;
  
  public LoadedApk packageInfo;
  
  Activity parent;
  
  boolean paused;
  
  int pendingConfigChanges;
  
  List<ReferrerIntent> pendingIntents;
  
  List<ResultInfo> pendingResults;
  
  PersistableBundle persistentState;
  
  ProfilerInfo profilerInfo;
  
  String referrer;
  
  boolean startsNotResumed;
  
  Bundle state;
  
  boolean stopped;
  
  private Configuration tmpConfig = new Configuration();
  
  public IBinder token;
  
  IVoiceInteractor voiceInteractor;
  
  Window window;
  
  public ActivityClientRecord() {
    this.isForward = false;
    init();
  }
  
  public ActivityClientRecord(IBinder paramIBinder1, Intent paramIntent, int paramInt, ActivityInfo paramActivityInfo, Configuration paramConfiguration, CompatibilityInfo paramCompatibilityInfo, String paramString, IVoiceInteractor paramIVoiceInteractor, Bundle paramBundle, PersistableBundle paramPersistableBundle, List<ResultInfo> paramList, List<ReferrerIntent> paramList1, boolean paramBoolean, ProfilerInfo paramProfilerInfo, ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder2, DisplayAdjustments.FixedRotationAdjustments paramFixedRotationAdjustments) {
    this.token = paramIBinder1;
    this.assistToken = paramIBinder2;
    this.ident = paramInt;
    this.intent = paramIntent;
    this.referrer = paramString;
    this.voiceInteractor = paramIVoiceInteractor;
    this.activityInfo = paramActivityInfo;
    this.compatInfo = paramCompatibilityInfo;
    this.state = paramBundle;
    this.persistentState = paramPersistableBundle;
    this.pendingResults = paramList;
    this.pendingIntents = paramList1;
    this.isForward = paramBoolean;
    this.profilerInfo = paramProfilerInfo;
    this.overrideConfig = paramConfiguration;
    this.packageInfo = paramClientTransactionHandler.getPackageInfoNoCheck(paramActivityInfo.applicationInfo, paramCompatibilityInfo);
    this.mPendingFixedRotationAdjustments = paramFixedRotationAdjustments;
    init();
  }
  
  private void init() {
    this.parent = null;
    this.embeddedID = null;
    this.paused = false;
    this.stopped = false;
    this.hideForNow = false;
    this.nextIdle = null;
    this.configCallback = new _$$Lambda$ActivityThread$ActivityClientRecord$HOrG1qglSjSUHSjKBn2rXtX0gGg(this);
  }
  
  private boolean isPreHoneycomb() {
    boolean bool;
    Activity activity = this.activity;
    if (activity != null && (activity.getApplicationInfo()).targetSdkVersion < 11) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isPreP() {
    boolean bool;
    Activity activity = this.activity;
    if (activity != null && (activity.getApplicationInfo()).targetSdkVersion < 28) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int getLifecycleState() {
    return this.mLifecycleState;
  }
  
  public String getStateString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ActivityClientRecord{");
    stringBuilder.append("paused=");
    stringBuilder.append(this.paused);
    stringBuilder.append(", stopped=");
    stringBuilder.append(this.stopped);
    stringBuilder.append(", hideForNow=");
    stringBuilder.append(this.hideForNow);
    stringBuilder.append(", startsNotResumed=");
    stringBuilder.append(this.startsNotResumed);
    stringBuilder.append(", isForward=");
    stringBuilder.append(this.isForward);
    stringBuilder.append(", pendingConfigChanges=");
    stringBuilder.append(this.pendingConfigChanges);
    stringBuilder.append(", preserveWindow=");
    stringBuilder.append(this.mPreserveWindow);
    if (this.activity != null) {
      stringBuilder.append(", Activity{");
      stringBuilder.append("resumed=");
      stringBuilder.append(this.activity.mResumed);
      stringBuilder.append(", stopped=");
      stringBuilder.append(this.activity.mStopped);
      stringBuilder.append(", finished=");
      stringBuilder.append(this.activity.isFinishing());
      stringBuilder.append(", destroyed=");
      stringBuilder.append(this.activity.isDestroyed());
      stringBuilder.append(", startedActivity=");
      stringBuilder.append(this.activity.mStartedActivity);
      stringBuilder.append(", changingConfigurations=");
      stringBuilder.append(this.activity.mChangingConfigurations);
      stringBuilder.append("}");
    } 
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public boolean isPersistable() {
    boolean bool;
    if (this.activityInfo.persistableMode == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isVisibleFromServer() {
    boolean bool;
    Activity activity = this.activity;
    if (activity != null && activity.mVisibleFromServer) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void setState(int paramInt) {
    this.mLifecycleState = paramInt;
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          if (paramInt != 4) {
            if (paramInt == 5) {
              this.paused = true;
              this.stopped = true;
            } 
          } else {
            this.paused = true;
            this.stopped = false;
          } 
        } else {
          this.paused = false;
          this.stopped = false;
        } 
      } else {
        this.paused = true;
        this.stopped = false;
      } 
    } else {
      this.paused = true;
      this.stopped = true;
    } 
  }
  
  public String toString() {
    String str;
    Intent intent = this.intent;
    if (intent != null) {
      ComponentName componentName = intent.getComponent();
    } else {
      intent = null;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ActivityRecord{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" token=");
    stringBuilder.append(this.token);
    stringBuilder.append(" ");
    if (intent == null) {
      str = "no component name";
    } else {
      str = str.toShortString();
    } 
    stringBuilder.append(str);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$ActivityClientRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */