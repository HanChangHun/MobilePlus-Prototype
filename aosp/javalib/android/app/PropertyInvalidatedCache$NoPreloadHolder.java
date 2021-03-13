package android.app;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

final class NoPreloadHolder {
  private static final AtomicLong sNextNonce = new AtomicLong((new Random()).nextLong());
  
  public static long next() {
    return sNextNonce.getAndIncrement();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PropertyInvalidatedCache$NoPreloadHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */