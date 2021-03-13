package android.app;

import android.os.ServiceManager;
import android.service.vr.IVrManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<VrManager> {
  public VrManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new VrManager(IVrManager.Stub.asInterface(ServiceManager.getServiceOrThrow("vrmanager")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$99.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */