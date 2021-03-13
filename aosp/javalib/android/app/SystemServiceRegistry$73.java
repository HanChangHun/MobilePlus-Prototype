package android.app;

import android.hardware.iris.IIrisService;
import android.hardware.iris.IrisManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<IrisManager> {
  public IrisManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IIrisService iIrisService = IIrisService.Stub.asInterface(ServiceManager.getServiceOrThrow("iris"));
    return new IrisManager(paramContextImpl.getOuterContext(), iIrisService);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$73.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */