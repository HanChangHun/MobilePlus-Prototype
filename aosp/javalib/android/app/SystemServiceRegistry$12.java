package android.app;

import android.os.ServiceManager;
import android.view.textclassifier.TextClassificationManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<TextClassificationManager> {
  public TextClassificationManager createService(ContextImpl paramContextImpl) {
    return new TextClassificationManager(paramContextImpl);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$12.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */