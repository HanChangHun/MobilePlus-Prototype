package android.app;

import android.content.pm.DataLoaderManager;
import android.content.pm.IDataLoaderManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<DataLoaderManager> {
  public DataLoaderManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new DataLoaderManager(IDataLoaderManager.Stub.asInterface(ServiceManager.getServiceOrThrow("dataloader_manager")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$112.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */