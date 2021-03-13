package android.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Picture;
import android.graphics.Rect;

public class PictureDrawable extends Drawable {
  private Picture mPicture;
  
  public PictureDrawable(Picture paramPicture) {
    this.mPicture = paramPicture;
  }
  
  public void draw(Canvas paramCanvas) {
    if (this.mPicture != null) {
      Rect rect = getBounds();
      paramCanvas.save();
      paramCanvas.clipRect(rect);
      paramCanvas.translate(rect.left, rect.top);
      paramCanvas.drawPicture(this.mPicture);
      paramCanvas.restore();
    } 
  }
  
  public int getIntrinsicHeight() {
    byte b;
    Picture picture = this.mPicture;
    if (picture != null) {
      b = picture.getHeight();
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int getIntrinsicWidth() {
    byte b;
    Picture picture = this.mPicture;
    if (picture != null) {
      b = picture.getWidth();
    } else {
      b = -1;
    } 
    return b;
  }
  
  public int getOpacity() {
    return -3;
  }
  
  public Picture getPicture() {
    return this.mPicture;
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setColorFilter(ColorFilter paramColorFilter) {}
  
  public void setPicture(Picture paramPicture) {
    this.mPicture = paramPicture;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/PictureDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */