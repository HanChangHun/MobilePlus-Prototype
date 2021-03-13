package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import java.util.Objects;

public class ArcShape extends RectShape {
  private final float mStartAngle;
  
  private final float mSweepAngle;
  
  public ArcShape(float paramFloat1, float paramFloat2) {
    this.mStartAngle = paramFloat1;
    this.mSweepAngle = paramFloat2;
  }
  
  public ArcShape clone() throws CloneNotSupportedException {
    return (ArcShape)super.clone();
  }
  
  public void draw(Canvas paramCanvas, Paint paramPaint) {
    paramCanvas.drawArc(rect(), this.mStartAngle, this.mSweepAngle, true, paramPaint);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    if (!super.equals(paramObject))
      return false; 
    paramObject = paramObject;
    if (Float.compare(((ArcShape)paramObject).mStartAngle, this.mStartAngle) != 0 || Float.compare(((ArcShape)paramObject).mSweepAngle, this.mSweepAngle) != 0)
      bool = false; 
    return bool;
  }
  
  public void getOutline(Outline paramOutline) {}
  
  public final float getStartAngle() {
    return this.mStartAngle;
  }
  
  public final float getSweepAngle() {
    return this.mSweepAngle;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(super.hashCode()), Float.valueOf(this.mStartAngle), Float.valueOf(this.mSweepAngle) });
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/shapes/ArcShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */