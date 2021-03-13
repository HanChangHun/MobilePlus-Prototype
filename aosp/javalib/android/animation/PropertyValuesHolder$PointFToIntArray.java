package android.animation;

import android.graphics.PointF;

class PointFToIntArray extends TypeConverter<PointF, int[]> {
  private int[] mCoordinates = new int[2];
  
  public PointFToIntArray() {
    super(PointF.class, (Class)int[].class);
  }
  
  public int[] convert(PointF paramPointF) {
    this.mCoordinates[0] = Math.round(paramPointF.x);
    this.mCoordinates[1] = Math.round(paramPointF.y);
    return this.mCoordinates;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PropertyValuesHolder$PointFToIntArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */