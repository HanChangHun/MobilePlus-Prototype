package android.hardware.camera2.marshal;

import android.util.Rational;
import com.android.internal.util.Preconditions;

public final class MarshalHelpers {
  public static final int SIZEOF_BYTE = 1;
  
  public static final int SIZEOF_DOUBLE = 8;
  
  public static final int SIZEOF_FLOAT = 4;
  
  public static final int SIZEOF_INT32 = 4;
  
  public static final int SIZEOF_INT64 = 8;
  
  public static final int SIZEOF_RATIONAL = 8;
  
  private MarshalHelpers() {
    throw new AssertionError();
  }
  
  public static int checkNativeType(int paramInt) {
    if (paramInt == 0 || paramInt == 1 || paramInt == 2 || paramInt == 3 || paramInt == 4 || paramInt == 5)
      return paramInt; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown nativeType ");
    stringBuilder.append(paramInt);
    throw new UnsupportedOperationException(stringBuilder.toString());
  }
  
  public static int checkNativeTypeEquals(int paramInt1, int paramInt2) {
    if (paramInt1 == paramInt2)
      return paramInt2; 
    throw new UnsupportedOperationException(String.format("Expected native type %d, but got %d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
  }
  
  public static <T> Class<T> checkPrimitiveClass(Class<T> paramClass) {
    Preconditions.checkNotNull(paramClass, "klass must not be null");
    if (isPrimitiveClass(paramClass))
      return paramClass; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unsupported class '");
    stringBuilder.append(paramClass);
    stringBuilder.append("'; expected a metadata primitive class");
    throw new UnsupportedOperationException(stringBuilder.toString());
  }
  
  public static int getPrimitiveTypeSize(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt != 4) {
              if (paramInt == 5)
                return 8; 
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Unknown type, can't get size for ");
              stringBuilder.append(paramInt);
              throw new UnsupportedOperationException(stringBuilder.toString());
            } 
            return 8;
          } 
          return 8;
        } 
        return 4;
      } 
      return 4;
    } 
    return 1;
  }
  
  public static <T> boolean isPrimitiveClass(Class<T> paramClass) {
    return (paramClass == null) ? false : ((paramClass == byte.class || paramClass == Byte.class) ? true : ((paramClass == int.class || paramClass == Integer.class) ? true : ((paramClass == float.class || paramClass == Float.class) ? true : ((paramClass == long.class || paramClass == Long.class) ? true : ((paramClass == double.class || paramClass == Double.class) ? true : ((paramClass == Rational.class)))))));
  }
  
  public static String toStringNativeType(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt != 4) {
              if (paramInt != 5) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("UNKNOWN(");
                stringBuilder.append(paramInt);
                stringBuilder.append(")");
                return stringBuilder.toString();
              } 
              return "TYPE_RATIONAL";
            } 
            return "TYPE_DOUBLE";
          } 
          return "TYPE_INT64";
        } 
        return "TYPE_FLOAT";
      } 
      return "TYPE_INT32";
    } 
    return "TYPE_BYTE";
  }
  
  public static <T> Class<T> wrapClassIfPrimitive(Class<T> paramClass) {
    return (Class<T>)((paramClass == byte.class) ? Byte.class : ((paramClass == int.class) ? Integer.class : ((paramClass == float.class) ? Float.class : ((paramClass == long.class) ? Long.class : ((paramClass == double.class) ? Double.class : paramClass)))));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/MarshalHelpers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */