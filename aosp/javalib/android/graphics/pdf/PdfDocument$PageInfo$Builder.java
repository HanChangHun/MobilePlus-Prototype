package android.graphics.pdf;

import android.graphics.Rect;

public final class Builder {
  private final PdfDocument.PageInfo mPageInfo;
  
  public Builder(int paramInt1, int paramInt2, int paramInt3) {
    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo(null);
    this.mPageInfo = pageInfo;
    if (paramInt1 > 0) {
      if (paramInt2 > 0) {
        if (paramInt3 >= 0) {
          PdfDocument.PageInfo.access$002(pageInfo, paramInt1);
          PdfDocument.PageInfo.access$102(this.mPageInfo, paramInt2);
          PdfDocument.PageInfo.access$602(this.mPageInfo, paramInt3);
          return;
        } 
        throw new IllegalArgumentException("pageNumber must be non negative");
      } 
      throw new IllegalArgumentException("page width must be positive");
    } 
    throw new IllegalArgumentException("page width must be positive");
  }
  
  public PdfDocument.PageInfo create() {
    if (PdfDocument.PageInfo.access$200(this.mPageInfo) == null)
      PdfDocument.PageInfo.access$202(this.mPageInfo, new Rect(0, 0, PdfDocument.PageInfo.access$000(this.mPageInfo), PdfDocument.PageInfo.access$100(this.mPageInfo))); 
    return this.mPageInfo;
  }
  
  public Builder setContentRect(Rect paramRect) {
    if (paramRect == null || (paramRect.left >= 0 && paramRect.top >= 0 && paramRect.right <= PdfDocument.PageInfo.access$000(this.mPageInfo) && paramRect.bottom <= PdfDocument.PageInfo.access$100(this.mPageInfo))) {
      PdfDocument.PageInfo.access$202(this.mPageInfo, paramRect);
      return this;
    } 
    throw new IllegalArgumentException("contentRect does not fit the page");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/pdf/PdfDocument$PageInfo$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */