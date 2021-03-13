package android.graphics.pdf;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import libcore.io.IoUtils;

public final class PdfRenderer implements AutoCloseable {
  static final Object sPdfiumLock = new Object();
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private Page mCurrentPage;
  
  private ParcelFileDescriptor mInput;
  
  private long mNativeDocument;
  
  private final int mPageCount;
  
  private final Point mTempPoint = new Point();
  
  public PdfRenderer(ParcelFileDescriptor paramParcelFileDescriptor) throws IOException {
    if (paramParcelFileDescriptor != null)
      try {
        Os.lseek(paramParcelFileDescriptor.getFileDescriptor(), 0L, OsConstants.SEEK_SET);
        long l = (Os.fstat(paramParcelFileDescriptor.getFileDescriptor())).st_size;
        this.mInput = paramParcelFileDescriptor;
        synchronized (sPdfiumLock) {
          l = nativeCreate(this.mInput.getFd(), l);
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
    Page page = this.mCurrentPage;
    if (page != null) {
      page.close();
      this.mCurrentPage = null;
    } 
    if (this.mNativeDocument != 0L)
      synchronized (sPdfiumLock) {
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
  
  private static native void nativeClosePage(long paramLong);
  
  private static native long nativeCreate(int paramInt, long paramLong);
  
  private static native int nativeGetPageCount(long paramLong);
  
  private static native long nativeOpenPageAndGetSize(long paramLong, int paramInt, Point paramPoint);
  
  private static native void nativeRenderPage(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong4, int paramInt5);
  
  private static native boolean nativeScaleForPrinting(long paramLong);
  
  private void throwIfClosed() {
    if (this.mInput != null)
      return; 
    throw new IllegalStateException("Already closed");
  }
  
  private void throwIfPageNotInDocument(int paramInt) {
    if (paramInt >= 0 && paramInt < this.mPageCount)
      return; 
    throw new IllegalArgumentException("Invalid page index");
  }
  
  private void throwIfPageOpened() {
    if (this.mCurrentPage == null)
      return; 
    throw new IllegalStateException("Current page not closed");
  }
  
  public void close() {
    throwIfClosed();
    throwIfPageOpened();
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
  
  public Page openPage(int paramInt) {
    throwIfClosed();
    throwIfPageOpened();
    throwIfPageNotInDocument(paramInt);
    Page page = new Page(paramInt);
    this.mCurrentPage = page;
    return page;
  }
  
  public boolean shouldScaleForPrinting() {
    throwIfClosed();
    synchronized (sPdfiumLock) {
      return nativeScaleForPrinting(this.mNativeDocument);
    } 
  }
  
  public final class Page implements AutoCloseable {
    public static final int RENDER_MODE_FOR_DISPLAY = 1;
    
    public static final int RENDER_MODE_FOR_PRINT = 2;
    
    private final CloseGuard mCloseGuard = CloseGuard.get();
    
    private final int mHeight;
    
    private final int mIndex;
    
    private long mNativePage;
    
    private final int mWidth;
    
    private Page(int param1Int) {
      Point point = PdfRenderer.this.mTempPoint;
      synchronized (PdfRenderer.sPdfiumLock) {
        this.mNativePage = PdfRenderer.nativeOpenPageAndGetSize(PdfRenderer.this.mNativeDocument, param1Int, point);
        this.mIndex = param1Int;
        this.mWidth = point.x;
        this.mHeight = point.y;
        this.mCloseGuard.open("close");
        return;
      } 
    }
    
    private void doClose() {
      if (this.mNativePage != 0L)
        synchronized (PdfRenderer.sPdfiumLock) {
          PdfRenderer.nativeClosePage(this.mNativePage);
          this.mNativePage = 0L;
        }  
      this.mCloseGuard.close();
      PdfRenderer.access$602(PdfRenderer.this, null);
    }
    
    private void throwIfClosed() {
      if (this.mNativePage != 0L)
        return; 
      throw new IllegalStateException("Already closed");
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
    
    public int getHeight() {
      return this.mHeight;
    }
    
    public int getIndex() {
      return this.mIndex;
    }
    
    public int getWidth() {
      return this.mWidth;
    }
    
    public void render(Bitmap param1Bitmap, Rect param1Rect, Matrix param1Matrix, int param1Int) {
      if (this.mNativePage != 0L) {
        Bitmap bitmap = (Bitmap)Preconditions.checkNotNull(param1Bitmap, "bitmap null");
        if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
          if (param1Rect == null || (param1Rect.left >= 0 && param1Rect.top >= 0 && param1Rect.right <= bitmap.getWidth() && param1Rect.bottom <= bitmap.getHeight())) {
            if (param1Matrix == null || param1Matrix.isAffine()) {
              if (param1Int == 2 || param1Int == 1) {
                if (param1Int != 2 || param1Int != 1) {
                  Matrix matrix;
                  byte b;
                  int j;
                  int k;
                  int i = 0;
                  if (param1Rect != null) {
                    b = param1Rect.left;
                  } else {
                    b = 0;
                  } 
                  if (param1Rect != null)
                    i = param1Rect.top; 
                  if (param1Rect != null) {
                    j = param1Rect.right;
                  } else {
                    j = bitmap.getWidth();
                  } 
                  if (param1Rect != null) {
                    k = param1Rect.bottom;
                  } else {
                    k = bitmap.getHeight();
                  } 
                  if (param1Matrix == null) {
                    matrix = new Matrix();
                    matrix.postScale((j - b) / getWidth(), (k - i) / getHeight());
                    matrix.postTranslate(b, i);
                  } else {
                    matrix = param1Matrix;
                  } 
                  long l = matrix.native_instance;
                  Object object = PdfRenderer.sPdfiumLock;
                  /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
                  try {
                    long l1 = PdfRenderer.this.mNativeDocument;
                    long l2 = this.mNativePage;
                    long l3 = bitmap.getNativeInstance();
                    try {
                      PdfRenderer.nativeRenderPage(l1, l2, l3, b, i, j, k, l, param1Int);
                      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
                      return;
                    } finally {}
                  } finally {}
                  /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
                  throw matrix;
                } 
                throw new IllegalArgumentException("Only single render mode supported");
              } 
              throw new IllegalArgumentException("Unsupported render mode");
            } 
            throw new IllegalArgumentException("transform not affine");
          } 
          throw new IllegalArgumentException("destBounds not in destination");
        } 
        throw new IllegalArgumentException("Unsupported pixel format");
      } 
      throw null;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface RenderMode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/pdf/PdfRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */