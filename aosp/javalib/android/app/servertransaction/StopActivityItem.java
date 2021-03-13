package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;

public class StopActivityItem extends ActivityLifecycleItem {
  public static final Parcelable.Creator<StopActivityItem> CREATOR = new Parcelable.Creator<StopActivityItem>() {
      public StopActivityItem createFromParcel(Parcel param1Parcel) {
        return new StopActivityItem(param1Parcel);
      }
      
      public StopActivityItem[] newArray(int param1Int) {
        return new StopActivityItem[param1Int];
      }
    };
  
  private static final String TAG = "StopActivityItem";
  
  private int mConfigChanges;
  
  private StopActivityItem() {}
  
  private StopActivityItem(Parcel paramParcel) {
    this.mConfigChanges = paramParcel.readInt();
  }
  
  public static StopActivityItem obtain(int paramInt) {
    StopActivityItem stopActivityItem1 = ObjectPool.<StopActivityItem>obtain(StopActivityItem.class);
    StopActivityItem stopActivityItem2 = stopActivityItem1;
    if (stopActivityItem1 == null)
      stopActivityItem2 = new StopActivityItem(); 
    stopActivityItem2.mConfigChanges = paramInt;
    return stopActivityItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mConfigChanges != ((StopActivityItem)paramObject).mConfigChanges)
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityStop");
    paramClientTransactionHandler.handleStopActivity(paramIBinder, this.mConfigChanges, paramPendingTransactionActions, true, "STOP_ACTIVITY_ITEM");
    Trace.traceEnd(64L);
  }
  
  public int getTargetState() {
    return 5;
  }
  
  public int hashCode() {
    return 17 * 31 + this.mConfigChanges;
  }
  
  public void postExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    paramClientTransactionHandler.reportStop(paramPendingTransactionActions);
  }
  
  public void recycle() {
    super.recycle();
    this.mConfigChanges = 0;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("StopActivityItem{configChanges=");
    stringBuilder.append(this.mConfigChanges);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mConfigChanges);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/StopActivityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */