package android.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;

public class IconResizer {
  private Canvas mCanvas;
  
  private int mIconHeight = -1;
  
  private int mIconWidth = -1;
  
  private final Rect mOldBounds = new Rect();
  
  public IconResizer() {
    Canvas canvas = new Canvas();
    this.mCanvas = canvas;
    canvas.setDrawFilter((DrawFilter)new PaintFlagsDrawFilter(4, 2));
    int i = (int)paramLauncherActivity.getResources().getDimension(17104896);
    this.mIconHeight = i;
    this.mIconWidth = i;
  }
  
  public Drawable createIconThumbnail(Drawable paramDrawable) {
    BitmapDrawable bitmapDrawable;
    int i = this.mIconWidth;
    int j = this.mIconHeight;
    int k = paramDrawable.getIntrinsicWidth();
    int m = paramDrawable.getIntrinsicHeight();
    if (paramDrawable instanceof PaintDrawable) {
      PaintDrawable paintDrawable = (PaintDrawable)paramDrawable;
      paintDrawable.setIntrinsicWidth(i);
      paintDrawable.setIntrinsicHeight(j);
    } 
    Drawable drawable = paramDrawable;
    if (i > 0) {
      drawable = paramDrawable;
      if (j > 0) {
        if (i < k || j < m) {
          Bitmap.Config config;
          int n;
          float f = k / m;
          if (k > m) {
            n = (int)(i / f);
          } else {
            n = j;
            if (m > k) {
              i = (int)(j * f);
              n = j;
            } 
          } 
          if (paramDrawable.getOpacity() != -1) {
            config = Bitmap.Config.ARGB_8888;
          } else {
            config = Bitmap.Config.RGB_565;
          } 
          Bitmap bitmap = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, config);
          Canvas canvas = this.mCanvas;
          canvas.setBitmap(bitmap);
          this.mOldBounds.set(paramDrawable.getBounds());
          k = (this.mIconWidth - i) / 2;
          j = (this.mIconHeight - n) / 2;
          paramDrawable.setBounds(k, j, k + i, j + n);
          paramDrawable.draw(canvas);
          paramDrawable.setBounds(this.mOldBounds);
          bitmapDrawable = new BitmapDrawable(LauncherActivity.this.getResources(), bitmap);
          canvas.setBitmap(null);
          return (Drawable)bitmapDrawable;
        } 
        drawable = paramDrawable;
        if (k < i) {
          drawable = paramDrawable;
          if (m < j) {
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, config);
            Canvas canvas = this.mCanvas;
            canvas.setBitmap(bitmap);
            this.mOldBounds.set(paramDrawable.getBounds());
            int n = (i - k) / 2;
            i = (j - m) / 2;
            paramDrawable.setBounds(n, i, n + k, i + m);
            paramDrawable.draw(canvas);
            paramDrawable.setBounds(this.mOldBounds);
            bitmapDrawable = new BitmapDrawable(LauncherActivity.this.getResources(), bitmap);
            canvas.setBitmap(null);
          } 
        } 
      } 
    } 
    return (Drawable)bitmapDrawable;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LauncherActivity$IconResizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */