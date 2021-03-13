package android.app;

import android.annotation.SystemApi;
import android.os.IBinder;

@SystemApi
public interface StaticServiceProducerWithBinder<TServiceClass> {
  TServiceClass createService(IBinder paramIBinder);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$StaticServiceProducerWithBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */