package android.graphics.drawable.shapes;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.Arrays;
import java.util.Objects;

public class RoundRectShape extends RectShape {
  private float[] mInnerRadii;
  
  private RectF mInnerRect;
  
  private RectF mInset;
  
  private float[] mOuterRadii;
  
  private Path mPath;
  
  public RoundRectShape(float[] paramArrayOffloat1, RectF paramRectF, float[] paramArrayOffloat2) {
    if (paramArrayOffloat1 == null || paramArrayOffloat1.length >= 8) {
      if (paramArrayOffloat2 == null || paramArrayOffloat2.length >= 8) {
        this.mOuterRadii = paramArrayOffloat1;
        this.mInset = paramRectF;
        this.mInnerRadii = paramArrayOffloat2;
        if (paramRectF != null)
          this.mInnerRect = new RectF(); 
        this.mPath = new Path();
        return;
      } 
      throw new ArrayIndexOutOfBoundsException("inner radii must have >= 8 values");
    } 
    throw new ArrayIndexOutOfBoundsException("outer radii must have >= 8 values");
  }
  
  public RoundRectShape clone() throws CloneNotSupportedException {
    RoundRectShape roundRectShape = (RoundRectShape)super.clone();
    float[] arrayOfFloat1 = this.mOuterRadii;
    float[] arrayOfFloat2 = null;
    if (arrayOfFloat1 != null) {
      arrayOfFloat1 = (float[])arrayOfFloat1.clone();
    } else {
      arrayOfFloat1 = null;
    } 
    roundRectShape.mOuterRadii = arrayOfFloat1;
    float[] arrayOfFloat3 = this.mInnerRadii;
    arrayOfFloat1 = arrayOfFloat2;
    if (arrayOfFloat3 != null)
      arrayOfFloat1 = (float[])arrayOfFloat3.clone(); 
    roundRectShape.mInnerRadii = arrayOfFloat1;
    roundRectShape.mInset = new RectF(this.mInset);
    roundRectShape.mInnerRect = new RectF(this.mInnerRect);
    roundRectShape.mPath = new Path(this.mPath);
    return roundRectShape;
  }
  
  public void draw(Canvas paramCanvas, Paint paramPaint) {
    paramCanvas.drawPath(this.mPath, paramPaint);
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
    if (!Arrays.equals(this.mOuterRadii, ((RoundRectShape)paramObject).mOuterRadii) || !Objects.equals(this.mInset, ((RoundRectShape)paramObject).mInset) || !Arrays.equals(this.mInnerRadii, ((RoundRectShape)paramObject).mInnerRadii) || !Objects.equals(this.mInnerRect, ((RoundRectShape)paramObject).mInnerRect) || !Objects.equals(this.mPath, ((RoundRectShape)paramObject).mPath))
      bool = false; 
    return bool;
  }
  
  public void getOutline(Outline paramOutline) {
    if (this.mInnerRect != null)
      return; 
    float f = 0.0F;
    float[] arrayOfFloat = this.mOuterRadii;
    if (arrayOfFloat != null) {
      float f1 = arrayOfFloat[0];
      byte b = 1;
      while (true) {
        f = f1;
        if (b < 8) {
          if (this.mOuterRadii[b] != f1) {
            paramOutline.setPath(this.mPath);
            return;
          } 
          b++;
          continue;
        } 
        break;
      } 
    } 
    RectF rectF = rect();
    paramOutline.setRoundRect((int)Math.ceil(rectF.left), (int)Math.ceil(rectF.top), (int)Math.floor(rectF.right), (int)Math.floor(rectF.bottom), f);
  }
  
  public int hashCode() {
    return (Objects.hash(new Object[] { Integer.valueOf(super.hashCode()), this.mInset, this.mInnerRect, this.mPath }) * 31 + Arrays.hashCode(this.mOuterRadii)) * 31 + Arrays.hashCode(this.mInnerRadii);
  }
  
  protected void onResize(float paramFloat1, float paramFloat2) {
    super.onResize(paramFloat1, paramFloat2);
    RectF rectF1 = rect();
    this.mPath.reset();
    float[] arrayOfFloat = this.mOuterRadii;
    if (arrayOfFloat != null) {
      this.mPath.addRoundRect(rectF1, arrayOfFloat, Path.Direction.CW);
    } else {
      this.mPath.addRect(rectF1, Path.Direction.CW);
    } 
    RectF rectF2 = this.mInnerRect;
    if (rectF2 != null) {
      rectF2.set(rectF1.left + this.mInset.left, rectF1.top + this.mInset.top, rectF1.right - this.mInset.right, rectF1.bottom - this.mInset.bottom);
      if (this.mInnerRect.width() < paramFloat1 && this.mInnerRect.height() < paramFloat2) {
        float[] arrayOfFloat1 = this.mInnerRadii;
        if (arrayOfFloat1 != null) {
          this.mPath.addRoundRect(this.mInnerRect, arrayOfFloat1, Path.Direction.CCW);
        } else {
          this.mPath.addRect(this.mInnerRect, Path.Direction.CCW);
        } 
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/shapes/RoundRectShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */