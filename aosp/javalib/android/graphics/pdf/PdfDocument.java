package android.graphics.pdf;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import dalvik.system.CloseGuard;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PdfDocument {
  private final byte[] mChunk = new byte[4096];
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private Page mCurrentPage;
  
  private long mNativeDocument = nativeCreateDocument();
  
  private final List<PageInfo> mPages = new ArrayList<>();
  
  public PdfDocument() {
    this.mCloseGuard.open("close");
  }
  
  private void dispose() {
    long l = this.mNativeDocument;
    if (l != 0L) {
      nativeClose(l);
      this.mCloseGuard.close();
      this.mNativeDocument = 0L;
    } 
  }
  
  private native void nativeClose(long paramLong);
  
  private native long nativeCreateDocument();
  
  private native void nativeFinishPage(long paramLong);
  
  private static native long nativeStartPage(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  private native void nativeWriteTo(long paramLong, OutputStream paramOutputStream, byte[] paramArrayOfbyte);
  
  private void throwIfClosed() {
    if (this.mNativeDocument != 0L)
      return; 
    throw new IllegalStateException("document is closed!");
  }
  
  private void throwIfCurrentPageNotFinished() {
    if (this.mCurrentPage == null)
      return; 
    throw new IllegalStateException("Current page not finished!");
  }
  
  public void close() {
    throwIfCurrentPageNotFinished();
    dispose();
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      dispose();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public void finishPage(Page paramPage) {
    throwIfClosed();
    if (paramPage != null) {
      if (paramPage == this.mCurrentPage) {
        if (!paramPage.isFinished()) {
          this.mPages.add(paramPage.getInfo());
          this.mCurrentPage = null;
          nativeFinishPage(this.mNativeDocument);
          paramPage.finish();
          return;
        } 
        throw new IllegalStateException("page already finished");
      } 
      throw new IllegalStateException("invalid page");
    } 
    throw new IllegalArgumentException("page cannot be null");
  }
  
  public List<PageInfo> getPages() {
    return Collections.unmodifiableList(this.mPages);
  }
  
  public Page startPage(PageInfo paramPageInfo) {
    throwIfClosed();
    throwIfCurrentPageNotFinished();
    if (paramPageInfo != null) {
      Page page = new Page(new PdfCanvas(nativeStartPage(this.mNativeDocument, paramPageInfo.mPageWidth, paramPageInfo.mPageHeight, paramPageInfo.mContentRect.left, paramPageInfo.mContentRect.top, paramPageInfo.mContentRect.right, paramPageInfo.mContentRect.bottom)), paramPageInfo);
      this.mCurrentPage = page;
      return page;
    } 
    throw new IllegalArgumentException("page cannot be null");
  }
  
  public void writeTo(OutputStream paramOutputStream) throws IOException {
    throwIfClosed();
    throwIfCurrentPageNotFinished();
    if (paramOutputStream != null) {
      nativeWriteTo(this.mNativeDocument, paramOutputStream, this.mChunk);
      return;
    } 
    throw new IllegalArgumentException("out cannot be null!");
  }
  
  public static final class Page {
    private Canvas mCanvas;
    
    private final PdfDocument.PageInfo mPageInfo;
    
    private Page(Canvas param1Canvas, PdfDocument.PageInfo param1PageInfo) {
      this.mCanvas = param1Canvas;
      this.mPageInfo = param1PageInfo;
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
  
  public static final class PageInfo {
    private Rect mContentRect;
    
    private int mPageHeight;
    
    private int mPageNumber;
    
    private int mPageWidth;
    
    private PageInfo() {}
    
    public Rect getContentRect() {
      return this.mContentRect;
    }
    
    public int getPageHeight() {
      return this.mPageHeight;
    }
    
    public int getPageNumber() {
      return this.mPageNumber;
    }
    
    public int getPageWidth() {
      return this.mPageWidth;
    }
    
    public static final class Builder {
      private final PdfDocument.PageInfo mPageInfo;
      
      public Builder(int param2Int1, int param2Int2, int param2Int3) {
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo();
        this.mPageInfo = pageInfo;
        if (param2Int1 > 0) {
          if (param2Int2 > 0) {
            if (param2Int3 >= 0) {
              PdfDocument.PageInfo.access$002(pageInfo, param2Int1);
              PdfDocument.PageInfo.access$102(this.mPageInfo, param2Int2);
              PdfDocument.PageInfo.access$602(this.mPageInfo, param2Int3);
              return;
            } 
            throw new IllegalArgumentException("pageNumber must be non negative");
          } 
          throw new IllegalArgumentException("page width must be positive");
        } 
        throw new IllegalArgumentException("page width must be positive");
      }
      
      public PdfDocument.PageInfo create() {
        if (this.mPageInfo.mContentRect == null)
          PdfDocument.PageInfo.access$202(this.mPageInfo, new Rect(0, 0, this.mPageInfo.mPageWidth, this.mPageInfo.mPageHeight)); 
        return this.mPageInfo;
      }
      
      public Builder setContentRect(Rect param2Rect) {
        if (param2Rect == null || (param2Rect.left >= 0 && param2Rect.top >= 0 && param2Rect.right <= this.mPageInfo.mPageWidth && param2Rect.bottom <= this.mPageInfo.mPageHeight)) {
          PdfDocument.PageInfo.access$202(this.mPageInfo, param2Rect);
          return this;
        } 
        throw new IllegalArgumentException("contentRect does not fit the page");
      }
    }
  }
  
  public static final class Builder {
    private final PdfDocument.PageInfo mPageInfo;
    
    public Builder(int param1Int1, int param1Int2, int param1Int3) {
      PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo();
      this.mPageInfo = pageInfo;
      if (param1Int1 > 0) {
        if (param1Int2 > 0) {
          if (param1Int3 >= 0) {
            PdfDocument.PageInfo.access$002(pageInfo, param1Int1);
            PdfDocument.PageInfo.access$102(this.mPageInfo, param1Int2);
            PdfDocument.PageInfo.access$602(this.mPageInfo, param1Int3);
            return;
          } 
          throw new IllegalArgumentException("pageNumber must be non negative");
        } 
        throw new IllegalArgumentException("page width must be positive");
      } 
      throw new IllegalArgumentException("page width must be positive");
    }
    
    public PdfDocument.PageInfo create() {
      if (this.mPageInfo.mContentRect == null)
        PdfDocument.PageInfo.access$202(this.mPageInfo, new Rect(0, 0, this.mPageInfo.mPageWidth, this.mPageInfo.mPageHeight)); 
      return this.mPageInfo;
    }
    
    public Builder setContentRect(Rect param1Rect) {
      if (param1Rect == null || (param1Rect.left >= 0 && param1Rect.top >= 0 && param1Rect.right <= this.mPageInfo.mPageWidth && param1Rect.bottom <= this.mPageInfo.mPageHeight)) {
        PdfDocument.PageInfo.access$202(this.mPageInfo, param1Rect);
        return this;
      } 
      throw new IllegalArgumentException("contentRect does not fit the page");
    }
  }
  
  private final class PdfCanvas extends Canvas {
    public PdfCanvas(long param1Long) {
      super(param1Long);
    }
    
    public void setBitmap(Bitmap param1Bitmap) {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/pdf/PdfDocument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */