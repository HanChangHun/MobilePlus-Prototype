package android.app;

import android.os.DropBoxManager;
import android.os.ServiceManager;
import com.android.internal.os.IDropBoxManagerService;

class null extends SystemServiceRegistry.CachedServiceFetcher<DropBoxManager> {
  public DropBoxManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new DropBoxManager(paramContextImpl, IDropBoxManagerService.Stub.asInterface(ServiceManager.getServiceOrThrow("dropbox")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$26.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */