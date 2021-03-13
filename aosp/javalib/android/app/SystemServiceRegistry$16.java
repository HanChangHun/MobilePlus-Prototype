package android.app;

import android.net.TetheringManager;
import android.os.IBinder;
import android.os.ServiceManager;
import java.util.function.Supplier;

class null extends SystemServiceRegistry.CachedServiceFetcher<TetheringManager> {
  public TetheringManager createService(ContextImpl paramContextImpl) {
    return new TetheringManager(paramContextImpl, (Supplier)_$$Lambda$SystemServiceRegistry$16$s6mZ42tuGUunhKa_5iwjLY5FGdM.INSTANCE);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$16.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */