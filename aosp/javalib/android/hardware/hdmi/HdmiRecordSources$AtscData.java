package android.hardware.hdmi;

public final class AtscData implements HdmiRecordSources.DigitalServiceIdentification {
  private final int mProgramNumber;
  
  private final int mTransportStreamId;
  
  public AtscData(int paramInt1, int paramInt2) {
    this.mTransportStreamId = paramInt1;
    this.mProgramNumber = paramInt2;
  }
  
  public int toByteArray(byte[] paramArrayOfbyte, int paramInt) {
    return HdmiRecordSources.access$100(this.mTransportStreamId, this.mProgramNumber, 0, paramArrayOfbyte, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$AtscData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */