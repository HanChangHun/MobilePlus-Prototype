package android.app;

import android.os.RemoteException;
import android.os.SystemClock;
import com.android.internal.app.IAppOpsService;
import com.android.internal.app.MessageSamplingConfig;

class null extends AppOpsManager.OnOpNotedCallback {
  private void reportStackTraceIfNeeded(SyncNotedAppOp paramSyncNotedAppOp) {
    if (!AppOpsManager.access$000())
      return; 
    MessageSamplingConfig messageSamplingConfig = AppOpsManager.access$100();
    if (AppOpsManager.leftCircularDistance(AppOpsManager.strOpToOp(paramSyncNotedAppOp.getOp()), messageSamplingConfig.getSampledOpCode(), 103) <= messageSamplingConfig.getAcceptableLeftDistance() || messageSamplingConfig.getExpirationTimeSinceBootMillis() < SystemClock.elapsedRealtime()) {
      String str = AppOpsManager.access$200();
      try {
        String str1 = ActivityThread.currentOpPackageName();
        IAppOpsService iAppOpsService = AppOpsManager.access$300();
        if (str1 == null)
          str1 = ""; 
        AppOpsManager.access$102(iAppOpsService.reportRuntimeAppOpAccessMessageAndGetConfig(str1, paramSyncNotedAppOp, str));
      } catch (RemoteException remoteException) {
        remoteException.rethrowFromSystemServer();
      } 
    } 
  }
  
  public void onAsyncNoted(AsyncNotedAppOp paramAsyncNotedAppOp) {}
  
  public void onNoted(SyncNotedAppOp paramSyncNotedAppOp) {
    reportStackTraceIfNeeded(paramSyncNotedAppOp);
  }
  
  public void onSelfNoted(SyncNotedAppOp paramSyncNotedAppOp) {
    reportStackTraceIfNeeded(paramSyncNotedAppOp);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */