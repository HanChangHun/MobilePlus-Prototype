package android.hardware.hdmi;

class ExternalSourceDecorator extends HdmiRecordSources.RecordSource {
  private final int mExternalSourceSpecifier;
  
  private final HdmiRecordSources.RecordSource mRecordSource;
  
  private ExternalSourceDecorator(HdmiRecordSources.RecordSource paramRecordSource, int paramInt) {
    super(paramRecordSource.mSourceType, paramRecordSource.getDataSize(false) + 1);
    this.mRecordSource = paramRecordSource;
    this.mExternalSourceSpecifier = paramInt;
  }
  
  int extraParamToByteArray(byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = (byte)(byte)this.mExternalSourceSpecifier;
    this.mRecordSource.toByteArray(false, paramArrayOfbyte, paramInt + 1);
    return getDataSize(false);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiTimerRecordSources$ExternalSourceDecorator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */