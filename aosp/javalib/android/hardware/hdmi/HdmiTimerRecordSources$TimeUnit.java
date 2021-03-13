package android.hardware.hdmi;

class TimeUnit {
  final int mHour;
  
  final int mMinute;
  
  TimeUnit(int paramInt1, int paramInt2) {
    this.mHour = paramInt1;
    this.mMinute = paramInt2;
  }
  
  static byte toBcdByte(int paramInt) {
    return (byte)(paramInt / 10 % 10 << 4 | paramInt % 10);
  }
  
  int toByteArray(byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = toBcdByte(this.mHour);
    paramArrayOfbyte[paramInt + 1] = toBcdByte(this.mMinute);
    return 2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiTimerRecordSources$TimeUnit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */