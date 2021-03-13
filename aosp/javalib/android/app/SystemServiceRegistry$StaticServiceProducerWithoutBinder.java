package android.app;

import android.annotation.SystemApi;

@SystemApi
public interface StaticServiceProducerWithoutBinder<TServiceClass> {
  TServiceClass createService();
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$StaticServiceProducerWithoutBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */