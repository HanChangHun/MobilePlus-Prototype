package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public final class TimerRecordSource {
  private final HdmiRecordSources.RecordSource mRecordSource;
  
  private final HdmiTimerRecordSources.TimerInfo mTimerInfo;
  
  private TimerRecordSource(HdmiTimerRecordSources.TimerInfo paramTimerInfo, HdmiRecordSources.RecordSource paramRecordSource) {
    this.mTimerInfo = paramTimerInfo;
    this.mRecordSource = paramRecordSource;
  }
  
  int getDataSize() {
    return this.mTimerInfo.getDataSize() + this.mRecordSource.getDataSize(false);
  }
  
  int toByteArray(byte[] paramArrayOfbyte, int paramInt) {
    int i = this.mTimerInfo.toByteArray(paramArrayOfbyte, paramInt);
    this.mRecordSource.toByteArray(false, paramArrayOfbyte, paramInt + i);
    return getDataSize();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiTimerRecordSources$TimerRecordSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */