package android.app.servertransaction;

import android.app.ActivityTaskManager;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Trace;

public class PauseActivityItem extends ActivityLifecycleItem {
  public static final Parcelable.Creator<PauseActivityItem> CREATOR = new Parcelable.Creator<PauseActivityItem>() {
      public PauseActivityItem createFromParcel(Parcel param1Parcel) {
        return new PauseActivityItem(param1Parcel);
      }
      
      public PauseActivityItem[] newArray(int param1Int) {
        return new PauseActivityItem[param1Int];
      }
    };
  
  private static final String TAG = "PauseActivityItem";
  
  private int mConfigChanges;
  
  private boolean mDontReport;
  
  private boolean mFinished;
  
  private boolean mUserLeaving;
  
  private PauseActivityItem() {}
  
  private PauseActivityItem(Parcel paramParcel) {
    this.mFinished = paramParcel.readBoolean();
    this.mUserLeaving = paramParcel.readBoolean();
    this.mConfigChanges = paramParcel.readInt();
    this.mDontReport = paramParcel.readBoolean();
  }
  
  public static PauseActivityItem obtain() {
    PauseActivityItem pauseActivityItem1 = ObjectPool.<PauseActivityItem>obtain(PauseActivityItem.class);
    PauseActivityItem pauseActivityItem2 = pauseActivityItem1;
    if (pauseActivityItem1 == null)
      pauseActivityItem2 = new PauseActivityItem(); 
    pauseActivityItem2.mFinished = false;
    pauseActivityItem2.mUserLeaving = false;
    pauseActivityItem2.mConfigChanges = 0;
    pauseActivityItem2.mDontReport = true;
    return pauseActivityItem2;
  }
  
  public static PauseActivityItem obtain(boolean paramBoolean1, boolean paramBoolean2, int paramInt, boolean paramBoolean3) {
    PauseActivityItem pauseActivityItem1 = ObjectPool.<PauseActivityItem>obtain(PauseActivityItem.class);
    PauseActivityItem pauseActivityItem2 = pauseActivityItem1;
    if (pauseActivityItem1 == null)
      pauseActivityItem2 = new PauseActivityItem(); 
    pauseActivityItem2.mFinished = paramBoolean1;
    pauseActivityItem2.mUserLeaving = paramBoolean2;
    pauseActivityItem2.mConfigChanges = paramInt;
    pauseActivityItem2.mDontReport = paramBoolean3;
    return pauseActivityItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mFinished != ((PauseActivityItem)paramObject).mFinished || this.mUserLeaving != ((PauseActivityItem)paramObject).mUserLeaving || this.mConfigChanges != ((PauseActivityItem)paramObject).mConfigChanges || this.mDontReport != ((PauseActivityItem)paramObject).mDontReport)
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityPause");
    paramClientTransactionHandler.handlePauseActivity(paramIBinder, this.mFinished, this.mUserLeaving, this.mConfigChanges, paramPendingTransactionActions, "PAUSE_ACTIVITY_ITEM");
    Trace.traceEnd(64L);
  }
  
  public int getTargetState() {
    return 4;
  }
  
  public int hashCode() {
    return (((17 * 31 + this.mFinished) * 31 + this.mUserLeaving) * 31 + this.mConfigChanges) * 31 + this.mDontReport;
  }
  
  public void postExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    if (this.mDontReport)
      return; 
    try {
      ActivityTaskManager.getService().activityPaused(paramIBinder);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void recycle() {
    super.recycle();
    this.mFinished = false;
    this.mUserLeaving = false;
    this.mConfigChanges = 0;
    this.mDontReport = false;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PauseActivityItem{finished=");
    stringBuilder.append(this.mFinished);
    stringBuilder.append(",userLeaving=");
    stringBuilder.append(this.mUserLeaving);
    stringBuilder.append(",configChanges=");
    stringBuilder.append(this.mConfigChanges);
    stringBuilder.append(",dontReport=");
    stringBuilder.append(this.mDontReport);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBoolean(this.mFinished);
    paramParcel.writeBoolean(this.mUserLeaving);
    paramParcel.writeInt(this.mConfigChanges);
    paramParcel.writeBoolean(this.mDontReport);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/PauseActivityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */