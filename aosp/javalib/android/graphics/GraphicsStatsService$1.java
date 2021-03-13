package android.graphics;

import android.os.Handler;
import android.os.Message;

class null implements Handler.Callback {
  public boolean handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 1) {
      if (i == 2)
        GraphicsStatsService.access$100(GraphicsStatsService.this); 
    } else {
      GraphicsStatsService.access$000(GraphicsStatsService.this, (GraphicsStatsService.HistoricalBuffer)paramMessage.obj);
    } 
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/GraphicsStatsService$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */