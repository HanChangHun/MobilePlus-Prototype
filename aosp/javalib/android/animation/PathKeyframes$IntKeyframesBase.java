package android.animation;

abstract class IntKeyframesBase extends PathKeyframes.SimpleKeyframes implements Keyframes.IntKeyframes {
  public Class getType() {
    return Integer.class;
  }
  
  public Object getValue(float paramFloat) {
    return Integer.valueOf(getIntValue(paramFloat));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PathKeyframes$IntKeyframesBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */