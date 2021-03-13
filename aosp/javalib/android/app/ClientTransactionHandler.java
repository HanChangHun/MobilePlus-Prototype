package android.app;

import android.app.servertransaction.ClientTransaction;
import android.app.servertransaction.ClientTransactionItem;
import android.app.servertransaction.PendingTransactionActions;
import android.app.servertransaction.TransactionExecutor;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.MergedConfiguration;
import android.view.DisplayAdjustments;
import com.android.internal.content.ReferrerIntent;
import java.util.List;
import java.util.Map;

public abstract class ClientTransactionHandler {
  public abstract void countLaunchingActivities(int paramInt);
  
  public void executeTransaction(ClientTransaction paramClientTransaction) {
    paramClientTransaction.preExecute(this);
    getTransactionExecutor().execute(paramClientTransaction);
    paramClientTransaction.recycle();
  }
  
  public abstract Map<IBinder, ClientTransactionItem> getActivitiesToBeDestroyed();
  
  public abstract Activity getActivity(IBinder paramIBinder);
  
  public abstract ActivityThread.ActivityClientRecord getActivityClient(IBinder paramIBinder);
  
  public abstract LoadedApk getPackageInfoNoCheck(ApplicationInfo paramApplicationInfo, CompatibilityInfo paramCompatibilityInfo);
  
  abstract TransactionExecutor getTransactionExecutor();
  
  public abstract void handleActivityConfigurationChanged(IBinder paramIBinder, Configuration paramConfiguration, int paramInt);
  
  public abstract void handleConfigurationChanged(Configuration paramConfiguration);
  
  public abstract void handleDestroyActivity(IBinder paramIBinder, boolean paramBoolean1, int paramInt, boolean paramBoolean2, String paramString);
  
  public abstract void handleFixedRotationAdjustments(IBinder paramIBinder, DisplayAdjustments.FixedRotationAdjustments paramFixedRotationAdjustments);
  
  public abstract Activity handleLaunchActivity(ActivityThread.ActivityClientRecord paramActivityClientRecord, PendingTransactionActions paramPendingTransactionActions, Intent paramIntent);
  
  public abstract void handleNewIntent(IBinder paramIBinder, List<ReferrerIntent> paramList);
  
  public abstract void handlePauseActivity(IBinder paramIBinder, boolean paramBoolean1, boolean paramBoolean2, int paramInt, PendingTransactionActions paramPendingTransactionActions, String paramString);
  
  public abstract void handlePictureInPictureRequested(IBinder paramIBinder);
  
  public abstract void handleRelaunchActivity(ActivityThread.ActivityClientRecord paramActivityClientRecord, PendingTransactionActions paramPendingTransactionActions);
  
  public abstract void handleResumeActivity(IBinder paramIBinder, boolean paramBoolean1, boolean paramBoolean2, String paramString);
  
  public abstract void handleSendResult(IBinder paramIBinder, List<ResultInfo> paramList, String paramString);
  
  public abstract void handleStartActivity(IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions);
  
  public abstract void handleStopActivity(IBinder paramIBinder, int paramInt, PendingTransactionActions paramPendingTransactionActions, boolean paramBoolean, String paramString);
  
  public abstract void handleTopResumedActivityChanged(IBinder paramIBinder, boolean paramBoolean, String paramString);
  
  public abstract void performRestartActivity(IBinder paramIBinder, boolean paramBoolean);
  
  public abstract ActivityThread.ActivityClientRecord prepareRelaunchActivity(IBinder paramIBinder, List<ResultInfo> paramList, List<ReferrerIntent> paramList1, int paramInt, MergedConfiguration paramMergedConfiguration, boolean paramBoolean);
  
  public abstract void reportRelaunch(IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions);
  
  public abstract void reportStop(PendingTransactionActions paramPendingTransactionActions);
  
  void scheduleTransaction(ClientTransaction paramClientTransaction) {
    paramClientTransaction.preExecute(this);
    sendMessage(159, paramClientTransaction);
  }
  
  abstract void sendMessage(int paramInt, Object paramObject);
  
  public abstract void updatePendingActivityConfiguration(IBinder paramIBinder, Configuration paramConfiguration);
  
  public abstract void updatePendingConfiguration(Configuration paramConfiguration);
  
  public abstract void updateProcessState(int paramInt, boolean paramBoolean);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ClientTransactionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */