package android.hardware.hdmi;

public final class DigitalChannelData implements HdmiRecordSources.DigitalServiceIdentification {
  private final HdmiRecordSources.ChannelIdentifier mChannelIdentifier;
  
  private DigitalChannelData(HdmiRecordSources.ChannelIdentifier paramChannelIdentifier) {
    this.mChannelIdentifier = paramChannelIdentifier;
  }
  
  public static DigitalChannelData ofOneNumber(int paramInt) {
    return new DigitalChannelData(new HdmiRecordSources.ChannelIdentifier(1, 0, paramInt, null));
  }
  
  public static DigitalChannelData ofTwoNumbers(int paramInt1, int paramInt2) {
    return new DigitalChannelData(new HdmiRecordSources.ChannelIdentifier(2, paramInt1, paramInt2, null));
  }
  
  public int toByteArray(byte[] paramArrayOfbyte, int paramInt) {
    HdmiRecordSources.ChannelIdentifier.access$400(this.mChannelIdentifier, paramArrayOfbyte, paramInt);
    paramArrayOfbyte[paramInt + 4] = (byte)0;
    paramArrayOfbyte[paramInt + 5] = (byte)0;
    return 6;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$DigitalChannelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */