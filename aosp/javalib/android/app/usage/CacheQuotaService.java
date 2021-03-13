package android.app.usage;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallback;
import android.util.Log;
import android.util.Pair;
import java.util.List;

@SystemApi
public abstract class CacheQuotaService extends Service {
  public static final String REQUEST_LIST_KEY = "requests";
  
  public static final String SERVICE_INTERFACE = "android.app.usage.CacheQuotaService";
  
  private static final String TAG = "CacheQuotaService";
  
  private Handler mHandler;
  
  private CacheQuotaServiceWrapper mWrapper;
  
  public IBinder onBind(Intent paramIntent) {
    return (IBinder)this.mWrapper;
  }
  
  public abstract List<CacheQuotaHint> onComputeCacheQuotaHints(List<CacheQuotaHint> paramList);
  
  public void onCreate() {
    super.onCreate();
    this.mWrapper = new CacheQuotaServiceWrapper();
    this.mHandler = new ServiceHandler(getMainLooper());
  }
  
  private final class CacheQuotaServiceWrapper extends ICacheQuotaService.Stub {
    private CacheQuotaServiceWrapper() {}
    
    public void computeCacheQuotaHints(RemoteCallback param1RemoteCallback, List<CacheQuotaHint> param1List) {
      Pair pair = Pair.create(param1RemoteCallback, param1List);
      Message message = CacheQuotaService.this.mHandler.obtainMessage(1, pair);
      CacheQuotaService.this.mHandler.sendMessage(message);
    }
  }
  
  private final class ServiceHandler extends Handler {
    public static final int MSG_SEND_LIST = 1;
    
    public ServiceHandler(Looper param1Looper) {
      super(param1Looper, null, true);
    }
    
    public void handleMessage(Message param1Message) {
      StringBuilder stringBuilder;
      int i = param1Message.what;
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
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/CacheQuotaService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */