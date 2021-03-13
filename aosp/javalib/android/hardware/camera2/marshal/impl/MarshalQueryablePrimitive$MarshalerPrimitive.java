package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalHelpers;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Rational;
import java.nio.ByteBuffer;

class MarshalerPrimitive extends Marshaler<T> {
  private final Class<T> mClass;
  
  protected MarshalerPrimitive(TypeReference<T> paramTypeReference, int paramInt) {
    super(paramMarshalQueryablePrimitive, paramTypeReference, paramInt);
    this.mClass = MarshalHelpers.wrapClassIfPrimitive(paramTypeReference.getRawType());
  }
  
  private void marshalPrimitive(byte paramByte, ByteBuffer paramByteBuffer) {
    paramByteBuffer.put(paramByte);
  }
  
  private void marshalPrimitive(double paramDouble, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putDouble(paramDouble);
  }
  
  private void marshalPrimitive(float paramFloat, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putFloat(paramFloat);
  }
  
  private void marshalPrimitive(int paramInt, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putInt(paramInt);
  }
  
  private void marshalPrimitive(long paramLong, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putLong(paramLong);
  }
  
  private void marshalPrimitive(Rational paramRational, ByteBuffer paramByteBuffer) {
    paramByteBuffer.putInt(paramRational.getNumerator());
    paramByteBuffer.putInt(paramRational.getDenominator());
  }
  
  private Object unmarshalObject(ByteBuffer paramByteBuffer) {
    StringBuilder stringBuilder;
    int i = this.mNativeType;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i == 5)
                return new Rational(paramByteBuffer.getInt(), paramByteBuffer.getInt()); 
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
  
  public int calculateMarshalSize(T paramT) {
    return MarshalHelpers.getPrimitiveTypeSize(this.mNativeType);
  }
  
  public int getNativeSize() {
    return MarshalHelpers.getPrimitiveTypeSize(this.mNativeType);
  }
  
  public void marshal(T paramT, ByteBuffer paramByteBuffer) {
    if (paramT instanceof Integer) {
      MarshalHelpers.checkNativeTypeEquals(1, this.mNativeType);
      marshalPrimitive(((Integer)paramT).intValue(), paramByteBuffer);
    } else if (paramT instanceof Float) {
      MarshalHelpers.checkNativeTypeEquals(2, this.mNativeType);
      marshalPrimitive(((Float)paramT).floatValue(), paramByteBuffer);
    } else if (paramT instanceof Long) {
      MarshalHelpers.checkNativeTypeEquals(3, this.mNativeType);
      marshalPrimitive(((Long)paramT).longValue(), paramByteBuffer);
    } else if (paramT instanceof Rational) {
      MarshalHelpers.checkNativeTypeEquals(5, this.mNativeType);
      marshalPrimitive((Rational)paramT, paramByteBuffer);
    } else if (paramT instanceof Double) {
      MarshalHelpers.checkNativeTypeEquals(4, this.mNativeType);
      marshalPrimitive(((Double)paramT).doubleValue(), paramByteBuffer);
    } else {
      if (paramT instanceof Byte) {
        MarshalHelpers.checkNativeTypeEquals(0, this.mNativeType);
        marshalPrimitive(((Byte)paramT).byteValue(), paramByteBuffer);
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can't marshal managed type ");
      stringBuilder.append(this.mTypeReference);
      throw new UnsupportedOperationException(stringBuilder.toString());
    } 
  }
  
  public T unmarshal(ByteBuffer paramByteBuffer) {
    return this.mClass.cast(unmarshalObject(paramByteBuffer));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/marshal/impl/MarshalQueryablePrimitive$MarshalerPrimitive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */