package android.app.servertransaction;

import android.app.ActivityTaskManager;
import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Trace;

public class TopResumedActivityChangeItem extends ClientTransactionItem {
  public static final Parcelable.Creator<TopResumedActivityChangeItem> CREATOR = new Parcelable.Creator<TopResumedActivityChangeItem>() {
      public TopResumedActivityChangeItem createFromParcel(Parcel param1Parcel) {
        return new TopResumedActivityChangeItem(param1Parcel);
      }
      
      public TopResumedActivityChangeItem[] newArray(int param1Int) {
        return new TopResumedActivityChangeItem[param1Int];
      }
    };
  
  private boolean mOnTop;
  
  private TopResumedActivityChangeItem() {}
  
  private TopResumedActivityChangeItem(Parcel paramParcel) {
    this.mOnTop = paramParcel.readBoolean();
  }
  
  public static TopResumedActivityChangeItem obtain(boolean paramBoolean) {
    TopResumedActivityChangeItem topResumedActivityChangeItem1 = ObjectPool.<TopResumedActivityChangeItem>obtain(TopResumedActivityChangeItem.class);
    TopResumedActivityChangeItem topResumedActivityChangeItem2 = topResumedActivityChangeItem1;
    if (topResumedActivityChangeItem1 == null)
      topResumedActivityChangeItem2 = new TopResumedActivityChangeItem(); 
    topResumedActivityChangeItem2.mOnTop = paramBoolean;
    return topResumedActivityChangeItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mOnTop != ((TopResumedActivityChangeItem)paramObject).mOnTop)
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "topResumedActivityChangeItem");
    paramClientTransactionHandler.handleTopResumedActivityChanged(paramIBinder, this.mOnTop, "topResumedActivityChangeItem");
    Trace.traceEnd(64L);
  }
  
  public int hashCode() {
    return 17 * 31 + this.mOnTop;
  }
  
  public void postExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    if (this.mOnTop)
      return; 
    try {
      ActivityTaskManager.getService().activityTopResumedStateLost();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void recycle() {
    this.mOnTop = false;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("TopResumedActivityChangeItem{onTop=");
    stringBuilder.append(this.mOnTop);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBoolean(this.mOnTop);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/TopResumedActivityChangeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */