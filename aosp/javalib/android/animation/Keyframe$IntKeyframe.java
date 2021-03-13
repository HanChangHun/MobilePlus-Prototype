package android.animation;

class IntKeyframe extends Keyframe {
  int mValue;
  
  IntKeyframe(float paramFloat) {
    this.mFraction = paramFloat;
    this.mValueType = int.class;
  }
  
  IntKeyframe(float paramFloat, int paramInt) {
    this.mFraction = paramFloat;
    this.mValue = paramInt;
    this.mValueType = int.class;
    this.mHasValue = true;
  }
  
  public IntKeyframe clone() {
    IntKeyframe intKeyframe;
    if (this.mHasValue) {
      intKeyframe = new IntKeyframe(getFraction(), this.mValue);
    } else {
      intKeyframe = new IntKeyframe(getFraction());
    } 
    intKeyframe.setInterpolator(getInterpolator());
    intKeyframe.mValueWasSetOnStart = this.mValueWasSetOnStart;
    return intKeyframe;
  }
  
  public int getIntValue() {
    return this.mValue;
  }
  
  public Object getValue() {
    return Integer.valueOf(this.mValue);
  }
  
  public void setValue(Object paramObject) {
    if (paramObject != null && paramObject.getClass() == Integer.class) {
      this.mValue = ((Integer)paramObject).intValue();
      this.mHasValue = true;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/Keyframe$IntKeyframe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */