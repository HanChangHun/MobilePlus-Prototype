package android.hardware.hdmi;

import libcore.util.EmptyArray;

class null extends IHdmiRecordListener.Stub {
  public byte[] getOneTouchRecordSource(int paramInt) {
    HdmiRecordSources.RecordSource recordSource = callback.onOneTouchRecordSourceRequested(paramInt);
    if (recordSource == null)
      return EmptyArray.BYTE; 
    byte[] arrayOfByte = new byte[recordSource.getDataSize(true)];
    recordSource.toByteArray(true, arrayOfByte, 0);
    return arrayOfByte;
  }
  
  public void onClearTimerRecordingResult(int paramInt1, int paramInt2) {
    callback.onClearTimerRecordingResult(paramInt1, paramInt2);
  }
  
  public void onOneTouchRecordResult(int paramInt1, int paramInt2) {
    callback.onOneTouchRecordResult(paramInt1, paramInt2);
  }
  
  public void onTimerRecordingResult(int paramInt1, int paramInt2) {
    callback.onTimerRecordingResult(paramInt1, HdmiRecordListener.TimerStatusData.parseFrom(paramInt2));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiTvClient$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */