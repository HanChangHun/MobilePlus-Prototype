package android.telephony.data;

import java.util.List;

final class SetDataProfileRequest {
  public final IDataServiceCallback callback;
  
  public final List<DataProfile> dps;
  
  public final boolean isRoaming;
  
  SetDataProfileRequest(List<DataProfile> paramList, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) {
    this.dps = paramList;
    this.isRoaming = paramBoolean;
    this.callback = paramIDataServiceCallback;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService$SetDataProfileRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */