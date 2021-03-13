package android.app.servertransaction;

import android.app.ActivityTaskManager;
import android.app.ActivityThread;
import android.os.BaseBundle;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.LogWriter;
import com.android.internal.util.IndentingPrintWriter;
import java.io.Writer;

public class StopInfo implements Runnable {
  private static final String TAG = "ActivityStopInfo";
  
  private ActivityThread.ActivityClientRecord mActivity;
  
  private CharSequence mDescription;
  
  private PersistableBundle mPersistentState;
  
  private Bundle mState;
  
  public void run() {
    try {
      ActivityTaskManager.getService().activityStopped(this.mActivity.token, this.mState, this.mPersistentState, this.mDescription);
      return;
    } catch (RemoteException remoteException) {
      IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter((Writer)new LogWriter(5, "ActivityStopInfo"), "  ");
      indentingPrintWriter.println("Bundle stats:");
      Bundle.dumpStats(indentingPrintWriter, (BaseBundle)this.mState);
      indentingPrintWriter.println("PersistableBundle stats:");
      Bundle.dumpStats(indentingPrintWriter, (BaseBundle)this.mPersistentState);
      if (remoteException instanceof android.os.TransactionTooLargeException && this.mActivity.packageInfo.getTargetSdkVersion() < 24) {
        Log.e("ActivityStopInfo", "App sent too much data in instance state, so it was ignored", (Throwable)remoteException);
        return;
      } 
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setActivity(ActivityThread.ActivityClientRecord paramActivityClientRecord) {
    this.mActivity = paramActivityClientRecord;
  }
  
  public void setDescription(CharSequence paramCharSequence) {
    this.mDescription = paramCharSequence;
  }
  
  public void setPersistentState(PersistableBundle paramPersistableBundle) {
    this.mPersistentState = paramPersistableBundle;
  }
  
  public void setState(Bundle paramBundle) {
    this.mState = paramBundle;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/PendingTransactionActions$StopInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */