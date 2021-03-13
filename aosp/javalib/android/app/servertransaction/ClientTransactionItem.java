package android.app.servertransaction;

import android.os.Parcelable;

public abstract class ClientTransactionItem implements BaseClientRequest, Parcelable {
  public int describeContents() {
    return 0;
  }
  
  public int getPostExecutionState() {
    return -1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ClientTransactionItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */