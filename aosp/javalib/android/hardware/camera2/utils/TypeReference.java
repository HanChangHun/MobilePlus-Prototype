package android.hardware.camera2.utils;

import com.android.internal.util.Preconditions;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public abstract class TypeReference<T> {
  private final int mHash;
  
  private final Type mType;
  
  protected TypeReference() {
    Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    this.mType = type;
    if (!containsTypeVariable(type)) {
      this.mHash = this.mType.hashCode();
      return;
    } 
    throw new IllegalArgumentException("Including a type variable in a type reference is not allowed");
  }
  
  private TypeReference(Type paramType) {
    this.mType = paramType;
    if (!containsTypeVariable(paramType)) {
      this.mHash = this.mType.hashCode();
      return;
    } 
    throw new IllegalArgumentException("Including a type variable in a type reference is not allowed");
  }
  
  public static boolean containsTypeVariable(Type paramType) {
    Type[] arrayOfType;
    boolean bool = false;
    if (paramType == null)
      return false; 
    if (paramType instanceof TypeVariable)
      return true; 
    if (paramType instanceof Class) {
      paramType = paramType;
      return ((paramType.getTypeParameters()).length != 0) ? true : containsTypeVariable(paramType.getDeclaringClass());
    } 
    if (paramType instanceof ParameterizedType) {
      arrayOfType = ((ParameterizedType)paramType).getActualTypeArguments();
      int i = arrayOfType.length;
      for (byte b = 0; b < i; b++) {
        if (containsTypeVariable(arrayOfType[b]))
          return true; 
      } 
      return false;
    } 
    if (arrayOfType instanceof WildcardType) {
      WildcardType wildcardType = (WildcardType)arrayOfType;
      if (containsTypeVariable(wildcardType.getLowerBounds()) || containsTypeVariable(wildcardType.getUpperBounds()))
        bool = true; 
      return bool;
    } 
    return false;
  }
  
  private static boolean containsTypeVariable(Type[] paramArrayOfType) {
    if (paramArrayOfType == null)
      return false; 
    int i = paramArrayOfType.length;
    for (byte b = 0; b < i; b++) {
      if (containsTypeVariable(paramArrayOfType[b]))
        return true; 
    } 
    return false;
  }
  
  public static <T> TypeReference<T> createSpecializedTypeReference(Class<T> paramClass) {
    return new SpecializedTypeReference<>(paramClass);
  }
  
  public static TypeReference<?> createSpecializedTypeReference(Type paramType) {
    return new SpecializedBaseTypeReference(paramType);
  }
  
  private static final Class<?> getArrayClass(Class<?> paramClass) {
    return Array.newInstance(paramClass, 0).getClass();
  }
  
  private static Type getComponentType(Type paramType) {
    Preconditions.checkNotNull(paramType, "type must not be null");
    if (paramType instanceof Class)
      return ((Class)paramType).getComponentType(); 
    if (paramType instanceof ParameterizedType)
      return null; 
    if (paramType instanceof GenericArrayType)
      return ((GenericArrayType)paramType).getGenericComponentType(); 
    if (!(paramType instanceof WildcardType)) {
      if (paramType instanceof TypeVariable)
        throw new AssertionError("Type variables are not allowed in type references"); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unhandled branch to get component type for type ");
      stringBuilder.append(paramType);
      throw new AssertionError(stringBuilder.toString());
    } 
    throw new UnsupportedOperationException("TODO: support wild card components");
  }
  
  private static final Class<?> getRawType(Type paramType) {
    if (paramType != null) {
      if (paramType instanceof Class)
        return (Class)paramType; 
      if (paramType instanceof ParameterizedType)
        return (Class)((ParameterizedType)paramType).getRawType(); 
      if (paramType instanceof GenericArrayType)
        return getArrayClass(getRawType(((GenericArrayType)paramType).getGenericComponentType())); 
      if (paramType instanceof WildcardType)
        return getRawType(((WildcardType)paramType).getUpperBounds()); 
      if (paramType instanceof TypeVariable)
        throw new AssertionError("Type variables are not allowed in type references"); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unhandled branch to get raw type for type ");
      stringBuilder.append(paramType);
      throw new AssertionError(stringBuilder.toString());
    } 
    throw new NullPointerException("type must not be null");
  }
  
  private static final Class<?> getRawType(Type[] paramArrayOfType) {
    if (paramArrayOfType == null)
      return null; 
    int i = paramArrayOfType.length;
    for (byte b = 0; b < i; b++) {
      Class<?> clazz = getRawType(paramArrayOfType[b]);
      if (clazz != null)
        return clazz; 
    } 
    return null;
  }
  
  private static void toString(Type paramType, StringBuilder paramStringBuilder) {
    if (paramType == null)
      return; 
    if (paramType instanceof TypeVariable) {
      paramStringBuilder.append(((TypeVariable)paramType).getName());
    } else if (paramType instanceof Class) {
      paramType = paramType;
      paramStringBuilder.append(paramType.getName());
      toString((Type[])paramType.getTypeParameters(), paramStringBuilder);
    } else if (paramType instanceof ParameterizedType) {
      paramType = paramType;
      paramStringBuilder.append(((Class)paramType.getRawType()).getName());
      toString(paramType.getActualTypeArguments(), paramStringBuilder);
    } else if (paramType instanceof GenericArrayType) {
      toString(((GenericArrayType)paramType).getGenericComponentType(), paramStringBuilder);
      paramStringBuilder.append("[]");
    } else {
      paramStringBuilder.append(paramType.toString());
    } 
  }
  
  private static void toString(Type[] paramArrayOfType, StringBuilder paramStringBuilder) {
    if (paramArrayOfType == null)
      return; 
    if (paramArrayOfType.length == 0)
      return; 
    paramStringBuilder.append("<");
    for (byte b = 0; b < paramArrayOfType.length; b++) {
      toString(paramArrayOfType[b], paramStringBuilder);
      if (b != paramArrayOfType.length - 1)
        paramStringBuilder.append(", "); 
    } 
    paramStringBuilder.append(">");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    if (paramObject instanceof TypeReference && this.mType.equals(((TypeReference)paramObject).mType)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public TypeReference<?> getComponentType() {
    Type type = getComponentType(this.mType);
    if (type != null) {
      TypeReference<?> typeReference = createSpecializedTypeReference(type);
    } else {
      type = null;
    } 
    return (TypeReference<?>)type;
  }
  
  public final Class<? super T> getRawType() {
    return (Class)getRawType(this.mType);
  }
  
  public Type getType() {
    return this.mType;
  }
  
  public int hashCode() {
    return this.mHash;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("TypeReference<");
    toString(getType(), stringBuilder);
    stringBuilder.append(">");
    return stringBuilder.toString();
  }
  
  private static class SpecializedBaseTypeReference extends TypeReference {
    public SpecializedBaseTypeReference(Type param1Type) {
      super(param1Type);
    }
  }
  
  private static class SpecializedTypeReference<T> extends TypeReference<T> {
    public SpecializedTypeReference(Class<T> param1Class) {
      super(param1Class);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/TypeReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */