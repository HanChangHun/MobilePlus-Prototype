package android.graphics;

import android.view.Surface;
import android.view.SurfaceControl;

public final class BLASTBufferQueue {
  private long mNativeObject;
  
  public BLASTBufferQueue(SurfaceControl paramSurfaceControl, int paramInt1, int paramInt2, boolean paramBoolean) {
    this.mNativeObject = nativeCreate(paramSurfaceControl.mNativeObject, paramInt1, paramInt2, paramBoolean);
  }
  
  private static native long nativeCreate(long paramLong1, long paramLong2, long paramLong3, boolean paramBoolean);
  
  private static native void nativeDestroy(long paramLong);
  
  private static native Surface nativeGetSurface(long paramLong);
  
  private static native void nativeSetNextTransaction(long paramLong1, long paramLong2);
  
  private static native void nativeUpdate(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
  
  public void destroy() {
    nativeDestroy(this.mNativeObject);
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mNativeObject != 0L)
        nativeDestroy(this.mNativeObject); 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public Surface getSurface() {
    return nativeGetSurface(this.mNativeObject);
  }
  
  public void setNextTransaction(SurfaceControl.Transaction paramTransaction) {
    nativeSetNextTransaction(this.mNativeObject, paramTransaction.mNativeObject);
  }
  
  public void update(SurfaceControl paramSurfaceControl, int paramInt1, int paramInt2) {
    nativeUpdate(this.mNativeObject, paramSurfaceControl.mNativeObject, paramInt1, paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BLASTBufferQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */