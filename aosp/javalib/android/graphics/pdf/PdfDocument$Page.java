package android.graphics.pdf;

import android.graphics.Canvas;

public final class Page {
  private Canvas mCanvas;
  
  private final PdfDocument.PageInfo mPageInfo;
  
  private Page(Canvas paramCanvas, PdfDocument.PageInfo paramPageInfo) {
    this.mCanvas = paramCanvas;
    this.mPageInfo = paramPageInfo;
  }
  
  private void finish() {
    Canvas canvas = this.mCanvas;
    if (canvas != null) {
      canvas.release();
      this.mCanvas = null;
    } 
  }
  
  public Canvas getCanvas() {
    return this.mCanvas;
  }
  
  public PdfDocument.PageInfo getInfo() {
    return this.mPageInfo;
  }
  
  boolean isFinished() {
    boolean bool;
    if (this.mCanvas == null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/pdf/PdfDocument$Page.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */