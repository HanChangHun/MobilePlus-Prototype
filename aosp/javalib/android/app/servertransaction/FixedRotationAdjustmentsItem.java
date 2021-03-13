package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.DisplayAdjustments;
import java.util.Objects;

public class FixedRotationAdjustmentsItem extends ClientTransactionItem {
  public static final Parcelable.Creator<FixedRotationAdjustmentsItem> CREATOR = new Parcelable.Creator<FixedRotationAdjustmentsItem>() {
      public FixedRotationAdjustmentsItem createFromParcel(Parcel param1Parcel) {
        return new FixedRotationAdjustmentsItem(param1Parcel);
      }
      
      public FixedRotationAdjustmentsItem[] newArray(int param1Int) {
        return new FixedRotationAdjustmentsItem[param1Int];
      }
    };
  
  private DisplayAdjustments.FixedRotationAdjustments mFixedRotationAdjustments;
  
  private IBinder mToken;
  
  private FixedRotationAdjustmentsItem() {}
  
  private FixedRotationAdjustmentsItem(Parcel paramParcel) {
    this.mToken = paramParcel.readStrongBinder();
    this.mFixedRotationAdjustments = (DisplayAdjustments.FixedRotationAdjustments)paramParcel.readTypedObject(DisplayAdjustments.FixedRotationAdjustments.CREATOR);
  }
  
  public static FixedRotationAdjustmentsItem obtain(IBinder paramIBinder, DisplayAdjustments.FixedRotationAdjustments paramFixedRotationAdjustments) {
    FixedRotationAdjustmentsItem fixedRotationAdjustmentsItem1 = ObjectPool.<FixedRotationAdjustmentsItem>obtain(FixedRotationAdjustmentsItem.class);
    FixedRotationAdjustmentsItem fixedRotationAdjustmentsItem2 = fixedRotationAdjustmentsItem1;
    if (fixedRotationAdjustmentsItem1 == null)
      fixedRotationAdjustmentsItem2 = new FixedRotationAdjustmentsItem(); 
    fixedRotationAdjustmentsItem2.mToken = paramIBinder;
    fixedRotationAdjustmentsItem2.mFixedRotationAdjustments = paramFixedRotationAdjustments;
    return fixedRotationAdjustmentsItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.mToken, ((FixedRotationAdjustmentsItem)paramObject).mToken) || !Objects.equals(this.mFixedRotationAdjustments, ((FixedRotationAdjustmentsItem)paramObject).mFixedRotationAdjustments))
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    paramClientTransactionHandler.handleFixedRotationAdjustments(this.mToken, this.mFixedRotationAdjustments);
  }
  
  public int hashCode() {
    return (17 * 31 + Objects.hashCode(this.mToken)) * 31 + Objects.hashCode(this.mFixedRotationAdjustments);
  }
  
  public void recycle() {
    this.mToken = null;
    this.mFixedRotationAdjustments = null;
    ObjectPool.recycle(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStrongBinder(this.mToken);
    paramParcel.writeTypedObject((Parcelable)this.mFixedRotationAdjustments, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/FixedRotationAdjustmentsItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */