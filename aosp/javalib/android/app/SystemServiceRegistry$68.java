package android.app;

import android.hardware.ConsumerIrManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<ConsumerIrManager> {
  public ConsumerIrManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new ConsumerIrManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$68.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */