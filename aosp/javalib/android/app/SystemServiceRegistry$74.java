package android.app;

import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.IAuthService;
import android.os.IBinder;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<BiometricManager> {
  public BiometricManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getServiceOrThrow("auth");
    IAuthService iAuthService = IAuthService.Stub.asInterface(iBinder);
    return new BiometricManager(paramContextImpl.getOuterContext(), iAuthService);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$74.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */