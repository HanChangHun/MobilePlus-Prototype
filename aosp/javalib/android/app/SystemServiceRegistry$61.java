package android.app;

import android.os.IUserManager;
import android.os.ServiceManager;
import android.os.UserManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<UserManager> {
  public UserManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new UserManager(paramContextImpl, IUserManager.Stub.asInterface(ServiceManager.getServiceOrThrow("user")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$61.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */