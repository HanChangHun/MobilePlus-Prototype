package android.app;

import android.os.IPowerManager;
import android.os.IThermalService;
import android.os.PowerManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<PowerManager> {
  public PowerManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IPowerManager iPowerManager = IPowerManager.Stub.asInterface(ServiceManager.getServiceOrThrow("power"));
    IThermalService iThermalService = IThermalService.Stub.asInterface(ServiceManager.getServiceOrThrow("thermalservice"));
    return new PowerManager(paramContextImpl.getOuterContext(), iPowerManager, iThermalService, paramContextImpl.mMainThread.getHandler());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$38.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */