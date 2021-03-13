package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.StaticServiceFetcher<TServiceClass> {
  public TServiceClass createService() throws ServiceManager.ServiceNotFoundException {
    return serviceProducer.createService(ServiceManager.getServiceOrThrow(serviceName));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$118.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */