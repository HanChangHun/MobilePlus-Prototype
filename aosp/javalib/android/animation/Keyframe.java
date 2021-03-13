package android.animation;

public abstract class Keyframe implements Cloneable {
  float mFraction;
  
  boolean mHasValue;
  
  private TimeInterpolator mInterpolator = null;
  
  Class mValueType;
  
  boolean mValueWasSetOnStart;
  
  public static Keyframe ofFloat(float paramFloat) {
    return new FloatKeyframe(paramFloat);
  }
  
  public static Keyframe ofFloat(float paramFloat1, float paramFloat2) {
    return new FloatKeyframe(paramFloat1, paramFloat2);
  }
  
  public static Keyframe ofInt(float paramFloat) {
    return new IntKeyframe(paramFloat);
  }
  
  public static Keyframe ofInt(float paramFloat, int paramInt) {
    return new IntKeyframe(paramFloat, paramInt);
  }
  
  public static Keyframe ofObject(float paramFloat) {
    return new ObjectKeyframe(paramFloat, null);
  }
  
  public static Keyframe ofObject(float paramFloat, Object paramObject) {
    return new ObjectKeyframe(paramFloat, paramObject);
  }
  
  public abstract Keyframe clone();
  
  public float getFraction() {
    return this.mFraction;
  }
  
  public TimeInterpolator getInterpolator() {
    return this.mInterpolator;
  }
  
  public Class getType() {
    return this.mValueType;
  }
  
  public abstract Object getValue();
  
  public boolean hasValue() {
    return this.mHasValue;
  }
  
  public void setFraction(float paramFloat) {
    this.mFraction = paramFloat;
  }
  
  public void setInterpolator(TimeInterpolator paramTimeInterpolator) {
    this.mInterpolator = paramTimeInterpolator;
  }
  
  public abstract void setValue(Object paramObject);
  
  void setValueWasSetOnStart(boolean paramBoolean) {
    this.mValueWasSetOnStart = paramBoolean;
  }
  
  boolean valueWasSetOnStart() {
    return this.mValueWasSetOnStart;
  }
  
  static class FloatKeyframe extends Keyframe {
    float mValue;
    
    FloatKeyframe(float param1Float) {
      this.mFraction = param1Float;
      this.mValueType = float.class;
    }
    
    FloatKeyframe(float param1Float1, float param1Float2) {
      this.mFraction = param1Float1;
      this.mValue = param1Float2;
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
    
    public void setValue(Object param1Object) {
      if (param1Object != null && param1Object.getClass() == Float.class) {
        this.mValue = ((Float)param1Object).floatValue();
        this.mHasValue = true;
      } 
    }
  }
  
  static class IntKeyframe extends Keyframe {
    int mValue;
    
    IntKeyframe(float param1Float) {
      this.mFraction = param1Float;
      this.mValueType = int.class;
    }
    
    IntKeyframe(float param1Float, int param1Int) {
      this.mFraction = param1Float;
      this.mValue = param1Int;
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
    
    public void setValue(Object param1Object) {
      if (param1Object != null && param1Object.getClass() == Integer.class) {
        this.mValue = ((Integer)param1Object).intValue();
        this.mHasValue = true;
      } 
    }
  }
  
  static class ObjectKeyframe extends Keyframe {
    Object mValue;
    
    ObjectKeyframe(float param1Float, Object<?> param1Object) {
      Class<Object> clazz;
      boolean bool;
      this.mFraction = param1Float;
      this.mValue = param1Object;
      if (param1Object != null) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mHasValue = bool;
      if (this.mHasValue) {
        param1Object = (Object<?>)param1Object.getClass();
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
    
    public void setValue(Object param1Object) {
      boolean bool;
      this.mValue = param1Object;
      if (param1Object != null) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mHasValue = bool;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/Keyframe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */