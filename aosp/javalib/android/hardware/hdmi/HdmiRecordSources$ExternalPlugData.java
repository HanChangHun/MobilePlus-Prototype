package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public final class ExternalPlugData extends HdmiRecordSources.RecordSource {
  static final int EXTRA_DATA_SIZE = 1;
  
  private final int mPlugNumber;
  
  private ExternalPlugData(int paramInt) {
    super(4, 1);
    this.mPlugNumber = paramInt;
  }
  
  int extraParamToByteArray(byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = (byte)(byte)this.mPlugNumber;
    return 1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$ExternalPlugData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */