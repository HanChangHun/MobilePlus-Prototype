package android.hardware.hdmi;

public final class AribData implements HdmiRecordSources.DigitalServiceIdentification {
  private final int mOriginalNetworkId;
  
  private final int mServiceId;
  
  private final int mTransportStreamId;
  
  public AribData(int paramInt1, int paramInt2, int paramInt3) {
    this.mTransportStreamId = paramInt1;
    this.mServiceId = paramInt2;
    this.mOriginalNetworkId = paramInt3;
  }
  
  public int toByteArray(byte[] paramArrayOfbyte, int paramInt) {
    return HdmiRecordSources.access$100(this.mTransportStreamId, this.mServiceId, this.mOriginalNetworkId, paramArrayOfbyte, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$AribData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */