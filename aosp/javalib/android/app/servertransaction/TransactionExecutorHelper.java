package android.app.servertransaction;

import android.app.Activity;
import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.util.IntArray;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

public class TransactionExecutorHelper {
  private static final int DESTRUCTION_PENALTY = 10;
  
  private static final int[] ON_RESUME_PRE_EXCUTION_STATES = new int[] { 2, 4 };
  
  private IntArray mLifecycleSequence = new IntArray(6);
  
  private static Activity getActivityForToken(IBinder paramIBinder, ClientTransactionHandler paramClientTransactionHandler) {
    return (paramIBinder == null) ? null : paramClientTransactionHandler.getActivity(paramIBinder);
  }
  
  static String getActivityName(IBinder paramIBinder, ClientTransactionHandler paramClientTransactionHandler) {
    Activity activity = getActivityForToken(paramIBinder, paramClientTransactionHandler);
    if (activity != null)
      return activity.getComponentName().getClassName(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Not found for token: ");
    stringBuilder.append(paramIBinder);
    return stringBuilder.toString();
  }
  
  public static ActivityLifecycleItem getLifecycleRequestForCurrentState(ActivityThread.ActivityClientRecord paramActivityClientRecord) {
    PauseActivityItem pauseActivityItem;
    int i = paramActivityClientRecord.getLifecycleState();
    if (i != 4) {
      if (i != 5) {
        ResumeActivityItem resumeActivityItem = ResumeActivityItem.obtain(false);
      } else {
        StopActivityItem stopActivityItem = StopActivityItem.obtain(0);
      } 
    } else {
      pauseActivityItem = PauseActivityItem.obtain();
    } 
    return pauseActivityItem;
  }
  
  static String getShortActivityName(IBinder paramIBinder, ClientTransactionHandler paramClientTransactionHandler) {
    Activity activity = getActivityForToken(paramIBinder, paramClientTransactionHandler);
    if (activity != null)
      return activity.getComponentName().getShortClassName(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Not found for token: ");
    stringBuilder.append(paramIBinder);
    return stringBuilder.toString();
  }
  
  static String getStateName(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected lifecycle state: ");
        stringBuilder.append(paramInt);
        throw new IllegalArgumentException(stringBuilder.toString());
      case 7:
        return "ON_RESTART";
      case 6:
        return "ON_DESTROY";
      case 5:
        return "ON_STOP";
      case 4:
        return "ON_PAUSE";
      case 3:
        return "ON_RESUME";
      case 2:
        return "ON_START";
      case 1:
        return "ON_CREATE";
      case 0:
        return "PRE_ON_CREATE";
      case -1:
        break;
    } 
    return "UNDEFINED";
  }
  
  static int lastCallbackRequestingState(ClientTransaction paramClientTransaction) {
    List<ClientTransactionItem> list = paramClientTransaction.getCallbacks();
    if (list == null || list.size() == 0)
      return -1; 
    int i = -1;
    int j = -1;
    int k = list.size() - 1;
    while (k >= 0) {
      int m = ((ClientTransactionItem)list.get(k)).getPostExecutionState();
      int n = i;
      int i1 = j;
      if (m != -1)
        if (i == -1 || i == m) {
          n = m;
          i1 = k;
        } else {
          break;
        }  
      k--;
      i = n;
      j = i1;
    } 
    return j;
  }
  
  private static boolean pathInvolvesDestruction(IntArray paramIntArray) {
    int i = paramIntArray.size();
    for (byte b = 0; b < i; b++) {
      if (paramIntArray.get(b) == 6)
        return true; 
    } 
    return false;
  }
  
  static String tId(ClientTransaction paramClientTransaction) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("tId:");
    stringBuilder.append(paramClientTransaction.hashCode());
    stringBuilder.append(" ");
    return stringBuilder.toString();
  }
  
  static String transactionToString(ClientTransaction paramClientTransaction, ClientTransactionHandler paramClientTransactionHandler) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    String str = tId(paramClientTransaction);
    paramClientTransaction.dump(str, printWriter);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str);
    stringBuilder.append("Target activity: ");
    printWriter.append(stringBuilder.toString()).println(getActivityName(paramClientTransaction.getActivityToken(), paramClientTransactionHandler));
    return stringWriter.toString();
  }
  
  public int getClosestOfStates(ActivityThread.ActivityClientRecord paramActivityClientRecord, int[] paramArrayOfint) {
    if (paramArrayOfint == null || paramArrayOfint.length == 0)
      return -1; 
    int i = paramActivityClientRecord.getLifecycleState();
    int j = -1;
    byte b = 0;
    int k;
    for (k = Integer.MAX_VALUE; b < paramArrayOfint.length; k = m) {
      getLifecyclePath(i, paramArrayOfint[b], false);
      int m = this.mLifecycleSequence.size();
      int n = m;
      if (pathInvolvesDestruction(this.mLifecycleSequence))
        n = m + 10; 
      m = k;
      if (k > n) {
        j = paramArrayOfint[b];
        m = n;
      } 
      b++;
    } 
    return j;
  }
  
  public int getClosestPreExecutionState(ActivityThread.ActivityClientRecord paramActivityClientRecord, int paramInt) {
    if (paramInt != -1) {
      if (paramInt == 3)
        return getClosestOfStates(paramActivityClientRecord, ON_RESUME_PRE_EXCUTION_STATES); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Pre-execution states for state: ");
      stringBuilder.append(paramInt);
      stringBuilder.append(" is not supported.");
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
    return -1;
  }
  
  public IntArray getLifecyclePath(int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramInt1 != -1 && paramInt2 != -1) {
      if (paramInt1 != 7 && paramInt2 != 7) {
        if (paramInt2 != 0 || paramInt1 == paramInt2) {
          this.mLifecycleSequence.clear();
          if (paramInt2 >= paramInt1) {
            if (paramInt1 == 2 && paramInt2 == 5) {
              this.mLifecycleSequence.add(5);
            } else {
              while (++paramInt1 <= paramInt2) {
                this.mLifecycleSequence.add(paramInt1);
                paramInt1++;
              } 
            } 
          } else if (paramInt1 == 4 && paramInt2 == 3) {
            this.mLifecycleSequence.add(3);
          } else if (paramInt1 <= 5 && paramInt2 >= 2) {
            while (++paramInt1 <= 5) {
              this.mLifecycleSequence.add(paramInt1);
              paramInt1++;
            } 
            this.mLifecycleSequence.add(7);
            for (paramInt1 = 2; paramInt1 <= paramInt2; paramInt1++)
              this.mLifecycleSequence.add(paramInt1); 
          } else {
            while (++paramInt1 <= 6) {
              this.mLifecycleSequence.add(paramInt1);
              paramInt1++;
            } 
            for (paramInt1 = 1; paramInt1 <= paramInt2; paramInt1++)
              this.mLifecycleSequence.add(paramInt1); 
          } 
          if (paramBoolean && this.mLifecycleSequence.size() != 0) {
            IntArray intArray = this.mLifecycleSequence;
            intArray.remove(intArray.size() - 1);
          } 
          return this.mLifecycleSequence;
        } 
        throw new IllegalArgumentException("Can only start in pre-onCreate state");
      } 
      throw new IllegalArgumentException("Can't start or finish in intermittent RESTART state");
    } 
    throw new IllegalArgumentException("Can't resolve lifecycle path for undefined state");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/TransactionExecutorHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */