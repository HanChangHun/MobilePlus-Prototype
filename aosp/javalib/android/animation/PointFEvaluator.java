package android.animation;

import android.graphics.PointF;

public class PointFEvaluator implements TypeEvaluator<PointF> {
  private PointF mPoint;
  
  public PointFEvaluator() {}
  
  public PointFEvaluator(PointF paramPointF) {
    this.mPoint = paramPointF;
  }
  
  public PointF evaluate(float paramFloat, PointF paramPointF1, PointF paramPointF2) {
    float f = paramPointF1.x + (paramPointF2.x - paramPointF1.x) * paramFloat;
    paramFloat = paramPointF1.y + (paramPointF2.y - paramPointF1.y) * paramFloat;
    paramPointF1 = this.mPoint;
    if (paramPointF1 != null) {
      paramPointF1.set(f, paramFloat);
      return this.mPoint;
    } 
    return new PointF(f, paramFloat);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PointFEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */