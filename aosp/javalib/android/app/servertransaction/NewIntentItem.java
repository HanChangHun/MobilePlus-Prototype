package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Trace;
import com.android.internal.content.ReferrerIntent;
import java.util.List;
import java.util.Objects;

public class NewIntentItem extends ClientTransactionItem {
  public static final Parcelable.Creator<NewIntentItem> CREATOR = new Parcelable.Creator<NewIntentItem>() {
      public NewIntentItem createFromParcel(Parcel param1Parcel) {
        return new NewIntentItem(param1Parcel);
      }
      
      public NewIntentItem[] newArray(int param1Int) {
        return new NewIntentItem[param1Int];
      }
    };
  
  private List<ReferrerIntent> mIntents;
  
  private boolean mResume;
  
  private NewIntentItem() {}
  
  private NewIntentItem(Parcel paramParcel) {
    this.mResume = paramParcel.readBoolean();
    this.mIntents = paramParcel.createTypedArrayList(ReferrerIntent.CREATOR);
  }
  
  public static NewIntentItem obtain(List<ReferrerIntent> paramList, boolean paramBoolean) {
    NewIntentItem newIntentItem1 = ObjectPool.<NewIntentItem>obtain(NewIntentItem.class);
    NewIntentItem newIntentItem2 = newIntentItem1;
    if (newIntentItem1 == null)
      newIntentItem2 = new NewIntentItem(); 
    newIntentItem2.mIntents = paramList;
    newIntentItem2.mResume = paramBoolean;
    return newIntentItem2;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mResume != ((NewIntentItem)paramObject).mResume || !Objects.equals(this.mIntents, ((NewIntentItem)paramObject).mIntents))
      bool = false; 
    return bool;
  }
  
  public void execute(ClientTransactionHandler paramClientTransactionHandler, IBinder paramIBinder, PendingTransactionActions paramPendingTransactionActions) {
    Trace.traceBegin(64L, "activityNewIntent");
    paramClientTransactionHandler.handleNewIntent(paramIBinder, this.mIntents);
    Trace.traceEnd(64L);
  }
  
  public int getPostExecutionState() {
    byte b;
    if (this.mResume) {
      b = 3;
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int hashCode() {
    return (17 * 31 + this.mResume) * 31 + this.mIntents.hashCode();
  }
  
  public void recycle() {
    this.mIntents = null;
    this.mResume = false;
    ObjectPool.recycle(this);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NewIntentItem{intents=");
    stringBuilder.append(this.mIntents);
    stringBuilder.append(",resume=");
    stringBuilder.append(this.mResume);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBoolean(this.mResume);
    paramParcel.writeTypedList(this.mIntents, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/NewIntentItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */