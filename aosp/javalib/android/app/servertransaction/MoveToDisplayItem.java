package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import java.util.Objects;

public class MoveToDisplayItem extends ClientTransactionItem {
  public static final Parcelable.Creator<MoveToDisplayItem> CREATOR = new Parcelable.Creator<MoveToDisplayItem>() {
      public MoveToDisplayItem createFromParcel(Parcel param1Parcel) {
        return new MoveToDisplayItem(param1Parcel);
      }
      
      public MoveToDisplayItem[] newArray(int param1Int) {
        return new MoveToDisplayItem[param1Int];
      }
    };
  
  private Configuration mConfiguration;
  
  private int mTargetDisplayId;
  
  private MoveToDisplayItem() {}
  
  private MoveToDisplayItem(Parcel paramParcel) {
    this.mTargetDisplayId = paramParcel.readInt();
    this.mConfiguration = (Configuration)paramParcel.readTypedObject(Configuration.CREATOR);
  }
  
  public static MoveToDisplayItem obtain(int paramInt, Configuration paramConfiguration) {
    if (paramConfiguration != null) {
      MoveToDisplayItem moveToDisplayItem1 = ObjectPool.<MoveToDisplayItem>obtain(MoveToDisplayItem.class);
      MoveToDisplayItem moveToDisplayItem2 = moveToDisplayItem1;
      if (moveToDisplayItem1 == null)
        moveToDisplayItem2 = new MoveToDisplayItem(); 
      moveToDisplayItem2.mTargetDisplayId = paramInt;
      moveToDisplayItem2.mConfiguration = paramConfiguration;
      return moveToDisplayItem2;
    } 
    throw new IllegalArgumentException("Configuration must not be null");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mTargetDisplayId != ((MoveToDisplayItem)paramObject).mTargetDisplayId || !Objects.equals(this.mConfiguration, ((MoveToDisplayItem)paramObject).mConfiguration))
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityMovedToDisplay");
    paramClientTransactionHandler.handleActivityConfigurationChanged(paramIBinder, this.mConfiguration, this.mTargetDisplayId);
    Trace.traceEnd(64L);
  }
  
  public int hashCode() {
    return (17 * 31 + this.mTargetDisplayId) * 31 + this.mConfiguration.hashCode();
  }
  
  public void preExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder) {
    paramClientTransactionHandler.updatePendingActivityConfiguration(paramIBinder, this.mConfiguration);
  }
  
  public void recycle() {
    this.mTargetDisplayId = 0;
    this.mConfiguration = Configuration.EMPTY;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("MoveToDisplayItem{targetDisplayId=");
    stringBuilder.append(this.mTargetDisplayId);
    stringBuilder.append(",configuration=");
    stringBuilder.append(this.mConfiguration);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mTargetDisplayId);
    paramParcel.writeTypedObject((Parcelable)this.mConfiguration, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/MoveToDisplayItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */