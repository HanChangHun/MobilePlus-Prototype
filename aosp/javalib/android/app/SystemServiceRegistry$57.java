package android.app;

import android.net.ConnectivityThread;
import android.net.lowpan.ILowpanManager;
import android.net.lowpan.LowpanManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<LowpanManager> {
  public LowpanManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    ILowpanManager iLowpanManager = ILowpanManager.Stub.asInterface(ServiceManager.getServiceOrThrow("lowpan"));
    return new LowpanManager(paramContextImpl.getOuterContext(), iLowpanManager, ConnectivityThread.getInstanceLooper());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$57.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */