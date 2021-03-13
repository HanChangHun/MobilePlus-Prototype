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

public class PendingTransactionActions {
  private boolean mCallOnPostCreate;
  
  private Bundle mOldState;
  
  private boolean mReportRelaunchToWM;
  
  private boolean mRestoreInstanceState;
  
  private StopInfo mStopInfo;
  
  public PendingTransactionActions() {
    clear();
  }
  
  public void clear() {
    this.mRestoreInstanceState = false;
    this.mCallOnPostCreate = false;
    this.mOldState = null;
    this.mStopInfo = null;
  }
  
  public Bundle getOldState() {
    return this.mOldState;
  }
  
  public StopInfo getStopInfo() {
    return this.mStopInfo;
  }
  
  public void setCallOnPostCreate(boolean paramBoolean) {
    this.mCallOnPostCreate = paramBoolean;
  }
  
  public void setOldState(Bundle paramBundle) {
    this.mOldState = paramBundle;
  }
  
  public void setReportRelaunchToWindowManager(boolean paramBoolean) {
    this.mReportRelaunchToWM = paramBoolean;
  }
  
  public void setRestoreInstanceState(boolean paramBoolean) {
    this.mRestoreInstanceState = paramBoolean;
  }
  
  public void setStopInfo(StopInfo paramStopInfo) {
    this.mStopInfo = paramStopInfo;
  }
  
  public boolean shouldCallOnPostCreate() {
    return this.mCallOnPostCreate;
  }
  
  public boolean shouldReportRelaunchToWindowManager() {
    return this.mReportRelaunchToWM;
  }
  
  public boolean shouldRestoreInstanceState() {
    return this.mRestoreInstanceState;
  }
  
  public static class StopInfo implements Runnable {
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
    
    public void setActivity(ActivityThread.ActivityClientRecord param1ActivityClientRecord) {
      this.mActivity = param1ActivityClientRecord;
    }
    
    public void setDescription(CharSequence param1CharSequence) {
      this.mDescription = param1CharSequence;
    }
    
    public void setPersistentState(PersistableBundle param1PersistableBundle) {
      this.mPersistentState = param1PersistableBundle;
    }
    
    public void setState(Bundle param1Bundle) {
      this.mState = param1Bundle;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/PendingTransactionActions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */