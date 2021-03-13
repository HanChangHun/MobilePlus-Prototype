package android.graphics;

import android.os.Handler;
import com.android.internal.util.VirtualRefBasePtr;

public class HardwareRendererObserver {
  private final long[] mFrameMetrics;
  
  private final Handler mHandler;
  
  private final OnFrameMetricsAvailableListener mListener;
  
  private VirtualRefBasePtr mNativePtr;
  
  public HardwareRendererObserver(OnFrameMetricsAvailableListener paramOnFrameMetricsAvailableListener, long[] paramArrayOflong, Handler paramHandler) {
    if (paramHandler != null && paramHandler.getLooper() != null) {
      if (paramHandler.getLooper().getQueue() != null) {
        this.mFrameMetrics = paramArrayOflong;
        this.mHandler = paramHandler;
        this.mListener = paramOnFrameMetricsAvailableListener;
        this.mNativePtr = new VirtualRefBasePtr(nCreateObserver());
        return;
      } 
      throw new IllegalStateException("invalid looper, null message queue\n");
    } 
    throw new NullPointerException("handler and its looper cannot be null");
  }
  
  private native long nCreateObserver();
  
  private static native int nGetNextBuffer(long paramLong, long[] paramArrayOflong);
  
  private void notifyDataAvailable() {
    this.mHandler.post(new _$$Lambda$HardwareRendererObserver$Z08IFApIdHoCBfw8BsQ_lVjpUlI(this));
  }
  
  long getNativeInstance() {
    return this.mNativePtr.get();
  }
  
  public static interface OnFrameMetricsAvailableListener {
    void onFrameMetricsAvailable(int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/HardwareRendererObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */