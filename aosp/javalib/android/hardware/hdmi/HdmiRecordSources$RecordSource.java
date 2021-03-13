package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public abstract class RecordSource {
  final int mExtraDataSize;
  
  final int mSourceType;
  
  RecordSource(int paramInt1, int paramInt2) {
    this.mSourceType = paramInt1;
    this.mExtraDataSize = paramInt2;
  }
  
  abstract int extraParamToByteArray(byte[] paramArrayOfbyte, int paramInt);
  
  final int getDataSize(boolean paramBoolean) {
    int i = this.mExtraDataSize;
    int j = i;
    if (paramBoolean)
      j = i + 1; 
    return j;
  }
  
  final int toByteArray(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    if (paramBoolean) {
      paramArrayOfbyte[paramInt] = (byte)(byte)this.mSourceType;
      i = paramInt + 1;
    } 
    extraParamToByteArray(paramArrayOfbyte, i);
    return getDataSize(paramBoolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources$RecordSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */