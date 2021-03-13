package android.app;

import android.content.om.IOverlayManager;
import android.content.om.OverlayManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<OverlayManager> {
  public OverlayManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new OverlayManager(paramContextImpl, IOverlayManager.Stub.asInterface(ServiceManager.getServiceOrThrow("overlay")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$89.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */