package android.graphics;

import java.io.InputStream;
import java.io.OutputStream;

public class Picture {
  private static final int WORKING_STREAM_STORAGE = 16384;
  
  private long mNativePicture;
  
  private PictureCanvas mRecordingCanvas;
  
  private boolean mRequiresHwAcceleration;
  
  public Picture() {
    this(nativeConstructor(0L));
  }
  
  public Picture(long paramLong) {
    if (paramLong != 0L) {
      this.mNativePicture = paramLong;
      return;
    } 
    throw new IllegalArgumentException();
  }
  
  public Picture(Picture paramPicture) {
    this(nativeConstructor(l));
  }
  
  @Deprecated
  public static Picture createFromStream(InputStream paramInputStream) {
    return new Picture(nativeCreateFromStream(paramInputStream, new byte[16384]));
  }
  
  private static native long nativeBeginRecording(long paramLong, int paramInt1, int paramInt2);
  
  private static native long nativeConstructor(long paramLong);
  
  private static native long nativeCreateFromStream(InputStream paramInputStream, byte[] paramArrayOfbyte);
  
  private static native void nativeDestructor(long paramLong);
  
  private static native void nativeDraw(long paramLong1, long paramLong2);
  
  private static native void nativeEndRecording(long paramLong);
  
  private static native int nativeGetHeight(long paramLong);
  
  private static native int nativeGetWidth(long paramLong);
  
  private static native boolean nativeWriteToStream(long paramLong, OutputStream paramOutputStream, byte[] paramArrayOfbyte);
  
  private void verifyValid() {
    if (this.mNativePicture != 0L)
      return; 
    throw new IllegalStateException("Picture is destroyed");
  }
  
  public Canvas beginRecording(int paramInt1, int paramInt2) {
    verifyValid();
    if (this.mRecordingCanvas == null) {
      PictureCanvas pictureCanvas = new PictureCanvas(this, nativeBeginRecording(this.mNativePicture, paramInt1, paramInt2));
      this.mRecordingCanvas = pictureCanvas;
      this.mRequiresHwAcceleration = false;
      return pictureCanvas;
    } 
    throw new IllegalStateException("Picture already recording, must call #endRecording()");
  }
  
  public void close() {
    long l = this.mNativePicture;
    if (l != 0L) {
      nativeDestructor(l);
      this.mNativePicture = 0L;
    } 
  }
  
  public void draw(Canvas paramCanvas) {
    verifyValid();
    if (this.mRecordingCanvas != null)
      endRecording(); 
    if (this.mRequiresHwAcceleration && !paramCanvas.isHardwareAccelerated())
      paramCanvas.onHwBitmapInSwMode(); 
    nativeDraw(paramCanvas.getNativeCanvasWrapper(), this.mNativePicture);
  }
  
  public void endRecording() {
    verifyValid();
    PictureCanvas pictureCanvas = this.mRecordingCanvas;
    if (pictureCanvas != null) {
      this.mRequiresHwAcceleration = pictureCanvas.mHoldsHwBitmap;
      this.mRecordingCanvas = null;
      nativeEndRecording(this.mNativePicture);
    } 
  }
  
  protected void finalize() throws Throwable {
    try {
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getHeight() {
    verifyValid();
    return nativeGetHeight(this.mNativePicture);
  }
  
  public int getWidth() {
    verifyValid();
    return nativeGetWidth(this.mNativePicture);
  }
  
  public boolean requiresHardwareAcceleration() {
    verifyValid();
    return this.mRequiresHwAcceleration;
  }
  
  @Deprecated
  public void writeToStream(OutputStream paramOutputStream) {
    verifyValid();
    if (paramOutputStream != null) {
      if (nativeWriteToStream(this.mNativePicture, paramOutputStream, new byte[16384]))
        return; 
      throw new RuntimeException();
    } 
    throw new IllegalArgumentException("stream cannot be null");
  }
  
  private static class PictureCanvas extends Canvas {
    boolean mHoldsHwBitmap;
    
    private final Picture mPicture;
    
    public PictureCanvas(Picture param1Picture, long param1Long) {
      super(param1Long);
      this.mPicture = param1Picture;
      this.mDensity = 0;
    }
    
    public void drawPicture(Picture param1Picture) {
      if (this.mPicture != param1Picture) {
        super.drawPicture(param1Picture);
        return;
      } 
      throw new RuntimeException("Cannot draw a picture into its recording canvas");
    }
    
    protected void onHwBitmapInSwMode() {
      this.mHoldsHwBitmap = true;
    }
    
    public void setBitmap(Bitmap param1Bitmap) {
      throw new RuntimeException("Cannot call setBitmap on a picture canvas");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Picture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */