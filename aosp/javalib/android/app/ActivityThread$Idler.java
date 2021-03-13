package android.app;

import android.os.MessageQueue;
import android.os.RemoteException;

class Idler implements MessageQueue.IdleHandler {
  private Idler() {}
  
  public final boolean queueIdle() {
    ActivityThread.ActivityClientRecord activityClientRecord = ActivityThread.this.mNewActivities;
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (ActivityThread.this.mBoundApplication != null) {
      bool2 = bool1;
      if (ActivityThread.this.mProfiler.profileFd != null) {
        bool2 = bool1;
        if (ActivityThread.this.mProfiler.autoStopProfiler)
          bool2 = true; 
      } 
    } 
    if (activityClientRecord != null) {
      ActivityThread.ActivityClientRecord activityClientRecord1;
      ActivityThread.this.mNewActivities = null;
      IActivityTaskManager iActivityTaskManager = ActivityTaskManager.getService();
      do {
        if (activityClientRecord.activity != null && !activityClientRecord.activity.mFinished)
          try {
            iActivityTaskManager.activityIdle(activityClientRecord.token, activityClientRecord.createdConfig, bool2);
            activityClientRecord.createdConfig = null;
          } catch (RemoteException remoteException) {
            throw remoteException.rethrowFromSystemServer();
          }  
        activityClientRecord1 = ((ActivityThread.ActivityClientRecord)remoteException).nextIdle;
        ((ActivityThread.ActivityClientRecord)remoteException).nextIdle = null;
        ActivityThread.ActivityClientRecord activityClientRecord2 = activityClientRecord1;
      } while (activityClientRecord1 != null);
    } 
    if (bool2)
      ActivityThread.this.mProfiler.stopProfiling(); 
    ActivityThread.access$3400(ActivityThread.this);
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$Idler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */