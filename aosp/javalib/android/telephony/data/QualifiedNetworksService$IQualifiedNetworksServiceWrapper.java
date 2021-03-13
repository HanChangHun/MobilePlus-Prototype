package android.telephony.data;

class IQualifiedNetworksServiceWrapper extends IQualifiedNetworksService.Stub {
  private IQualifiedNetworksServiceWrapper() {}
  
  public void createNetworkAvailabilityProvider(int paramInt, IQualifiedNetworksServiceCallback paramIQualifiedNetworksServiceCallback) {
    QualifiedNetworksService.access$200(QualifiedNetworksService.this).obtainMessage(1, paramInt, 0, paramIQualifiedNetworksServiceCallback).sendToTarget();
  }
  
  public void removeNetworkAvailabilityProvider(int paramInt) {
    QualifiedNetworksService.access$200(QualifiedNetworksService.this).obtainMessage(2, paramInt, 0).sendToTarget();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/QualifiedNetworksService$IQualifiedNetworksServiceWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */