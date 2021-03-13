package android.telephony.data;

import android.net.LinkProperties;

final class SetupDataCallRequest {
  public final int accessNetworkType;
  
  public final boolean allowRoaming;
  
  public final IDataServiceCallback callback;
  
  public final DataProfile dataProfile;
  
  public final boolean isRoaming;
  
  public final LinkProperties linkProperties;
  
  public final int reason;
  
  SetupDataCallRequest(int paramInt1, DataProfile paramDataProfile, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, LinkProperties paramLinkProperties, IDataServiceCallback paramIDataServiceCallback) {
    this.accessNetworkType = paramInt1;
    this.dataProfile = paramDataProfile;
    this.isRoaming = paramBoolean1;
    this.allowRoaming = paramBoolean2;
    this.linkProperties = paramLinkProperties;
    this.reason = paramInt2;
    this.callback = paramIDataServiceCallback;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService$SetupDataCallRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */