package android.app.usage;

import android.os.IBinder;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.util.List;

public class Default implements ICacheQuotaService {
  public IBinder asBinder() {
    return null;
  }
  
  public void computeCacheQuotaHints(RemoteCallback paramRemoteCallback, List<CacheQuotaHint> paramList) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/ICacheQuotaService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */