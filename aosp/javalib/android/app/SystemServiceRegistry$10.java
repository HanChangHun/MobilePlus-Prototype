package android.app;

import android.bluetooth.BluetoothManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<BluetoothManager> {
  public BluetoothManager createService(ContextImpl paramContextImpl) {
    return new BluetoothManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$10.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */