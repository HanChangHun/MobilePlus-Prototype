package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.Objects;

public class PathShape extends Shape {
  private Path mPath;
  
  private float mScaleX;
  
  private float mScaleY;
  
  private final float mStdHeight;
  
  private final float mStdWidth;
  
  public PathShape(Path paramPath, float paramFloat1, float paramFloat2) {
    this.mPath = paramPath;
    this.mStdWidth = paramFloat1;
    this.mStdHeight = paramFloat2;
  }
  
  public PathShape clone() throws CloneNotSupportedException {
    PathShape pathShape = (PathShape)super.clone();
    pathShape.mPath = new Path(this.mPath);
    return pathShape;
  }
  
  public void draw(Canvas paramCanvas, Paint paramPaint) {
    paramCanvas.save();
    paramCanvas.scale(this.mScaleX, this.mScaleY);
    paramCanvas.drawPath(this.mPath, paramPaint);
    paramCanvas.restore();
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
    if (Float.compare(((PathShape)paramObject).mStdWidth, this.mStdWidth) != 0 || Float.compare(((PathShape)paramObject).mStdHeight, this.mStdHeight) != 0 || Float.compare(((PathShape)paramObject).mScaleX, this.mScaleX) != 0 || Float.compare(((PathShape)paramObject).mScaleY, this.mScaleY) != 0 || !Objects.equals(this.mPath, ((PathShape)paramObject).mPath))
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(super.hashCode()), Float.valueOf(this.mStdWidth), Float.valueOf(this.mStdHeight), this.mPath, Float.valueOf(this.mScaleX), Float.valueOf(this.mScaleY) });
  }
  
  protected void onResize(float paramFloat1, float paramFloat2) {
    this.mScaleX = paramFloat1 / this.mStdWidth;
    this.mScaleY = paramFloat2 / this.mStdHeight;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/shapes/PathShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */