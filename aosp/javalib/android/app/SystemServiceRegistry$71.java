package android.app;

import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.IFingerprintService;
import android.os.IBinder;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<FingerprintManager> {
  public FingerprintManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder;
    if ((paramContextImpl.getApplicationInfo()).targetSdkVersion >= 26) {
      iBinder = ServiceManager.getServiceOrThrow("fingerprint");
    } else {
      iBinder = ServiceManager.getService("fingerprint");
    } 
    IFingerprintService iFingerprintService = IFingerprintService.Stub.asInterface(iBinder);
    return new FingerprintManager(paramContextImpl.getOuterContext(), iFingerprintService);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$71.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */