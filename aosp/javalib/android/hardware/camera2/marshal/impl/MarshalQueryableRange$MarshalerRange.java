package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalRegistry;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Range;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

class MarshalerRange extends Marshaler<Range<T>> {
  private final Class<? super Range<T>> mClass;
  
  private final Constructor<Range<T>> mConstructor;
  
  private final Marshaler<T> mNestedTypeMarshaler;
  
  protected MarshalerRange(TypeReference<Range<T>> paramTypeReference, int paramInt) {
    super(paramMarshalQueryableRange, paramTypeReference, paramInt);
    this.mClass = paramTypeReference.getRawType();
    try {
      ParameterizedType parameterizedType = (ParameterizedType)paramTypeReference.getType();
      Type type = parameterizedType.getActualTypeArguments()[0];
      this.mNestedTypeMarshaler = MarshalRegistry.getMarshaler(TypeReference.createSpecializedTypeReference(type), this.mNativeType);
      try {
        this.mConstructor = (Constructor)this.mClass.getConstructor(new Class[] { Comparable.class, Comparable.class });
        return;
      } catch (NoSuchMethodException noSuchMethodException) {
        throw new AssertionError(noSuchMethodException);
      } 
    } catch (ClassCastException classCastException) {
      throw new AssertionError("Raw use of Range is not supported", classCastException);
    } 
  }
  
  public int calculateMarshalSize(Range<T> paramRange) {
    int i = getNativeSize();
    return (i != NATIVE_SIZE_DYNAMIC) ? i : (this.mNestedTypeMarshaler.calculateMarshalSize(paramRange.getLower()) + this.mNestedTypeMarshaler.calculateMarshalSize(paramRange.getUpper()));
  }
  
  public int getNativeSize() {
    int i = this.mNestedTypeMarshaler.getNativeSize();
    return (i != NATIVE_SIZE_DYNAMIC) ? (i * 2) : NATIVE_SIZE_DYNAMIC;
  }
  
  public void marshal(Range<T> paramRange, ByteBuffer paramByteBuffer) {
    this.mNestedTypeMarshaler.marshal(paramRange.getLower(), paramByteBuffer);
    this.mNestedTypeMarshaler.marshal(paramRange.getUpper(), paramByteBuffer);
  }
  
  public Range<T> unmarshal(ByteBuffer paramByteBuffer) {
    Comparable comparable2 = (Comparable)this.mNestedTypeMarshaler.unmarshal(paramByteBuffer);
    Comparable comparable1 = (Comparable)this.mNestedTypeMarshaler.unmarshal(paramByteBuffer);
    try {
      return this.mConstructor.newInstance(new Object[] { comparable2, comparable1 });
    } catch (InstantiationException instantiationException) {
      throw new AssertionError(instantiationException);
    } catch (IllegalAccessException illegalAccessException) {
      throw new AssertionError(illegalAccessException);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new AssertionError(illegalArgumentException);
    } catch (InvocationTargetException invocationTargetException) {
      throw new AssertionError(invocationTargetException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableRange$MarshalerRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */