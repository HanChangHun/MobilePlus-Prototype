package android.telephony.data;

final class SetInitialAttachApnRequest {
  public final IDataServiceCallback callback;
  
  public final DataProfile dataProfile;
  
  public final boolean isRoaming;
  
  SetInitialAttachApnRequest(DataProfile paramDataProfile, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) {
    this.dataProfile = paramDataProfile;
    this.isRoaming = paramBoolean;
    this.callback = paramIDataServiceCallback;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/DataService$SetInitialAttachApnRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */