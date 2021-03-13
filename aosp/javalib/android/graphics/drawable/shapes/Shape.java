package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import java.util.Objects;

public abstract class Shape implements Cloneable {
  private float mHeight;
  
  private float mWidth;
  
  public Shape clone() throws CloneNotSupportedException {
    return (Shape)super.clone();
  }
  
  public abstract void draw(Canvas paramCanvas, Paint paramPaint);
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (Float.compare(((Shape)paramObject).mWidth, this.mWidth) != 0 || Float.compare(((Shape)paramObject).mHeight, this.mHeight) != 0)
      bool = false; 
    return bool;
  }
  
  public final float getHeight() {
    return this.mHeight;
  }
  
  public void getOutline(Outline paramOutline) {}
  
  public final float getWidth() {
    return this.mWidth;
  }
  
  public boolean hasAlpha() {
    return true;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Float.valueOf(this.mWidth), Float.valueOf(this.mHeight) });
  }
  
  protected void onResize(float paramFloat1, float paramFloat2) {}
  
  public final void resize(float paramFloat1, float paramFloat2) {
    float f = paramFloat1;
    if (paramFloat1 < 0.0F)
      f = 0.0F; 
    paramFloat1 = paramFloat2;
    if (paramFloat2 < 0.0F)
      paramFloat1 = 0.0F; 
    if (this.mWidth != f || this.mHeight != paramFloat1) {
      this.mWidth = f;
      this.mHeight = paramFloat1;
      onResize(f, paramFloat1);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/shapes/Shape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */