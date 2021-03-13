package android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class AppTask {
  private IAppTask mAppTaskImpl;
  
  public AppTask(IAppTask paramIAppTask) {
    this.mAppTaskImpl = paramIAppTask;
  }
  
  public void finishAndRemoveTask() {
    try {
      this.mAppTaskImpl.finishAndRemoveTask();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public ActivityManager.RecentTaskInfo getTaskInfo() {
    try {
      return this.mAppTaskImpl.getTaskInfo();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void moveToFront() {
    try {
      ActivityThread.ApplicationThread applicationThread = ActivityThread.currentActivityThread().getApplicationThread();
      String str = ActivityThread.currentPackageName();
      this.mAppTaskImpl.moveToFront(applicationThread, str);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setExcludeFromRecents(boolean paramBoolean) {
    try {
      this.mAppTaskImpl.setExcludeFromRecents(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void startActivity(Context paramContext, Intent paramIntent, Bundle paramBundle) {
    ActivityThread activityThread = ActivityThread.currentActivityThread();
    activityThread.getInstrumentation().execStartActivityFromAppTask(paramContext, (IBinder)activityThread.getApplicationThread(), this.mAppTaskImpl, paramIntent, paramBundle);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$AppTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */