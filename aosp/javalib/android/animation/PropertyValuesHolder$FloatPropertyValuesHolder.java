package android.animation;

import android.util.FloatProperty;
import android.util.Log;
import android.util.Property;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

class FloatPropertyValuesHolder extends PropertyValuesHolder {
  private static final HashMap<Class, HashMap<String, Long>> sJNISetterPropertyMap = new HashMap<>();
  
  float mFloatAnimatedValue;
  
  Keyframes.FloatKeyframes mFloatKeyframes;
  
  private FloatProperty mFloatProperty;
  
  long mJniSetter;
  
  public FloatPropertyValuesHolder(Property paramProperty, Keyframes.FloatKeyframes paramFloatKeyframes) {
    super(paramProperty);
    this.mValueType = float.class;
    this.mKeyframes = paramFloatKeyframes;
    this.mFloatKeyframes = paramFloatKeyframes;
    if (paramProperty instanceof FloatProperty)
      this.mFloatProperty = (FloatProperty)this.mProperty; 
  }
  
  public FloatPropertyValuesHolder(Property paramProperty, float... paramVarArgs) {
    super(paramProperty);
    setFloatValues(paramVarArgs);
    if (paramProperty instanceof FloatProperty)
      this.mFloatProperty = (FloatProperty)this.mProperty; 
  }
  
  public FloatPropertyValuesHolder(String paramString, Keyframes.FloatKeyframes paramFloatKeyframes) {
    super(paramString);
    this.mValueType = float.class;
    this.mKeyframes = paramFloatKeyframes;
    this.mFloatKeyframes = paramFloatKeyframes;
  }
  
  public FloatPropertyValuesHolder(String paramString, float... paramVarArgs) {
    super(paramString);
    setFloatValues(paramVarArgs);
  }
  
  void calculateValue(float paramFloat) {
    this.mFloatAnimatedValue = this.mFloatKeyframes.getFloatValue(paramFloat);
  }
  
  public FloatPropertyValuesHolder clone() {
    FloatPropertyValuesHolder floatPropertyValuesHolder = (FloatPropertyValuesHolder)super.clone();
    floatPropertyValuesHolder.mFloatKeyframes = (Keyframes.FloatKeyframes)floatPropertyValuesHolder.mKeyframes;
    return floatPropertyValuesHolder;
  }
  
  Object getAnimatedValue() {
    return Float.valueOf(this.mFloatAnimatedValue);
  }
  
  void setAnimatedValue(Object paramObject) {
    FloatProperty floatProperty = this.mFloatProperty;
    if (floatProperty != null) {
      floatProperty.setValue(paramObject, this.mFloatAnimatedValue);
      return;
    } 
    if (this.mProperty != null) {
      this.mProperty.set(paramObject, Float.valueOf(this.mFloatAnimatedValue));
      return;
    } 
    long l = this.mJniSetter;
    if (l != 0L) {
      PropertyValuesHolder.access$400(paramObject, l, this.mFloatAnimatedValue);
      return;
    } 
    if (this.mSetter != null)
      try {
        this.mTmpValueArray[0] = Float.valueOf(this.mFloatAnimatedValue);
        this.mSetter.invoke(paramObject, this.mTmpValueArray);
      } catch (InvocationTargetException invocationTargetException) {
        Log.e("PropertyValuesHolder", invocationTargetException.toString());
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("PropertyValuesHolder", illegalAccessException.toString());
      }  
  }
  
  public void setFloatValues(float... paramVarArgs) {
    super.setFloatValues(paramVarArgs);
    this.mFloatKeyframes = (Keyframes.FloatKeyframes)this.mKeyframes;
  }
  
  public void setProperty(Property paramProperty) {
    if (paramProperty instanceof FloatProperty) {
      this.mFloatProperty = (FloatProperty)paramProperty;
    } else {
      super.setProperty(paramProperty);
    } 
  }
  
  void setupSetter(Class paramClass) {
    if (this.mProperty != null)
      return; 
    synchronized (sJNISetterPropertyMap) {
      HashMap<Object, Object> hashMap = (HashMap)sJNISetterPropertyMap.get(paramClass);
      boolean bool = false;
      if (hashMap != null) {
        boolean bool1 = hashMap.containsKey(this.mPropertyName);
        bool = bool1;
        if (bool1) {
          Long long_ = (Long)hashMap.get(this.mPropertyName);
          bool = bool1;
          if (long_ != null) {
            this.mJniSetter = long_.longValue();
            bool = bool1;
          } 
        } 
      } 
      if (!bool) {
        String str = getMethodName("set", this.mPropertyName);
        try {
          this.mJniSetter = PropertyValuesHolder.access$500(paramClass, str);
        } catch (NoSuchMethodError noSuchMethodError) {}
        HashMap<Object, Object> hashMap1 = hashMap;
        if (hashMap == null) {
          hashMap1 = new HashMap<>();
          this();
          sJNISetterPropertyMap.put(paramClass, hashMap1);
        } 
        hashMap1.put(this.mPropertyName, Long.valueOf(this.mJniSetter));
      } 
      if (this.mJniSetter == 0L)
        super.setupSetter(paramClass); 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PropertyValuesHolder$FloatPropertyValuesHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */