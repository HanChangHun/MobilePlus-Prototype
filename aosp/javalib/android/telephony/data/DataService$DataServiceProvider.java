package android.telephony.data;

import android.net.LinkProperties;
import java.util.ArrayList;
import java.util.List;

public abstract class DataServiceProvider implements AutoCloseable {
  private final List<IDataServiceCallback> mDataCallListChangedCallbacks = new ArrayList<>();
  
  private final int mSlotIndex;
  
  public DataServiceProvider(int paramInt) {
    this.mSlotIndex = paramInt;
  }
  
  private void registerForDataCallListChanged(IDataServiceCallback paramIDataServiceCallback) {
    synchronized (this.mDataCallListChangedCallbacks) {
      this.mDataCallListChangedCallbacks.add(paramIDataServiceCallback);
      return;
    } 
  }
  
  private void unregisterForDataCallListChanged(IDataServiceCallback paramIDataServiceCallback) {
    synchronized (this.mDataCallListChangedCallbacks) {
      this.mDataCallListChangedCallbacks.remove(paramIDataServiceCallback);
      return;
    } 
  }
  
  public abstract void close();
  
  public void deactivateDataCall(int paramInt1, int paramInt2, DataServiceCallback paramDataServiceCallback) {
    if (paramDataServiceCallback != null)
      paramDataServiceCallback.onDeactivateDataCallComplete(1); 
  }
  
  public final int getSlotIndex() {
    return this.mSlotIndex;
  }
  
  public final void notifyDataCallListChanged(List<DataCallResponse> paramList) {
    synchronized (this.mDataCallListChangedCallbacks) {
      for (IDataServiceCallback iDataServiceCallback : this.mDataCallListChangedCallbacks) {
        DataService.DataServiceHandler dataServiceHandler = DataService.access$100(DataService.this);
        int i = this.mSlotIndex;
        DataService.DataCallListChangedIndication dataCallListChangedIndication = new DataService.DataCallListChangedIndication();
        this(paramList, iDataServiceCallback);
        dataServiceHandler.obtainMessage(11, i, 0, dataCallListChangedIndication).sendToTarget();
      } 
      return;
    } 
  }
  
  public void requestDataCallList(DataServiceCallback paramDataServiceCallback) {
    paramDataServiceCallback.onRequestDataCallListComplete(1, null);
  }
  
  public void setDataProfile(List<DataProfile> paramList, boolean paramBoolean, DataServiceCallback paramDataServiceCallback) {
    if (paramDataServiceCallback != null)
      paramDataServiceCallback.onSetDataProfileComplete(1); 
  }
  
  public void setInitialAttachApn(DataProfile paramDataProfile, boolean paramBoolean, DataServiceCallback paramDataServiceCallback) {
    if (paramDataServiceCallback != null)
      paramDataServiceCallback.onSetInitialAttachApnComplete(1); 
  }
  
  public void setupDataCall(int paramInt1, DataProfile paramDataProfile, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, LinkProperties paramLinkProperties, DataServiceCallback paramDataServiceCallback) {
    if (paramDataServiceCallback != null)
      paramDataServiceCallback.onSetupDataCallComplete(1, null); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService$DataServiceProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */