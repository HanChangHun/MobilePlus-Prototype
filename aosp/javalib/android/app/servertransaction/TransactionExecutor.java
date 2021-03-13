package android.app.servertransaction;

import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.util.IntArray;
import android.util.Slog;
import java.util.List;
import java.util.Map;

public class TransactionExecutor {
  private static final boolean DEBUG_RESOLVER = false;
  
  private static final String TAG = "TransactionExecutor";
  
  private TransactionExecutorHelper mHelper = new TransactionExecutorHelper();
  
  private PendingTransactionActions mPendingActions = new PendingTransactionActions();
  
  private ClientTransactionHandler mTransactionHandler;
  
  public TransactionExecutor(ClientTransactionHandler paramClientTransactionHandler) {
    this.mTransactionHandler = paramClientTransactionHandler;
  }
  
  private void cycleToPath(ActivityThread.ActivityClientRecord paramActivityClientRecord, int paramInt, boolean paramBoolean, ClientTransaction paramClientTransaction) {
    int i = paramActivityClientRecord.getLifecycleState();
    performLifecycleSequence(paramActivityClientRecord, this.mHelper.getLifecyclePath(i, paramInt, paramBoolean), paramClientTransaction);
  }
  
  private void executeLifecycleState(ClientTransaction paramClientTransaction) {
    ActivityLifecycleItem activityLifecycleItem = paramClientTransaction.getLifecycleStateRequest();
    if (activityLifecycleItem == null)
      return; 
    IBinder iBinder = paramClientTransaction.getActivityToken();
    ActivityThread.ActivityClientRecord activityClientRecord = this.mTransactionHandler.getActivityClient(iBinder);
    if (activityClientRecord == null)
      return; 
    cycleToPath(activityClientRecord, activityLifecycleItem.getTargetState(), true, paramClientTransaction);
    activityLifecycleItem.execute(this.mTransactionHandler, iBinder, this.mPendingActions);
    activityLifecycleItem.postExecute(this.mTransactionHandler, iBinder, this.mPendingActions);
  }
  
  private void performLifecycleSequence(ActivityThread.ActivityClientRecord paramActivityClientRecord, IntArray paramIntArray, ClientTransaction paramClientTransaction) {
    int i = paramIntArray.size();
    for (byte b = 0; b < i; b++) {
      StringBuilder stringBuilder1;
      ClientTransactionHandler clientTransactionHandler;
      IBinder iBinder;
      StringBuilder stringBuilder2;
      int j = paramIntArray.get(b);
      switch (j) {
        default:
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Unexpected lifecycle state: ");
          stringBuilder1.append(j);
          throw new IllegalArgumentException(stringBuilder1.toString());
        case 7:
          this.mTransactionHandler.performRestartActivity(((ActivityThread.ActivityClientRecord)stringBuilder1).token, false);
          break;
        case 6:
          clientTransactionHandler = this.mTransactionHandler;
          iBinder = ((ActivityThread.ActivityClientRecord)stringBuilder1).token;
          stringBuilder2 = new StringBuilder();
          stringBuilder2.append("performLifecycleSequence. cycling to:");
          stringBuilder2.append(paramIntArray.get(i - 1));
          clientTransactionHandler.handleDestroyActivity(iBinder, false, 0, false, stringBuilder2.toString());
          break;
        case 5:
          this.mTransactionHandler.handleStopActivity(((ActivityThread.ActivityClientRecord)stringBuilder1).token, 0, this.mPendingActions, false, "LIFECYCLER_STOP_ACTIVITY");
          break;
        case 4:
          this.mTransactionHandler.handlePauseActivity(((ActivityThread.ActivityClientRecord)stringBuilder1).token, false, false, 0, this.mPendingActions, "LIFECYCLER_PAUSE_ACTIVITY");
          break;
        case 3:
          this.mTransactionHandler.handleResumeActivity(((ActivityThread.ActivityClientRecord)stringBuilder1).token, false, ((ActivityThread.ActivityClientRecord)stringBuilder1).isForward, "LIFECYCLER_RESUME_ACTIVITY");
          break;
        case 2:
          this.mTransactionHandler.handleStartActivity(((ActivityThread.ActivityClientRecord)stringBuilder1).token, this.mPendingActions);
          break;
        case 1:
          this.mTransactionHandler.handleLaunchActivity((ActivityThread.ActivityClientRecord)stringBuilder1, this.mPendingActions, null);
          break;
      } 
    } 
  }
  
  public void cycleToPath(ActivityThread.ActivityClientRecord paramActivityClientRecord, int paramInt, ClientTransaction paramClientTransaction) {
    cycleToPath(paramActivityClientRecord, paramInt, false, paramClientTransaction);
  }
  
  public void execute(ClientTransaction paramClientTransaction) {
    IBinder iBinder = paramClientTransaction.getActivityToken();
    if (iBinder != null) {
      Map map = this.mTransactionHandler.getActivitiesToBeDestroyed();
      ClientTransactionItem clientTransactionItem = (ClientTransactionItem)map.get(iBinder);
      if (clientTransactionItem != null) {
        if (paramClientTransaction.getLifecycleStateRequest() == clientTransactionItem)
          map.remove(iBinder); 
        if (this.mTransactionHandler.getActivityClient(iBinder) == null) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(TransactionExecutorHelper.tId(paramClientTransaction));
          stringBuilder.append("Skip pre-destroyed transaction:\n");
          stringBuilder.append(TransactionExecutorHelper.transactionToString(paramClientTransaction, this.mTransactionHandler));
          Slog.w("TransactionExecutor", stringBuilder.toString());
          return;
        } 
      } 
    } 
    executeCallbacks(paramClientTransaction);
    executeLifecycleState(paramClientTransaction);
    this.mPendingActions.clear();
  }
  
  public void executeCallbacks(ClientTransaction paramClientTransaction) {
    byte b;
    List<ClientTransactionItem> list = paramClientTransaction.getCallbacks();
    if (list == null || list.isEmpty())
      return; 
    IBinder iBinder = paramClientTransaction.getActivityToken();
    ActivityThread.ActivityClientRecord activityClientRecord = this.mTransactionHandler.getActivityClient(iBinder);
    ActivityLifecycleItem activityLifecycleItem = paramClientTransaction.getLifecycleStateRequest();
    if (activityLifecycleItem != null) {
      b = activityLifecycleItem.getTargetState();
    } else {
      b = -1;
    } 
    int i = TransactionExecutorHelper.lastCallbackRequestingState(paramClientTransaction);
    int j = list.size();
    byte b1 = 0;
    while (b1 < j) {
      ClientTransactionItem clientTransactionItem = list.get(b1);
      int k = clientTransactionItem.getPostExecutionState();
      int m = this.mHelper.getClosestPreExecutionState(activityClientRecord, clientTransactionItem.getPostExecutionState());
      if (m != -1)
        cycleToPath(activityClientRecord, m, paramClientTransaction); 
      clientTransactionItem.execute(this.mTransactionHandler, iBinder, this.mPendingActions);
      clientTransactionItem.postExecute(this.mTransactionHandler, iBinder, this.mPendingActions);
      ActivityThread.ActivityClientRecord activityClientRecord1 = activityClientRecord;
      if (activityClientRecord == null)
        activityClientRecord1 = this.mTransactionHandler.getActivityClient(iBinder); 
      if (k != -1 && activityClientRecord1 != null) {
        boolean bool;
        if (b1 == i && b == k) {
          bool = true;
        } else {
          bool = false;
        } 
        cycleToPath(activityClientRecord1, k, bool, paramClientTransaction);
      } 
      b1++;
      activityClientRecord = activityClientRecord1;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/TransactionExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */