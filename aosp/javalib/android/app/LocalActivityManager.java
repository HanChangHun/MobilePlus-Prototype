package android.app;

import android.app.servertransaction.PendingTransactionActions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Window;
import com.android.internal.content.ReferrerIntent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class LocalActivityManager {
  static final int CREATED = 2;
  
  static final int DESTROYED = 5;
  
  static final int INITIALIZING = 1;
  
  static final int RESTORED = 0;
  
  static final int RESUMED = 4;
  
  static final int STARTED = 3;
  
  private static final String TAG = "LocalActivityManager";
  
  private static final boolean localLOGV = false;
  
  private final Map<String, LocalActivityRecord> mActivities = new HashMap<>();
  
  private final ArrayList<LocalActivityRecord> mActivityArray = new ArrayList<>();
  
  private final ActivityThread mActivityThread = ActivityThread.currentActivityThread();
  
  private int mCurState = 1;
  
  private boolean mFinishing;
  
  private final Activity mParent;
  
  private LocalActivityRecord mResumed;
  
  private boolean mSingleMode;
  
  public LocalActivityManager(Activity paramActivity, boolean paramBoolean) {
    this.mParent = paramActivity;
    this.mSingleMode = paramBoolean;
  }
  
  private void moveToState(LocalActivityRecord paramLocalActivityRecord, int paramInt) {
    if (paramLocalActivityRecord.curState == 0 || paramLocalActivityRecord.curState == 5)
      return; 
    if (paramLocalActivityRecord.curState == 1) {
      HashMap<String, Object> hashMap = this.mParent.getLastNonConfigurationChildInstances();
      if (hashMap != null) {
        hashMap = (HashMap<String, Object>)hashMap.get(paramLocalActivityRecord.id);
      } else {
        hashMap = null;
      } 
      if (hashMap != null) {
        Activity.NonConfigurationInstances nonConfigurationInstances2 = new Activity.NonConfigurationInstances();
        nonConfigurationInstances2.activity = hashMap;
        Activity.NonConfigurationInstances nonConfigurationInstances1 = nonConfigurationInstances2;
      } else {
        hashMap = null;
      } 
      if (paramLocalActivityRecord.activityInfo == null)
        paramLocalActivityRecord.activityInfo = this.mActivityThread.resolveActivityInfo(paramLocalActivityRecord.intent); 
      paramLocalActivityRecord.activity = this.mActivityThread.startActivityNow(this.mParent, paramLocalActivityRecord.id, paramLocalActivityRecord.intent, paramLocalActivityRecord.activityInfo, (IBinder)paramLocalActivityRecord, paramLocalActivityRecord.instanceState, (Activity.NonConfigurationInstances)hashMap, (IBinder)paramLocalActivityRecord);
      if (paramLocalActivityRecord.activity == null)
        return; 
      paramLocalActivityRecord.window = paramLocalActivityRecord.activity.getWindow();
      paramLocalActivityRecord.instanceState = null;
      ActivityThread.ActivityClientRecord activityClientRecord = this.mActivityThread.getActivityClient((IBinder)paramLocalActivityRecord);
      if (!paramLocalActivityRecord.activity.mFinished) {
        PendingTransactionActions pendingTransactionActions = new PendingTransactionActions();
        pendingTransactionActions.setOldState(activityClientRecord.state);
        pendingTransactionActions.setRestoreInstanceState(true);
        pendingTransactionActions.setCallOnPostCreate(true);
      } else {
        hashMap = null;
      } 
      this.mActivityThread.handleStartActivity((IBinder)paramLocalActivityRecord, (PendingTransactionActions)hashMap);
      paramLocalActivityRecord.curState = 3;
      if (paramInt == 4) {
        this.mActivityThread.performResumeActivity((IBinder)paramLocalActivityRecord, true, "moveToState-INITIALIZING");
        paramLocalActivityRecord.curState = 4;
      } 
      return;
    } 
    int i = paramLocalActivityRecord.curState;
    if (i != 2) {
      if (i != 3) {
        if (i != 4)
          return; 
        if (paramInt == 3) {
          performPause(paramLocalActivityRecord, this.mFinishing);
          paramLocalActivityRecord.curState = 3;
        } 
        if (paramInt == 2) {
          performPause(paramLocalActivityRecord, this.mFinishing);
          this.mActivityThread.performStopActivity((IBinder)paramLocalActivityRecord, false, "moveToState-RESUMED");
          paramLocalActivityRecord.curState = 2;
        } 
        return;
      } 
      if (paramInt == 4) {
        this.mActivityThread.performResumeActivity((IBinder)paramLocalActivityRecord, true, "moveToState-STARTED");
        paramLocalActivityRecord.instanceState = null;
        paramLocalActivityRecord.curState = 4;
      } 
      if (paramInt == 2) {
        this.mActivityThread.performStopActivity((IBinder)paramLocalActivityRecord, false, "moveToState-STARTED");
        paramLocalActivityRecord.curState = 2;
      } 
      return;
    } 
    if (paramInt == 3) {
      this.mActivityThread.performRestartActivity((IBinder)paramLocalActivityRecord, true);
      paramLocalActivityRecord.curState = 3;
    } 
    if (paramInt == 4) {
      this.mActivityThread.performRestartActivity((IBinder)paramLocalActivityRecord, true);
      this.mActivityThread.performResumeActivity((IBinder)paramLocalActivityRecord, true, "moveToState-CREATED");
      paramLocalActivityRecord.curState = 4;
    } 
  }
  
  private Window performDestroy(LocalActivityRecord paramLocalActivityRecord, boolean paramBoolean) {
    Window window = paramLocalActivityRecord.window;
    if (paramLocalActivityRecord.curState == 4 && !paramBoolean)
      performPause(paramLocalActivityRecord, paramBoolean); 
    this.mActivityThread.performDestroyActivity((IBinder)paramLocalActivityRecord, paramBoolean, 0, false, "LocalActivityManager::performDestroy");
    paramLocalActivityRecord.activity = null;
    paramLocalActivityRecord.window = null;
    if (paramBoolean)
      paramLocalActivityRecord.instanceState = null; 
    paramLocalActivityRecord.curState = 5;
    return window;
  }
  
  private void performPause(LocalActivityRecord paramLocalActivityRecord, boolean paramBoolean) {
    boolean bool;
    if (paramLocalActivityRecord.instanceState == null) {
      bool = true;
    } else {
      bool = false;
    } 
    Bundle bundle = this.mActivityThread.performPauseActivity((IBinder)paramLocalActivityRecord, paramBoolean, "performPause", (PendingTransactionActions)null);
    if (bool)
      paramLocalActivityRecord.instanceState = bundle; 
  }
  
  public Window destroyActivity(String paramString, boolean paramBoolean) {
    LocalActivityRecord localActivityRecord = this.mActivities.get(paramString);
    Window window = null;
    if (localActivityRecord != null) {
      Window window1 = performDestroy(localActivityRecord, paramBoolean);
      window = window1;
      if (paramBoolean) {
        this.mActivities.remove(paramString);
        this.mActivityArray.remove(localActivityRecord);
        window = window1;
      } 
    } 
    return window;
  }
  
  public void dispatchCreate(Bundle paramBundle) {
    if (paramBundle != null)
      for (String str : paramBundle.keySet()) {
        try {
          Bundle bundle = paramBundle.getBundle(str);
          LocalActivityRecord localActivityRecord = this.mActivities.get(str);
          if (localActivityRecord != null) {
            localActivityRecord.instanceState = bundle;
            continue;
          } 
          localActivityRecord = new LocalActivityRecord();
          this(str, null);
          localActivityRecord.instanceState = bundle;
          this.mActivities.put(str, localActivityRecord);
          this.mActivityArray.add(localActivityRecord);
        } catch (Exception exception) {
          Log.e("LocalActivityManager", "Exception thrown when restoring LocalActivityManager state", exception);
        } 
      }  
    this.mCurState = 2;
  }
  
  public void dispatchDestroy(boolean paramBoolean) {
    int i = this.mActivityArray.size();
    for (byte b = 0; b < i; b++) {
      LocalActivityRecord localActivityRecord = this.mActivityArray.get(b);
      this.mActivityThread.performDestroyActivity((IBinder)localActivityRecord, paramBoolean, 0, false, "LocalActivityManager::dispatchDestroy");
    } 
    this.mActivities.clear();
    this.mActivityArray.clear();
  }
  
  public void dispatchPause(boolean paramBoolean) {
    if (paramBoolean)
      this.mFinishing = true; 
    this.mCurState = 3;
    if (this.mSingleMode) {
      LocalActivityRecord localActivityRecord = this.mResumed;
      if (localActivityRecord != null)
        moveToState(localActivityRecord, 3); 
    } else {
      int i = this.mActivityArray.size();
      for (byte b = 0; b < i; b++) {
        LocalActivityRecord localActivityRecord = this.mActivityArray.get(b);
        if (localActivityRecord.curState == 4)
          moveToState(localActivityRecord, 3); 
      } 
    } 
  }
  
  public void dispatchResume() {
    this.mCurState = 4;
    if (this.mSingleMode) {
      LocalActivityRecord localActivityRecord = this.mResumed;
      if (localActivityRecord != null)
        moveToState(localActivityRecord, 4); 
    } else {
      int i = this.mActivityArray.size();
      for (byte b = 0; b < i; b++)
        moveToState(this.mActivityArray.get(b), 4); 
    } 
  }
  
  public HashMap<String, Object> dispatchRetainNonConfigurationInstance() {
    HashMap<Object, Object> hashMap = null;
    int i = this.mActivityArray.size();
    byte b = 0;
    while (b < i) {
      LocalActivityRecord localActivityRecord = this.mActivityArray.get(b);
      HashMap<Object, Object> hashMap1 = hashMap;
      if (localActivityRecord != null) {
        hashMap1 = hashMap;
        if (localActivityRecord.activity != null) {
          Object object = localActivityRecord.activity.onRetainNonConfigurationInstance();
          hashMap1 = hashMap;
          if (object != null) {
            hashMap1 = hashMap;
            if (hashMap == null)
              hashMap1 = new HashMap<>(); 
            hashMap1.put(localActivityRecord.id, object);
          } 
        } 
      } 
      b++;
      hashMap = hashMap1;
    } 
    return (HashMap)hashMap;
  }
  
  public void dispatchStop() {
    this.mCurState = 2;
    int i = this.mActivityArray.size();
    for (byte b = 0; b < i; b++)
      moveToState(this.mActivityArray.get(b), 2); 
  }
  
  public Activity getActivity(String paramString) {
    LocalActivityRecord localActivityRecord = this.mActivities.get(paramString);
    if (localActivityRecord != null) {
      Activity activity = localActivityRecord.activity;
    } else {
      localActivityRecord = null;
    } 
    return (Activity)localActivityRecord;
  }
  
  public Activity getCurrentActivity() {
    LocalActivityRecord localActivityRecord = this.mResumed;
    if (localActivityRecord != null) {
      Activity activity = localActivityRecord.activity;
    } else {
      localActivityRecord = null;
    } 
    return (Activity)localActivityRecord;
  }
  
  public String getCurrentId() {
    LocalActivityRecord localActivityRecord = this.mResumed;
    if (localActivityRecord != null) {
      String str = localActivityRecord.id;
    } else {
      localActivityRecord = null;
    } 
    return (String)localActivityRecord;
  }
  
  public void removeAllActivities() {
    dispatchDestroy(true);
  }
  
  public Bundle saveInstanceState() {
    Bundle bundle = null;
    int i = this.mActivityArray.size();
    byte b = 0;
    while (b < i) {
      LocalActivityRecord localActivityRecord = this.mActivityArray.get(b);
      Bundle bundle1 = bundle;
      if (bundle == null)
        bundle1 = new Bundle(); 
      if ((localActivityRecord.instanceState != null || localActivityRecord.curState == 4) && localActivityRecord.activity != null) {
        bundle = new Bundle();
        localActivityRecord.activity.performSaveInstanceState(bundle);
        localActivityRecord.instanceState = bundle;
      } 
      if (localActivityRecord.instanceState != null)
        bundle1.putBundle(localActivityRecord.id, localActivityRecord.instanceState); 
      b++;
      bundle = bundle1;
    } 
    return bundle;
  }
  
  public Window startActivity(String paramString, Intent paramIntent) {
    if (this.mCurState != 1) {
      LocalActivityRecord localActivityRecord2;
      boolean bool2;
      ActivityInfo activityInfo3;
      boolean bool1 = false;
      boolean bool = false;
      ActivityInfo activityInfo1 = null;
      LocalActivityRecord localActivityRecord1 = this.mActivities.get(paramString);
      if (localActivityRecord1 == null) {
        localActivityRecord2 = new LocalActivityRecord(paramString, paramIntent);
        bool2 = true;
        activityInfo3 = activityInfo1;
      } else {
        bool2 = bool1;
        activityInfo3 = activityInfo1;
        localActivityRecord2 = localActivityRecord1;
        if (localActivityRecord1.intent != null) {
          boolean bool3 = localActivityRecord1.intent.filterEquals(paramIntent);
          bool2 = bool1;
          bool = bool3;
          activityInfo3 = activityInfo1;
          localActivityRecord2 = localActivityRecord1;
          if (bool3) {
            activityInfo3 = localActivityRecord1.activityInfo;
            localActivityRecord2 = localActivityRecord1;
            bool = bool3;
            bool2 = bool1;
          } 
        } 
      } 
      ActivityInfo activityInfo2 = activityInfo3;
      if (activityInfo3 == null)
        activityInfo2 = this.mActivityThread.resolveActivityInfo(paramIntent); 
      if (this.mSingleMode) {
        LocalActivityRecord localActivityRecord = this.mResumed;
        if (localActivityRecord != null && localActivityRecord != localActivityRecord2 && this.mCurState == 4)
          moveToState(localActivityRecord, 3); 
      } 
      if (bool2) {
        this.mActivities.put(paramString, localActivityRecord2);
        this.mActivityArray.add(localActivityRecord2);
      } else if (localActivityRecord2.activityInfo != null) {
        if (activityInfo2 == localActivityRecord2.activityInfo || (activityInfo2.name.equals(localActivityRecord2.activityInfo.name) && activityInfo2.packageName.equals(localActivityRecord2.activityInfo.packageName))) {
          if (activityInfo2.launchMode != 0 || (paramIntent.getFlags() & 0x20000000) != 0) {
            ArrayList<ReferrerIntent> arrayList = new ArrayList(1);
            arrayList.add(new ReferrerIntent(paramIntent, this.mParent.getPackageName()));
            this.mActivityThread.handleNewIntent((IBinder)localActivityRecord2, arrayList);
            localActivityRecord2.intent = paramIntent;
            moveToState(localActivityRecord2, this.mCurState);
            if (this.mSingleMode)
              this.mResumed = localActivityRecord2; 
            return localActivityRecord2.window;
          } 
          if (bool && (paramIntent.getFlags() & 0x4000000) == 0) {
            localActivityRecord2.intent = paramIntent;
            moveToState(localActivityRecord2, this.mCurState);
            if (this.mSingleMode)
              this.mResumed = localActivityRecord2; 
            return localActivityRecord2.window;
          } 
        } 
        performDestroy(localActivityRecord2, true);
      } 
      localActivityRecord2.intent = paramIntent;
      localActivityRecord2.curState = 1;
      localActivityRecord2.activityInfo = activityInfo2;
      moveToState(localActivityRecord2, this.mCurState);
      if (this.mSingleMode)
        this.mResumed = localActivityRecord2; 
      return localActivityRecord2.window;
    } 
    throw new IllegalStateException("Activities can't be added until the containing group has been created.");
  }
  
  private static class LocalActivityRecord extends Binder {
    Activity activity;
    
    ActivityInfo activityInfo;
    
    int curState = 0;
    
    final String id;
    
    Bundle instanceState;
    
    Intent intent;
    
    Window window;
    
    LocalActivityRecord(String param1String, Intent param1Intent) {
      this.id = param1String;
      this.intent = param1Intent;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LocalActivityManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */