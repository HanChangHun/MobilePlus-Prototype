package android.hardware.hdmi;

final class ChannelIdentifier {
  private final int mChannelNumberFormat;
  
  private final int mMajorChannelNumber;
  
  private final int mMinorChannelNumber;
  
  private ChannelIdentifier(int paramInt1, int paramInt2, int paramInt3) {
    this.mChannelNumberFormat = paramInt1;
    this.mMajorChannelNumber = paramInt2;
    this.mMinorChannelNumber = paramInt3;
  }
  
  private int toByteArray(byte[] paramArrayOfbyte, int paramInt) {
    int i = this.mChannelNumberFormat;
    int j = this.mMajorChannelNumber;
    paramArrayOfbyte[paramInt] = (byte)(byte)(i << 2 | j >>> 8 & 0x3);
    paramArrayOfbyte[paramInt + 1] = (byte)(byte)(j & 0xFF);
    HdmiRecordSources.access$200((short)this.mMinorChannelNumber, paramArrayOfbyte, paramInt + 2);
    return 4;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$ChannelIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */