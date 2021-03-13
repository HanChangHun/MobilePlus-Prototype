package android.animation;

import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;
import java.lang.ref.WeakReference;

public final class ObjectAnimator extends ValueAnimator {
  private static final boolean DBG = false;
  
  private static final String LOG_TAG = "ObjectAnimator";
  
  private boolean mAutoCancel = false;
  
  private Property mProperty;
  
  private String mPropertyName;
  
  private WeakReference<Object> mTarget;
  
  public ObjectAnimator() {}
  
  private <T> ObjectAnimator(T paramT, Property<T, ?> paramProperty) {
    setTarget(paramT);
    setProperty(paramProperty);
  }
  
  private ObjectAnimator(Object paramObject, String paramString) {
    setTarget(paramObject);
    setPropertyName(paramString);
  }
  
  private boolean hasSameTargetAndProperties(Animator paramAnimator) {
    if (paramAnimator instanceof ObjectAnimator) {
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = ((ObjectAnimator)paramAnimator).getValues();
      if (((ObjectAnimator)paramAnimator).getTarget() == getTarget() && this.mValues.length == arrayOfPropertyValuesHolder.length) {
        for (byte b = 0; b < this.mValues.length; b++) {
          PropertyValuesHolder propertyValuesHolder1 = this.mValues[b];
          PropertyValuesHolder propertyValuesHolder2 = arrayOfPropertyValuesHolder[b];
          if (propertyValuesHolder1.getPropertyName() == null || !propertyValuesHolder1.getPropertyName().equals(propertyValuesHolder2.getPropertyName()))
            return false; 
        } 
        return true;
      } 
    } 
    return false;
  }
  
  public static <T> ObjectAnimator ofArgb(T paramT, Property<T, Integer> paramProperty, int... paramVarArgs) {
    ObjectAnimator objectAnimator = ofInt(paramT, paramProperty, paramVarArgs);
    objectAnimator.setEvaluator(ArgbEvaluator.getInstance());
    return objectAnimator;
  }
  
  public static ObjectAnimator ofArgb(Object paramObject, String paramString, int... paramVarArgs) {
    paramObject = ofInt(paramObject, paramString, paramVarArgs);
    paramObject.setEvaluator(ArgbEvaluator.getInstance());
    return (ObjectAnimator)paramObject;
  }
  
  public static <T> ObjectAnimator ofFloat(T paramT, Property<T, Float> paramProperty1, Property<T, Float> paramProperty2, Path paramPath) {
    PathKeyframes pathKeyframes = KeyframeSet.ofPath(paramPath);
    PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframes(paramProperty1, pathKeyframes.createXFloatKeyframes());
    return ofPropertyValuesHolder(paramT, new PropertyValuesHolder[] { propertyValuesHolder, PropertyValuesHolder.ofKeyframes(paramProperty2, pathKeyframes.createYFloatKeyframes()) });
  }
  
  public static <T> ObjectAnimator ofFloat(T paramT, Property<T, Float> paramProperty, float... paramVarArgs) {
    ObjectAnimator objectAnimator = new ObjectAnimator(paramT, paramProperty);
    objectAnimator.setFloatValues(paramVarArgs);
    return objectAnimator;
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, String paramString1, String paramString2, Path paramPath) {
    PathKeyframes pathKeyframes = KeyframeSet.ofPath(paramPath);
    PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframes(paramString1, pathKeyframes.createXFloatKeyframes());
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { propertyValuesHolder, PropertyValuesHolder.ofKeyframes(paramString2, pathKeyframes.createYFloatKeyframes()) });
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, String paramString, float... paramVarArgs) {
    paramObject = new ObjectAnimator(paramObject, paramString);
    paramObject.setFloatValues(paramVarArgs);
    return (ObjectAnimator)paramObject;
  }
  
  public static <T> ObjectAnimator ofInt(T paramT, Property<T, Integer> paramProperty1, Property<T, Integer> paramProperty2, Path paramPath) {
    PathKeyframes pathKeyframes = KeyframeSet.ofPath(paramPath);
    PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframes(paramProperty1, pathKeyframes.createXIntKeyframes());
    return ofPropertyValuesHolder(paramT, new PropertyValuesHolder[] { propertyValuesHolder, PropertyValuesHolder.ofKeyframes(paramProperty2, pathKeyframes.createYIntKeyframes()) });
  }
  
  public static <T> ObjectAnimator ofInt(T paramT, Property<T, Integer> paramProperty, int... paramVarArgs) {
    ObjectAnimator objectAnimator = new ObjectAnimator(paramT, paramProperty);
    objectAnimator.setIntValues(paramVarArgs);
    return objectAnimator;
  }
  
  public static ObjectAnimator ofInt(Object paramObject, String paramString1, String paramString2, Path paramPath) {
    PathKeyframes pathKeyframes = KeyframeSet.ofPath(paramPath);
    PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframes(paramString1, pathKeyframes.createXIntKeyframes());
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { propertyValuesHolder, PropertyValuesHolder.ofKeyframes(paramString2, pathKeyframes.createYIntKeyframes()) });
  }
  
  public static ObjectAnimator ofInt(Object paramObject, String paramString, int... paramVarArgs) {
    paramObject = new ObjectAnimator(paramObject, paramString);
    paramObject.setIntValues(paramVarArgs);
    return (ObjectAnimator)paramObject;
  }
  
  @SafeVarargs
  public static <T> ObjectAnimator ofMultiFloat(Object paramObject, String paramString, TypeConverter<T, float[]> paramTypeConverter, TypeEvaluator<T> paramTypeEvaluator, T... paramVarArgs) {
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofMultiFloat(paramString, paramTypeConverter, paramTypeEvaluator, paramVarArgs) });
  }
  
  public static ObjectAnimator ofMultiFloat(Object paramObject, String paramString, Path paramPath) {
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofMultiFloat(paramString, paramPath) });
  }
  
  public static ObjectAnimator ofMultiFloat(Object paramObject, String paramString, float[][] paramArrayOffloat) {
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofMultiFloat(paramString, paramArrayOffloat) });
  }
  
  @SafeVarargs
  public static <T> ObjectAnimator ofMultiInt(Object paramObject, String paramString, TypeConverter<T, int[]> paramTypeConverter, TypeEvaluator<T> paramTypeEvaluator, T... paramVarArgs) {
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofMultiInt(paramString, paramTypeConverter, paramTypeEvaluator, paramVarArgs) });
  }
  
  public static ObjectAnimator ofMultiInt(Object paramObject, String paramString, Path paramPath) {
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofMultiInt(paramString, paramPath) });
  }
  
  public static ObjectAnimator ofMultiInt(Object paramObject, String paramString, int[][] paramArrayOfint) {
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofMultiInt(paramString, paramArrayOfint) });
  }
  
  @SafeVarargs
  public static <T, V, P> ObjectAnimator ofObject(T paramT, Property<T, P> paramProperty, TypeConverter<V, P> paramTypeConverter, TypeEvaluator<V> paramTypeEvaluator, V... paramVarArgs) {
    return ofPropertyValuesHolder(paramT, new PropertyValuesHolder[] { PropertyValuesHolder.ofObject(paramProperty, paramTypeConverter, paramTypeEvaluator, paramVarArgs) });
  }
  
  public static <T, V> ObjectAnimator ofObject(T paramT, Property<T, V> paramProperty, TypeConverter<PointF, V> paramTypeConverter, Path paramPath) {
    return ofPropertyValuesHolder(paramT, new PropertyValuesHolder[] { PropertyValuesHolder.ofObject(paramProperty, paramTypeConverter, paramPath) });
  }
  
  @SafeVarargs
  public static <T, V> ObjectAnimator ofObject(T paramT, Property<T, V> paramProperty, TypeEvaluator<V> paramTypeEvaluator, V... paramVarArgs) {
    ObjectAnimator objectAnimator = new ObjectAnimator(paramT, paramProperty);
    objectAnimator.setObjectValues((Object[])paramVarArgs);
    objectAnimator.setEvaluator(paramTypeEvaluator);
    return objectAnimator;
  }
  
  public static ObjectAnimator ofObject(Object paramObject, String paramString, TypeConverter<PointF, ?> paramTypeConverter, Path paramPath) {
    return ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofObject(paramString, paramTypeConverter, paramPath) });
  }
  
  public static ObjectAnimator ofObject(Object paramObject, String paramString, TypeEvaluator paramTypeEvaluator, Object... paramVarArgs) {
    paramObject = new ObjectAnimator(paramObject, paramString);
    paramObject.setObjectValues(paramVarArgs);
    paramObject.setEvaluator(paramTypeEvaluator);
    return (ObjectAnimator)paramObject;
  }
  
  public static ObjectAnimator ofPropertyValuesHolder(Object paramObject, PropertyValuesHolder... paramVarArgs) {
    ObjectAnimator objectAnimator = new ObjectAnimator();
    objectAnimator.setTarget(paramObject);
    objectAnimator.setValues(paramVarArgs);
    return objectAnimator;
  }
  
  void animateValue(float paramFloat) {
    Object object = getTarget();
    if (this.mTarget != null && object == null) {
      cancel();
      return;
    } 
    super.animateValue(paramFloat);
    int i = this.mValues.length;
    for (byte b = 0; b < i; b++)
      this.mValues[b].setAnimatedValue(object); 
  }
  
  public ObjectAnimator clone() {
    return (ObjectAnimator)super.clone();
  }
  
  String getNameForTrace() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("animator:");
    stringBuilder.append(getPropertyName());
    return stringBuilder.toString();
  }
  
  public String getPropertyName() {
    String str;
    Property property1 = null;
    Property property2 = null;
    if (this.mPropertyName != null) {
      str = this.mPropertyName;
    } else {
      Property property = this.mProperty;
      if (property != null) {
        str = property.getName();
      } else {
        property = property1;
        if (this.mValues != null) {
          property = property1;
          if (this.mValues.length > 0) {
            byte b = 0;
            while (true) {
              property = property2;
              if (b < this.mValues.length) {
                if (b == 0) {
                  str = "";
                } else {
                  StringBuilder stringBuilder1 = new StringBuilder();
                  stringBuilder1.append((String)property2);
                  stringBuilder1.append(",");
                  str = stringBuilder1.toString();
                } 
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(this.mValues[b].getPropertyName());
                String str1 = stringBuilder.toString();
                b++;
                continue;
              } 
              break;
            } 
          } 
        } 
      } 
    } 
    return str;
  }
  
  public Object getTarget() {
    WeakReference<Object> weakReference = this.mTarget;
    if (weakReference == null) {
      weakReference = null;
    } else {
      weakReference = (WeakReference<Object>)weakReference.get();
    } 
    return weakReference;
  }
  
  void initAnimation() {
    if (!this.mInitialized) {
      Object object = getTarget();
      if (object != null) {
        int i = this.mValues.length;
        for (byte b = 0; b < i; b++)
          this.mValues[b].setupSetterAndGetter(object); 
      } 
      super.initAnimation();
    } 
  }
  
  boolean isInitialized() {
    return this.mInitialized;
  }
  
  public void setAutoCancel(boolean paramBoolean) {
    this.mAutoCancel = paramBoolean;
  }
  
  public ObjectAnimator setDuration(long paramLong) {
    super.setDuration(paramLong);
    return this;
  }
  
  public void setFloatValues(float... paramVarArgs) {
    if (this.mValues == null || this.mValues.length == 0) {
      Property<?, Float> property = this.mProperty;
      if (property != null) {
        setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(property, paramVarArgs) });
      } else {
        setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(this.mPropertyName, paramVarArgs) });
      } 
      return;
    } 
    super.setFloatValues(paramVarArgs);
  }
  
  public void setIntValues(int... paramVarArgs) {
    if (this.mValues == null || this.mValues.length == 0) {
      Property<?, Integer> property = this.mProperty;
      if (property != null) {
        setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofInt(property, paramVarArgs) });
      } else {
        setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofInt(this.mPropertyName, paramVarArgs) });
      } 
      return;
    } 
    super.setIntValues(paramVarArgs);
  }
  
  public void setObjectValues(Object... paramVarArgs) {
    if (this.mValues == null || this.mValues.length == 0) {
      Property property = this.mProperty;
      if (property != null) {
        setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofObject(property, (TypeEvaluator)null, paramVarArgs) });
      } else {
        setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofObject(this.mPropertyName, (TypeEvaluator)null, paramVarArgs) });
      } 
      return;
    } 
    super.setObjectValues(paramVarArgs);
  }
  
  public void setProperty(Property paramProperty) {
    if (this.mValues != null) {
      PropertyValuesHolder propertyValuesHolder = this.mValues[0];
      String str = propertyValuesHolder.getPropertyName();
      propertyValuesHolder.setProperty(paramProperty);
      this.mValuesMap.remove(str);
      this.mValuesMap.put(this.mPropertyName, propertyValuesHolder);
    } 
    if (this.mProperty != null)
      this.mPropertyName = paramProperty.getName(); 
    this.mProperty = paramProperty;
    this.mInitialized = false;
  }
  
  public void setPropertyName(String paramString) {
    if (this.mValues != null) {
      PropertyValuesHolder propertyValuesHolder = this.mValues[0];
      String str = propertyValuesHolder.getPropertyName();
      propertyValuesHolder.setPropertyName(paramString);
      this.mValuesMap.remove(str);
      this.mValuesMap.put(paramString, propertyValuesHolder);
    } 
    this.mPropertyName = paramString;
    this.mInitialized = false;
  }
  
  public void setTarget(Object paramObject) {
    if (getTarget() != paramObject) {
      if (isStarted())
        cancel(); 
      if (paramObject == null) {
        paramObject = null;
      } else {
        paramObject = new WeakReference(paramObject);
      } 
      this.mTarget = (WeakReference<Object>)paramObject;
      this.mInitialized = false;
    } 
  }
  
  public void setupEndValues() {
    initAnimation();
    Object object = getTarget();
    if (object != null) {
      int i = this.mValues.length;
      for (byte b = 0; b < i; b++)
        this.mValues[b].setupEndValue(object); 
    } 
  }
  
  public void setupStartValues() {
    initAnimation();
    Object object = getTarget();
    if (object != null) {
      int i = this.mValues.length;
      for (byte b = 0; b < i; b++)
        this.mValues[b].setupStartValue(object); 
    } 
  }
  
  boolean shouldAutoCancel(AnimationHandler.AnimationFrameCallback paramAnimationFrameCallback) {
    if (paramAnimationFrameCallback == null)
      return false; 
    if (paramAnimationFrameCallback instanceof ObjectAnimator) {
      paramAnimationFrameCallback = paramAnimationFrameCallback;
      if (((ObjectAnimator)paramAnimationFrameCallback).mAutoCancel && hasSameTargetAndProperties((Animator)paramAnimationFrameCallback))
        return true; 
    } 
    return false;
  }
  
  public void start() {
    AnimationHandler.getInstance().autoCancelBasedOn(this);
    super.start();
  }
  
  public String toString() {
    StringBuilder stringBuilder2;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("ObjectAnimator@");
    stringBuilder1.append(Integer.toHexString(hashCode()));
    stringBuilder1.append(", target ");
    stringBuilder1.append(getTarget());
    String str1 = stringBuilder1.toString();
    String str2 = str1;
    if (this.mValues != null) {
      byte b = 0;
      while (true) {
        str2 = str1;
        if (b < this.mValues.length) {
          stringBuilder2 = new StringBuilder();
          stringBuilder2.append(str1);
          stringBuilder2.append("\n    ");
          stringBuilder2.append(this.mValues[b].toString());
          str1 = stringBuilder2.toString();
          b++;
          continue;
        } 
        break;
      } 
    } 
    return (String)stringBuilder2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/ObjectAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */