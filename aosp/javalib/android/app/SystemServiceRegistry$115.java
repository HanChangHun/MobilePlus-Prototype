package android.app;

import android.os.IBinder;
import android.os.ServiceManager;
import android.security.FileIntegrityManager;
import android.security.IFileIntegrityService;

class null extends SystemServiceRegistry.CachedServiceFetcher<FileIntegrityManager> {
  public FileIntegrityManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getServiceOrThrow("file_integrity");
    return new FileIntegrityManager(paramContextImpl.getOuterContext(), IFileIntegrityService.Stub.asInterface(iBinder));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$115.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */