package android.graphics.pdf;

import android.graphics.Rect;

public final class PageInfo {
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


/* Location:              /home/chun/Desktop/temp/!/android/graphics/pdf/PdfDocument$PageInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */