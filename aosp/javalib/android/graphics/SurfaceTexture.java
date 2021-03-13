package android.graphics;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import java.lang.ref.WeakReference;

public class SurfaceTexture {
  private final Looper mCreatorLooper = Looper.myLooper();
  
  private long mFrameAvailableListener;
  
  private boolean mIsSingleBuffered;
  
  private Handler mOnFrameAvailableHandler;
  
  private long mProducer;
  
  private long mSurfaceTexture;
  
  public SurfaceTexture(int paramInt) {
    this(paramInt, false);
  }
  
  public SurfaceTexture(int paramInt, boolean paramBoolean) {
    this.mIsSingleBuffered = paramBoolean;
    nativeInit(false, paramInt, paramBoolean, new WeakReference<>(this));
  }
  
  public SurfaceTexture(boolean paramBoolean) {
    this.mIsSingleBuffered = paramBoolean;
    nativeInit(true, 0, paramBoolean, new WeakReference<>(this));
  }
  
  private native int nativeAttachToGLContext(int paramInt);
  
  private native int nativeDetachFromGLContext();
  
  private native void nativeFinalize();
  
  private native long nativeGetTimestamp();
  
  private native void nativeGetTransformMatrix(float[] paramArrayOffloat);
  
  private native void nativeInit(boolean paramBoolean1, int paramInt, boolean paramBoolean2, WeakReference<SurfaceTexture> paramWeakReference) throws Surface.OutOfResourcesException;
  
  private native boolean nativeIsReleased();
  
  private native void nativeRelease();
  
  private native void nativeReleaseTexImage();
  
  private native void nativeSetDefaultBufferSize(int paramInt1, int paramInt2);
  
  private native void nativeUpdateTexImage();
  
  private static void postEventFromNative(WeakReference<SurfaceTexture> paramWeakReference) {
    SurfaceTexture surfaceTexture = paramWeakReference.get();
    if (surfaceTexture != null) {
      Handler handler = surfaceTexture.mOnFrameAvailableHandler;
      if (handler != null)
        handler.sendEmptyMessage(0); 
    } 
  }
  
  public void attachToGLContext(int paramInt) {
    if (nativeAttachToGLContext(paramInt) == 0)
      return; 
    throw new RuntimeException("Error during attachToGLContext (see logcat for details)");
  }
  
  public void detachFromGLContext() {
    if (nativeDetachFromGLContext() == 0)
      return; 
    throw new RuntimeException("Error during detachFromGLContext (see logcat for details)");
  }
  
  protected void finalize() throws Throwable {
    try {
      nativeFinalize();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public long getTimestamp() {
    return nativeGetTimestamp();
  }
  
  public void getTransformMatrix(float[] paramArrayOffloat) {
    if (paramArrayOffloat.length == 16) {
      nativeGetTransformMatrix(paramArrayOffloat);
      return;
    } 
    throw new IllegalArgumentException();
  }
  
  public boolean isReleased() {
    return nativeIsReleased();
  }
  
  public boolean isSingleBuffered() {
    return this.mIsSingleBuffered;
  }
  
  public void release() {
    nativeRelease();
  }
  
  public void releaseTexImage() {
    nativeReleaseTexImage();
  }
  
  public void setDefaultBufferSize(int paramInt1, int paramInt2) {
    nativeSetDefaultBufferSize(paramInt1, paramInt2);
  }
  
  public void setOnFrameAvailableListener(OnFrameAvailableListener paramOnFrameAvailableListener) {
    setOnFrameAvailableListener(paramOnFrameAvailableListener, null);
  }
  
  public void setOnFrameAvailableListener(final OnFrameAvailableListener listener, Handler paramHandler) {
    if (listener != null) {
      Looper looper;
      if (paramHandler != null) {
        looper = paramHandler.getLooper();
      } else {
        looper = this.mCreatorLooper;
        if (looper == null)
          looper = Looper.getMainLooper(); 
      } 
      this.mOnFrameAvailableHandler = new Handler(looper, null, true) {
          public void handleMessage(Message param1Message) {
            listener.onFrameAvailable(SurfaceTexture.this);
          }
        };
    } else {
      this.mOnFrameAvailableHandler = null;
    } 
  }
  
  public void updateTexImage() {
    nativeUpdateTexImage();
  }
  
  public static interface OnFrameAvailableListener {
    void onFrameAvailable(SurfaceTexture param1SurfaceTexture);
  }
  
  @Deprecated
  public static class OutOfResourcesException extends Exception {
    public OutOfResourcesException() {}
    
    public OutOfResourcesException(String param1String) {
      super(param1String);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/SurfaceTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */