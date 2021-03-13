package android.animation;

import android.graphics.PointF;

class null extends PathKeyframes.IntKeyframesBase {
  public int getIntValue(float paramFloat) {
    return Math.round(((PointF)PathKeyframes.this.getValue(paramFloat)).x);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PathKeyframes$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */