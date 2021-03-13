package android.app;

import android.app.trust.TrustManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.StaticServiceFetcher<TrustManager> {
  public TrustManager createService() throws ServiceManager.ServiceNotFoundException {
    return new TrustManager(ServiceManager.getServiceOrThrow("trust"));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$70.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */