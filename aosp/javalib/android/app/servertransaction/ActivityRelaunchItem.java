package android.app.servertransaction;

import android.app.ActivityThread;
import android.app.ClientTransactionHandler;
import android.app.ResultInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import android.util.MergedConfiguration;
import com.android.internal.content.ReferrerIntent;
import java.util.List;
import java.util.Objects;

public class ActivityRelaunchItem extends ClientTransactionItem {
  public static final Parcelable.Creator<ActivityRelaunchItem> CREATOR = new Parcelable.Creator<ActivityRelaunchItem>() {
      public ActivityRelaunchItem createFromParcel(Parcel param1Parcel) {
        return new ActivityRelaunchItem(param1Parcel);
      }
      
      public ActivityRelaunchItem[] newArray(int param1Int) {
        return new ActivityRelaunchItem[param1Int];
      }
    };
  
  private static final String TAG = "ActivityRelaunchItem";
  
  private ActivityThread.ActivityClientRecord mActivityClientRecord;
  
  private MergedConfiguration mConfig;
  
  private int mConfigChanges;
  
  private List<ReferrerIntent> mPendingNewIntents;
  
  private List<ResultInfo> mPendingResults;
  
  private boolean mPreserveWindow;
  
  private ActivityRelaunchItem() {}
  
  private ActivityRelaunchItem(Parcel paramParcel) {
    this.mPendingResults = paramParcel.createTypedArrayList(ResultInfo.CREATOR);
    this.mPendingNewIntents = paramParcel.createTypedArrayList(ReferrerIntent.CREATOR);
    this.mConfigChanges = paramParcel.readInt();
    this.mConfig = (MergedConfiguration)paramParcel.readTypedObject(MergedConfiguration.CREATOR);
    this.mPreserveWindow = paramParcel.readBoolean();
  }
  
  public static ActivityRelaunchItem obtain(List<ResultInfo> paramList, List<ReferrerIntent> paramList1, int paramInt, MergedConfiguration paramMergedConfiguration, boolean paramBoolean) {
    ActivityRelaunchItem activityRelaunchItem1 = ObjectPool.<ActivityRelaunchItem>obtain(ActivityRelaunchItem.class);
    ActivityRelaunchItem activityRelaunchItem2 = activityRelaunchItem1;
    if (activityRelaunchItem1 == null)
      activityRelaunchItem2 = new ActivityRelaunchItem(); 
    activityRelaunchItem2.mPendingResults = paramList;
    activityRelaunchItem2.mPendingNewIntents = paramList1;
    activityRelaunchItem2.mConfigChanges = paramInt;
    activityRelaunchItem2.mConfig = paramMergedConfiguration;
    activityRelaunchItem2.mPreserveWindow = paramBoolean;
    return activityRelaunchItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.mPendingResults, ((ActivityRelaunchItem)paramObject).mPendingResults) || !Objects.equals(this.mPendingNewIntents, ((ActivityRelaunchItem)paramObject).mPendingNewIntents) || this.mConfigChanges != ((ActivityRelaunchItem)paramObject).mConfigChanges || !Objects.equals(this.mConfig, ((ActivityRelaunchItem)paramObject).mConfig) || this.mPreserveWindow != ((ActivityRelaunchItem)paramObject).mPreserveWindow)
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    if (this.mActivityClientRecord == null)
      return; 
    Trace.traceBegin(64L, "activityRestart");
    paramClientTransactionHandler.handleRelaunchActivity(this.mActivityClientRecord, paramPendingTransactionActions);
    Trace.traceEnd(64L);
  }
  
  public int hashCode() {
    return ((((17 * 31 + Objects.hashCode(this.mPendingResults)) * 31 + Objects.hashCode(this.mPendingNewIntents)) * 31 + this.mConfigChanges) * 31 + Objects.hashCode(this.mConfig)) * 31 + this.mPreserveWindow;
  }
  
  public void postExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    paramClientTransactionHandler.reportRelaunch(paramIBinder, paramPendingTransactionActions);
  }
  
  public void preExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder) {
    this.mActivityClientRecord = paramClientTransactionHandler.prepareRelaunchActivity(paramIBinder, this.mPendingResults, this.mPendingNewIntents, this.mConfigChanges, this.mConfig, this.mPreserveWindow);
  }
  
  public void recycle() {
    this.mPendingResults = null;
    this.mPendingNewIntents = null;
    this.mConfigChanges = 0;
    this.mConfig = null;
    this.mPreserveWindow = false;
    this.mActivityClientRecord = null;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ActivityRelaunchItem{pendingResults=");
    stringBuilder.append(this.mPendingResults);
    stringBuilder.append(",pendingNewIntents=");
    stringBuilder.append(this.mPendingNewIntents);
    stringBuilder.append(",configChanges=");
    stringBuilder.append(this.mConfigChanges);
    stringBuilder.append(",config=");
    stringBuilder.append(this.mConfig);
    stringBuilder.append(",preserveWindow");
    stringBuilder.append(this.mPreserveWindow);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedList(this.mPendingResults, paramInt);
    paramParcel.writeTypedList(this.mPendingNewIntents, paramInt);
    paramParcel.writeInt(this.mConfigChanges);
    paramParcel.writeTypedObject((Parcelable)this.mConfig, paramInt);
    paramParcel.writeBoolean(this.mPreserveWindow);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ActivityRelaunchItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */