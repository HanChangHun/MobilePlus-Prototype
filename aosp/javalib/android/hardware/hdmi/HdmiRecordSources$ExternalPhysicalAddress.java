package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public final class ExternalPhysicalAddress extends HdmiRecordSources.RecordSource {
  static final int EXTRA_DATA_SIZE = 2;
  
  private final int mPhysicalAddress;
  
  private ExternalPhysicalAddress(int paramInt) {
    super(5, 2);
    this.mPhysicalAddress = paramInt;
  }
  
  int extraParamToByteArray(byte[] paramArrayOfbyte, int paramInt) {
    HdmiRecordSources.access$200((short)this.mPhysicalAddress, paramArrayOfbyte, paramInt);
    return 2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$ExternalPhysicalAddress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */