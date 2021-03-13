package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public final class OwnSource extends HdmiRecordSources.RecordSource {
  private static final int EXTRA_DATA_SIZE = 0;
  
  private OwnSource() {
    super(1, 0);
  }
  
  int extraParamToByteArray(byte[] paramArrayOfbyte, int paramInt) {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$OwnSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */