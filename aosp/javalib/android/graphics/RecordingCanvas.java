package android.graphics;

import android.util.Pools;
import android.view.DisplayListCanvas;
import android.view.TextureLayer;
import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;

public final class RecordingCanvas extends DisplayListCanvas {
  public static final int MAX_BITMAP_SIZE = 104857600;
  
  private static final int POOL_LIMIT = 25;
  
  private static final Pools.SynchronizedPool<RecordingCanvas> sPool = new Pools.SynchronizedPool(25);
  
  private int mHeight;
  
  public RenderNode mNode;
  
  private int mWidth;
  
  protected RecordingCanvas(RenderNode paramRenderNode, int paramInt1, int paramInt2) {
    super(nCreateDisplayListCanvas(paramRenderNode.mNativeRenderNode, paramInt1, paramInt2));
  }
  
  @FastNative
  private static native void nCallDrawGLFunction(long paramLong1, long paramLong2, Runnable paramRunnable);
  
  @CriticalNative
  private static native long nCreateDisplayListCanvas(long paramLong, int paramInt1, int paramInt2);
  
  @CriticalNative
  private static native void nDrawCircle(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5);
  
  @CriticalNative
  private static native void nDrawRenderNode(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nDrawRoundRect(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8);
  
  @CriticalNative
  private static native void nDrawTextureLayer(long paramLong1, long paramLong2);
  
  @CriticalNative
  private static native void nDrawWebViewFunctor(long paramLong, int paramInt);
  
  @CriticalNative
  private static native long nFinishRecording(long paramLong);
  
  @CriticalNative
  private static native int nGetMaximumTextureHeight();
  
  @CriticalNative
  private static native int nGetMaximumTextureWidth();
  
  @CriticalNative
  private static native void nInsertReorderBarrier(long paramLong, boolean paramBoolean);
  
  @CriticalNative
  private static native void nResetDisplayListCanvas(long paramLong1, long paramLong2, int paramInt1, int paramInt2);
  
  static RecordingCanvas obtain(RenderNode paramRenderNode, int paramInt1, int paramInt2) {
    if (paramRenderNode != null) {
      RecordingCanvas recordingCanvas = (RecordingCanvas)sPool.acquire();
      if (recordingCanvas == null) {
        recordingCanvas = new RecordingCanvas(paramRenderNode, paramInt1, paramInt2);
      } else {
        nResetDisplayListCanvas(recordingCanvas.mNativeCanvasWrapper, paramRenderNode.mNativeRenderNode, paramInt1, paramInt2);
      } 
      recordingCanvas.mNode = paramRenderNode;
      recordingCanvas.mWidth = paramInt1;
      recordingCanvas.mHeight = paramInt2;
      return recordingCanvas;
    } 
    throw new IllegalArgumentException("node cannot be null");
  }
  
  @Deprecated
  public void callDrawGLFunction2(long paramLong) {
    nCallDrawGLFunction(this.mNativeCanvasWrapper, paramLong, (Runnable)null);
  }
  
  public void disableZ() {
    nInsertReorderBarrier(this.mNativeCanvasWrapper, false);
  }
  
  public void drawCircle(CanvasProperty<Float> paramCanvasProperty1, CanvasProperty<Float> paramCanvasProperty2, CanvasProperty<Float> paramCanvasProperty3, CanvasProperty<Paint> paramCanvasProperty) {
    nDrawCircle(this.mNativeCanvasWrapper, paramCanvasProperty1.getNativeContainer(), paramCanvasProperty2.getNativeContainer(), paramCanvasProperty3.getNativeContainer(), paramCanvasProperty.getNativeContainer());
  }
  
  @Deprecated
  public void drawGLFunctor2(long paramLong, Runnable paramRunnable) {
    nCallDrawGLFunction(this.mNativeCanvasWrapper, paramLong, paramRunnable);
  }
  
  public void drawRenderNode(RenderNode paramRenderNode) {
    nDrawRenderNode(this.mNativeCanvasWrapper, paramRenderNode.mNativeRenderNode);
  }
  
  public void drawRoundRect(CanvasProperty<Float> paramCanvasProperty1, CanvasProperty<Float> paramCanvasProperty2, CanvasProperty<Float> paramCanvasProperty3, CanvasProperty<Float> paramCanvasProperty4, CanvasProperty<Float> paramCanvasProperty5, CanvasProperty<Float> paramCanvasProperty6, CanvasProperty<Paint> paramCanvasProperty) {
    nDrawRoundRect(this.mNativeCanvasWrapper, paramCanvasProperty1.getNativeContainer(), paramCanvasProperty2.getNativeContainer(), paramCanvasProperty3.getNativeContainer(), paramCanvasProperty4.getNativeContainer(), paramCanvasProperty5.getNativeContainer(), paramCanvasProperty6.getNativeContainer(), paramCanvasProperty.getNativeContainer());
  }
  
  public void drawTextureLayer(TextureLayer paramTextureLayer) {
    nDrawTextureLayer(this.mNativeCanvasWrapper, paramTextureLayer.getLayerHandle());
  }
  
  public void drawWebViewFunctor(int paramInt) {
    nDrawWebViewFunctor(this.mNativeCanvasWrapper, paramInt);
  }
  
  public void enableZ() {
    nInsertReorderBarrier(this.mNativeCanvasWrapper, true);
  }
  
  long finishRecording() {
    return nFinishRecording(this.mNativeCanvasWrapper);
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public int getMaximumBitmapHeight() {
    return nGetMaximumTextureHeight();
  }
  
  public int getMaximumBitmapWidth() {
    return nGetMaximumTextureWidth();
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public boolean isHardwareAccelerated() {
    return true;
  }
  
  public boolean isOpaque() {
    return false;
  }
  
  public boolean isRecordingFor(Object paramObject) {
    boolean bool;
    if (paramObject == this.mNode) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  void recycle() {
    this.mNode = null;
    sPool.release(this);
  }
  
  public void setBitmap(Bitmap paramBitmap) {
    throw new UnsupportedOperationException();
  }
  
  public void setDensity(int paramInt) {}
  
  protected void throwIfCannotDraw(Bitmap paramBitmap) {
    super.throwIfCannotDraw(paramBitmap);
    int i = paramBitmap.getByteCount();
    if (i <= 104857600)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Canvas: trying to draw too large(");
    stringBuilder.append(i);
    stringBuilder.append("bytes) bitmap.");
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RecordingCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */