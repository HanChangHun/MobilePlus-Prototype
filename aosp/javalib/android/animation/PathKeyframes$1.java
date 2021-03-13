package android.animation;

import android.graphics.PointF;

class null extends PathKeyframes.FloatKeyframesBase {
  public float getFloatValue(float paramFloat) {
    return ((PointF)PathKeyframes.this.getValue(paramFloat)).x;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PathKeyframes$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */