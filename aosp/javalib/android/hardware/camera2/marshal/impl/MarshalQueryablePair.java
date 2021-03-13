package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.MarshalRegistry;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Pair;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

public class MarshalQueryablePair<T1, T2> implements MarshalQueryable<Pair<T1, T2>> {
  public Marshaler<Pair<T1, T2>> createMarshaler(TypeReference<Pair<T1, T2>> paramTypeReference, int paramInt) {
    return new MarshalerPair(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<Pair<T1, T2>> paramTypeReference, int paramInt) {
    return Pair.class.equals(paramTypeReference.getRawType());
  }
  
  private class MarshalerPair extends Marshaler<Pair<T1, T2>> {
    private final Class<? super Pair<T1, T2>> mClass;
    
    private final Constructor<Pair<T1, T2>> mConstructor;
    
    private final Marshaler<T1> mNestedTypeMarshalerFirst;
    
    private final Marshaler<T2> mNestedTypeMarshalerSecond;
    
    protected MarshalerPair(TypeReference<Pair<T1, T2>> param1TypeReference, int param1Int) {
      super(MarshalQueryablePair.this, param1TypeReference, param1Int);
      this.mClass = param1TypeReference.getRawType();
      try {
        ParameterizedType parameterizedType = (ParameterizedType)param1TypeReference.getType();
        Type type2 = parameterizedType.getActualTypeArguments()[0];
        this.mNestedTypeMarshalerFirst = MarshalRegistry.getMarshaler(TypeReference.createSpecializedTypeReference(type2), this.mNativeType);
        Type type1 = parameterizedType.getActualTypeArguments()[1];
        this.mNestedTypeMarshalerSecond = MarshalRegistry.getMarshaler(TypeReference.createSpecializedTypeReference(type1), this.mNativeType);
        try {
          this.mConstructor = (Constructor)this.mClass.getConstructor(new Class[] { Object.class, Object.class });
          return;
        } catch (NoSuchMethodException noSuchMethodException) {
          throw new AssertionError(noSuchMethodException);
        } 
      } catch (ClassCastException classCastException) {
        throw new AssertionError("Raw use of Pair is not supported", classCastException);
      } 
    }
    
    public int calculateMarshalSize(Pair<T1, T2> param1Pair) {
      int i = getNativeSize();
      return (i != NATIVE_SIZE_DYNAMIC) ? i : (this.mNestedTypeMarshalerFirst.calculateMarshalSize(param1Pair.first) + this.mNestedTypeMarshalerSecond.calculateMarshalSize(param1Pair.second));
    }
    
    public int getNativeSize() {
      int i = this.mNestedTypeMarshalerFirst.getNativeSize();
      int j = this.mNestedTypeMarshalerSecond.getNativeSize();
      return (i != NATIVE_SIZE_DYNAMIC && j != NATIVE_SIZE_DYNAMIC) ? (i + j) : NATIVE_SIZE_DYNAMIC;
    }
    
    public void marshal(Pair<T1, T2> param1Pair, ByteBuffer param1ByteBuffer) {
      if (param1Pair.first != null) {
        if (param1Pair.second != null) {
          this.mNestedTypeMarshalerFirst.marshal(param1Pair.first, param1ByteBuffer);
          this.mNestedTypeMarshalerSecond.marshal(param1Pair.second, param1ByteBuffer);
          return;
        } 
        throw new UnsupportedOperationException("Pair#second must not be null");
      } 
      throw new UnsupportedOperationException("Pair#first must not be null");
    }
    
    public Pair<T1, T2> unmarshal(ByteBuffer param1ByteBuffer) {
      Object object2 = this.mNestedTypeMarshalerFirst.unmarshal(param1ByteBuffer);
      Object object1 = this.mNestedTypeMarshalerSecond.unmarshal(param1ByteBuffer);
      try {
        return this.mConstructor.newInstance(new Object[] { object2, object1 });
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryablePair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */