package android.app;

import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.content.ComponentName;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.ParceledListSlice;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.service.voice.IVoiceInteractionSession;
import android.text.TextUtils;
import android.view.IRecentsAnimationRunner;
import android.view.RemoteAnimationAdapter;
import android.view.RemoteAnimationDefinition;
import android.window.IWindowOrganizerController;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.policy.IKeyguardDismissCallback;
import java.util.List;

public interface IActivityTaskManager extends IInterface {
  void activityDestroyed(IBinder paramIBinder) throws RemoteException;
  
  void activityIdle(IBinder paramIBinder, Configuration paramConfiguration, boolean paramBoolean) throws RemoteException;
  
  void activityPaused(IBinder paramIBinder) throws RemoteException;
  
  void activityRelaunched(IBinder paramIBinder) throws RemoteException;
  
  void activityResumed(IBinder paramIBinder) throws RemoteException;
  
  void activityStopped(IBinder paramIBinder, Bundle paramBundle, PersistableBundle paramPersistableBundle, CharSequence paramCharSequence) throws RemoteException;
  
  void activityTopResumedStateLost() throws RemoteException;
  
  int addAppTask(IBinder paramIBinder, Intent paramIntent, ActivityManager.TaskDescription paramTaskDescription, Bitmap paramBitmap) throws RemoteException;
  
  void alwaysShowUnsupportedCompileSdkWarning(ComponentName paramComponentName) throws RemoteException;
  
  void cancelRecentsAnimation(boolean paramBoolean) throws RemoteException;
  
  void cancelTaskWindowTransition(int paramInt) throws RemoteException;
  
  void clearLaunchParamsForPackages(List<String> paramList) throws RemoteException;
  
  boolean convertFromTranslucent(IBinder paramIBinder) throws RemoteException;
  
  boolean convertToTranslucent(IBinder paramIBinder, Bundle paramBundle) throws RemoteException;
  
  void dismissKeyguard(IBinder paramIBinder, IKeyguardDismissCallback paramIKeyguardDismissCallback, CharSequence paramCharSequence) throws RemoteException;
  
  boolean enterPictureInPictureMode(IBinder paramIBinder, PictureInPictureParams paramPictureInPictureParams) throws RemoteException;
  
  boolean finishActivity(IBinder paramIBinder, int paramInt1, Intent paramIntent, int paramInt2) throws RemoteException;
  
  boolean finishActivityAffinity(IBinder paramIBinder) throws RemoteException;
  
  void finishSubActivity(IBinder paramIBinder, String paramString, int paramInt) throws RemoteException;
  
  void finishVoiceTask(IVoiceInteractionSession paramIVoiceInteractionSession) throws RemoteException;
  
  ComponentName getActivityClassForToken(IBinder paramIBinder) throws RemoteException;
  
  Bundle getActivityOptions(IBinder paramIBinder) throws RemoteException;
  
  List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException;
  
  List<ActivityManager.StackInfo> getAllStackInfosOnDisplay(int paramInt) throws RemoteException;
  
  Point getAppTaskThumbnailSize() throws RemoteException;
  
  List<IBinder> getAppTasks(String paramString) throws RemoteException;
  
  Bundle getAssistContextExtras(int paramInt) throws RemoteException;
  
  ComponentName getCallingActivity(IBinder paramIBinder) throws RemoteException;
  
  String getCallingPackage(IBinder paramIBinder) throws RemoteException;
  
  ConfigurationInfo getDeviceConfigurationInfo() throws RemoteException;
  
  int getDisplayId(IBinder paramIBinder) throws RemoteException;
  
  List<ActivityManager.RunningTaskInfo> getFilteredTasks(int paramInt, boolean paramBoolean) throws RemoteException;
  
  ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException;
  
  int getFrontActivityScreenCompatMode() throws RemoteException;
  
  int getLastResumedActivityUserId() throws RemoteException;
  
  String getLaunchedFromPackage(IBinder paramIBinder) throws RemoteException;
  
  int getLaunchedFromUid(IBinder paramIBinder) throws RemoteException;
  
  int getLockTaskModeState() throws RemoteException;
  
  int getMaxNumPictureInPictureActions(IBinder paramIBinder) throws RemoteException;
  
  boolean getPackageAskScreenCompat(String paramString) throws RemoteException;
  
  String getPackageForToken(IBinder paramIBinder) throws RemoteException;
  
  int getPackageScreenCompatMode(String paramString) throws RemoteException;
  
  ParceledListSlice getRecentTasks(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  int getRequestedOrientation(IBinder paramIBinder) throws RemoteException;
  
  ActivityManager.StackInfo getStackInfo(int paramInt1, int paramInt2) throws RemoteException;
  
  ActivityManager.StackInfo getStackInfoOnDisplay(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  Rect getTaskBounds(int paramInt) throws RemoteException;
  
  ActivityManager.TaskDescription getTaskDescription(int paramInt) throws RemoteException;
  
  Bitmap getTaskDescriptionIcon(String paramString, int paramInt) throws RemoteException;
  
  int getTaskForActivity(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  ActivityManager.TaskSnapshot getTaskSnapshot(int paramInt, boolean paramBoolean) throws RemoteException;
  
  List<ActivityManager.RunningTaskInfo> getTasks(int paramInt) throws RemoteException;
  
  IBinder getUriPermissionOwnerForActivity(IBinder paramIBinder) throws RemoteException;
  
  IWindowOrganizerController getWindowOrganizerController() throws RemoteException;
  
  void invalidateHomeTaskSnapshot(IBinder paramIBinder) throws RemoteException;
  
  boolean isActivityStartAllowedOnDisplay(int paramInt1, Intent paramIntent, String paramString, int paramInt2) throws RemoteException;
  
  boolean isAssistDataAllowedOnCurrentActivity() throws RemoteException;
  
  boolean isImmersive(IBinder paramIBinder) throws RemoteException;
  
  boolean isInLockTaskMode() throws RemoteException;
  
  boolean isRootVoiceInteraction(IBinder paramIBinder) throws RemoteException;
  
  boolean isTopActivityImmersive() throws RemoteException;
  
  boolean isTopOfTask(IBinder paramIBinder) throws RemoteException;
  
  void keyguardGoingAway(int paramInt) throws RemoteException;
  
  boolean launchAssistIntent(Intent paramIntent, int paramInt1, String paramString, int paramInt2, Bundle paramBundle) throws RemoteException;
  
  boolean moveActivityTaskToBack(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void moveStackToDisplay(int paramInt1, int paramInt2) throws RemoteException;
  
  void moveTaskToFront(IApplicationThread paramIApplicationThread, String paramString, int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException;
  
  void moveTaskToStack(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException;
  
  boolean moveTopActivityToPinnedStack(int paramInt, Rect paramRect) throws RemoteException;
  
  boolean navigateUpTo(IBinder paramIBinder, Intent paramIntent1, int paramInt, Intent paramIntent2) throws RemoteException;
  
  void notifyActivityDrawn(IBinder paramIBinder) throws RemoteException;
  
  void notifyEnterAnimationComplete(IBinder paramIBinder) throws RemoteException;
  
  void notifyLaunchTaskBehindComplete(IBinder paramIBinder) throws RemoteException;
  
  void onBackPressedOnTaskRoot(IBinder paramIBinder, IRequestFinishCallback paramIRequestFinishCallback) throws RemoteException;
  
  void overridePendingTransition(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2) throws RemoteException;
  
  void positionTaskInStack(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void registerRemoteAnimationForNextActivityStart(String paramString, RemoteAnimationAdapter paramRemoteAnimationAdapter) throws RemoteException;
  
  void registerRemoteAnimations(IBinder paramIBinder, RemoteAnimationDefinition paramRemoteAnimationDefinition) throws RemoteException;
  
  void registerRemoteAnimationsForDisplay(int paramInt, RemoteAnimationDefinition paramRemoteAnimationDefinition) throws RemoteException;
  
  void registerTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException;
  
  boolean releaseActivityInstance(IBinder paramIBinder) throws RemoteException;
  
  void releaseSomeActivities(IApplicationThread paramIApplicationThread) throws RemoteException;
  
  void removeAllVisibleRecentTasks() throws RemoteException;
  
  void removeStack(int paramInt) throws RemoteException;
  
  void removeStacksInWindowingModes(int[] paramArrayOfint) throws RemoteException;
  
  void removeStacksWithActivityTypes(int[] paramArrayOfint) throws RemoteException;
  
  boolean removeTask(int paramInt) throws RemoteException;
  
  void reportActivityFullyDrawn(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void reportAssistContextExtras(IBinder paramIBinder, Bundle paramBundle, AssistStructure paramAssistStructure, AssistContent paramAssistContent, Uri paramUri) throws RemoteException;
  
  void reportSizeConfigurations(IBinder paramIBinder, int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3) throws RemoteException;
  
  boolean requestAssistContextExtras(int paramInt, IAssistDataReceiver paramIAssistDataReceiver, Bundle paramBundle, IBinder paramIBinder, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException;
  
  boolean requestAutofillData(IAssistDataReceiver paramIAssistDataReceiver, Bundle paramBundle, IBinder paramIBinder, int paramInt) throws RemoteException;
  
  void requestPictureInPictureMode(IBinder paramIBinder) throws RemoteException;
  
  IBinder requestStartActivityPermissionToken(IBinder paramIBinder) throws RemoteException;
  
  void resizeDockedStack(Rect paramRect1, Rect paramRect2, Rect paramRect3, Rect paramRect4, Rect paramRect5) throws RemoteException;
  
  boolean resizeTask(int paramInt1, Rect paramRect, int paramInt2) throws RemoteException;
  
  void restartActivityProcessIfVisible(IBinder paramIBinder) throws RemoteException;
  
  void resumeAppSwitches() throws RemoteException;
  
  void setActivityController(IActivityController paramIActivityController, boolean paramBoolean) throws RemoteException;
  
  void setDisablePreviewScreenshots(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void setDisplayToSingleTaskInstance(int paramInt) throws RemoteException;
  
  void setFocusedStack(int paramInt) throws RemoteException;
  
  void setFocusedTask(int paramInt) throws RemoteException;
  
  void setFrontActivityScreenCompatMode(int paramInt) throws RemoteException;
  
  void setImmersive(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void setInheritShowWhenLocked(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void setLockScreenShown(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException;
  
  void setPackageAskScreenCompat(String paramString, boolean paramBoolean) throws RemoteException;
  
  void setPackageScreenCompatMode(String paramString, int paramInt) throws RemoteException;
  
  void setPersistentVrThread(int paramInt) throws RemoteException;
  
  void setPictureInPictureParams(IBinder paramIBinder, PictureInPictureParams paramPictureInPictureParams) throws RemoteException;
  
  void setRequestedOrientation(IBinder paramIBinder, int paramInt) throws RemoteException;
  
  void setShowWhenLocked(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void setSplitScreenResizing(boolean paramBoolean) throws RemoteException;
  
  void setTaskDescription(IBinder paramIBinder, ActivityManager.TaskDescription paramTaskDescription) throws RemoteException;
  
  void setTaskResizeable(int paramInt1, int paramInt2) throws RemoteException;
  
  boolean setTaskWindowingMode(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException;
  
  boolean setTaskWindowingModeSplitScreenPrimary(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void setTurnScreenOn(IBinder paramIBinder, boolean paramBoolean) throws RemoteException;
  
  void setVoiceKeepAwake(IVoiceInteractionSession paramIVoiceInteractionSession, boolean paramBoolean) throws RemoteException;
  
  int setVrMode(IBinder paramIBinder, boolean paramBoolean, ComponentName paramComponentName) throws RemoteException;
  
  void setVrThread(int paramInt) throws RemoteException;
  
  boolean shouldUpRecreateTask(IBinder paramIBinder, String paramString) throws RemoteException;
  
  boolean showAssistFromActivity(IBinder paramIBinder, Bundle paramBundle) throws RemoteException;
  
  void showLockTaskEscapeMessage(IBinder paramIBinder) throws RemoteException;
  
  int startActivities(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, IBinder paramIBinder, Bundle paramBundle, int paramInt) throws RemoteException;
  
  int startActivity(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException;
  
  WaitResult startActivityAndWait(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException;
  
  int startActivityAsCaller(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder1, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, IBinder paramIBinder2, boolean paramBoolean, int paramInt3) throws RemoteException;
  
  int startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException;
  
  int startActivityFromRecents(int paramInt, Bundle paramBundle) throws RemoteException;
  
  int startActivityIntentSender(IApplicationThread paramIApplicationThread, IIntentSender paramIIntentSender, IBinder paramIBinder1, Intent paramIntent, String paramString1, IBinder paramIBinder2, String paramString2, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) throws RemoteException;
  
  int startActivityWithConfig(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, Configuration paramConfiguration, Bundle paramBundle, int paramInt3) throws RemoteException;
  
  int startAssistantActivity(String paramString1, String paramString2, int paramInt1, int paramInt2, Intent paramIntent, String paramString3, Bundle paramBundle, int paramInt3) throws RemoteException;
  
  boolean startDreamActivity(Intent paramIntent) throws RemoteException;
  
  void startLocalVoiceInteraction(IBinder paramIBinder, Bundle paramBundle) throws RemoteException;
  
  void startLockTaskModeByToken(IBinder paramIBinder) throws RemoteException;
  
  boolean startNextMatchingActivity(IBinder paramIBinder, Intent paramIntent, Bundle paramBundle) throws RemoteException;
  
  void startRecentsActivity(Intent paramIntent, IAssistDataReceiver paramIAssistDataReceiver, IRecentsAnimationRunner paramIRecentsAnimationRunner) throws RemoteException;
  
  void startSystemLockTaskMode(int paramInt) throws RemoteException;
  
  int startVoiceActivity(String paramString1, String paramString2, int paramInt1, int paramInt2, Intent paramIntent, String paramString3, IVoiceInteractionSession paramIVoiceInteractionSession, IVoiceInteractor paramIVoiceInteractor, int paramInt3, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt4) throws RemoteException;
  
  void stopAppSwitches() throws RemoteException;
  
  void stopLocalVoiceInteraction(IBinder paramIBinder) throws RemoteException;
  
  void stopLockTaskModeByToken(IBinder paramIBinder) throws RemoteException;
  
  void stopSystemLockTaskMode() throws RemoteException;
  
  boolean supportsLocalVoiceInteraction() throws RemoteException;
  
  void suppressResizeConfigChanges(boolean paramBoolean) throws RemoteException;
  
  void toggleFreeformWindowingMode(IBinder paramIBinder) throws RemoteException;
  
  void unhandledBack() throws RemoteException;
  
  void unregisterRemoteAnimations(IBinder paramIBinder) throws RemoteException;
  
  void unregisterTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException;
  
  boolean updateConfiguration(Configuration paramConfiguration) throws RemoteException;
  
  void updateLockTaskFeatures(int paramInt1, int paramInt2) throws RemoteException;
  
  void updateLockTaskPackages(int paramInt, String[] paramArrayOfString) throws RemoteException;
  
  boolean willActivityBeVisible(IBinder paramIBinder) throws RemoteException;
  
  public static class Default implements IActivityTaskManager {
    public void activityDestroyed(IBinder param1IBinder) throws RemoteException {}
    
    public void activityIdle(IBinder param1IBinder, Configuration param1Configuration, boolean param1Boolean) throws RemoteException {}
    
    public void activityPaused(IBinder param1IBinder) throws RemoteException {}
    
    public void activityRelaunched(IBinder param1IBinder) throws RemoteException {}
    
    public void activityResumed(IBinder param1IBinder) throws RemoteException {}
    
    public void activityStopped(IBinder param1IBinder, Bundle param1Bundle, PersistableBundle param1PersistableBundle, CharSequence param1CharSequence) throws RemoteException {}
    
    public void activityTopResumedStateLost() throws RemoteException {}
    
    public int addAppTask(IBinder param1IBinder, Intent param1Intent, ActivityManager.TaskDescription param1TaskDescription, Bitmap param1Bitmap) throws RemoteException {
      return 0;
    }
    
    public void alwaysShowUnsupportedCompileSdkWarning(ComponentName param1ComponentName) throws RemoteException {}
    
    public IBinder asBinder() {
      return null;
    }
    
    public void cancelRecentsAnimation(boolean param1Boolean) throws RemoteException {}
    
    public void cancelTaskWindowTransition(int param1Int) throws RemoteException {}
    
    public void clearLaunchParamsForPackages(List<String> param1List) throws RemoteException {}
    
    public boolean convertFromTranslucent(IBinder param1IBinder) throws RemoteException {
      return false;
    }
    
    public boolean convertToTranslucent(IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {
      return false;
    }
    
    public void dismissKeyguard(IBinder param1IBinder, IKeyguardDismissCallback param1IKeyguardDismissCallback, CharSequence param1CharSequence) throws RemoteException {}
    
    public boolean enterPictureInPictureMode(IBinder param1IBinder, PictureInPictureParams param1PictureInPictureParams) throws RemoteException {
      return false;
    }
    
    public boolean finishActivity(IBinder param1IBinder, int param1Int1, Intent param1Intent, int param1Int2) throws RemoteException {
      return false;
    }
    
    public boolean finishActivityAffinity(IBinder param1IBinder) throws RemoteException {
      return false;
    }
    
    public void finishSubActivity(IBinder param1IBinder, String param1String, int param1Int) throws RemoteException {}
    
    public void finishVoiceTask(IVoiceInteractionSession param1IVoiceInteractionSession) throws RemoteException {}
    
    public ComponentName getActivityClassForToken(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public Bundle getActivityOptions(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
      return null;
    }
    
    public List<ActivityManager.StackInfo> getAllStackInfosOnDisplay(int param1Int) throws RemoteException {
      return null;
    }
    
    public Point getAppTaskThumbnailSize() throws RemoteException {
      return null;
    }
    
    public List<IBinder> getAppTasks(String param1String) throws RemoteException {
      return null;
    }
    
    public Bundle getAssistContextExtras(int param1Int) throws RemoteException {
      return null;
    }
    
    public ComponentName getCallingActivity(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public String getCallingPackage(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public ConfigurationInfo getDeviceConfigurationInfo() throws RemoteException {
      return null;
    }
    
    public int getDisplayId(IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public List<ActivityManager.RunningTaskInfo> getFilteredTasks(int param1Int, boolean param1Boolean) throws RemoteException {
      return null;
    }
    
    public ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException {
      return null;
    }
    
    public int getFrontActivityScreenCompatMode() throws RemoteException {
      return 0;
    }
    
    public int getLastResumedActivityUserId() throws RemoteException {
      return 0;
    }
    
    public String getLaunchedFromPackage(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public int getLaunchedFromUid(IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public int getLockTaskModeState() throws RemoteException {
      return 0;
    }
    
    public int getMaxNumPictureInPictureActions(IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public boolean getPackageAskScreenCompat(String param1String) throws RemoteException {
      return false;
    }
    
    public String getPackageForToken(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public int getPackageScreenCompatMode(String param1String) throws RemoteException {
      return 0;
    }
    
    public ParceledListSlice getRecentTasks(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      return null;
    }
    
    public int getRequestedOrientation(IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public ActivityManager.StackInfo getStackInfo(int param1Int1, int param1Int2) throws RemoteException {
      return null;
    }
    
    public ActivityManager.StackInfo getStackInfoOnDisplay(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      return null;
    }
    
    public Rect getTaskBounds(int param1Int) throws RemoteException {
      return null;
    }
    
    public ActivityManager.TaskDescription getTaskDescription(int param1Int) throws RemoteException {
      return null;
    }
    
    public Bitmap getTaskDescriptionIcon(String param1String, int param1Int) throws RemoteException {
      return null;
    }
    
    public int getTaskForActivity(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      return 0;
    }
    
    public ActivityManager.TaskSnapshot getTaskSnapshot(int param1Int, boolean param1Boolean) throws RemoteException {
      return null;
    }
    
    public List<ActivityManager.RunningTaskInfo> getTasks(int param1Int) throws RemoteException {
      return null;
    }
    
    public IBinder getUriPermissionOwnerForActivity(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public IWindowOrganizerController getWindowOrganizerController() throws RemoteException {
      return null;
    }
    
    public void invalidateHomeTaskSnapshot(IBinder param1IBinder) throws RemoteException {}
    
    public boolean isActivityStartAllowedOnDisplay(int param1Int1, Intent param1Intent, String param1String, int param1Int2) throws RemoteException {
      return false;
    }
    
    public boolean isAssistDataAllowedOnCurrentActivity() throws RemoteException {
      return false;
    }
    
    public boolean isImmersive(IBinder param1IBinder) throws RemoteException {
      return false;
    }
    
    public boolean isInLockTaskMode() throws RemoteException {
      return false;
    }
    
    public boolean isRootVoiceInteraction(IBinder param1IBinder) throws RemoteException {
      return false;
    }
    
    public boolean isTopActivityImmersive() throws RemoteException {
      return false;
    }
    
    public boolean isTopOfTask(IBinder param1IBinder) throws RemoteException {
      return false;
    }
    
    public void keyguardGoingAway(int param1Int) throws RemoteException {}
    
    public boolean launchAssistIntent(Intent param1Intent, int param1Int1, String param1String, int param1Int2, Bundle param1Bundle) throws RemoteException {
      return false;
    }
    
    public boolean moveActivityTaskToBack(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public void moveStackToDisplay(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void moveTaskToFront(IApplicationThread param1IApplicationThread, String param1String, int param1Int1, int param1Int2, Bundle param1Bundle) throws RemoteException {}
    
    public void moveTaskToStack(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {}
    
    public boolean moveTopActivityToPinnedStack(int param1Int, Rect param1Rect) throws RemoteException {
      return false;
    }
    
    public boolean navigateUpTo(IBinder param1IBinder, Intent param1Intent1, int param1Int, Intent param1Intent2) throws RemoteException {
      return false;
    }
    
    public void notifyActivityDrawn(IBinder param1IBinder) throws RemoteException {}
    
    public void notifyEnterAnimationComplete(IBinder param1IBinder) throws RemoteException {}
    
    public void notifyLaunchTaskBehindComplete(IBinder param1IBinder) throws RemoteException {}
    
    public void onBackPressedOnTaskRoot(IBinder param1IBinder, IRequestFinishCallback param1IRequestFinishCallback) throws RemoteException {}
    
    public void overridePendingTransition(IBinder param1IBinder, String param1String, int param1Int1, int param1Int2) throws RemoteException {}
    
    public void positionTaskInStack(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {}
    
    public void registerRemoteAnimationForNextActivityStart(String param1String, RemoteAnimationAdapter param1RemoteAnimationAdapter) throws RemoteException {}
    
    public void registerRemoteAnimations(IBinder param1IBinder, RemoteAnimationDefinition param1RemoteAnimationDefinition) throws RemoteException {}
    
    public void registerRemoteAnimationsForDisplay(int param1Int, RemoteAnimationDefinition param1RemoteAnimationDefinition) throws RemoteException {}
    
    public void registerTaskStackListener(ITaskStackListener param1ITaskStackListener) throws RemoteException {}
    
    public boolean releaseActivityInstance(IBinder param1IBinder) throws RemoteException {
      return false;
    }
    
    public void releaseSomeActivities(IApplicationThread param1IApplicationThread) throws RemoteException {}
    
    public void removeAllVisibleRecentTasks() throws RemoteException {}
    
    public void removeStack(int param1Int) throws RemoteException {}
    
    public void removeStacksInWindowingModes(int[] param1ArrayOfint) throws RemoteException {}
    
    public void removeStacksWithActivityTypes(int[] param1ArrayOfint) throws RemoteException {}
    
    public boolean removeTask(int param1Int) throws RemoteException {
      return false;
    }
    
    public void reportActivityFullyDrawn(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void reportAssistContextExtras(IBinder param1IBinder, Bundle param1Bundle, AssistStructure param1AssistStructure, AssistContent param1AssistContent, Uri param1Uri) throws RemoteException {}
    
    public void reportSizeConfigurations(IBinder param1IBinder, int[] param1ArrayOfint1, int[] param1ArrayOfint2, int[] param1ArrayOfint3) throws RemoteException {}
    
    public boolean requestAssistContextExtras(int param1Int, IAssistDataReceiver param1IAssistDataReceiver, Bundle param1Bundle, IBinder param1IBinder, boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      return false;
    }
    
    public boolean requestAutofillData(IAssistDataReceiver param1IAssistDataReceiver, Bundle param1Bundle, IBinder param1IBinder, int param1Int) throws RemoteException {
      return false;
    }
    
    public void requestPictureInPictureMode(IBinder param1IBinder) throws RemoteException {}
    
    public IBinder requestStartActivityPermissionToken(IBinder param1IBinder) throws RemoteException {
      return null;
    }
    
    public void resizeDockedStack(Rect param1Rect1, Rect param1Rect2, Rect param1Rect3, Rect param1Rect4, Rect param1Rect5) throws RemoteException {}
    
    public boolean resizeTask(int param1Int1, Rect param1Rect, int param1Int2) throws RemoteException {
      return false;
    }
    
    public void restartActivityProcessIfVisible(IBinder param1IBinder) throws RemoteException {}
    
    public void resumeAppSwitches() throws RemoteException {}
    
    public void setActivityController(IActivityController param1IActivityController, boolean param1Boolean) throws RemoteException {}
    
    public void setDisablePreviewScreenshots(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void setDisplayToSingleTaskInstance(int param1Int) throws RemoteException {}
    
    public void setFocusedStack(int param1Int) throws RemoteException {}
    
    public void setFocusedTask(int param1Int) throws RemoteException {}
    
    public void setFrontActivityScreenCompatMode(int param1Int) throws RemoteException {}
    
    public void setImmersive(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void setInheritShowWhenLocked(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void setLockScreenShown(boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {}
    
    public void setPackageAskScreenCompat(String param1String, boolean param1Boolean) throws RemoteException {}
    
    public void setPackageScreenCompatMode(String param1String, int param1Int) throws RemoteException {}
    
    public void setPersistentVrThread(int param1Int) throws RemoteException {}
    
    public void setPictureInPictureParams(IBinder param1IBinder, PictureInPictureParams param1PictureInPictureParams) throws RemoteException {}
    
    public void setRequestedOrientation(IBinder param1IBinder, int param1Int) throws RemoteException {}
    
    public void setShowWhenLocked(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void setSplitScreenResizing(boolean param1Boolean) throws RemoteException {}
    
    public void setTaskDescription(IBinder param1IBinder, ActivityManager.TaskDescription param1TaskDescription) throws RemoteException {}
    
    public void setTaskResizeable(int param1Int1, int param1Int2) throws RemoteException {}
    
    public boolean setTaskWindowingMode(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public boolean setTaskWindowingModeSplitScreenPrimary(int param1Int, boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public void setTurnScreenOn(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {}
    
    public void setVoiceKeepAwake(IVoiceInteractionSession param1IVoiceInteractionSession, boolean param1Boolean) throws RemoteException {}
    
    public int setVrMode(IBinder param1IBinder, boolean param1Boolean, ComponentName param1ComponentName) throws RemoteException {
      return 0;
    }
    
    public void setVrThread(int param1Int) throws RemoteException {}
    
    public boolean shouldUpRecreateTask(IBinder param1IBinder, String param1String) throws RemoteException {
      return false;
    }
    
    public boolean showAssistFromActivity(IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {
      return false;
    }
    
    public void showLockTaskEscapeMessage(IBinder param1IBinder) throws RemoteException {}
    
    public int startActivities(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent[] param1ArrayOfIntent, String[] param1ArrayOfString, IBinder param1IBinder, Bundle param1Bundle, int param1Int) throws RemoteException {
      return 0;
    }
    
    public int startActivity(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle) throws RemoteException {
      return 0;
    }
    
    public WaitResult startActivityAndWait(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int3) throws RemoteException {
      return null;
    }
    
    public int startActivityAsCaller(IApplicationThread param1IApplicationThread, String param1String1, Intent param1Intent, String param1String2, IBinder param1IBinder1, String param1String3, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, IBinder param1IBinder2, boolean param1Boolean, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public int startActivityAsUser(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public int startActivityFromRecents(int param1Int, Bundle param1Bundle) throws RemoteException {
      return 0;
    }
    
    public int startActivityIntentSender(IApplicationThread param1IApplicationThread, IIntentSender param1IIntentSender, IBinder param1IBinder1, Intent param1Intent, String param1String1, IBinder param1IBinder2, String param1String2, int param1Int1, int param1Int2, int param1Int3, Bundle param1Bundle) throws RemoteException {
      return 0;
    }
    
    public int startActivityWithConfig(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, Configuration param1Configuration, Bundle param1Bundle, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public int startAssistantActivity(String param1String1, String param1String2, int param1Int1, int param1Int2, Intent param1Intent, String param1String3, Bundle param1Bundle, int param1Int3) throws RemoteException {
      return 0;
    }
    
    public boolean startDreamActivity(Intent param1Intent) throws RemoteException {
      return false;
    }
    
    public void startLocalVoiceInteraction(IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {}
    
    public void startLockTaskModeByToken(IBinder param1IBinder) throws RemoteException {}
    
    public boolean startNextMatchingActivity(IBinder param1IBinder, Intent param1Intent, Bundle param1Bundle) throws RemoteException {
      return false;
    }
    
    public void startRecentsActivity(Intent param1Intent, IAssistDataReceiver param1IAssistDataReceiver, IRecentsAnimationRunner param1IRecentsAnimationRunner) throws RemoteException {}
    
    public void startSystemLockTaskMode(int param1Int) throws RemoteException {}
    
    public int startVoiceActivity(String param1String1, String param1String2, int param1Int1, int param1Int2, Intent param1Intent, String param1String3, IVoiceInteractionSession param1IVoiceInteractionSession, IVoiceInteractor param1IVoiceInteractor, int param1Int3, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int4) throws RemoteException {
      return 0;
    }
    
    public void stopAppSwitches() throws RemoteException {}
    
    public void stopLocalVoiceInteraction(IBinder param1IBinder) throws RemoteException {}
    
    public void stopLockTaskModeByToken(IBinder param1IBinder) throws RemoteException {}
    
    public void stopSystemLockTaskMode() throws RemoteException {}
    
    public boolean supportsLocalVoiceInteraction() throws RemoteException {
      return false;
    }
    
    public void suppressResizeConfigChanges(boolean param1Boolean) throws RemoteException {}
    
    public void toggleFreeformWindowingMode(IBinder param1IBinder) throws RemoteException {}
    
    public void unhandledBack() throws RemoteException {}
    
    public void unregisterRemoteAnimations(IBinder param1IBinder) throws RemoteException {}
    
    public void unregisterTaskStackListener(ITaskStackListener param1ITaskStackListener) throws RemoteException {}
    
    public boolean updateConfiguration(Configuration param1Configuration) throws RemoteException {
      return false;
    }
    
    public void updateLockTaskFeatures(int param1Int1, int param1Int2) throws RemoteException {}
    
    public void updateLockTaskPackages(int param1Int, String[] param1ArrayOfString) throws RemoteException {}
    
    public boolean willActivityBeVisible(IBinder param1IBinder) throws RemoteException {
      return false;
    }
  }
  
  public static abstract class Stub extends Binder implements IActivityTaskManager {
    private static final String DESCRIPTOR = "android.app.IActivityTaskManager";
    
    static final int TRANSACTION_activityDestroyed = 23;
    
    static final int TRANSACTION_activityIdle = 18;
    
    static final int TRANSACTION_activityPaused = 21;
    
    static final int TRANSACTION_activityRelaunched = 24;
    
    static final int TRANSACTION_activityResumed = 19;
    
    static final int TRANSACTION_activityStopped = 22;
    
    static final int TRANSACTION_activityTopResumedStateLost = 20;
    
    static final int TRANSACTION_addAppTask = 75;
    
    static final int TRANSACTION_alwaysShowUnsupportedCompileSdkWarning = 141;
    
    static final int TRANSACTION_cancelRecentsAnimation = 60;
    
    static final int TRANSACTION_cancelTaskWindowTransition = 127;
    
    static final int TRANSACTION_clearLaunchParamsForPackages = 152;
    
    static final int TRANSACTION_convertFromTranslucent = 43;
    
    static final int TRANSACTION_convertToTranslucent = 44;
    
    static final int TRANSACTION_dismissKeyguard = 126;
    
    static final int TRANSACTION_enterPictureInPictureMode = 113;
    
    static final int TRANSACTION_finishActivity = 16;
    
    static final int TRANSACTION_finishActivityAffinity = 17;
    
    static final int TRANSACTION_finishSubActivity = 38;
    
    static final int TRANSACTION_finishVoiceTask = 71;
    
    static final int TRANSACTION_getActivityClassForToken = 107;
    
    static final int TRANSACTION_getActivityOptions = 67;
    
    static final int TRANSACTION_getAllStackInfos = 93;
    
    static final int TRANSACTION_getAllStackInfosOnDisplay = 95;
    
    static final int TRANSACTION_getAppTaskThumbnailSize = 76;
    
    static final int TRANSACTION_getAppTasks = 68;
    
    static final int TRANSACTION_getAssistContextExtras = 98;
    
    static final int TRANSACTION_getCallingActivity = 28;
    
    static final int TRANSACTION_getCallingPackage = 27;
    
    static final int TRANSACTION_getDeviceConfigurationInfo = 125;
    
    static final int TRANSACTION_getDisplayId = 47;
    
    static final int TRANSACTION_getFilteredTasks = 33;
    
    static final int TRANSACTION_getFocusedStackInfo = 58;
    
    static final int TRANSACTION_getFrontActivityScreenCompatMode = 25;
    
    static final int TRANSACTION_getLastResumedActivityUserId = 131;
    
    static final int TRANSACTION_getLaunchedFromPackage = 55;
    
    static final int TRANSACTION_getLaunchedFromUid = 54;
    
    static final int TRANSACTION_getLockTaskModeState = 65;
    
    static final int TRANSACTION_getMaxNumPictureInPictureActions = 116;
    
    static final int TRANSACTION_getPackageAskScreenCompat = 150;
    
    static final int TRANSACTION_getPackageForToken = 108;
    
    static final int TRANSACTION_getPackageScreenCompatMode = 148;
    
    static final int TRANSACTION_getRecentTasks = 39;
    
    static final int TRANSACTION_getRequestedOrientation = 42;
    
    static final int TRANSACTION_getStackInfo = 94;
    
    static final int TRANSACTION_getStackInfoOnDisplay = 96;
    
    static final int TRANSACTION_getTaskBounds = 59;
    
    static final int TRANSACTION_getTaskDescription = 52;
    
    static final int TRANSACTION_getTaskDescriptionIcon = 80;
    
    static final int TRANSACTION_getTaskForActivity = 37;
    
    static final int TRANSACTION_getTaskSnapshot = 128;
    
    static final int TRANSACTION_getTasks = 32;
    
    static final int TRANSACTION_getUriPermissionOwnerForActivity = 117;
    
    static final int TRANSACTION_getWindowOrganizerController = 119;
    
    static final int TRANSACTION_invalidateHomeTaskSnapshot = 130;
    
    static final int TRANSACTION_isActivityStartAllowedOnDisplay = 14;
    
    static final int TRANSACTION_isAssistDataAllowedOnCurrentActivity = 102;
    
    static final int TRANSACTION_isImmersive = 48;
    
    static final int TRANSACTION_isInLockTaskMode = 64;
    
    static final int TRANSACTION_isRootVoiceInteraction = 104;
    
    static final int TRANSACTION_isTopActivityImmersive = 50;
    
    static final int TRANSACTION_isTopOfTask = 72;
    
    static final int TRANSACTION_keyguardGoingAway = 106;
    
    static final int TRANSACTION_launchAssistIntent = 99;
    
    static final int TRANSACTION_moveActivityTaskToBack = 51;
    
    static final int TRANSACTION_moveStackToDisplay = 86;
    
    static final int TRANSACTION_moveTaskToFront = 36;
    
    static final int TRANSACTION_moveTaskToStack = 89;
    
    static final int TRANSACTION_moveTopActivityToPinnedStack = 112;
    
    static final int TRANSACTION_navigateUpTo = 35;
    
    static final int TRANSACTION_notifyActivityDrawn = 45;
    
    static final int TRANSACTION_notifyEnterAnimationComplete = 74;
    
    static final int TRANSACTION_notifyLaunchTaskBehindComplete = 73;
    
    static final int TRANSACTION_onBackPressedOnTaskRoot = 155;
    
    static final int TRANSACTION_overridePendingTransition = 53;
    
    static final int TRANSACTION_positionTaskInStack = 109;
    
    static final int TRANSACTION_registerRemoteAnimationForNextActivityStart = 139;
    
    static final int TRANSACTION_registerRemoteAnimations = 137;
    
    static final int TRANSACTION_registerRemoteAnimationsForDisplay = 140;
    
    static final int TRANSACTION_registerTaskStackListener = 81;
    
    static final int TRANSACTION_releaseActivityInstance = 77;
    
    static final int TRANSACTION_releaseSomeActivities = 79;
    
    static final int TRANSACTION_removeAllVisibleRecentTasks = 31;
    
    static final int TRANSACTION_removeStack = 87;
    
    static final int TRANSACTION_removeStacksInWindowingModes = 91;
    
    static final int TRANSACTION_removeStacksWithActivityTypes = 92;
    
    static final int TRANSACTION_removeTask = 30;
    
    static final int TRANSACTION_reportActivityFullyDrawn = 46;
    
    static final int TRANSACTION_reportAssistContextExtras = 56;
    
    static final int TRANSACTION_reportSizeConfigurations = 110;
    
    static final int TRANSACTION_requestAssistContextExtras = 100;
    
    static final int TRANSACTION_requestAutofillData = 101;
    
    static final int TRANSACTION_requestPictureInPictureMode = 115;
    
    static final int TRANSACTION_requestStartActivityPermissionToken = 78;
    
    static final int TRANSACTION_resizeDockedStack = 118;
    
    static final int TRANSACTION_resizeTask = 85;
    
    static final int TRANSACTION_restartActivityProcessIfVisible = 154;
    
    static final int TRANSACTION_resumeAppSwitches = 145;
    
    static final int TRANSACTION_setActivityController = 146;
    
    static final int TRANSACTION_setDisablePreviewScreenshots = 129;
    
    static final int TRANSACTION_setDisplayToSingleTaskInstance = 153;
    
    static final int TRANSACTION_setFocusedStack = 57;
    
    static final int TRANSACTION_setFocusedTask = 29;
    
    static final int TRANSACTION_setFrontActivityScreenCompatMode = 26;
    
    static final int TRANSACTION_setImmersive = 49;
    
    static final int TRANSACTION_setInheritShowWhenLocked = 135;
    
    static final int TRANSACTION_setLockScreenShown = 97;
    
    static final int TRANSACTION_setPackageAskScreenCompat = 151;
    
    static final int TRANSACTION_setPackageScreenCompatMode = 149;
    
    static final int TRANSACTION_setPersistentVrThread = 143;
    
    static final int TRANSACTION_setPictureInPictureParams = 114;
    
    static final int TRANSACTION_setRequestedOrientation = 41;
    
    static final int TRANSACTION_setShowWhenLocked = 134;
    
    static final int TRANSACTION_setSplitScreenResizing = 120;
    
    static final int TRANSACTION_setTaskDescription = 66;
    
    static final int TRANSACTION_setTaskResizeable = 83;
    
    static final int TRANSACTION_setTaskWindowingMode = 88;
    
    static final int TRANSACTION_setTaskWindowingModeSplitScreenPrimary = 90;
    
    static final int TRANSACTION_setTurnScreenOn = 136;
    
    static final int TRANSACTION_setVoiceKeepAwake = 147;
    
    static final int TRANSACTION_setVrMode = 121;
    
    static final int TRANSACTION_setVrThread = 142;
    
    static final int TRANSACTION_shouldUpRecreateTask = 34;
    
    static final int TRANSACTION_showAssistFromActivity = 103;
    
    static final int TRANSACTION_showLockTaskEscapeMessage = 105;
    
    static final int TRANSACTION_startActivities = 2;
    
    static final int TRANSACTION_startActivity = 1;
    
    static final int TRANSACTION_startActivityAndWait = 7;
    
    static final int TRANSACTION_startActivityAsCaller = 13;
    
    static final int TRANSACTION_startActivityAsUser = 3;
    
    static final int TRANSACTION_startActivityFromRecents = 12;
    
    static final int TRANSACTION_startActivityIntentSender = 6;
    
    static final int TRANSACTION_startActivityWithConfig = 8;
    
    static final int TRANSACTION_startAssistantActivity = 10;
    
    static final int TRANSACTION_startDreamActivity = 5;
    
    static final int TRANSACTION_startLocalVoiceInteraction = 122;
    
    static final int TRANSACTION_startLockTaskModeByToken = 61;
    
    static final int TRANSACTION_startNextMatchingActivity = 4;
    
    static final int TRANSACTION_startRecentsActivity = 11;
    
    static final int TRANSACTION_startSystemLockTaskMode = 69;
    
    static final int TRANSACTION_startVoiceActivity = 9;
    
    static final int TRANSACTION_stopAppSwitches = 144;
    
    static final int TRANSACTION_stopLocalVoiceInteraction = 123;
    
    static final int TRANSACTION_stopLockTaskModeByToken = 62;
    
    static final int TRANSACTION_stopSystemLockTaskMode = 70;
    
    static final int TRANSACTION_supportsLocalVoiceInteraction = 124;
    
    static final int TRANSACTION_suppressResizeConfigChanges = 111;
    
    static final int TRANSACTION_toggleFreeformWindowingMode = 84;
    
    static final int TRANSACTION_unhandledBack = 15;
    
    static final int TRANSACTION_unregisterRemoteAnimations = 138;
    
    static final int TRANSACTION_unregisterTaskStackListener = 82;
    
    static final int TRANSACTION_updateConfiguration = 132;
    
    static final int TRANSACTION_updateLockTaskFeatures = 133;
    
    static final int TRANSACTION_updateLockTaskPackages = 63;
    
    static final int TRANSACTION_willActivityBeVisible = 40;
    
    public Stub() {
      attachInterface(this, "android.app.IActivityTaskManager");
    }
    
    public static IActivityTaskManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IActivityTaskManager");
      return (iInterface != null && iInterface instanceof IActivityTaskManager) ? (IActivityTaskManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IActivityTaskManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 155:
          return "onBackPressedOnTaskRoot";
        case 154:
          return "restartActivityProcessIfVisible";
        case 153:
          return "setDisplayToSingleTaskInstance";
        case 152:
          return "clearLaunchParamsForPackages";
        case 151:
          return "setPackageAskScreenCompat";
        case 150:
          return "getPackageAskScreenCompat";
        case 149:
          return "setPackageScreenCompatMode";
        case 148:
          return "getPackageScreenCompatMode";
        case 147:
          return "setVoiceKeepAwake";
        case 146:
          return "setActivityController";
        case 145:
          return "resumeAppSwitches";
        case 144:
          return "stopAppSwitches";
        case 143:
          return "setPersistentVrThread";
        case 142:
          return "setVrThread";
        case 141:
          return "alwaysShowUnsupportedCompileSdkWarning";
        case 140:
          return "registerRemoteAnimationsForDisplay";
        case 139:
          return "registerRemoteAnimationForNextActivityStart";
        case 138:
          return "unregisterRemoteAnimations";
        case 137:
          return "registerRemoteAnimations";
        case 136:
          return "setTurnScreenOn";
        case 135:
          return "setInheritShowWhenLocked";
        case 134:
          return "setShowWhenLocked";
        case 133:
          return "updateLockTaskFeatures";
        case 132:
          return "updateConfiguration";
        case 131:
          return "getLastResumedActivityUserId";
        case 130:
          return "invalidateHomeTaskSnapshot";
        case 129:
          return "setDisablePreviewScreenshots";
        case 128:
          return "getTaskSnapshot";
        case 127:
          return "cancelTaskWindowTransition";
        case 126:
          return "dismissKeyguard";
        case 125:
          return "getDeviceConfigurationInfo";
        case 124:
          return "supportsLocalVoiceInteraction";
        case 123:
          return "stopLocalVoiceInteraction";
        case 122:
          return "startLocalVoiceInteraction";
        case 121:
          return "setVrMode";
        case 120:
          return "setSplitScreenResizing";
        case 119:
          return "getWindowOrganizerController";
        case 118:
          return "resizeDockedStack";
        case 117:
          return "getUriPermissionOwnerForActivity";
        case 116:
          return "getMaxNumPictureInPictureActions";
        case 115:
          return "requestPictureInPictureMode";
        case 114:
          return "setPictureInPictureParams";
        case 113:
          return "enterPictureInPictureMode";
        case 112:
          return "moveTopActivityToPinnedStack";
        case 111:
          return "suppressResizeConfigChanges";
        case 110:
          return "reportSizeConfigurations";
        case 109:
          return "positionTaskInStack";
        case 108:
          return "getPackageForToken";
        case 107:
          return "getActivityClassForToken";
        case 106:
          return "keyguardGoingAway";
        case 105:
          return "showLockTaskEscapeMessage";
        case 104:
          return "isRootVoiceInteraction";
        case 103:
          return "showAssistFromActivity";
        case 102:
          return "isAssistDataAllowedOnCurrentActivity";
        case 101:
          return "requestAutofillData";
        case 100:
          return "requestAssistContextExtras";
        case 99:
          return "launchAssistIntent";
        case 98:
          return "getAssistContextExtras";
        case 97:
          return "setLockScreenShown";
        case 96:
          return "getStackInfoOnDisplay";
        case 95:
          return "getAllStackInfosOnDisplay";
        case 94:
          return "getStackInfo";
        case 93:
          return "getAllStackInfos";
        case 92:
          return "removeStacksWithActivityTypes";
        case 91:
          return "removeStacksInWindowingModes";
        case 90:
          return "setTaskWindowingModeSplitScreenPrimary";
        case 89:
          return "moveTaskToStack";
        case 88:
          return "setTaskWindowingMode";
        case 87:
          return "removeStack";
        case 86:
          return "moveStackToDisplay";
        case 85:
          return "resizeTask";
        case 84:
          return "toggleFreeformWindowingMode";
        case 83:
          return "setTaskResizeable";
        case 82:
          return "unregisterTaskStackListener";
        case 81:
          return "registerTaskStackListener";
        case 80:
          return "getTaskDescriptionIcon";
        case 79:
          return "releaseSomeActivities";
        case 78:
          return "requestStartActivityPermissionToken";
        case 77:
          return "releaseActivityInstance";
        case 76:
          return "getAppTaskThumbnailSize";
        case 75:
          return "addAppTask";
        case 74:
          return "notifyEnterAnimationComplete";
        case 73:
          return "notifyLaunchTaskBehindComplete";
        case 72:
          return "isTopOfTask";
        case 71:
          return "finishVoiceTask";
        case 70:
          return "stopSystemLockTaskMode";
        case 69:
          return "startSystemLockTaskMode";
        case 68:
          return "getAppTasks";
        case 67:
          return "getActivityOptions";
        case 66:
          return "setTaskDescription";
        case 65:
          return "getLockTaskModeState";
        case 64:
          return "isInLockTaskMode";
        case 63:
          return "updateLockTaskPackages";
        case 62:
          return "stopLockTaskModeByToken";
        case 61:
          return "startLockTaskModeByToken";
        case 60:
          return "cancelRecentsAnimation";
        case 59:
          return "getTaskBounds";
        case 58:
          return "getFocusedStackInfo";
        case 57:
          return "setFocusedStack";
        case 56:
          return "reportAssistContextExtras";
        case 55:
          return "getLaunchedFromPackage";
        case 54:
          return "getLaunchedFromUid";
        case 53:
          return "overridePendingTransition";
        case 52:
          return "getTaskDescription";
        case 51:
          return "moveActivityTaskToBack";
        case 50:
          return "isTopActivityImmersive";
        case 49:
          return "setImmersive";
        case 48:
          return "isImmersive";
        case 47:
          return "getDisplayId";
        case 46:
          return "reportActivityFullyDrawn";
        case 45:
          return "notifyActivityDrawn";
        case 44:
          return "convertToTranslucent";
        case 43:
          return "convertFromTranslucent";
        case 42:
          return "getRequestedOrientation";
        case 41:
          return "setRequestedOrientation";
        case 40:
          return "willActivityBeVisible";
        case 39:
          return "getRecentTasks";
        case 38:
          return "finishSubActivity";
        case 37:
          return "getTaskForActivity";
        case 36:
          return "moveTaskToFront";
        case 35:
          return "navigateUpTo";
        case 34:
          return "shouldUpRecreateTask";
        case 33:
          return "getFilteredTasks";
        case 32:
          return "getTasks";
        case 31:
          return "removeAllVisibleRecentTasks";
        case 30:
          return "removeTask";
        case 29:
          return "setFocusedTask";
        case 28:
          return "getCallingActivity";
        case 27:
          return "getCallingPackage";
        case 26:
          return "setFrontActivityScreenCompatMode";
        case 25:
          return "getFrontActivityScreenCompatMode";
        case 24:
          return "activityRelaunched";
        case 23:
          return "activityDestroyed";
        case 22:
          return "activityStopped";
        case 21:
          return "activityPaused";
        case 20:
          return "activityTopResumedStateLost";
        case 19:
          return "activityResumed";
        case 18:
          return "activityIdle";
        case 17:
          return "finishActivityAffinity";
        case 16:
          return "finishActivity";
        case 15:
          return "unhandledBack";
        case 14:
          return "isActivityStartAllowedOnDisplay";
        case 13:
          return "startActivityAsCaller";
        case 12:
          return "startActivityFromRecents";
        case 11:
          return "startRecentsActivity";
        case 10:
          return "startAssistantActivity";
        case 9:
          return "startVoiceActivity";
        case 8:
          return "startActivityWithConfig";
        case 7:
          return "startActivityAndWait";
        case 6:
          return "startActivityIntentSender";
        case 5:
          return "startDreamActivity";
        case 4:
          return "startNextMatchingActivity";
        case 3:
          return "startActivityAsUser";
        case 2:
          return "startActivities";
        case 1:
          break;
      } 
      return "startActivity";
    }
    
    public static boolean setDefaultImpl(IActivityTaskManager param1IActivityTaskManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IActivityTaskManager != null) {
          Proxy.sDefaultImpl = param1IActivityTaskManager;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        boolean bool21;
        int i16;
        boolean bool20;
        int i15;
        boolean bool19;
        int i14;
        boolean bool18;
        int i13;
        boolean bool17;
        int i12;
        boolean bool16;
        int i11;
        boolean bool15;
        int i10;
        boolean bool14;
        int i9;
        boolean bool13;
        int i8;
        boolean bool12;
        int i7;
        boolean bool11;
        int i6;
        boolean bool10;
        int i5;
        boolean bool9;
        int i4;
        boolean bool8;
        int i3;
        boolean bool7;
        int i2;
        boolean bool6;
        int i1;
        boolean bool5;
        int n;
        boolean bool4;
        int m;
        boolean bool3;
        int k;
        boolean bool2;
        int j;
        boolean bool1;
        ActivityManager.TaskSnapshot taskSnapshot;
        ConfigurationInfo configurationInfo;
        IWindowOrganizerController iWindowOrganizerController;
        IBinder iBinder2;
        String str3;
        ComponentName componentName2;
        Bundle bundle2;
        ActivityManager.StackInfo stackInfo3;
        List<ActivityManager.StackInfo> list3;
        ActivityManager.StackInfo stackInfo2;
        List<ActivityManager.StackInfo> list2;
        Bitmap bitmap;
        IBinder iBinder1;
        Point point;
        List<IBinder> list1;
        Bundle bundle1;
        Rect rect1;
        ActivityManager.StackInfo stackInfo1;
        String str2;
        ActivityManager.TaskDescription taskDescription;
        ParceledListSlice parceledListSlice;
        List<ActivityManager.RunningTaskInfo> list;
        ComponentName componentName1;
        String str1;
        WaitResult waitResult;
        String str6;
        IVoiceInteractionSession iVoiceInteractionSession1;
        IActivityController iActivityController;
        String str5;
        IBinder iBinder5;
        IKeyguardDismissCallback iKeyguardDismissCallback;
        IBinder iBinder4;
        String str4;
        IBinder iBinder3;
        IBinder iBinder8;
        IAssistDataReceiver iAssistDataReceiver;
        String str8;
        IApplicationThread iApplicationThread1;
        IBinder iBinder7;
        IIntentSender iIntentSender;
        IBinder iBinder6;
        String str7;
        Rect rect2;
        IBinder iBinder9;
        String str10;
        IApplicationThread iApplicationThread2;
        Intent[] arrayOfIntent;
        Rect rect3;
        IBinder iBinder12;
        IApplicationThread iApplicationThread3;
        String str13;
        IBinder iBinder11;
        String str12;
        IBinder iBinder10;
        String str15;
        IApplicationThread iApplicationThread5;
        String str14;
        String str18;
        IVoiceInteractionSession iVoiceInteractionSession2;
        String str17;
        IApplicationThread iApplicationThread6;
        IBinder iBinder14;
        IVoiceInteractor iVoiceInteractor;
        String str19;
        String str22;
        IBinder iBinder15;
        String str21;
        String[] arrayOfString;
        IBinder iBinder17;
        String str23;
        IBinder iBinder16;
        int i17;
        boolean bool22 = false;
        boolean bool23 = false;
        boolean bool24 = false;
        boolean bool25 = false;
        boolean bool26 = false;
        boolean bool27 = false;
        boolean bool28 = false;
        boolean bool29 = false;
        boolean bool30 = false;
        boolean bool31 = false;
        boolean bool32 = false;
        boolean bool33 = false;
        boolean bool34 = false;
        boolean bool35 = false;
        boolean bool36 = false;
        boolean bool37 = false;
        boolean bool38 = false;
        boolean bool39 = false;
        boolean bool40 = false;
        boolean bool41 = false;
        boolean bool42 = false;
        boolean bool43 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 155:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            onBackPressedOnTaskRoot(param1Parcel1.readStrongBinder(), IRequestFinishCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 154:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            restartActivityProcessIfVisible(param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 153:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            setDisplayToSingleTaskInstance(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 152:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            clearLaunchParamsForPackages(param1Parcel1.createStringArrayList());
            param1Parcel2.writeNoException();
            return true;
          case 151:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            str6 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0)
              bool43 = true; 
            setPackageAskScreenCompat(str6, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 150:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            bool21 = getPackageAskScreenCompat(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool21);
            return true;
          case 149:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            setPackageScreenCompatMode(param1Parcel1.readString(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 148:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            i16 = getPackageScreenCompatMode(param1Parcel1.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i16);
            return true;
          case 147:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            iVoiceInteractionSession1 = IVoiceInteractionSession.Stub.asInterface(param1Parcel1.readStrongBinder());
            bool43 = bool22;
            if (param1Parcel1.readInt() != 0)
              bool43 = true; 
            setVoiceKeepAwake(iVoiceInteractionSession1, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 146:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            iActivityController = IActivityController.Stub.asInterface(param1Parcel1.readStrongBinder());
            bool43 = bool23;
            if (param1Parcel1.readInt() != 0)
              bool43 = true; 
            setActivityController(iActivityController, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 145:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            resumeAppSwitches();
            param1Parcel2.writeNoException();
            return true;
          case 144:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            stopAppSwitches();
            param1Parcel2.writeNoException();
            return true;
          case 143:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            setPersistentVrThread(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 142:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            setVrThread(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 141:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            if (param1Parcel1.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            alwaysShowUnsupportedCompileSdkWarning((ComponentName)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 140:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            i16 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              RemoteAnimationDefinition remoteAnimationDefinition = (RemoteAnimationDefinition)RemoteAnimationDefinition.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            registerRemoteAnimationsForDisplay(i16, (RemoteAnimationDefinition)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 139:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            str5 = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              RemoteAnimationAdapter remoteAnimationAdapter = (RemoteAnimationAdapter)RemoteAnimationAdapter.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            registerRemoteAnimationForNextActivityStart(str5, (RemoteAnimationAdapter)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 138:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            unregisterRemoteAnimations(param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 137:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            iBinder5 = param1Parcel1.readStrongBinder();
            if (param1Parcel1.readInt() != 0) {
              RemoteAnimationDefinition remoteAnimationDefinition = (RemoteAnimationDefinition)RemoteAnimationDefinition.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            registerRemoteAnimations(iBinder5, (RemoteAnimationDefinition)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 136:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            iBinder5 = param1Parcel1.readStrongBinder();
            bool43 = bool24;
            if (param1Parcel1.readInt() != 0)
              bool43 = true; 
            setTurnScreenOn(iBinder5, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 135:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            iBinder5 = param1Parcel1.readStrongBinder();
            bool43 = bool25;
            if (param1Parcel1.readInt() != 0)
              bool43 = true; 
            setInheritShowWhenLocked(iBinder5, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 134:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            iBinder5 = param1Parcel1.readStrongBinder();
            bool43 = bool26;
            if (param1Parcel1.readInt() != 0)
              bool43 = true; 
            setShowWhenLocked(iBinder5, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 133:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            updateLockTaskFeatures(param1Parcel1.readInt(), param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 132:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            if (param1Parcel1.readInt() != 0) {
              Configuration configuration = (Configuration)Configuration.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            bool20 = updateConfiguration((Configuration)param1Parcel1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool20);
            return true;
          case 131:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            i15 = getLastResumedActivityUserId();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i15);
            return true;
          case 130:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            invalidateHomeTaskSnapshot(param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 129:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            iBinder5 = param1Parcel1.readStrongBinder();
            bool43 = bool27;
            if (param1Parcel1.readInt() != 0)
              bool43 = true; 
            setDisablePreviewScreenshots(iBinder5, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 128:
            param1Parcel1.enforceInterface("android.app.IActivityTaskManager");
            i15 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              bool43 = true;
            } else {
              bool43 = false;
            } 
            taskSnapshot = getTaskSnapshot(i15, bool43);
            param1Parcel2.writeNoException();
            if (taskSnapshot != null) {
              param1Parcel2.writeInt(1);
              taskSnapshot.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 127:
            taskSnapshot.enforceInterface("android.app.IActivityTaskManager");
            cancelTaskWindowTransition(taskSnapshot.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 126:
            taskSnapshot.enforceInterface("android.app.IActivityTaskManager");
            iBinder8 = taskSnapshot.readStrongBinder();
            iKeyguardDismissCallback = IKeyguardDismissCallback.Stub.asInterface(taskSnapshot.readStrongBinder());
            if (taskSnapshot.readInt() != 0) {
              CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)taskSnapshot);
            } else {
              taskSnapshot = null;
            } 
            dismissKeyguard(iBinder8, iKeyguardDismissCallback, (CharSequence)taskSnapshot);
            param1Parcel2.writeNoException();
            return true;
          case 125:
            taskSnapshot.enforceInterface("android.app.IActivityTaskManager");
            configurationInfo = getDeviceConfigurationInfo();
            param1Parcel2.writeNoException();
            if (configurationInfo != null) {
              param1Parcel2.writeInt(1);
              configurationInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 124:
            configurationInfo.enforceInterface("android.app.IActivityTaskManager");
            bool19 = supportsLocalVoiceInteraction();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool19);
            return true;
          case 123:
            configurationInfo.enforceInterface("android.app.IActivityTaskManager");
            stopLocalVoiceInteraction(configurationInfo.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 122:
            configurationInfo.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = configurationInfo.readStrongBinder();
            if (configurationInfo.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)configurationInfo);
            } else {
              configurationInfo = null;
            } 
            startLocalVoiceInteraction(iBinder4, (Bundle)configurationInfo);
            param1Parcel2.writeNoException();
            return true;
          case 121:
            configurationInfo.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = configurationInfo.readStrongBinder();
            bool43 = bool28;
            if (configurationInfo.readInt() != 0)
              bool43 = true; 
            if (configurationInfo.readInt() != 0) {
              ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel((Parcel)configurationInfo);
            } else {
              configurationInfo = null;
            } 
            i14 = setVrMode(iBinder4, bool43, (ComponentName)configurationInfo);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i14);
            return true;
          case 120:
            configurationInfo.enforceInterface("android.app.IActivityTaskManager");
            bool43 = bool29;
            if (configurationInfo.readInt() != 0)
              bool43 = true; 
            setSplitScreenResizing(bool43);
            param1Parcel2.writeNoException();
            return true;
          case 119:
            configurationInfo.enforceInterface("android.app.IActivityTaskManager");
            iWindowOrganizerController = getWindowOrganizerController();
            param1Parcel2.writeNoException();
            if (iWindowOrganizerController != null) {
              IBinder iBinder = iWindowOrganizerController.asBinder();
            } else {
              iWindowOrganizerController = null;
            } 
            param1Parcel2.writeStrongBinder((IBinder)iWindowOrganizerController);
            return true;
          case 118:
            iWindowOrganizerController.enforceInterface("android.app.IActivityTaskManager");
            if (iWindowOrganizerController.readInt() != 0) {
              Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)iWindowOrganizerController);
            } else {
              iBinder4 = null;
            } 
            if (iWindowOrganizerController.readInt() != 0) {
              Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)iWindowOrganizerController);
            } else {
              iBinder8 = null;
            } 
            if (iWindowOrganizerController.readInt() != 0) {
              rect2 = (Rect)Rect.CREATOR.createFromParcel((Parcel)iWindowOrganizerController);
            } else {
              rect2 = null;
            } 
            if (iWindowOrganizerController.readInt() != 0) {
              rect3 = (Rect)Rect.CREATOR.createFromParcel((Parcel)iWindowOrganizerController);
            } else {
              rect3 = null;
            } 
            if (iWindowOrganizerController.readInt() != 0) {
              Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)iWindowOrganizerController);
            } else {
              iWindowOrganizerController = null;
            } 
            resizeDockedStack((Rect)iBinder4, (Rect)iBinder8, rect2, rect3, (Rect)iWindowOrganizerController);
            param1Parcel2.writeNoException();
            return true;
          case 117:
            iWindowOrganizerController.enforceInterface("android.app.IActivityTaskManager");
            iBinder2 = getUriPermissionOwnerForActivity(iWindowOrganizerController.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStrongBinder(iBinder2);
            return true;
          case 116:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            i14 = getMaxNumPictureInPictureActions(iBinder2.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i14);
            return true;
          case 115:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            requestPictureInPictureMode(iBinder2.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 114:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = iBinder2.readStrongBinder();
            if (iBinder2.readInt() != 0) {
              PictureInPictureParams pictureInPictureParams = (PictureInPictureParams)PictureInPictureParams.CREATOR.createFromParcel((Parcel)iBinder2);
            } else {
              iBinder2 = null;
            } 
            setPictureInPictureParams(iBinder4, (PictureInPictureParams)iBinder2);
            param1Parcel2.writeNoException();
            return true;
          case 113:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = iBinder2.readStrongBinder();
            if (iBinder2.readInt() != 0) {
              PictureInPictureParams pictureInPictureParams = (PictureInPictureParams)PictureInPictureParams.CREATOR.createFromParcel((Parcel)iBinder2);
            } else {
              iBinder2 = null;
            } 
            bool18 = enterPictureInPictureMode(iBinder4, (PictureInPictureParams)iBinder2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool18);
            return true;
          case 112:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            i13 = iBinder2.readInt();
            if (iBinder2.readInt() != 0) {
              Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)iBinder2);
            } else {
              iBinder2 = null;
            } 
            bool17 = moveTopActivityToPinnedStack(i13, (Rect)iBinder2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool17);
            return true;
          case 111:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            bool43 = bool30;
            if (iBinder2.readInt() != 0)
              bool43 = true; 
            suppressResizeConfigChanges(bool43);
            param1Parcel2.writeNoException();
            return true;
          case 110:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            reportSizeConfigurations(iBinder2.readStrongBinder(), iBinder2.createIntArray(), iBinder2.createIntArray(), iBinder2.createIntArray());
            param1Parcel2.writeNoException();
            return true;
          case 109:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            positionTaskInStack(iBinder2.readInt(), iBinder2.readInt(), iBinder2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 108:
            iBinder2.enforceInterface("android.app.IActivityTaskManager");
            str3 = getPackageForToken(iBinder2.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str3);
            return true;
          case 107:
            str3.enforceInterface("android.app.IActivityTaskManager");
            componentName2 = getActivityClassForToken(str3.readStrongBinder());
            param1Parcel2.writeNoException();
            if (componentName2 != null) {
              param1Parcel2.writeInt(1);
              componentName2.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 106:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            keyguardGoingAway(componentName2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 105:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            showLockTaskEscapeMessage(componentName2.readStrongBinder());
            return true;
          case 104:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            bool17 = isRootVoiceInteraction(componentName2.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool17);
            return true;
          case 103:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = componentName2.readStrongBinder();
            if (componentName2.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)componentName2);
            } else {
              componentName2 = null;
            } 
            bool17 = showAssistFromActivity(iBinder4, (Bundle)componentName2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool17);
            return true;
          case 102:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            bool17 = isAssistDataAllowedOnCurrentActivity();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool17);
            return true;
          case 101:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            iAssistDataReceiver = IAssistDataReceiver.Stub.asInterface(componentName2.readStrongBinder());
            if (componentName2.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)componentName2);
            } else {
              iBinder4 = null;
            } 
            bool17 = requestAutofillData(iAssistDataReceiver, (Bundle)iBinder4, componentName2.readStrongBinder(), componentName2.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool17);
            return true;
          case 100:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            i12 = componentName2.readInt();
            iAssistDataReceiver = IAssistDataReceiver.Stub.asInterface(componentName2.readStrongBinder());
            if (componentName2.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)componentName2);
            } else {
              iBinder4 = null;
            } 
            iBinder9 = componentName2.readStrongBinder();
            if (componentName2.readInt() != 0) {
              bool43 = true;
            } else {
              bool43 = false;
            } 
            if (componentName2.readInt() != 0) {
              bool31 = true;
            } else {
              bool31 = false;
            } 
            bool16 = requestAssistContextExtras(i12, iAssistDataReceiver, (Bundle)iBinder4, iBinder9, bool43, bool31);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool16);
            return true;
          case 99:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            if (componentName2.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)componentName2);
            } else {
              iBinder4 = null;
            } 
            i11 = componentName2.readInt();
            str8 = componentName2.readString();
            param1Int2 = componentName2.readInt();
            if (componentName2.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)componentName2);
            } else {
              componentName2 = null;
            } 
            bool15 = launchAssistIntent((Intent)iBinder4, i11, str8, param1Int2, (Bundle)componentName2);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool15);
            return true;
          case 98:
            componentName2.enforceInterface("android.app.IActivityTaskManager");
            bundle2 = getAssistContextExtras(componentName2.readInt());
            param1Parcel2.writeNoException();
            if (bundle2 != null) {
              param1Parcel2.writeInt(1);
              bundle2.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 97:
            bundle2.enforceInterface("android.app.IActivityTaskManager");
            if (bundle2.readInt() != 0) {
              bool43 = true;
            } else {
              bool43 = false;
            } 
            if (bundle2.readInt() != 0)
              bool31 = true; 
            setLockScreenShown(bool43, bool31);
            param1Parcel2.writeNoException();
            return true;
          case 96:
            bundle2.enforceInterface("android.app.IActivityTaskManager");
            stackInfo3 = getStackInfoOnDisplay(bundle2.readInt(), bundle2.readInt(), bundle2.readInt());
            param1Parcel2.writeNoException();
            if (stackInfo3 != null) {
              param1Parcel2.writeInt(1);
              stackInfo3.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 95:
            stackInfo3.enforceInterface("android.app.IActivityTaskManager");
            list3 = getAllStackInfosOnDisplay(stackInfo3.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list3);
            return true;
          case 94:
            list3.enforceInterface("android.app.IActivityTaskManager");
            stackInfo2 = getStackInfo(list3.readInt(), list3.readInt());
            param1Parcel2.writeNoException();
            if (stackInfo2 != null) {
              param1Parcel2.writeInt(1);
              stackInfo2.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 93:
            stackInfo2.enforceInterface("android.app.IActivityTaskManager");
            list2 = getAllStackInfos();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list2);
            return true;
          case 92:
            list2.enforceInterface("android.app.IActivityTaskManager");
            removeStacksWithActivityTypes(list2.createIntArray());
            param1Parcel2.writeNoException();
            return true;
          case 91:
            list2.enforceInterface("android.app.IActivityTaskManager");
            removeStacksInWindowingModes(list2.createIntArray());
            param1Parcel2.writeNoException();
            return true;
          case 90:
            list2.enforceInterface("android.app.IActivityTaskManager");
            i10 = list2.readInt();
            bool43 = bool32;
            if (list2.readInt() != 0)
              bool43 = true; 
            bool14 = setTaskWindowingModeSplitScreenPrimary(i10, bool43);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool14);
            return true;
          case 89:
            list2.enforceInterface("android.app.IActivityTaskManager");
            param1Int2 = list2.readInt();
            i9 = list2.readInt();
            bool43 = bool33;
            if (list2.readInt() != 0)
              bool43 = true; 
            moveTaskToStack(param1Int2, i9, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 88:
            list2.enforceInterface("android.app.IActivityTaskManager");
            i9 = list2.readInt();
            param1Int2 = list2.readInt();
            bool43 = bool34;
            if (list2.readInt() != 0)
              bool43 = true; 
            bool13 = setTaskWindowingMode(i9, param1Int2, bool43);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool13);
            return true;
          case 87:
            list2.enforceInterface("android.app.IActivityTaskManager");
            removeStack(list2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 86:
            list2.enforceInterface("android.app.IActivityTaskManager");
            moveStackToDisplay(list2.readInt(), list2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 85:
            list2.enforceInterface("android.app.IActivityTaskManager");
            i8 = list2.readInt();
            if (list2.readInt() != 0) {
              Rect rect = (Rect)Rect.CREATOR.createFromParcel((Parcel)list2);
            } else {
              iBinder4 = null;
            } 
            bool12 = resizeTask(i8, (Rect)iBinder4, list2.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool12);
            return true;
          case 84:
            list2.enforceInterface("android.app.IActivityTaskManager");
            toggleFreeformWindowingMode(list2.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 83:
            list2.enforceInterface("android.app.IActivityTaskManager");
            setTaskResizeable(list2.readInt(), list2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 82:
            list2.enforceInterface("android.app.IActivityTaskManager");
            unregisterTaskStackListener(ITaskStackListener.Stub.asInterface(list2.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 81:
            list2.enforceInterface("android.app.IActivityTaskManager");
            registerTaskStackListener(ITaskStackListener.Stub.asInterface(list2.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 80:
            list2.enforceInterface("android.app.IActivityTaskManager");
            bitmap = getTaskDescriptionIcon(list2.readString(), list2.readInt());
            param1Parcel2.writeNoException();
            if (bitmap != null) {
              param1Parcel2.writeInt(1);
              bitmap.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 79:
            bitmap.enforceInterface("android.app.IActivityTaskManager");
            releaseSomeActivities(IApplicationThread.Stub.asInterface(bitmap.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 78:
            bitmap.enforceInterface("android.app.IActivityTaskManager");
            iBinder1 = requestStartActivityPermissionToken(bitmap.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStrongBinder(iBinder1);
            return true;
          case 77:
            iBinder1.enforceInterface("android.app.IActivityTaskManager");
            bool12 = releaseActivityInstance(iBinder1.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool12);
            return true;
          case 76:
            iBinder1.enforceInterface("android.app.IActivityTaskManager");
            point = getAppTaskThumbnailSize();
            param1Parcel2.writeNoException();
            if (point != null) {
              param1Parcel2.writeInt(1);
              point.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 75:
            point.enforceInterface("android.app.IActivityTaskManager");
            iBinder9 = point.readStrongBinder();
            if (point.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)point);
            } else {
              iBinder4 = null;
            } 
            if (point.readInt() != 0) {
              ActivityManager.TaskDescription taskDescription1 = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel((Parcel)point);
            } else {
              str8 = null;
            } 
            if (point.readInt() != 0) {
              Bitmap bitmap1 = (Bitmap)Bitmap.CREATOR.createFromParcel((Parcel)point);
            } else {
              point = null;
            } 
            i7 = addAppTask(iBinder9, (Intent)iBinder4, (ActivityManager.TaskDescription)str8, (Bitmap)point);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i7);
            return true;
          case 74:
            point.enforceInterface("android.app.IActivityTaskManager");
            notifyEnterAnimationComplete(point.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 73:
            point.enforceInterface("android.app.IActivityTaskManager");
            notifyLaunchTaskBehindComplete(point.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 72:
            point.enforceInterface("android.app.IActivityTaskManager");
            bool11 = isTopOfTask(point.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool11);
            return true;
          case 71:
            point.enforceInterface("android.app.IActivityTaskManager");
            finishVoiceTask(IVoiceInteractionSession.Stub.asInterface(point.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 70:
            point.enforceInterface("android.app.IActivityTaskManager");
            stopSystemLockTaskMode();
            param1Parcel2.writeNoException();
            return true;
          case 69:
            point.enforceInterface("android.app.IActivityTaskManager");
            startSystemLockTaskMode(point.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 68:
            point.enforceInterface("android.app.IActivityTaskManager");
            list1 = getAppTasks(point.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeBinderList(list1);
            return true;
          case 67:
            list1.enforceInterface("android.app.IActivityTaskManager");
            bundle1 = getActivityOptions(list1.readStrongBinder());
            param1Parcel2.writeNoException();
            if (bundle1 != null) {
              param1Parcel2.writeInt(1);
              bundle1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 66:
            bundle1.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = bundle1.readStrongBinder();
            if (bundle1.readInt() != 0) {
              ActivityManager.TaskDescription taskDescription1 = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel((Parcel)bundle1);
            } else {
              bundle1 = null;
            } 
            setTaskDescription(iBinder4, (ActivityManager.TaskDescription)bundle1);
            param1Parcel2.writeNoException();
            return true;
          case 65:
            bundle1.enforceInterface("android.app.IActivityTaskManager");
            i6 = getLockTaskModeState();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i6);
            return true;
          case 64:
            bundle1.enforceInterface("android.app.IActivityTaskManager");
            bool10 = isInLockTaskMode();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool10);
            return true;
          case 63:
            bundle1.enforceInterface("android.app.IActivityTaskManager");
            updateLockTaskPackages(bundle1.readInt(), bundle1.createStringArray());
            param1Parcel2.writeNoException();
            return true;
          case 62:
            bundle1.enforceInterface("android.app.IActivityTaskManager");
            stopLockTaskModeByToken(bundle1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 61:
            bundle1.enforceInterface("android.app.IActivityTaskManager");
            startLockTaskModeByToken(bundle1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 60:
            bundle1.enforceInterface("android.app.IActivityTaskManager");
            bool43 = bool35;
            if (bundle1.readInt() != 0)
              bool43 = true; 
            cancelRecentsAnimation(bool43);
            param1Parcel2.writeNoException();
            return true;
          case 59:
            bundle1.enforceInterface("android.app.IActivityTaskManager");
            rect1 = getTaskBounds(bundle1.readInt());
            param1Parcel2.writeNoException();
            if (rect1 != null) {
              param1Parcel2.writeInt(1);
              rect1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 58:
            rect1.enforceInterface("android.app.IActivityTaskManager");
            stackInfo1 = getFocusedStackInfo();
            param1Parcel2.writeNoException();
            if (stackInfo1 != null) {
              param1Parcel2.writeInt(1);
              stackInfo1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 57:
            stackInfo1.enforceInterface("android.app.IActivityTaskManager");
            setFocusedStack(stackInfo1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 56:
            stackInfo1.enforceInterface("android.app.IActivityTaskManager");
            iBinder12 = stackInfo1.readStrongBinder();
            if (stackInfo1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)stackInfo1);
            } else {
              iBinder4 = null;
            } 
            if (stackInfo1.readInt() != 0) {
              AssistStructure assistStructure = (AssistStructure)AssistStructure.CREATOR.createFromParcel((Parcel)stackInfo1);
            } else {
              str8 = null;
            } 
            if (stackInfo1.readInt() != 0) {
              AssistContent assistContent = (AssistContent)AssistContent.CREATOR.createFromParcel((Parcel)stackInfo1);
            } else {
              iBinder9 = null;
            } 
            if (stackInfo1.readInt() != 0) {
              Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)stackInfo1);
            } else {
              stackInfo1 = null;
            } 
            reportAssistContextExtras(iBinder12, (Bundle)iBinder4, (AssistStructure)str8, (AssistContent)iBinder9, (Uri)stackInfo1);
            param1Parcel2.writeNoException();
            return true;
          case 55:
            stackInfo1.enforceInterface("android.app.IActivityTaskManager");
            str2 = getLaunchedFromPackage(stackInfo1.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str2);
            return true;
          case 54:
            str2.enforceInterface("android.app.IActivityTaskManager");
            i5 = getLaunchedFromUid(str2.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i5);
            return true;
          case 53:
            str2.enforceInterface("android.app.IActivityTaskManager");
            overridePendingTransition(str2.readStrongBinder(), str2.readString(), str2.readInt(), str2.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 52:
            str2.enforceInterface("android.app.IActivityTaskManager");
            taskDescription = getTaskDescription(str2.readInt());
            param1Parcel2.writeNoException();
            if (taskDescription != null) {
              param1Parcel2.writeInt(1);
              taskDescription.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 51:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = taskDescription.readStrongBinder();
            bool43 = bool36;
            if (taskDescription.readInt() != 0)
              bool43 = true; 
            bool9 = moveActivityTaskToBack(iBinder4, bool43);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool9);
            return true;
          case 50:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            bool9 = isTopActivityImmersive();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool9);
            return true;
          case 49:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = taskDescription.readStrongBinder();
            bool43 = bool37;
            if (taskDescription.readInt() != 0)
              bool43 = true; 
            setImmersive(iBinder4, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 48:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            bool9 = isImmersive(taskDescription.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool9);
            return true;
          case 47:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            i4 = getDisplayId(taskDescription.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i4);
            return true;
          case 46:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = taskDescription.readStrongBinder();
            bool43 = bool38;
            if (taskDescription.readInt() != 0)
              bool43 = true; 
            reportActivityFullyDrawn(iBinder4, bool43);
            param1Parcel2.writeNoException();
            return true;
          case 45:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            notifyActivityDrawn(taskDescription.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 44:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = taskDescription.readStrongBinder();
            if (taskDescription.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)taskDescription);
            } else {
              taskDescription = null;
            } 
            bool8 = convertToTranslucent(iBinder4, (Bundle)taskDescription);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool8);
            return true;
          case 43:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            bool8 = convertFromTranslucent(taskDescription.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool8);
            return true;
          case 42:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            i3 = getRequestedOrientation(taskDescription.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i3);
            return true;
          case 41:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            setRequestedOrientation(taskDescription.readStrongBinder(), taskDescription.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 40:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            bool7 = willActivityBeVisible(taskDescription.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool7);
            return true;
          case 39:
            taskDescription.enforceInterface("android.app.IActivityTaskManager");
            parceledListSlice = getRecentTasks(taskDescription.readInt(), taskDescription.readInt(), taskDescription.readInt());
            param1Parcel2.writeNoException();
            if (parceledListSlice != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 38:
            parceledListSlice.enforceInterface("android.app.IActivityTaskManager");
            finishSubActivity(parceledListSlice.readStrongBinder(), parceledListSlice.readString(), parceledListSlice.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 37:
            parceledListSlice.enforceInterface("android.app.IActivityTaskManager");
            iBinder4 = parceledListSlice.readStrongBinder();
            bool43 = bool39;
            if (parceledListSlice.readInt() != 0)
              bool43 = true; 
            i2 = getTaskForActivity(iBinder4, bool43);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i2);
            return true;
          case 36:
            parceledListSlice.enforceInterface("android.app.IActivityTaskManager");
            iApplicationThread1 = IApplicationThread.Stub.asInterface(parceledListSlice.readStrongBinder());
            str4 = parceledListSlice.readString();
            i2 = parceledListSlice.readInt();
            param1Int2 = parceledListSlice.readInt();
            if (parceledListSlice.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              parceledListSlice = null;
            } 
            moveTaskToFront(iApplicationThread1, str4, i2, param1Int2, (Bundle)parceledListSlice);
            param1Parcel2.writeNoException();
            return true;
          case 35:
            parceledListSlice.enforceInterface("android.app.IActivityTaskManager");
            iBinder7 = parceledListSlice.readStrongBinder();
            if (parceledListSlice.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              str4 = null;
            } 
            i2 = parceledListSlice.readInt();
            if (parceledListSlice.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)parceledListSlice);
            } else {
              parceledListSlice = null;
            } 
            bool6 = navigateUpTo(iBinder7, (Intent)str4, i2, (Intent)parceledListSlice);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool6);
            return true;
          case 34:
            parceledListSlice.enforceInterface("android.app.IActivityTaskManager");
            bool6 = shouldUpRecreateTask(parceledListSlice.readStrongBinder(), parceledListSlice.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool6);
            return true;
          case 33:
            parceledListSlice.enforceInterface("android.app.IActivityTaskManager");
            i1 = parceledListSlice.readInt();
            bool43 = bool40;
            if (parceledListSlice.readInt() != 0)
              bool43 = true; 
            list = getFilteredTasks(i1, bool43);
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 32:
            list.enforceInterface("android.app.IActivityTaskManager");
            list = getTasks(list.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 31:
            list.enforceInterface("android.app.IActivityTaskManager");
            removeAllVisibleRecentTasks();
            param1Parcel2.writeNoException();
            return true;
          case 30:
            list.enforceInterface("android.app.IActivityTaskManager");
            bool5 = removeTask(list.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool5);
            return true;
          case 29:
            list.enforceInterface("android.app.IActivityTaskManager");
            setFocusedTask(list.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 28:
            list.enforceInterface("android.app.IActivityTaskManager");
            componentName1 = getCallingActivity(list.readStrongBinder());
            param1Parcel2.writeNoException();
            if (componentName1 != null) {
              param1Parcel2.writeInt(1);
              componentName1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 27:
            componentName1.enforceInterface("android.app.IActivityTaskManager");
            str1 = getCallingPackage(componentName1.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeString(str1);
            return true;
          case 26:
            str1.enforceInterface("android.app.IActivityTaskManager");
            setFrontActivityScreenCompatMode(str1.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 25:
            str1.enforceInterface("android.app.IActivityTaskManager");
            n = getFrontActivityScreenCompatMode();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(n);
            return true;
          case 24:
            str1.enforceInterface("android.app.IActivityTaskManager");
            activityRelaunched(str1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 23:
            str1.enforceInterface("android.app.IActivityTaskManager");
            activityDestroyed(str1.readStrongBinder());
            return true;
          case 22:
            str1.enforceInterface("android.app.IActivityTaskManager");
            iBinder9 = str1.readStrongBinder();
            if (str1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str1);
            } else {
              str4 = null;
            } 
            if (str1.readInt() != 0) {
              PersistableBundle persistableBundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder7 = null;
            } 
            if (str1.readInt() != 0) {
              CharSequence charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel((Parcel)str1);
            } else {
              str1 = null;
            } 
            activityStopped(iBinder9, (Bundle)str4, (PersistableBundle)iBinder7, str1);
            param1Parcel2.writeNoException();
            return true;
          case 21:
            str1.enforceInterface("android.app.IActivityTaskManager");
            activityPaused(str1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 20:
            str1.enforceInterface("android.app.IActivityTaskManager");
            activityTopResumedStateLost();
            param1Parcel2.writeNoException();
            return true;
          case 19:
            str1.enforceInterface("android.app.IActivityTaskManager");
            activityResumed(str1.readStrongBinder());
            param1Parcel2.writeNoException();
            return true;
          case 18:
            str1.enforceInterface("android.app.IActivityTaskManager");
            iBinder3 = str1.readStrongBinder();
            if (str1.readInt() != 0) {
              Configuration configuration = (Configuration)Configuration.CREATOR.createFromParcel((Parcel)str1);
            } else {
              param1Parcel2 = null;
            } 
            bool43 = bool41;
            if (str1.readInt() != 0)
              bool43 = true; 
            activityIdle(iBinder3, (Configuration)param1Parcel2, bool43);
            return true;
          case 17:
            str1.enforceInterface("android.app.IActivityTaskManager");
            bool4 = finishActivityAffinity(str1.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool4);
            return true;
          case 16:
            str1.enforceInterface("android.app.IActivityTaskManager");
            iBinder7 = str1.readStrongBinder();
            m = str1.readInt();
            if (str1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder3 = null;
            } 
            bool3 = finishActivity(iBinder7, m, (Intent)iBinder3, str1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 15:
            str1.enforceInterface("android.app.IActivityTaskManager");
            unhandledBack();
            param1Parcel2.writeNoException();
            return true;
          case 14:
            str1.enforceInterface("android.app.IActivityTaskManager");
            k = str1.readInt();
            if (str1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder3 = null;
            } 
            bool2 = isActivityStartAllowedOnDisplay(k, (Intent)iBinder3, str1.readString(), str1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 13:
            str1.enforceInterface("android.app.IActivityTaskManager");
            iApplicationThread3 = IApplicationThread.Stub.asInterface(str1.readStrongBinder());
            str15 = str1.readString();
            if (str1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder3 = null;
            } 
            str18 = str1.readString();
            iBinder14 = str1.readStrongBinder();
            str22 = str1.readString();
            param1Int2 = str1.readInt();
            j = str1.readInt();
            if (str1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder7 = null;
            } 
            if (str1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder9 = null;
            } 
            iBinder17 = str1.readStrongBinder();
            bool43 = bool42;
            if (str1.readInt() != 0)
              bool43 = true; 
            j = startActivityAsCaller(iApplicationThread3, str15, (Intent)iBinder3, str18, iBinder14, str22, param1Int2, j, (ProfilerInfo)iBinder7, (Bundle)iBinder9, iBinder17, bool43, str1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 12:
            str1.enforceInterface("android.app.IActivityTaskManager");
            j = str1.readInt();
            if (str1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str1);
            } else {
              str1 = null;
            } 
            j = startActivityFromRecents(j, (Bundle)str1);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 11:
            str1.enforceInterface("android.app.IActivityTaskManager");
            if (str1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder3 = null;
            } 
            startRecentsActivity((Intent)iBinder3, IAssistDataReceiver.Stub.asInterface(str1.readStrongBinder()), IRecentsAnimationRunner.Stub.asInterface(str1.readStrongBinder()));
            param1Parcel2.writeNoException();
            return true;
          case 10:
            str1.enforceInterface("android.app.IActivityTaskManager");
            str13 = str1.readString();
            str10 = str1.readString();
            param1Int2 = str1.readInt();
            j = str1.readInt();
            if (str1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder3 = null;
            } 
            str15 = str1.readString();
            if (str1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder7 = null;
            } 
            j = startAssistantActivity(str13, str10, param1Int2, j, (Intent)iBinder3, str15, (Bundle)iBinder7, str1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 9:
            str1.enforceInterface("android.app.IActivityTaskManager");
            str15 = str1.readString();
            str13 = str1.readString();
            j = str1.readInt();
            param1Int2 = str1.readInt();
            if (str1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder3 = null;
            } 
            str22 = str1.readString();
            iVoiceInteractionSession2 = IVoiceInteractionSession.Stub.asInterface(str1.readStrongBinder());
            iVoiceInteractor = IVoiceInteractor.Stub.asInterface(str1.readStrongBinder());
            i17 = str1.readInt();
            if (str1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder7 = null;
            } 
            if (str1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str1);
            } else {
              str10 = null;
            } 
            j = startVoiceActivity(str15, str13, j, param1Int2, (Intent)iBinder3, str22, iVoiceInteractionSession2, iVoiceInteractor, i17, (ProfilerInfo)iBinder7, (Bundle)str10, str1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 8:
            str1.enforceInterface("android.app.IActivityTaskManager");
            iApplicationThread5 = IApplicationThread.Stub.asInterface(str1.readStrongBinder());
            str17 = str1.readString();
            str13 = str1.readString();
            if (str1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder3 = null;
            } 
            str19 = str1.readString();
            iBinder17 = str1.readStrongBinder();
            str22 = str1.readString();
            j = str1.readInt();
            param1Int2 = str1.readInt();
            if (str1.readInt() != 0) {
              Configuration configuration = (Configuration)Configuration.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder7 = null;
            } 
            if (str1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str1);
            } else {
              str10 = null;
            } 
            j = startActivityWithConfig(iApplicationThread5, str17, str13, (Intent)iBinder3, str19, iBinder17, str22, j, param1Int2, (Configuration)iBinder7, (Bundle)str10, str1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 7:
            str1.enforceInterface("android.app.IActivityTaskManager");
            iApplicationThread5 = IApplicationThread.Stub.asInterface(str1.readStrongBinder());
            str13 = str1.readString();
            str17 = str1.readString();
            if (str1.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder3 = null;
            } 
            str19 = str1.readString();
            iBinder15 = str1.readStrongBinder();
            str23 = str1.readString();
            param1Int2 = str1.readInt();
            j = str1.readInt();
            if (str1.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)str1);
            } else {
              iBinder7 = null;
            } 
            if (str1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)str1);
            } else {
              str10 = null;
            } 
            waitResult = startActivityAndWait(iApplicationThread5, str13, str17, (Intent)iBinder3, str19, iBinder15, str23, param1Int2, j, (ProfilerInfo)iBinder7, (Bundle)str10, str1.readInt());
            param1Parcel2.writeNoException();
            if (waitResult != null) {
              param1Parcel2.writeInt(1);
              waitResult.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 6:
            waitResult.enforceInterface("android.app.IActivityTaskManager");
            iApplicationThread2 = IApplicationThread.Stub.asInterface(waitResult.readStrongBinder());
            iIntentSender = IIntentSender.Stub.asInterface(waitResult.readStrongBinder());
            iBinder11 = waitResult.readStrongBinder();
            if (waitResult.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              iBinder3 = null;
            } 
            str14 = waitResult.readString();
            iBinder15 = waitResult.readStrongBinder();
            str17 = waitResult.readString();
            i17 = waitResult.readInt();
            j = waitResult.readInt();
            param1Int2 = waitResult.readInt();
            if (waitResult.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              waitResult = null;
            } 
            j = startActivityIntentSender(iApplicationThread2, iIntentSender, iBinder11, (Intent)iBinder3, str14, iBinder15, str17, i17, j, param1Int2, (Bundle)waitResult);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(j);
            return true;
          case 5:
            waitResult.enforceInterface("android.app.IActivityTaskManager");
            if (waitResult.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              waitResult = null;
            } 
            bool1 = startDreamActivity((Intent)waitResult);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 4:
            waitResult.enforceInterface("android.app.IActivityTaskManager");
            iBinder6 = waitResult.readStrongBinder();
            if (waitResult.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              iBinder3 = null;
            } 
            if (waitResult.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              waitResult = null;
            } 
            bool1 = startNextMatchingActivity(iBinder6, (Intent)iBinder3, (Bundle)waitResult);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 3:
            waitResult.enforceInterface("android.app.IActivityTaskManager");
            iApplicationThread6 = IApplicationThread.Stub.asInterface(waitResult.readStrongBinder());
            str12 = waitResult.readString();
            str14 = waitResult.readString();
            if (waitResult.readInt() != 0) {
              Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              iBinder3 = null;
            } 
            str19 = waitResult.readString();
            iBinder16 = waitResult.readStrongBinder();
            str21 = waitResult.readString();
            i = waitResult.readInt();
            param1Int2 = waitResult.readInt();
            if (waitResult.readInt() != 0) {
              ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              iBinder6 = null;
            } 
            if (waitResult.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              iApplicationThread2 = null;
            } 
            i = startActivityAsUser(iApplicationThread6, str12, str14, (Intent)iBinder3, str19, iBinder16, str21, i, param1Int2, (ProfilerInfo)iBinder6, (Bundle)iApplicationThread2, waitResult.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 2:
            waitResult.enforceInterface("android.app.IActivityTaskManager");
            iApplicationThread4 = IApplicationThread.Stub.asInterface(waitResult.readStrongBinder());
            str16 = waitResult.readString();
            str7 = waitResult.readString();
            arrayOfIntent = (Intent[])waitResult.createTypedArray(Intent.CREATOR);
            arrayOfString = waitResult.createStringArray();
            iBinder10 = waitResult.readStrongBinder();
            if (waitResult.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)waitResult);
            } else {
              iBinder3 = null;
            } 
            i = startActivities(iApplicationThread4, str16, str7, arrayOfIntent, arrayOfString, iBinder10, (Bundle)iBinder3, waitResult.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(i);
            return true;
          case 1:
            break;
        } 
        waitResult.enforceInterface("android.app.IActivityTaskManager");
        IApplicationThread iApplicationThread4 = IApplicationThread.Stub.asInterface(waitResult.readStrongBinder());
        String str9 = waitResult.readString();
        String str11 = waitResult.readString();
        if (waitResult.readInt() != 0) {
          Intent intent = (Intent)Intent.CREATOR.createFromParcel((Parcel)waitResult);
        } else {
          iBinder3 = null;
        } 
        String str20 = waitResult.readString();
        IBinder iBinder13 = waitResult.readStrongBinder();
        String str16 = waitResult.readString();
        param1Int2 = waitResult.readInt();
        int i = waitResult.readInt();
        if (waitResult.readInt() != 0) {
          ProfilerInfo profilerInfo = (ProfilerInfo)ProfilerInfo.CREATOR.createFromParcel((Parcel)waitResult);
        } else {
          str7 = null;
        } 
        if (waitResult.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)waitResult);
        } else {
          waitResult = null;
        } 
        i = startActivity(iApplicationThread4, str9, str11, (Intent)iBinder3, str20, iBinder13, str16, param1Int2, i, (ProfilerInfo)str7, (Bundle)waitResult);
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(i);
        return true;
      } 
      param1Parcel2.writeString("android.app.IActivityTaskManager");
      return true;
    }
    
    private static class Proxy implements IActivityTaskManager {
      public static IActivityTaskManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public void activityDestroyed(IBinder param2IBinder) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(23, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().activityDestroyed(param2IBinder);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void activityIdle(IBinder param2IBinder, Configuration param2Configuration, boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel.writeStrongBinder(param2IBinder);
          boolean bool = false;
          if (param2Configuration != null) {
            parcel.writeInt(1);
            param2Configuration.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2Boolean)
            bool = true; 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(18, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().activityIdle(param2IBinder, param2Configuration, param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void activityPaused(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().activityPaused(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void activityRelaunched(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().activityRelaunched(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void activityResumed(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().activityResumed(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void activityStopped(IBinder param2IBinder, Bundle param2Bundle, PersistableBundle param2PersistableBundle, CharSequence param2CharSequence) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2PersistableBundle != null) {
            parcel1.writeInt(1);
            param2PersistableBundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2CharSequence != null) {
            parcel1.writeInt(1);
            TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().activityStopped(param2IBinder, param2Bundle, param2PersistableBundle, param2CharSequence);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void activityTopResumedStateLost() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().activityTopResumedStateLost();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int addAppTask(IBinder param2IBinder, Intent param2Intent, ActivityManager.TaskDescription param2TaskDescription, Bitmap param2Bitmap) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2TaskDescription != null) {
            parcel1.writeInt(1);
            param2TaskDescription.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bitmap != null) {
            parcel1.writeInt(1);
            param2Bitmap.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().addAppTask(param2IBinder, param2Intent, param2TaskDescription, param2Bitmap); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void alwaysShowUnsupportedCompileSdkWarning(ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().alwaysShowUnsupportedCompileSdkWarning(param2ComponentName);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void cancelRecentsAnimation(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().cancelRecentsAnimation(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void cancelTaskWindowTransition(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(127, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().cancelTaskWindowTransition(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void clearLaunchParamsForPackages(List<String> param2List) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStringList(param2List);
          if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().clearLaunchParamsForPackages(param2List);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean convertFromTranslucent(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(43, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().convertFromTranslucent(param2IBinder);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean convertToTranslucent(IBinder param2IBinder, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          boolean bool = true;
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().convertToTranslucent(param2IBinder, param2Bundle);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void dismissKeyguard(IBinder param2IBinder, IKeyguardDismissCallback param2IKeyguardDismissCallback, CharSequence param2CharSequence) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2IKeyguardDismissCallback != null) {
            iBinder = param2IKeyguardDismissCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2CharSequence != null) {
            parcel1.writeInt(1);
            TextUtils.writeToParcel(param2CharSequence, parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().dismissKeyguard(param2IBinder, param2IKeyguardDismissCallback, param2CharSequence);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean enterPictureInPictureMode(IBinder param2IBinder, PictureInPictureParams param2PictureInPictureParams) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          boolean bool = true;
          if (param2PictureInPictureParams != null) {
            parcel1.writeInt(1);
            param2PictureInPictureParams.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().enterPictureInPictureMode(param2IBinder, param2PictureInPictureParams);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean finishActivity(IBinder param2IBinder, int param2Int1, Intent param2Intent, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int1);
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().finishActivity(param2IBinder, param2Int1, param2Intent, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean finishActivityAffinity(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(17, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().finishActivityAffinity(param2IBinder);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void finishSubActivity(IBinder param2IBinder, String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().finishSubActivity(param2IBinder, param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void finishVoiceTask(IVoiceInteractionSession param2IVoiceInteractionSession) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IVoiceInteractionSession != null) {
            iBinder = param2IVoiceInteractionSession.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().finishVoiceTask(param2IVoiceInteractionSession);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ComponentName getActivityClassForToken(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getActivityClassForToken(param2IBinder); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
          } else {
            param2IBinder = null;
          } 
          return (ComponentName)param2IBinder;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Bundle getActivityOptions(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getActivityOptions(param2IBinder); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
          } else {
            param2IBinder = null;
          } 
          return (Bundle)param2IBinder;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(93, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getAllStackInfos(); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.StackInfo> getAllStackInfosOnDisplay(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(95, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getAllStackInfosOnDisplay(param2Int); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Point getAppTaskThumbnailSize() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          Point point;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            point = IActivityTaskManager.Stub.getDefaultImpl().getAppTaskThumbnailSize();
            return point;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            point = (Point)Point.CREATOR.createFromParcel(parcel2);
          } else {
            point = null;
          } 
          return point;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<IBinder> getAppTasks(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getAppTasks(param2String); 
          parcel2.readException();
          return parcel2.createBinderArrayList();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Bundle getAssistContextExtras(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          Bundle bundle;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(98, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bundle = IActivityTaskManager.Stub.getDefaultImpl().getAssistContextExtras(param2Int);
            return bundle;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
          } else {
            bundle = null;
          } 
          return bundle;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ComponentName getCallingActivity(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getCallingActivity(param2IBinder); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
          } else {
            param2IBinder = null;
          } 
          return (ComponentName)param2IBinder;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getCallingPackage(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getCallingPackage(param2IBinder); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ConfigurationInfo getDeviceConfigurationInfo() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ConfigurationInfo configurationInfo;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            configurationInfo = IActivityTaskManager.Stub.getDefaultImpl().getDeviceConfigurationInfo();
            return configurationInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            configurationInfo = (ConfigurationInfo)ConfigurationInfo.CREATOR.createFromParcel(parcel2);
          } else {
            configurationInfo = null;
          } 
          return configurationInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getDisplayId(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getDisplayId(param2IBinder); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.RunningTaskInfo> getFilteredTasks(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getFilteredTasks(param2Int, param2Boolean); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ActivityManager.StackInfo stackInfo;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getFocusedStackInfo();
            return stackInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
          } else {
            stackInfo = null;
          } 
          return stackInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getFrontActivityScreenCompatMode() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getFrontActivityScreenCompatMode(); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IActivityTaskManager";
      }
      
      public int getLastResumedActivityUserId() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getLastResumedActivityUserId(); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getLaunchedFromPackage(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getLaunchedFromPackage(param2IBinder); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getLaunchedFromUid(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getLaunchedFromUid(param2IBinder); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getLockTaskModeState() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getLockTaskModeState(); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getMaxNumPictureInPictureActions(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(116, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getMaxNumPictureInPictureActions(param2IBinder); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean getPackageAskScreenCompat(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(150, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().getPackageAskScreenCompat(param2String);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getPackageForToken(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(108, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getPackageForToken(param2IBinder); 
          parcel2.readException();
          return parcel2.readString();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getPackageScreenCompatMode(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getPackageScreenCompatMode(param2String); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParceledListSlice getRecentTasks(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ParceledListSlice parceledListSlice;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            parceledListSlice = IActivityTaskManager.Stub.getDefaultImpl().getRecentTasks(param2Int1, param2Int2, param2Int3);
            return parceledListSlice;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
          } else {
            parceledListSlice = null;
          } 
          return parceledListSlice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getRequestedOrientation(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getRequestedOrientation(param2IBinder); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ActivityManager.StackInfo getStackInfo(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ActivityManager.StackInfo stackInfo;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(94, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getStackInfo(param2Int1, param2Int2);
            return stackInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
          } else {
            stackInfo = null;
          } 
          return stackInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ActivityManager.StackInfo getStackInfoOnDisplay(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ActivityManager.StackInfo stackInfo;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(96, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getStackInfoOnDisplay(param2Int1, param2Int2, param2Int3);
            return stackInfo;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
          } else {
            stackInfo = null;
          } 
          return stackInfo;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Rect getTaskBounds(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          Rect rect;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            rect = IActivityTaskManager.Stub.getDefaultImpl().getTaskBounds(param2Int);
            return rect;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            rect = (Rect)Rect.CREATOR.createFromParcel(parcel2);
          } else {
            rect = null;
          } 
          return rect;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ActivityManager.TaskDescription getTaskDescription(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          ActivityManager.TaskDescription taskDescription;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            taskDescription = IActivityTaskManager.Stub.getDefaultImpl().getTaskDescription(param2Int);
            return taskDescription;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            taskDescription = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel(parcel2);
          } else {
            taskDescription = null;
          } 
          return taskDescription;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Bitmap getTaskDescriptionIcon(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getTaskDescriptionIcon(param2String, param2Int); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (Bitmap)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int getTaskForActivity(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            i = IActivityTaskManager.Stub.getDefaultImpl().getTaskForActivity(param2IBinder, param2Boolean);
            return i;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          return i;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ActivityManager.TaskSnapshot getTaskSnapshot(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          ActivityManager.TaskSnapshot taskSnapshot;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(128, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            taskSnapshot = IActivityTaskManager.Stub.getDefaultImpl().getTaskSnapshot(param2Int, param2Boolean);
            return taskSnapshot;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            taskSnapshot = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcel2);
          } else {
            taskSnapshot = null;
          } 
          return taskSnapshot;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public List<ActivityManager.RunningTaskInfo> getTasks(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getTasks(param2Int); 
          parcel2.readException();
          return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder getUriPermissionOwnerForActivity(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(117, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            param2IBinder = IActivityTaskManager.Stub.getDefaultImpl().getUriPermissionOwnerForActivity(param2IBinder);
            return param2IBinder;
          } 
          parcel2.readException();
          param2IBinder = parcel2.readStrongBinder();
          return param2IBinder;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IWindowOrganizerController getWindowOrganizerController() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(119, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
            return IActivityTaskManager.Stub.getDefaultImpl().getWindowOrganizerController(); 
          parcel2.readException();
          return IWindowOrganizerController.Stub.asInterface(parcel2.readStrongBinder());
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void invalidateHomeTaskSnapshot(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(130, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().invalidateHomeTaskSnapshot(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isActivityStartAllowedOnDisplay(int param2Int1, Intent param2Intent, String param2String, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().isActivityStartAllowedOnDisplay(param2Int1, param2Intent, param2String, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isAssistDataAllowedOnCurrentActivity() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(102, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().isAssistDataAllowedOnCurrentActivity();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isImmersive(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(48, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().isImmersive(param2IBinder);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isInLockTaskMode() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(64, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().isInLockTaskMode();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isRootVoiceInteraction(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(104, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().isRootVoiceInteraction(param2IBinder);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isTopActivityImmersive() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(50, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().isTopActivityImmersive();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean isTopOfTask(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(72, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().isTopOfTask(param2IBinder);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void keyguardGoingAway(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(106, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().keyguardGoingAway(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean launchAssistIntent(Intent param2Intent, int param2Int1, String param2String, int param2Int2, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeString(param2String);
              try {
                parcel1.writeInt(param2Int2);
                if (param2Bundle != null) {
                  parcel1.writeInt(1);
                  param2Bundle.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                try {
                  if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
                    bool = IActivityTaskManager.Stub.getDefaultImpl().launchAssistIntent(param2Intent, param2Int1, param2String, param2Int2, param2Bundle);
                    parcel2.recycle();
                    parcel1.recycle();
                    return bool;
                  } 
                  parcel2.readException();
                  param2Int1 = parcel2.readInt();
                  if (param2Int1 == 0)
                    bool = false; 
                  parcel2.recycle();
                  parcel1.recycle();
                  return bool;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2Intent;
      }
      
      public boolean moveActivityTaskToBack(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          boolean bool = true;
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            param2Boolean = IActivityTaskManager.Stub.getDefaultImpl().moveActivityTaskToBack(param2IBinder, param2Boolean);
            return param2Boolean;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0) {
            param2Boolean = bool;
          } else {
            param2Boolean = false;
          } 
          return param2Boolean;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void moveStackToDisplay(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(86, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().moveStackToDisplay(param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void moveTaskToFront(IApplicationThread param2IApplicationThread, String param2String, int param2Int1, int param2Int2, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().moveTaskToFront(param2IApplicationThread, param2String, param2Int1, param2Int2, param2Bundle);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void moveTaskToStack(int param2Int1, int param2Int2, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().moveTaskToStack(param2Int1, param2Int2, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean moveTopActivityToPinnedStack(int param2Int, Rect param2Rect) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          boolean bool = true;
          if (param2Rect != null) {
            parcel1.writeInt(1);
            param2Rect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().moveTopActivityToPinnedStack(param2Int, param2Rect);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean navigateUpTo(IBinder param2IBinder, Intent param2Intent1, int param2Int, Intent param2Intent2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          boolean bool = true;
          if (param2Intent1 != null) {
            parcel1.writeInt(1);
            param2Intent1.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int);
          if (param2Intent2 != null) {
            parcel1.writeInt(1);
            param2Intent2.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().navigateUpTo(param2IBinder, param2Intent1, param2Int, param2Intent2);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void notifyActivityDrawn(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().notifyActivityDrawn(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void notifyEnterAnimationComplete(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().notifyEnterAnimationComplete(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void notifyLaunchTaskBehindComplete(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().notifyLaunchTaskBehindComplete(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void onBackPressedOnTaskRoot(IBinder param2IBinder, IRequestFinishCallback param2IRequestFinishCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2IRequestFinishCallback != null) {
            iBinder = param2IRequestFinishCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(155, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().onBackPressedOnTaskRoot(param2IBinder, param2IRequestFinishCallback);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void overridePendingTransition(IBinder param2IBinder, String param2String, int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().overridePendingTransition(param2IBinder, param2String, param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void positionTaskInStack(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(109, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().positionTaskInStack(param2Int1, param2Int2, param2Int3);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerRemoteAnimationForNextActivityStart(String param2String, RemoteAnimationAdapter param2RemoteAnimationAdapter) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeString(param2String);
          if (param2RemoteAnimationAdapter != null) {
            parcel1.writeInt(1);
            param2RemoteAnimationAdapter.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimationForNextActivityStart(param2String, param2RemoteAnimationAdapter);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerRemoteAnimations(IBinder param2IBinder, RemoteAnimationDefinition param2RemoteAnimationDefinition) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2RemoteAnimationDefinition != null) {
            parcel1.writeInt(1);
            param2RemoteAnimationDefinition.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(137, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimations(param2IBinder, param2RemoteAnimationDefinition);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerRemoteAnimationsForDisplay(int param2Int, RemoteAnimationDefinition param2RemoteAnimationDefinition) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (param2RemoteAnimationDefinition != null) {
            parcel1.writeInt(1);
            param2RemoteAnimationDefinition.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(140, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimationsForDisplay(param2Int, param2RemoteAnimationDefinition);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void registerTaskStackListener(ITaskStackListener param2ITaskStackListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2ITaskStackListener != null) {
            iBinder = param2ITaskStackListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(81, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().registerTaskStackListener(param2ITaskStackListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean releaseActivityInstance(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(77, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().releaseActivityInstance(param2IBinder);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void releaseSomeActivities(IApplicationThread param2IApplicationThread) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().releaseSomeActivities(param2IApplicationThread);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeAllVisibleRecentTasks() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().removeAllVisibleRecentTasks();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeStack(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().removeStack(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeStacksInWindowingModes(int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().removeStacksInWindowingModes(param2ArrayOfint);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void removeStacksWithActivityTypes(int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().removeStacksWithActivityTypes(param2ArrayOfint);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean removeTask(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(30, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().removeTask(param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void reportActivityFullyDrawn(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().reportActivityFullyDrawn(param2IBinder, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void reportAssistContextExtras(IBinder param2IBinder, Bundle param2Bundle, AssistStructure param2AssistStructure, AssistContent param2AssistContent, Uri param2Uri) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2AssistStructure != null) {
            parcel1.writeInt(1);
            param2AssistStructure.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2AssistContent != null) {
            parcel1.writeInt(1);
            param2AssistContent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Uri != null) {
            parcel1.writeInt(1);
            param2Uri.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().reportAssistContextExtras(param2IBinder, param2Bundle, param2AssistStructure, param2AssistContent, param2Uri);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void reportSizeConfigurations(IBinder param2IBinder, int[] param2ArrayOfint1, int[] param2ArrayOfint2, int[] param2ArrayOfint3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeIntArray(param2ArrayOfint1);
          parcel1.writeIntArray(param2ArrayOfint2);
          parcel1.writeIntArray(param2ArrayOfint3);
          if (!this.mRemote.transact(110, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().reportSizeConfigurations(param2IBinder, param2ArrayOfint1, param2ArrayOfint2, param2ArrayOfint3);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean requestAssistContextExtras(int param2Int, IAssistDataReceiver param2IAssistDataReceiver, Bundle param2Bundle, IBinder param2IBinder, boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          try {
            IBinder iBinder;
            parcel1.writeInt(param2Int);
            if (param2IAssistDataReceiver != null) {
              iBinder = param2IAssistDataReceiver.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            boolean bool = true;
            if (param2Bundle != null) {
              parcel1.writeInt(1);
              param2Bundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              boolean bool1;
              parcel1.writeStrongBinder(param2IBinder);
              if (param2Boolean1) {
                bool1 = true;
              } else {
                bool1 = false;
              } 
              parcel1.writeInt(bool1);
              if (param2Boolean2) {
                bool1 = true;
              } else {
                bool1 = false;
              } 
              parcel1.writeInt(bool1);
              try {
                if (!this.mRemote.transact(100, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
                  param2Boolean1 = IActivityTaskManager.Stub.getDefaultImpl().requestAssistContextExtras(param2Int, param2IAssistDataReceiver, param2Bundle, param2IBinder, param2Boolean1, param2Boolean2);
                  parcel2.recycle();
                  parcel1.recycle();
                  return param2Boolean1;
                } 
                parcel2.readException();
                param2Int = parcel2.readInt();
                if (param2Int != 0) {
                  param2Boolean1 = bool;
                } else {
                  param2Boolean1 = false;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return param2Boolean1;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IAssistDataReceiver;
      }
      
      public boolean requestAutofillData(IAssistDataReceiver param2IAssistDataReceiver, Bundle param2Bundle, IBinder param2IBinder, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IAssistDataReceiver != null) {
            iBinder = param2IAssistDataReceiver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          boolean bool = true;
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(101, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().requestAutofillData(param2IAssistDataReceiver, param2Bundle, param2IBinder, param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestPictureInPictureMode(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().requestPictureInPictureMode(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder requestStartActivityPermissionToken(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(78, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            param2IBinder = IActivityTaskManager.Stub.getDefaultImpl().requestStartActivityPermissionToken(param2IBinder);
            return param2IBinder;
          } 
          parcel2.readException();
          param2IBinder = parcel2.readStrongBinder();
          return param2IBinder;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void resizeDockedStack(Rect param2Rect1, Rect param2Rect2, Rect param2Rect3, Rect param2Rect4, Rect param2Rect5) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2Rect1 != null) {
            parcel1.writeInt(1);
            param2Rect1.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Rect2 != null) {
            parcel1.writeInt(1);
            param2Rect2.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Rect3 != null) {
            parcel1.writeInt(1);
            param2Rect3.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Rect4 != null) {
            parcel1.writeInt(1);
            param2Rect4.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Rect5 != null) {
            parcel1.writeInt(1);
            param2Rect5.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().resizeDockedStack(param2Rect1, param2Rect2, param2Rect3, param2Rect4, param2Rect5);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean resizeTask(int param2Int1, Rect param2Rect, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          boolean bool = true;
          if (param2Rect != null) {
            parcel1.writeInt(1);
            param2Rect.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(85, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().resizeTask(param2Int1, param2Rect, param2Int2);
            return bool;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void restartActivityProcessIfVisible(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().restartActivityProcessIfVisible(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void resumeAppSwitches() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(145, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().resumeAppSwitches();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setActivityController(IActivityController param2IActivityController, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IActivityController != null) {
            iBinder = param2IActivityController.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setActivityController(param2IActivityController, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setDisablePreviewScreenshots(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(129, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setDisablePreviewScreenshots(param2IBinder, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setDisplayToSingleTaskInstance(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(153, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setDisplayToSingleTaskInstance(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setFocusedStack(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setFocusedStack(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setFocusedTask(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setFocusedTask(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setFrontActivityScreenCompatMode(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setFrontActivityScreenCompatMode(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setImmersive(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setImmersive(param2IBinder, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setInheritShowWhenLocked(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setInheritShowWhenLocked(param2IBinder, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setLockScreenShown(boolean param2Boolean1, boolean param2Boolean2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          boolean bool1 = true;
          if (param2Boolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (param2Boolean2) {
            bool2 = bool1;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (!this.mRemote.transact(97, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setLockScreenShown(param2Boolean1, param2Boolean2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setPackageAskScreenCompat(String param2String, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeString(param2String);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setPackageAskScreenCompat(param2String, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setPackageScreenCompatMode(String param2String, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setPackageScreenCompatMode(param2String, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setPersistentVrThread(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setPersistentVrThread(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setPictureInPictureParams(IBinder param2IBinder, PictureInPictureParams param2PictureInPictureParams) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2PictureInPictureParams != null) {
            parcel1.writeInt(1);
            param2PictureInPictureParams.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setPictureInPictureParams(param2IBinder, param2PictureInPictureParams);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setRequestedOrientation(IBinder param2IBinder, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setRequestedOrientation(param2IBinder, param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setShowWhenLocked(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setShowWhenLocked(param2IBinder, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setSplitScreenResizing(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setSplitScreenResizing(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setTaskDescription(IBinder param2IBinder, ActivityManager.TaskDescription param2TaskDescription) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2TaskDescription != null) {
            parcel1.writeInt(1);
            param2TaskDescription.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setTaskDescription(param2IBinder, param2TaskDescription);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setTaskResizeable(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setTaskResizeable(param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean setTaskWindowingMode(int param2Int1, int param2Int2, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          boolean bool1 = true;
          if (param2Boolean) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            param2Boolean = IActivityTaskManager.Stub.getDefaultImpl().setTaskWindowingMode(param2Int1, param2Int2, param2Boolean);
            return param2Boolean;
          } 
          parcel2.readException();
          param2Int1 = parcel2.readInt();
          if (param2Int1 != 0) {
            param2Boolean = bool1;
          } else {
            param2Boolean = false;
          } 
          return param2Boolean;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean setTaskWindowingModeSplitScreenPrimary(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          boolean bool1 = true;
          if (param2Boolean) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            param2Boolean = IActivityTaskManager.Stub.getDefaultImpl().setTaskWindowingModeSplitScreenPrimary(param2Int, param2Boolean);
            return param2Boolean;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0) {
            param2Boolean = bool1;
          } else {
            param2Boolean = false;
          } 
          return param2Boolean;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setTurnScreenOn(IBinder param2IBinder, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(136, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setTurnScreenOn(param2IBinder, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setVoiceKeepAwake(IVoiceInteractionSession param2IVoiceInteractionSession, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IVoiceInteractionSession != null) {
            iBinder = param2IVoiceInteractionSession.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(147, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setVoiceKeepAwake(param2IVoiceInteractionSession, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int setVrMode(IBinder param2IBinder, boolean param2Boolean, ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Boolean) {
            i = 1;
          } else {
            i = 0;
          } 
          parcel1.writeInt(i);
          if (param2ComponentName != null) {
            parcel1.writeInt(1);
            param2ComponentName.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(121, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            i = IActivityTaskManager.Stub.getDefaultImpl().setVrMode(param2IBinder, param2Boolean, param2ComponentName);
            return i;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          return i;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setVrThread(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().setVrThread(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean shouldUpRecreateTask(IBinder param2IBinder, String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(34, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().shouldUpRecreateTask(param2IBinder, param2String);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean showAssistFromActivity(IBinder param2IBinder, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          boolean bool = true;
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(103, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().showAssistFromActivity(param2IBinder, param2Bundle);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void showLockTaskEscapeMessage(IBinder param2IBinder) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(105, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().showLockTaskEscapeMessage(param2IBinder);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public int startActivities(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, Intent[] param2ArrayOfIntent, String[] param2ArrayOfString, IBinder param2IBinder, Bundle param2Bundle, int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IApplicationThread != null) {
            iBinder = param2IApplicationThread.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          try {
            parcel1.writeString(param2String1);
            try {
              parcel1.writeString(param2String2);
              try {
                parcel1.writeTypedArray((Parcelable[])param2ArrayOfIntent, 0);
                parcel1.writeStringArray(param2ArrayOfString);
                parcel1.writeStrongBinder(param2IBinder);
                if (param2Bundle != null) {
                  parcel1.writeInt(1);
                  param2Bundle.writeToParcel(parcel1, 0);
                } else {
                  parcel1.writeInt(0);
                } 
                parcel1.writeInt(param2Int);
                if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
                  param2Int = IActivityTaskManager.Stub.getDefaultImpl().startActivities(param2IApplicationThread, param2String1, param2String2, param2ArrayOfIntent, param2ArrayOfString, param2IBinder, param2Bundle, param2Int);
                  parcel2.recycle();
                  parcel1.recycle();
                  return param2Int;
                } 
                parcel2.readException();
                param2Int = parcel2.readInt();
                parcel2.recycle();
                parcel1.recycle();
                return param2Int;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startActivity(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, Intent param2Intent, String param2String3, IBinder param2IBinder, String param2String4, int param2Int1, int param2Int2, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String3);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String4);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityTaskManager.startActivity(param2IApplicationThread, param2String1, param2String2, param2Intent, param2String3, param2IBinder, param2String4, param2Int1, param2Int2, param2ProfilerInfo, param2Bundle);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public WaitResult startActivityAndWait(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, Intent param2Intent, String param2String3, IBinder param2IBinder, String param2String4, int param2Int1, int param2Int2, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String3);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String4);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int3);
          boolean bool = this.mRemote.transact(7, parcel1, parcel2, 0);
          if (!bool)
            try {
              if (IActivityTaskManager.Stub.getDefaultImpl() != null) {
                IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
                try {
                  WaitResult waitResult = iActivityTaskManager.startActivityAndWait(param2IApplicationThread, param2String1, param2String2, param2Intent, param2String3, param2IBinder, param2String4, param2Int1, param2Int2, param2ProfilerInfo, param2Bundle, param2Int3);
                  parcel2.recycle();
                  parcel1.recycle();
                  return waitResult;
                } finally {}
                parcel2.recycle();
                parcel1.recycle();
                throw param2IApplicationThread;
              } 
            } finally {} 
          try {
            parcel2.readException();
            if (parcel2.readInt() != 0) {
              Parcelable.Creator<WaitResult> creator = WaitResult.CREATOR;
              try {
                WaitResult waitResult = (WaitResult)creator.createFromParcel(parcel2);
              } finally {}
            } else {
              param2IApplicationThread = null;
            } 
            parcel2.recycle();
            parcel1.recycle();
            return (WaitResult)param2IApplicationThread;
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startActivityAsCaller(IApplicationThread param2IApplicationThread, String param2String1, Intent param2Intent, String param2String2, IBinder param2IBinder1, String param2String3, int param2Int1, int param2Int2, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle, IBinder param2IBinder2, boolean param2Boolean, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String2);
          parcel1.writeStrongBinder(param2IBinder1);
          parcel1.writeString(param2String3);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeStrongBinder(param2IBinder2);
          if (!param2Boolean)
            bool = false; 
          parcel1.writeInt(bool);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityTaskManager.startActivityAsCaller(param2IApplicationThread, param2String1, param2Intent, param2String2, param2IBinder1, param2String3, param2Int1, param2Int2, param2ProfilerInfo, param2Bundle, param2IBinder2, param2Boolean, param2Int3);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startActivityAsUser(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, Intent param2Intent, String param2String3, IBinder param2IBinder, String param2String4, int param2Int1, int param2Int2, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String3);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String4);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityTaskManager.startActivityAsUser(param2IApplicationThread, param2String1, param2String2, param2Intent, param2String3, param2IBinder, param2String4, param2Int1, param2Int2, param2ProfilerInfo, param2Bundle, param2Int3);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startActivityFromRecents(int param2Int, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            param2Int = IActivityTaskManager.Stub.getDefaultImpl().startActivityFromRecents(param2Int, param2Bundle);
            return param2Int;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          return param2Int;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int startActivityIntentSender(IApplicationThread param2IApplicationThread, IIntentSender param2IIntentSender, IBinder param2IBinder1, Intent param2Intent, String param2String1, IBinder param2IBinder2, String param2String2, int param2Int1, int param2Int2, int param2Int3, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          IBinder iBinder1 = null;
          if (param2IApplicationThread != null) {
            try {
              iBinder2 = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          IBinder iBinder2 = iBinder1;
          if (param2IIntentSender != null)
            iBinder2 = param2IIntentSender.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          parcel1.writeStrongBinder(param2IBinder1);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String1);
          parcel1.writeStrongBinder(param2IBinder2);
          parcel1.writeString(param2String2);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityTaskManager.startActivityIntentSender(param2IApplicationThread, param2IIntentSender, param2IBinder1, param2Intent, param2String1, param2IBinder2, param2String2, param2Int1, param2Int2, param2Int3, param2Bundle);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startActivityWithConfig(IApplicationThread param2IApplicationThread, String param2String1, String param2String2, Intent param2Intent, String param2String3, IBinder param2IBinder, String param2String4, int param2Int1, int param2Int2, Configuration param2Configuration, Bundle param2Bundle, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2IApplicationThread != null) {
            try {
              iBinder = param2IApplicationThread.asBinder();
            } finally {}
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String3);
          parcel1.writeStrongBinder(param2IBinder);
          parcel1.writeString(param2String4);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2Configuration != null) {
            parcel1.writeInt(1);
            param2Configuration.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityTaskManager.startActivityWithConfig(param2IApplicationThread, param2String1, param2String2, param2Intent, param2String3, param2IBinder, param2String4, param2Int1, param2Int2, param2Configuration, param2Bundle, param2Int3);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2IApplicationThread;
      }
      
      public int startAssistantActivity(String param2String1, String param2String2, int param2Int1, int param2Int2, Intent param2Intent, String param2String3, Bundle param2Bundle, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          try {
            parcel1.writeString(param2String1);
            try {
              parcel1.writeString(param2String2);
              parcel1.writeInt(param2Int1);
              parcel1.writeInt(param2Int2);
              if (param2Intent != null) {
                parcel1.writeInt(1);
                param2Intent.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              parcel1.writeString(param2String3);
              if (param2Bundle != null) {
                parcel1.writeInt(1);
                param2Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              parcel1.writeInt(param2Int3);
              if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
                param2Int1 = IActivityTaskManager.Stub.getDefaultImpl().startAssistantActivity(param2String1, param2String2, param2Int1, param2Int2, param2Intent, param2String3, param2Bundle, param2Int3);
                parcel2.recycle();
                parcel1.recycle();
                return param2Int1;
              } 
              parcel2.readException();
              param2Int1 = parcel2.readInt();
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public boolean startDreamActivity(Intent param2Intent) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().startDreamActivity(param2Intent);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void startLocalVoiceInteraction(IBinder param2IBinder, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(122, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().startLocalVoiceInteraction(param2IBinder, param2Bundle);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void startLockTaskModeByToken(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().startLockTaskModeByToken(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean startNextMatchingActivity(IBinder param2IBinder, Intent param2Intent, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          boolean bool = true;
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().startNextMatchingActivity(param2IBinder, param2Intent, param2Bundle);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void startRecentsActivity(Intent param2Intent, IAssistDataReceiver param2IAssistDataReceiver, IRecentsAnimationRunner param2IRecentsAnimationRunner) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2Intent != null) {
            parcel1.writeInt(1);
            param2Intent.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          IBinder iBinder1 = null;
          if (param2IAssistDataReceiver != null) {
            iBinder2 = param2IAssistDataReceiver.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          IBinder iBinder2 = iBinder1;
          if (param2IRecentsAnimationRunner != null)
            iBinder2 = param2IRecentsAnimationRunner.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().startRecentsActivity(param2Intent, param2IAssistDataReceiver, param2IRecentsAnimationRunner);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void startSystemLockTaskMode(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().startSystemLockTaskMode(param2Int);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int startVoiceActivity(String param2String1, String param2String2, int param2Int1, int param2Int2, Intent param2Intent, String param2String3, IVoiceInteractionSession param2IVoiceInteractionSession, IVoiceInteractor param2IVoiceInteractor, int param2Int3, ProfilerInfo param2ProfilerInfo, Bundle param2Bundle, int param2Int4) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeString(param2String1);
          parcel1.writeString(param2String2);
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (param2Intent != null) {
            try {
              parcel1.writeInt(1);
              param2Intent.writeToParcel(parcel1, 0);
            } finally {}
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeString(param2String3);
          IBinder iBinder1 = null;
          if (param2IVoiceInteractionSession != null) {
            iBinder2 = param2IVoiceInteractionSession.asBinder();
          } else {
            iBinder2 = null;
          } 
          parcel1.writeStrongBinder(iBinder2);
          IBinder iBinder2 = iBinder1;
          if (param2IVoiceInteractor != null)
            iBinder2 = param2IVoiceInteractor.asBinder(); 
          parcel1.writeStrongBinder(iBinder2);
          parcel1.writeInt(param2Int3);
          if (param2ProfilerInfo != null) {
            parcel1.writeInt(1);
            param2ProfilerInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel1.writeInt(1);
            param2Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int4);
          if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
            try {
              param2Int1 = iActivityTaskManager.startVoiceActivity(param2String1, param2String2, param2Int1, param2Int2, param2Intent, param2String3, param2IVoiceInteractionSession, param2IVoiceInteractor, param2Int3, param2ProfilerInfo, param2Bundle, param2Int4);
              parcel2.recycle();
              parcel1.recycle();
              return param2Int1;
            } finally {}
          } else {
            Parcel parcel = parcel2;
            parcel.readException();
            param2Int1 = parcel.readInt();
            parcel.recycle();
            parcel1.recycle();
            return param2Int1;
          } 
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2String1;
      }
      
      public void stopAppSwitches() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().stopAppSwitches();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void stopLocalVoiceInteraction(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(123, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().stopLocalVoiceInteraction(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void stopLockTaskModeByToken(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(62, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().stopLockTaskModeByToken(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void stopSystemLockTaskMode() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().stopSystemLockTaskMode();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean supportsLocalVoiceInteraction() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(124, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().supportsLocalVoiceInteraction();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void suppressResizeConfigChanges(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().suppressResizeConfigChanges(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void toggleFreeformWindowingMode(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(84, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().toggleFreeformWindowingMode(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unhandledBack() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().unhandledBack();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterRemoteAnimations(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().unregisterRemoteAnimations(param2IBinder);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void unregisterTaskStackListener(ITaskStackListener param2ITaskStackListener) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          if (param2ITaskStackListener != null) {
            iBinder = param2ITaskStackListener.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().unregisterTaskStackListener(param2ITaskStackListener);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean updateConfiguration(Configuration param2Configuration) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          boolean bool = true;
          if (param2Configuration != null) {
            parcel1.writeInt(1);
            param2Configuration.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(132, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().updateConfiguration(param2Configuration);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i == 0)
            bool = false; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void updateLockTaskFeatures(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().updateLockTaskFeatures(param2Int1, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void updateLockTaskPackages(int param2Int, String[] param2ArrayOfString) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeInt(param2Int);
          parcel1.writeStringArray(param2ArrayOfString);
          if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            IActivityTaskManager.Stub.getDefaultImpl().updateLockTaskPackages(param2Int, param2ArrayOfString);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean willActivityBeVisible(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
          parcel1.writeStrongBinder(param2IBinder);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(40, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
            bool = IActivityTaskManager.Stub.getDefaultImpl().willActivityBeVisible(param2IBinder);
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IActivityTaskManager {
    public static IActivityTaskManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public void activityDestroyed(IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(23, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().activityDestroyed(param1IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void activityIdle(IBinder param1IBinder, Configuration param1Configuration, boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel.writeStrongBinder(param1IBinder);
        boolean bool = false;
        if (param1Configuration != null) {
          parcel.writeInt(1);
          param1Configuration.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(18, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().activityIdle(param1IBinder, param1Configuration, param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void activityPaused(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().activityPaused(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void activityRelaunched(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().activityRelaunched(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void activityResumed(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().activityResumed(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void activityStopped(IBinder param1IBinder, Bundle param1Bundle, PersistableBundle param1PersistableBundle, CharSequence param1CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1PersistableBundle != null) {
          parcel1.writeInt(1);
          param1PersistableBundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param1CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().activityStopped(param1IBinder, param1Bundle, param1PersistableBundle, param1CharSequence);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void activityTopResumedStateLost() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().activityTopResumedStateLost();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int addAppTask(IBinder param1IBinder, Intent param1Intent, ActivityManager.TaskDescription param1TaskDescription, Bitmap param1Bitmap) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1TaskDescription != null) {
          parcel1.writeInt(1);
          param1TaskDescription.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bitmap != null) {
          parcel1.writeInt(1);
          param1Bitmap.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().addAppTask(param1IBinder, param1Intent, param1TaskDescription, param1Bitmap); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void alwaysShowUnsupportedCompileSdkWarning(ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(141, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().alwaysShowUnsupportedCompileSdkWarning(param1ComponentName);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void cancelRecentsAnimation(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(60, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().cancelRecentsAnimation(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void cancelTaskWindowTransition(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(127, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().cancelTaskWindowTransition(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearLaunchParamsForPackages(List<String> param1List) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStringList(param1List);
        if (!this.mRemote.transact(152, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().clearLaunchParamsForPackages(param1List);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean convertFromTranslucent(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(43, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().convertFromTranslucent(param1IBinder);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean convertToTranslucent(IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        boolean bool = true;
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().convertToTranslucent(param1IBinder, param1Bundle);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void dismissKeyguard(IBinder param1IBinder, IKeyguardDismissCallback param1IKeyguardDismissCallback, CharSequence param1CharSequence) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1IKeyguardDismissCallback != null) {
          iBinder = param1IKeyguardDismissCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1CharSequence != null) {
          parcel1.writeInt(1);
          TextUtils.writeToParcel(param1CharSequence, parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(126, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().dismissKeyguard(param1IBinder, param1IKeyguardDismissCallback, param1CharSequence);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean enterPictureInPictureMode(IBinder param1IBinder, PictureInPictureParams param1PictureInPictureParams) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        boolean bool = true;
        if (param1PictureInPictureParams != null) {
          parcel1.writeInt(1);
          param1PictureInPictureParams.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(113, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().enterPictureInPictureMode(param1IBinder, param1PictureInPictureParams);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean finishActivity(IBinder param1IBinder, int param1Int1, Intent param1Intent, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int1);
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().finishActivity(param1IBinder, param1Int1, param1Intent, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean finishActivityAffinity(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().finishActivityAffinity(param1IBinder);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finishSubActivity(IBinder param1IBinder, String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().finishSubActivity(param1IBinder, param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void finishVoiceTask(IVoiceInteractionSession param1IVoiceInteractionSession) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IVoiceInteractionSession != null) {
          iBinder = param1IVoiceInteractionSession.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(71, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().finishVoiceTask(param1IVoiceInteractionSession);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getActivityClassForToken(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(107, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getActivityClassForToken(param1IBinder); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          param1IBinder = null;
        } 
        return (ComponentName)param1IBinder;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getActivityOptions(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getActivityOptions(param1IBinder); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          param1IBinder = null;
        } 
        return (Bundle)param1IBinder;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(93, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getAllStackInfos(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.StackInfo> getAllStackInfosOnDisplay(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(95, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getAllStackInfosOnDisplay(param1Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.StackInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Point getAppTaskThumbnailSize() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Point point;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          point = IActivityTaskManager.Stub.getDefaultImpl().getAppTaskThumbnailSize();
          return point;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          point = (Point)Point.CREATOR.createFromParcel(parcel2);
        } else {
          point = null;
        } 
        return point;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<IBinder> getAppTasks(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getAppTasks(param1String); 
        parcel2.readException();
        return parcel2.createBinderArrayList();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bundle getAssistContextExtras(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Bundle bundle;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(98, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bundle = IActivityTaskManager.Stub.getDefaultImpl().getAssistContextExtras(param1Int);
          return bundle;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel2);
        } else {
          bundle = null;
        } 
        return bundle;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ComponentName getCallingActivity(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getCallingActivity(param1IBinder); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel2);
        } else {
          param1IBinder = null;
        } 
        return (ComponentName)param1IBinder;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getCallingPackage(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getCallingPackage(param1IBinder); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ConfigurationInfo getDeviceConfigurationInfo() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ConfigurationInfo configurationInfo;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(125, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          configurationInfo = IActivityTaskManager.Stub.getDefaultImpl().getDeviceConfigurationInfo();
          return configurationInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          configurationInfo = (ConfigurationInfo)ConfigurationInfo.CREATOR.createFromParcel(parcel2);
        } else {
          configurationInfo = null;
        } 
        return configurationInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getDisplayId(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getDisplayId(param1IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.RunningTaskInfo> getFilteredTasks(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getFilteredTasks(param1Int, param1Boolean); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityManager.StackInfo getFocusedStackInfo() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ActivityManager.StackInfo stackInfo;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(58, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getFocusedStackInfo();
          return stackInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
        } else {
          stackInfo = null;
        } 
        return stackInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getFrontActivityScreenCompatMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getFrontActivityScreenCompatMode(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IActivityTaskManager";
    }
    
    public int getLastResumedActivityUserId() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(131, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getLastResumedActivityUserId(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getLaunchedFromPackage(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getLaunchedFromPackage(param1IBinder); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getLaunchedFromUid(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getLaunchedFromUid(param1IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getLockTaskModeState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getLockTaskModeState(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getMaxNumPictureInPictureActions(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(116, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getMaxNumPictureInPictureActions(param1IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getPackageAskScreenCompat(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(150, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().getPackageAskScreenCompat(param1String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getPackageForToken(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(108, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getPackageForToken(param1IBinder); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPackageScreenCompatMode(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(148, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getPackageScreenCompatMode(param1String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getRecentTasks(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IActivityTaskManager.Stub.getDefaultImpl().getRecentTasks(param1Int1, param1Int2, param1Int3);
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getRequestedOrientation(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getRequestedOrientation(param1IBinder); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityManager.StackInfo getStackInfo(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ActivityManager.StackInfo stackInfo;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(94, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getStackInfo(param1Int1, param1Int2);
          return stackInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
        } else {
          stackInfo = null;
        } 
        return stackInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityManager.StackInfo getStackInfoOnDisplay(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ActivityManager.StackInfo stackInfo;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(96, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          stackInfo = IActivityTaskManager.Stub.getDefaultImpl().getStackInfoOnDisplay(param1Int1, param1Int2, param1Int3);
          return stackInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          stackInfo = (ActivityManager.StackInfo)ActivityManager.StackInfo.CREATOR.createFromParcel(parcel2);
        } else {
          stackInfo = null;
        } 
        return stackInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Rect getTaskBounds(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Rect rect;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(59, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          rect = IActivityTaskManager.Stub.getDefaultImpl().getTaskBounds(param1Int);
          return rect;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          rect = (Rect)Rect.CREATOR.createFromParcel(parcel2);
        } else {
          rect = null;
        } 
        return rect;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityManager.TaskDescription getTaskDescription(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ActivityManager.TaskDescription taskDescription;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          taskDescription = IActivityTaskManager.Stub.getDefaultImpl().getTaskDescription(param1Int);
          return taskDescription;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          taskDescription = (ActivityManager.TaskDescription)ActivityManager.TaskDescription.CREATOR.createFromParcel(parcel2);
        } else {
          taskDescription = null;
        } 
        return taskDescription;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Bitmap getTaskDescriptionIcon(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(80, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getTaskDescriptionIcon(param1String, param1Int); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (Bitmap)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getTaskForActivity(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          i = IActivityTaskManager.Stub.getDefaultImpl().getTaskForActivity(param1IBinder, param1Boolean);
          return i;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ActivityManager.TaskSnapshot getTaskSnapshot(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        ActivityManager.TaskSnapshot taskSnapshot;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(128, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          taskSnapshot = IActivityTaskManager.Stub.getDefaultImpl().getTaskSnapshot(param1Int, param1Boolean);
          return taskSnapshot;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          taskSnapshot = (ActivityManager.TaskSnapshot)ActivityManager.TaskSnapshot.CREATOR.createFromParcel(parcel2);
        } else {
          taskSnapshot = null;
        } 
        return taskSnapshot;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<ActivityManager.RunningTaskInfo> getTasks(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getTasks(param1Int); 
        parcel2.readException();
        return parcel2.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder getUriPermissionOwnerForActivity(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(117, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          param1IBinder = IActivityTaskManager.Stub.getDefaultImpl().getUriPermissionOwnerForActivity(param1IBinder);
          return param1IBinder;
        } 
        parcel2.readException();
        param1IBinder = parcel2.readStrongBinder();
        return param1IBinder;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IWindowOrganizerController getWindowOrganizerController() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(119, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null)
          return IActivityTaskManager.Stub.getDefaultImpl().getWindowOrganizerController(); 
        parcel2.readException();
        return IWindowOrganizerController.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void invalidateHomeTaskSnapshot(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(130, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().invalidateHomeTaskSnapshot(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isActivityStartAllowedOnDisplay(int param1Int1, Intent param1Intent, String param1String, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().isActivityStartAllowedOnDisplay(param1Int1, param1Intent, param1String, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAssistDataAllowedOnCurrentActivity() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(102, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().isAssistDataAllowedOnCurrentActivity();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isImmersive(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(48, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().isImmersive(param1IBinder);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isInLockTaskMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(64, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().isInLockTaskMode();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isRootVoiceInteraction(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(104, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().isRootVoiceInteraction(param1IBinder);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isTopActivityImmersive() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(50, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().isTopActivityImmersive();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isTopOfTask(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(72, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().isTopOfTask(param1IBinder);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void keyguardGoingAway(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(106, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().keyguardGoingAway(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean launchAssistIntent(Intent param1Intent, int param1Int1, String param1String, int param1Int2, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeString(param1String);
            try {
              parcel1.writeInt(param1Int2);
              if (param1Bundle != null) {
                parcel1.writeInt(1);
                param1Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              try {
                if (!this.mRemote.transact(99, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
                  bool = IActivityTaskManager.Stub.getDefaultImpl().launchAssistIntent(param1Intent, param1Int1, param1String, param1Int2, param1Bundle);
                  parcel2.recycle();
                  parcel1.recycle();
                  return bool;
                } 
                parcel2.readException();
                param1Int1 = parcel2.readInt();
                if (param1Int1 == 0)
                  bool = false; 
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1Intent;
    }
    
    public boolean moveActivityTaskToBack(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        boolean bool = true;
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IActivityTaskManager.Stub.getDefaultImpl().moveActivityTaskToBack(param1IBinder, param1Boolean);
          return param1Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param1Boolean = bool;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void moveStackToDisplay(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(86, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().moveStackToDisplay(param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void moveTaskToFront(IApplicationThread param1IApplicationThread, String param1String, int param1Int1, int param1Int2, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().moveTaskToFront(param1IApplicationThread, param1String, param1Int1, param1Int2, param1Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void moveTaskToStack(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(89, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().moveTaskToStack(param1Int1, param1Int2, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean moveTopActivityToPinnedStack(int param1Int, Rect param1Rect) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        boolean bool = true;
        if (param1Rect != null) {
          parcel1.writeInt(1);
          param1Rect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(112, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().moveTopActivityToPinnedStack(param1Int, param1Rect);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean navigateUpTo(IBinder param1IBinder, Intent param1Intent1, int param1Int, Intent param1Intent2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        boolean bool = true;
        if (param1Intent1 != null) {
          parcel1.writeInt(1);
          param1Intent1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int);
        if (param1Intent2 != null) {
          parcel1.writeInt(1);
          param1Intent2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().navigateUpTo(param1IBinder, param1Intent1, param1Int, param1Intent2);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyActivityDrawn(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().notifyActivityDrawn(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyEnterAnimationComplete(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().notifyEnterAnimationComplete(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void notifyLaunchTaskBehindComplete(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().notifyLaunchTaskBehindComplete(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onBackPressedOnTaskRoot(IBinder param1IBinder, IRequestFinishCallback param1IRequestFinishCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1IRequestFinishCallback != null) {
          iBinder = param1IRequestFinishCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(155, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().onBackPressedOnTaskRoot(param1IBinder, param1IRequestFinishCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void overridePendingTransition(IBinder param1IBinder, String param1String, int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().overridePendingTransition(param1IBinder, param1String, param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void positionTaskInStack(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(109, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().positionTaskInStack(param1Int1, param1Int2, param1Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerRemoteAnimationForNextActivityStart(String param1String, RemoteAnimationAdapter param1RemoteAnimationAdapter) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeString(param1String);
        if (param1RemoteAnimationAdapter != null) {
          parcel1.writeInt(1);
          param1RemoteAnimationAdapter.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(139, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimationForNextActivityStart(param1String, param1RemoteAnimationAdapter);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerRemoteAnimations(IBinder param1IBinder, RemoteAnimationDefinition param1RemoteAnimationDefinition) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1RemoteAnimationDefinition != null) {
          parcel1.writeInt(1);
          param1RemoteAnimationDefinition.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(137, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimations(param1IBinder, param1RemoteAnimationDefinition);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerRemoteAnimationsForDisplay(int param1Int, RemoteAnimationDefinition param1RemoteAnimationDefinition) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (param1RemoteAnimationDefinition != null) {
          parcel1.writeInt(1);
          param1RemoteAnimationDefinition.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(140, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().registerRemoteAnimationsForDisplay(param1Int, param1RemoteAnimationDefinition);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerTaskStackListener(ITaskStackListener param1ITaskStackListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1ITaskStackListener != null) {
          iBinder = param1ITaskStackListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(81, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().registerTaskStackListener(param1ITaskStackListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean releaseActivityInstance(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(77, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().releaseActivityInstance(param1IBinder);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void releaseSomeActivities(IApplicationThread param1IApplicationThread) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(79, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().releaseSomeActivities(param1IApplicationThread);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeAllVisibleRecentTasks() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().removeAllVisibleRecentTasks();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeStack(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(87, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().removeStack(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeStacksInWindowingModes(int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(91, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().removeStacksInWindowingModes(param1ArrayOfint);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeStacksWithActivityTypes(int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(92, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().removeStacksWithActivityTypes(param1ArrayOfint);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeTask(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(30, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().removeTask(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportActivityFullyDrawn(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().reportActivityFullyDrawn(param1IBinder, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportAssistContextExtras(IBinder param1IBinder, Bundle param1Bundle, AssistStructure param1AssistStructure, AssistContent param1AssistContent, Uri param1Uri) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1AssistStructure != null) {
          parcel1.writeInt(1);
          param1AssistStructure.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1AssistContent != null) {
          parcel1.writeInt(1);
          param1AssistContent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Uri != null) {
          parcel1.writeInt(1);
          param1Uri.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(56, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().reportAssistContextExtras(param1IBinder, param1Bundle, param1AssistStructure, param1AssistContent, param1Uri);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void reportSizeConfigurations(IBinder param1IBinder, int[] param1ArrayOfint1, int[] param1ArrayOfint2, int[] param1ArrayOfint3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeIntArray(param1ArrayOfint1);
        parcel1.writeIntArray(param1ArrayOfint2);
        parcel1.writeIntArray(param1ArrayOfint3);
        if (!this.mRemote.transact(110, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().reportSizeConfigurations(param1IBinder, param1ArrayOfint1, param1ArrayOfint2, param1ArrayOfint3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean requestAssistContextExtras(int param1Int, IAssistDataReceiver param1IAssistDataReceiver, Bundle param1Bundle, IBinder param1IBinder, boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        try {
          IBinder iBinder;
          parcel1.writeInt(param1Int);
          if (param1IAssistDataReceiver != null) {
            iBinder = param1IAssistDataReceiver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          boolean bool = true;
          if (param1Bundle != null) {
            parcel1.writeInt(1);
            param1Bundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            boolean bool1;
            parcel1.writeStrongBinder(param1IBinder);
            if (param1Boolean1) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            parcel1.writeInt(bool1);
            if (param1Boolean2) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            parcel1.writeInt(bool1);
            try {
              if (!this.mRemote.transact(100, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
                param1Boolean1 = IActivityTaskManager.Stub.getDefaultImpl().requestAssistContextExtras(param1Int, param1IAssistDataReceiver, param1Bundle, param1IBinder, param1Boolean1, param1Boolean2);
                parcel2.recycle();
                parcel1.recycle();
                return param1Boolean1;
              } 
              parcel2.readException();
              param1Int = parcel2.readInt();
              if (param1Int != 0) {
                param1Boolean1 = bool;
              } else {
                param1Boolean1 = false;
              } 
              parcel2.recycle();
              parcel1.recycle();
              return param1Boolean1;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IAssistDataReceiver;
    }
    
    public boolean requestAutofillData(IAssistDataReceiver param1IAssistDataReceiver, Bundle param1Bundle, IBinder param1IBinder, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IAssistDataReceiver != null) {
          iBinder = param1IAssistDataReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        boolean bool = true;
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(101, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().requestAutofillData(param1IAssistDataReceiver, param1Bundle, param1IBinder, param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestPictureInPictureMode(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(115, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().requestPictureInPictureMode(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder requestStartActivityPermissionToken(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(78, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          param1IBinder = IActivityTaskManager.Stub.getDefaultImpl().requestStartActivityPermissionToken(param1IBinder);
          return param1IBinder;
        } 
        parcel2.readException();
        param1IBinder = parcel2.readStrongBinder();
        return param1IBinder;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resizeDockedStack(Rect param1Rect1, Rect param1Rect2, Rect param1Rect3, Rect param1Rect4, Rect param1Rect5) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1Rect1 != null) {
          parcel1.writeInt(1);
          param1Rect1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Rect2 != null) {
          parcel1.writeInt(1);
          param1Rect2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Rect3 != null) {
          parcel1.writeInt(1);
          param1Rect3.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Rect4 != null) {
          parcel1.writeInt(1);
          param1Rect4.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Rect5 != null) {
          parcel1.writeInt(1);
          param1Rect5.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(118, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().resizeDockedStack(param1Rect1, param1Rect2, param1Rect3, param1Rect4, param1Rect5);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean resizeTask(int param1Int1, Rect param1Rect, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        boolean bool = true;
        if (param1Rect != null) {
          parcel1.writeInt(1);
          param1Rect.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(85, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().resizeTask(param1Int1, param1Rect, param1Int2);
          return bool;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void restartActivityProcessIfVisible(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(154, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().restartActivityProcessIfVisible(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resumeAppSwitches() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(145, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().resumeAppSwitches();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setActivityController(IActivityController param1IActivityController, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IActivityController != null) {
          iBinder = param1IActivityController.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(146, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setActivityController(param1IActivityController, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDisablePreviewScreenshots(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(129, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setDisablePreviewScreenshots(param1IBinder, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setDisplayToSingleTaskInstance(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(153, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setDisplayToSingleTaskInstance(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setFocusedStack(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(57, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setFocusedStack(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setFocusedTask(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setFocusedTask(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setFrontActivityScreenCompatMode(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setFrontActivityScreenCompatMode(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setImmersive(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setImmersive(param1IBinder, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setInheritShowWhenLocked(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(135, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setInheritShowWhenLocked(param1IBinder, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setLockScreenShown(boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        boolean bool1 = true;
        if (param1Boolean1) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (param1Boolean2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(97, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setLockScreenShown(param1Boolean1, param1Boolean2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPackageAskScreenCompat(String param1String, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeString(param1String);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(151, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setPackageAskScreenCompat(param1String, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPackageScreenCompatMode(String param1String, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(149, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setPackageScreenCompatMode(param1String, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPersistentVrThread(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(143, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setPersistentVrThread(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPictureInPictureParams(IBinder param1IBinder, PictureInPictureParams param1PictureInPictureParams) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1PictureInPictureParams != null) {
          parcel1.writeInt(1);
          param1PictureInPictureParams.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(114, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setPictureInPictureParams(param1IBinder, param1PictureInPictureParams);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setRequestedOrientation(IBinder param1IBinder, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setRequestedOrientation(param1IBinder, param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setShowWhenLocked(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(134, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setShowWhenLocked(param1IBinder, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSplitScreenResizing(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(120, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setSplitScreenResizing(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTaskDescription(IBinder param1IBinder, ActivityManager.TaskDescription param1TaskDescription) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1TaskDescription != null) {
          parcel1.writeInt(1);
          param1TaskDescription.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setTaskDescription(param1IBinder, param1TaskDescription);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTaskResizeable(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(83, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setTaskResizeable(param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setTaskWindowingMode(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        boolean bool1 = true;
        if (param1Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(88, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IActivityTaskManager.Stub.getDefaultImpl().setTaskWindowingMode(param1Int1, param1Int2, param1Boolean);
          return param1Boolean;
        } 
        parcel2.readException();
        param1Int1 = parcel2.readInt();
        if (param1Int1 != 0) {
          param1Boolean = bool1;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setTaskWindowingModeSplitScreenPrimary(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        boolean bool1 = true;
        if (param1Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(90, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          param1Boolean = IActivityTaskManager.Stub.getDefaultImpl().setTaskWindowingModeSplitScreenPrimary(param1Int, param1Boolean);
          return param1Boolean;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0) {
          param1Boolean = bool1;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTurnScreenOn(IBinder param1IBinder, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(136, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setTurnScreenOn(param1IBinder, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setVoiceKeepAwake(IVoiceInteractionSession param1IVoiceInteractionSession, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IVoiceInteractionSession != null) {
          iBinder = param1IVoiceInteractionSession.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(147, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setVoiceKeepAwake(param1IVoiceInteractionSession, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int setVrMode(IBinder param1IBinder, boolean param1Boolean, ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (param1ComponentName != null) {
          parcel1.writeInt(1);
          param1ComponentName.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(121, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          i = IActivityTaskManager.Stub.getDefaultImpl().setVrMode(param1IBinder, param1Boolean, param1ComponentName);
          return i;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        return i;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setVrThread(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(142, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().setVrThread(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean shouldUpRecreateTask(IBinder param1IBinder, String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(34, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().shouldUpRecreateTask(param1IBinder, param1String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean showAssistFromActivity(IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        boolean bool = true;
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(103, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().showAssistFromActivity(param1IBinder, param1Bundle);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void showLockTaskEscapeMessage(IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(105, parcel, null, 1) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().showLockTaskEscapeMessage(param1IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public int startActivities(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent[] param1ArrayOfIntent, String[] param1ArrayOfString, IBinder param1IBinder, Bundle param1Bundle, int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IApplicationThread != null) {
          iBinder = param1IApplicationThread.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        try {
          parcel1.writeString(param1String1);
          try {
            parcel1.writeString(param1String2);
            try {
              parcel1.writeTypedArray((Parcelable[])param1ArrayOfIntent, 0);
              parcel1.writeStringArray(param1ArrayOfString);
              parcel1.writeStrongBinder(param1IBinder);
              if (param1Bundle != null) {
                parcel1.writeInt(1);
                param1Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              parcel1.writeInt(param1Int);
              if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
                param1Int = IActivityTaskManager.Stub.getDefaultImpl().startActivities(param1IApplicationThread, param1String1, param1String2, param1ArrayOfIntent, param1ArrayOfString, param1IBinder, param1Bundle, param1Int);
                parcel2.recycle();
                parcel1.recycle();
                return param1Int;
              } 
              parcel2.readException();
              param1Int = parcel2.readInt();
              parcel2.recycle();
              parcel1.recycle();
              return param1Int;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startActivity(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String3);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String4);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityTaskManager.startActivity(param1IApplicationThread, param1String1, param1String2, param1Intent, param1String3, param1IBinder, param1String4, param1Int1, param1Int2, param1ProfilerInfo, param1Bundle);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public WaitResult startActivityAndWait(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String3);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String4);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int3);
        boolean bool = this.mRemote.transact(7, parcel1, parcel2, 0);
        if (!bool)
          try {
            if (IActivityTaskManager.Stub.getDefaultImpl() != null) {
              IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
              try {
                WaitResult waitResult = iActivityTaskManager.startActivityAndWait(param1IApplicationThread, param1String1, param1String2, param1Intent, param1String3, param1IBinder, param1String4, param1Int1, param1Int2, param1ProfilerInfo, param1Bundle, param1Int3);
                parcel2.recycle();
                parcel1.recycle();
                return waitResult;
              } finally {}
              parcel2.recycle();
              parcel1.recycle();
              throw param1IApplicationThread;
            } 
          } finally {} 
        try {
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            Parcelable.Creator<WaitResult> creator = WaitResult.CREATOR;
            try {
              WaitResult waitResult = (WaitResult)creator.createFromParcel(parcel2);
            } finally {}
          } else {
            param1IApplicationThread = null;
          } 
          parcel2.recycle();
          parcel1.recycle();
          return (WaitResult)param1IApplicationThread;
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startActivityAsCaller(IApplicationThread param1IApplicationThread, String param1String1, Intent param1Intent, String param1String2, IBinder param1IBinder1, String param1String3, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, IBinder param1IBinder2, boolean param1Boolean, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String2);
        parcel1.writeStrongBinder(param1IBinder1);
        parcel1.writeString(param1String3);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeStrongBinder(param1IBinder2);
        if (!param1Boolean)
          bool = false; 
        parcel1.writeInt(bool);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityTaskManager.startActivityAsCaller(param1IApplicationThread, param1String1, param1Intent, param1String2, param1IBinder1, param1String3, param1Int1, param1Int2, param1ProfilerInfo, param1Bundle, param1IBinder2, param1Boolean, param1Int3);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startActivityAsUser(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String3);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String4);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityTaskManager.startActivityAsUser(param1IApplicationThread, param1String1, param1String2, param1Intent, param1String3, param1IBinder, param1String4, param1Int1, param1Int2, param1ProfilerInfo, param1Bundle, param1Int3);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startActivityFromRecents(int param1Int, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          param1Int = IActivityTaskManager.Stub.getDefaultImpl().startActivityFromRecents(param1Int, param1Bundle);
          return param1Int;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        return param1Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int startActivityIntentSender(IApplicationThread param1IApplicationThread, IIntentSender param1IIntentSender, IBinder param1IBinder1, Intent param1Intent, String param1String1, IBinder param1IBinder2, String param1String2, int param1Int1, int param1Int2, int param1Int3, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        IBinder iBinder1 = null;
        if (param1IApplicationThread != null) {
          try {
            iBinder2 = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IIntentSender != null)
          iBinder2 = param1IIntentSender.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeStrongBinder(param1IBinder1);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String1);
        parcel1.writeStrongBinder(param1IBinder2);
        parcel1.writeString(param1String2);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityTaskManager.startActivityIntentSender(param1IApplicationThread, param1IIntentSender, param1IBinder1, param1Intent, param1String1, param1IBinder2, param1String2, param1Int1, param1Int2, param1Int3, param1Bundle);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startActivityWithConfig(IApplicationThread param1IApplicationThread, String param1String1, String param1String2, Intent param1Intent, String param1String3, IBinder param1IBinder, String param1String4, int param1Int1, int param1Int2, Configuration param1Configuration, Bundle param1Bundle, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1IApplicationThread != null) {
          try {
            iBinder = param1IApplicationThread.asBinder();
          } finally {}
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String3);
        parcel1.writeStrongBinder(param1IBinder);
        parcel1.writeString(param1String4);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1Configuration != null) {
          parcel1.writeInt(1);
          param1Configuration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityTaskManager.startActivityWithConfig(param1IApplicationThread, param1String1, param1String2, param1Intent, param1String3, param1IBinder, param1String4, param1Int1, param1Int2, param1Configuration, param1Bundle, param1Int3);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1IApplicationThread;
    }
    
    public int startAssistantActivity(String param1String1, String param1String2, int param1Int1, int param1Int2, Intent param1Intent, String param1String3, Bundle param1Bundle, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        try {
          parcel1.writeString(param1String1);
          try {
            parcel1.writeString(param1String2);
            parcel1.writeInt(param1Int1);
            parcel1.writeInt(param1Int2);
            if (param1Intent != null) {
              parcel1.writeInt(1);
              param1Intent.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            parcel1.writeString(param1String3);
            if (param1Bundle != null) {
              parcel1.writeInt(1);
              param1Bundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            parcel1.writeInt(param1Int3);
            if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
              param1Int1 = IActivityTaskManager.Stub.getDefaultImpl().startAssistantActivity(param1String1, param1String2, param1Int1, param1Int2, param1Intent, param1String3, param1Bundle, param1Int3);
              parcel2.recycle();
              parcel1.recycle();
              return param1Int1;
            } 
            parcel2.readException();
            param1Int1 = parcel2.readInt();
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public boolean startDreamActivity(Intent param1Intent) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().startDreamActivity(param1Intent);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startLocalVoiceInteraction(IBinder param1IBinder, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(122, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().startLocalVoiceInteraction(param1IBinder, param1Bundle);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startLockTaskModeByToken(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(61, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().startLockTaskModeByToken(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startNextMatchingActivity(IBinder param1IBinder, Intent param1Intent, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        boolean bool = true;
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().startNextMatchingActivity(param1IBinder, param1Intent, param1Bundle);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startRecentsActivity(Intent param1Intent, IAssistDataReceiver param1IAssistDataReceiver, IRecentsAnimationRunner param1IRecentsAnimationRunner) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1Intent != null) {
          parcel1.writeInt(1);
          param1Intent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        IBinder iBinder1 = null;
        if (param1IAssistDataReceiver != null) {
          iBinder2 = param1IAssistDataReceiver.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IRecentsAnimationRunner != null)
          iBinder2 = param1IRecentsAnimationRunner.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().startRecentsActivity(param1Intent, param1IAssistDataReceiver, param1IRecentsAnimationRunner);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startSystemLockTaskMode(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().startSystemLockTaskMode(param1Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int startVoiceActivity(String param1String1, String param1String2, int param1Int1, int param1Int2, Intent param1Intent, String param1String3, IVoiceInteractionSession param1IVoiceInteractionSession, IVoiceInteractor param1IVoiceInteractor, int param1Int3, ProfilerInfo param1ProfilerInfo, Bundle param1Bundle, int param1Int4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeString(param1String1);
        parcel1.writeString(param1String2);
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (param1Intent != null) {
          try {
            parcel1.writeInt(1);
            param1Intent.writeToParcel(parcel1, 0);
          } finally {}
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param1String3);
        IBinder iBinder1 = null;
        if (param1IVoiceInteractionSession != null) {
          iBinder2 = param1IVoiceInteractionSession.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param1IVoiceInteractor != null)
          iBinder2 = param1IVoiceInteractor.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeInt(param1Int3);
        if (param1ProfilerInfo != null) {
          parcel1.writeInt(1);
          param1ProfilerInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel1.writeInt(1);
          param1Bundle.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int4);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager iActivityTaskManager = IActivityTaskManager.Stub.getDefaultImpl();
          try {
            param1Int1 = iActivityTaskManager.startVoiceActivity(param1String1, param1String2, param1Int1, param1Int2, param1Intent, param1String3, param1IVoiceInteractionSession, param1IVoiceInteractor, param1Int3, param1ProfilerInfo, param1Bundle, param1Int4);
            parcel2.recycle();
            parcel1.recycle();
            return param1Int1;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          param1Int1 = parcel.readInt();
          parcel.recycle();
          parcel1.recycle();
          return param1Int1;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1String1;
    }
    
    public void stopAppSwitches() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(144, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().stopAppSwitches();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopLocalVoiceInteraction(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(123, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().stopLocalVoiceInteraction(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopLockTaskModeByToken(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(62, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().stopLockTaskModeByToken(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopSystemLockTaskMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().stopSystemLockTaskMode();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean supportsLocalVoiceInteraction() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(124, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().supportsLocalVoiceInteraction();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void suppressResizeConfigChanges(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(111, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().suppressResizeConfigChanges(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void toggleFreeformWindowingMode(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(84, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().toggleFreeformWindowingMode(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unhandledBack() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().unhandledBack();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterRemoteAnimations(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(138, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().unregisterRemoteAnimations(param1IBinder);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterTaskStackListener(ITaskStackListener param1ITaskStackListener) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        if (param1ITaskStackListener != null) {
          iBinder = param1ITaskStackListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(82, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().unregisterTaskStackListener(param1ITaskStackListener);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean updateConfiguration(Configuration param1Configuration) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        boolean bool = true;
        if (param1Configuration != null) {
          parcel1.writeInt(1);
          param1Configuration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(132, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().updateConfiguration(param1Configuration);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateLockTaskFeatures(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(133, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().updateLockTaskFeatures(param1Int1, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void updateLockTaskPackages(int param1Int, String[] param1ArrayOfString) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeInt(param1Int);
        parcel1.writeStringArray(param1ArrayOfString);
        if (!this.mRemote.transact(63, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          IActivityTaskManager.Stub.getDefaultImpl().updateLockTaskPackages(param1Int, param1ArrayOfString);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean willActivityBeVisible(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IActivityTaskManager");
        parcel1.writeStrongBinder(param1IBinder);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(40, parcel1, parcel2, 0) && IActivityTaskManager.Stub.getDefaultImpl() != null) {
          bool = IActivityTaskManager.Stub.getDefaultImpl().willActivityBeVisible(param1IBinder);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityTaskManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */