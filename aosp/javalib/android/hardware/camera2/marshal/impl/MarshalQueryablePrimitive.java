package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalHelpers;
import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Rational;
import java.nio.ByteBuffer;

public final class MarshalQueryablePrimitive<T> implements MarshalQueryable<T> {
  public Marshaler<T> createMarshaler(TypeReference<T> paramTypeReference, int paramInt) {
    return new MarshalerPrimitive(paramTypeReference, paramInt);
  }
  
  public boolean isTypeMappingSupported(TypeReference<T> paramTypeReference, int paramInt) {
    boolean bool = paramTypeReference.getType() instanceof Class;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool6 = false;
    if (bool) {
      Class<byte> clazz = (Class)paramTypeReference.getType();
      if (clazz == byte.class || clazz == Byte.class) {
        bool6 = bool5;
        if (paramInt == 0)
          bool6 = true; 
        return bool6;
      } 
      if (clazz == int.class || clazz == Integer.class) {
        bool6 = bool4;
        if (paramInt == 1)
          bool6 = true; 
        return bool6;
      } 
      if (clazz == float.class || clazz == Float.class) {
        bool6 = bool3;
        if (paramInt == 2)
          bool6 = true; 
        return bool6;
      } 
      if (clazz == long.class || clazz == Long.class) {
        bool6 = bool2;
        if (paramInt == 3)
          bool6 = true; 
        return bool6;
      } 
      if (clazz == double.class || clazz == Double.class) {
        bool6 = bool1;
        if (paramInt == 4)
          bool6 = true; 
        return bool6;
      } 
      if (clazz == Rational.class) {
        if (paramInt == 5)
          bool6 = true; 
        return bool6;
      } 
    } 
    return false;
  }
  
  private class MarshalerPrimitive extends Marshaler<T> {
    private final Class<T> mClass;
    
    protected MarshalerPrimitive(TypeReference<T> param1TypeReference, int param1Int) {
      super(MarshalQueryablePrimitive.this, param1TypeReference, param1Int);
      this.mClass = MarshalHelpers.wrapClassIfPrimitive(param1TypeReference.getRawType());
    }
    
    private void marshalPrimitive(byte param1Byte, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.put(param1Byte);
    }
    
    private void marshalPrimitive(double param1Double, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putDouble(param1Double);
    }
    
    private void marshalPrimitive(float param1Float, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putFloat(param1Float);
    }
    
    private void marshalPrimitive(int param1Int, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putInt(param1Int);
    }
    
    private void marshalPrimitive(long param1Long, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putLong(param1Long);
    }
    
    private void marshalPrimitive(Rational param1Rational, ByteBuffer param1ByteBuffer) {
      param1ByteBuffer.putInt(param1Rational.getNumerator());
      param1ByteBuffer.putInt(param1Rational.getDenominator());
    }
    
    private Object unmarshalObject(ByteBuffer param1ByteBuffer) {
      StringBuilder stringBuilder;
      int i = this.mNativeType;
      if (i != 0) {
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i != 4) {
                if (i == 5)
                  return new Rational(param1ByteBuffer.getInt(), param1ByteBuffer.getInt()); 
                stringBuilder = new StringBuilder();
                stringBuilder.append("Can't unmarshal native type ");
                stringBuilder.append(this.mNativeType);
                throw new UnsupportedOperationException(stringBuilder.toString());
              } 
              return Double.valueOf(stringBuilder.getDouble());
            } 
            return Long.valueOf(stringBuilder.getLong());
          } 
          return Float.valueOf(stringBuilder.getFloat());
        } 
        return Integer.valueOf(stringBuilder.getInt());
      } 
      return Byte.valueOf(stringBuilder.get());
    }
    
    public int calculateMarshalSize(T param1T) {
      return MarshalHelpers.getPrimitiveTypeSize(this.mNativeType);
    }
    
    public int getNativeSize() {
      return MarshalHelpers.getPrimitiveTypeSize(this.mNativeType);
    }
    
    public void marshal(T param1T, ByteBuffer param1ByteBuffer) {
      if (param1T instanceof Integer) {
        MarshalHelpers.checkNativeTypeEquals(1, this.mNativeType);
        marshalPrimitive(((Integer)param1T).intValue(), param1ByteBuffer);
      } else if (param1T instanceof Float) {
        MarshalHelpers.checkNativeTypeEquals(2, this.mNativeType);
        marshalPrimitive(((Float)param1T).floatValue(), param1ByteBuffer);
      } else if (param1T instanceof Long) {
        MarshalHelpers.checkNativeTypeEquals(3, this.mNativeType);
        marshalPrimitive(((Long)param1T).longValue(), param1ByteBuffer);
      } else if (param1T instanceof Rational) {
        MarshalHelpers.checkNativeTypeEquals(5, this.mNativeType);
        marshalPrimitive((Rational)param1T, param1ByteBuffer);
      } else if (param1T instanceof Double) {
        MarshalHelpers.checkNativeTypeEquals(4, this.mNativeType);
        marshalPrimitive(((Double)param1T).doubleValue(), param1ByteBuffer);
      } else {
        if (param1T instanceof Byte) {
          MarshalHelpers.checkNativeTypeEquals(0, this.mNativeType);
          marshalPrimitive(((Byte)param1T).byteValue(), param1ByteBuffer);
          return;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Can't marshal managed type ");
        stringBuilder.append(this.mTypeReference);
        throw new UnsupportedOperationException(stringBuilder.toString());
      } 
    }
    
    public T unmarshal(ByteBuffer param1ByteBuffer) {
      return this.mClass.cast(unmarshalObject(param1ByteBuffer));
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryablePrimitive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */