package android.app.usage;

import android.os.Message;
import android.os.RemoteCallback;
import android.util.Pair;
import java.util.List;

final class CacheQuotaServiceWrapper extends ICacheQuotaService.Stub {
  private CacheQuotaServiceWrapper() {}
  
  public void computeCacheQuotaHints(RemoteCallback paramRemoteCallback, List<CacheQuotaHint> paramList) {
    Pair pair = Pair.create(paramRemoteCallback, paramList);
    Message message = CacheQuotaService.access$100(CacheQuotaService.this).obtainMessage(1, pair);
    CacheQuotaService.access$100(CacheQuotaService.this).sendMessage(message);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/CacheQuotaService$CacheQuotaServiceWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */