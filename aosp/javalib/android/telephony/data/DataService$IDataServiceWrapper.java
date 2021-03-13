package android.telephony.data;

import android.net.LinkProperties;
import java.util.List;

class IDataServiceWrapper extends IDataService.Stub {
  private IDataServiceWrapper() {}
  
  public void createDataServiceProvider(int paramInt) {
    DataService.access$100(DataService.this).obtainMessage(1, paramInt, 0).sendToTarget();
  }
  
  public void deactivateDataCall(int paramInt1, int paramInt2, int paramInt3, IDataServiceCallback paramIDataServiceCallback) {
    DataService.access$100(DataService.this).obtainMessage(5, paramInt1, 0, new DataService.DeactivateDataCallRequest(paramInt2, paramInt3, paramIDataServiceCallback)).sendToTarget();
  }
  
  public void registerForDataCallListChanged(int paramInt, IDataServiceCallback paramIDataServiceCallback) {
    if (paramIDataServiceCallback == null) {
      DataService.access$500(DataService.this, "registerForDataCallListChanged: callback is null");
      return;
    } 
    DataService.access$100(DataService.this).obtainMessage(9, paramInt, 0, paramIDataServiceCallback).sendToTarget();
  }
  
  public void removeDataServiceProvider(int paramInt) {
    DataService.access$100(DataService.this).obtainMessage(2, paramInt, 0).sendToTarget();
  }
  
  public void requestDataCallList(int paramInt, IDataServiceCallback paramIDataServiceCallback) {
    if (paramIDataServiceCallback == null) {
      DataService.access$500(DataService.this, "requestDataCallList: callback is null");
      return;
    } 
    DataService.access$100(DataService.this).obtainMessage(8, paramInt, 0, paramIDataServiceCallback).sendToTarget();
  }
  
  public void setDataProfile(int paramInt, List<DataProfile> paramList, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) {
    DataService.access$100(DataService.this).obtainMessage(7, paramInt, 0, new DataService.SetDataProfileRequest(paramList, paramBoolean, paramIDataServiceCallback)).sendToTarget();
  }
  
  public void setInitialAttachApn(int paramInt, DataProfile paramDataProfile, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) {
    DataService.access$100(DataService.this).obtainMessage(6, paramInt, 0, new DataService.SetInitialAttachApnRequest(paramDataProfile, paramBoolean, paramIDataServiceCallback)).sendToTarget();
  }
  
  public void setupDataCall(int paramInt1, int paramInt2, DataProfile paramDataProfile, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, LinkProperties paramLinkProperties, IDataServiceCallback paramIDataServiceCallback) {
    DataService.access$100(DataService.this).obtainMessage(4, paramInt1, 0, new DataService.SetupDataCallRequest(paramInt2, paramDataProfile, paramBoolean1, paramBoolean2, paramInt3, paramLinkProperties, paramIDataServiceCallback)).sendToTarget();
  }
  
  public void unregisterForDataCallListChanged(int paramInt, IDataServiceCallback paramIDataServiceCallback) {
    if (paramIDataServiceCallback == null) {
      DataService.access$500(DataService.this, "unregisterForDataCallListChanged: callback is null");
      return;
    } 
    DataService.access$100(DataService.this).obtainMessage(10, paramInt, 0, paramIDataServiceCallback).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService$IDataServiceWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */