package android.app;

import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<AlarmManager> {
  public AlarmManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new AlarmManager(IAlarmManager.Stub.asInterface(ServiceManager.getServiceOrThrow("alarm")), paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$7.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */