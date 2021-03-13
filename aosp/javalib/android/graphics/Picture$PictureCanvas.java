package android.graphics;

class PictureCanvas extends Canvas {
  boolean mHoldsHwBitmap;
  
  private final Picture mPicture;
  
  public PictureCanvas(Picture paramPicture, long paramLong) {
    super(paramLong);
    this.mPicture = paramPicture;
    this.mDensity = 0;
  }
  
  public void drawPicture(Picture paramPicture) {
    if (this.mPicture != paramPicture) {
      super.drawPicture(paramPicture);
      return;
    } 
    throw new RuntimeException("Cannot draw a picture into its recording canvas");
  }
  
  protected void onHwBitmapInSwMode() {
    this.mHoldsHwBitmap = true;
  }
  
  public void setBitmap(Bitmap paramBitmap) {
    throw new RuntimeException("Cannot call setBitmap on a picture canvas");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Picture$PictureCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */