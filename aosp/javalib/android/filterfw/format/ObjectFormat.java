package android.filterfw.format;

import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.NativeBuffer;

public class ObjectFormat {
  private static int bytesPerSampleForClass(Class<?> paramClass, int paramInt) {
    if (paramInt == 2) {
      if (NativeBuffer.class.isAssignableFrom(paramClass))
        try {
          return ((NativeBuffer)paramClass.newInstance()).getElementSize();
        } catch (Exception exception) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Could not determine the size of an element in a native object-based frame of type ");
          stringBuilder1.append(paramClass);
          stringBuilder1.append("! Perhaps it is missing a default constructor?");
          throw new RuntimeException(stringBuilder1.toString());
        }  
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Native object-based formats must be of a NativeBuffer subclass! (Received class: ");
      stringBuilder.append(paramClass);
      stringBuilder.append(").");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return 1;
  }
  
  public static MutableFrameFormat fromClass(Class paramClass, int paramInt) {
    return fromClass(paramClass, 0, paramInt);
  }
  
  public static MutableFrameFormat fromClass(Class paramClass, int paramInt1, int paramInt2) {
    MutableFrameFormat mutableFrameFormat = new MutableFrameFormat(8, paramInt2);
    mutableFrameFormat.setObjectClass(getBoxedClass(paramClass));
    if (paramInt1 != 0)
      mutableFrameFormat.setDimensions(paramInt1); 
    mutableFrameFormat.setBytesPerSample(bytesPerSampleForClass(paramClass, paramInt2));
    return mutableFrameFormat;
  }
  
  public static MutableFrameFormat fromObject(Object paramObject, int paramInt) {
    if (paramObject == null) {
      paramObject = new MutableFrameFormat(8, paramInt);
    } else {
      paramObject = fromClass(paramObject.getClass(), 0, paramInt);
    } 
    return (MutableFrameFormat)paramObject;
  }
  
  public static MutableFrameFormat fromObject(Object paramObject, int paramInt1, int paramInt2) {
    if (paramObject == null) {
      paramObject = new MutableFrameFormat(8, paramInt2);
    } else {
      paramObject = fromClass(paramObject.getClass(), paramInt1, paramInt2);
    } 
    return (MutableFrameFormat)paramObject;
  }
  
  private static Class getBoxedClass(Class<boolean> paramClass) {
    if (paramClass.isPrimitive()) {
      if (paramClass == boolean.class)
        return Boolean.class; 
      if (paramClass == byte.class)
        return Byte.class; 
      if (paramClass == char.class)
        return Character.class; 
      if (paramClass == short.class)
        return Short.class; 
      if (paramClass == int.class)
        return Integer.class; 
      if (paramClass == long.class)
        return Long.class; 
      if (paramClass == float.class)
        return Float.class; 
      if (paramClass == double.class)
        return Double.class; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown primitive type: ");
      stringBuilder.append(paramClass.getSimpleName());
      stringBuilder.append("!");
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    return paramClass;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/format/ObjectFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */