package android.animation;

import android.util.IntProperty;
import android.util.Log;
import android.util.Property;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

class IntPropertyValuesHolder extends PropertyValuesHolder {
  private static final HashMap<Class, HashMap<String, Long>> sJNISetterPropertyMap = new HashMap<>();
  
  int mIntAnimatedValue;
  
  Keyframes.IntKeyframes mIntKeyframes;
  
  private IntProperty mIntProperty;
  
  long mJniSetter;
  
  public IntPropertyValuesHolder(Property paramProperty, Keyframes.IntKeyframes paramIntKeyframes) {
    super(paramProperty);
    this.mValueType = int.class;
    this.mKeyframes = paramIntKeyframes;
    this.mIntKeyframes = paramIntKeyframes;
    if (paramProperty instanceof IntProperty)
      this.mIntProperty = (IntProperty)this.mProperty; 
  }
  
  public IntPropertyValuesHolder(Property paramProperty, int... paramVarArgs) {
    super(paramProperty);
    setIntValues(paramVarArgs);
    if (paramProperty instanceof IntProperty)
      this.mIntProperty = (IntProperty)this.mProperty; 
  }
  
  public IntPropertyValuesHolder(String paramString, Keyframes.IntKeyframes paramIntKeyframes) {
    super(paramString);
    this.mValueType = int.class;
    this.mKeyframes = paramIntKeyframes;
    this.mIntKeyframes = paramIntKeyframes;
  }
  
  public IntPropertyValuesHolder(String paramString, int... paramVarArgs) {
    super(paramString);
    setIntValues(paramVarArgs);
  }
  
  void calculateValue(float paramFloat) {
    this.mIntAnimatedValue = this.mIntKeyframes.getIntValue(paramFloat);
  }
  
  public IntPropertyValuesHolder clone() {
    IntPropertyValuesHolder intPropertyValuesHolder = (IntPropertyValuesHolder)super.clone();
    intPropertyValuesHolder.mIntKeyframes = (Keyframes.IntKeyframes)intPropertyValuesHolder.mKeyframes;
    return intPropertyValuesHolder;
  }
  
  Object getAnimatedValue() {
    return Integer.valueOf(this.mIntAnimatedValue);
  }
  
  void setAnimatedValue(Object paramObject) {
    IntProperty intProperty = this.mIntProperty;
    if (intProperty != null) {
      intProperty.setValue(paramObject, this.mIntAnimatedValue);
      return;
    } 
    if (this.mProperty != null) {
      this.mProperty.set(paramObject, Integer.valueOf(this.mIntAnimatedValue));
      return;
    } 
    long l = this.mJniSetter;
    if (l != 0L) {
      PropertyValuesHolder.access$200(paramObject, l, this.mIntAnimatedValue);
      return;
    } 
    if (this.mSetter != null)
      try {
        this.mTmpValueArray[0] = Integer.valueOf(this.mIntAnimatedValue);
        this.mSetter.invoke(paramObject, this.mTmpValueArray);
      } catch (InvocationTargetException invocationTargetException) {
        Log.e("PropertyValuesHolder", invocationTargetException.toString());
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("PropertyValuesHolder", illegalAccessException.toString());
      }  
  }
  
  public void setIntValues(int... paramVarArgs) {
    super.setIntValues(paramVarArgs);
    this.mIntKeyframes = (Keyframes.IntKeyframes)this.mKeyframes;
  }
  
  public void setProperty(Property paramProperty) {
    if (paramProperty instanceof IntProperty) {
      this.mIntProperty = (IntProperty)paramProperty;
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
          this.mJniSetter = PropertyValuesHolder.access$300(paramClass, str);
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


/* Location:              /home/chun/Desktop/temp/!/android/animation/PropertyValuesHolder$IntPropertyValuesHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */