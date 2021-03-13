package android.hardware;

import android.os.MemoryFile;
import dalvik.system.CloseGuard;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.channels.Channel;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SensorDirectChannel implements Channel {
  public static final int RATE_FAST = 2;
  
  public static final int RATE_NORMAL = 1;
  
  public static final int RATE_STOP = 0;
  
  public static final int RATE_VERY_FAST = 3;
  
  public static final int TYPE_HARDWARE_BUFFER = 2;
  
  public static final int TYPE_MEMORY_FILE = 1;
  
  private final CloseGuard mCloseGuard;
  
  private final AtomicBoolean mClosed = new AtomicBoolean();
  
  private final SensorManager mManager;
  
  private final int mNativeHandle;
  
  private final long mSize;
  
  private final int mType;
  
  SensorDirectChannel(SensorManager paramSensorManager, int paramInt1, int paramInt2, long paramLong) {
    CloseGuard closeGuard = CloseGuard.get();
    this.mCloseGuard = closeGuard;
    this.mManager = paramSensorManager;
    this.mNativeHandle = paramInt1;
    this.mType = paramInt2;
    this.mSize = paramLong;
    closeGuard.open("SensorDirectChannel");
  }
  
  static long[] encodeData(MemoryFile paramMemoryFile) {
    byte b;
    try {
      b = paramMemoryFile.getFileDescriptor().getInt$();
    } catch (IOException iOException) {
      b = -1;
    } 
    return new long[] { 1L, 0L, b };
  }
  
  public void close() {
    if (this.mClosed.compareAndSet(false, true)) {
      this.mCloseGuard.close();
      this.mManager.destroyDirectChannel(this);
    } 
  }
  
  public int configure(Sensor paramSensor, int paramInt) {
    return this.mManager.configureDirectChannelImpl(this, paramSensor, paramInt);
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  int getNativeHandle() {
    return this.mNativeHandle;
  }
  
  public boolean isOpen() {
    return this.mClosed.get() ^ true;
  }
  
  @Deprecated
  public boolean isValid() {
    return isOpen();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface MemoryType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RateLevel {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/SensorDirectChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */