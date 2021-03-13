package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public final class DigitalServiceSource extends HdmiRecordSources.RecordSource {
  private static final int DIGITAL_SERVICE_IDENTIFIED_BY_CHANNEL = 1;
  
  private static final int DIGITAL_SERVICE_IDENTIFIED_BY_DIGITAL_ID = 0;
  
  static final int EXTRA_DATA_SIZE = 7;
  
  private final int mBroadcastSystem;
  
  private final HdmiRecordSources.DigitalServiceIdentification mIdentification;
  
  private final int mIdentificationMethod;
  
  private DigitalServiceSource(int paramInt1, int paramInt2, HdmiRecordSources.DigitalServiceIdentification paramDigitalServiceIdentification) {
    super(2, 7);
    this.mIdentificationMethod = paramInt1;
    this.mBroadcastSystem = paramInt2;
    this.mIdentification = paramDigitalServiceIdentification;
  }
  
  int extraParamToByteArray(byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = (byte)(byte)(this.mIdentificationMethod << 7 | this.mBroadcastSystem & 0x7F);
    this.mIdentification.toByteArray(paramArrayOfbyte, paramInt + 1);
    return 7;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$DigitalServiceSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */