package android.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;

class FastBitmapDrawable extends Drawable {
  private final Bitmap mBitmap;
  
  private int mDrawLeft;
  
  private int mDrawTop;
  
  private final int mHeight;
  
  private final Paint mPaint;
  
  private final int mWidth;
  
  private FastBitmapDrawable(Bitmap paramBitmap) {
    this.mBitmap = paramBitmap;
    this.mWidth = paramBitmap.getWidth();
    int i = paramBitmap.getHeight();
    this.mHeight = i;
    setBounds(0, 0, this.mWidth, i);
    Paint paint = new Paint();
    this.mPaint = paint;
    paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC));
  }
  
  public void draw(Canvas paramCanvas) {
    paramCanvas.drawBitmap(this.mBitmap, this.mDrawLeft, this.mDrawTop, this.mPaint);
  }
  
  public int getIntrinsicHeight() {
    return this.mHeight;
  }
  
  public int getIntrinsicWidth() {
    return this.mWidth;
  }
  
  public int getMinimumHeight() {
    return this.mHeight;
  }
  
  public int getMinimumWidth() {
    return this.mWidth;
  }
  
  public int getOpacity() {
    return -1;
  }
  
  public void setAlpha(int paramInt) {
    throw new UnsupportedOperationException("Not supported with this drawable");
  }
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mDrawLeft = (paramInt3 - paramInt1 - this.mWidth) / 2 + paramInt1;
    this.mDrawTop = (paramInt4 - paramInt2 - this.mHeight) / 2 + paramInt2;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {
    throw new UnsupportedOperationException("Not supported with this drawable");
  }
  
  public void setDither(boolean paramBoolean) {
    throw new UnsupportedOperationException("Not supported with this drawable");
  }
  
  public void setFilterBitmap(boolean paramBoolean) {
    throw new UnsupportedOperationException("Not supported with this drawable");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperManager$FastBitmapDrawable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */