package android.app;

import android.hardware.ISerialManager;
import android.hardware.SerialManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<SerialManager> {
  public SerialManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new SerialManager(paramContextImpl, ISerialManager.Stub.asInterface(ServiceManager.getServiceOrThrow("serial")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$54.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */