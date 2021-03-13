package android.animation;

import java.util.HashMap;

class MultiIntValuesHolder extends PropertyValuesHolder {
  private static final HashMap<Class, HashMap<String, Long>> sJNISetterPropertyMap = new HashMap<>();
  
  private long mJniSetter;
  
  public MultiIntValuesHolder(String paramString, TypeConverter paramTypeConverter, TypeEvaluator paramTypeEvaluator, Keyframes paramKeyframes) {
    super(paramString);
    setConverter(paramTypeConverter);
    this.mKeyframes = paramKeyframes;
    setEvaluator(paramTypeEvaluator);
  }
  
  public MultiIntValuesHolder(String paramString, TypeConverter paramTypeConverter, TypeEvaluator paramTypeEvaluator, Object... paramVarArgs) {
    super(paramString);
    setConverter(paramTypeConverter);
    setObjectValues(paramVarArgs);
    setEvaluator(paramTypeEvaluator);
  }
  
  void setAnimatedValue(Object paramObject) {
    int[] arrayOfInt = (int[])getAnimatedValue();
    int i = arrayOfInt.length;
    long l = this.mJniSetter;
    if (l != 0L)
      if (i != 1) {
        if (i != 2) {
          if (i != 4) {
            PropertyValuesHolder.access$1200(paramObject, l, arrayOfInt);
          } else {
            PropertyValuesHolder.access$1100(paramObject, l, arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
          } 
        } else {
          PropertyValuesHolder.access$1000(paramObject, l, arrayOfInt[0], arrayOfInt[1]);
        } 
      } else {
        PropertyValuesHolder.access$200(paramObject, l, arrayOfInt[0]);
      }  
  }
  
  void setupSetter(Class paramClass) {
    if (this.mJniSetter != 0L)
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
        calculateValue(0.0F);
        int i = ((int[])getAnimatedValue()).length;
        try {
          this.mJniSetter = PropertyValuesHolder.access$1300(paramClass, str, i);
        } catch (NoSuchMethodError noSuchMethodError) {
          try {
            this.mJniSetter = PropertyValuesHolder.access$1300(paramClass, this.mPropertyName, i);
          } catch (NoSuchMethodError noSuchMethodError1) {}
        } 
        HashMap<Object, Object> hashMap1 = hashMap;
        if (hashMap == null) {
          hashMap1 = new HashMap<>();
          this();
          sJNISetterPropertyMap.put(paramClass, hashMap1);
        } 
        hashMap1.put(this.mPropertyName, Long.valueOf(this.mJniSetter));
      } 
      return;
    } 
  }
  
  void setupSetterAndGetter(Object paramObject) {
    setupSetter(paramObject.getClass());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PropertyValuesHolder$MultiIntValuesHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */