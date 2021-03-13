package android.app.blob;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.IBinder;

public class BlobStoreManagerFrameworkInitializer {
  public static void initialize() {
    SystemServiceRegistry.registerContextAwareService("blob_store", BlobStoreManager.class, (SystemServiceRegistry.ContextAwareServiceProducerWithBinder)_$$Lambda$BlobStoreManagerFrameworkInitializer$WjSRSHMmxWPF4Fq_7TpX23MBh2U.INSTANCE);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/blob/BlobStoreManagerFrameworkInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */