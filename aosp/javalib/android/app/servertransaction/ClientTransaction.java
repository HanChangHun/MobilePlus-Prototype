package android.app.servertransaction;

import android.app.ClientTransactionHandler;
import android.app.IApplicationThread;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientTransaction implements Parcelable, ObjectPoolItem {
  public static final Parcelable.Creator<ClientTransaction> CREATOR = new Parcelable.Creator<ClientTransaction>() {
      public ClientTransaction createFromParcel(Parcel param1Parcel) {
        return new ClientTransaction(param1Parcel);
      }
      
      public ClientTransaction[] newArray(int param1Int) {
        return new ClientTransaction[param1Int];
      }
    };
  
  private List<ClientTransactionItem> mActivityCallbacks;
  
  private IBinder mActivityToken;
  
  private IApplicationThread mClient;
  
  private ActivityLifecycleItem mLifecycleStateRequest;
  
  private ClientTransaction() {}
  
  private ClientTransaction(Parcel paramParcel) {
    this.mClient = (IApplicationThread)paramParcel.readStrongBinder();
    if (paramParcel.readBoolean())
      this.mActivityToken = paramParcel.readStrongBinder(); 
    this.mLifecycleStateRequest = (ActivityLifecycleItem)paramParcel.readParcelable(getClass().getClassLoader());
    if (paramParcel.readBoolean()) {
      ArrayList<ClientTransactionItem> arrayList = new ArrayList();
      this.mActivityCallbacks = arrayList;
      paramParcel.readParcelableList(arrayList, getClass().getClassLoader());
    } 
  }
  
  public static ClientTransaction obtain(IApplicationThread paramIApplicationThread, IBinder paramIBinder) {
    ClientTransaction clientTransaction1 = ObjectPool.<ClientTransaction>obtain(ClientTransaction.class);
    ClientTransaction clientTransaction2 = clientTransaction1;
    if (clientTransaction1 == null)
      clientTransaction2 = new ClientTransaction(); 
    clientTransaction2.mClient = paramIApplicationThread;
    clientTransaction2.mActivityToken = paramIBinder;
    return clientTransaction2;
  }
  
  public void addCallback(ClientTransactionItem paramClientTransactionItem) {
    if (this.mActivityCallbacks == null)
      this.mActivityCallbacks = new ArrayList<>(); 
    this.mActivityCallbacks.add(paramClientTransactionItem);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(String paramString, PrintWriter paramPrintWriter) {
    byte b;
    paramPrintWriter.append(paramString).println("ClientTransaction{");
    paramPrintWriter.append(paramString).print("  callbacks=[");
    List<ClientTransactionItem> list = this.mActivityCallbacks;
    if (list != null) {
      b = list.size();
    } else {
      b = 0;
    } 
    if (b) {
      paramPrintWriter.println();
      for (byte b1 = 0; b1 < b; b1++)
        paramPrintWriter.append(paramString).append("    ").println(((ClientTransactionItem)this.mActivityCallbacks.get(b1)).toString()); 
      paramPrintWriter.append(paramString).println("  ]");
    } else {
      paramPrintWriter.println("]");
    } 
    PrintWriter printWriter = paramPrintWriter.append(paramString).append("  stateRequest=");
    ActivityLifecycleItem activityLifecycleItem = this.mLifecycleStateRequest;
    if (activityLifecycleItem != null) {
      String str = activityLifecycleItem.toString();
    } else {
      activityLifecycleItem = null;
    } 
    printWriter.println((String)activityLifecycleItem);
    paramPrintWriter.append(paramString).println("}");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.mActivityCallbacks, ((ClientTransaction)paramObject).mActivityCallbacks) || !Objects.equals(this.mLifecycleStateRequest, ((ClientTransaction)paramObject).mLifecycleStateRequest) || this.mClient != ((ClientTransaction)paramObject).mClient || this.mActivityToken != ((ClientTransaction)paramObject).mActivityToken)
      bool = false; 
    return bool;
  }
  
  public IBinder getActivityToken() {
    return this.mActivityToken;
  }
  
  public List<ClientTransactionItem> getCallbacks() {
    return this.mActivityCallbacks;
  }
  
  public IApplicationThread getClient() {
    return this.mClient;
  }
  
  public ActivityLifecycleItem getLifecycleStateRequest() {
    return this.mLifecycleStateRequest;
  }
  
  public int hashCode() {
    return (17 * 31 + Objects.hashCode(this.mActivityCallbacks)) * 31 + Objects.hashCode(this.mLifecycleStateRequest);
  }
  
  public void preExecute(ClientTransactionHandler paramClientTransactionHandler) {
    List<ClientTransactionItem> list = this.mActivityCallbacks;
    if (list != null) {
      int i = list.size();
      for (byte b = 0; b < i; b++)
        ((ClientTransactionItem)this.mActivityCallbacks.get(b)).preExecute(paramClientTransactionHandler, this.mActivityToken); 
    } 
    ActivityLifecycleItem activityLifecycleItem = this.mLifecycleStateRequest;
    if (activityLifecycleItem != null)
      activityLifecycleItem.preExecute(paramClientTransactionHandler, this.mActivityToken); 
  }
  
  public void recycle() {
    List<ClientTransactionItem> list = this.mActivityCallbacks;
    if (list != null) {
      int i = list.size();
      for (byte b = 0; b < i; b++)
        ((ClientTransactionItem)this.mActivityCallbacks.get(b)).recycle(); 
      this.mActivityCallbacks.clear();
    } 
    ActivityLifecycleItem activityLifecycleItem = this.mLifecycleStateRequest;
    if (activityLifecycleItem != null) {
      activityLifecycleItem.recycle();
      this.mLifecycleStateRequest = null;
    } 
    this.mClient = null;
    this.mActivityToken = null;
    ObjectPool.recycle(this);
  }
  
  public void schedule() throws RemoteException {
    this.mClient.scheduleTransaction(this);
  }
  
  public void setLifecycleStateRequest(ActivityLifecycleItem paramActivityLifecycleItem) {
    this.mLifecycleStateRequest = paramActivityLifecycleItem;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    boolean bool2;
    paramParcel.writeStrongBinder(this.mClient.asBinder());
    IBinder iBinder = this.mActivityToken;
    boolean bool1 = true;
    if (iBinder != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    paramParcel.writeBoolean(bool2);
    if (bool2)
      paramParcel.writeStrongBinder(this.mActivityToken); 
    paramParcel.writeParcelable(this.mLifecycleStateRequest, paramInt);
    if (this.mActivityCallbacks != null) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    paramParcel.writeBoolean(bool2);
    if (bool2)
      paramParcel.writeParcelableList(this.mActivityCallbacks, paramInt); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/servertransaction/ClientTransaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */