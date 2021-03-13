package android.graphics.pdf;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;

public final class Page implements AutoCloseable {
  public static final int RENDER_MODE_FOR_DISPLAY = 1;
  
  public static final int RENDER_MODE_FOR_PRINT = 2;
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private final int mHeight;
  
  private final int mIndex;
  
  private long mNativePage;
  
  private final int mWidth;
  
  private Page(int paramInt) {
    Point point = PdfRenderer.access$100(paramPdfRenderer);
    synchronized (PdfRenderer.sPdfiumLock) {
      this.mNativePage = PdfRenderer.access$300(PdfRenderer.access$200(paramPdfRenderer), paramInt, point);
      this.mIndex = paramInt;
      this.mWidth = point.x;
      this.mHeight = point.y;
      this.mCloseGuard.open("close");
      return;
    } 
  }
  
  private void doClose() {
    if (this.mNativePage != 0L)
      synchronized (PdfRenderer.sPdfiumLock) {
        PdfRenderer.access$500(this.mNativePage);
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
  
  public void render(Bitmap paramBitmap, Rect paramRect, Matrix paramMatrix, int paramInt) {
    if (this.mNativePage != 0L) {
      Bitmap bitmap = (Bitmap)Preconditions.checkNotNull(paramBitmap, "bitmap null");
      if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
        if (paramRect == null || (paramRect.left >= 0 && paramRect.top >= 0 && paramRect.right <= bitmap.getWidth() && paramRect.bottom <= bitmap.getHeight())) {
          if (paramMatrix == null || paramMatrix.isAffine()) {
            if (paramInt == 2 || paramInt == 1) {
              if (paramInt != 2 || paramInt != 1) {
                Matrix matrix;
                byte b;
                int j;
                int k;
                int i = 0;
                if (paramRect != null) {
                  b = paramRect.left;
                } else {
                  b = 0;
                } 
                if (paramRect != null)
                  i = paramRect.top; 
                if (paramRect != null) {
                  j = paramRect.right;
                } else {
                  j = bitmap.getWidth();
                } 
                if (paramRect != null) {
                  k = paramRect.bottom;
                } else {
                  k = bitmap.getHeight();
                } 
                if (paramMatrix == null) {
                  matrix = new Matrix();
                  matrix.postScale((j - b) / getWidth(), (k - i) / getHeight());
                  matrix.postTranslate(b, i);
                } else {
                  matrix = paramMatrix;
                } 
                long l = matrix.native_instance;
                Object object = PdfRenderer.sPdfiumLock;
                /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
                try {
                  long l1 = PdfRenderer.access$200(PdfRenderer.this);
                  long l2 = this.mNativePage;
                  long l3 = bitmap.getNativeInstance();
                  try {
                    PdfRenderer.access$400(l1, l2, l3, b, i, j, k, l, paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/graphics/pdf/PdfRenderer$Page.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */