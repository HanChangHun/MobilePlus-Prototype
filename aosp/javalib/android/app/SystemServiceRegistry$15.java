package android.app;

import android.os.IBinder;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.StaticServiceFetcher<IBinder> {
  public IBinder createService() throws ServiceManager.ServiceNotFoundException {
    return ServiceManager.getServiceOrThrow("netd");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$15.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */