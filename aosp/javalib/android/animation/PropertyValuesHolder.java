package android.animation;

import android.graphics.Path;
import android.graphics.PointF;
import android.util.FloatProperty;
import android.util.IntProperty;
import android.util.Log;
import android.util.PathParser;
import android.util.Property;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class PropertyValuesHolder implements Cloneable {
  private static Class[] DOUBLE_VARIANTS;
  
  private static Class[] FLOAT_VARIANTS;
  
  private static Class[] INTEGER_VARIANTS;
  
  private static final TypeEvaluator sFloatEvaluator;
  
  private static final HashMap<Class, HashMap<String, Method>> sGetterPropertyMap;
  
  private static final TypeEvaluator sIntEvaluator = new IntEvaluator();
  
  private static final HashMap<Class, HashMap<String, Method>> sSetterPropertyMap;
  
  private Object mAnimatedValue;
  
  private TypeConverter mConverter;
  
  private TypeEvaluator mEvaluator;
  
  private Method mGetter = null;
  
  Keyframes mKeyframes = null;
  
  protected Property mProperty;
  
  String mPropertyName;
  
  Method mSetter = null;
  
  final Object[] mTmpValueArray = new Object[1];
  
  Class mValueType;
  
  static {
    sFloatEvaluator = new FloatEvaluator();
    FLOAT_VARIANTS = new Class[] { float.class, Float.class, double.class, int.class, Double.class, Integer.class };
    INTEGER_VARIANTS = new Class[] { int.class, Integer.class, float.class, double.class, Float.class, Double.class };
    DOUBLE_VARIANTS = new Class[] { double.class, Double.class, float.class, int.class, Float.class, Integer.class };
    sSetterPropertyMap = new HashMap<>();
    sGetterPropertyMap = new HashMap<>();
  }
  
  private PropertyValuesHolder(Property paramProperty) {
    this.mProperty = paramProperty;
    if (paramProperty != null)
      this.mPropertyName = paramProperty.getName(); 
  }
  
  private PropertyValuesHolder(String paramString) {
    this.mPropertyName = paramString;
  }
  
  private Object convertBack(Object paramObject) {
    TypeConverter typeConverter = this.mConverter;
    Object object = paramObject;
    if (typeConverter != null)
      if (typeConverter instanceof BidirectionalTypeConverter) {
        object = ((BidirectionalTypeConverter)typeConverter).convertBack(paramObject);
      } else {
        paramObject = new StringBuilder();
        paramObject.append("Converter ");
        paramObject.append(this.mConverter.getClass().getName());
        paramObject.append(" must be a BidirectionalTypeConverter");
        throw new IllegalArgumentException(paramObject.toString());
      }  
    return object;
  }
  
  static String getMethodName(String paramString1, String paramString2) {
    if (paramString2 == null || paramString2.length() == 0)
      return paramString1; 
    char c = Character.toUpperCase(paramString2.charAt(0));
    paramString2 = paramString2.substring(1);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1);
    stringBuilder.append(c);
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  private Method getPropertyFunction(Class paramClass1, String paramString, Class paramClass2) {
    Method method1 = null;
    Method method2 = null;
    String str = getMethodName(paramString, this.mPropertyName);
    if (paramClass2 == null) {
      try {
        method1 = paramClass1.getMethod(str, null);
        method2 = method1;
        Method method = method2;
      } catch (NoSuchMethodException noSuchMethodException1) {
        Method method = method2;
      } 
    } else {
      Class[] arrayOfClass1;
      Class[] arrayOfClass2 = new Class[1];
      if (paramClass2.equals(Float.class)) {
        arrayOfClass1 = FLOAT_VARIANTS;
      } else if (paramClass2.equals(Integer.class)) {
        arrayOfClass1 = INTEGER_VARIANTS;
      } else if (paramClass2.equals(Double.class)) {
        arrayOfClass1 = DOUBLE_VARIANTS;
      } else {
        arrayOfClass1 = new Class[1];
        arrayOfClass1[0] = paramClass2;
      } 
      int i = arrayOfClass1.length;
      byte b = 0;
      while (true) {
        noSuchMethodException2 = noSuchMethodException1;
        if (b < i) {
          Class clazz = arrayOfClass1[b];
          arrayOfClass2[0] = clazz;
          try {
            Method method4 = paramClass1.getMethod(str, arrayOfClass2);
            Method method3 = method4;
            if (this.mConverter == null) {
              method3 = method4;
              this.mValueType = clazz;
            } 
            return method4;
          } catch (NoSuchMethodException noSuchMethodException2) {
            b++;
            continue;
          } 
        } 
        break;
      } 
    } 
    if (noSuchMethodException2 == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Method ");
      stringBuilder.append(getMethodName(paramString, this.mPropertyName));
      stringBuilder.append("() with type ");
      stringBuilder.append(paramClass2);
      stringBuilder.append(" not found on target class ");
      stringBuilder.append(paramClass1);
      Log.w("PropertyValuesHolder", stringBuilder.toString());
    } 
    return (Method)noSuchMethodException2;
  }
  
  private static native void nCallFloatMethod(Object paramObject, long paramLong, float paramFloat);
  
  private static native void nCallFourFloatMethod(Object paramObject, long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  private static native void nCallFourIntMethod(Object paramObject, long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  private static native void nCallIntMethod(Object paramObject, long paramLong, int paramInt);
  
  private static native void nCallMultipleFloatMethod(Object paramObject, long paramLong, float[] paramArrayOffloat);
  
  private static native void nCallMultipleIntMethod(Object paramObject, long paramLong, int[] paramArrayOfint);
  
  private static native void nCallTwoFloatMethod(Object paramObject, long paramLong, float paramFloat1, float paramFloat2);
  
  private static native void nCallTwoIntMethod(Object paramObject, long paramLong, int paramInt1, int paramInt2);
  
  private static native long nGetFloatMethod(Class paramClass, String paramString);
  
  private static native long nGetIntMethod(Class paramClass, String paramString);
  
  private static native long nGetMultipleFloatMethod(Class paramClass, String paramString, int paramInt);
  
  private static native long nGetMultipleIntMethod(Class paramClass, String paramString, int paramInt);
  
  public static PropertyValuesHolder ofFloat(Property<?, Float> paramProperty, float... paramVarArgs) {
    return new FloatPropertyValuesHolder(paramProperty, paramVarArgs);
  }
  
  public static PropertyValuesHolder ofFloat(String paramString, float... paramVarArgs) {
    return new FloatPropertyValuesHolder(paramString, paramVarArgs);
  }
  
  public static PropertyValuesHolder ofInt(Property<?, Integer> paramProperty, int... paramVarArgs) {
    return new IntPropertyValuesHolder(paramProperty, paramVarArgs);
  }
  
  public static PropertyValuesHolder ofInt(String paramString, int... paramVarArgs) {
    return new IntPropertyValuesHolder(paramString, paramVarArgs);
  }
  
  public static PropertyValuesHolder ofKeyframe(Property paramProperty, Keyframe... paramVarArgs) {
    return ofKeyframes(paramProperty, KeyframeSet.ofKeyframe(paramVarArgs));
  }
  
  public static PropertyValuesHolder ofKeyframe(String paramString, Keyframe... paramVarArgs) {
    return ofKeyframes(paramString, KeyframeSet.ofKeyframe(paramVarArgs));
  }
  
  static PropertyValuesHolder ofKeyframes(Property paramProperty, Keyframes paramKeyframes) {
    if (paramKeyframes instanceof Keyframes.IntKeyframes)
      return new IntPropertyValuesHolder(paramProperty, (Keyframes.IntKeyframes)paramKeyframes); 
    if (paramKeyframes instanceof Keyframes.FloatKeyframes)
      return new FloatPropertyValuesHolder(paramProperty, (Keyframes.FloatKeyframes)paramKeyframes); 
    PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(paramProperty);
    propertyValuesHolder.mKeyframes = paramKeyframes;
    propertyValuesHolder.mValueType = paramKeyframes.getType();
    return propertyValuesHolder;
  }
  
  static PropertyValuesHolder ofKeyframes(String paramString, Keyframes paramKeyframes) {
    if (paramKeyframes instanceof Keyframes.IntKeyframes)
      return new IntPropertyValuesHolder(paramString, (Keyframes.IntKeyframes)paramKeyframes); 
    if (paramKeyframes instanceof Keyframes.FloatKeyframes)
      return new FloatPropertyValuesHolder(paramString, (Keyframes.FloatKeyframes)paramKeyframes); 
    PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(paramString);
    propertyValuesHolder.mKeyframes = paramKeyframes;
    propertyValuesHolder.mValueType = paramKeyframes.getType();
    return propertyValuesHolder;
  }
  
  public static <T> PropertyValuesHolder ofMultiFloat(String paramString, TypeConverter<T, float[]> paramTypeConverter, TypeEvaluator<T> paramTypeEvaluator, Keyframe... paramVarArgs) {
    return new MultiFloatValuesHolder(paramString, paramTypeConverter, paramTypeEvaluator, KeyframeSet.ofKeyframe(paramVarArgs));
  }
  
  @SafeVarargs
  public static <V> PropertyValuesHolder ofMultiFloat(String paramString, TypeConverter<V, float[]> paramTypeConverter, TypeEvaluator<V> paramTypeEvaluator, V... paramVarArgs) {
    return new MultiFloatValuesHolder(paramString, paramTypeConverter, paramTypeEvaluator, (Object[])paramVarArgs);
  }
  
  public static PropertyValuesHolder ofMultiFloat(String paramString, Path paramPath) {
    PathKeyframes pathKeyframes = KeyframeSet.ofPath(paramPath);
    return new MultiFloatValuesHolder(paramString, new PointFToFloatArray(), null, pathKeyframes);
  }
  
  public static PropertyValuesHolder ofMultiFloat(String paramString, float[][] paramArrayOffloat) {
    if (paramArrayOffloat.length >= 2) {
      int i = 0;
      byte b = 0;
      while (b < paramArrayOffloat.length) {
        if (paramArrayOffloat[b] != null) {
          int j = (paramArrayOffloat[b]).length;
          if (b == 0) {
            i = j;
          } else if (j != i) {
            throw new IllegalArgumentException("Values must all have the same length");
          } 
          b++;
          continue;
        } 
        throw new IllegalArgumentException("values must not be null");
      } 
      return new MultiFloatValuesHolder(paramString, null, new FloatArrayEvaluator(new float[i]), (Object[])paramArrayOffloat);
    } 
    throw new IllegalArgumentException("At least 2 values must be supplied");
  }
  
  public static <T> PropertyValuesHolder ofMultiInt(String paramString, TypeConverter<T, int[]> paramTypeConverter, TypeEvaluator<T> paramTypeEvaluator, Keyframe... paramVarArgs) {
    return new MultiIntValuesHolder(paramString, paramTypeConverter, paramTypeEvaluator, KeyframeSet.ofKeyframe(paramVarArgs));
  }
  
  @SafeVarargs
  public static <V> PropertyValuesHolder ofMultiInt(String paramString, TypeConverter<V, int[]> paramTypeConverter, TypeEvaluator<V> paramTypeEvaluator, V... paramVarArgs) {
    return new MultiIntValuesHolder(paramString, paramTypeConverter, paramTypeEvaluator, (Object[])paramVarArgs);
  }
  
  public static PropertyValuesHolder ofMultiInt(String paramString, Path paramPath) {
    PathKeyframes pathKeyframes = KeyframeSet.ofPath(paramPath);
    return new MultiIntValuesHolder(paramString, new PointFToIntArray(), null, pathKeyframes);
  }
  
  public static PropertyValuesHolder ofMultiInt(String paramString, int[][] paramArrayOfint) {
    if (paramArrayOfint.length >= 2) {
      int i = 0;
      byte b = 0;
      while (b < paramArrayOfint.length) {
        if (paramArrayOfint[b] != null) {
          int j = (paramArrayOfint[b]).length;
          if (b == 0) {
            i = j;
          } else if (j != i) {
            throw new IllegalArgumentException("Values must all have the same length");
          } 
          b++;
          continue;
        } 
        throw new IllegalArgumentException("values must not be null");
      } 
      return new MultiIntValuesHolder(paramString, null, new IntArrayEvaluator(new int[i]), (Object[])paramArrayOfint);
    } 
    throw new IllegalArgumentException("At least 2 values must be supplied");
  }
  
  @SafeVarargs
  public static <T, V> PropertyValuesHolder ofObject(Property<?, V> paramProperty, TypeConverter<T, V> paramTypeConverter, TypeEvaluator<T> paramTypeEvaluator, T... paramVarArgs) {
    PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(paramProperty);
    propertyValuesHolder.setConverter(paramTypeConverter);
    propertyValuesHolder.setObjectValues((Object[])paramVarArgs);
    propertyValuesHolder.setEvaluator(paramTypeEvaluator);
    return propertyValuesHolder;
  }
  
  public static <V> PropertyValuesHolder ofObject(Property<?, V> paramProperty, TypeConverter<PointF, V> paramTypeConverter, Path paramPath) {
    PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(paramProperty);
    propertyValuesHolder.mKeyframes = KeyframeSet.ofPath(paramPath);
    propertyValuesHolder.mValueType = PointF.class;
    propertyValuesHolder.setConverter(paramTypeConverter);
    return propertyValuesHolder;
  }
  
  @SafeVarargs
  public static <V> PropertyValuesHolder ofObject(Property paramProperty, TypeEvaluator<V> paramTypeEvaluator, V... paramVarArgs) {
    PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(paramProperty);
    propertyValuesHolder.setObjectValues((Object[])paramVarArgs);
    propertyValuesHolder.setEvaluator(paramTypeEvaluator);
    return propertyValuesHolder;
  }
  
  public static PropertyValuesHolder ofObject(String paramString, TypeConverter<PointF, ?> paramTypeConverter, Path paramPath) {
    PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(paramString);
    propertyValuesHolder.mKeyframes = KeyframeSet.ofPath(paramPath);
    propertyValuesHolder.mValueType = PointF.class;
    propertyValuesHolder.setConverter(paramTypeConverter);
    return propertyValuesHolder;
  }
  
  public static PropertyValuesHolder ofObject(String paramString, TypeEvaluator paramTypeEvaluator, Object... paramVarArgs) {
    PropertyValuesHolder propertyValuesHolder = new PropertyValuesHolder(paramString);
    propertyValuesHolder.setObjectValues(paramVarArgs);
    propertyValuesHolder.setEvaluator(paramTypeEvaluator);
    return propertyValuesHolder;
  }
  
  private void setupGetter(Class paramClass) {
    this.mGetter = setupSetterOrGetter(paramClass, sGetterPropertyMap, "get", null);
  }
  
  private Method setupSetterOrGetter(Class paramClass1, HashMap<Class, HashMap<String, Method>> paramHashMap, String paramString, Class paramClass2) {
    synchronized (null) {
      HashMap<Object, Object> hashMap = (HashMap)paramHashMap.get(paramClass1);
      boolean bool = false;
      Method method = null;
      if (hashMap != null) {
        boolean bool1 = hashMap.containsKey(this.mPropertyName);
        method = null;
        bool = bool1;
        if (bool1) {
          method = (Method)hashMap.get(this.mPropertyName);
          bool = bool1;
        } 
      } 
      if (!bool) {
        method = getPropertyFunction(paramClass1, paramString, paramClass2);
        HashMap<Object, Object> hashMap1 = hashMap;
        if (hashMap == null) {
          hashMap1 = new HashMap<>();
          this();
          paramHashMap.put(paramClass1, hashMap1);
        } 
        hashMap1.put(this.mPropertyName, method);
      } 
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/HashMap<[ObjectType{java/lang/Class}, ObjectType{java/util/HashMap<[ObjectType{java/lang/String}, ObjectType{java/lang/reflect/Method}]>}]>}, name=paramHashMap} */
      return method;
    } 
  }
  
  private void setupValue(Object paramObject, Keyframe paramKeyframe) {
    Property property = this.mProperty;
    if (property != null) {
      paramKeyframe.setValue(convertBack(property.get(paramObject)));
    } else {
      try {
        if (this.mGetter == null) {
          setupGetter(paramObject.getClass());
          if (this.mGetter == null)
            return; 
        } 
        paramKeyframe.setValue(convertBack(this.mGetter.invoke(paramObject, new Object[0])));
      } catch (InvocationTargetException invocationTargetException) {
        Log.e("PropertyValuesHolder", invocationTargetException.toString());
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("PropertyValuesHolder", illegalAccessException.toString());
      } 
    } 
  }
  
  void calculateValue(float paramFloat) {
    Object object = this.mKeyframes.getValue(paramFloat);
    TypeConverter typeConverter = this.mConverter;
    if (typeConverter != null)
      object = typeConverter.convert(object); 
    this.mAnimatedValue = object;
  }
  
  public PropertyValuesHolder clone() {
    try {
      PropertyValuesHolder propertyValuesHolder = (PropertyValuesHolder)super.clone();
      propertyValuesHolder.mPropertyName = this.mPropertyName;
      propertyValuesHolder.mProperty = this.mProperty;
      propertyValuesHolder.mKeyframes = this.mKeyframes.clone();
      propertyValuesHolder.mEvaluator = this.mEvaluator;
      return propertyValuesHolder;
    } catch (CloneNotSupportedException cloneNotSupportedException) {
      return null;
    } 
  }
  
  Object getAnimatedValue() {
    return this.mAnimatedValue;
  }
  
  public String getPropertyName() {
    return this.mPropertyName;
  }
  
  public void getPropertyValues(PropertyValues paramPropertyValues) {
    init();
    paramPropertyValues.propertyName = this.mPropertyName;
    paramPropertyValues.type = this.mValueType;
    paramPropertyValues.startValue = this.mKeyframes.getValue(0.0F);
    if (paramPropertyValues.startValue instanceof PathParser.PathData)
      paramPropertyValues.startValue = new PathParser.PathData((PathParser.PathData)paramPropertyValues.startValue); 
    paramPropertyValues.endValue = this.mKeyframes.getValue(1.0F);
    if (paramPropertyValues.endValue instanceof PathParser.PathData)
      paramPropertyValues.endValue = new PathParser.PathData((PathParser.PathData)paramPropertyValues.endValue); 
    Keyframes keyframes = this.mKeyframes;
    if (keyframes instanceof PathKeyframes.FloatKeyframesBase || keyframes instanceof PathKeyframes.IntKeyframesBase || (keyframes.getKeyframes() != null && this.mKeyframes.getKeyframes().size() > 2)) {
      paramPropertyValues.dataSource = new PropertyValues.DataSource() {
          public Object getValueAtFraction(float param1Float) {
            return PropertyValuesHolder.this.mKeyframes.getValue(param1Float);
          }
        };
      return;
    } 
    paramPropertyValues.dataSource = null;
  }
  
  public Class getValueType() {
    return this.mValueType;
  }
  
  void init() {
    if (this.mEvaluator == null) {
      TypeEvaluator typeEvaluator1;
      Class<Integer> clazz = this.mValueType;
      if (clazz == Integer.class) {
        typeEvaluator1 = sIntEvaluator;
      } else if (typeEvaluator1 == Float.class) {
        typeEvaluator1 = sFloatEvaluator;
      } else {
        typeEvaluator1 = null;
      } 
      this.mEvaluator = typeEvaluator1;
    } 
    TypeEvaluator typeEvaluator = this.mEvaluator;
    if (typeEvaluator != null)
      this.mKeyframes.setEvaluator(typeEvaluator); 
  }
  
  void setAnimatedValue(Object paramObject) {
    Property property = this.mProperty;
    if (property != null)
      property.set(paramObject, getAnimatedValue()); 
    if (this.mSetter != null)
      try {
        this.mTmpValueArray[0] = getAnimatedValue();
        this.mSetter.invoke(paramObject, this.mTmpValueArray);
      } catch (InvocationTargetException invocationTargetException) {
        Log.e("PropertyValuesHolder", invocationTargetException.toString());
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("PropertyValuesHolder", illegalAccessException.toString());
      }  
  }
  
  public void setConverter(TypeConverter paramTypeConverter) {
    this.mConverter = paramTypeConverter;
  }
  
  public void setEvaluator(TypeEvaluator paramTypeEvaluator) {
    this.mEvaluator = paramTypeEvaluator;
    this.mKeyframes.setEvaluator(paramTypeEvaluator);
  }
  
  public void setFloatValues(float... paramVarArgs) {
    this.mValueType = float.class;
    this.mKeyframes = KeyframeSet.ofFloat(paramVarArgs);
  }
  
  public void setIntValues(int... paramVarArgs) {
    this.mValueType = int.class;
    this.mKeyframes = KeyframeSet.ofInt(paramVarArgs);
  }
  
  public void setKeyframes(Keyframe... paramVarArgs) {
    int i = paramVarArgs.length;
    Keyframe[] arrayOfKeyframe = new Keyframe[Math.max(i, 2)];
    this.mValueType = paramVarArgs[0].getType();
    for (byte b = 0; b < i; b++)
      arrayOfKeyframe[b] = paramVarArgs[b]; 
    this.mKeyframes = new KeyframeSet(arrayOfKeyframe);
  }
  
  public void setObjectValues(Object... paramVarArgs) {
    this.mValueType = paramVarArgs[0].getClass();
    KeyframeSet keyframeSet = KeyframeSet.ofObject(paramVarArgs);
    this.mKeyframes = keyframeSet;
    TypeEvaluator typeEvaluator = this.mEvaluator;
    if (typeEvaluator != null)
      keyframeSet.setEvaluator(typeEvaluator); 
  }
  
  public void setProperty(Property paramProperty) {
    this.mProperty = paramProperty;
  }
  
  public void setPropertyName(String paramString) {
    this.mPropertyName = paramString;
  }
  
  void setupEndValue(Object paramObject) {
    List<Keyframe> list = this.mKeyframes.getKeyframes();
    if (!list.isEmpty())
      setupValue(paramObject, list.get(list.size() - 1)); 
  }
  
  void setupSetter(Class paramClass) {
    Class clazz;
    TypeConverter typeConverter = this.mConverter;
    if (typeConverter == null) {
      clazz = this.mValueType;
    } else {
      clazz = clazz.getTargetType();
    } 
    this.mSetter = setupSetterOrGetter(paramClass, sSetterPropertyMap, "set", clazz);
  }
  
  void setupSetterAndGetter(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mProperty : Landroid/util/Property;
    //   4: ifnull -> 190
    //   7: aconst_null
    //   8: astore_2
    //   9: aload_0
    //   10: getfield mKeyframes : Landroid/animation/Keyframes;
    //   13: invokeinterface getKeyframes : ()Ljava/util/List;
    //   18: astore_3
    //   19: aload_3
    //   20: ifnonnull -> 29
    //   23: iconst_0
    //   24: istore #4
    //   26: goto -> 37
    //   29: aload_3
    //   30: invokeinterface size : ()I
    //   35: istore #4
    //   37: iconst_0
    //   38: istore #5
    //   40: iload #5
    //   42: iload #4
    //   44: if_icmpge -> 122
    //   47: aload_3
    //   48: iload #5
    //   50: invokeinterface get : (I)Ljava/lang/Object;
    //   55: checkcast android/animation/Keyframe
    //   58: astore #6
    //   60: aload #6
    //   62: invokevirtual hasValue : ()Z
    //   65: ifeq -> 79
    //   68: aload_2
    //   69: astore #7
    //   71: aload #6
    //   73: invokevirtual valueWasSetOnStart : ()Z
    //   76: ifeq -> 113
    //   79: aload_2
    //   80: astore #7
    //   82: aload_2
    //   83: ifnonnull -> 100
    //   86: aload_0
    //   87: aload_0
    //   88: getfield mProperty : Landroid/util/Property;
    //   91: aload_1
    //   92: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   95: invokespecial convertBack : (Ljava/lang/Object;)Ljava/lang/Object;
    //   98: astore #7
    //   100: aload #6
    //   102: aload #7
    //   104: invokevirtual setValue : (Ljava/lang/Object;)V
    //   107: aload #6
    //   109: iconst_1
    //   110: invokevirtual setValueWasSetOnStart : (Z)V
    //   113: iinc #5, 1
    //   116: aload #7
    //   118: astore_2
    //   119: goto -> 40
    //   122: return
    //   123: astore_2
    //   124: new java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial <init> : ()V
    //   131: astore_2
    //   132: aload_2
    //   133: ldc_w 'No such property ('
    //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload_2
    //   141: aload_0
    //   142: getfield mProperty : Landroid/util/Property;
    //   145: invokevirtual getName : ()Ljava/lang/String;
    //   148: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload_2
    //   153: ldc_w ') on target object '
    //   156: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: pop
    //   160: aload_2
    //   161: aload_1
    //   162: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   165: pop
    //   166: aload_2
    //   167: ldc_w '. Trying reflection instead'
    //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: ldc_w 'PropertyValuesHolder'
    //   177: aload_2
    //   178: invokevirtual toString : ()Ljava/lang/String;
    //   181: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   184: pop
    //   185: aload_0
    //   186: aconst_null
    //   187: putfield mProperty : Landroid/util/Property;
    //   190: aload_0
    //   191: getfield mProperty : Landroid/util/Property;
    //   194: ifnonnull -> 362
    //   197: aload_1
    //   198: invokevirtual getClass : ()Ljava/lang/Class;
    //   201: astore #7
    //   203: aload_0
    //   204: getfield mSetter : Ljava/lang/reflect/Method;
    //   207: ifnonnull -> 216
    //   210: aload_0
    //   211: aload #7
    //   213: invokevirtual setupSetter : (Ljava/lang/Class;)V
    //   216: aload_0
    //   217: getfield mKeyframes : Landroid/animation/Keyframes;
    //   220: invokeinterface getKeyframes : ()Ljava/util/List;
    //   225: astore_2
    //   226: aload_2
    //   227: ifnonnull -> 236
    //   230: iconst_0
    //   231: istore #4
    //   233: goto -> 244
    //   236: aload_2
    //   237: invokeinterface size : ()I
    //   242: istore #4
    //   244: iconst_0
    //   245: istore #5
    //   247: iload #5
    //   249: iload #4
    //   251: if_icmpge -> 362
    //   254: aload_2
    //   255: iload #5
    //   257: invokeinterface get : (I)Ljava/lang/Object;
    //   262: checkcast android/animation/Keyframe
    //   265: astore_3
    //   266: aload_3
    //   267: invokevirtual hasValue : ()Z
    //   270: ifeq -> 280
    //   273: aload_3
    //   274: invokevirtual valueWasSetOnStart : ()Z
    //   277: ifeq -> 356
    //   280: aload_0
    //   281: getfield mGetter : Ljava/lang/reflect/Method;
    //   284: ifnonnull -> 301
    //   287: aload_0
    //   288: aload #7
    //   290: invokespecial setupGetter : (Ljava/lang/Class;)V
    //   293: aload_0
    //   294: getfield mGetter : Ljava/lang/reflect/Method;
    //   297: ifnonnull -> 301
    //   300: return
    //   301: aload_3
    //   302: aload_0
    //   303: aload_0
    //   304: getfield mGetter : Ljava/lang/reflect/Method;
    //   307: aload_1
    //   308: iconst_0
    //   309: anewarray java/lang/Object
    //   312: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   315: invokespecial convertBack : (Ljava/lang/Object;)Ljava/lang/Object;
    //   318: invokevirtual setValue : (Ljava/lang/Object;)V
    //   321: aload_3
    //   322: iconst_1
    //   323: invokevirtual setValueWasSetOnStart : (Z)V
    //   326: goto -> 356
    //   329: astore_3
    //   330: ldc_w 'PropertyValuesHolder'
    //   333: aload_3
    //   334: invokevirtual toString : ()Ljava/lang/String;
    //   337: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   340: pop
    //   341: goto -> 356
    //   344: astore_3
    //   345: ldc_w 'PropertyValuesHolder'
    //   348: aload_3
    //   349: invokevirtual toString : ()Ljava/lang/String;
    //   352: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   355: pop
    //   356: iinc #5, 1
    //   359: goto -> 247
    //   362: return
    // Exception table:
    //   from	to	target	type
    //   9	19	123	java/lang/ClassCastException
    //   29	37	123	java/lang/ClassCastException
    //   47	68	123	java/lang/ClassCastException
    //   71	79	123	java/lang/ClassCastException
    //   86	100	123	java/lang/ClassCastException
    //   100	113	123	java/lang/ClassCastException
    //   301	326	344	java/lang/reflect/InvocationTargetException
    //   301	326	329	java/lang/IllegalAccessException
  }
  
  void setupStartValue(Object paramObject) {
    List<Keyframe> list = this.mKeyframes.getKeyframes();
    if (!list.isEmpty())
      setupValue(paramObject, list.get(0)); 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.mPropertyName);
    stringBuilder.append(": ");
    stringBuilder.append(this.mKeyframes.toString());
    return stringBuilder.toString();
  }
  
  static class FloatPropertyValuesHolder extends PropertyValuesHolder {
    private static final HashMap<Class, HashMap<String, Long>> sJNISetterPropertyMap = new HashMap<>();
    
    float mFloatAnimatedValue;
    
    Keyframes.FloatKeyframes mFloatKeyframes;
    
    private FloatProperty mFloatProperty;
    
    long mJniSetter;
    
    public FloatPropertyValuesHolder(Property param1Property, Keyframes.FloatKeyframes param1FloatKeyframes) {
      super(param1Property);
      this.mValueType = float.class;
      this.mKeyframes = param1FloatKeyframes;
      this.mFloatKeyframes = param1FloatKeyframes;
      if (param1Property instanceof FloatProperty)
        this.mFloatProperty = (FloatProperty)this.mProperty; 
    }
    
    public FloatPropertyValuesHolder(Property param1Property, float... param1VarArgs) {
      super(param1Property);
      setFloatValues(param1VarArgs);
      if (param1Property instanceof FloatProperty)
        this.mFloatProperty = (FloatProperty)this.mProperty; 
    }
    
    public FloatPropertyValuesHolder(String param1String, Keyframes.FloatKeyframes param1FloatKeyframes) {
      super(param1String);
      this.mValueType = float.class;
      this.mKeyframes = param1FloatKeyframes;
      this.mFloatKeyframes = param1FloatKeyframes;
    }
    
    public FloatPropertyValuesHolder(String param1String, float... param1VarArgs) {
      super(param1String);
      setFloatValues(param1VarArgs);
    }
    
    void calculateValue(float param1Float) {
      this.mFloatAnimatedValue = this.mFloatKeyframes.getFloatValue(param1Float);
    }
    
    public FloatPropertyValuesHolder clone() {
      FloatPropertyValuesHolder floatPropertyValuesHolder = (FloatPropertyValuesHolder)super.clone();
      floatPropertyValuesHolder.mFloatKeyframes = (Keyframes.FloatKeyframes)floatPropertyValuesHolder.mKeyframes;
      return floatPropertyValuesHolder;
    }
    
    Object getAnimatedValue() {
      return Float.valueOf(this.mFloatAnimatedValue);
    }
    
    void setAnimatedValue(Object param1Object) {
      FloatProperty floatProperty = this.mFloatProperty;
      if (floatProperty != null) {
        floatProperty.setValue(param1Object, this.mFloatAnimatedValue);
        return;
      } 
      if (this.mProperty != null) {
        this.mProperty.set(param1Object, Float.valueOf(this.mFloatAnimatedValue));
        return;
      } 
      long l = this.mJniSetter;
      if (l != 0L) {
        PropertyValuesHolder.nCallFloatMethod(param1Object, l, this.mFloatAnimatedValue);
        return;
      } 
      if (this.mSetter != null)
        try {
          this.mTmpValueArray[0] = Float.valueOf(this.mFloatAnimatedValue);
          this.mSetter.invoke(param1Object, this.mTmpValueArray);
        } catch (InvocationTargetException invocationTargetException) {
          Log.e("PropertyValuesHolder", invocationTargetException.toString());
        } catch (IllegalAccessException illegalAccessException) {
          Log.e("PropertyValuesHolder", illegalAccessException.toString());
        }  
    }
    
    public void setFloatValues(float... param1VarArgs) {
      super.setFloatValues(param1VarArgs);
      this.mFloatKeyframes = (Keyframes.FloatKeyframes)this.mKeyframes;
    }
    
    public void setProperty(Property param1Property) {
      if (param1Property instanceof FloatProperty) {
        this.mFloatProperty = (FloatProperty)param1Property;
      } else {
        super.setProperty(param1Property);
      } 
    }
    
    void setupSetter(Class param1Class) {
      if (this.mProperty != null)
        return; 
      synchronized (sJNISetterPropertyMap) {
        HashMap<Object, Object> hashMap = (HashMap)sJNISetterPropertyMap.get(param1Class);
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
            this.mJniSetter = PropertyValuesHolder.nGetFloatMethod(param1Class, str);
          } catch (NoSuchMethodError noSuchMethodError) {}
          HashMap<Object, Object> hashMap1 = hashMap;
          if (hashMap == null) {
            hashMap1 = new HashMap<>();
            this();
            sJNISetterPropertyMap.put(param1Class, hashMap1);
          } 
          hashMap1.put(this.mPropertyName, Long.valueOf(this.mJniSetter));
        } 
        if (this.mJniSetter == 0L)
          super.setupSetter(param1Class); 
        return;
      } 
    }
  }
  
  static class IntPropertyValuesHolder extends PropertyValuesHolder {
    private static final HashMap<Class, HashMap<String, Long>> sJNISetterPropertyMap = new HashMap<>();
    
    int mIntAnimatedValue;
    
    Keyframes.IntKeyframes mIntKeyframes;
    
    private IntProperty mIntProperty;
    
    long mJniSetter;
    
    public IntPropertyValuesHolder(Property param1Property, Keyframes.IntKeyframes param1IntKeyframes) {
      super(param1Property);
      this.mValueType = int.class;
      this.mKeyframes = param1IntKeyframes;
      this.mIntKeyframes = param1IntKeyframes;
      if (param1Property instanceof IntProperty)
        this.mIntProperty = (IntProperty)this.mProperty; 
    }
    
    public IntPropertyValuesHolder(Property param1Property, int... param1VarArgs) {
      super(param1Property);
      setIntValues(param1VarArgs);
      if (param1Property instanceof IntProperty)
        this.mIntProperty = (IntProperty)this.mProperty; 
    }
    
    public IntPropertyValuesHolder(String param1String, Keyframes.IntKeyframes param1IntKeyframes) {
      super(param1String);
      this.mValueType = int.class;
      this.mKeyframes = param1IntKeyframes;
      this.mIntKeyframes = param1IntKeyframes;
    }
    
    public IntPropertyValuesHolder(String param1String, int... param1VarArgs) {
      super(param1String);
      setIntValues(param1VarArgs);
    }
    
    void calculateValue(float param1Float) {
      this.mIntAnimatedValue = this.mIntKeyframes.getIntValue(param1Float);
    }
    
    public IntPropertyValuesHolder clone() {
      IntPropertyValuesHolder intPropertyValuesHolder = (IntPropertyValuesHolder)super.clone();
      intPropertyValuesHolder.mIntKeyframes = (Keyframes.IntKeyframes)intPropertyValuesHolder.mKeyframes;
      return intPropertyValuesHolder;
    }
    
    Object getAnimatedValue() {
      return Integer.valueOf(this.mIntAnimatedValue);
    }
    
    void setAnimatedValue(Object param1Object) {
      IntProperty intProperty = this.mIntProperty;
      if (intProperty != null) {
        intProperty.setValue(param1Object, this.mIntAnimatedValue);
        return;
      } 
      if (this.mProperty != null) {
        this.mProperty.set(param1Object, Integer.valueOf(this.mIntAnimatedValue));
        return;
      } 
      long l = this.mJniSetter;
      if (l != 0L) {
        PropertyValuesHolder.nCallIntMethod(param1Object, l, this.mIntAnimatedValue);
        return;
      } 
      if (this.mSetter != null)
        try {
          this.mTmpValueArray[0] = Integer.valueOf(this.mIntAnimatedValue);
          this.mSetter.invoke(param1Object, this.mTmpValueArray);
        } catch (InvocationTargetException invocationTargetException) {
          Log.e("PropertyValuesHolder", invocationTargetException.toString());
        } catch (IllegalAccessException illegalAccessException) {
          Log.e("PropertyValuesHolder", illegalAccessException.toString());
        }  
    }
    
    public void setIntValues(int... param1VarArgs) {
      super.setIntValues(param1VarArgs);
      this.mIntKeyframes = (Keyframes.IntKeyframes)this.mKeyframes;
    }
    
    public void setProperty(Property param1Property) {
      if (param1Property instanceof IntProperty) {
        this.mIntProperty = (IntProperty)param1Property;
      } else {
        super.setProperty(param1Property);
      } 
    }
    
    void setupSetter(Class param1Class) {
      if (this.mProperty != null)
        return; 
      synchronized (sJNISetterPropertyMap) {
        HashMap<Object, Object> hashMap = (HashMap)sJNISetterPropertyMap.get(param1Class);
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
            this.mJniSetter = PropertyValuesHolder.nGetIntMethod(param1Class, str);
          } catch (NoSuchMethodError noSuchMethodError) {}
          HashMap<Object, Object> hashMap1 = hashMap;
          if (hashMap == null) {
            hashMap1 = new HashMap<>();
            this();
            sJNISetterPropertyMap.put(param1Class, hashMap1);
          } 
          hashMap1.put(this.mPropertyName, Long.valueOf(this.mJniSetter));
        } 
        if (this.mJniSetter == 0L)
          super.setupSetter(param1Class); 
        return;
      } 
    }
  }
  
  static class MultiFloatValuesHolder extends PropertyValuesHolder {
    private static final HashMap<Class, HashMap<String, Long>> sJNISetterPropertyMap = new HashMap<>();
    
    private long mJniSetter;
    
    public MultiFloatValuesHolder(String param1String, TypeConverter param1TypeConverter, TypeEvaluator param1TypeEvaluator, Keyframes param1Keyframes) {
      super(param1String);
      setConverter(param1TypeConverter);
      this.mKeyframes = param1Keyframes;
      setEvaluator(param1TypeEvaluator);
    }
    
    public MultiFloatValuesHolder(String param1String, TypeConverter param1TypeConverter, TypeEvaluator param1TypeEvaluator, Object... param1VarArgs) {
      super(param1String);
      setConverter(param1TypeConverter);
      setObjectValues(param1VarArgs);
      setEvaluator(param1TypeEvaluator);
    }
    
    void setAnimatedValue(Object param1Object) {
      float[] arrayOfFloat = (float[])getAnimatedValue();
      int i = arrayOfFloat.length;
      long l = this.mJniSetter;
      if (l != 0L)
        if (i != 1) {
          if (i != 2) {
            if (i != 4) {
              PropertyValuesHolder.nCallMultipleFloatMethod(param1Object, l, arrayOfFloat);
            } else {
              PropertyValuesHolder.nCallFourFloatMethod(param1Object, l, arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
            } 
          } else {
            PropertyValuesHolder.nCallTwoFloatMethod(param1Object, l, arrayOfFloat[0], arrayOfFloat[1]);
          } 
        } else {
          PropertyValuesHolder.nCallFloatMethod(param1Object, l, arrayOfFloat[0]);
        }  
    }
    
    void setupSetter(Class param1Class) {
      if (this.mJniSetter != 0L)
        return; 
      synchronized (sJNISetterPropertyMap) {
        HashMap<Object, Object> hashMap = (HashMap)sJNISetterPropertyMap.get(param1Class);
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
          int i = ((float[])getAnimatedValue()).length;
          try {
            this.mJniSetter = PropertyValuesHolder.nGetMultipleFloatMethod(param1Class, str, i);
          } catch (NoSuchMethodError noSuchMethodError) {
            try {
              this.mJniSetter = PropertyValuesHolder.nGetMultipleFloatMethod(param1Class, this.mPropertyName, i);
            } catch (NoSuchMethodError noSuchMethodError1) {}
          } 
          HashMap<Object, Object> hashMap1 = hashMap;
          if (hashMap == null) {
            hashMap1 = new HashMap<>();
            this();
            sJNISetterPropertyMap.put(param1Class, hashMap1);
          } 
          hashMap1.put(this.mPropertyName, Long.valueOf(this.mJniSetter));
        } 
        return;
      } 
    }
    
    void setupSetterAndGetter(Object param1Object) {
      setupSetter(param1Object.getClass());
    }
  }
  
  static class MultiIntValuesHolder extends PropertyValuesHolder {
    private static final HashMap<Class, HashMap<String, Long>> sJNISetterPropertyMap = new HashMap<>();
    
    private long mJniSetter;
    
    public MultiIntValuesHolder(String param1String, TypeConverter param1TypeConverter, TypeEvaluator param1TypeEvaluator, Keyframes param1Keyframes) {
      super(param1String);
      setConverter(param1TypeConverter);
      this.mKeyframes = param1Keyframes;
      setEvaluator(param1TypeEvaluator);
    }
    
    public MultiIntValuesHolder(String param1String, TypeConverter param1TypeConverter, TypeEvaluator param1TypeEvaluator, Object... param1VarArgs) {
      super(param1String);
      setConverter(param1TypeConverter);
      setObjectValues(param1VarArgs);
      setEvaluator(param1TypeEvaluator);
    }
    
    void setAnimatedValue(Object param1Object) {
      int[] arrayOfInt = (int[])getAnimatedValue();
      int i = arrayOfInt.length;
      long l = this.mJniSetter;
      if (l != 0L)
        if (i != 1) {
          if (i != 2) {
            if (i != 4) {
              PropertyValuesHolder.nCallMultipleIntMethod(param1Object, l, arrayOfInt);
            } else {
              PropertyValuesHolder.nCallFourIntMethod(param1Object, l, arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
            } 
          } else {
            PropertyValuesHolder.nCallTwoIntMethod(param1Object, l, arrayOfInt[0], arrayOfInt[1]);
          } 
        } else {
          PropertyValuesHolder.nCallIntMethod(param1Object, l, arrayOfInt[0]);
        }  
    }
    
    void setupSetter(Class param1Class) {
      if (this.mJniSetter != 0L)
        return; 
      synchronized (sJNISetterPropertyMap) {
        HashMap<Object, Object> hashMap = (HashMap)sJNISetterPropertyMap.get(param1Class);
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
            this.mJniSetter = PropertyValuesHolder.nGetMultipleIntMethod(param1Class, str, i);
          } catch (NoSuchMethodError noSuchMethodError) {
            try {
              this.mJniSetter = PropertyValuesHolder.nGetMultipleIntMethod(param1Class, this.mPropertyName, i);
            } catch (NoSuchMethodError noSuchMethodError1) {}
          } 
          HashMap<Object, Object> hashMap1 = hashMap;
          if (hashMap == null) {
            hashMap1 = new HashMap<>();
            this();
            sJNISetterPropertyMap.put(param1Class, hashMap1);
          } 
          hashMap1.put(this.mPropertyName, Long.valueOf(this.mJniSetter));
        } 
        return;
      } 
    }
    
    void setupSetterAndGetter(Object param1Object) {
      setupSetter(param1Object.getClass());
    }
  }
  
  private static class PointFToFloatArray extends TypeConverter<PointF, float[]> {
    private float[] mCoordinates = new float[2];
    
    public PointFToFloatArray() {
      super(PointF.class, (Class)float[].class);
    }
    
    public float[] convert(PointF param1PointF) {
      this.mCoordinates[0] = param1PointF.x;
      this.mCoordinates[1] = param1PointF.y;
      return this.mCoordinates;
    }
  }
  
  private static class PointFToIntArray extends TypeConverter<PointF, int[]> {
    private int[] mCoordinates = new int[2];
    
    public PointFToIntArray() {
      super(PointF.class, (Class)int[].class);
    }
    
    public int[] convert(PointF param1PointF) {
      this.mCoordinates[0] = Math.round(param1PointF.x);
      this.mCoordinates[1] = Math.round(param1PointF.y);
      return this.mCoordinates;
    }
  }
  
  public static class PropertyValues {
    public DataSource dataSource = null;
    
    public Object endValue;
    
    public String propertyName;
    
    public Object startValue;
    
    public Class type;
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("property name: ");
      stringBuilder.append(this.propertyName);
      stringBuilder.append(", type: ");
      stringBuilder.append(this.type);
      stringBuilder.append(", startValue: ");
      stringBuilder.append(this.startValue.toString());
      stringBuilder.append(", endValue: ");
      stringBuilder.append(this.endValue.toString());
      return stringBuilder.toString();
    }
    
    public static interface DataSource {
      Object getValueAtFraction(float param2Float);
    }
  }
  
  public static interface DataSource {
    Object getValueAtFraction(float param1Float);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/PropertyValuesHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */