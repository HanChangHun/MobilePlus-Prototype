package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public final class TimerInfo {
  private static final int BASIC_INFO_SIZE = 7;
  
  private static final int DAY_OF_MONTH_SIZE = 1;
  
  private static final int DURATION_SIZE = 2;
  
  private static final int MONTH_OF_YEAR_SIZE = 1;
  
  private static final int RECORDING_SEQUENCE_SIZE = 1;
  
  private static final int START_TIME_SIZE = 2;
  
  private final int mDayOfMonth;
  
  private final HdmiTimerRecordSources.Duration mDuration;
  
  private final int mMonthOfYear;
  
  private final int mRecordingSequence;
  
  private final HdmiTimerRecordSources.Time mStartTime;
  
  private TimerInfo(int paramInt1, int paramInt2, HdmiTimerRecordSources.Time paramTime, HdmiTimerRecordSources.Duration paramDuration, int paramInt3) {
    this.mDayOfMonth = paramInt1;
    this.mMonthOfYear = paramInt2;
    this.mStartTime = paramTime;
    this.mDuration = paramDuration;
    this.mRecordingSequence = paramInt3;
  }
  
  int getDataSize() {
    return 7;
  }
  
  int toByteArray(byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = (byte)(byte)this.mDayOfMonth;
    paramArrayOfbyte[++paramInt] = (byte)(byte)this.mMonthOfYear;
    paramInt = ++paramInt + this.mStartTime.toByteArray(paramArrayOfbyte, paramInt);
    paramArrayOfbyte[paramInt + this.mDuration.toByteArray(paramArrayOfbyte, paramInt)] = (byte)(byte)this.mRecordingSequence;
    return getDataSize();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiTimerRecordSources$TimerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */