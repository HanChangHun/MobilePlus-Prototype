package android.app;

import android.os.IBinder;
import android.os.ServiceManager;
import android.service.persistentdata.IPersistentDataBlockService;
import android.service.persistentdata.PersistentDataBlockManager;

class null extends SystemServiceRegistry.StaticServiceFetcher<PersistentDataBlockManager> {
  public PersistentDataBlockManager createService() throws ServiceManager.ServiceNotFoundException {
    IBinder iBinder = ServiceManager.getServiceOrThrow("persistent_data_block");
    IPersistentDataBlockService iPersistentDataBlockService = IPersistentDataBlockService.Stub.asInterface(iBinder);
    return (iPersistentDataBlockService != null) ? new PersistentDataBlockManager(iPersistentDataBlockService) : null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$80.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */