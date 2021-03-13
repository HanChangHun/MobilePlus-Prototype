package android.graphics;

import java.io.IOException;

final class HistoricalBuffer {
  final byte[] mData = new byte[GraphicsStatsService.access$200(GraphicsStatsService.this)];
  
  final GraphicsStatsService.BufferInfo mInfo;
  
  HistoricalBuffer(GraphicsStatsService.ActiveBuffer paramActiveBuffer) throws IOException {
    GraphicsStatsService.BufferInfo bufferInfo = paramActiveBuffer.mInfo;
    this.mInfo = bufferInfo;
    bufferInfo.mEndTime = System.currentTimeMillis();
    paramActiveBuffer.readBytes(this.mData, GraphicsStatsService.access$200(paramGraphicsStatsService));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/GraphicsStatsService$HistoricalBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */