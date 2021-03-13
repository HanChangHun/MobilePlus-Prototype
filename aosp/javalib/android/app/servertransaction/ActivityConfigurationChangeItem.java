package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import java.util.Objects;

public class ActivityConfigurationChangeItem extends ClientTransactionItem {
  public static final Parcelable.Creator<ActivityConfigurationChangeItem> CREATOR = new Parcelable.Creator<ActivityConfigurationChangeItem>() {
      public ActivityConfigurationChangeItem createFromParcel(Parcel param1Parcel) {
        return new ActivityConfigurationChangeItem(param1Parcel);
      }
      
      public ActivityConfigurationChangeItem[] newArray(int param1Int) {
        return new ActivityConfigurationChangeItem[param1Int];
      }
    };
  
  private Configuration mConfiguration;
  
  private ActivityConfigurationChangeItem() {}
  
  private ActivityConfigurationChangeItem(Parcel paramParcel) {
    this.mConfiguration = (Configuration)paramParcel.readTypedObject(Configuration.CREATOR);
  }
  
  public static ActivityConfigurationChangeItem obtain(Configuration paramConfiguration) {
    if (paramConfiguration != null) {
      ActivityConfigurationChangeItem activityConfigurationChangeItem1 = ObjectPool.<ActivityConfigurationChangeItem>obtain(ActivityConfigurationChangeItem.class);
      ActivityConfigurationChangeItem activityConfigurationChangeItem2 = activityConfigurationChangeItem1;
      if (activityConfigurationChangeItem1 == null)
        activityConfigurationChangeItem2 = new ActivityConfigurationChangeItem(); 
      activityConfigurationChangeItem2.mConfiguration = paramConfiguration;
      return activityConfigurationChangeItem2;
    } 
    throw new IllegalArgumentException("Config must not be null.");
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return Objects.equals(this.mConfiguration, ((ActivityConfigurationChangeItem)paramObject).mConfiguration);
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityConfigChanged");
    paramClientTransactionHandler.handleActivityConfigurationChanged(paramIBinder, this.mConfiguration, -1);
    Trace.traceEnd(64L);
  }
  
  public int hashCode() {
    return this.mConfiguration.hashCode();
  }
  
  public void preExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder) {
    paramClientTransactionHandler.updatePendingActivityConfiguration(paramIBinder, this.mConfiguration);
  }
  
  public void recycle() {
    this.mConfiguration = Configuration.EMPTY;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ActivityConfigurationChange{config=");
    stringBuilder.append(this.mConfiguration);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedObject((Parcelable)this.mConfiguration, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ActivityConfigurationChangeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */