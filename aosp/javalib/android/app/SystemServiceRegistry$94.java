package android.app;

import android.os.BugreportManager;
import android.os.IBinder;
import android.os.IDumpstate;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<BugreportManager> {
  public BugreportManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getServiceOrThrow("bugreport");
    return new BugreportManager(paramContextImpl.getOuterContext(), IDumpstate.Stub.asInterface(iBinder));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$94.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */