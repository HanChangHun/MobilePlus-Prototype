package android.hardware.camera2.utils;

public class ScopedLock implements AutoCloseable {
  private ScopedLock() {}
  
  public void close() {
    CloseableLock.this.releaseLock();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/CloseableLock$ScopedLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */