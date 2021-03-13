package android.app;

import android.hardware.usb.IUsbManager;
import android.hardware.usb.UsbManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<UsbManager> {
  public UsbManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new UsbManager(paramContextImpl, IUsbManager.Stub.asInterface(ServiceManager.getServiceOrThrow("usb")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$52.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */