package android.app;

import android.os.ServiceManager;
import android.os.SystemVibrator;
import android.os.Vibrator;

class null extends SystemServiceRegistry.CachedServiceFetcher<Vibrator> {
  public Vibrator createService(ContextImpl paramContextImpl) {
    return (Vibrator)new SystemVibrator(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$55.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */