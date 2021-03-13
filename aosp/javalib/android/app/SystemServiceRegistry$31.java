package android.app;

import android.os.ServiceManager;
import android.view.textservice.TextServicesManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TextServicesManager> {
  public TextServicesManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return TextServicesManager.createInstance(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$31.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */