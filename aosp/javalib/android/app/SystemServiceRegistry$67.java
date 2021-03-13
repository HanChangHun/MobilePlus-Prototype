package android.app;

import android.companion.CompanionDeviceManager;
import android.companion.ICompanionDeviceManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<CompanionDeviceManager> {
  public CompanionDeviceManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    ICompanionDeviceManager iCompanionDeviceManager = null;
    if (paramContextImpl.getPackageManager().hasSystemFeature("android.software.companion_device_setup"))
      iCompanionDeviceManager = ICompanionDeviceManager.Stub.asInterface(ServiceManager.getServiceOrThrow("companiondevice")); 
    return new CompanionDeviceManager(iCompanionDeviceManager, paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$67.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */