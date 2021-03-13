package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;

public class OvalShape extends RectShape {
  public OvalShape clone() throws CloneNotSupportedException {
    return (OvalShape)super.clone();
  }
  
  public void draw(Canvas paramCanvas, Paint paramPaint) {
    paramCanvas.drawOval(rect(), paramPaint);
  }
  
  public void getOutline(Outline paramOutline) {
    RectF rectF = rect();
    paramOutline.setOval((int)Math.ceil(rectF.left), (int)Math.ceil(rectF.top), (int)Math.floor(rectF.right), (int)Math.floor(rectF.bottom));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/shapes/OvalShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */