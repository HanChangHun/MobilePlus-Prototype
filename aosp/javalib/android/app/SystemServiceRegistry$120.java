package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TServiceClass> {
  public TServiceClass createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return serviceProducer.createService(paramContextImpl.getOuterContext(), ServiceManager.getServiceOrThrow(serviceName));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$120.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */