package android.animation;

class FloatKeyframe extends Keyframe {
  float mValue;
  
  FloatKeyframe(float paramFloat) {
    this.mFraction = paramFloat;
    this.mValueType = float.class;
  }
  
  FloatKeyframe(float paramFloat1, float paramFloat2) {
    this.mFraction = paramFloat1;
    this.mValue = paramFloat2;
    this.mValueType = float.class;
    this.mHasValue = true;
  }
  
  public FloatKeyframe clone() {
    FloatKeyframe floatKeyframe;
    if (this.mHasValue) {
      floatKeyframe = new FloatKeyframe(getFraction(), this.mValue);
    } else {
      floatKeyframe = new FloatKeyframe(getFraction());
    } 
    floatKeyframe.setInterpolator(getInterpolator());
    floatKeyframe.mValueWasSetOnStart = this.mValueWasSetOnStart;
    return floatKeyframe;
  }
  
  public float getFloatValue() {
    return this.mValue;
  }
  
  public Object getValue() {
    return Float.valueOf(this.mValue);
  }
  
  public void setValue(Object paramObject) {
    if (paramObject != null && paramObject.getClass() == Float.class) {
      this.mValue = ((Float)paramObject).floatValue();
      this.mHasValue = true;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/Keyframe$FloatKeyframe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */