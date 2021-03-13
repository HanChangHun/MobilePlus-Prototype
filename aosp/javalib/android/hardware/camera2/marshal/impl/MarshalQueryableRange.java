package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.MarshalRegistry;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Range;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

public class MarshalQueryableRange<T extends Comparable<? super T>> implements MarshalQueryable<Range<T>> {
  private static final int RANGE_COUNT = 2;
  
  public Marshaler<Range<T>> createMarshaler(TypeReference<Range<T>> paramTypeReference, int paramInt) {
    return new MarshalerRange(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<Range<T>> paramTypeReference, int paramInt) {
    return Range.class.equals(paramTypeReference.getRawType());
  }
  
  private class MarshalerRange extends Marshaler<Range<T>> {
    private final Class<? super Range<T>> mClass;
    
    private final Constructor<Range<T>> mConstructor;
    
    private final Marshaler<T> mNestedTypeMarshaler;
    
    protected MarshalerRange(TypeReference<Range<T>> param1TypeReference, int param1Int) {
      super(MarshalQueryableRange.this, param1TypeReference, param1Int);
      this.mClass = param1TypeReference.getRawType();
      try {
        ParameterizedType parameterizedType = (ParameterizedType)param1TypeReference.getType();
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
    
    public int calculateMarshalSize(Range<T> param1Range) {
      int i = getNativeSize();
      return (i != NATIVE_SIZE_DYNAMIC) ? i : (this.mNestedTypeMarshaler.calculateMarshalSize(param1Range.getLower()) + this.mNestedTypeMarshaler.calculateMarshalSize(param1Range.getUpper()));
    }
    
    public int getNativeSize() {
      int i = this.mNestedTypeMarshaler.getNativeSize();
      return (i != NATIVE_SIZE_DYNAMIC) ? (i * 2) : NATIVE_SIZE_DYNAMIC;
    }
    
    public void marshal(Range<T> param1Range, ByteBuffer param1ByteBuffer) {
      this.mNestedTypeMarshaler.marshal(param1Range.getLower(), param1ByteBuffer);
      this.mNestedTypeMarshaler.marshal(param1Range.getUpper(), param1ByteBuffer);
    }
    
    public Range<T> unmarshal(ByteBuffer param1ByteBuffer) {
      Comparable comparable2 = (Comparable)this.mNestedTypeMarshaler.unmarshal(param1ByteBuffer);
      Comparable comparable1 = (Comparable)this.mNestedTypeMarshaler.unmarshal(param1ByteBuffer);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryableRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */