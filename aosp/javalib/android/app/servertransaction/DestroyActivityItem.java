package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;

public class DestroyActivityItem extends ActivityLifecycleItem {
  public static final Parcelable.Creator<DestroyActivityItem> CREATOR = new Parcelable.Creator<DestroyActivityItem>() {
      public DestroyActivityItem createFromParcel(Parcel param1Parcel) {
        return new DestroyActivityItem(param1Parcel);
      }
      
      public DestroyActivityItem[] newArray(int param1Int) {
        return new DestroyActivityItem[param1Int];
      }
    };
  
  private int mConfigChanges;
  
  private boolean mFinished;
  
  private DestroyActivityItem() {}
  
  private DestroyActivityItem(Parcel paramParcel) {
    this.mFinished = paramParcel.readBoolean();
    this.mConfigChanges = paramParcel.readInt();
  }
  
  public static DestroyActivityItem obtain(boolean paramBoolean, int paramInt) {
    DestroyActivityItem destroyActivityItem1 = ObjectPool.<DestroyActivityItem>obtain(DestroyActivityItem.class);
    DestroyActivityItem destroyActivityItem2 = destroyActivityItem1;
    if (destroyActivityItem1 == null)
      destroyActivityItem2 = new DestroyActivityItem(); 
    destroyActivityItem2.mFinished = paramBoolean;
    destroyActivityItem2.mConfigChanges = paramInt;
    return destroyActivityItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mFinished != ((DestroyActivityItem)paramObject).mFinished || this.mConfigChanges != ((DestroyActivityItem)paramObject).mConfigChanges)
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityDestroy");
    paramClientTransactionHandler.handleDestroyActivity(paramIBinder, this.mFinished, this.mConfigChanges, false, "DestroyActivityItem");
    Trace.traceEnd(64L);
  }
  
  public int getTargetState() {
    return 6;
  }
  
  public int hashCode() {
    return (17 * 31 + this.mFinished) * 31 + this.mConfigChanges;
  }
  
  public void preExecute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder) {
    paramClientTransactionHandler.getActivitiesToBeDestroyed().put(paramIBinder, this);
  }
  
  public void recycle() {
    super.recycle();
    this.mFinished = false;
    this.mConfigChanges = 0;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DestroyActivityItem{finished=");
    stringBuilder.append(this.mFinished);
    stringBuilder.append(",mConfigChanges=");
    stringBuilder.append(this.mConfigChanges);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBoolean(this.mFinished);
    paramParcel.writeInt(this.mConfigChanges);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/DestroyActivityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */