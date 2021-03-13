package android.app;

import android.os.RemoteException;

class null implements Runnable {
  public void run() {
    if (!ActivityThread.this.mSomeActivitiesChanged)
      return; 
    Runtime runtime = Runtime.getRuntime();
    long l = runtime.maxMemory();
    if (runtime.totalMemory() - runtime.freeMemory() > 3L * l / 4L) {
      ActivityThread.this.mSomeActivitiesChanged = false;
      try {
        ActivityTaskManager.getService().releaseSomeActivities(ActivityThread.this.mAppThread);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityThread$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */