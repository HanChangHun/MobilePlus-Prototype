package android.hardware.camera2.legacy;

import android.util.AndroidException;

public class BufferQueueAbandonedException extends AndroidException {
  public BufferQueueAbandonedException() {}
  
  public BufferQueueAbandonedException(Exception paramException) {
    super(paramException);
  }
  
  public BufferQueueAbandonedException(String paramString) {
    super(paramString);
  }
  
  public BufferQueueAbandonedException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyExceptionUtils$BufferQueueAbandonedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */