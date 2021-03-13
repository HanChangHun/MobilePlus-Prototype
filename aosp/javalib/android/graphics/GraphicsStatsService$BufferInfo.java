package android.graphics;

final class BufferInfo {
  long mEndTime;
  
  final String mPackageName;
  
  long mStartTime;
  
  final long mVersionCode;
  
  BufferInfo(String paramString, long paramLong1, long paramLong2) {
    this.mPackageName = paramString;
    this.mVersionCode = paramLong1;
    this.mStartTime = paramLong2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/GraphicsStatsService$BufferInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */