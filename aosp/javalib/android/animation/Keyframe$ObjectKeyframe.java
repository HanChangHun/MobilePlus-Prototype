package android.animation;

class ObjectKeyframe extends Keyframe {
  Object mValue;
  
  ObjectKeyframe(float paramFloat, Object<?> paramObject) {
    Class<Object> clazz;
    boolean bool;
    this.mFraction = paramFloat;
    this.mValue = paramObject;
    if (paramObject != null) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mHasValue = bool;
    if (this.mHasValue) {
      paramObject = (Object<?>)paramObject.getClass();
    } else {
      clazz = Object.class;
    } 
    this.mValueType = clazz;
  }
  
  public ObjectKeyframe clone() {
    float f = getFraction();
    if (hasValue()) {
      objectKeyframe = (ObjectKeyframe)this.mValue;
    } else {
      objectKeyframe = null;
    } 
    ObjectKeyframe objectKeyframe = new ObjectKeyframe(f, objectKeyframe);
    objectKeyframe.mValueWasSetOnStart = this.mValueWasSetOnStart;
    objectKeyframe.setInterpolator(getInterpolator());
    return objectKeyframe;
  }
  
  public Object getValue() {
    return this.mValue;
  }
  
  public void setValue(Object paramObject) {
    boolean bool;
    this.mValue = paramObject;
    if (paramObject != null) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mHasValue = bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/Keyframe$ObjectKeyframe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */