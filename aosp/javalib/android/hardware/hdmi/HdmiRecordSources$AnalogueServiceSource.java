package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public final class AnalogueServiceSource extends HdmiRecordSources.RecordSource {
  static final int EXTRA_DATA_SIZE = 4;
  
  private final int mBroadcastSystem;
  
  private final int mBroadcastType;
  
  private final int mFrequency;
  
  private AnalogueServiceSource(int paramInt1, int paramInt2, int paramInt3) {
    super(3, 4);
    this.mBroadcastType = paramInt1;
    this.mFrequency = paramInt2;
    this.mBroadcastSystem = paramInt3;
  }
  
  int extraParamToByteArray(byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = (byte)(byte)this.mBroadcastType;
    HdmiRecordSources.access$200((short)this.mFrequency, paramArrayOfbyte, paramInt + 1);
    paramArrayOfbyte[paramInt + 3] = (byte)(byte)this.mBroadcastSystem;
    return 4;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$AnalogueServiceSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */