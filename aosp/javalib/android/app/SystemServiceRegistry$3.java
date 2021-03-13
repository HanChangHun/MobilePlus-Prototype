package android.app;

import android.accounts.AccountManager;
import android.accounts.IAccountManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<AccountManager> {
  public AccountManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new AccountManager(paramContextImpl, IAccountManager.Stub.asInterface(ServiceManager.getServiceOrThrow("account")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */