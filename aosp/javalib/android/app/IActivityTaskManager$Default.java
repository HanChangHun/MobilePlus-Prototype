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
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.service.voice.IVoiceInteractionSession;
import android.view.IRecentsAnimationRunner;
import android.view.RemoteAnimationAdapter;
import android.view.RemoteAnimationDefinition;
import android.window.IWindowOrganizerController;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.policy.IKeyguardDismissCallback;
import java.util.List;

public class Default implements IActivityTaskManager {
  public void activityDestroyed(IBinder paramIBinder) throws RemoteException {}
  
  public void activityIdle(IBinder paramIBinder, Configuration paramConfiguration, boolean paramBoolean) throws RemoteException {}
  
  public void activityPaused(IBinder paramIBinder) throws RemoteException {}
  
  public void activityRelaunched(IBinder paramIBinder) throws RemoteException {}
  
  public void activityResumed(IBinder paramIBinder) throws RemoteException {}
  
  public void activityStopped(IBinder paramIBinder, Bundle paramBundle, PersistableBundle paramPersistableBundle, CharSequence paramCharSequence) throws RemoteException {}
  
  public void activityTopResumedStateLost() throws RemoteException {}
  
  public int addAppTask(IBinder paramIBinder, Intent paramIntent, ActivityManager.TaskDescription paramTaskDescription, Bitmap paramBitmap) throws RemoteException {
    return 0;
  }
  
  public void alwaysShowUnsupportedCompileSdkWarning(ComponentName paramComponentName) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void cancelRecentsAnimation(boolean paramBoolean) throws RemoteException {}
  
  public void cancelTaskWindowTransition(int paramInt) throws RemoteException {}
  
  public void clearLaunchParamsForPackages(List<String> paramList) throws RemoteException {}
  
  public boolean convertFromTranslucent(IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public boolean convertToTranslucent(IBinder paramIBinder, Bundle paramBundle) throws RemoteException {
    return false;
  }
  
  public void dismissKeyguard(IBinder paramIBinder, IKeyguardDismissCallback paramIKeyguardDismissCallback, CharSequence paramCharSequence) throws RemoteException {}
  
  public boolean enterPictureInPictureMode(IBinder paramIBinder, PictureInPictureParams paramPictureInPictureParams) throws RemoteException {
    return false;
  }
  
  public boolean finishActivity(IBinder paramIBinder, int paramInt1, Intent paramIntent, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean finishActivityAffinity(IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public void finishSubActivity(IBinder paramIBinder, String paramString, int paramInt) throws RemoteException {}
  
  public void finishVoiceTask(IVoiceInteractionSession paramIVoiceInteractionSession) throws RemoteException {}
  
  public ComponentName getActivityClassForToken(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public Bundle getActivityOptions(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public List<ActivityManager.StackInfo> getAllStackInfos() throws RemoteException {
    return null;
  }
  
  public List<ActivityManager.StackInfo> getAllStackInfosOnDisplay(int paramInt) throws RemoteException {
    return null;
  }
  
  public Point getAppTaskThumbnailSize() throws RemoteException {
    return null;
  }
  
  public List<IBinder> getAppTasks(String paramString) throws RemoteException {
    return null;
  }
  
  public Bundle getAssistContextExtras(int paramInt) throws RemoteException {
    return null;
  }
  
  public ComponentName getCallingActivity(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public String getCallingPackage(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public ConfigurationInfo getDeviceConfigurationInfo() throws RemoteException {
    return null;
  }
  
  public int getDisplayId(IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public List<ActivityManager.RunningTaskInfo> getFilteredTasks(int paramInt, boolean paramBoolean) throws RemoteException {
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
  
  public String getLaunchedFromPackage(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public int getLaunchedFromUid(IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public int getLockTaskModeState() throws RemoteException {
    return 0;
  }
  
  public int getMaxNumPictureInPictureActions(IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public boolean getPackageAskScreenCompat(String paramString) throws RemoteException {
    return false;
  }
  
  public String getPackageForToken(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public int getPackageScreenCompatMode(String paramString) throws RemoteException {
    return 0;
  }
  
  public ParceledListSlice getRecentTasks(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    return null;
  }
  
  public int getRequestedOrientation(IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public ActivityManager.StackInfo getStackInfo(int paramInt1, int paramInt2) throws RemoteException {
    return null;
  }
  
  public ActivityManager.StackInfo getStackInfoOnDisplay(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    return null;
  }
  
  public Rect getTaskBounds(int paramInt) throws RemoteException {
    return null;
  }
  
  public ActivityManager.TaskDescription getTaskDescription(int paramInt) throws RemoteException {
    return null;
  }
  
  public Bitmap getTaskDescriptionIcon(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public int getTaskForActivity(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    return 0;
  }
  
  public ActivityManager.TaskSnapshot getTaskSnapshot(int paramInt, boolean paramBoolean) throws RemoteException {
    return null;
  }
  
  public List<ActivityManager.RunningTaskInfo> getTasks(int paramInt) throws RemoteException {
    return null;
  }
  
  public IBinder getUriPermissionOwnerForActivity(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public IWindowOrganizerController getWindowOrganizerController() throws RemoteException {
    return null;
  }
  
  public void invalidateHomeTaskSnapshot(IBinder paramIBinder) throws RemoteException {}
  
  public boolean isActivityStartAllowedOnDisplay(int paramInt1, Intent paramIntent, String paramString, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean isAssistDataAllowedOnCurrentActivity() throws RemoteException {
    return false;
  }
  
  public boolean isImmersive(IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public boolean isInLockTaskMode() throws RemoteException {
    return false;
  }
  
  public boolean isRootVoiceInteraction(IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public boolean isTopActivityImmersive() throws RemoteException {
    return false;
  }
  
  public boolean isTopOfTask(IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public void keyguardGoingAway(int paramInt) throws RemoteException {}
  
  public boolean launchAssistIntent(Intent paramIntent, int paramInt1, String paramString, int paramInt2, Bundle paramBundle) throws RemoteException {
    return false;
  }
  
  public boolean moveActivityTaskToBack(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void moveStackToDisplay(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void moveTaskToFront(IApplicationThread paramIApplicationThread, String paramString, int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException {}
  
  public void moveTaskToStack(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {}
  
  public boolean moveTopActivityToPinnedStack(int paramInt, Rect paramRect) throws RemoteException {
    return false;
  }
  
  public boolean navigateUpTo(IBinder paramIBinder, Intent paramIntent1, int paramInt, Intent paramIntent2) throws RemoteException {
    return false;
  }
  
  public void notifyActivityDrawn(IBinder paramIBinder) throws RemoteException {}
  
  public void notifyEnterAnimationComplete(IBinder paramIBinder) throws RemoteException {}
  
  public void notifyLaunchTaskBehindComplete(IBinder paramIBinder) throws RemoteException {}
  
  public void onBackPressedOnTaskRoot(IBinder paramIBinder, IRequestFinishCallback paramIRequestFinishCallback) throws RemoteException {}
  
  public void overridePendingTransition(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void positionTaskInStack(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void registerRemoteAnimationForNextActivityStart(String paramString, RemoteAnimationAdapter paramRemoteAnimationAdapter) throws RemoteException {}
  
  public void registerRemoteAnimations(IBinder paramIBinder, RemoteAnimationDefinition paramRemoteAnimationDefinition) throws RemoteException {}
  
  public void registerRemoteAnimationsForDisplay(int paramInt, RemoteAnimationDefinition paramRemoteAnimationDefinition) throws RemoteException {}
  
  public void registerTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException {}
  
  public boolean releaseActivityInstance(IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public void releaseSomeActivities(IApplicationThread paramIApplicationThread) throws RemoteException {}
  
  public void removeAllVisibleRecentTasks() throws RemoteException {}
  
  public void removeStack(int paramInt) throws RemoteException {}
  
  public void removeStacksInWindowingModes(int[] paramArrayOfint) throws RemoteException {}
  
  public void removeStacksWithActivityTypes(int[] paramArrayOfint) throws RemoteException {}
  
  public boolean removeTask(int paramInt) throws RemoteException {
    return false;
  }
  
  public void reportActivityFullyDrawn(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void reportAssistContextExtras(IBinder paramIBinder, Bundle paramBundle, AssistStructure paramAssistStructure, AssistContent paramAssistContent, Uri paramUri) throws RemoteException {}
  
  public void reportSizeConfigurations(IBinder paramIBinder, int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3) throws RemoteException {}
  
  public boolean requestAssistContextExtras(int paramInt, IAssistDataReceiver paramIAssistDataReceiver, Bundle paramBundle, IBinder paramIBinder, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    return false;
  }
  
  public boolean requestAutofillData(IAssistDataReceiver paramIAssistDataReceiver, Bundle paramBundle, IBinder paramIBinder, int paramInt) throws RemoteException {
    return false;
  }
  
  public void requestPictureInPictureMode(IBinder paramIBinder) throws RemoteException {}
  
  public IBinder requestStartActivityPermissionToken(IBinder paramIBinder) throws RemoteException {
    return null;
  }
  
  public void resizeDockedStack(Rect paramRect1, Rect paramRect2, Rect paramRect3, Rect paramRect4, Rect paramRect5) throws RemoteException {}
  
  public boolean resizeTask(int paramInt1, Rect paramRect, int paramInt2) throws RemoteException {
    return false;
  }
  
  public void restartActivityProcessIfVisible(IBinder paramIBinder) throws RemoteException {}
  
  public void resumeAppSwitches() throws RemoteException {}
  
  public void setActivityController(IActivityController paramIActivityController, boolean paramBoolean) throws RemoteException {}
  
  public void setDisablePreviewScreenshots(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void setDisplayToSingleTaskInstance(int paramInt) throws RemoteException {}
  
  public void setFocusedStack(int paramInt) throws RemoteException {}
  
  public void setFocusedTask(int paramInt) throws RemoteException {}
  
  public void setFrontActivityScreenCompatMode(int paramInt) throws RemoteException {}
  
  public void setImmersive(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void setInheritShowWhenLocked(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void setLockScreenShown(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
  
  public void setPackageAskScreenCompat(String paramString, boolean paramBoolean) throws RemoteException {}
  
  public void setPackageScreenCompatMode(String paramString, int paramInt) throws RemoteException {}
  
  public void setPersistentVrThread(int paramInt) throws RemoteException {}
  
  public void setPictureInPictureParams(IBinder paramIBinder, PictureInPictureParams paramPictureInPictureParams) throws RemoteException {}
  
  public void setRequestedOrientation(IBinder paramIBinder, int paramInt) throws RemoteException {}
  
  public void setShowWhenLocked(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void setSplitScreenResizing(boolean paramBoolean) throws RemoteException {}
  
  public void setTaskDescription(IBinder paramIBinder, ActivityManager.TaskDescription paramTaskDescription) throws RemoteException {}
  
  public void setTaskResizeable(int paramInt1, int paramInt2) throws RemoteException {}
  
  public boolean setTaskWindowingMode(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean setTaskWindowingModeSplitScreenPrimary(int paramInt, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public void setTurnScreenOn(IBinder paramIBinder, boolean paramBoolean) throws RemoteException {}
  
  public void setVoiceKeepAwake(IVoiceInteractionSession paramIVoiceInteractionSession, boolean paramBoolean) throws RemoteException {}
  
  public int setVrMode(IBinder paramIBinder, boolean paramBoolean, ComponentName paramComponentName) throws RemoteException {
    return 0;
  }
  
  public void setVrThread(int paramInt) throws RemoteException {}
  
  public boolean shouldUpRecreateTask(IBinder paramIBinder, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean showAssistFromActivity(IBinder paramIBinder, Bundle paramBundle) throws RemoteException {
    return false;
  }
  
  public void showLockTaskEscapeMessage(IBinder paramIBinder) throws RemoteException {}
  
  public int startActivities(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent[] paramArrayOfIntent, String[] paramArrayOfString, IBinder paramIBinder, Bundle paramBundle, int paramInt) throws RemoteException {
    return 0;
  }
  
  public int startActivity(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle) throws RemoteException {
    return 0;
  }
  
  public WaitResult startActivityAndWait(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException {
    return null;
  }
  
  public int startActivityAsCaller(IApplicationThread paramIApplicationThread, String paramString1, Intent paramIntent, String paramString2, IBinder paramIBinder1, String paramString3, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, IBinder paramIBinder2, boolean paramBoolean, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public int startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public int startActivityFromRecents(int paramInt, Bundle paramBundle) throws RemoteException {
    return 0;
  }
  
  public int startActivityIntentSender(IApplicationThread paramIApplicationThread, IIntentSender paramIIntentSender, IBinder paramIBinder1, Intent paramIntent, String paramString1, IBinder paramIBinder2, String paramString2, int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) throws RemoteException {
    return 0;
  }
  
  public int startActivityWithConfig(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, String paramString3, IBinder paramIBinder, String paramString4, int paramInt1, int paramInt2, Configuration paramConfiguration, Bundle paramBundle, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public int startAssistantActivity(String paramString1, String paramString2, int paramInt1, int paramInt2, Intent paramIntent, String paramString3, Bundle paramBundle, int paramInt3) throws RemoteException {
    return 0;
  }
  
  public boolean startDreamActivity(Intent paramIntent) throws RemoteException {
    return false;
  }
  
  public void startLocalVoiceInteraction(IBinder paramIBinder, Bundle paramBundle) throws RemoteException {}
  
  public void startLockTaskModeByToken(IBinder paramIBinder) throws RemoteException {}
  
  public boolean startNextMatchingActivity(IBinder paramIBinder, Intent paramIntent, Bundle paramBundle) throws RemoteException {
    return false;
  }
  
  public void startRecentsActivity(Intent paramIntent, IAssistDataReceiver paramIAssistDataReceiver, IRecentsAnimationRunner paramIRecentsAnimationRunner) throws RemoteException {}
  
  public void startSystemLockTaskMode(int paramInt) throws RemoteException {}
  
  public int startVoiceActivity(String paramString1, String paramString2, int paramInt1, int paramInt2, Intent paramIntent, String paramString3, IVoiceInteractionSession paramIVoiceInteractionSession, IVoiceInteractor paramIVoiceInteractor, int paramInt3, ProfilerInfo paramProfilerInfo, Bundle paramBundle, int paramInt4) throws RemoteException {
    return 0;
  }
  
  public void stopAppSwitches() throws RemoteException {}
  
  public void stopLocalVoiceInteraction(IBinder paramIBinder) throws RemoteException {}
  
  public void stopLockTaskModeByToken(IBinder paramIBinder) throws RemoteException {}
  
  public void stopSystemLockTaskMode() throws RemoteException {}
  
  public boolean supportsLocalVoiceInteraction() throws RemoteException {
    return false;
  }
  
  public void suppressResizeConfigChanges(boolean paramBoolean) throws RemoteException {}
  
  public void toggleFreeformWindowingMode(IBinder paramIBinder) throws RemoteException {}
  
  public void unhandledBack() throws RemoteException {}
  
  public void unregisterRemoteAnimations(IBinder paramIBinder) throws RemoteException {}
  
  public void unregisterTaskStackListener(ITaskStackListener paramITaskStackListener) throws RemoteException {}
  
  public boolean updateConfiguration(Configuration paramConfiguration) throws RemoteException {
    return false;
  }
  
  public void updateLockTaskFeatures(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void updateLockTaskPackages(int paramInt, String[] paramArrayOfString) throws RemoteException {}
  
  public boolean willActivityBeVisible(IBinder paramIBinder) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IActivityTaskManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */