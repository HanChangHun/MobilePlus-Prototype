package android.app;

import android.content.rollback.IRollbackManager;
import android.content.rollback.RollbackManager;
import android.os.IBinder;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<RollbackManager> {
  public RollbackManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getServiceOrThrow("rollback");
    return new RollbackManager(paramContextImpl.getOuterContext(), IRollbackManager.Stub.asInterface(iBinder));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$109.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */