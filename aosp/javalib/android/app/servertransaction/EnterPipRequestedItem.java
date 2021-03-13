package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class EnterPipRequestedItem extends ClientTransactionItem {
  public static final Parcelable.Creator<EnterPipRequestedItem> CREATOR = new Parcelable.Creator<EnterPipRequestedItem>() {
      public EnterPipRequestedItem createFromParcel(Parcel param1Parcel) {
        return new EnterPipRequestedItem();
      }
      
      public EnterPipRequestedItem[] newArray(int param1Int) {
        return new EnterPipRequestedItem[param1Int];
      }
    };
  
  private EnterPipRequestedItem() {}
  
  public static EnterPipRequestedItem obtain() {
    EnterPipRequestedItem enterPipRequestedItem1 = ObjectPool.<EnterPipRequestedItem>obtain(EnterPipRequestedItem.class);
    EnterPipRequestedItem enterPipRequestedItem2 = enterPipRequestedItem1;
    if (enterPipRequestedItem1 == null)
      enterPipRequestedItem2 = new EnterPipRequestedItem(); 
    return enterPipRequestedItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (this == paramObject) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    paramClientTransactionHandler.handlePictureInPictureRequested(paramIBinder);
  }
  
  public void recycle() {
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    return "EnterPipRequestedItem{}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/EnterPipRequestedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */