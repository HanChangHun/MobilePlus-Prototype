package android.hardware.camera2.impl;

import android.os.Handler;
import java.util.Objects;
import java.util.concurrent.Executor;

class CameraHandlerExecutor implements Executor {
  private final Handler mHandler;
  
  public CameraHandlerExecutor(Handler paramHandler) {
    Objects.requireNonNull(paramHandler);
    this.mHandler = paramHandler;
  }
  
  public void execute(Runnable paramRunnable) {
    this.mHandler.post(paramRunnable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/impl/CameraDeviceImpl$CameraHandlerExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */