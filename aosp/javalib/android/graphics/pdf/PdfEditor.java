package android.graphics.pdf;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import dalvik.system.CloseGuard;
import java.io.IOException;
import libcore.io.IoUtils;

public final class PdfEditor {
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private ParcelFileDescriptor mInput;
  
  private long mNativeDocument;
  
  private int mPageCount;
  
  public PdfEditor(ParcelFileDescriptor paramParcelFileDescriptor) throws IOException {
    if (paramParcelFileDescriptor != null)
      try {
        Os.lseek(paramParcelFileDescriptor.getFileDescriptor(), 0L, OsConstants.SEEK_SET);
        long l = (Os.fstat(paramParcelFileDescriptor.getFileDescriptor())).st_size;
        this.mInput = paramParcelFileDescriptor;
        synchronized (PdfRenderer.sPdfiumLock) {
          l = nativeOpen(this.mInput.getFd(), l);
          this.mNativeDocument = l;
          try {
            return;
          } finally {
            nativeClose(this.mNativeDocument);
            this.mNativeDocument = 0L;
          } 
        } 
      } catch (ErrnoException errnoException) {
        throw new IllegalArgumentException("file descriptor not seekable");
      }  
    throw new NullPointerException("input cannot be null");
  }
  
  private void doClose() {
    if (this.mNativeDocument != 0L)
      synchronized (PdfRenderer.sPdfiumLock) {
        nativeClose(this.mNativeDocument);
        this.mNativeDocument = 0L;
      }  
    ParcelFileDescriptor parcelFileDescriptor = this.mInput;
    if (parcelFileDescriptor != null) {
      IoUtils.closeQuietly((AutoCloseable)parcelFileDescriptor);
      this.mInput = null;
    } 
    this.mCloseGuard.close();
  }
  
  private static native void nativeClose(long paramLong);
  
  private static native int nativeGetPageCount(long paramLong);
  
  private static native boolean nativeGetPageCropBox(long paramLong, int paramInt, Rect paramRect);
  
  private static native boolean nativeGetPageMediaBox(long paramLong, int paramInt, Rect paramRect);
  
  private static native void nativeGetPageSize(long paramLong, int paramInt, Point paramPoint);
  
  private static native long nativeOpen(int paramInt, long paramLong);
  
  private static native int nativeRemovePage(long paramLong, int paramInt);
  
  private static native boolean nativeScaleForPrinting(long paramLong);
  
  private static native void nativeSetPageCropBox(long paramLong, int paramInt, Rect paramRect);
  
  private static native void nativeSetPageMediaBox(long paramLong, int paramInt, Rect paramRect);
  
  private static native void nativeSetTransformAndClip(long paramLong1, int paramInt1, long paramLong2, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  private static native void nativeWrite(long paramLong, int paramInt);
  
  private void throwIfClosed() {
    if (this.mInput != null)
      return; 
    throw new IllegalStateException("Already closed");
  }
  
  private void throwIfCropBoxNull(Rect paramRect) {
    if (paramRect != null)
      return; 
    throw new NullPointerException("cropBox cannot be null");
  }
  
  private void throwIfMediaBoxNull(Rect paramRect) {
    if (paramRect != null)
      return; 
    throw new NullPointerException("mediaBox cannot be null");
  }
  
  private void throwIfNotNullAndNotAfine(Matrix paramMatrix) {
    if (paramMatrix == null || paramMatrix.isAffine())
      return; 
    throw new IllegalStateException("Matrix must be afine");
  }
  
  private void throwIfOutCropBoxNull(Rect paramRect) {
    if (paramRect != null)
      return; 
    throw new NullPointerException("outCropBox cannot be null");
  }
  
  private void throwIfOutMediaBoxNull(Rect paramRect) {
    if (paramRect != null)
      return; 
    throw new NullPointerException("outMediaBox cannot be null");
  }
  
  private void throwIfOutSizeNull(Point paramPoint) {
    if (paramPoint != null)
      return; 
    throw new NullPointerException("outSize cannot be null");
  }
  
  private void throwIfPageNotInDocument(int paramInt) {
    if (paramInt >= 0 && paramInt < this.mPageCount)
      return; 
    throw new IllegalArgumentException("Invalid page index");
  }
  
  public void close() {
    throwIfClosed();
    doClose();
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      doClose();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getPageCount() {
    throwIfClosed();
    return this.mPageCount;
  }
  
  public boolean getPageCropBox(int paramInt, Rect paramRect) {
    throwIfClosed();
    throwIfOutCropBoxNull(paramRect);
    throwIfPageNotInDocument(paramInt);
    synchronized (PdfRenderer.sPdfiumLock) {
      return nativeGetPageCropBox(this.mNativeDocument, paramInt, paramRect);
    } 
  }
  
  public boolean getPageMediaBox(int paramInt, Rect paramRect) {
    throwIfClosed();
    throwIfOutMediaBoxNull(paramRect);
    throwIfPageNotInDocument(paramInt);
    synchronized (PdfRenderer.sPdfiumLock) {
      return nativeGetPageMediaBox(this.mNativeDocument, paramInt, paramRect);
    } 
  }
  
  public void getPageSize(int paramInt, Point paramPoint) {
    throwIfClosed();
    throwIfOutSizeNull(paramPoint);
    throwIfPageNotInDocument(paramInt);
    synchronized (PdfRenderer.sPdfiumLock) {
      nativeGetPageSize(this.mNativeDocument, paramInt, paramPoint);
      return;
    } 
  }
  
  public void removePage(int paramInt) {
    throwIfClosed();
    throwIfPageNotInDocument(paramInt);
    synchronized (PdfRenderer.sPdfiumLock) {
      this.mPageCount = nativeRemovePage(this.mNativeDocument, paramInt);
      return;
    } 
  }
  
  public void setPageCropBox(int paramInt, Rect paramRect) {
    throwIfClosed();
    throwIfCropBoxNull(paramRect);
    throwIfPageNotInDocument(paramInt);
    synchronized (PdfRenderer.sPdfiumLock) {
      nativeSetPageCropBox(this.mNativeDocument, paramInt, paramRect);
      return;
    } 
  }
  
  public void setPageMediaBox(int paramInt, Rect paramRect) {
    throwIfClosed();
    throwIfMediaBoxNull(paramRect);
    throwIfPageNotInDocument(paramInt);
    synchronized (PdfRenderer.sPdfiumLock) {
      nativeSetPageMediaBox(this.mNativeDocument, paramInt, paramRect);
      return;
    } 
  }
  
  public void setTransformAndClip(int paramInt, Matrix paramMatrix, Rect paramRect) {
    throwIfClosed();
    throwIfPageNotInDocument(paramInt);
    throwIfNotNullAndNotAfine(paramMatrix);
    Matrix matrix = paramMatrix;
    if (paramMatrix == null)
      matrix = Matrix.IDENTITY_MATRIX; 
    if (paramRect == null) {
      null = new Point();
      getPageSize(paramInt, null);
      synchronized (PdfRenderer.sPdfiumLock) {
        nativeSetTransformAndClip(this.mNativeDocument, paramInt, matrix.native_instance, 0, 0, null.x, null.y);
      } 
    } else {
      synchronized (PdfRenderer.sPdfiumLock) {
        nativeSetTransformAndClip(this.mNativeDocument, paramInt, matrix.native_instance, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
        return;
      } 
    } 
  }
  
  public boolean shouldScaleForPrinting() {
    throwIfClosed();
    synchronized (PdfRenderer.sPdfiumLock) {
      return nativeScaleForPrinting(this.mNativeDocument);
    } 
  }
  
  public void write(ParcelFileDescriptor paramParcelFileDescriptor) throws IOException {
    try {
      throwIfClosed();
    } finally {
      IoUtils.closeQuietly((AutoCloseable)paramParcelFileDescriptor);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/pdf/PdfEditor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */