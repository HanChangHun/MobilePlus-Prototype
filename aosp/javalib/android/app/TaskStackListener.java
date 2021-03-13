package android.app;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

public abstract class TaskStackListener extends ITaskStackListener.Stub {
  public void onActivityDismissingDockedStack() throws RemoteException {}
  
  public void onActivityForcedResizable(String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  @Deprecated
  public void onActivityLaunchOnSecondaryDisplayFailed() throws RemoteException {}
  
  public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo paramRunningTaskInfo, int paramInt) throws RemoteException {
    onActivityLaunchOnSecondaryDisplayFailed();
  }
  
  public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo paramRunningTaskInfo, int paramInt) throws RemoteException {}
  
  public void onActivityPinned(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void onActivityRequestedOrientationChanged(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo paramRunningTaskInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) throws RemoteException {}
  
  public void onActivityRotation(int paramInt) {}
  
  public void onActivityUnpinned() throws RemoteException {}
  
  public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException {}
  
  public void onRecentTaskListFrozenChanged(boolean paramBoolean) {}
  
  public void onRecentTaskListUpdated() throws RemoteException {}
  
  public void onSingleTaskDisplayDrawn(int paramInt) throws RemoteException {}
  
  public void onSingleTaskDisplayEmpty(int paramInt) throws RemoteException {}
  
  public void onSizeCompatModeActivityChanged(int paramInt, IBinder paramIBinder) throws RemoteException {}
  
  public void onTaskCreated(int paramInt, ComponentName paramComponentName) throws RemoteException {}
  
  @Deprecated
  public void onTaskDescriptionChanged(int paramInt, ActivityManager.TaskDescription paramTaskDescription) throws RemoteException {}
  
  public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException {
    onTaskDescriptionChanged(paramRunningTaskInfo.taskId, paramRunningTaskInfo.taskDescription);
  }
  
  public void onTaskDisplayChanged(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onTaskFocusChanged(int paramInt, boolean paramBoolean) {}
  
  @Deprecated
  public void onTaskMovedToFront(int paramInt) throws RemoteException {}
  
  public void onTaskMovedToFront(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException {
    onTaskMovedToFront(paramRunningTaskInfo.taskId);
  }
  
  public void onTaskProfileLocked(int paramInt1, int paramInt2) throws RemoteException {}
  
  @Deprecated
  public void onTaskRemovalStarted(int paramInt) throws RemoteException {}
  
  public void onTaskRemovalStarted(ActivityManager.RunningTaskInfo paramRunningTaskInfo) throws RemoteException {
    onTaskRemovalStarted(paramRunningTaskInfo.taskId);
  }
  
  public void onTaskRemoved(int paramInt) throws RemoteException {}
  
  public void onTaskRequestedOrientationChanged(int paramInt1, int paramInt2) {}
  
  public void onTaskSnapshotChanged(int paramInt, ActivityManager.TaskSnapshot paramTaskSnapshot) throws RemoteException {
    if (Binder.getCallingPid() != Process.myPid() && paramTaskSnapshot != null && paramTaskSnapshot.getSnapshot() != null)
      paramTaskSnapshot.getSnapshot().destroy(); 
  }
  
  public void onTaskStackChanged() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/TaskStackListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */