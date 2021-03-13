package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public abstract class HdmiRecordListener {
  public void onClearTimerRecordingResult(int paramInt1, int paramInt2) {}
  
  public void onOneTouchRecordResult(int paramInt1, int paramInt2) {}
  
  public abstract HdmiRecordSources.RecordSource onOneTouchRecordSourceRequested(int paramInt);
  
  public void onTimerRecordingResult(int paramInt, TimerStatusData paramTimerStatusData) {}
  
  @SystemApi
  public static class TimerStatusData {
    private int mDurationHour;
    
    private int mDurationMinute;
    
    private int mExtraError;
    
    private int mMediaInfo;
    
    private int mNotProgrammedError;
    
    private boolean mOverlapped;
    
    private boolean mProgrammed;
    
    private int mProgrammedInfo;
    
    private static int bcdByteToInt(byte param1Byte) {
      return (param1Byte >> 4 & 0xF) * 10 + param1Byte & 0xF;
    }
    
    static TimerStatusData parseFrom(int param1Int) {
      boolean bool2;
      TimerStatusData timerStatusData = new TimerStatusData();
      boolean bool1 = true;
      if ((param1Int >> 31 & 0x1) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      timerStatusData.mOverlapped = bool2;
      timerStatusData.mMediaInfo = param1Int >> 29 & 0x3;
      if ((param1Int >> 28 & 0x1) != 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      timerStatusData.mProgrammed = bool2;
      if (bool2) {
        timerStatusData.mProgrammedInfo = param1Int >> 24 & 0xF;
        timerStatusData.mDurationHour = bcdByteToInt((byte)(param1Int >> 16 & 0xFF));
        timerStatusData.mDurationMinute = bcdByteToInt((byte)(param1Int >> 8 & 0xFF));
      } else {
        timerStatusData.mNotProgrammedError = param1Int >> 24 & 0xF;
        timerStatusData.mDurationHour = bcdByteToInt((byte)(param1Int >> 16 & 0xFF));
        timerStatusData.mDurationMinute = bcdByteToInt((byte)(param1Int >> 8 & 0xFF));
      } 
      timerStatusData.mExtraError = param1Int & 0xFF;
      return timerStatusData;
    }
    
    public int getDurationHour() {
      return this.mDurationHour;
    }
    
    public int getDurationMinute() {
      return this.mDurationMinute;
    }
    
    public int getExtraError() {
      return this.mExtraError;
    }
    
    public int getMediaInfo() {
      return this.mMediaInfo;
    }
    
    public int getNotProgammedError() {
      if (!isProgrammed())
        return this.mNotProgrammedError; 
      throw new IllegalStateException("Has no not-programmed error. Call getProgrammedInfo() instead.");
    }
    
    public int getProgrammedInfo() {
      if (isProgrammed())
        return this.mProgrammedInfo; 
      throw new IllegalStateException("No programmed info. Call getNotProgammedError() instead.");
    }
    
    public boolean isOverlapped() {
      return this.mOverlapped;
    }
    
    public boolean isProgrammed() {
      return this.mProgrammed;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */