package android.animation;

abstract class FloatKeyframesBase extends PathKeyframes.SimpleKeyframes implements Keyframes.FloatKeyframes {
  public Class getType() {
    return Float.class;
  }
  
  public Object getValue(float paramFloat) {
    return Float.valueOf(getFloatValue(paramFloat));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PathKeyframes$FloatKeyframesBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */