package android.telephony.data;

final class DeactivateDataCallRequest {
  public final IDataServiceCallback callback;
  
  public final int cid;
  
  public final int reason;
  
  DeactivateDataCallRequest(int paramInt1, int paramInt2, IDataServiceCallback paramIDataServiceCallback) {
    this.cid = paramInt1;
    this.reason = paramInt2;
    this.callback = paramIDataServiceCallback;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService$DeactivateDataCallRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */