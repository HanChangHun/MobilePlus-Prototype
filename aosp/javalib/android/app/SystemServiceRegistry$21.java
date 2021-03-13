package android.app;

import android.location.CountryDetector;
import android.location.ICountryDetector;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.StaticServiceFetcher<CountryDetector> {
  public CountryDetector createService() throws ServiceManager.ServiceNotFoundException {
    return new CountryDetector(ICountryDetector.Stub.asInterface(ServiceManager.getServiceOrThrow("country_detector")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$21.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */