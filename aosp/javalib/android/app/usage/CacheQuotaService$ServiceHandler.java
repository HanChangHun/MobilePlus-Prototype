package android.app.usage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallback;
import android.util.Log;
import android.util.Pair;
import java.util.List;

final class ServiceHandler extends Handler {
  public static final int MSG_SEND_LIST = 1;
  
  public ServiceHandler(Looper paramLooper) {
    super(paramLooper, null, true);
  }
  
  public void handleMessage(Message paramMessage) {
    StringBuilder stringBuilder;
    int i = paramMessage.what;
    if (i != 1) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("Handling unknown message: ");
      stringBuilder.append(i);
      Log.w("CacheQuotaService", stringBuilder.toString());
    } else {
      Pair pair = (Pair)((Message)stringBuilder).obj;
      List<CacheQuotaHint> list = CacheQuotaService.this.onComputeCacheQuotaHints((List<CacheQuotaHint>)pair.second);
      Bundle bundle = new Bundle();
      bundle.putParcelableList("requests", list);
      ((RemoteCallback)pair.first).sendResult(bundle);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/CacheQuotaService$ServiceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */