package android.app;

import android.os.IBinder;
import android.os.ServiceManager;
import android.os.incremental.IIncrementalService;
import android.os.incremental.IncrementalManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<IncrementalManager> {
  public IncrementalManager createService(ContextImpl paramContextImpl) {
    IBinder iBinder = ServiceManager.getService("incremental");
    return (iBinder == null) ? null : new IncrementalManager(IIncrementalService.Stub.asInterface(iBinder));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$114.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */