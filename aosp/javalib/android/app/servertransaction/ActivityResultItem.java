package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.app.ResultInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import java.util.List;
import java.util.Objects;

public class ActivityResultItem extends ClientTransactionItem {
  public static final Parcelable.Creator<ActivityResultItem> CREATOR = new Parcelable.Creator<ActivityResultItem>() {
      public ActivityResultItem createFromParcel(Parcel param1Parcel) {
        return new ActivityResultItem(param1Parcel);
      }
      
      public ActivityResultItem[] newArray(int param1Int) {
        return new ActivityResultItem[param1Int];
      }
    };
  
  private List<ResultInfo> mResultInfoList;
  
  private ActivityResultItem() {}
  
  private ActivityResultItem(Parcel paramParcel) {
    this.mResultInfoList = paramParcel.createTypedArrayList(ResultInfo.CREATOR);
  }
  
  public static ActivityResultItem obtain(List<ResultInfo> paramList) {
    ActivityResultItem activityResultItem1 = ObjectPool.<ActivityResultItem>obtain(ActivityResultItem.class);
    ActivityResultItem activityResultItem2 = activityResultItem1;
    if (activityResultItem1 == null)
      activityResultItem2 = new ActivityResultItem(); 
    activityResultItem2.mResultInfoList = paramList;
    return activityResultItem2;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return Objects.equals(this.mResultInfoList, ((ActivityResultItem)paramObject).mResultInfoList);
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityDeliverResult");
    paramClientTransactionHandler.handleSendResult(paramIBinder, this.mResultInfoList, "ACTIVITY_RESULT");
    Trace.traceEnd(64L);
  }
  
  public int hashCode() {
    return this.mResultInfoList.hashCode();
  }
  
  public void recycle() {
    this.mResultInfoList = null;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ActivityResultItem{resultInfoList=");
    stringBuilder.append(this.mResultInfoList);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedList(this.mResultInfoList, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ActivityResultItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */