package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;

public class StartActivityItem extends ActivityLifecycleItem {
  public static final Parcelable.Creator<StartActivityItem> CREATOR = new Parcelable.Creator<StartActivityItem>() {
      public StartActivityItem createFromParcel(Parcel param1Parcel) {
        return new StartActivityItem(param1Parcel);
      }
      
      public StartActivityItem[] newArray(int param1Int) {
        return new StartActivityItem[param1Int];
      }
    };
  
  private static final String TAG = "StartActivityItem";
  
  private StartActivityItem() {}
  
  private StartActivityItem(Parcel paramParcel) {}
  
  public static StartActivityItem obtain() {
    StartActivityItem startActivityItem1 = ObjectPool.<StartActivityItem>obtain(StartActivityItem.class);
    StartActivityItem startActivityItem2 = startActivityItem1;
    if (startActivityItem1 == null)
      startActivityItem2 = new StartActivityItem(); 
    return startActivityItem2;
  }
  
  public boolean equals(Object paramObject) {
    return (this == paramObject) ? true : (!(paramObject == null || getClass() != paramObject.getClass()));
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "startActivityItem");
    paramClientTransactionHandler.handleStartActivity(paramIBinder, paramPendingTransactionActions);
    Trace.traceEnd(64L);
  }
  
  public int getTargetState() {
    return 2;
  }
  
  public int hashCode() {
    return 17;
  }
  
  public void recycle() {
    super.recycle();
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    return "StartActivityItem{}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/StartActivityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */