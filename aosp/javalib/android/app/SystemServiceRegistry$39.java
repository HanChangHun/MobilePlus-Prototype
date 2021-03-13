package android.app;

import android.os.IRecoverySystem;
import android.os.RecoverySystem;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<RecoverySystem> {
  public RecoverySystem createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new RecoverySystem(IRecoverySystem.Stub.asInterface(ServiceManager.getServiceOrThrow("recovery")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$39.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */