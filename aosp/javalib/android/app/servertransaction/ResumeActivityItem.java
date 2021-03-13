package android.app.servertransaction;

import android.app.ActivityTaskManager;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Trace;

public class ResumeActivityItem extends ActivityLifecycleItem {
  public static final Parcelable.Creator<ResumeActivityItem> CREATOR = new Parcelable.Creator<ResumeActivityItem>() {
      public ResumeActivityItem createFromParcel(Parcel param1Parcel) {
        return new ResumeActivityItem(param1Parcel);
      }
      
      public ResumeActivityItem[] newArray(int param1Int) {
        return new ResumeActivityItem[param1Int];
      }
    };
  
  private static final String TAG = "ResumeActivityItem";
  
  private boolean mIsForward;
  
  private int mProcState;
  
  private boolean mUpdateProcState;
  
  private ResumeActivityItem() {}
  
  private ResumeActivityItem(Parcel paramParcel) {
    this.mProcState = paramParcel.readInt();
    this.mUpdateProcState = paramParcel.readBoolean();
    this.mIsForward = paramParcel.readBoolean();
  }
  
  public static ResumeActivityItem obtain(int paramInt, boolean paramBoolean) {
    ResumeActivityItem resumeActivityItem1 = ObjectPool.<ResumeActivityItem>obtain(ResumeActivityItem.class);
    ResumeActivityItem resumeActivityItem2 = resumeActivityItem1;
    if (resumeActivityItem1 == null)
      resumeActivityItem2 = new ResumeActivityItem(); 
    resumeActivityItem2.mProcState = paramInt;
    resumeActivityItem2.mUpdateProcState = true;
    resumeActivityItem2.mIsForward = paramBoolean;
    return resumeActivityItem2;
  }
  
  public static ResumeActivityItem obtain(boolean paramBoolean) {
    ResumeActivityItem resumeActivityItem1 = ObjectPool.<ResumeActivityItem>obtain(ResumeActivityItem.class);
    ResumeActivityItem resumeActivityItem2 = resumeActivityItem1;
    if (resumeActivityItem1 == null)
      resumeActivityItem2 = new ResumeActivityItem(); 
    resumeActivityItem2.mProcState = -1;
    resumeActivityItem2.mUpdateProcState = false;
    resumeActivityItem2.mIsForward = paramBoolean;
    return resumeActivityItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mProcState != ((ResumeActivityItem)paramObject).mProcState || this.mUpdateProcState != ((ResumeActivityItem)paramObject).mUpdateProcState || this.mIsForward != ((ResumeActivityItem)paramObject).mIsForward)
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityResume");
    paramClientTransactionHandler.handleResumeActivity(paramIBinder, true, this.mIsForward, "RESUME_ACTIVITY");
    Trace.traceEnd(64L);
  }
  
  public int getTargetState() {
    return 3;
  }
  
  public int hashCode() {
    return ((17 * 31 + this.mProcState) * 31 + this.mUpdateProcState) * 31 + this.mIsForward;
  }
  
  public void postExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    try {
      ActivityTaskManager.getService().activityResumed(paramIBinder);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void preExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder) {
    if (this.mUpdateProcState)
      paramClientTransactionHandler.updateProcessState(this.mProcState, false); 
  }
  
  public void recycle() {
    super.recycle();
    this.mProcState = -1;
    this.mUpdateProcState = false;
    this.mIsForward = false;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ResumeActivityItem{procState=");
    stringBuilder.append(this.mProcState);
    stringBuilder.append(",updateProcState=");
    stringBuilder.append(this.mUpdateProcState);
    stringBuilder.append(",isForward=");
    stringBuilder.append(this.mIsForward);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mProcState);
    paramParcel.writeBoolean(this.mUpdateProcState);
    paramParcel.writeBoolean(this.mIsForward);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ResumeActivityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */