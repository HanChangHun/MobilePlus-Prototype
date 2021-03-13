package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.Objects;

public class RectShape extends Shape {
  private RectF mRect = new RectF();
  
  public RectShape clone() throws CloneNotSupportedException {
    RectShape rectShape = (RectShape)super.clone();
    rectShape.mRect = new RectF(this.mRect);
    return rectShape;
  }
  
  public void draw(Canvas paramCanvas, Paint paramPaint) {
    paramCanvas.drawRect(this.mRect, paramPaint);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    paramObject = paramObject;
    return Objects.equals(this.mRect, ((RectShape)paramObject).mRect);
  }
  
  public void getOutline(Outline paramOutline) {
    RectF rectF = rect();
    paramOutline.setRect((int)Math.ceil(rectF.left), (int)Math.ceil(rectF.top), (int)Math.floor(rectF.right), (int)Math.floor(rectF.bottom));
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(super.hashCode()), this.mRect });
  }
  
  protected void onResize(float paramFloat1, float paramFloat2) {
    this.mRect.set(0.0F, 0.0F, paramFloat1, paramFloat2);
  }
  
  protected final RectF rect() {
    return this.mRect;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/shapes/RectShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */