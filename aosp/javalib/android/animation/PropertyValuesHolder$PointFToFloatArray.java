package android.animation;

import android.graphics.PointF;

class PointFToFloatArray extends TypeConverter<PointF, float[]> {
  private float[] mCoordinates = new float[2];
  
  public PointFToFloatArray() {
    super(PointF.class, (Class)float[].class);
  }
  
  public float[] convert(PointF paramPointF) {
    this.mCoordinates[0] = paramPointF.x;
    this.mCoordinates[1] = paramPointF.y;
    return this.mCoordinates;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PropertyValuesHolder$PointFToFloatArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */